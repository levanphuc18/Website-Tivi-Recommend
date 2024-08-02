<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- ========== Tag Lib ========= -->
<%@include file="/WEB-INF/views/admin/includes/header/taglib.jsp"%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/css/bootstrap-datepicker.min.css"
	rel="stylesheet">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/js/bootstrap-datepicker.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/js/all.min.js"></script>

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
						href="${applicationScope.addConfigPage }">Khuyến mãi</a></li>
					<li class="breadcrumb-item active" aria-current="page">Thêm
						khuyến mãi</li>
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

								<form:form action="${applicationScope.disscountPage }/add"
									method="POST" enctype="multipart/form-data"
									modelAttribute="disscount">
									<div class="mb-4">
										<label for="configName">Tên khuyến mãi</label>
										<form:input type="configName"
											class="form-control ${nameValid}" path="name" id="configName"
											aria-describedby="disscountNameHelp"
											placeholder="Nhập tên khuyến mãi" />
										<form:errors path="name" class="invalid-feedback" />
									</div>

									<div>
										<label for="last_name">Lý do</label>
										<form:input class="form-control ${reason}" id="reason"
											path="reason" type="text" placeholder="Nhập lý do" />
									</div>
									<div>
										<label for="last_name">Mức giảm giá</label>
										<form:input class="form-control ${percent}" id="percent"
											path="percent" type="text" placeholder="Nhập mức giảm giá" />
									</div>

									<div class="row mb-4">
										<div class="col-6">
											<label for="startDate">Ngày bắt đầu</label>
											<div class="input-group date" id="startDatePicker">
												<form:input type="text" class="form-control ${dateValid}"
													path="startDate" id="startDate"
													placeholder="Nhập ngày bắt đầu" data-input="data-input" />
												<div class="input-group-append">
													<span class="input-group-text"><i
														class="fa fa-calendar"></i></span>
												</div>
												<form:errors path="startDate" class="invalid-feedback" />
											</div>
										</div>

										<div class="col-6">
											<label for="endDate">Ngày kết thúc</label>
											<div class="input-group date" id="endDatePicker">
												<form:input type="text" class="form-control ${dateValid}"
													path="endDate" id="endDate"
													placeholder="Nhập ngày kết thúc" data-input="data-input" />
												<div class="input-group-append">
													<span class="input-group-text"><i
														class="fa fa-calendar"></i></span>
												</div>
												<form:errors path="endDate" class="invalid-feedback" />
											</div>
										</div>
									</div>



									<div class="mb-3">
										<button class="btn btn-primary" type="submit">Thêm
											khuyến mãi</button>
									</div>
								</form:form>
							</div>
							<script type="text/javascript">
								$(document)
										.ready(
												function() {
													$('#startDatePicker')
															.datepicker(
																	{
																		format : 'dd/mm/yyyy',
																		todayHighlight : true,
																		autoclose : true
																	});

													$('#endDatePicker')
															.datepicker(
																	{
																		format : 'dd/mm/yyyy',
																		todayHighlight : true,
																		autoclose : true
																	});

													$('form')
															.on(
																	'submit',
																	function(
																			event) {
																		var startDate = $(
																				'#startDate')
																				.val();
																		var endDate = $(
																				'#endDate')
																				.val();

																		if (!startDate
																				|| !endDate) {
																			event
																					.preventDefault();
																			alert('Vui lòng chọn đầy đủ ngày bắt đầu và ngày kết thúc.');
																			return false;
																		}

																		var start = new Date(
																				startDate
																						.split(
																								'/')
																						.reverse()
																						.join(
																								'/'));
																		var end = new Date(
																				endDate
																						.split(
																								'/')
																						.reverse()
																						.join(
																								'/'));

																		if (start > end) {
																			event
																					.preventDefault();
																			alert('Ngày bắt đầu phải nhỏ hơn ngày kết thúc.');
																			return false;
																		}
																	});
												});
							</script>
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
