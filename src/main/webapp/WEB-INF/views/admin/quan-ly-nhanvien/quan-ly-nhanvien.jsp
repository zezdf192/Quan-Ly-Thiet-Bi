<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

</head>
<body>
	<div class="manage-body" style="min-height: 600px">
		<h2 class="title">Quản lý nhân viên</h2>
		<div class="header-exam">
			<button type="button" class="btn btn-primary" data-bs-toggle="modal"
				data-bs-target="#exampleModal">Thêm mới nhân viên</button>
			<div>
				<form:form class="d-flex" style="gap:20px; height:40px" method="GET"
					action="/QuanLyThietBi/quan-tri/quan-ly-nhanvien/tim-kiem-nhanvien">
					<input name="inputText" class="form-control"
						placeholder="Tìm kiếm..." />
					<select class="form-select " style="width: 250px"
						name="selectOption" id="cars">


						<option value="10">Tất cả trạng thái</option>
						<option value="1">Đang cho mượn</option>
						<option value="0">Sẵn sàng cho mượn</option>

					</select>


					<button type="submit" class="btn btn-primary" style="width: 165px">Tìm
						kiếm</button>
				</form:form>

			</div>
		</div>
		<div class="table-container mt-3">
			<div class="table-head">
				<table>
					<thead>
						<tr class="table-header">
							<th>Mã nhân viên</th>
							<th>Tên nhân viên</th>

							<th>Tài khoản</th>
							<th>Đang làm việc</th>
							<th>Các thao tác</th>
						</tr>
					</thead>
				</table>
			</div>
			<div class="table-body">
				<table>
					<tbody>
						<c:forEach var="item" items="${ nhanviens }">
							<tr>
								<td>${ item.maNV }</td>
								<td>${item.tenNV }</td>

								<td>${item.taiKhoan }</td>
								<td><c:choose>
										<c:when test="${item.dangLamViec==1}">
											<input class="form-check-input" type="checkbox" checked
												disabled>
										</c:when>
										<c:otherwise>
											<input type="checkbox" disabled>
										</c:otherwise>
									</c:choose></td>
								<td class="action"
									style="display: flex; gap: 10px; justify-content: center;">
									<button class="btn btn-success">Xem</button> <form:form
										method="GET"
										action="/QuanLyThietBi/quan-tri/quan-ly-nhanvien/sua-tt-nhanvien/${ item.maNV }">
										<button type="submit" class=" btn btn-warning">Sửa</button>
									</form:form>

									<button text="${item.maNV}"
										class=" btn btn-danger showAlertBtn">Xóa</button>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>

		<!-- Modal -->
		<div class="modal fade" id="exampleModal" tabindex="-1"
			aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog ">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="exampleModalLabel">Thêm mới nhânviên</h5>
						<button type="button" class="btn-close" data-bs-dismiss="modal"
							aria-label="Close"></button>
					</div>
					<div class="modal-body">
						<form:form action="/QuanLyThietBi/quan-tri/quan-ly-nhanvien/tao-nhanvien" class="row g-3" modelAttribute="nhanvienmoi" method="POST">
							<div class="col-md-12">
								<label for="manv" class="form-label">Mã nhân viên</label> 
								<input name="maNV" type="text" class="form-control" />
							</div>
							<div class="col-md-12">
								<label for="tennv" class="form-label">Họ và tên</label>
								<input name="tenNV" type="text" class="form-control" id="tennv" />
							</div>
							<div class="col-12">
								<label for="cmnd" class="form-label">CMND</label> 
								<input name="CMND" type="text" class="form-control" id="cmnd" />
							</div>
							<div class="col-12">
								<label for="email" class="form-label">Email</label>
								<input name="email" type="email" class="form-control" id="email" />
							</div>
							<div class="col-md-12">
								<label for="sdt" class="form-label">Số điện thoại</label> 
								<input name="sdt" type="text" class="form-control" id="sdt" />
							</div>
							<div class="col-md-12">
								<label for="taikhoan" class="form-label">Tài khoản</label> 
								<input name="taiKhoan" type="text" class="form-control" id="taikhoan" />
							</div>
							
							<div class="col-12">
								<button type="submit" class="btn btn-primary float-end">Thêm</button>
							</div>
						</form:form>
					</div>

				</div>
			</div>
		</div>
		<!-- End Modal -->
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>