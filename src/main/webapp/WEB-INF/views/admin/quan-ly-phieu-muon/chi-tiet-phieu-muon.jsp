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
	<div class="container" style="min-height: 600px">
	<form class="row" id="borrowForm" action="/QuanLyThietBi/quan-tri/quan-ly-phieu-muon/xac-nhan-tra" method="POST">
		
			<h2>Chi tiết thiết bị mượn</h2>
			<%-- <c:if test="${not empty error}">
			    <div class="alert alert-danger">${error}</div>
			</c:if> --%>
			
			<div class="col-12">
                
                	<div class="row">
                		<div class="form-group col-md-6">
                        <label for="studentID">Mã sinh viên mượn:</label>
	                        <input type="text" required class="form-control" id="studentID" name="studentID" value="${pm.maSVMuon }" readonly>
	                    </div>
	                    <div class="form-group col-md-6">
	                        <label for="dateTime">Thời gian trả:</label>
	                        <!-- <input type="datetime-local" required class="form-control" id="dateTime" name="dateTime" readonly> -->
	                        <input name="thoiDiemTra" type="text" class="form-control" id="tgtraInput" readonly value=${ pm.thoiDiemTra }/>
	                    </div>
	                     <input type="text" hidden="hidden" name="maPM" value="${ pm.maPhieuMuon }">
	                     <input type="text" hidden="hidden" name="maPhong" value="${ pm.maPhong }">
	                    
                	</div>
                    
                
			</div>
			
			
			
			<div class="col-md-6 mt-2">
				<h3>Thiết bị đã mượn</h3>
				<div class="list-container">
					<ul class="list-group list-item" id="elementList">
					<c:forEach var="item" items="${ thietbi }">
						<li class="list-group-item">${ item.maTB } - ${item.tenLoaiTB }</li>	
					</c:forEach>
					</ul>
				</div>
			</div>
			<div class="col-md-6 mt-2">
				<h2>Thiết bị đã trả</h2>
				<div class="table-tb-wrapper">
					<table class="table-tb">
						<thead>
							<tr>
								<th>Tên</th>
							</tr>
						</thead>
						<tbody id="selectedElement">
							<c:forEach var="item" items="${ thietbidatra }">
								<%-- <li class="list-group-item">${ item.maTB } - ${item.tenLoaiTB }</li>	 --%>
								<tr data-name="' + elementName + '"><td contenteditable="false">${ item.maTB } - ${item.tenLoaiTB }</td></tr>
							</c:forEach>
							
							<!-- Dữ liệu về phần tử được chọn sẽ được thêm vào đây -->
						</tbody>
					</table>
				</div>
			</div>
			<div class="form-group col-12 mt-4 d-flex justify-content-end">
					<c:if test="${soluongtbconlai >= 1}">
				        <button type="submit" class="btn btn-primary" >Xác nhận</button>
				    </c:if>
				    <c:choose>
				        <c:when test="${soluongtbconlai < 1}">
				             <a href="/QuanLyThietBi/quan-tri/quan-ly-phieu-muon">Quay lại</a>
				        </c:when>
				        <c:otherwise>
				             <button type="submit" class="btn btn-primary" >Xác nhận</button>
				        </c:otherwise>
				    </c:choose>
					<%-- <c:if test="${not empty error}">
					    <a href="/QuanLyThietBi/quan-tri/quan-ly-phieu-muon">Quay lại</a>
					</c:if>
	               <button type="submit" class="btn btn-primary" >Xác nhận</button> --%>
	         </div>
		</form>
	</div>
	
	<script>
	$(document).ready(function() {
	    // Mảng để lưu trữ tên các phần tử đã được chọn
	    var selectedElements = [];

	    // Xử lý sự kiện khi phần tử trong danh sách được click
	    $('#elementList li').click(function() {
	        // Lấy tên của phần tử được click
	        var elementName = $(this).text();

	        // Kiểm tra xem phần tử đã được chọn trước đó hay chưa
	        if (!selectedElements.includes(elementName)) {
	            // Thêm tên phần tử vào mảng các phần tử đã được chọn
	            selectedElements.push(elementName);

	            // Tạo một hàng mới trong bảng và thêm vào
	            var newRow = '<tr data-name="' + elementName + '"><td>' + elementName + '</td></tr>';
	            $('#selectedElement').append(newRow);
	        }
	    });

	    // Xử lý sự kiện khi phần tử trong bảng được click
	    $(document).on('click', '#selectedElement tr', function() {
	        // Lấy tên của phần tử được click
	        var elementName = $(this).data('name');

	        // Xóa phần tử khỏi mảng các phần tử đã được chọn
	        selectedElements = selectedElements.filter(function(item) {
	            return item !== elementName;
	        });

	        // Xóa hàng tương ứng khỏi bảng
	        $(this).remove();
	    });

        // Xử lý sự kiện gửi form
	    $('#borrowForm').submit(function(event) {
	    	
	        // Kiểm tra xem có phần tử nào đã được chọn hay không
	        if (selectedElements.length === 0) {
	            event.preventDefault(); // Ngăn form gửi đi nếu không có phần tử nào được chọn
	            alert("Bạn cần phải chọn ít nhất một thiết bị để mượn!");
	        } else {
	            
	            
	            	
	                var selectedElementNames = selectedElements.join(', ');
	                $('<input>').attr({
	                    type: 'hidden',
	                    name: 'selectedElements',
	                    value: selectedElementNames
	                }).appendTo('#borrowForm');
	              
	                return true; // Cho phép tiếp tục gửi form
	            
	        }
	      
	    });
	});
	
	</script>
	
	

</body>
</html>