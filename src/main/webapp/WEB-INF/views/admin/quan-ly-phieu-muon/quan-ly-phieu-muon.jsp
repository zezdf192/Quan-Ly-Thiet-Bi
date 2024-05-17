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
		<h2 class="title" style="color: #198754; font-size: 25px">Quản lý
			phiếu mượn</h2>
		<div class="header-exam">
			<form:form  method="GET" action="/QuanLyThietBi/quan-tri/quan-ly-phieu-muon/lap-phieu-muon">
				<button type="submit" class="btn btn-primary" >Lập phiếu mượn</button>
			</form:form>
			<!-- <button type="button" class="btn btn-primary" data-bs-toggle="modal"
				data-bs-target="#addNew">Lập phiếu mượn</button> -->
			<div>
				<form:form class="d-flex" style="gap:20px; height:40px" method="GET"
					action="/QuanLyThietBi/quan-tri/quan-ly-phieu-muon/tim-kiem-phieu-muon">
					<input name="inputText" class="form-control"
						placeholder="Tìm kiếm theo mã phòng..." />
					<select class="form-select " style="width: 250px"
						name="selectOption" id="cars">
						<option value="10">Tất cả phiếu mượn</option>
						<option value="0">Phiếu mượn chưa trả</option>
						<option value="1">Phiếu mượn đã trả</option>
						<option value="2">Phiếu mượn quá hạn</option>
						
						
					</select>


					<button type="submit" class="btn btn-primary" style="width: 165px">Tìm
						kiếm</button>
				</form:form>

			</div>
		</div>
		<div class="table-container mt-3" style="font-size: 13px">
			<div class="table-head">
				<table>
					<thead>
						<tr class="table-header">
							<th style="width: 15%">Mã Phòng</th>
							<th style="width: 15%">Thời điểm mượn</th>
							<th style="width: 15%">Han trả</th>
							<th style="width: 15%">Mã SV mượn</th>
							<th style="width: 15%">Mã NV xuất</th>
							<th>Thao tác</th>
						</tr>
					</thead>
				</table>
			</div>
			<div class="table-body">
				<table>
					<tbody>
						<c:forEach var="item" items="${ phieumuons }">
							<tr>
								<td style="width: 15%">${ item.maPhong }</td>
								<td style="width: 15%">${item.thoiDiemMuon}</td>
								<td style="width: 15%">${item.hanTra}</td>
								<td style="width: 15%">${item.maSVMuon}</td>
								<td style="width: 15%">${item.maNVLap}</td>
								<td class="action"
									style="display: flex; gap: 5px; justify-content: center">
									<button type="button" class="btn btn-success btn-show"
										data-bs-toggle="modal" data-bs-target="#inforForm"
										data-bs-whatever="${item.maPhieuMuon} , ${item.maPhong} , ${item.maSVMuon} , ${item.maNVLap} , ${item.thoiDiemMuon} ,${item.hanTra}">Xem</button>
									<%-- <button type="button" class="btn btn-warning btn-update"
										data-bs-toggle="modal" data-bs-target="#update"
										data-bs-whatever="${item.maPhieuMuon}">Sửa</button>
									<button type="button" class="btn btn-danger btn-delete"
										data-bs-toggle="modal" data-bs-target="#confirmDelete"
										data-bs-whatever="${item.maPhieuMuon}">Xóa</button> --%>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>

		

		<!-- Modal xem thông tin-->
		<div class="modal fade" id="inforForm" tabindex="-1"
			aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog ">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="exampleModalLabel">THÔNG TIN PHIẾU MƯỢN</h5>
						<button type="button" class="btn-close" data-bs-dismiss="modal"
							aria-label="Close"></button>
					</div>
					<div class="modal-body">
						<form:form id="showInforForm" action="" class="row g-3" method="GET"
							modelAttribute="detailPM">
							<div class="col-md-12">
								<label for="maPhieuMuon" class="form-label">Mã phiếu mượn</label> <input
									name="maPhieuMuon" type="text" class="form-control" id="maPMInput"
									disabled="true" />
							</div>
							<div class="col-md-12">
								<label for="maPhong" class="form-label">Phòng mượn</label> <input
									name="maPhong" type="text" class="form-control" id="phongInput"
									disabled="true" />
							</div>
							<div class="col-12">
								<label for="maSVMuon" class="form-label">Sinh viên mượn</label> <input
									name="maSVMuon" type="text" class="form-control" id="svInput"
									disabled="true" />
							</div>
							<div class="col-12">
								<label for="maNVLap" class="form-label">Nhân viên lập</label> <input
									name="maNVLap" type="text" class="form-control" id="nvInput"
									disabled="true" />
							</div>
							<div class="col-md-12">
								<label for="thoiDiemMuon" class="form-label">Thời gian mượn</label> <input
									name="thoiDiemMuon" type="text" class="form-control" id="tgmuonInput"
									disabled="true" />
							</div>
							
							<div class="col-md-12">
								<label for="hanTra" class="form-label">Thời gian trả</label> <input
									name="hanTra" type="text" class="form-control" id="tgtraInput"
									disabled="true" />
							</div>
							<div class="col-md-12">
								<button type="submit" class="btn btn-primary float-end">Xem thông tin chi tiết</button>
							</div>

						</form:form>
					</div>

				</div>
			</div>
		</div>
		<!-- End Modal xem thông tin-->

		<!-- Modal xác nhận xóa-->
		<div class="modal fade" id="confirmDelete" tabindex="-1"
			aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog ">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title" id="exampleModalLabel">Xác nhận xóa phiếu mượn</h4>
						<button type="button" class="btn-close" data-bs-dismiss="modal"
							aria-label="Close"></button>
					</div>
					<div class="modal-body">
						<form:form id="deleteForm" action="" class="row g-3" method="POST">
							<h5>
								Bạn chắc chắn muốn xóa phiếu mượn có mã : <span id="maSpan"></span>
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

		<!-- Modal sửa thông tin-->
		<div class="modal fade" id="update" tabindex="-1"
			aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog ">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title" id="exampleModalLabel">Sửa thông tin phiếu mượn</h4>
						<button type="button" class="btn-close" data-bs-dismiss="modal"
							aria-label="Close"></button>
					</div>
					<div class="modal-body">
						<form:form id="updateForm" action="" class="row g-3"
							modelAttribute="" method="POST">
							

						</form:form>
					</div>

				</div>
			</div>
		</div>
		<!-- End Modal sửa thông tin -->

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
            var ma = this.getAttribute('data-bs-whatever');
            document.getElementById('deleteForm').action = "/QuanLyThietBi/quan-tri/quan-ly-phieu-muon/xoa-phieu-muon/" + ma;
            document.getElementById('maSpan').innerText = ma;
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
            
            var maPM = values[0];
            var phong = values[1];
            var svmuon = values[2];
            var nvlap = values[3];
            var tgmuon = values[4];
            
            var tgtra = values[5];
            
           // document.getElementById('showInforForm').action;
           document.getElementById('showInforForm').action = "/QuanLyThietBi/quan-tri/quan-ly-phieu-muon/chi-tiet-pm/" + maPM;

            document.getElementById('maPMInput').value = maPM;
            document.getElementById('phongInput').value = phong;
            document.getElementById('svInput').value = svmuon;
            document.getElementById('nvInput').value = nvlap;
            document.getElementById('tgmuonInput').value = tgmuon;
       
            document.getElementById('tgtraInput').value = tgtra;
        });
    });
</script>

</body>
</html>