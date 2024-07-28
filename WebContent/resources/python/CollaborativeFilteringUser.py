import numpy as np # linear algebra
import pandas as pd # data processing, CSV file I/O (e.g. pd.read_csv)
from surprise import Dataset, Reader, KNNBasic, SVD, NMF
from surprise.model_selection import train_test_split
from surprise.model_selection.validation import cross_validate
from surprise.accuracy import rmse, mae
from sklearn.metrics import precision_score, recall_score, f1_score
from tabulate import tabulate
from surprise.model_selection import GridSearchCV
import sys

# Load the dataset
rt = pd.read_csv('D:\\JAVA\\Eclipse\\Doanthuctap\\webbantivi\\WebContent\\resources\\python\\dataFavorite\\ratings.csv')
p = pd.read_csv('D:\\JAVA\\Eclipse\\Doanthuctap\\webbantivi\\WebContent\\resources\\python\\dataFavorite\\products.csv')

# use relevant columns
df_rt = rt[['customer_id','product_id', 'rating']]
df_p = p[['product_id']]

# Create a Surprise Dataset
reader = Reader(rating_scale=(0, 5))    # Định nghĩa thang điểm đánh giá từ 0 đến 5
surprise_data = Dataset.load_from_df(df_rt, reader) # Tạo Dataset từ DataFrame sử dụng Surprise

# chia dữ liệu thành hai tập huấn luyện và kiểm tra để đánh giá hiệu suất của mô hình
trainset, testset = train_test_split(surprise_data, test_size=.2, random_state=42)  # Chia tập dữ liệu

# # Chọn thuật toán SVD
# Huấn luyện mô hình SVD và thực hiện cross-validation:
model_svd = SVD()
cv_results = cross_validate(model_svd, surprise_data, measures=['RMSE', 'MAE'], cv=5, verbose=True)


# Train the model on the whole train dataset
trainset = surprise_data.build_full_trainset()  # Tạo tập huấn luyện từ toàn bộ dữ liệu
model_svd.fit(trainset) # Huấn luyện mô hình trên tập huấn luyện

# Make predictions for the test set
predictions = model_svd.test(testset)   # Dự đoán rating trên tập kiểm tra

# Convert Surprise Prediction objects to a format suitable for sklearn evaluation metrics
true_labels = np.array([pred.r_ui for pred in predictions]) # Lấy ra giá trị thực tế
predicted_labels = np.array([pred.est for pred in predictions]) # Lấy ra giá trị dự đoán

# Binarize the true labels and predicted labels (e.g., using a threshold)
threshold = 3   # Ngưỡng để nhị phân hóa nhãn, nếu giá trị rating thực tế hoặc dự đoán lớn hơn hoặc bằng 3, sẽ được gán nhãn 1, ngược lại sẽ là 0
binarized_true_labels = (true_labels >= threshold).astype(int)  # Nhãn thực tế nhị phân
binarized_predicted_labels = (predicted_labels >= threshold).astype(int)    # Nhãn dự đoán nhị phân

# Tính toán các metrics(số liệu) đánh giá: precision(độ chính xác), recall, F1-score, RMSE và MAE:
precision = precision_score(binarized_true_labels, binarized_predicted_labels)
recall = recall_score(binarized_true_labels, binarized_predicted_labels)
f1 = f1_score(binarized_true_labels, binarized_predicted_labels)
rmse_score = rmse(predictions)
mae_score = mae(predictions)

# Define the parameter grid for hyperparameter tuning
param_grid = {'n_factors': [50, 100],   # Số lượng yếu tố latent
              'n_epochs': [20, 30, 50],      # Số lượng vòng lặp huấn luyện
              'lr_all': [0.005, 0.01, 0.001, 0.002], # Tốc độ học cho quá trình huấn luyện.
              'reg_all': [0.05, 0.1, 0.01, 0.2]}   # Hệ số regularization để điều chỉnh độ phức tạp của mô hình và giảm overfitting

# Use GridSearchCV to find the best hyperparameters
model_tune = GridSearchCV(SVD, param_grid, measures=['rmse', 'mae'], cv=5, n_jobs=-1)   # Tìm kiếm siêu tham số
model_tune.fit(surprise_data)   # Huấn luyện mô hình với siêu tham số tốt nhất

# Huấn luyện mô hình với siêu tham số tốt nhất và đánh giá:
best_rmse = model_tune.best_score['rmse']
best_mae = model_tune.best_score['mae']
best_params = model_tune.best_params['rmse']    # Lấy ra các siêu tham số tốt nhất


best_model = SVD(**best_params) # Tạo mô hình mới với siêu tham số tốt nhất
best_model.fit(surprise_data.build_full_trainset()) # Huấn luyện mô hình trên toàn bộ tập huấn luyện
predictions = best_model.test(testset)  # Dự đoán trên tập kiểm tra

# Convert Surprise Prediction objects to a format suitable for sklearn evaluation metrics
true_labels = np.array([pred.r_ui for pred in predictions])
predicted_labels = np.array([pred.est for pred in predictions])

# Binarize the true labels and predicted labels (e.g., using a threshold)
threshold = 3
binarized_true_labels = (true_labels >= threshold).astype(int)
binarized_predicted_labels = (predicted_labels >= threshold).astype(int)

# Compute precision, recall, and F1-score
precision = precision_score(binarized_true_labels, binarized_predicted_labels)
recall = recall_score(binarized_true_labels, binarized_predicted_labels)
f1 = f1_score(binarized_true_labels, binarized_predicted_labels)
rmse_score = rmse(predictions)
mae_score = mae(predictions)

# Display the evaluation metrics
# print("Evaluation Metrics of SVD:")
# print(f"Precision: {precision}")
# print(f"Recall: {recall}")
# print(f"F1-score: {f1}")
# print(f"RMSE: {rmse_score}")
# print(f"MAE: {mae_score}")
# print("===================================")


# Top-10 recommenations for a user

#  top-N recommendations for a user
# user_id = 'U0013'
user_id = str(sys.argv[1])
n = 4  # Number of recommendations

print(f"\nRecommendations for User {user_id}:")

# Get a list of all unique product IDs
all_product_ids = df_rt['product_id'].unique()  # Tất cả sản phẩm có sẵn

# Remove products that the user has already rated
products_rated_by_user = df_rt[df_rt['customer_id'] == user_id]['product_id'].values    # Sản phẩm đã được người dùng đánh giá
products_to_predict = np.setdiff1d(all_product_ids, products_rated_by_user)         # Sản phẩm chưa được người dùng đánh giá

# Dự đoán rating cho các sản phẩm chưa được đánh giá
predictions = [best_model.predict(user_id, product_id) for product_id in products_to_predict]

# Sắp xếp theo thứ tự giảm dần của rating dự đoán
predictions.sort(key=lambda x: x.est, reverse=True)


top_n_recommendations = [prediction.iid for prediction in predictions[:n]]
print(top_n_recommendations)
