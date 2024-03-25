<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="manage-body" style="min-height: 600px">
		<h2 class="title">Phòng: <span>${ maPhong }</span></h2>
		<c:if test="${not empty error}">
		    <div class="alert alert-danger">${error}</div>
		</c:if>
		
		<div class="header-exam">
		
			

			<button type="button" class="btn btn-secondary" data-toggle="modal" data-target="#createModalCenter">Thêm mới thiết bị</button>
			<div >
			<form:form class="d-flex" style="gap:20px; height:40px" method="GET" action="/QuanLyThietBi/quan-tri/quan-ly-phong/tim-kiem-phong">
				<input name="inputText" class="form-control" placeholder="Tìm kiếm theo tên thiết bị..."/>
				<select class="form-select " style="width: 250px"
				name="selectOption" id="cars">

					
					<option value="10">
						Tất cả trạng thái</option>
						<option value="1">
						Đang cho mượn</option>
						<option value="0">
						Sẵn sàng cho mượn</option><option value="-1">
						Thiết bị không cho mượn</option>
				
			</select>
					
					<button type="submit" class="btn btn-primary" style="width:174px">Tìm kiếm</button>
				</form:form>
				
			</div>
		</div>
		<div class="table-container mt-3">
			<div class="table-head">
				<table>
					<thead>
						<tr class="table-header">
							<th class="none-m px-5">Mã thiết bị</th>
							<th>Tên thiết bị</th>
							<th>Loại thiết bị</th>
							<th class="none-m">Trạng thái</th>
							
							<th>Các thao tác</th>
						</tr>
					</thead>
				</table>
			</div>
			<div class="table-body">
				<table>
					<tbody>
							<c:forEach var="item" items="${ thietbi }">
						
							<tr>
							<td class="none-m px-5">${ item.maTB }</td>
							<td>${item.tenTB }</td>
							<td>${item.loaiTB }</td>
							<td class="none-m">${item.tenTinhTrang }</td>
							
							<td class="action" style="display: flex; gap: 10px; justify-content: center;">
							<form:form method="GET" action="/QuanLyThietBi/quan-tri/quan-ly-phong/chi-tiet-thiet-bi/${ item.maTB }">
								<button type="submit" class="btn btn-success" data-toggle="modal" data-target="#exampleModalCenter">Xem</button>
							</form:form>
							
									<button type="button" class="btn btn-warning" data-toggle="modal" data-target="#editModalCenter">
				  Edit
				</button>
								
								<button text="${item.maPhong}" class=" btn btn-danger showAlertBtn">
									Xóa</button>
									

									</td>
						</tr>
						</c:forEach>
						
						
					</tbody>
				</table>
			</div>
	
	
					<!-- Modal Create -->
					
						<div class="modal fade" id="createModalCenter" tabindex="-1" role="dialog" aria-labelledby="createModalCenter" aria-hidden="true">
						 <form:form  method="POST" action="them-moi/${maPhong }" modelAttribute="createTBRoom">
						  <div class="modal-dialog modal-dialog-centered" role="document">
						    <div class="modal-content">
						      <div class="modal-header">
						        <h5 class="modal-title" id="exampleModalLongTitle">Thêm mới thiết bị trong phòng ${ maPhong }</h5>
						        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
						          <span aria-hidden="true">&times;</span>
						        </button>
						      </div>
						      <div class="modal-body">
						      
								  <div class="form-group">
								    <label for="exampleInputEmail1">Mã thiết bị</label>
								    <input type="text" name="maTB" required class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Nhập mã thiết bị" >
								   
								  </div>
								  <div class="form-group">
								    <label for="exampleInputPassword1">Tên thiết bị</label>
								    <input type="text" name="tenTB" required class="form-control" id="exampleInputPassword1" placeholder="Nhập tên thiết bị" >
								  </div>
								 <div class="form-group">
								    <label for="userInput">Chọn loại thiết bị:</label>
								    <select class="form-control" name="loaiTB" id="inputSelector">
									    <c:forEach var="item" items="${ loaiTB }">
									    	<option value="${item.maLoai}">${item.tenLoai}</option>
									    </c:forEach>
								        
								    
								        <option value="other">Khác...</option>
								    </select>
								    <input type="text" name="loaiTB" class="form-control mt-2" id="customInput" placeholder="Nhập giá trị..." style="display: none;">
								</div>
								
								 <div class="form-group">
								    <label for="userInput">Chọn tình trạng thiết bị:</label>
								    <select name="tinhTrangTB" class="form-control" id="typeTBSelector">
								        <option value="0">Sẵn sàng cho mượn</option>
								        <option value="8">Thiết bị cố định của phòng</option>
								       
								    </select>
								    <input type="text" class="form-control mt-2" id="typeTB" placeholder="Nhập giá trị..." style="display: none;">
								</div>
																 
								
						      </div>
						      <div class="modal-footer">
						        <button type="button" class="btn btn-secondary" data-dismiss="modal">Đóng</button>
						        <button type="submit" class="btn btn-primary">Tạo mới</button>
						      </div>
						    </div>
						  </div>
						</form:form>
						</div>
						
			
				
				
		</div>
	</div>
	<!-- JavaScript to handle the alert without jQuery -->
<script>
document.addEventListener("DOMContentLoaded", function() {
    var showAlertBtns = document.querySelectorAll(".showAlertBtn");
  
	
    showAlertBtns.forEach(function(btn) {
        btn.addEventListener("click", function() {
            // Show the confirm dialog
            var result = confirm("Vẫn tiếp tục hành động xóa?");
            
            // Check the result of the confirm dialog
            if (result) {
                // User clicked "OK"
                window.location.href = '/QuanLyThietBi/quan-tri/quan-ly-phong/xoa-phong/' + btn.getAttribute("text");
            } else {
                // User clicked "Cancel" or closed the dialog
            }
        });
    });


});

//Model 
document.getElementById('inputSelector').addEventListener('change', function() {
    var value = this.value;
    var customInput = document.getElementById('customInput');
    if(value === 'other') {
        // Hiển thị input nếu người dùng chọn "Khác..."
        customInput.style.display = '';
    } else {
        // Ẩn input nếu người dùng không chọn "Khác..."
        customInput.style.display = 'none';
    }
});

	
</script>

<script>
$(document).ready(function() {
    // Xử lý sự kiện gửi form
    $('#borrowForm').submit(function(event) {
        // Lấy thời gian đã chọn từ trường input
        var selectedDateTime = new Date($('#dateTime').val());
        var now = new Date(); // Lấy thời gian hiện tại

        // Kiểm tra xem thời gian đã chọn có phải là tương lai không
        if (selectedDateTime <= now) {
            // Nếu thời gian đã chọn là quá khứ, hiển thị thông báo và ngăn chặn gửi form
            alert("Thời gian phải là tương lai.");
            event.preventDefault(); // Ngăn chặn gửi form
        }
    });
});
</script>

</body>
</html>