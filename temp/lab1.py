import os
os.environ['TF_CPP_MIN_LOG_LEVEL'] = '2'
os.environ['TF_ENABLE_ONEDNN_OPTS'] = '0'

import csv
import numpy as np
import tensorflow as tf
import matplotlib.pyplot as plt

file_path = 'data.csv'
data_list = []
try:
    with open(file_path, 'r') as csv_file:
        reader = csv.reader(csv_file)
        next(reader)
        for row in reader:
            if row:
                data_list.append(list(map(float, row)))
    data = np.array(data_list, dtype=np.float64)
except (FileNotFoundError, ValueError) as e:
    print(f"Ошибка чтения файла '{file_path}': {e}")
    exit()

num_runs = 5
epochs = 1000
learning_rate = 0.01
r2_list = []

best_r2 = -999
best_points = None
best_points_test = None
best_points_test_err = None

for run in range(num_runs):
    np.random.shuffle(data)
    
    train_features = data[:450, [0, 1, 2, 4, 5, 6, 7, 8, 9, 10, 12]]
    train_prices = data[:450, 13:14]
    
    test_features = data[450:, [0, 1, 2, 4, 5, 6, 7, 8, 9, 10, 12]]
    test_prices = data[450:, 13:14]
    
    train_mean = np.mean(train_features, axis=0)
    train_std = np.std(train_features, axis=0)
    
    train_features_normalized = (train_features - train_mean) / (train_std)
    test_features_normalized = (test_features - train_mean) / (train_std)
    
    train_prices_mean = np.mean(train_prices)
    train_prices_std = np.std(train_prices)
    train_prices_normalized = (train_prices - train_prices_mean) / train_prices_std
    test_prices_normalized = (test_prices - train_prices_mean) / train_prices_std
    
    num_features = train_features_normalized.shape[1]
    
    A = tf.Variable(np.random.rand(num_features, 1), dtype=tf.float64)
    b = tf.Variable(tf.zeros(1, dtype=tf.float64))
    
    points = [[], []]
    
    def calc_predictions(x):
        return tf.add(b, tf.matmul(x, A))
    
    def calc_error(y_pred, y_true):
        return tf.reduce_mean(tf.square(y_pred - y_true))
    
    optimizer = tf.optimizers.SGD(learning_rate)
    
    if run == 0:
        first_house_features = data[0, [0, 1, 2, 4, 5, 6, 7, 8, 9, 10, 12]]
        first_house_real_price = data[0, 13]
        
        first_house_norm = (first_house_features - train_mean) / train_std
        first_house_tensor = tf.reshape(tf.constant(first_house_norm, dtype=tf.float64), (1, -1))
        
        pred_norm_before = calc_predictions(first_house_tensor).numpy()[0, 0]
        pred_price_before = pred_norm_before * train_prices_std + train_prices_mean
        
        print(f'До обучения: {pred_price_before:.2f}')
        print('Начало обучения...\n')
    
    for i in range(epochs):
        with tf.GradientTape() as tape:
            error = calc_error(calc_predictions(train_features_normalized), train_prices_normalized)
        
        gradients = tape.gradient(error, [A, b])
        optimizer.apply_gradients(zip(gradients, [A, b]))
        
        if i % 10 == 0:
            points[0].append(i + 1)
            points[1].append(error.numpy())
    
    predictions_test_normalized = calc_predictions(test_features_normalized)
    test_error = calc_error(predictions_test_normalized, test_prices_normalized)
    
    predictions_test = predictions_test_normalized.numpy() * train_prices_std + train_prices_mean
    
    y_true = test_prices.flatten()
    y_pred = predictions_test.flatten()
    
    ss_res = np.sum((y_true - y_pred) ** 2)
    ss_tot = np.sum((y_true - np.mean(y_true)) ** 2)
    
    r2 = 1 - ss_res / ss_tot
    r2_list.append(r2)
    
    print(f'Run {run + 1}: R^2 = {r2:.4f}')
    
    if r2 > best_r2:
        best_r2 = r2
        best_points = points
        
        points_test = [[], [], []]
        points_test_err = [[], []]
        
        for i in range(len(test_prices)):
            err = abs(test_prices[i, 0] - predictions_test[i, 0])
            points_test_err[0].append(i + 1)
            points_test_err[1].append(err)
            
            points_test[0].append(i + 1)
            points_test[1].append(test_prices[i, 0])
            points_test[2].append(predictions_test[i, 0])
        
        best_points_test = points_test
        best_points_test_err = points_test_err
        
        best_first_house_real = data[0, 13]
        
        first_house_features = data[0, [0, 1, 2, 4, 5, 6, 7, 8, 9, 10, 12]]
        first_house_norm = (first_house_features - train_mean) / train_std
        first_house_tensor = tf.reshape(tf.constant(first_house_norm, dtype=tf.float64), (1, -1))
        
        pred_norm_after = calc_predictions(first_house_tensor).numpy()[0, 0]
        best_pred_price = pred_norm_after * train_prices_std + train_prices_mean

mean_r2 = np.mean(r2_list)
print(f'\nСредний R^2 из {num_runs} запусков: {mean_r2:.4f}')
print(f'Лучший R^2: {best_r2:.4f}')
print(f'\nРеальная цена первого дома: {best_first_house_real:.2f}')
print(f'После обучения: {best_pred_price:.2f}')

fig, axs = plt.subplots(3, 1, figsize=(10, 12), constrained_layout=True)

axs[0].plot(best_points[0], best_points[1], 'r--')
axs[0].set_title(f'Ошибка во время обучения (Лучший запуск, R^2={best_r2:.4f})')
axs[0].set_xlabel('Эпохи')
axs[0].set_ylabel('Ошибка')
axs[0].grid(True)
axs[0].set_xlim(0, epochs)

axs[1].plot(best_points_test[0], best_points_test[2], 'b.', label='Предсказано')
axs[1].plot(best_points_test[0], best_points_test[1], 'r+', label='Реальное')
axs[1].set_title('Результаты на тестовой выборке')
axs[1].set_ylabel('Цена')
axs[1].legend()
axs[1].grid(True)

axs[2].plot(best_points_test_err[0], best_points_test_err[1], 'r-')
axs[2].set_title('Абсолютная ошибка на тестовой выборке')
axs[2].set_xlabel('Пример')
axs[2].set_ylabel('Ошибка')
axs[2].grid(True)

plt.show()