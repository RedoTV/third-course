import numpy as np
import matplotlib.pyplot as plt
from tensorflow.keras.datasets import mnist

(x_train, y_train), (x_test, y_test) = mnist.load_data()

x_train = x_train.reshape(-1, 784) / 255.0
x_test = x_test.reshape(-1, 784) / 255.0

def one_hot(y, num_classes=10):
    return np.eye(num_classes)[y]

y_train_oh = one_hot(y_train)
y_test_oh = one_hot(y_test)

def sigmoid(x):
    return 1 / (1 + np.exp(-np.clip(x, -500, 500)))

def sigmoid_derivative(x):
    s = sigmoid(x)
    return s * (1 - s)

def softmax(x):
    exp_x = np.exp(x - np.max(x, axis=1, keepdims=True))
    return exp_x / np.sum(exp_x, axis=1, keepdims=True)

class MLP:
    def __init__(self, layer_sizes):
        """
        layer_sizes: список размеров слоёв, например [784, 128, 64, 10]
        784 - входной слой, 10 - выходной, остальное - скрытые
        """
        self.layer_sizes = layer_sizes
        self.weights = []
        self.biases = []
        
        np.random.seed(42)
        for i in range(len(layer_sizes) - 1):
            w = np.random.randn(layer_sizes[i], layer_sizes[i+1]) * 0.1
            b = np.zeros((1, layer_sizes[i+1]))
            self.weights.append(w)
            self.biases.append(b)
    
    def forward(self, X):
        nets = []
        outs = [X]
        
        for i in range(len(self.weights)):
            net = outs[-1].dot(self.weights[i]) - self.biases[i]
            nets.append(net)
            
            if i == len(self.weights) - 1:
                out = softmax(net)
            else:
                out = sigmoid(net)
            outs.append(out)
        
        return nets, outs
    
    def backward(self, X, Y, nets, outs):
        m = X.shape[0]
        gradients_w = []
        gradients_b = []
        
        dE_dnet = outs[-1] - Y
        
        for i in range(len(self.weights) - 1, -1, -1):
            dw = outs[i].T.dot(dE_dnet) / m
            db = -np.sum(dE_dnet, axis=0, keepdims=True) / m
            
            gradients_w.insert(0, dw)
            gradients_b.insert(0, db)
            
            if i > 0:
                dE_dout = dE_dnet.dot(self.weights[i].T)
                dE_dnet = dE_dout * sigmoid_derivative(nets[i-1])
        
        return gradients_w, gradients_b
    
    def update_weights(self, gradients_w, gradients_b, lr):
        for i in range(len(self.weights)):
            self.weights[i] -= lr * gradients_w[i]
            self.biases[i] -= lr * gradients_b[i]
    
    def predict(self, X):
        _, outs = self.forward(X)
        return outs[-1]

def train(model, X, Y, X_val, Y_val, epochs=10, batch_size=128, lr=0.1):
    history = {'train_loss': [], 'train_acc': [], 'val_acc': []}
    
    for epoch in range(epochs):
        indices = np.random.permutation(X.shape[0])
        X_shuffled = X[indices]
        Y_shuffled = Y[indices]
        
        epoch_loss = 0
        batches = 0
        
        for i in range(0, X.shape[0], batch_size):
            X_batch = X_shuffled[i:i+batch_size]
            Y_batch = Y_shuffled[i:i+batch_size]
            
            nets, outs = model.forward(X_batch)
            loss = -np.mean(np.sum(Y_batch * np.log(outs[-1] + 1e-8), axis=1))
            epoch_loss += loss
            batches += 1
            
            gradients_w, gradients_b = model.backward(X_batch, Y_batch, nets, outs)
            model.update_weights(gradients_w, gradients_b, lr)
        
        train_pred = model.predict(X)
        train_acc = np.mean(np.argmax(train_pred, axis=1) == np.argmax(Y, axis=1))
        
        val_pred = model.predict(X_val)
        val_acc = np.mean(np.argmax(val_pred, axis=1) == np.argmax(Y_val, axis=1))
        
        history['train_loss'].append(epoch_loss / batches)
        history['train_acc'].append(train_acc)
        history['val_acc'].append(val_acc)
        
        print(f"Эпоха {epoch+1}/{epochs}: Loss={history['train_loss'][-1]:.4f}, Train Acc={train_acc:.4f}, Val Acc={val_acc:.4f}")
    
    return history

print("Начало обучения многослойного персептрона...")
print("784 - входной слой (фиксированный для MNIST)")
print("128, 64 - скрытые слои (можно добавлять/убирать/менять размеры)")
print("10 - выходной слой (количество классов)\n")

model = MLP([784, 128, 64, 10])
history = train(model, x_train, y_train_oh, x_test, y_test_oh, epochs=10, batch_size=128, lr=0.1)

test_pred = model.predict(x_test)
test_acc = np.mean(np.argmax(test_pred, axis=1) == y_test)
print(f"\nИтоговая точность на тесте: {test_acc:.4f}")

plt.figure(figsize=(12, 4))

plt.subplot(1, 2, 1)
plt.plot(history['train_acc'], label='Train Accuracy', marker='o')
plt.plot(history['val_acc'], label='Val Accuracy', marker='s')
plt.xlabel('Эпоха')
plt.ylabel('Точность')
plt.title('Точность модели')
plt.legend()
plt.grid(True)

plt.subplot(1, 2, 2)
plt.plot(history['train_loss'], label='Train Loss', marker='o')
plt.xlabel('Эпоха')
plt.ylabel('Cross-Entropy Loss')
plt.title('Функция потерь')
plt.legend()
plt.grid(True)

plt.tight_layout()
plt.show()

predictions = np.argmax(test_pred[:10], axis=1)
plt.figure(figsize=(15, 3))
for i in range(10):
    plt.subplot(2, 5, i+1)
    plt.imshow(x_test[i].reshape(28, 28), cmap='gray')
    color = 'green' if predictions[i] == y_test[i] else 'red'
    plt.title(f'Предсказано: {predictions[i]}\nИстина: {y_test[i]}', color=color)
    plt.axis('off')
plt.tight_layout()
plt.show()