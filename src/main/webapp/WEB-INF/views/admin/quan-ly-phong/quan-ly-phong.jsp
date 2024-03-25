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
		<h2 class="title">Quản lý phòng học</h2>
		<c:if test="${not empty error}">
		    <div class="alert alert-danger">${error}</div>
		</c:if>
		<div class="header-exam">
		
		
		<c:if test="${not empty errorMessageRoom }">
				<div class="position-fixed bottom-0 end-0 p-3" style="z-index: 11">
					<div id="liveToast1" class="toast hide" role="alert"
						aria-live="assertive" aria-atomic="true">
						<div class="toast-header bg-warning">
							<i class="bi bi-exclamation-octagon"></i> <strong class="me-auto">THẤT
								BẠI</strong>
							<button type="button" class="btn-close" data-bs-dismiss="toast"
								aria-label="Close"></button>
						</div>
						<div class="toast-body">${errorMessageRoom}</div>
					</div>
				</div>
			</c:if>

			<c:if test="${not empty messageRoom }">
				<div class="position-fixed bottom-0 end-0 p-3" style="z-index: 11">
					<div id="liveToast2" class="toast hide" role="alert"
						aria-live="assertive" aria-atomic="true">
						<div class="toast-header bg-success">
							<i class="bi bi-exclamation-octagon"></i> <strong class="me-auto">THÀNH
								CÔNG</strong>
							<button type="button" class="btn-close" data-bs-dismiss="toast"
								aria-label="Close"></button>
						</div>
						<div class="toast-body">${messageRoom}</div>
					</div>
				</div>
			</c:if>
			
			<button type="button" class="btn btn-primary" data-bs-toggle="modal"
				data-bs-target="#addNewStaff">Thêm mới phòng </button>
		
			<div>
				<form:form class="d-flex" style="gap:20px; height:40px" method="GET"
					action="/QuanLyThietBi/quan-tri/quan-ly-phong/tim-kiem-phong">
					<input name="inputText" class="form-control"
						placeholder="Tìm kiếm theo tên phòng..." />
					<select class="form-select " style="width: 250px"
						name="selectOption" id="cars">


						<option value="10">Tất cả trạng thái</option>
						<option value="1">Đang cho mượn</option>
						<option value="0">Sẵn sàng cho mượn</option>
						<option value="2">Bảo trì</option>
						
						
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
							<th class="none-m px-5">STT</th>
							<th>Tên phòng</th>
							<th class="none-m">Trạng thái</th>

							<th>Các thao tác</th>
						</tr>
					</thead>
				</table>
			</div>
			<div class="table-body">
				<table>
					<tbody>
						<c:forEach var="item" items="${phongs}" varStatus="loop">
						    <tr>
						        <td class="none-m px-5">${loop.index + 1}</td>
						        <td>${item.maPhong}</td>
						        <td class="none-m">${item.tenTinhTrang}</td>
						
						        <td class="action" style="display: flex; gap: 10px; justify-content: center;">
						            <form:form method="GET" action="/QuanLyThietBi/quan-tri/quan-ly-phong/chi-tiet-phong/${item.maPhong}">
						                <button type="submit" class="btn btn-success">Xem</button>
						            </form:form>
						            
						            <button type="button" class="btn btn-warning btn-update"
										data-bs-toggle="modal" data-bs-target="#updateRoom"
										data-bs-whatever="${item.maPhong},${item.tenTinhTrang}">Sửa
									</button>
						
						            <button type="button" class="btn btn-danger btn-delete"
										data-bs-toggle="modal" data-bs-target="#confirmDeleteModal"
										data-bs-whatever="${item.maPhong}">Xóa</button>
						           
						        </td>
						    </tr>
						</c:forEach>


					</tbody>
				</table>
			</div>

		</div>
		
		
	<!-- Modal thêm mới phong-->
		
		<div class="modal fade" id="addNewStaff" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
		    <div class="modal-dialog modal-dialog-centered" role="document">
		        <div class="modal-content">
		            <div class="modal-header">
		                <h5 class="modal-title" id="exampleModalLabel">Thêm mới phòng</h5>
		                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
		            </div>
		            <div class="modal-body">
		                <form:form method="POST" action="/QuanLyThietBi/quan-tri/quan-ly-phong/tao-phongs" modelAttribute="createRoomForm" class="row g-3">
		                    <div class="col-md-12">
		                        <label for="manv" class="form-label">Nhập mã phòng học</label>
		                        <input name="maPhong" type="text" class="form-control" required />
		                    </div>
		
		                    <div class="col-12">
		                        <button type="submit" class="btn btn-primary float-end">Thêm</button>
		                    </div>
		                </form:form>
		            </div>
		        </div>
		    </div>
		</div>

		<!-- End Modal thêm mới phong--> 
		
		<!-- Modal thay doi phong-->
		<div class="modal fade" id="updateRoom" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
		    <div class="modal-dialog modal-dialog-centered" role="document">
		        <div class="modal-content">
		            <div class="modal-header">
		                <h5 class="modal-title" id="exampleModalLabel">Thay đổi trạng thái phòng</h5>
		                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
		            </div>
		            <div class="modal-body">
		                <form:form id="updateForm" method="POST" action="" modelAttribute="updateRoomForm" class="row g-3">
		                    <div class="col-md-12">
		                        <label for="manv" class="form-label">Mã phòng học</label>
		                        <input name="maPhong" id="maPhongModal" type="text" class="form-control" required disabled="true" />
		                    </div>
		                    
		                  <select name="selectOption" class="form-select col-md-12" id="selectRoom">
							    <c:forEach var="item" items="${trangThai}">
							        <option value="${item.maTinhTrang}">${item.tenTinhTrang}</option>
							    </c:forEach>
							</select>

		
		                    <div class="col-12">
		                        <button type="submit" class="btn btn-primary float-end">Thay đổi</button>
		                    </div>
		                </form:form>
		            </div>
		        </div>
		    </div>
		</div>
		<!-- End Modal tthay doi phong--> 
	</div>
	
	<!-- Modal xác nhận xóa-->
		<div class="modal fade" id="confirmDeleteModal" tabindex="-1"
			aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog ">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title" id="exampleModalLabel">Xác nhận xóa
							phòng</h4>
						<button type="button" class="btn-close" data-bs-dismiss="modal"
							aria-label="Close"></button>
					</div>
					<div class="modal-body">
						<form:form id="deleteForm" action="" class="row g-3" method="POST">
							<h5>
								Bạn chắc chắn muốn xóa mã phòng có mã là <span id="maPhongSpan"></span>?
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

<script>

		document.querySelectorAll('.btn-delete').forEach(button => {
		    button.addEventListener('click', function () {
		    	var maPhong = this.getAttribute('data-bs-whatever');
		    	
		    	
		        document.getElementById('deleteForm').action = "/QuanLyThietBi/quan-tri/quan-ly-phong/xoa-phong/" + maPhong ;
		       
		        document.getElementById('maPhongSpan').innerText = maPhong;
		    });
		});



	document.querySelectorAll('.btn-update').forEach(button => {
	    button.addEventListener('click', function () {
	        
	        var modelData = this.getAttribute('data-bs-whatever');
	    	var values = modelData.split(",");
	    	
	    	var maPhong = values[0];
	    	var tenTrangThai = values[1];
	    	

	     
	    	// alert(tenTrangThai);
	        
	        var updateForm = document.getElementById('updateForm'); 
	        
	        updateForm.action = "/QuanLyThietBi/quan-tri/quan-ly-phong/sua-phong/" + maPhong;

	       
	        
	      
	        document.getElementById('maPhongModal').value = maPhong;    
	       // document.getElementById('selectRoom').value = tenTrangThai;

	        var selectElement = document.getElementById("selectRoom");
	      

	        // Lặp qua tất cả các option trong select
	        for (var i = 0; i < selectElement.options.length; i++) {
	            var option = selectElement.options[i];
	            if (option.textContent === tenTrangThai) { // Nếu giá trị của option trùng với giá trị mặc định
	            	document.getElementById('selectRoom').value = option.value; // Đặt selectedIndex của select là index của option đó
	                break; // Kết thúc vòng lặp
	            }
	        }
	    
	     
	    });
	});


    // Kiểm tra nếu có thông báo lỗi trong session thì hiển thị nó
   
   	
   		
   	
</script>

</body>
</html>