<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Light - Studio</title>
<%@include file="/WEB-INF/views/store/include/store-head.jsp"%>
</head>

<body>
	<%@include file="/WEB-INF/views/store/include/store-header.jsp"%>
	<main class="main flex">
		<!-- Bộ lọc sản phẩm -->
		<aside class="filter-sidebar w-1/4 p-4 bg-gray-200">
			<h2 class="text-xl font-bold mb-4">Bộ lọc sản phẩm</h2>
			<form action="store/filter" method="get">
				<!-- Screen Type -->
				<div class="mb-4">
					<label class="block font-bold mb-2">Loại màn hình</label>
					<select name="screentype" class="w-full p-2 border">
						<option value="">Chọn loại màn hình</option>
						<option value="LED">LED</option>
						<option value="LCD">LCD</option>
						<!-- Thêm các tùy chọn khác nếu cần -->
					</select>
				</div>

				<!-- Screen Size -->
				<div class="mb-4">
					<label class="block font-bold mb-2">Kích thước màn hình</label>
					<select name="screensize" class="w-full p-2 border">
						<option value="">Chọn kích thước màn hình</option>
						<option value="14">14 inch</option>
						<option value="15">15 inch</option>
						<!-- Thêm các tùy chọn khác nếu cần -->
					</select>
				</div>

				<!-- Scanning Frequency -->
				<div class="mb-4">
					<label class="block font-bold mb-2">Tần số quét</label>
					<select name="scanningfrequency" class="w-full p-2 border">
						<option value="">Chọn tần số quét</option>
						<option value="60Hz">60Hz</option>
						<option value="120Hz">120Hz</option>
						<!-- Thêm các tùy chọn khác nếu cần -->
					</select>
				</div>

				<!-- Resolution -->
				<div class="mb-4">
					<label class="block font-bold mb-2">Độ phân giải</label>
					<select name="resolution" class="w-full p-2 border">
						<option value="">Chọn độ phân giải</option>
						<option value="1080p">1080p</option>
						<option value="4K">4K</option>
						<!-- Thêm các tùy chọn khác nếu cần -->
					</select>
				</div>

				<!-- Utilities -->
				<div class="mb-4">
					<label class="block font-bold mb-2">Tiện ích</label>
					<input type="text" name="utilities" class="w-full p-2 border" placeholder="Nhập tiện ích">
				</div>

				<!-- Operating System -->
				<div class="mb-4">
					<label class="block font-bold mb-2">Hệ điều hành</label>
					<input type="text" name="operatingsystem" class="w-full p-2 border" placeholder="Nhập hệ điều hành">
				</div>

				<button type="submit" class="w-full p-2 bg-indigo-500 text-white font-bold">Áp dụng bộ lọc</button>
			</form>
		</aside>

		<!-- Nội dung chính -->
		<div class="main-content w-3/4 p-4">
			<!-- Nội dung hiện có của bạn -->
			<div class="main__slider flex justify-between">
				<!-- Nội dung slider hiện có -->
			</div>

			<!-- Các sản phẩm -->
			<div class="main__new-product section px-32 my-16">
				<!-- Nội dung sản phẩm hiện có -->
			</div>
		</div>
	</main>

	<%@include file="/WEB-INF/views/store/include/store-footer.jsp"%>
	<script
		src="<c:url value='/resources/store/assets/js/swiper-bundle.min.js'/>"></script>
	<script src="<c:url value='/resources/store/assets/js/main.js'/>"></script>
</body>
</html>
