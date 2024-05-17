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
		<h2 class="title">Chọn phòng học</h2>
		
		<div class="header-exam" style="justify-content: flex-end;">
		
		
	
			
		
		
			<%-- <div>
				<form:form class="d-flex" style="gap:20px; height:40px" method="GET"
					action="/QuanLyThietBi/quan-tri/quan-ly-phong/tim-kiem-phong">
					<input name="inputText" class="form-control"
						placeholder="Tìm kiếm theo tên phòng..." />
					

					<button type="submit" class="btn btn-primary" style="width: 165px">Tìm
						kiếm</button>
				</form:form>

			</div> --%>
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
						           
						             <form:form id="updateForm" method="GET" action="lap-phieu-muon/${item.maPhong}" modelAttribute="thietBiTrongPhong" class="row g-3">
		                    				<button type="submit" class="btn btn-success btn-update">Chọn thiết bị</button>
		               				</form:form>
						            
						
						            
						           
						        </td>
						    </tr>
						</c:forEach>


					</tbody>
				</table>
			</div>

		</div>
		
		</div>
		
	
<script>





/* document.querySelectorAll('.btn-update').forEach(button => {
    button.addEventListener('click', function () {
        
        var maPhong = this.getAttribute('data-bs-whatever');
    	
    	
    	

     
    	// alert(tenTrangThai);
        
        var updateForm = document.getElementById('updateForm'); 
        
        updateForm.action = "/QuanLyThietBi/quan-tri/quan-ly-phong/sua-phong/" + maPhong;

       
        
      
        document.getElementById('maPhongModal').value = maPhong;    
       // document.getElementById('selectRoom').value = tenTrangThai;

       
     
    });
}); */
</script>

</body>
</html>