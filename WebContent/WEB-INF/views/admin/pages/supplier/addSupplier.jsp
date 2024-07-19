<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- ========== Tag Lib ========= -->
<%@include file="/WEB-INF/views/admin/includes/header/taglib.jsp"%>

<!DOCTYPE html>
<html lang="en">

<head>

<!-- ========== Meta Tags ========= -->
<%@include file="/WEB-INF/views/admin/includes/header/head.jsp"%>
<title>${title }</title>

<!-- ========== Favicon linkup ========= -->
<%@include file="/WEB-INF/views/admin/includes/header/favicon.jsp"%>

<!-- ========== All CSS files linkup ========= -->
<%@include file="/WEB-INF/views/admin/includes/header/styles.jsp"%>

</head>

<body>
	<%@include file="/WEB-INF/views/admin/includes/nav/sidebar.jsp"%>
	<main class="content">
		<%@include file="/WEB-INF/views/admin/includes/nav/navbar.jsp"%>

		<div class="py-4">
			<nav aria-label="breadcrumb" class="d-none d-md-inline-block">
				<ol class="breadcrumb breadcrumb-dark breadcrumb-transparent">
					<li class="breadcrumb-item"><a
						href="${applicationScope.dashboardPage }"> <svg
								class="icon icon-xxs" fill="none" stroke="currentColor"
								viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
								<path stroke-linecap="round" stroke-linejoin="round"
									stroke-width="2"
									d="M3 12l2-2m0 0l7-7 7 7M5 10v10a1 1 0 001 1h3m10-11l2 2m-2-2v10a1 1 0 01-1 1h-3m-6 0a1 1 0 001-1v-4a1 1 0 011-1h2a1 1 0 011 1v4a1 1 0 001 1m-6 0h6"></path></svg>
					</a></li>
					<li class="breadcrumb-item"><a
						href="${applicationScope.addSupplierPage }">Nhà cung cấp</a></li>
					<li class="breadcrumb-item active" aria-current="page">Thêm
						nhà cung cấp</li>
				</ol>
			</nav>
			<div class="d-flex justify-content-between w-100 flex-wrap">
				<div class="mb-3 mb-lg-0">
					<h1 class="h4">${title }</h1>
					<p class="mb-0 text-gray-400"></p>
				</div>
			</div>
		</div>

		<div class="row">
			<div class="col-12">
				<div class="card border-0 shadow components-section">
					<div class="card-body">
						<div class="row mb-4">
							<div class="col-lg-12 col-sm-12">
								<form:form action="${applicationScope.supplierPage}/add"
									method="POST" enctype="multipart/form-data"
									modelAttribute="supplier">
									<div class="mb-4">
										<label for="supplierName">Tên nhà cung cấp</label>
										<form:input type="supplierName"
											class="form-control ${nameValid}" path="name"
											id="supplierName" aria-describedby="supplierNameHelp"
											placeholder="Nhập tên nhà cung cấp" />
										<form:errors path="name" class="invalid-feedback" />
									</div>
									<div>
										<label for="last_name">Số điện thoại</label>
										<form:input class="form-control ${phone}" id="phone"
											path="phone" type="text" placeholder="Nhập số điện thoại" />
									</div>
									<div>
										<label for="last_name">Email</label>
										<form:input class="form-control ${email}" id="email"
											path="email" type="text" placeholder="Nhập email" />
									</div>
									<label for="last_name">Địa chỉ</label>
									<form:input class="form-control ${address}" id="address"
										path="address" type="text" placeholder="Nhập địa chỉ" />

									<div>
										<label for="last_name">Mã số thuế</label>
										<form:input class="form-control ${tax}" id="tax" path="tax"
											type="text" placeholder="Nhập mã số thuế" />
									</div>

									<div class="mb-3">
										<button class="btn btn-primary" type="submit">Thêm
											nhà cung cấp</button>
									</div>

								</form:form>
							</div>


						</div>
					</div>
				</div>
			</div>
		</div>

		<%@include file="/WEB-INF/views/admin/includes/footer/footer.jsp"%>
	</main>
	<!-- ========== All JS files linkup ========= -->
	<%@include file="/WEB-INF/views/admin/includes/footer/script.jsp"%>

	<!-- ========== Notyf JS linkup ========= -->
	<%@include file="/WEB-INF/views/admin/includes/footer/notyf.jsp"%>

</body>

</html>
