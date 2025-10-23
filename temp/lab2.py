import os
os.environ['TF_CPP_MIN_LOG_LEVEL'] = '2'
os.environ['TF_ENABLE_ONEDNN_OPTS'] = '0'

import pandas as pd
import numpy as np
import tensorflow as tf
import matplotlib.pyplot as plt

file_path = 'data.csv'
learning_rate = 0.005
epochs = 10000
patience = 200
min_delta = 0.0001

try:
    df = pd.read_csv(file_path)
    data = np.array(df.dropna().to_numpy(), dtype=np.float32)
except FileNotFoundError as e:
    print(f"Ошибка чтения файла '{file_path}': {e}")
    exit()

mask = data[:, 13] < 50.0
data = data[mask]
np.random.seed(42)

np.random.shuffle(data)

train_size = int(0.8 * len(data))
val_size = int(0.1 * len(data))

train_data = data[:train_size]
val_data = data[train_size : train_size + val_size]
test_data = data[train_size + val_size :]

feature_indices = [0, 1, 2, 4, 5, 6, 7, 8, 9, 10, 12]
price_index = 13

x_train, y_train = train_data[:, feature_indices], train_data[:, price_index:price_index+1]
x_val, y_val = val_data[:, feature_indices], val_data[:, price_index:price_index+1]
x_test, y_test = test_data[:, feature_indices], test_data[:, price_index:price_index+1]

x_mean = np.mean(x_train, axis=0)
x_std = np.std(x_train, axis=0)

x_train_norm = (x_train - x_mean) / x_std
x_val_norm = (x_val - x_mean) / x_std
x_test_norm = (x_test - x_mean) / x_std

y_mean = np.mean(y_train)
y_std = np.std(y_train)

y_train_norm = (y_train - y_mean) / y_std
y_val_norm = (y_val - y_mean) / y_std

num_features = x_train_norm.shape[1]
A = tf.Variable(tf.random.normal([num_features, 1], dtype=tf.float32, stddev=0.1))
b = tf.Variable(tf.zeros(1, dtype=tf.float32))

def calc_predictions(x):
    x_tensor = tf.constant(x, dtype=tf.float32)
    return tf.add(b, tf.matmul(x_tensor, A))

def calc_error(y_pred, y_true):
    y_true_tensor = tf.constant(y_true, dtype=tf.float32)
    return tf.reduce_mean(tf.square(y_pred - y_true_tensor))

optimizer = tf.optimizers.SGD(learning_rate)

best_val_error = float('inf')
patience_counter = 0
best_epoch = 0
best_A = None
best_b = None

points_train_err = [[], []]
points_val_err = [[], []]

print('Начало обучения...\n')

for i in range(epochs):
    with tf.GradientTape() as tape:
        train_pred = calc_predictions(x_train_norm)
        train_error = calc_error(train_pred, y_train_norm)
    
    gradients = tape.gradient(train_error, [A, b])
    optimizer.apply_gradients(zip(gradients, [A, b]))

    if i % 10 == 0:
        val_pred = calc_rpredictions(x_val_norm)
        val_error = calc_error(val_pred, y_val_norm).numpy()

        points_train_err[0].append(i + 1)
        points_train_err[1].append(train_error.numpy())
        points_val_err[0].append(i + 1)
        points_val_err[1].append(val_error)

        if val_error < best_val_error - min_delta:
            best_val_error = val_error
            best_epoch = i
            best_A = A.numpy().copy()
            best_b = b.numpy().copy()
            patience_counter = 0
        else:
            patience_counter += 10
        
        if (i % 100 == 0):
            print(f"Epoch {i}: Train Error = {train_error.numpy():.4f}, Val Error = {val_error:.4f}")

    if patience_counter >= patience:
        print(f"\n\tРанняя остановка на эпохе {i}.")
        print(f"\tЛучшая ошибка валидации: {best_val_error:.4f} на эпохе {best_epoch}.")
        break

if best_A is not None:
    A.assign(best_A)
    b.assign(best_b)
    print(f"\tВеса модели восстановлены к состоянию на эпохе {best_epoch}.")

test_pred_norm = calc_predictions(x_test_norm)
test_pred_denorm = test_pred_norm.numpy() * y_std + y_mean

y_true = y_test.flatten()
y_pred = test_pred_denorm.flatten()

ss_res = np.sum((y_true - y_pred) ** 2)
ss_tot = np.sum((y_true - np.mean(y_true)) ** 2)
r2 = 1 - (ss_res / ss_tot)

print(f'\nФинальный R^2 на тестовой выборке: {r2:.4f}')

points_test_err = [[], []]
for i in range(len(y_test)):
    err = abs(y_test[i, 0] - test_pred_denorm[i, 0])
    points_test_err[0].append(i + 1)
    points_test_err[1].append(err)

fig = plt.figure(figsize=(15, 12))
plt.suptitle('Результаты обучения и тестирования модели', fontsize=16)

ax1 = plt.subplot(2, 2, 1)
ax1.plot(points_train_err[0], points_train_err[1], 'b-', label='Train Error')
ax1.plot(points_val_err[0], points_val_err[1], 'r--', label='Validation Error')
if best_epoch > 0:
    ax1.axvline(x=best_epoch, color='g', linestyle=':', linewidth=2, label=f'Best Epoch ({best_epoch})')
ax1.set_title('Ошибка обучения и валидации')
ax1.set_xlabel('Эпохи')
ax1.set_ylabel('MSE')
ax1.legend()
ax1.grid(True)
ax1.set_xlim(left=0)

ax2 = plt.subplot(2, 2, 2)
ax2.scatter(y_test, test_pred_denorm, alpha=0.7, edgecolors='k', linewidth=0.5)
min_val = min(y_test.min(), test_pred_denorm.min())
max_val = max(y_test.max(), test_pred_denorm.max())
ax2.plot([min_val, max_val], [min_val, max_val], 'r--', label='Идеальное предсказание')
ax2.set_title(f'Предсказания vs Реальные значения (R²={r2:.3f})')
ax2.set_xlabel('Реальные цены')
ax2.set_ylabel('Предсказанные цены')
ax2.legend()
ax2.grid(True)
ax2.axis('equal')

ax3 = plt.subplot(2, 2, 3)
ax3.plot(points_test_err[0], points_test_err[1], 'r-')
mean_err = np.mean(points_test_err[1])
ax3.axhline(y=mean_err, color='b', linestyle='--', label=f'Средняя ошибка: {mean_err:.2f}')
ax3.set_title('Абсолютная ошибка на тестовой выборке')
ax3.set_xlabel('Пример')
ax3.set_ylabel('Абсолютная ошибка')
ax3.legend()
ax3.grid(True)

plt.tight_layout(rect=[0, 0, 1, 0.96])
plt.show()