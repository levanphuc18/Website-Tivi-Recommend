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
            <div class="card-body">
                <div class="row mb-4">
                    <div class="card card-body border-0 shadow mb-4">
                        <h2 class="h5 mb-4">Thông tin nhà cung cấp</h2>
                        <form:form action ="${applicationScope.supplierPage }/edit/${supplier.id}" method = "post" modelAttribute="supplier" enctype="multipart/form-data">
                            <div class="row">
                                <div class="col-md-6 mb-3">
                                    <div>
                                        <label for="first_name">#</label>
                                        <form:input class="form-control" path="id" type="text" value = "${supplier.id}" readonly="true" />
                                    </div>
                                </div>
                                <div class="col-md-6 mb-3">
                                    <div>
                                        <label for="last_name">Tên nhà cung cấp</label>
                                        <form:input class="form-control ${nameValid }" id="name" path="name" type="text" />
                                       	<form:errors path="name" class="invalid-feedback"/>
                                    </div>
                                </div>
                            </div>
                            
                            <div class="row">
                                <div class="col-md-12 mb-3">
                                    <div>
                                        <label for="last_name">Số điện thoại</label>
                                        <form:input class="form-control ${phone}" id="phone" path="phone" type="text" />
                                    </div>
                                    <div>
                                        <label for="last_name">Email</label>
                                        <form:input class="form-control ${email}" id="email" path="email" type="text" />               
                                    </div>
                                        <label for="last_name">Địa chỉ</label>
                                        <form:input class="form-control ${address}" id="address" path="address" type="text" />
                                    
                                    <div>
                                        <label for="last_name">Mã số thuế</label>
                                        <form:input class="form-control ${tax}" id="tax" path="tax" type="text" />                                     	
                                    </div>
                                    
                                </div>
                            </div>
                            <div class="mt-3">
                                <button class="btn btn-gray-800 mt-2 animate-up-2 d-inline-flex align-items-center" type="submit">
                                	<svg class="icon icon-xs me-2" fill="none" stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg"><path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M5 13l4 4L19 7"></path></svg>
                                	Lưu
                                </button>
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
