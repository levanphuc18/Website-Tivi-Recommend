<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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

     <div class="py-4">
         <nav aria-label="breadcrumb" class="d-none d-md-inline-block">
             <ol class="breadcrumb breadcrumb-dark breadcrumb-transparent">
                 <li class="breadcrumb-item">
                     <a href="#">
                         <svg class="icon icon-xxs" fill="none" stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
                             <path
                                 stroke-linecap="round"
                                 stroke-linejoin="round"
                                 stroke-width="2"
                                 d="M3 12l2-2m0 0l7-7 7 7M5 10v10a1 1 0 001 1h3m10-11l2 2m-2-2v10a1 1 0 01-1 1h-3m-6 0a1 1 0 001-1v-4a1 1 0 011-1h2a1 1 0 011 1v4a1 1 0 001 1m-6 0h6"
                             ></path>
                         </svg>
                     </a>
                 </li>
                 <li class="breadcrumb-item">Nhà cung cấp</li>
                 <li class="breadcrumb-item active" aria-current="page"><a href="${requestScope['javax.servlet.forward.request_uri']}">${supplier.id }</a></li>
             </ol>
         </nav>
         <div class="d-flex justify-content-between w-100 flex-wrap">
             <div class="mb-3 mb-lg-0">
                 <h1 class="h4">Thông tin nhà cung cấp</h1>
             </div>
         </div>
     </div>

     <div class="row">
         <div class="col-lg-12">
             <div class="card border-0 shadow">
                 <div class="card-body">
                     <div class="row d-flex">
                         <div class="d-flex col-md-9 flex-md-row col-sm-8 flex-column mt-2">
                             <div class="col-md-10 col-12 d-flex flex-column justify-content-center">
                                 <div class="">
                                     <h3 class="fw-extrabold mb-0">${supplier.name }</h3>
                                     
                                     <div>
                                         <span class="small text-gray-500">Số điện thoại: ${supplier.phone}</span>                                                                             
                                     </div>
                                     <div>
                                         <span class="small text-gray-500">Email: ${supplier.email}</span>                                                                             
                                     </div>
                                     <div>
                                         <span class="small text-gray-500">Địa chỉ: ${supplier.address}</span>                                                                             
                                     </div>
                                     <div>
                                         <span class="small text-gray-500">Mã số thuế: ${supplier.tax}</span>                                                                             
                                     </div>
                                 </div>
                             </div>

                             <div class="col-md-2 col-12 mt-2 d-flex flex-column">
                                 <a href="${applicationScope.supplierPage }/edit/${supplier.id}" class="btn btn-block btn-gray-800 mb-3 d-inline-flex align-items-center justify-content-center">
	                                 <svg class="icon icon-xs me-2" fill="none" stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg"><path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M11 5H6a2 2 0 00-2 2v11a2 2 0 002 2h11a2 2 0 002-2v-5m-1.414-9.414a2 2 0 112.828 2.828L11.828 15H9v-2.828l8.586-8.586z"></path></svg>
	                                 Chỉnh sửa
	                             </a>

                                 <!-- Button Delete -->
                                 <button class="btn btn-block btn-danger mb-3 d-inline-flex align-items-center justify-content-center" data-bs-toggle="modal" data-bs-target="#modal-warning" id="btn-delete" data-id="${supplier.id }">
                                     <svg class="icon icon-xs me-2" fill="none" stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg"><path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M19 7l-.867 12.142A2 2 0 0116.138 21H7.862a2 2 0 01-1.995-1.858L5 7m5 4v6m4-6v6m1-10V4a1 1 0 00-1-1h-4a1 1 0 00-1 1v3M4 7h16"></path></svg>
                                     Xoá
                                 </button>
                             </div>
                         </div>
                     </div>
                 </div>
             </div>
         </div>
     </div>

     <hr class="mt-4" />
     

     <%@include file="/WEB-INF/views/admin/includes/footer/footer.jsp"%>
 </main>
 
  <!-- ========== Modal warning linkup ========= -->
 <%@include file="/WEB-INF/views/admin/pages/config/deleteConfigModal.jsp"%>

 <!-- ========== All JS files linkup ========= -->
 <%@include file="/WEB-INF/views/admin/includes/footer/script.jsp"%>
 
 <script type="text/javascript">
	const btnDelete = document.querySelector("#btn-delete");
	
	btnDelete.addEventListener('click', () => {
			let supplierId = btnDelete.dataset.id;
			const btnConfirm = document.querySelector("#btnConfirm");
			btnConfirm.href = "<c:out value='${applicationScope.supplierPage}'/>/delete/" + supplierId;
		})
 </script>
</body>
</html>
