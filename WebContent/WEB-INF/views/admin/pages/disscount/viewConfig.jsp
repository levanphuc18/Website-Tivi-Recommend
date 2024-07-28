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
                 <li class="breadcrumb-item">Cấu hình</li>
                 <li class="breadcrumb-item active" aria-current="page"><a href="${requestScope['javax.servlet.forward.request_uri']}">${config.id }</a></li>
             </ol>
         </nav>
         <div class="d-flex justify-content-between w-100 flex-wrap">
             <div class="mb-3 mb-lg-0">
                 <h1 class="h4">Thông tin cấu hình</h1>
             </div>
         </div>
     </div>

     <div class="row">
         <div class="col-lg-12">
             <div class="card border-0 shadow">
                 <div class="card-body">
                     <div class="row d-flex">
                         <div class="col-md-3 col-sm-4 col-12">
                             <img src="${config.image }" class="img-fluid" style="height: 100%;" />
                         </div>

                         <div class="d-flex col-md-9 flex-md-row col-sm-8 flex-column mt-2">
                             <div class="col-md-10 col-12 d-flex flex-column justify-content-center">
                                 <div class="">
                                     <h3 class="fw-extrabold mb-0">${config.name }</h3>
                                     
                                     <div>
                                         <span class="small text-gray-500">Màu: ${config.color}</span>                                                                             
                                     </div>
                                     <div>
                                         <span class="small text-gray-500">Loại màn hình: ${config.screentype}</span>                                                                             
                                     </div>
                                     <div>
                                         <span class="small text-gray-500">Kích thước: ${config.screensize}</span>                                                                             
                                     </div>
                                     <div>
                                         <span class="small text-gray-500">Tần số quét: ${config.scanningfrequency}</span>                                                                             
                                     </div>
                                     <div>
                                         <span class="small text-gray-500">Độ phân giải: ${config.resolution}</span>                                                                             
                                     </div>
                                     <div>
                                         <span class="small text-gray-500">Tiện ích: ${config.utilities}</span>                                                                             
                                     </div>
                                     <div>
                                         <span class="small text-gray-500">Hệ điều hành: ${config.operatingsystem}</span>                                                                             
                                     </div>
                                     
                                 </div>

                                 <div class="d-flex mt-1">
                                     <div>
                                         <span class="small text-gray-500">Ngày thêm:</span>
                                         <span class="fw-normal">
                                         	<fmt:setLocale value="vi_VN" scope="session"/>
                            				<fmt:formatDate value="${config.dateAdded}" pattern="dd/MM/yyyy"/>
                                         </span>
                                     </div>
                                 </div>

                                 <div class="d-flex mt-1">
                                     <div>
                                         <span class="small text-gray-500">Số lượng sản phẩm thuộc danh mục:</span>
                                         <span class="text-success fw-bolder fs-6">${config.products.size() }</span>
                                     </div>
                                 </div>
                             </div>

                             <div class="col-md-2 col-12 mt-2 d-flex flex-column">
                                 <a href="${applicationScope.configPage }/edit/${config.id}" class="btn btn-block btn-gray-800 mb-3 d-inline-flex align-items-center justify-content-center">
	                                 <svg class="icon icon-xs me-2" fill="none" stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg"><path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M11 5H6a2 2 0 00-2 2v11a2 2 0 002 2h11a2 2 0 002-2v-5m-1.414-9.414a2 2 0 112.828 2.828L11.828 15H9v-2.828l8.586-8.586z"></path></svg>
	                                 Chỉnh sửa
	                             </a>

                                 <!-- Button Delete -->
                                 <button class="btn btn-block btn-danger mb-3 d-inline-flex align-items-center justify-content-center" data-bs-toggle="modal" data-bs-target="#modal-warning" id="btn-delete" data-id="${config.id }">
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

     <div class="row mt-4">
         <div class="py-4 d-flex justify-content-between w-100 flex-wrap">
             <div class="mb-3 mb-lg-0">
                 <h1 class="h4">Danh sách các sản phẩm</h1>
                 <p class="mb-0 text-gray-400">Các sản phẩm thuộc danh mục</p>
             </div>
         </div>

         <%@include file="/WEB-INF/views/admin/includes/nav/sidebar.jsp"%>

         <jsp:useBean id="pagedListHolder" scope="request" type="org.springframework.beans.support.PagedListHolder" />
         <c:url value="${pagedLink }" var="pagedLink">
             <c:param name="p" value="~" />
         </c:url>

         
     </div>
     <div class="card card-body border-0 shadow table-wrapper table-responsive overflow-hidden">
         <table class="table table-hover table-responsive" id="datatable">
             <thead class="thead-dark">
                 <tr>
                     <th class="border-gray-200 rounded-start">#</th>
                     <th class="border-gray-200">Tên sản phẩm</th>
                     <th class="border-gray-200">Số lượng</th>
                     <th class="border-gray-200">Đơn vị tính</th>
                     <th class="border-gray-200">Giá</th>
                     <th class="border-gray-200">Mô tả</th>
                     <!-- <th class="border-gray-200">Loại</th> -->
                    <!--  <th class="border-gray-200">Ngày thêm</th> -->
                     <th class="border-gray-200">Hình ảnh</th>
                     <th class="border-gray-200 rounded-end"></th>
                 </tr>
             </thead>
             <tbody>
                 <!-- Item -->
                 <c:forEach items="${pagedListHolder.pageList}" var="product">
                     <tr>
                         <td>
                             <a href="${applicationScope.productPage}/${product.id}" class="fw-bold">
                                 ${product.id}
                             </a>
                         </td>
                         <td><span class="fw-normal text-wrap">${product.name}</span></td>
                         <td><span class="fw-bold text-wrap">${product.quantity}</span></td>
                         <td><span class="fw-normal text-wrap">${product.unit}</span></td>
                         <td>
                             <span class="fw-bold text-wrap">
                                 <fmt:setLocale value="vi_VN" scope="session" />
                                 <fmt:formatNumber value="${product.price}" type="currency" />
                             </span>
                         </td>
                         <td><span class="fw-normal text-wrap">${product.description}</span></td>
                         <%-- <td><span class="fw-bold text-danger text-wrap">${product.config.name}</span></td> --%>
                         <%-- <td>
                             <span class="fw-normal text-wrap">
                                 <fmt:formatDate value="${product.dateAdded}" pattern="dd/MM/yyyy" />
                             </span>
                         </td> --%>
                         <td>
                             <span class="fw-normal text-wrap">
                                 <img src="${!product.images.isEmpty() ? product.images.get(0).image : ''}" width="100" height="100" alt="${product.name}" />
                             </span>
                         </td>
                         <td class="text-center">
                             <div class="btn-group">
                                 <button class="btn btn-link text-dark dropdown-toggle dropdown-toggle-split m-0 p-0" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                     <svg class="icon icon-xxs" stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
                                         <path
                                             stroke-linecap="round"
                                             stroke-linejoin="round"
                                             stroke-width="2"
                                             d="M5 12h.01M12 12h.01M19 12h.01M6 12a1 1 0 11-2 0 1 1 0 012 0zm7 0a1 1 0 11-2 0 1 1 0 012 0zm7 0a1 1 0 11-2 0 1 1 0 012 0z"
                                         ></path>
                                     </svg>

                                     <span class="visually-hidden">Toggle Dropdown</span>
                                 </button>
                                 <div class="dropdown-menu py-0">
                                     <a class="dropdown-item rounded-top" href="${applicationScope.productPage}/${product.id}">
                                         <i class="bi bi-eye-fill dropdown-icon text-gray-400 me-2 icon-sm"></i>
                                         Chi tiết
                                     </a>
                                     <a class="dropdown-item text-warning" href="${applicationScope.productPage}/edit/${product.id}">
                                         <i class="bi bi-pencil-square dropdown-icon me-2"></i>
                                         Chỉnh sửa
                                     </a>
                                     <%-- <a id="btnDelete" class="dropdown-item text-danger rounded-bottom" data-bs-toggle="modal" data-bs-target="#modal-warning" data-id="${product.id}">
                                         <i class="bi bi-trash2-fill dropdown-icon me-2"></i>
                                         Xoá
                                     </a> --%>
                                 </div>
                             </div>
                         </td>
                     </tr>
                 </c:forEach>
             </tbody>
         </table>

         <div>
             <tg:adminPaging pagedListHolder="${pagedListHolder}" pagedLink="${pagedLink}" />
         </div>
     </div>

     <%@include file="/WEB-INF/views/admin/includes/footer/footer.jsp"%>
 </main>
 
  <!-- ========== Modal warning linkup ========= -->
 <%@include file="/WEB-INF/views/admin/pages/config/deleteConfigModal.jsp"%>

 <!-- ========== All JS files linkup ========= -->
 <%@include file="/WEB-INF/views/admin/includes/footer/script.jsp"%>
 
 <script type="text/javascript">
	const btnDelete = document.querySelector("#btn-delete");
	
	btnDelete.addEventListener('click', () => {
			let configId = btnDelete.dataset.id;
			const btnConfirm = document.querySelector("#btnConfirm");
			btnConfirm.href = "<c:out value='${applicationScope.configPage}'/>/delete/" + configId;
		})
 </script>
</body>
</html>
