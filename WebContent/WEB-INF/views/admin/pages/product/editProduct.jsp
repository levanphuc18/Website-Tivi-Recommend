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
           
           <!--  <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center py-4">
                <div>
                    <div class="dropdown">
                        <button class="btn btn-secondary d-inline-flex align-items-center me-2 dropdown-toggle" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <svg class="icon icon-xs me-2" fill="none" stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg"><path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 6v6m0 0v6m0-6h6m-6 0H6"></path></svg>
                            New
                        </button>
                        <div class="dropdown-menu dashboard-dropdown dropdown-menu-start mt-2 py-1">
                            <a class="dropdown-item d-flex align-items-center" href="#">
                                <svg class="dropdown-icon text-gray-400 me-2" fill="currentColor" viewBox="0 0 20 20" xmlns="http://www.w3.org/2000/svg"><path fill-rule="evenodd" d="M4 4a2 2 0 012-2h4.586A2 2 0 0112 2.586L15.414 6A2 2 0 0116 7.414V16a2 2 0 01-2 2H6a2 2 0 01-2-2V4zm2 6a1 1 0 011-1h6a1 1 0 110 2H7a1 1 0 01-1-1zm1 3a1 1 0 100 2h6a1 1 0 100-2H7z" clip-rule="evenodd"></path></svg>
                                Document
                            </a>
                            <a class="dropdown-item d-flex align-items-center" href="#">
                                <svg class="dropdown-icon text-gray-400 me-2" fill="currentColor" viewBox="0 0 20 20" xmlns="http://www.w3.org/2000/svg"><path fill-rule="evenodd" d="M18 10c0 3.866-3.582 7-8 7a8.841 8.841 0 01-4.083-.98L2 17l1.338-3.123C2.493 12.767 2 11.434 2 10c0-3.866 3.582-7 8-7s8 3.134 8 7zM7 9H5v2h2V9zm8 0h-2v2h2V9zM9 9h2v2H9V9z" clip-rule="evenodd"></path></svg>
                                Message
                            </a>
                            <a class="dropdown-item d-flex align-items-center" href="#">
                                <svg class="dropdown-icon text-gray-400 me-2" fill="currentColor" viewBox="0 0 20 20" xmlns="http://www.w3.org/2000/svg"><path d="M5.5 13a3.5 3.5 0 01-.369-6.98 4 4 0 117.753-1.977A4.5 4.5 0 1113.5 13H11V9.413l1.293 1.293a1 1 0 001.414-1.414l-3-3a1 1 0 00-1.414 0l-3 3a1 1 0 001.414 1.414L9 9.414V13H5.5z"></path><path d="M9 13h2v5a1 1 0 11-2 0v-5z"></path></svg>                            
                                Product
                            </a>
                            <div role="separator" class="dropdown-divider my-1"></div>
                            <a class="dropdown-item d-flex align-items-center" href="#">
                                <svg class="dropdown-icon text-danger me-2" fill="currentColor" viewBox="0 0 20 20" xmlns="http://www.w3.org/2000/svg"><path fill-rule="evenodd" d="M12.395 2.553a1 1 0 00-1.45-.385c-.345.23-.614.558-.822.88-.214.33-.403.713-.57 1.116-.334.804-.614 1.768-.84 2.734a31.365 31.365 0 00-.613 3.58 2.64 2.64 0 01-.945-1.067c-.328-.68-.398-1.534-.398-2.654A1 1 0 005.05 6.05 6.981 6.981 0 003 11a7 7 0 1011.95-4.95c-.592-.591-.98-.985-1.348-1.467-.363-.476-.724-1.063-1.207-2.03zM12.12 15.12A3 3 0 017 13s.879.5 2.5.5c0-1 .5-4 1.25-4.5.5 1 .786 1.293 1.371 1.879A2.99 2.99 0 0113 13a2.99 2.99 0 01-.879 2.121z" clip-rule="evenodd"></path></svg>
                                My Plan
                            </a>
                        </div>
                    </div>
                </div>
                <div>
                    <button type="button" class="btn btn-gray-800 d-inline-flex align-items-center me-2">
                        <svg class="icon icon-xs" fill="currentColor" viewBox="0 0 20 20" xmlns="http://www.w3.org/2000/svg"><path fill-rule="evenodd" d="M6 2a1 1 0 00-1 1v1H4a2 2 0 00-2 2v10a2 2 0 002 2h12a2 2 0 002-2V6a2 2 0 00-2-2h-1V3a1 1 0 10-2 0v1H7V3a1 1 0 00-1-1zm0 5a1 1 0 000 2h8a1 1 0 100-2H6z" clip-rule="evenodd"></path></svg>
                    </button>
                    <button class="btn btn-gray-800 d-inline-flex align-items-center dropdown-toggle" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        <svg class="icon icon-xs me-2" fill="currentColor" viewBox="0 0 20 20" xmlns="http://www.w3.org/2000/svg"><path d="M9 2a1 1 0 000 2h2a1 1 0 100-2H9z"></path><path fill-rule="evenodd" d="M4 5a2 2 0 012-2 3 3 0 003 3h2a3 3 0 003-3 2 2 0 012 2v11a2 2 0 01-2 2H6a2 2 0 01-2-2V5zm3 4a1 1 0 000 2h.01a1 1 0 100-2H7zm3 0a1 1 0 000 2h3a1 1 0 100-2h-3zm-3 4a1 1 0 100 2h.01a1 1 0 100-2H7zm3 0a1 1 0 100 2h3a1 1 0 100-2h-3z" clip-rule="evenodd"></path></svg>
                        Reports
                        <svg class="icon icon-xs ms-1" fill="currentColor" viewBox="0 0 20 20" xmlns="http://www.w3.org/2000/svg"><path fill-rule="evenodd" d="M5.293 7.293a1 1 0 011.414 0L10 10.586l3.293-3.293a1 1 0 111.414 1.414l-4 4a1 1 0 01-1.414 0l-4-4a1 1 0 010-1.414z" clip-rule="evenodd"></path></svg>
                    </button>
                    <div class="dropdown-menu dashboard-dropdown dropdown-menu-start mt-2 py-1">
                        <a class="dropdown-item d-flex align-items-center" href="#">
                            <svg class="dropdown-icon text-gray-400 me-2" fill="currentColor" viewBox="0 0 20 20" xmlns="http://www.w3.org/2000/svg"><path d="M4 3a2 2 0 100 4h12a2 2 0 100-4H4z"></path><path fill-rule="evenodd" d="M3 8h14v7a2 2 0 01-2 2H5a2 2 0 01-2-2V8zm5 3a1 1 0 011-1h2a1 1 0 110 2H9a1 1 0 01-1-1z" clip-rule="evenodd"></path></svg>
                            Products
                        </a>
                        <a class="dropdown-item d-flex align-items-center" href="#">
                            <svg class="dropdown-icon text-gray-400 me-2" fill="currentColor" viewBox="0 0 20 20" xmlns="http://www.w3.org/2000/svg"><path d="M9 6a3 3 0 11-6 0 3 3 0 016 0zM17 6a3 3 0 11-6 0 3 3 0 016 0zM12.93 17c.046-.327.07-.66.07-1a6.97 6.97 0 00-1.5-4.33A5 5 0 0119 16v1h-6.07zM6 11a5 5 0 015 5v1H1v-1a5 5 0 015-5z"></path></svg>                            
                            Customers
                        </a>
                        <a class="dropdown-item d-flex align-items-center" href="#">
                            <svg class="dropdown-icon text-gray-400 me-2" fill="currentColor" viewBox="0 0 20 20" xmlns="http://www.w3.org/2000/svg"><path fill-rule="evenodd" d="M10 2a4 4 0 00-4 4v1H5a1 1 0 00-.994.89l-1 9A1 1 0 004 18h12a1 1 0 00.994-1.11l-1-9A1 1 0 0015 7h-1V6a4 4 0 00-4-4zm2 5V6a2 2 0 10-4 0v1h4zm-6 3a1 1 0 112 0 1 1 0 01-2 0zm7-1a1 1 0 100 2 1 1 0 000-2z" clip-rule="evenodd"></path></svg>
                            Orders
                        </a>
                        <a class="dropdown-item d-flex align-items-center" href="#">
                            <svg class="dropdown-icon text-gray-400 me-2" fill="currentColor" viewBox="0 0 20 20" xmlns="http://www.w3.org/2000/svg"><path fill-rule="evenodd" d="M3 3a1 1 0 000 2v8a2 2 0 002 2h2.586l-1.293 1.293a1 1 0 101.414 1.414L10 15.414l2.293 2.293a1 1 0 001.414-1.414L12.414 15H15a2 2 0 002-2V5a1 1 0 100-2H3zm11 4a1 1 0 10-2 0v4a1 1 0 102 0V7zm-3 1a1 1 0 10-2 0v3a1 1 0 102 0V8zM8 9a1 1 0 00-2 0v2a1 1 0 102 0V9z" clip-rule="evenodd"></path></svg>
                            Console
                        </a>
                        <div role="separator" class="dropdown-divider my-1"></div>
                        <a class="dropdown-item d-flex align-items-center" href="#">
                            <svg class="dropdown-icon text-gray-800 me-2" fill="currentColor" viewBox="0 0 20 20" xmlns="http://www.w3.org/2000/svg"><path d="M9 2a1 1 0 000 2h2a1 1 0 100-2H9z"></path><path fill-rule="evenodd" d="M4 5a2 2 0 012-2 3 3 0 003 3h2a3 3 0 003-3 2 2 0 012 2v11a2 2 0 01-2 2H6a2 2 0 01-2-2V5zm3 4a1 1 0 000 2h.01a1 1 0 100-2H7zm3 0a1 1 0 000 2h3a1 1 0 100-2h-3zm-3 4a1 1 0 100 2h.01a1 1 0 100-2H7zm3 0a1 1 0 100 2h3a1 1 0 100-2h-3z" clip-rule="evenodd"></path></svg>
                            All Reports
                        </a>
                    </div>
                </div>
            </div> -->
            
            
            <div class="row pt-4">
                <div class="col-12 col-xl-8">
                    <div class="card card-body border-0 shadow">
                        <h2 class="h5 mb-4">Thông tin sản phẩm </h2>
                        <form:form action ="${applicationScope.productPage }/edit/${product.id}" method = "post" modelAttribute="product" enctype="multipart/form-data">
                            <div class="row mb-3">
                                <div class="col-md-6">
                                    <div>
                                        <label for="first_name">#</label>
                                        <form:input class="form-control" id="id" type="text" value="${product.id}" path="id" readonly="true" />
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div>
                                        <label for="last_name">Tên sản phẩm</label>
                                        <div class="input-group">
                                        	<form:input path="name" class="form-control" id="productName"  type="text" value = "${product.name}" />
                                    	</div>	
                                    </div>
                                </div>
                            </div>
                            
                            <div class="row mb-3">
                                <div class="col-md-6">
                                    <div>
                                        <label for="quantity">Số lượng</label>
                                        <form:input class="form-control" id="quantity" path = "quantity" type="text" value = "${product.quantity }" />
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div>
                                        <label for="unit">Đơn vị tính</label>
                                        <form:input class="form-control" id="unit" path = "unit" type="text" value = "${product.unit}" />
                                    </div>
                                </div>
                            </div>
                            
                            <div class="row mb-3">
                                <div class="col-md-6">
                                    <div>
                                        <label for="price">Giá tiền</label>
                                        <div class="input-group">
                                        	<span class="input-group-text">
                                        		<svg class="icon icon-xs" fill="none" stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg"><path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M17 9V7a2 2 0 00-2-2H5a2 2 0 00-2 2v6a2 2 0 002 2h2m2 4h10a2 2 0 002-2v-6a2 2 0 00-2-2H9a2 2 0 00-2 2v6a2 2 0 002 2zm7-5a2 2 0 11-4 0 2 2 0 014 0z"></path></svg>
											</span>
                                        <form:input class="form-control" id="price" path="price" type="text" label="<fmt:formatNumber value='${product.price}' type='number' groupingUsed = 'false' />" />
                                    	</div>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div>
                                        <label for="unit">Danh mục</label>
                                        <form:select path="category.id"  class="form-select ${categoryValid }" id="category" aria-describedby="catgoryHelp" >
	                                     	<form:option value="">Chọn danh mục của sản phẩm</form:option>
	                                     	<form:options items="${categories}" itemLabel="name" itemValue="id"/>
	                                 	</form:select>
	                                 	<form:errors path = "category" class="invalid-feedback"/>
                                    </div>
                                </div>
                            </div>
                            
                            <div class="mb-3 d-flex overflow-auto pt-4 flex-nowrap" id="preview-images">
								<c:forEach items="${product.images}" var="image" varStatus="i">
									<div class='position-relative mr-2 me-3 animate__animated animate__fadeIn' style='height: 150px; width: 150px; flex: 0 0 auto'>
                                  		<i class='bi bi-x-circle-fill position-absolute top-0 start-100 translate-middle text-danger h5' onclick='removeFileFromFileList(this)' data-id="${i.index}"></i>
                                  	<img src='${image.image }' width='150' height='150' id="image"/>
                                  </div>
									<%-- <img src="${image.image }" width='150' height='150' id="image" data-id="${i.index}"/> --%>
								</c:forEach>
							</div>

					          <div class="row mb-3">
					           <label for="formFile" class="form-label">Chọn file hình ảnh</label>
					           <form:input class="form-control" type="file" id="images" path="images" accept="image/*" multiple="multiple" />
					           <small class="form-text text-muted">Chỉ nhận file ảnh, kích thước tối đa 20MB</small>
					          </div>
                            
                            <div class="row mb-3">
                                <div class="col-md-12">
                                    <div class="form-group">
                                        <label for="">Mô tả</label>
                                        <form:textarea class="form-control" id="description" path="description" rows="4"></form:textarea>
                                    </div>
                                </div>
                            </div>
                            
                            <div class="row align-items-center mb-3">
                                <div class="col-md-12">
                                    <label for="birthday">Ngày thêm</label>
                                    <div class="input-group">
                                        <span class="input-group-text">
                                           <svg class="icon icon-xs" fill="none" stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg"><path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M8 7V3m8 4V3m-9 8h10M5 21h14a2 2 0 002-2V7a2 2 0 00-2-2H5a2 2 0 00-2 2v12a2 2 0 002 2z"></path></svg>
                                        </span>
                                        
                                        <form:input path="dateAdded" label ="<fmt:formatDate value='${product.dateAdded}' pattern='dd/MM/yyyy' />" class="form-control" id="dateAdded" type="text" readonly="true" />                                               
                                     </div>
                                </div>
                            </div>
                            
                            <div class="mt-4">
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
                               		<img src="${product.images.size() != 0 ? product.images[0].image : '' }" class="img-fluid" style="height: 100%;" alt="${product.name}" id="hero-image"/>  		
                                </div>
                                <div class="card-body pb-5">
                                    <!-- <img src="../assets/img/team/profile-picture-1.jpg" class="avatar-xl rounded-circle mx-auto mt-n7 mb-4" alt="Neil Portrait"> -->
                                    <h4>${product.name }</h4>
                                    <h6 class="fw-normal text-success">${product.category.name}</h6>
                                    <h4 class="text-gray d-flex align-items-center justify-content-center">
                                    	<svg class="icon icon-xs me-2" fill="none" stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg"><path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M7 20l4-16m2 16l4-16M6 9h14M4 15h14"></path></svg>
                                    	${product.id }
                                    </h4>
                                    <p class="text-danger d-flex align-items-center justify-content-center mb-0">
                                    	<svg class="icon icon-xs me-2" fill="currentColor" stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg"><path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M4.318 6.318a4.5 4.5 0 000 6.364L12 20.364l7.682-7.682a4.5 4.5 0 00-6.364-6.364L12 7.636l-1.318-1.318a4.5 4.5 0 00-6.364 0z"></path></svg>
                                    	${product.favoriteProducts.size() }
                                    </p>
                                    <p class="text-info mb-4 d-flex align-items-center justify-content-center">
                                    	<svg class="icon icon-xs me-2" fill="none" stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg"><path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M15 12a3 3 0 11-6 0 3 3 0 016 0z"></path><path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M2.458 12C3.732 7.943 7.523 5 12 5c4.478 0 8.268 2.943 9.542 7-1.274 4.057-5.064 7-9.542 7-4.477 0-8.268-2.943-9.542-7z"></path></svg>
                                    	${product.views }
                                    </p>
                                    
                                    <a class="btn btn-sm btn-secondary d-inline-flex align-items-center" href="${applicationScope.productPage }/${product.id }">
                                        <svg class="icon icon-xs me-2" fill="none" stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg"><path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M13 16h-1v-4h-1m1-4h.01M21 12a9 9 0 11-18 0 9 9 0 0118 0z"></path></svg>
                                        <span>Xem sản phẩm</span>
                                    </a>
                                </div>
                             </div>
                        </div>
                        
                        <!-- <div class="col-12">
                            <div class="card card-body border-0 shadow">
                                <h2 class="h5 mb-4">Đổi ảnh sản phẩm</h2>
                                <div class="d-flex align-items-center">
                                    <div class="me-3">
                                        Avatar
                                        <img class="rounded avatar-xl" src="../assets/img/profile-cover.jpg" alt="change cover">
                                    </div>
                                    <div class="file-field">
                                        <div class="d-flex justify-content-xl-center ms-xl-3">
                                            <div class="d-flex">
                                                <svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" fill="currentColor" class="bi bi-paperclip icon text-gray-500 me-2" viewBox="0 0 16 16">
												  <path d="M4.5 3a2.5 2.5 0 0 1 5 0v9a1.5 1.5 0 0 1-3 0V5a.5.5 0 0 1 1 0v7a.5.5 0 0 0 1 0V3a1.5 1.5 0 1 0-3 0v9a2.5 2.5 0 0 0 5 0V5a.5.5 0 0 1 1 0v7a3.5 3.5 0 1 1-7 0V3z"/>
												</svg>
                                                <input type="file" name="image">
                                                <div class="d-md-block text-left">
                                                    <div class="fw-normal text-dark mb-1">Chọn hình ảnh</div>
                                                    <div class="text-gray small">JPG, GIF hoặc PNG. Kích thước tối đa 800K</div>
                                                </div>
                                            </div>
                                        </div>
                                     </div>                                        
                                </div>
                            </div>
                        </div> -->
                        </form:form>
                    </div>
                </div>
            </div>
		<%@include file="/WEB-INF/views/admin/includes/footer/footer.jsp"%>
        </main>
        
        <script type="text/javascript">
           var previewImage = document.getElementById("preview-images");
           var imageUpload = document.getElementById("images");
           var imageElements = document.querySelectorAll("#image");
           var heroImage = document.getElementById("hero-image");
           
           let index = 1;
           let dt = new DataTransfer();
           let numberOfFile = "${product.images.size()}";
           
           if(imageElements.length != 0) {
	           for(let imageEle of imageElements) {
	           	let url = imageEle.src;
	               let fileName = "${product.id}_" + index + "." + imageEle.src.slice((imageEle.src.lastIndexOf(".") - 1 >>> 0) + 2);
	               fetch(url, { mode: "cors" }).then(async (response) => {
	                   const contentType = response.headers.get("content-type");
	                   const blob = await response.blob();
	                   const file = new File([blob], fileName, { contentType });
	                   dt.items.add(file);
	                   imageUpload.files = dt.files;
	                 });
	               index++;
	           }
           }

           imageUpload.onchange = () => {
             updatePreviewImage(imageUpload.files);
           };

           const updatePreviewImage = (files) => {
             let imgElement = "";
             let index = 0;
             for (let file of files) {
               if (file) {
                 imgElement += "<div class='position-relative mr-2 me-3 animate__animated animate__fadeIn' style='height: 150px; width: 150px; flex: 0 0 auto'>";
                 imgElement += "<i class='bi bi-x-circle-fill position-absolute top-0 start-100 translate-middle text-danger h5' onclick='removeFileFromFileList(this)' data-id='" + index + "'></i>";
                 imgElement += "<img src='" + URL.createObjectURL(file) + "' width='150' height='150'/></div>";
               }
               index++;
             }
             previewImage.innerHTML = imgElement;
             if(files.length == 0) heroImage.src = "unknow";
             else heroImage.src = URL.createObjectURL(files[0]);
           };
           
           const removeFileFromFileList = (e) => {
               let index = parseInt(e.dataset.id);
               const dt = new DataTransfer();

               for (let i = 0; i < imageUpload.files.length; i++) {
                 const file = imageUpload.files[i];
                 if (index !== i) dt.items.add(file); // here you exclude the file. thus removing it.
               }

               imageUpload.files = dt.files; // Assign the updates list
               updatePreviewImage(imageUpload.files);
             };
           </script>
        
<!-- ========== All JS files linkup ========= -->
<%@include file="/WEB-INF/views/admin/includes/footer/script.jsp"%>

<!-- ========== Notyf JS linkup ========= -->
<%@include file="/WEB-INF/views/admin/includes/footer/notyf.jsp"%>
    
</body>

</html>
