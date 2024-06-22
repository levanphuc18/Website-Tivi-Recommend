<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
<title>Đăng ký</title>
<%@include file="/WEB-INF/views/store/include/store-head.jsp"%>
<style type="text/css">
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

.select-item {
	width: 100%;
	position: relative;
}

.select-item>span {
	position: absolute;
	width: 22px;
	height: 22px;
	right: 22px;
	top: 12px;
	display: none;
	z-index: 5;
}

.select-item>span>img {
	width: 100%;
	height: 100%;
}
</style>
</head>

<body>
	<%@include file="/WEB-INF/views/store/include/store-header.jsp"%>
	<main class="main">
		<div
			class="sign-up-form-container mx-auto my-10 border-2 border-gray-200 rounded-md">
			<div class="flex">
				<a href="${applicationScope.signInPage}"
					class="switch-to-sign-in w-full py-2 relative text-xl text-indigo-400 text-center">ĐĂNG
					NHẬP</a><a href="${applicationScope.signUpPage}"
					class="switch-to-sign-up w-full py-2 relative text-xl text-indigo-400 text-center active-show-after">ĐĂNG
					KÝ</a>
			</div>


			<f:form action="store/sign-up" class="w-full p-4" method="post"
				modelAttribute="customer">
				<f:input type="text" path="username" id=""
					class="w-full p-2 border-2 border-gray-300 rounded-md mb-3"
					placeholder="Tên đăng nhập (*)" />
				<f:errors path="username" cssClass="error" element="div" />
				<f:input type="text" path="firstName" id=""
					class="w-full p-2 border-2 border-gray-300 rounded-md mb-3"
					placeholder="Họ (*)" />
				<f:errors path="firstName" cssClass="error" element="div" />
				<f:input type="text" path="lastName" id=""
					class="w-full p-2 border-2 border-gray-300 rounded-md mb-3"
					placeholder="Tên (*)" />
				<f:errors path="lastName" cssClass="error" element="div" />

				<f:input type="text" path="telephone" id=""
					class="w-full p-2 border-2 border-gray-300 rounded-md mb-3"
					placeholder="Điện thoại (*)" />
				<f:errors path="telephone" cssClass="error" element="div" />
				<f:input type="text" path="email" id=""
					class="w-full p-2 border-2 border-gray-300 rounded-md mb-3"
					placeholder="Email (*)" />
				<f:errors path="email" cssClass="error" element="div" />

				<f:input type="text" path="fullAddress" id="full-address"
					class="w-full p-2 border-2 border-gray-300 rounded-md mb-3"
					placeholder="Địa chỉ (*)" />
				<f:errors path="fullAddress" cssClass="error" element="div" />


				<f:input type="password" path="password" id=""
					class="w-full p-2 border-2 border-gray-300 rounded-md mb-3"
					placeholder="Mật khẩu của bạn (*)" />
				<f:errors path="password" cssClass="error" element="div" />
				<f:input type="password" path="confirmPassword" id=""
					class="w-full p-2 border-2 border-gray-300 rounded-md mb-3"
					placeholder="Nhập lại mật khẩu (*)" />
				<f:errors path="confirmPassword" cssClass="error" element="div" />
				<div class="mb-2 flex justify-end">
					<label for="showPassword" class=""><input type="checkbox"
						class="mr-1" id="showPassword">Hiện mật khẩu</label>
				</div>
				<button
					class="w-full px-4 py-2 font-bold bg-indigo-400 hover:bg-indigo-500 transition-colors text-white rounded-md">ĐĂNG
					KÝ</button>
			</f:form>
		</div>

	</main>
	<%@include file="/WEB-INF/views/store/include/store-footer.jsp"%>
	<script src="<c:url value='/resources/store/assets/js/swiper-bundle.min.js'/>"></script>

	<script src="<c:url value='/resources/store/assets/js/main.js'/>"></script>

	<script src="<c:url value='/resources/store/assets/js/sign-up.js'/>"></script>
	<script type="text/javascript">
		var a = document.getElementsByClassName("error");
		for (var i = 0; i < a.length; i++) {
			a[i].addEventListener("click", function(e) {
				e.target.style.display = "none";
			})
		}
		const show = document.getElementById("showPassword");
		const inputPasswordField = document
				.querySelectorAll('input[type="password"]');
		show.addEventListener("click", function(e) {
			if (this.checked) {
				for (var i = 0; i < inputPasswordField.length; i++) {
					inputPasswordField[i].type = "text";
				}
			} else {
				for (var i = 0; i < inputPasswordField.length; i++) {
					inputPasswordField[i].type = "password";
				}
			}
		});
	</script>
</body>

</html>