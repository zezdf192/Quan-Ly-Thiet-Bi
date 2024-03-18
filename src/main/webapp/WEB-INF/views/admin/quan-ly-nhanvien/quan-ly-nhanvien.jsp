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
				data-bs-target="#addNewStaff">Thêm mới nhân viên</button>
			<div>
				<form:form class="d-flex" style="gap:20px; height:40px" method="GET"
					action="/QuanLyThietBi/quan-tri/quan-ly-nhanvien/tim-kiem-nhanvien">
					<input name="inputText" class="form-control"
						placeholder="Tìm kiếm..." />
					<select class="form-select " style="width: 250px"
						name="selectOption" id="cars">


						<option value="0">Mã nhân viên</option>
						<option value="1">Tên nhân viên</option>
						<option value="2">Đang làm việc</option>
						<option value="3">Đã nghỉ việc</option>

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

								<td><c:choose>
										<c:when test="${empty item.taiKhoan}">
											<button type="button" class="btn btn-primary btn-addAccount"
												data-bs-toggle="modal" data-bs-target="#addNewAccount"
												data-bs-whatever="${item.maNV}">Thêm</button>
										</c:when>
										<c:otherwise>
											${item.taiKhoan}
										</c:otherwise>
									</c:choose></td>
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
									<button type="button" class="btn btn-success btn-show"
										data-bs-toggle="modal" data-bs-target="#inforFormStaff"
										data-bs-whatever="${item.maNV}, ${item.tenNV}, ${item.CMND}, ${item.email}, ${item.sdt}">Xem</button>
									<button type="button" class="btn btn-warning btn-update"
										data-bs-toggle="modal" data-bs-target="#updateStaff"
										data-bs-whatever="${item.maNV}, ${item.tenNV}, ${item.CMND}, ${item.email}, ${item.sdt}, ${item.dangLamViec}">Sửa</button>

									<button type="button" class="btn btn-danger btn-delete"
										data-bs-toggle="modal" data-bs-target="#confirmDeleteModal"
										data-bs-whatever="${item.maNV}">Xóa</button>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>

		<!-- Modal thêm mới nhân viên-->
		<div class="modal fade" id="addNewStaff" tabindex="-1"
			aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog ">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="exampleModalLabel">Thêm mới
							nhânviên</h5>
						<button type="button" class="btn-close" data-bs-dismiss="modal"
							aria-label="Close"></button>
					</div>
					<div class="modal-body">
						<form:form
							action="/QuanLyThietBi/quan-tri/quan-ly-nhanvien/tao-nhanvien"
							class="row g-3" modelAttribute="nhanvienmoi" method="POST">
							<div class="col-md-12">
								<label for="manv" class="form-label">Mã nhân viên</label> <input
									name="maNV" type="text" class="form-control" />
							</div>
							<div class="col-md-12">
								<label for="tennv" class="form-label">Họ và tên</label> <input
									name="tenNV" type="text" class="form-control" />
							</div>
							<div class="col-12">
								<label for="cmnd" class="form-label">CMND</label> <input
									name="CMND" type="text" class="form-control" />
							</div>
							<div class="col-12">
								<label for="email" class="form-label">Email</label> <input
									name="email" type="email" class="form-control"  />
							</div>
							<div class="col-md-12">
								<label for="sdt" class="form-label">Số điện thoại</label> <input
									name="sdt" type="text" class="form-control" />
							</div>

							<div class="col-12">
								<button type="submit" class="btn btn-primary float-end">Thêm</button>
							</div>

						</form:form>
					</div>

				</div>
			</div>
		</div>
		<!-- End Modal thêm mới nhân viên-->

		<!-- Modal xem thông tin nhân viên-->
		<div class="modal fade" id="inforFormStaff" tabindex="-1"
			aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog ">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="exampleModalLabel">THÔNG TIN NHÂN VIÊN</h5>
						<button type="button" class="btn-close" data-bs-dismiss="modal"
							aria-label="Close"></button>
					</div>
					<div class="modal-body">
						<form:form 
							id="showInforForm"
							class="row g-3" 
							method="GET"
							action=""
							modelAttribute="nhanvien">
							<div class="col-md-12">
								<label for="manv" class="form-label">Mã nhân viên</label> <input
									name="maNV" type="text" class="form-control" id="maNVInput" disabled="true"/>
							</div>
							<div class="col-md-12">
								<label for="tennv" class="form-label">Họ và tên</label> <input
									name="tenNV" type="text" class="form-control" id="tenNVInput" disabled="true"/>
							</div>
							<div class="col-12">
								<label for="cmnd" class="form-label">CMND</label> <input
									name="CMND" type="text" class="form-control" id="CMNDInput" disabled="true"/>
							</div>
							<div class="col-12">
								<label for="email" class="form-label">Email</label> <input
									name="email" type="email" class="form-control" id="emailInput" disabled="true"/>
							</div>
							<div class="col-md-12">
								<label for="sdt" class="form-label">Số điện thoại</label> <input
									name="sdt" type="text" class="form-control" id="sdtInput" disabled="true"/>
							</div>

							<!-- <div class="col-12">
								<button type="submit" class="btn btn-primary float-end">Đóng</button>
							</div> -->

						</form:form>
					</div>

				</div>
			</div>
		</div>
		<!-- End Modal xem thông tin nhân viên-->

		<!-- Modal xác nhận xóa-->
		<div class="modal fade" id="confirmDeleteModal" tabindex="-1"
			aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog ">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title" id="exampleModalLabel">Xác nhận xóa
							nhân viên</h4>
						<button type="button" class="btn-close" data-bs-dismiss="modal"
							aria-label="Close"></button>
					</div>
					<div class="modal-body">
						<form:form id="deleteForm" action="" class="row g-3" method="POST">
							<h5>
								Bạn chắc chắn muốn xóa nhân viên có mã là <span id="maNVSpan"></span>?
							</h5>
							<div class="col-12">
								<button type="submit" class="btn btn-danger float-end">Xác
									nhận</button>
							</div>

						</form:form>
					</div>

				</div>
			</div>
		</div>
		<!-- End Modal xác nhận xóa-->

		<!-- Modal thêm tài khoản -->
		<div class="modal fade" id="addNewAccount" tabindex="-1"
			aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog ">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="exampleModalLabel">Tạo tài khoản
							nhân viên</h5>
						<button type="button" class="btn-close" data-bs-dismiss="modal"
							aria-label="Close"></button>
					</div>
					<div class="modal-body">
						<form:form id="addAccountForm" action="" class="row g-3"
							modelAttribute="taikhoan" method="POST">
							<div class="col-md-12">
								<label for="tenTaiKhoan" class="form-label">Tên tài
									khoản</label> <input name="tenTaiKhoan" type="text"
									class="form-control" />
							</div>
							<div class="col-md-12">
								<label for="matKhau" class="form-label">Mật khẩu</label> <input
									name="matKhau" type="text" class="form-control" />
							</div>

							<div class="col-12">
								<button type="submit" class="btn btn-primary float-end">Thêm</button>
							</div>
						</form:form>
					</div>

				</div>
			</div>
		</div>
		<!-- End Modal thêm tài khoản -->

		<!-- Modal sửa thông tin nhân viên -->
		<div class="modal fade" id="updateStaff" tabindex="-1"
			aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog ">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title" id="exampleModalLabel">Sửa thông tin
							nhân viên</h4>
						<button type="button" class="btn-close" data-bs-dismiss="modal"
							aria-label="Close"></button>
					</div>
					<div class="modal-body">
						<form:form id="updateForm" action="" class="row g-3"
							modelAttribute="nhanvien" method="POST">
							<div class="col-md-12">
								<label for="manv" class="form-label">Mã nhân viên</label> <input
									name="maNV" class="form-control" id="manv"/>
							</div>
							<div class="col-md-12">
								<label for="tennv" class="form-label">Họ và tên</label> <input
									name="tenNV" class="form-control" id="tennv" />
							</div>
							<div class="col-12">
								<label for="cmnd" class="form-label">CMND</label> <input
									name="CMND" class="form-control" id="cmnd" />
							</div>
							<div class="col-12">
								<label for="email" class="form-label">Email</label> <input
									name="email" class="form-control" id="email"/>
							</div>
							<div class="col-md-12">
								<label for="sdt" class="form-label">Số điện thoại</label> <input
									name="sdt" class="form-control" id="sdt" />
							</div>
							<div class="col-md-12">
								<label for="sdt" class="form-label">Tình trạng làm việc</label>
								<div class="form-check col-md-4">
									<input class="form-check-input" type="radio" name="dangLamViec"
										id="danglamviec" value=1 > <label
										class="form-check-label" for="flexRadioDefault1"> Đang
										làm việc </label>
								</div>
								<div class="form-check col-md-4">
									<input class="form-check-input " type="radio"
										name="dangLamViec" id="danghiviec" value=0> <label
										class="form-check-label" for="flexRadioDefault2"> Đã
										nghỉ việc </label>
								</div>
							</div>

							<div class="col-12">
								<button type="submit" class="btn btn-primary float-end">Xác
									nhận</button>
							</div>

						</form:form>
					</div>

				</div>
			</div>
		</div>
		<!-- End Modal sửa thông tin nhân viên -->

		<!-- Hiển thị thông báo lỗi -->
		<c:if test="${not empty errors.allErrors}">
			<c:forEach items="${errors.allErrors}" var="error">
				<div style="color: red;">
					<c:out value="${error.defaultMessage}" />
				</div>
			</c:forEach>
		</c:if>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
	<script>
    document.querySelectorAll('.btn-delete').forEach(button => {
        button.addEventListener('click', function () {
            var maNV = this.getAttribute('data-bs-whatever');
            document.getElementById('deleteForm').action = "/QuanLyThietBi/quan-tri/quan-ly-nhanvien/xoa-nhanvien/" + maNV;
            document.getElementById('maNVSpan').innerText = maNV;
        });
    });
    document.querySelectorAll('.btn-update').forEach(button => {
        button.addEventListener('click', function () {
            
            var modelData = this.getAttribute('data-bs-whatever');
        	var values = modelData.split(",");
        	
        	var maNV = values[0];
            var tenNV = values[1];
            var CMND = values[2];
            var email = values[3];
            var sdt = values[4];
            var dangLamViec = values[5];

            console.log(values)
            document.getElementById('updateForm').action = "/QuanLyThietBi/quan-tri/quan-ly-nhanvien/sua-tt-nhanvien/" + maNV;
            document.getElementById('maNVSpan').innerText = maNV;
            
            document.getElementById('manv').value = maNV;
            document.getElementById('tennv').value = tenNV;
            document.getElementById('cmnd').value = CMND;
            document.getElementById('email').value = email;
            document.getElementById('sdt').value = sdt;
            
            if (dangLamViec == 1) {
            	document.getElementById('danglamviec').checked = true;
            } else if (dangLamViec == 0) {
            	document.getElementById('danghiviec').checked = true;
            }
        });
    });
    
    document.querySelectorAll('.btn-addAccount').forEach(button => {
        button.addEventListener('click', function () {
        	var maNV = this.getAttribute('data-bs-whatever');
            
            document.getElementById('addAccountForm').action = "/QuanLyThietBi/quan-tri/quan-ly-nhanvien/tao-taikhoan/" + maNV;
            document.getElementById('maNVSpan').innerText = maNV;
            
            
        });
    });
    
    document.querySelectorAll('.btn-show').forEach(button => {
        button.addEventListener('click', function () {
            var modelData = this.getAttribute('data-bs-whatever');
            
            var values = modelData.split(",");
            console.log(values)
            
            var maNV = values[0];
            var tenNV = values[1];
            var CMND = values[2];
            var email = values[3];
            var sdt = values[4];
            
            document.getElementById('showInforForm').action = "/QuanLyThietBi/quan-tri/quan-ly-nhanvien/xem-nhanvien/" + maNV;

            document.getElementById('maNVInput').value = maNV;
            document.getElementById('tenNVInput').value = tenNV;
            document.getElementById('CMNDInput').value = CMND;
            document.getElementById('emailInput').value = email;
            document.getElementById('sdtInput').value = sdt;
        });
    });
</script>

</body>
</html>