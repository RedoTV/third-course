import os
os.environ['TF_CPP_MIN_LOG_LEVEL'] = '2'
os.environ['TF_ENABLE_ONEDNN_OPTS'] = '0'

import pandas as pd
import numpy as np
import tensorflow as tf
import matplotlib.pyplot as plt
from sklearn.preprocessing import LabelEncoder

file_path = 'car_evaluation.csv'

df = pd.read_csv(file_path, header=None,
                 names=['buying', 'maint', 'doors', 'persons', 'lug_boot', 'safety', 'class'])

le_dict = {}
for column in df.columns[:-1]:
    le = LabelEncoder()
    df[column] = le.fit_transform(df[column])
    le_dict[column] = le

df['class_binary'] = df['class'].apply(lambda x: 0.0 if x == 'unacc' else 1.0)

data = np.array(df[['buying', 'maint', 'doors', 'persons', 'lug_boot', 'safety', 'class_binary']].to_numpy(), dtype=np.float32)

train_size = int(0.8 * len(data))
val_size = int(0.1 * len(data))

x_train = data[:train_size, :-1]
y_train = data[:train_size, -1:]
x_val = data[train_size:train_size+val_size, :-1]
y_val = data[train_size:train_size+val_size, -1:]
x_test = data[train_size+val_size:, :-1]
y_test = data[train_size+val_size:, -1:]

x_mean = x_train.mean(axis=0)
x_std = x_train.std(axis=0)

x_train = (x_train - x_mean) / x_std
x_val = (x_val - x_mean) / x_std
x_test = (x_test - x_mean) / x_std

num_features = x_train.shape[1]

a = tf.Variable(np.random.randn(num_features, 1), dtype=tf.float32)
b = tf.Variable(tf.zeros([1], dtype=tf.float32))

a0 = a.numpy().copy()
b0 = b.numpy().copy()

best_a = None
best_b = None
best_epoch = 0

def calc_logits(x):
    return tf.add(b, tf.matmul(x, a))

def calc_predictions(x):
    return tf.nn.sigmoid(calc_logits(x))

def calc_error(x, targets, class_weight=5.0):
    loss = tf.nn.sigmoid_cross_entropy_with_logits(
        logits=calc_logits(x),
        labels=targets
    )
    weights = tf.where(targets == 1.0, class_weight, 1.0)
    weighted_loss = loss * weights
    return tf.reduce_mean(weighted_loss, axis=None)

learning_rate = 0.03
optimizer = tf.keras.optimizers.SGD(learning_rate)

epochs = 5000
patience = 100
best_val_error = float('inf')
patience_counter = 0
min_delta = 0.00005

points = [[], []]
points_val = [[], []]
points_test = [[], [], [], []]
points_test_err = [[], []]

print("Обучение логистической регрессии")

for i in range(epochs):
    with tf.GradientTape() as tape:
        error = calc_error(x_train, y_train)
    
    gradients = tape.gradient(error, [a, b])
    optimizer.apply_gradients(zip(gradients, [a, b]))
    
    if i % 10 == 0:
        points[0].append(i)
        points[1].append(error.numpy())
        
        val_error = calc_error(x_val, y_val)
        points_val[0].append(i)
        points_val[1].append(val_error.numpy())
        
        if val_error.numpy() < best_val_error - min_delta:
            best_val_error = val_error.numpy()
            best_epoch = i
            best_a = a.numpy().copy()
            best_b = b.numpy().copy()
            patience_counter = 0
        else:
            patience_counter += 10
        
        if (i % 100 == 0):
            test_error = calc_error(x_test, y_test)
            print(f"Epoch {i}: Train = {error:.4f}, Val = {val_error:.4f}, Test = {test_error:.4f}")
    
    if patience_counter >= patience:
        print(f"\n\tРанняя остановка обучения на эпохе {i}")
        print(f"\tЛучшая валидационная ошибка: {best_val_error:.4f} на эпохе {best_epoch}")
        break

if best_a is not None:
    a.assign(best_a)
    b.assign(best_b)
    print(f"\tВеса восстановлены к эпохе {best_epoch}")

predictions = calc_predictions(x_test).numpy()
test_error = calc_error(x_test, y_test)

predictions_classes = []
right = 0

for i in range(len(predictions)):
    predictions_classes.append(1.0 if predictions[i] > 0.5 else 0.0)
    if np.allclose(predictions_classes[i], y_test[i]):
        right += 1

predictions_classes = np.array(predictions_classes).flatten()
y_test_flat = y_test.flatten()

tp = np.sum((predictions_classes == 1) & (y_test_flat == 1))
tn = np.sum((predictions_classes == 0) & (y_test_flat == 0))
fp = np.sum((predictions_classes == 1) & (y_test_flat == 0))
fn = np.sum((predictions_classes == 0) & (y_test_flat == 1))

precision = tp / (tp + fp) if (tp + fp) > 0 else 0.0
recall = tp / (tp + fn) if (tp + fn) > 0 else 0.0
f1_score = 2 * (precision * recall) / (precision + recall) if (precision + recall) > 0 else 0.0

print("Метрики классификации:")
print(f"Accuracy: {right / len(predictions):.4f} ({right} из {len(predictions)} верно предсказано)")
print(f"Precision: {precision:.4f}")
print(f"Recall: {recall:.4f}")
print(f"F1-Score: {f1_score:.4f}")

for i in range(len(y_test)):
    err = abs(y_test[i] - predictions[i])
    points_test_err[0].append(i + 1)
    points_test_err[1].append(err)
    points_test[0].append(i + 1)
    points_test[1].append(y_test[i])
    points_test[2].append(predictions[i])
    points_test[3].append(predictions_classes[i])

plt.figure(figsize=(15, 10))

plt.subplot(2, 2, 1)
plt.plot(points[0], points[1], 'b-', linewidth=2, label='Train Error')
plt.plot(points_val[0], points_val[1], 'r--', linewidth=2, label='Validation Error')
plt.axvline(x=best_epoch, color='green', linestyle=':', linewidth=2, label=f'Best Epoch ({best_epoch})')
plt.title('Training and Validation Error (Cross Entropy)')
plt.ylabel('Error')
plt.xlabel('Epoch')
plt.xlim(0, max(points[0]))
plt.legend()
plt.grid(True, alpha=0.3)

plt.subplot(2, 2, 2)
plt.plot(points_test_err[0], points_test_err[1], 'r-', linewidth=2)
plt.xlabel('n')
plt.ylabel('diff')
plt.title('Absolute Error')
plt.grid(True, alpha=0.3)

plt.subplot(2, 1, 2)
plt.plot(points_test[0], points_test[1], 'bo', label='Test classes')
plt.plot(points_test[0], points_test[3], 'gx', label='Predicted classes')
plt.title('Testing result')
plt.ylabel('pred vs test')
plt.xlabel('n')
plt.legend()
plt.grid(True, alpha=0.3)

plt.tight_layout()
plt.show()