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
		<div class="header-exam">
			<a class="add-exam" href="/QuanLyThietBi/quan-tri/quan-ly-phong/tao-phong">Thêm mới phòng học</a>
			<div >
			<form:form class="d-flex" style="gap:20px; height:40px" method="GET" action="/QuanLyThietBi/quan-tri/quan-ly-phong/tim-kiem-phong">
				<input name="inputText" class="form-control" placeholder="Tìm kiếm theo tên phòng..."/>
				<select class="form-select " style="width: 250px"
				name="selectOption" id="cars">

					
					<option value="10">
						Tất cả trạng thái</option>
						<option value="1">
						Đang cho mượn</option>
						<option value="0">
						Sẵn sàng cho mượn</option>
				
			</select>
				
					
					<button type="submit" class="btn btn-primary" style="width:165px">Tìm kiếm</button>
				</form:form>
				
			</div>
		</div>
		<div class="table-container mt-3">
			<div class="table-head">
				<table>
					<thead>
						<tr class="table-header">
							<th class="none-m px-5">STT</th>
							<th>Tên phòng
										</th>
							<th class="none-m">Trạng thái</th>
							
							<th>Các thao tác</th>
						</tr>
					</thead>
				</table>
			</div>
			<div class="table-body">
				<table>
					<tbody>
						<c:forEach var="item" items="${ phongs }">
						
							<tr>
							<td class="none-m px-5">1</td>
							<td>${ item.maPhong }</td>
							<td class="none-m">${item.tenTinhTrang }</td>
							
							<td class="action" style="display: flex; gap: 10px; justify-content: center;"><button
									class="btn btn-success">Xem
									</button>
									<form:form method="GET" action="/QuanLyThietBi/quan-tri/quan-ly-phong/sua-phong/${ item.maPhong }"><button type="submit" class=" btn btn-warning">
									Sửa</button></form:form>
								
								<button text="${item.maPhong}" class=" btn btn-danger showAlertBtn">
									Xóa</button>
									

									</td>
						</tr>
						</c:forEach>
						
						
					</tbody>
				</table>
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

	
</script>

</body>
</html>