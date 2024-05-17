<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/layouts/admin/header.jsp"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- Google Fonts -->
<link href="https://fonts.gstatic.com" rel="preconnect">
<link href="resource/assets/dist/css/bill.css" rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i"
	rel="stylesheet">

<body>
	<div class="text-center" style="font-size: 20px; padding-bottom: 20px">Thống Kê Phiếu Mượn</div>

<form:form method="GET" action="/QuanLyThietBi/quan-tri/thong-ke/tim-kiem">
	<section class="section">
		<div class="card">
			<div class="card-body">
				<h5 class="card-title">Bộ lọc</h5>
				<div class="row">
					<div class="col-lg-5">
						<div class="col-12">
							<div class="row">
								<div class="input-lable w-25 form-control"
									id="inputGroup-sizing-default">Từ ngày</div>
								<input type="date" name="tungaypm"
									class="date-filter w-75 form-control" value="${timeFrom }">
							</div>
							<div class="row">
								<div class="input-lable w-25 form-control"
									id="inputGroup-sizing-default">Đến</div>
								<input type="date" class="date-filter w-75 form-control"
									name="denngaypm" value="${timeTo}" max=${timeNow }>
							</div>
						</div>
					</div>

					<div class="col-lg-7">
						<div class="col-12">
							<div class="d-flex" style="gap:20px; height:40px">
								<input name="inputText" class="form-control"
									placeholder="Tìm kiếm..." />
								<select class="form-select " style="width: 200px"
									name="selectOption" id="cars">

									<option value="0">Mã Phòng</option>
									<option value="1">Mã sinh viên mượn</option>
									<option value="2">Mã nhân viên lập</option>
								</select>


								<button type="submit" class="btn btn-primary"
									style="width: 165px">Lọc</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
</form:form>


	<div class="manage-body" style="min-height: 600px; margin-top: 50px">
		<div class="table-container mt-3" style="font-size: 13px">
			<div class="table-head">
				<table>
					<thead>
						<tr class="table-header">
							<th>Mã Phòng</th>
							<th>Thời điểm mượn</th>
							<th>Hạn điểm trả</th>
							<th>Mã SV mượn</th>
							<th>Mã NV xuất</th>
						</tr>
					</thead>
				</table>
			</div>
			<div class="table-body">
				<table>
					<tbody>
						<c:forEach var="item" items="${ listphieumuon }">
							<tr>
								<td>${ item.maPhong }</td>
								<td>${item.thoiDiemMuon}</td>
								<td>${item.hanTra}</td>
								<td>${item.maSVMuon}</td>
								<td>${item.maNVLap}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>








	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
<script>
	$
	{
		thongbao
	}
	// toast thông báo
	var tb = document.getElementById('toast');
	if (tb.classList.contains('success')) {
		showtoast({
			title : "Thành công!",
			message : "'${myparam}' loại thiết bị thành công.",
			type : "success",
			duration : 3000
		});
	} else if (tb.classList.contains('error')) {
		showtoast({
			title : "Lỗi!",
			message : "${myparam} loại thiết bị thất bại.",
			type : "error",
			duration : 3000
		});

	}
</script>
</html>