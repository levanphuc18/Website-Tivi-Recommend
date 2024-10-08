<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
<title>Lịch sử mua hàng</title>
<%@include file="/WEB-INF/views/store/include/store-head.jsp"%>
<style type="text/css">
.star {
	color: gray;
	font-size: 24px;
	cursor: pointer;
}

.star.selected {
	color: gold;
}

.error::before {
	content: '';
	display: block;
	width: 0;
	height: 0;
	border-left: 8px solid transparent;
	border-right: 8px solid transparent;
	border-bottom: 8px solid #FB3F3F;
	position: absolute;
	top: -8px;
	left: 10px;
}

.error {
	cursor: pointer;
	position: relative;
	background-color: #FB3F3F;
	padding: 2px 8px;
	width: fit-content;
	border-radius: 4px;
	transform: translateY(-8px);
	color: white;
}

.ordered-list {
	height: 660px;
	overflow-y: scroll;
}
</style>
</head>

<body>
	<%@include file="/WEB-INF/views/store/include/store-header.jsp"%>
	<main class="main">
		<div class="main__new-product px-32 mb-12 mt-6">
			<div class="mb-5 font-bold text-xl">
				<a href="${applicationScope.userInfoPage}"
					class="hover:text-indigo-500 transition-colors">Hồ sơ cá nhân</a>
				> <span class="text-indigo-500">Lịch sử mua hàng</span>
			</div>
			<div class="flex justify-between info-wrapper">
				<div class="p-4 pt-0 border-2 mr-4 border-gray-200 user-info-nav">
					<div
						class="my-2 mt-3 text-center text-indigo-500 text-xl font-bold">${customerEntity.firstname}
						${customerEntity.lastname}</div>
					<div class="mb-1">
						<a href="${applicationScope.userInfoPage}"
							class="hover:text-indigo-500 transition-colors">Thông tin
							tài khoản</a>
					</div>
					<div class="mb-1">
						<a href="${applicationScope.changePasswordPage}"
							class="hover:text-indigo-500 transition-colors">Đổi mật
							khẩu</a>
					</div>
					<div class="mb-1">
						<a href="${applicationScope.shoppingCartPage}"
							class="hover:text-indigo-500 transition-colors">Giỏ hàng</a>
					</div>
					<div class="mb-1">
						<a href="${applicationScope.userFavoritePage}"
							class="hover:text-indigo-500 transition-colors">Sản phẩm
							đã thích</a>
					</div>
					<div class="mb-1">
						<span href="${applicationScope.orderHistoryPage}"
							class="text-indigo-500 transition-colors">Lịch sử mua
							hàng</span>
					</div>
					<div class="mb-1">
						<a href="${applicationScope.logOutPage}"
							class="hover:text-indigo-500 transition-colors">Đăng xuất</a>
					</div>
				</div>
				<div class="sign-up-form-container border-2 border-gray-200 flex-1">
					<div class="my-3 px-4 font-bold">ĐƠN HÀNG ĐÃ MUA</div>
					<div class="px-4">
						<hr class="">
					</div>
					<div class="px-4 ordered-list">
						<c:if test="${listOrder.size()==0}">
							<p>Bạn chưa có đơn hàng nào.</p>
							<p class="mb-2">
								<a href="${applicationScope.storeIndexPage}"
									class="inline-block font-bold text-indigo-500 hover:text-indigo-300 transition-colors">Quay
									lại trang chủ</a> để mua sắm.
							</p>
						</c:if>

						<div class="order-row flex justify-between">
							<div class="order-cell flex-1 p-2 border font-bold">Sản
								phẩm</div>

							<div
								class="order-cell w-1/6 p-2 border text-center align-middle font-bold">Số
								lượng</div>
							<div
								class="order-cell w-1/5 p-2 border text-center align-middle font-bold">Thành
								tiền</div>
							<div
								class="order-cell w-1/6 p-2 border text-center align-middle font-bold">Đánh
								gia</div>
						</div>
						<c:forEach var="order" items="${listOrder}" varStatus="loop">
							<p class="p-2 text-indigo-500 font-bold">${loop.index+1}.</p>

							<c:forEach var="c" items="${order.orderDetails}">
								<div class="order-row flex">
									<div class="order-cell flex-1 p-2 border">
										<a
											href="${applicationScope.productDetailPage}/${c.product.id}"
											class="hover:text-indigo-500">${c.product.name}</a><br>
										<div class="cart-list__price text-sm">
											Đơn giá: <span class="font-bold"><fmt:setLocale
													value="vi_VN" scope="session" /> <fmt:formatNumber
													value="${c.product.price}" type="currency" /></span>
										</div>
									</div>

									<div
										class="order-cell w-1/6 p-2 border text-center align-middle">${c.quantity}</div>
									<div
										class="order-cell w-1/5 p-2 border text-center align-middle">
										<fmt:setLocale value="vi_VN" scope="session" />
										<fmt:formatNumber value="${c.quantity*c.product.price}"
											type="currency" />
									</div>

									<div
										class="order-cell w-1/6 p-2 border text-center align-middle">
										<div class="product-ratings" data-product-id="${c.product.id}"
     data-order-id="${order.id}"
     data-current-rating="${productOrderRatings[c.product.id][order.id]}">
    <span class="star" data-value="1" onclick="submitRating(1, this)">★</span>
    <span class="star" data-value="2" onclick="submitRating(2, this)">★</span>
    <span class="star" data-value="3" onclick="submitRating(3, this)">★</span>
    <span class="star" data-value="4" onclick="submitRating(4, this)">★</span>
    <span class="star" data-value="5" onclick="submitRating(5, this)">★</span>
</div>



									</div>

								</div>
							</c:forEach>

							<div class="w-full flex justify-between my-1 p-2">
								<span class="">Tổng cộng:</span><span class="font-bold"><fmt:setLocale
										value="vi_VN" scope="session" /> <fmt:formatNumber
										value="${order.totalPrice}" type="currency" /></span>
							</div>
							<div class="w-full border-b border-black"></div>
						</c:forEach>
					</div>
				</div>
			</div>
		</div>
	</main>
	<%@include file="/WEB-INF/views/store/include/store-footer.jsp"%>

	<script src="https://unpkg.com/swiper@7/swiper-bundle.min.js"></script>
	<script
		src="<c:url value='/resources/store/assets/js/swiper-bundle.min.js'/>"></script>
	<script src="<c:url value='/resources/store/assets/js/main.js'/>"></script>
	<script type="text/javascript">
	
	function submitRating(rating, starElement) {
	    const container = starElement.closest('.product-ratings');
	    const productId = container.getAttribute('data-product-id');
	    const orderId = container.getAttribute('data-order-id');
	    const customerId = "${customerEntity.id}"; // Lấy customerId từ session

	    // Cập nhật data-current-rating trong DOM
	    container.setAttribute('data-current-rating', rating);

	    fetch('/LightStudio/store/user-info/order-history/submit-rating', {
	        method: 'POST',
	        headers: {
	            'Content-Type': 'application/x-www-form-urlencoded'
	        },
	        body: new URLSearchParams({
	            'productId': productId,
	            'rating': rating,
	            'orderId': orderId,
	            'customerId': customerId
	        })
	    }).then(response => {
	        if (response.ok) {
	            console.log(`Review submitted successfully for product ${productId}`)
	            updateStars(rating, productId, orderId); // Cập nhật sao sau khi gửi đánh giá
	        } else {
	            console.error('Failed to submit review. Status:', response.status);
	        }
	    }).catch(error => console.error('Error:', error));
	}


	function updateStars(rating, productId, orderId) {
	    console.log('Updating stars for productId:', productId, 'orderId:', orderId, 'rating:', rating);
	    const stars = document.querySelectorAll(`.product-ratings[data-product-id='${productId}'][data-order-id='${orderId}'] .star`);
	    
	    stars.forEach(star => {
	        const starValue = parseInt(star.getAttribute('data-value'));
	        if (starValue <= rating) {
	            star.classList.add('selected');
	        } else {
	            star.classList.remove('selected');
	        }
	    });
	}


window.addEventListener('load', () => {
    document.querySelectorAll('.product-ratings').forEach(container => {
        const currentRating = parseInt(container.getAttribute('data-current-rating')) || 0;
        const stars = container.querySelectorAll('.star');
        
        stars.forEach(star => {
            const starValue = parseInt(star.getAttribute('data-value'));
            if (starValue <= currentRating) {
                star.classList.add('selected');
            } else {
                star.classList.remove('selected');
            }
        });
    });
});


document.addEventListener('DOMContentLoaded', function() {
    // Tìm tất cả các phần tử có lớp "product-ratings"
    document.querySelectorAll('.product-ratings').forEach(function(ratingsContainer) {
        // Lấy giá trị đánh giá hiện tại từ thuộc tính data
        var currentRating = parseInt(ratingsContainer.getAttribute('data-current-rating'));
        // Lấy tất cả các sao trong phần tử đánh giá
        ratingsContainer.querySelectorAll('.star').forEach(function(star) {
            if (parseInt(star.getAttribute('data-value')) <= currentRating) {
                star.classList.add('selected'); // Thêm lớp CSS để làm sao sáng lên
            }
        });
    });
});

	</script>

</body>

</html>
