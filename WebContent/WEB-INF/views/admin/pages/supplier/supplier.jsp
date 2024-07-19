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

<jsp:useBean id="pagedListHolder" scope="request"
	type="org.springframework.beans.support.PagedListHolder" />
<c:url
	value="${pagedLink}"
	var="pagedLink">
	<c:param name="p" value="~" />
</c:url>


<main class="content">
	<%@include file="/WEB-INF/views/admin/includes/nav/navbar.jsp"%>
	
            <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center py-4">
                <div class="d-block mb-4 mb-md-0">
                    <nav aria-label="breadcrumb" class="d-none d-md-inline-block">
                        <ol class="breadcrumb breadcrumb-dark breadcrumb-transparent">
                            <li class="breadcrumb-item">
                                <a href="#">
                                    <svg class="icon icon-xxs" fill="none" stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
                                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M3 12l2-2m0 0l7-7 7 7M5 10v10a1 1 0 001 1h3m10-11l2 2m-2-2v10a1 1 0 01-1 1h-3m-6 0a1 1 0 001-1v-4a1 1 0 011-1h2a1 1 0 011 1v4a1 1 0 001 1m-6 0h6"></path>
                                    </svg>
                                </a>
                            </li>
                            <li class="breadcrumb-item">Nhà cung cấp</li>
                            <li class="breadcrumb-item active" aria-current="page"><a href="${requestScope['javax.servlet.forward.request_uri']}">Quản lý</a></li>
                        </ol>
                    </nav>
                    <h2 class="h4">Nhà cung cấp</h2>
                    <!-- <p class="mb-0">Trang sản phẩm</p> -->
                </div>
                <div class="btn-toolbar mb-2 mb-md-0">
                    <a href="${applicationScope.addSupplierPage }" class="btn btn-sm btn-secondary d-inline-flex align-items-center">
                        <svg class="icon icon-xs me-2" fill="none" stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg"><path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 6v6m0 0v6m0-6h6m-6 0H6"></path></svg>
                        Thêm nhà cung cấp
                    </a>
                    <!-- <div class="btn-group ms-2 ms-lg-3">
                        <button type="button" class="btn btn-sm btn-outline-gray-600">Chia sẻ</button>
                        <button type="button" class="btn btn-sm btn-outline-gray-600">Xuất file</button>
                    </div> -->
                </div>
            </div>
            
            <div class="table-settings mb-4">
                <div class="row align-items-center justify-content-between">
                    <div class="col col-md-6 col-lg-3 col-xl-4 ">
                    <form action = "${applicationScope.supplierPage }" method="get">
                        <div class="input-group me-2 me-lg-3 fmxw-300">
                            <input type="text" class="form-control" placeholder="Tìm kiếm" name = "search"/>
                        	<a class="input-group-text" href = "${applicationScope.supplierPage}">
                        		<svg class="icon icon-xs" fill="none" stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg"><path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M6 18L18 6M6 6l12 12"></path></svg>
                                </a>

                                <button class="input-group-text" type="submit">
                                    <svg class="icon icon-xs" fill="none" stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg"><path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M21 21l-6-6m2-5a7 7 0 11-14 0 7 7 0 0114 0z"></path></svg>
                                </button>
                        </div>
                     </form>
                      
                    </div>
                    
                </div>
            </div>
				
            <div class="card card-body border-0 shadow table-wrapper table-responsive overflow-hidden">
                <table class="table table-hover">
                    <thead class="thead-dark">
                        <tr>
                            <th class="border-gray-200 rounded-start">#</th>
                            <th class="border-gray-200">Tên nhà cung cấp</th>
                            <th class="border-gray-200">Số điện thoại</th>
                            <th class="border-gray-200">Email</th>						
                            <th class="border-gray-200">Địa chỉ</th>
                            <th class="border-gray-200">Mã số thuế</th>
                            <th class="border-gray-200 rounded-end"></th>
                        </tr>
                    </thead>
                    <tbody>
                        <!-- Item -->
                        <c:forEach items="${pagedListHolder.pageList}" var="supplier" varStatus="i">
                        <tr>
                            <td>
                                <a href="${applicationScope.supplierPage}/${supplier.id}" class="fw-bold">
                                    ${supplier.id}
                                </a>
                            </td>
                            <td><span class="fw-normal">${supplier.name}</span></td>
                            <td><span class="fw-normal text-wrap">${supplier.phone}</span></td>
                            <td><span class="fw-normal text-wrap">${supplier.email}</span></td>
                            <td><span class="fw-normal text-wrap">${supplier.address}</span></td>
                            <td><span class="fw-normal text-wrap">${supplier.tax}</span></td>
                            
                            <td class = "text-center">
                                <div class="btn-group">
                                    <button class="btn btn-link text-dark dropdown-toggle dropdown-toggle-split m-0 p-0" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                      	<svg class="icon icon-xxs" stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg"><path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M5 12h.01M12 12h.01M19 12h.01M6 12a1 1 0 11-2 0 1 1 0 012 0zm7 0a1 1 0 11-2 0 1 1 0 012 0zm7 0a1 1 0 11-2 0 1 1 0 012 0z"></path></svg>
                                       
                                        <span class="visually-hidden">Toggle Dropdown</span>
                                    </button>
                                    <div class="dropdown-menu py-0">
                                    	
										<a class="dropdown-item rounded-top" href="${applicationScope.supplierPage}/${supplier.id}">
										<i class="bi bi-eye-fill dropdown-icon text-gray-400 me-2 icon-sm"></i>
										Chi tiết</a>
                                    
                                        
                                        <a class="dropdown-item text-warning" href="${applicationScope.supplierPage}/edit/${supplier.id}">
	                                        <i class="bi bi-pencil-square dropdown-icon me-2"></i>
	                                        Chỉnh sửa
                                        </a>
                                        
                                        <a id="btnDelete" class="dropdown-item text-danger rounded-bottom" data-bs-toggle="modal" data-bs-target="#modal-warning" data-id="${supplier.id}">
                                    <i class="bi bi-trash2-fill dropdown-icon me-2"></i>
                                    Xoá
                                 </a>
                                    </div>
                                </div>
                            </td>
                        </tr>   
                      </c:forEach>      
                    </tbody>
                </table>
                
                <div>
                	<tg:adminPaging pagedListHolder="${pagedListHolder}"
					pagedLink="${pagedLink}" />
                </div>
                
                
            </div>
		<%@include file="/WEB-INF/views/admin/includes/footer/footer.jsp"%>

</main>

<!-- ========== All JS files linkup ========= -->
<%@include file="/WEB-INF/views/admin/includes/footer/script.jsp"%>

<!-- ========== Notyf JS linkup ========= -->
<%@include file="/WEB-INF/views/admin/includes/footer/notyf.jsp"%>

<!-- ========== Modal warning linkup ========= -->
 <%@include file="/WEB-INF/views/admin/pages/supplier/deleteSupplierModal.jsp"%>

</body>

<script type="text/javascript">
 		const btnDeletes = document.querySelectorAll("#btnDelete");
 		
 		btnDeletes.forEach(btnDelete => {
 			btnDelete.addEventListener('click', () => {
 	   			let supplierId = btnDelete.dataset.id;
 	   			const btnConfirm = document.querySelector("#btnConfirm");
 	   			btnConfirm.href = "<c:out value='${applicationScope.supplierPage}'/>/delete/" + supplierId;
 	   		})
 		})
   		
   </script>
</html>
