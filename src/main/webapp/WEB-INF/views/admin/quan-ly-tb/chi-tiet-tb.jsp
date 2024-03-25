<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Chỉnh Sửa Thông Tin Thiết Bị</title>
<style>
.custom-card {
    font-size: 1.2rem;
    padding: 20px;
}

.custom-card .card {
    width: 100%;
    height: auto;
}

.custom-card img.card-img-top {
    height: 250px;
    object-fit: cover;
    width: 100%;
}



</style>
</head>
<body>

<div class="container mt-5">
    <div class="row">
    	<c:if test="${not empty error}">
		    <div class="alert alert-danger">${error}</div>
		</c:if>
        <div class="col-md-6">
            <div class="card custom-card">
                <h2 class="title p-4">Thông tin của thiết bị ${tenTB}</h2>
            	<form:form action="sua-thiet-bi/${maThietBi }" method="POST"  modelAttribute="updateTB">
            		 <div class="card-body">
                  
                     <p class="card-text"><strong>Mã Phòng:</strong> <span  id="viewPhong">${maPhong}</span></p>
                    <p class="card-text"><strong>Mã Thiết Bị:</strong> <span id="viewMaTB">${maThietBi}</span></p>
                    <p class="card-text"><strong>Loại Thiết Bị:</strong> <span id="viewLoaiTB">${tenLoaiTB}</span></p>
                    <p class="card-text"><strong>Trạng thái thiết bị:</strong> <span id="viewTrangThai">${tenTrangThai}</span></p>
                    <p class="card-text"><strong>Mô Tả:</strong> <span id="viewMoTa">Mô tả chi tiết về thiết bị ở đây...</span></p>
                   
                    
                  <input id="maPhongInput" name="maPhong" hidden="hidden" type="text" value="${maPhong }"/>
				<input id="trinhTrangTBInput" name="tinhTrangTB" hidden="hidden" type="text" value="${ optionActive }"/>
				<input id="loaiTBInput" name="loaiTB" hidden="hidden" type="text" value="${loaiTB }"/>

                   
                    
                   <button type="submit" class="btn btn-primary">Chỉnh Sửa</button>
                    <a href="#" id="deleteButton" data-id="${ maThietBi }" class="btn btn-danger">Xóa</a>

                </div>
            	</form:form>
               
            </div>
        </div>
        
         <div class="col-md-6"> <!-- Sử dụng toàn bộ chiều rộng -->
            <div class="card custom-card">
              <h2 class="p-4">Chỉnh sửa thông tin thiết bị</h2>
			                <div class="card-body">
			    <form id="editThietBiForm" action="/update-data" method="POST"> <!-- Thay "your-action-url" bằng URL xử lý form của bạn -->
			       
			         <div class="form-group">
			            <label for="phong">Mã phòng:</label>
			             <select class="form-select" 
						 id="phong">
		
							<c:forEach var="item" items="${phongs}">
								<option value="${item.maPhong}"
									${item.maPhong == maPhong ? 'selected' : ''}>
									${item.maPhong}</option>
							</c:forEach>
			
			
						</select>
					   
			        </div>
			        
			        <div class="form-group">
			            <label for="loaiThietBi">Loại Thiết Bị:</label>
			             <select class="form-select" 
						 id="loaiThietBi">
		
							<c:forEach var="item" items="${loaiTB}">
								<option value="${item.maLoai}"
									${item.maLoai == tenLoaiTB ? 'selected' : ''}>
									${item.maLoai}</option>
							</c:forEach>
			
			
						</select>
					   
			        </div>
			        <div class="form-group">
			            <label for="trangThai">Trạng Thái:</label>
			             <select class="form-select" 
						 id="trangThai">
		
							<c:forEach var="item" items="${trangThai}">
								<option value="${item.maTinhTrang}"
									${item.maTinhTrang == optionActive ? 'selected' : ''}>
									${item.tenTinhTrang}</option>
							</c:forEach>
			
			
						</select>
			        </div>
			        <div class="form-group">
			            <label for="moTa">Mô Tả:</label>
			            <textarea class="form-control" id="moTa" name="moTa" rows="3">Mô tả chi tiết về thiết bị ở đây...</textarea>
			        </div>
			        <button type="submit" class="btn btn-primary">Lưu Thay Đổi</button>
			        <button type="button" id="resetFormButton" class="btn btn-danger">Xóa thay đổi</button>

			    </form>
			</div>
    </div>
</div>
</div>
</div>

<script>
document.addEventListener("DOMContentLoaded", function() {
    var form = document.getElementById("editThietBiForm");
    form.addEventListener("submit", function(event) {
        event.preventDefault(); // Ngăn chặn form gửi theo cách thông thường

        // Lấy giá trị từ form chỉnh sửa
      
        // Giả sử id cho loại thiết bị là "loaiThietBi" và trạng thái thiết bị là "trangThai"
      var phongSelect = document.getElementById("phong");
        var phong = phongSelect.options[phongSelect.selectedIndex].text 
        
        var loaiThietBiSelect = document.getElementById("loaiThietBi");
        var loaiThietBi = loaiThietBiSelect.options[loaiThietBiSelect.selectedIndex].text;
        
        var trangThaiSelect = document.getElementById("trangThai");
        
        let trangThai = trangThaiSelect.options[trangThaiSelect.selectedIndex].text;
        let inttrangThai = trangThaiSelect.options[trangThaiSelect.selectedIndex].value;

        // Cập nhật form hiển thị
       document.getElementById("viewPhong").textContent = phong; 
        
        document.getElementById("viewLoaiTB").textContent = loaiThietBi;
        document.getElementById("viewTrangThai").textContent = trangThai;
        
        document.getElementById("maPhongInput").value = phong;
        document.getElementById("trinhTrangTBInput").value = inttrangThai;
        document.getElementById("loaiTBInput").value = loaiThietBi;

      
       
    });
    
    // Xử lý nút "Xóa thay đổi" để reset form chỉnh sửa
    document.getElementById("resetFormButton").addEventListener("click", function() {
        form.reset();
    });
});

document.addEventListener("DOMContentLoaded", function() {
    document.getElementById("deleteButton").addEventListener("click", function(event) {
        event.preventDefault(); // Ngăn chặn hành động mặc định của thẻ <a>

        var deviceId = this.getAttribute("data-id"); // Lấy ID từ thuộc tính data-id

        // Hiển thị cảnh báo
        var confirmDeletion = confirm("Bạn có chắc chắn muốn xóa thiết bị này không?");

        if (confirmDeletion) {
            // Nếu người dùng nhấn "OK", chuyển hướng đến URL động
            window.location.href = '/QuanLyThietBi/quan-tri/quan-ly-thiet-bi/xoa-thiet-bi/' + deviceId;
        } else {
            // Nếu người dùng nhấn "Cancel" hoặc đóng hộp thoại
        }
    });
});

</script>



</body>
</html>
