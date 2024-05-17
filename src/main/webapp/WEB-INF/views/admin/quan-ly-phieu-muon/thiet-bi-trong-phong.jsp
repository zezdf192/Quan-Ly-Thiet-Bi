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
	<form class="row" id="borrowForm" action="/QuanLyThietBi/quan-tri/quan-ly-phieu-muon/xac-nhan-lap" method="POST">
		
			<h2>Lập phiếu mượn</h2>
			<c:if test="${not empty error}">
			    <div class="alert alert-danger">${error}</div>
			</c:if>
			<div class="col-12">
                
                	<div class="row">
                		<div class="form-group col-md-6">
                        <label for="studentID">Nhập mã sinh viên:</label>
	                        <input type="text" required class="form-control" id="studentID" name="studentID">
	                    </div>
	                    <div class="form-group col-md-6">
	                        <label for="dateTime">Chọn thời gian trả:</label>
	                        <input type="datetime-local" required class="form-control" id="dateTime" name="dateTime">
	                        <button type="button" class="btn btn-primary mt-2" id="morningButton">Sáng</button>
        					<button type="button" class="btn btn-secondary mt-2" id="afternoonButton">Chiều</button>
	                    </div>
	                     <input type="text" hidden="hidden" name="maPhong" value="${ maPhong }">
	                    
                	</div>
                    
                
			</div>
			
			<div class="col-md-4 mt-2">
				<h3>Thiết bị sẵn sàng cho mượn</h3>
				<div class="list-container">
					<ul class="list-group list-item" id="elementList">
					<c:forEach var="item" items="${ thietbi }">
						<li class="list-group-item">${ item.maTB } - ${item.tenTB }</li>	
					</c:forEach>
					</ul>
				</div>
			</div>
			
			<div class="col-md-4 mt-2">
				<h3>Thiết bị phòng CSVC</h3>
				<div class="list-container">
					<ul class="list-group list-item" id="elementList">
					<c:forEach var="item" items="${ csvc }">
						<li class="list-group-item">${ item.maTB } - ${item.tenTB }</li>	
					</c:forEach>
					</ul>
				</div>
			</div>
			
			<div class="col-md-4 mt-2">
				<h2>Thiết bị mượn</h2>
				<div class="table-tb-wrapper">
					<table class="table-tb">
						<thead>
							<tr>
								<th>Tên</th>
							</tr>
						</thead>
						<tbody id="selectedElement">
							<!-- Dữ liệu về phần tử được chọn sẽ được thêm vào đây -->
						</tbody>
					</table>
				</div>
			</div>
			<div class="form-group col-12 mt-4 d-flex justify-content-end">
	               <button type="submit" class="btn btn-primary" >Xác nhận</button>
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
	            var inputDateTime = new Date(document.getElementById("dateTime").value);
	            var currentDateTime = new Date();

	            if (inputDateTime <= currentDateTime) {
	                event.preventDefault(); // Ngăn form gửi đi nếu thời gian nhập vào là quá khứ hoặc hiện tại
	                alert("Thời gian không được chọn quá khứ hoặc hiện tại!");
	            } else {
	                var selectedElementNames = selectedElements.join(', ');
	                $('<input>').attr({
	                    type: 'hidden',
	                    name: 'selectedElements',
	                    value: selectedElementNames
	                }).appendTo('#borrowForm');
	                return true; // Cho phép tiếp tục gửi form
	            }
	        }
	    });
	});
	
	</script>
	
	 <script>
        document.getElementById('morningButton').addEventListener('click', function() {
            setDateTime('11:30');
        });

        document.getElementById('afternoonButton').addEventListener('click', function() {
            setDateTime('17:30');
        });

        function setDateTime(time) {
            const dateInput = document.getElementById('dateTime');
            const dateValue = new Date(dateInput.value);
            if (!isNaN(dateValue.getTime())) {  // Check if the date is valid
                const [hours, minutes] = time.split(':');
                dateValue.setHours(parseInt(hours), parseInt(minutes), 0, 0);

                // Adjust for timezone offset
                const tzoffset = (new Date()).getTimezoneOffset() * 60000; // offset in milliseconds
                const localISOTime = (new Date(dateValue.getTime() - tzoffset)).toISOString().slice(0, 16);

                dateInput.value = localISOTime;
            } else {
                alert('Vui lòng chọn ngày hợp lệ trước.');
            }
        }
    </script>
	
	

</body>
</html>
