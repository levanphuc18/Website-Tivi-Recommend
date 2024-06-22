<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Light - Studio</title>
<%@include file="/WEB-INF/views/store/include/store-head.jsp"%>

</head>

<body>
	<%@include file="/WEB-INF/views/store/include/store-header.jsp"%>
	<main class="main">

		<div class="main__slider flex justify-between">
			<div class="swiper swiper-1">
				<!-- Additional required wrapper -->
				<div class="swiper-wrapper">
					<!-- Slides -->
					<div class="swiper-slide">
						<a href="#" class="w-full"><img
							src="<c:url value='/resources/store/assets/img/Slide1.jpg'/>"
							alt="" class="w-full h-full"></a>
					</div>
					<div class="swiper-slide">
						<a href="#" class="w-full"><img
							src="<c:url value='/resources/store/assets/img/Slide2.jpg'/>"
							alt="" class="w-full h-full"></a>
					</div>
					<div class="swiper-slide">
						<a href="#" class="w-full"><img
							src="<c:url value='/resources/store/assets/img/Slide3.jpg'/>"
							alt="" class="w-full h-full"></a>
					</div>
				</div>
				<!-- If we need pagination -->
				<div class="swiper-pagination"></div>

				<!-- If we need navigation buttons -->
				<div class="swiper-button-prev"></div>
				<div class="swiper-button-next"></div>

			</div>
			<div class="slider__right flex flex-col flex-1 ml-2">
				<a href="#" class="w-full mb-2 h-1/2"><img
					src="<c:url value='/resources/store/assets/img/SlideMin1.png'/>"
					alt="" class="w-full h-full"></a> <a href="#"
					class="w-full h-1/2"><img
					src="<c:url value='/resources/store/assets/img/SlideMin2.png'/>"
					alt="" class="w-full h-full"></a>
			</div>
		</div>




		<!-- LƯỢT XEM NHIỀUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUU	 -->


		<div class="main__new-product section px-32 my-16">
			<div class="section__heading mb-8">
				<h3>
					<a href="#"
						class="hover:text-indigo-400 transition-colors text-3xl font-bold">Sản
						phẩm có lượt xem nhiều</a>
				</h3>
			</div>
			<div class="new-product__list flex flex-wrap">
				<c:forEach var="p" items="${listlx}">
					<div class="item w-1/4 px-3 mb-4 flex flex-col justify-between">
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


							<ul class="list-inline product-ratings">
								<li><i class="rating fa fa-star"></i></li>
								<li><i class="rating fa fa-star"></i></li>
								<li><i class="rating fa fa-star"></i></li>
								<li><i class="rating fa fa-star"></i></li>
								<li><i class="rating fa fa-star"></i></li>
							</ul>


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

		<!-- LƯỢT XEM NHIỀUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUU	 -->


		<div class="main__new-product section px-32 my-16">
			<div class="section__heading mb-8">
				<h3>
					<a href="#"
						class="hover:text-indigo-400 transition-colors text-3xl font-bold">Sản
						phẩm mới</a>
				</h3>
			</div>
			<div class="new-product__list flex flex-wrap">
				<c:forEach var="p" items="${list}">
					<div class="item w-1/4 px-3 mb-4 flex flex-col justify-between">
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

							<ul class="list-inline product-ratings">
								<li><i class="rating fa fa-star"></i></li>
								<li><i class="rating fa fa-star"></i></li>
								<li><i class="rating fa fa-star"></i></li>
								<li><i class="rating fa fa-star"></i></li>
								<li><i class="rating fa fa-star"></i></li>
							</ul>

							<c:if test="${p.quantity <= 0}">
								<span class="p-1 ml-1 bg-gray-500 text-white">Hết hàng</span>
							</c:if>
						</div>
						<%-- <span class="item__price font-bold"><fmt:setLocale value="vi_VN" scope="session" />
                              <fmt:formatNumber value="${p.price}" type="currency" /></span> --%>
					</div>

				</c:forEach>
				<div class="list__see-more w-full text-center mt-1">
					<a href="store/all"
						class="px-4 py-2 font-bold bg-indigo-400 hover:bg-indigo-500 transition-colors text-white rounded-md">Xem
						thêm</a>
				</div>
			</div>

		</div>

		<!-- DDDDĐAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA	 -->



	</main>

	
	<%@include file="/WEB-INF/views/store/include/store-footer.jsp"%>
	<script
		src="<c:url value='/resources/store/assets/js/swiper-bundle.min.js'/>"></script>
	<!-- <script src="https://unpkg.com/swiper@7/swiper-bundle.min.js"></script> -->
	<!-- <script src="./assets/js/main.js"></script> -->
	<script src="<c:url value='/resources/store/assets/js/main.js'/>"></script>
	

	
	
</body>

</html>