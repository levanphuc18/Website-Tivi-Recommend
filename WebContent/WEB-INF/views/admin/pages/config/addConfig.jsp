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
						href="${applicationScope.addConfigPage }">Cấu hình</a></li>
					<li class="breadcrumb-item active" aria-current="page">Thêm
						cấu hình</li>
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

								<form:form action="${applicationScope.configPage }/add"
									method="POST" enctype="multipart/form-data"
									modelAttribute="config">
									<div class="mb-4">
										<label for="configName">Tên cấu hình</label>
										<form:input type="configName"
											class="form-control ${nameValid}" path="name" id="configName"
											aria-describedby="configNameHelp"
											placeholder="Nhập tên cấu hình" />
										<form:errors path="name" class="invalid-feedback" />
									</div>

									<div>
										<label for="last_name">Màu</label>
										<form:input class="form-control ${color}" id="color"
											path="color" type="text" placeholder="Nhập màu" />
									</div>
									<div>
										<label for="last_name">Loại màn hình</label>
										<form:input class="form-control ${screentype}" id="screentype"
											path="screentype" type="text"
											placeholder="Nhập loại màn hình" />
									</div>
									<label for="last_name">Kích thước</label>
									<form:input class="form-control ${screensize}" id="screensize"
										path="screensize" type="text"
										placeholder="Nhập kích thước màn hình" />

									<div>
										<label for="last_name">Tần số quét</label>
										<form:input class="form-control ${scanningfrequency}"
											id="scanningfrequency" path="scanningfrequency" type="text"
											placeholder="Nhập tần số quét (VD: 60HZ)" />
									</div>
									<div>
										<label for="last_name">Độ phân giải</label>
										<form:input class="form-control ${resolution}" id="resolution"
											path="resolution" type="text"
											placeholder="Nhập độ phân giải (VD: 4K)" />
									</div>
									<div>
										<label for="last_name">Tiện ích</label>
										<form:input class="form-control ${utilities}" id="utilities"
											path="utilities" type="text" placeholder="Nhập tiện ích" />
									</div>
									<div>
										<label for="last_name">Hệ điều hành</label>
										<form:input class="form-control ${operatingsystem}"
											id="operatingsystem" path="operatingsystem" type="text"
											placeholder="Nhập hệ điều hành" />
									</div>

									<div class="mb-3">
										<img alt="Hình ảnh" src="" id="preview-image"
											class="img-thumbnail" width="200" height="200">
									</div>

									<div class="mb-3">
										<label for="formFile" class="form-label">Chọn file
											hình ảnh</label>
										<form:input class="form-control" type="file" path="image"
											id="image" />
									</div>
									<div class="mb-3">
										<button class="btn btn-primary" type="submit">Thêm
											cấu hình</button>
									</div>

									<script>
                 	var previewImage = document.getElementById("preview-image");
                 	var imageUpload = document.getElementById("image");
                 	imageUpload.onchange = evt => {
                 		  const [file] = imageUpload.files
                 		  if (file) {
                 			  previewImage.src = URL.createObjectURL(file)
                 		  }
                 		}
                 </script>

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
