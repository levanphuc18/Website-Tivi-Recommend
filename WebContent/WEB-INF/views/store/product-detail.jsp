<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">

<head>
<title>${product.name}</title>
<%@include file="/WEB-INF/views/store/include/store-head.jsp"%>
</head>
<style>
.star {
        font-size: 0.8em;
        color: lightgray;
        cursor: pointer;
        display: inline-block;
    }
    
    .star.filled {
        color: gold;
    }
    
    .star.half-filled::before {
        content: '★';
        color: gold;
        position: absolute;
        overflow: hidden;
        width: 50%;
    }

.form-container {
	display: flex;
	flex-direction: column;
	gap: 10px;
}

.form-item {
	display: flex;
	justify-content: space-between;
	align-items: center;
}

.form-item.header {
	background-color: #f0f0f0;
	padding: 5px;
}

.label {
	font-weight: bold;
	width: 250px; /* Adjust width as needed */
}

.value {
	flex: 1;
	padding-left: 10px;
}

.pre-wrap {
	white-space: pre-wrap;
}

.scroll-frame {
	max-height: 300px; /* Chiều cao tối đa của khung cuộn */
	overflow-y: auto; /* Thêm thanh cuộn dọc */
	padding: 10px;
	border: 1px solid #ccc; /* Đường viền của khung cuộn */
	margin-top: 20px; /* Khoảng cách phía trên của khung cuộn */
}

.description-box {
	border: 1px solid #ccc; /* Đường viền khung */
	border-radius: 8px; /* Bo tròn góc */
	padding: 12px; /* Khoảng cách lề bên trong */ body { font-family :
	Arial, sans-serif;
	margin: 0;
	padding: 0;
	background-color: #f4f4f4;
}

.container {
	width: 80%;
	margin: 0 auto;
	background-color: #fff;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

.header, .footer {
	background-color: #333;
	color: #fff;
	text-align: center;
	padding: 10px 0;
}

.content {
	padding: 20px;
	text-align: left;
}
</style>

<body>
	<%@include file="/WEB-INF/views/store/include/store-header.jsp"%>
	<main class="main">
		<div class="main__new-product section px-32 my-5">
			<div class="product__detail">
				<div class="product-navigation font-bold mb-5">
					<a href="store/all"
						class="inline-block font-bold text-xl hover:text-indigo-500 transition-colors">Tất
						cả</a> > <a href="store/category/${product.category.id}"
						class="inline-block font-bold text-xl text-indigo-600 hover:text-indigo-500 transition-colors">${product.category.name}</a>
				</div>
				<div class="flex detail__wrapper">
					<a href="${applicationScope.productDetailPage}/${product.id}"
						class="product-detail__img-container"><img
						src="${product.images[0].image}" alt="${product.id}"></a>
					<div class="ml-5 flex-1 detail__desc">
						<div class="flex justify-between">
							<h3 class="text-3xl font-bold mb-2">${product.name}
								<div class="product-ratings"
									data-current-rating="${reviewInfo.averageRating}">
									<span class="star" data-value="1">★</span> 
									<span class="star" data-value="2">★</span> 
									<span class="star" data-value="3">★</span>
									<span class="star" data-value="4">★</span> 
									<span class="star" data-value="5">★</span>
								</div>
							</h3>
							<a
								href="store/insert-to-favlist/${product.id}/product-detailseperator${product.id}">
								<c:set var="contains" value="false" /> <c:forEach var="fav"
									items="${listFavorite}">
									<c:if test="${fav.product.id eq product.id}">
										<c:set var="contains" value="true" />
									</c:if>
								</c:forEach> <c:if test="${contains==true}">
									<i
										class="fa text-2xl fa-heart text-white text-indigo-500 text-2xl transition-colors"></i>
								</c:if> <c:if test="${contains==false}">
									<i
										class="fa text-2xl fa-heart text-black  text-2xl transition-colors"></i>
								</c:if> <!-- <i
										class="fa text-2xl fa-heart text-white hover:text-indigo-500 transition-colors"></i> -->
							</a>
						</div>

						<div class="flex justify-between">
							<%-- <h6 class="mb-2 text-gray-500">Mã SP: ${product.id}</h6> --%>
							<h6 class="mb-2 text-gray-500">Lượt đánh giá: ${reviewsCount}</h6>

							<div class="flex">
								<h6 class="mb-2 text-gray-500 mr-4">Tồn kho:
									${product.quantity}</h6>
								<h6 class="mb-2 text-gray-500">Lượt xem: ${product.views}</h6>


								<!-- <div class="border-l-2 border-white h-7 mx-2"></div> -->
								<!-- <i
								class="fa fa-share-alt text-2xl hover:text-indigo-500 text-2xl mr-2 cursor-pointer"></i>
							<i
								class="fa fa-heart hover:text-indigo-500 text-2xl cursor-pointer"></i> -->
							</div>
						</div>
						<!-- chi tiet cau hinh -->
						<div class="form-container">
							<div class="form-item header">
								<span class="label">THÔNG SỐ SẢN PHẨM:</span>
							</div>
							<div class="form-item">
								<span class="label"> Loại Tivi:</span> <span class="value">${product.category.name}</span>
							</div>
							<div class="form-item">
								<span class="label"> Kích thước màn hình:</span> <span
									class="value">${product.config.screensize}</span>
							</div>
							<div class="form-item">
								<span class="label"> Loại màn hình:</span> <span class="value">${product.config.screentype}</span>
							</div>
							<div class="form-item">
								<span class="label"> Hệ điều hành:</span> <span class="value">${product.config.operatingsystem}</span>
							</div>

							<div class="form-item header">
								<span class="label">CÔNG NGHỆ HÌNH ẢNH:</span>
							</div>
							<div class="form-item">
								<span class="label"> Tần số quét:</span> <span class="value">${product.config.scanningfrequency}</span>
							</div>
							<div class="form-item">
								<span class="label"> Độ phân giải:</span> <span class="value">${product.config.resolution}</span>
							</div>

							<div class="form-item header">
								<span class="label">TIỆN ÍCH:</span>
							</div>
							<div class="form-item">
								<span class="label"> Tiện ích:</span> <span class="value">${product.config.utilities}</span>
							</div>
						</div>
						<!-- chi tiet cau hinh -->



						<div>
							<%-- <span class="item__price font-bold"><fmt:setLocale value="vi_VN" scope="session" />
                              <fmt:formatNumber value="${pd.price}" type="currency" /></span> --%>
							<h2 class="price my-4 text-3xl font-bold">
								<fmt:setLocale value="vi_VN" scope="session" />
								<fmt:formatNumber value="${product.price}" type="currency" />
							</h2>
							<c:if test="${product.quantity <= 0}">
								<span class="px-1 ml-1 bg-gray-500 text-white text-2xl">Hết
									hàng</span>
							</c:if>
						</div>
						<%-- <h2 class="price my-4 text-3xl font-bold"><fmt:setLocale value="vi_VN" scope="session" />
                              <fmt:formatNumber value="${product.price}" type="currency" /></h2> --%>

						<!-- <div class="flex my-4 items-center">
						<h6 class="color mr-4">Màu sắc</h6>
						<div
							class="color-visual border-2 border-blue-400 rounded-full w-9 h-9 bg-yellow-300 cursor-pointer">
						</div>
					</div> -->
						<form>
							<!--  chưa cập nhật thêm vào giỏ -->

							<div class="flex my-4 border-gray-300 border-b-2 pb-5">
								<a
									href="store/shopping-cart/insert-into-cart/${product.id}/product-detailseperator${product.id}"
									class="w-full text-center py-2 font-bold bg-red-900 hover:bg-red-500 transition-colors text-white rounded-md mr-4 add-to-cart">THÊM
									VÀO GIỎ HÀNG</a>
								<!-- <button
								class="w-full text-center py-2 font-bold bg-indigo-400 hover:bg-indigo-500 transition-colors text-white rounded-md buy-now">MUA
								NGAY</button> -->
							</div>
						</form>

					</div>
				</div>

				<div class="description-box border rounded-lg p-4">
					<h6 class="mb-2 text-black">
						<strong><span
							style="background-color: #e0e0e0; padding: 2px 4px; border-radius: 4px;">Mô
								tả:</span></strong>
					</h6>
					<div class="scroll-frame">
						<pre class="pre-wrap"
							style="font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; font-size: 14px; color: #333; line-height: 1.6; background-color: #f8f9fa; border: 1px solid #ddd; padding: 15px; border-radius: 8px; box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);">
            ${product.description}
        </pre>
					</div>
				</div>

				<div class="section__heading mb-8  mt-16">
					<h3>
						<span href="#"
							class="<!-- hover:text-indigo-400 --> transition-colors text-3xl font-bold">Các
							sản phẩm đã xem</span>
					</h3>
				</div>
				<div class="visited-product flex flex-wrap">
					<c:if test="${sessionScope.listRecentViewProducts != null}">
						<c:forEach var="p" items="${sessionScope.listRecentViewProducts}">
							<div class="item w-1/5 px-3 mb-4 flex flex-col justify-between">
								<div>
									<div class="relative item-menu-container overflow-hidden">
										<a href="${applicationScope.productDetailPage}/${p.id}"
											class="block overflow-hidden"> <img
											src="${p.images[0].image}" alt="${p.id}"
											class="transform hover:scale-110 transition-all " /></a>

										<div
											class="flex justify-center bg-indigo-300 items-center py-1 absolute item__menu w-full">
											<a
												href="store/insert-to-favlist/${p.id}/product-detailseperator${p.id}">
												<c:set var="contains" value="false" /> <c:forEach var="fav"
													items="${listFavorite}">
													<c:if test="${fav.product.id eq p.id}">
														<c:set var="contains" value="true" />
													</c:if>
												</c:forEach> <c:if test="${contains==true}">
													<i
														class="fa text-2xl fa-heart text-white text-indigo-500 transition-colors"></i>
												</c:if> <c:if test="${contains==false}">
													<i
														class="fa text-2xl fa-heart text-white hover:text-indigo-500 transition-colors"></i>
												</c:if> <!-- <i
										class="fa text-2xl fa-heart text-white hover:text-indigo-500 transition-colors"></i> -->
											</a>
											<div class="border-l-2 border-white h-7 mx-2"></div>
											<a
												href="store/shopping-cart/insert-into-cart/${p.id}/product-detailseperator${p.id}"><i
												class="fa text-2xl fa-cart-plus text-white hover:text-indigo-200 transition-colors"></i></a>
										</div>
									</div>
									<a
										href="${applicationScope.productDetailPage}/${p.id}/product-detailseperator${p.id}"
										class="item__name block hover:text-indigo-400 transition-colors font-bold my-2">${p.name}</a>
								</div>
								<div>
									<span class="item__price font-bold"><fmt:setLocale
											value="vi_VN" scope="session" /> <fmt:formatNumber
											value="${p.price}" type="currency" /></span>
									<c:if test="${p.quantity <= 0}">
										<span class="p-1 ml-1 bg-gray-500 text-white">Hết
											hàng</span>
									</c:if>
								</div>
								<%-- <span class="item__price font-bold"><fmt:setLocale value="vi_VN" scope="session" />
                              <fmt:formatNumber value="${p.price}" type="currency" /></span> --%>
							</div>
						</c:forEach>
					</c:if>


				</div>

				<!-- ĐỀ XUẤT SẢN PHẨMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM	 -->


				<div class="section__heading mb-8  mt-16">
					<h3>
						<span href="#"
							class="<!-- hover:text-indigo-400 --> transition-colors text-3xl font-bold">Các
							sản phẩm đề xuất</span>
					</h3>
				</div>

				<div class="visited-product flex flex-wrap"">
					<c:forEach var="p" items="${listDXSP}">
						<div class="item w-1/5 px-3 mb-4 flex flex-col justify-between">
							<div>
								<div class="relative item-menu-container overflow-hidden">
									<a href="${applicationScope.productDetailPage}/${p.id}"
										class="block overflow-hidden"> <img
										src="${p.images[0].image}" alt="${p.id}"
										class="transform hover:scale-110 transition-all " /></a>

									<div
										class="flex justify-center bg-indigo-300 items-center py-1 absolute item__menu w-full">
										<%-- <a href="store/insert-to-favlist/${p.id}"> --%>
										<a href="store/insert-to-favlist/${p.id}/index"> <c:set
												var="contains" value="false" /> <c:forEach var="fav"
												items="${listFavorite}">
												<c:if test="${fav.product.id eq p.id}">
													<c:set var="contains" value="true" />
												</c:if>
											</c:forEach> <c:if test="${contains==true}">
												<i
													class="fa text-2xl fa-heart text-white text-indigo-500 transition-colors"></i>
											</c:if> <c:if test="${contains==false}">
												<i
													class="fa text-2xl fa-heart text-white hover:text-indigo-500 transition-colors"></i>
											</c:if> <!-- <i
										class="fa text-2xl fa-heart text-white hover:text-indigo-500 transition-colors"></i> --></a>
										<div class="border-l-2 border-white h-7 mx-2"></div>
										<a href="store/shopping-cart/insert-into-cart/${p.id}/index"><i
											class="fa text-2xl fa-cart-plus text-white hover:text-indigo-200 transition-colors"></i></a>
									</div>
								</div>
								<a href="${applicationScope.productDetailPage}/${p.id}"
									class="item__name block hover:text-indigo-400 transition-colors font-bold my-2">${p.name}</a>
							</div>
							<div>
								<span class="item__price font-bold"><fmt:setLocale
										value="vi_VN" scope="session" /> <fmt:formatNumber
										value="${p.price}" type="currency" /></span>
								<c:if test="${p.quantity <= 0}">
									<span class="p-1 ml-1 bg-gray-500 text-white">Hết hàng</span>
								</c:if>
							</div>
							<%-- <span class="item__price font-bold"><fmt:setLocale value="vi_VN" scope="session" />
                              <fmt:formatNumber value="${p.price}" type="currency" /></span> --%>
						</div>

					</c:forEach>


					<!--
				<div class="list__see-more w-full text-center mt-1">
					<a href="store/all"
						class="px-4 py-2 font-bold bg-indigo-400 hover:bg-indigo-500 transition-colors text-white rounded-md">Xem
						thêm</a>
				</div>
				
				-->

				</div>
			</div>

			<!-- ĐỀ XUẤT SẢN PHẨMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM	 -->

		</div>
		</div>
	</main>
	<%@include file="/WEB-INF/views/store/include/store-footer.jsp"%>

	<script src="https://unpkg.com/swiper@7/swiper-bundle.min.js"></script>
	<!-- <script src="./assets/js/main.js"></script> -->
	<script src="<c:url value='/resources/store/assets/js/main.js'/>"></script>
	<!-- <script src="./assets/js/product-detail.js"></script> -->
	<script
		src="<c:url value='/resources/store/assets/js/product-detail.js'/>"></script>
		
		<script>
    document.addEventListener('DOMContentLoaded', function() {
        const ratingElements = document.querySelectorAll('.product-ratings');
        
        ratingElements.forEach(function(ratingElement) {
            const currentRating = parseFloat(ratingElement.getAttribute('data-current-rating'));
            const stars = ratingElement.querySelectorAll('.star');
            
            stars.forEach(function(star) {
                const starValue = parseInt(star.getAttribute('data-value'));
                if (currentRating >= starValue) {
                    star.classList.add('filled');
                } else if (currentRating >= starValue - 0.5) {
                    star.classList.add('half-filled');
                }
            });
        });
    });
</script>
		
</body>

</html>