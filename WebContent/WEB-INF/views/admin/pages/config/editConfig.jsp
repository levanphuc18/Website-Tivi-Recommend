<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- ========== Tag Lib ========= -->
<%@include file="/WEB-INF/views/admin/includes/header/taglib.jsp"%>

<!DOCTYPE html>
<html lang="en">

<head>

<!-- ========== Meta Tags ========= -->
<%@include file="/WEB-INF/views/admin/includes/header/head.jsp"%>

<!-- ========== Favicon linkup ========= -->
<%@include file="/WEB-INF/views/admin/includes/header/favicon.jsp"%>

<!-- ========== All CSS files linkup ========= -->
<%@include file="/WEB-INF/views/admin/includes/header/styles.jsp"%>
</head>

<body>
<%@include file="/WEB-INF/views/admin/includes/nav/sidebar.jsp"%>

        <main class="content">
			<%@include file="/WEB-INF/views/admin/includes/nav/navbar.jsp"%>
           
            
            <div class="row pt-4">
                <div class="col-12 col-xl-8">
                    <div class="card card-body border-0 shadow mb-4">
                        <h2 class="h5 mb-4">Thông tin cấu hình</h2>
                        <form:form action ="${applicationScope.configPage }/edit/${config.id}" method = "post" modelAttribute="config" enctype="multipart/form-data">
                            <div class="row">
                                <div class="col-md-6 mb-3">
                                    <div>
                                        <label for="first_name">#</label>
                                        <form:input class="form-control" path="id" type="text" value = "${category.id }" readonly="true" />
                                    </div>
                                </div>
                                <div class="col-md-6 mb-3">
                                    <div>
                                        <label for="last_name">Tên cấu hình</label>
                                        <form:input class="form-control ${nameValid }" id="name" path="name" type="text" />
                                       	<form:errors path="name" class="invalid-feedback"/>
                                    </div>
                                </div>
                            </div>
                            <div class="row align-items-center">
                                <div class="col-md-12 mb-3">
                                    <label for="birthday">Ngày thêm</label>
                                    <div class="input-group">
                                        <span class="input-group-text">
                                            <svg class="icon icon-xs" fill="none" stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg"><path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M8 7V3m8 4V3m-9 8h10M5 21h14a2 2 0 002-2V7a2 2 0 00-2-2H5a2 2 0 00-2 2v12a2 2 0 002 2z"></path></svg>
                                        </span>
                                        <form:input path="dateAdded" label="<fmt:formatDate value='${config.dateAdded}' pattern='dd/MM/yyyy'/>" class="form-control" id="dateAdded" type="text" readonly="true" />                                               
                                     </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-12 mb-3">
                                    <div>
                                        <label for="last_name">Màu</label>
                                        <form:input class="form-control ${color}" id="color" path="color" type="text" />
                                    </div>
                                    <div>
                                        <label for="last_name">Loại màn hình</label>
                                        <form:input class="form-control ${screentype}" id="screentype" path="screentype" type="text" />               
                                    </div>
                                        <label for="last_name">Kích thước</label>
                                        <form:input class="form-control ${screensize}" id="screensize" path="screensize" type="text" />
                                    
                                    <div>
                                        <label for="last_name">Tần số quét</label>
                                        <form:input class="form-control ${scanningfrequency}" id="scanningfrequency" path="scanningfrequency" type="text" />                                     	
                                    </div>
                                    <div>
                                        <label for="last_name">Độ phân giải</label>
                                        <form:input class="form-control ${resolution}" id="resolution" path="resolution" type="text" />                                       
                                    </div>
                                    <div>
                                        <label for="last_name">Tiện ích</label>
                                        <form:input class="form-control ${utilities}" id="utilities" path="utilities" type="text" />                        
                                    </div>
                                    <div>
                                        <label for="last_name">Hệ điều hành</label>
                                        <form:input class="form-control ${operatingsystem}" id="operatingsystem" path="operatingsystem" type="text" />
                                    </div>
                                </div>
                            </div>
                            <div class="mt-3">
                                <button class="btn btn-gray-800 mt-2 animate-up-2 d-inline-flex align-items-center" type="submit">
                                	<svg class="icon icon-xs me-2" fill="none" stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg"><path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M5 13l4 4L19 7"></path></svg>
                                	Lưu
                                </button>
                            </div>
                        
                    </div>
                </div>
                
                <div class="col-12 col-xl-4">
                    <div class="row">
                        <div class="col-12 mb-4">
                            <div class="card shadow border-0 text-center p-0">
                                <div class="rounded-top">
                                	<img src="${category.image}" class="img-fluid" alt="${category.name}" id="preview-image">
                                </div>
                                <div class="card-body pb-5">
                                    <!-- <img src="../assets/img/team/profile-picture-1.jpg" class="avatar-xl rounded-circle mx-auto mt-n7 mb-4" alt="Neil Portrait"> -->
                                    <h4 class="h3">${category.name }</h4>
                                    <h6 class="fw-normal">Sản phẩm: ${category.products.size()}</h6>
                                    <p class="text-gray mb-4 d-flex align-items-center justify-content-center">
                                    <svg class="icon icon-xs me-2" fill="none" stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg"><path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M7 20l4-16m2 16l4-16M6 9h14M4 15h14"></path></svg>
                                    	${category.id }
                                    </p>
                                    
                                    <a class="btn btn-sm btn-secondary d-inline-flex align-items-center" href="${applicationScope.categoryPage }/${category.id }">
                                        <svg class="icon icon-xs me-2" fill="none" stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg"><path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M15 12a3 3 0 11-6 0 3 3 0 016 0z"></path><path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M2.458 12C3.732 7.943 7.523 5 12 5c4.478 0 8.268 2.943 9.542 7-1.274 4.057-5.064 7-9.542 7-4.477 0-8.268-2.943-9.542-7z"></path></svg>
                                        Xem sản phẩm
                                    </a>
                                </div>
                             </div>
                        </div>
                        
                        <div class="col-12">
                            <div class="card card-body border-0 shadow">
                                <h2 class="h5 mb-4">Đổi ảnh danh mục</h2>
                                <div class="d-flex align-items-center">
                                    <!-- <div class="me-3">
                                        Avatar
                                        <img class="rounded avatar-xl" src="../assets/img/profile-cover.jpg" alt="change cover">
                                    </div> -->
                                    <div class="file-field">
                                        <div class="d-flex justify-content-xl-center ms-xl-3">
                                            <div class="d-flex">
                                                <svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" fill="currentColor" class="bi bi-paperclip icon text-gray-500 me-2" viewBox="0 0 16 16">
												  <path d="M4.5 3a2.5 2.5 0 0 1 5 0v9a1.5 1.5 0 0 1-3 0V5a.5.5 0 0 1 1 0v7a.5.5 0 0 0 1 0V3a1.5 1.5 0 1 0-3 0v9a2.5 2.5 0 0 0 5 0V5a.5.5 0 0 1 1 0v7a3.5 3.5 0 1 1-7 0V3z"/>
												</svg>		
												
                                                <input type="file" name = "image" id="image-upload" accept="image/*">
                                                <div class="d-md-block text-left">
                                                    <div class="fw-normal text-dark mb-1">Chọn hình ảnh</div>
                                                    <div class="text-gray small">JPG, GIF hoặc PNG. Kích thước tối đa 800K</div>
                                                </div>
                                                <script>
                                                	var previewImage = document.getElementById("preview-image");
                                                	var imageUpload = document.getElementById("image-upload");
                                                	imageUpload.onchange = evt => {
                                                		  const [file] = imageUpload.files
                                                		  if (file) {
                                                			  previewImage.src = URL.createObjectURL(file)
                                                		  }
                                                		}
                                                </script>
                                            </div>
                                        </div>
                                     </div>                                        
                                </div>
                            </div>
                        </div>
                        </form:form>
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
