<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="container mt-5 ms-5" style="min-height: 600px">
		<h2 class="title mt-3">Thêm mới phòng học</h2>
		<form:form method="POST" action="/QuanLyThietBi/quan-tri/quan-ly-phong/tao-phongs" modelAttribute="createRoomForm">
			<div class="form-group mt-4">
				<label for="exampleInputEmail1">Nhập mã phòng học</label> 
				<input name="maPhong" style="max-width: 500px"
					type="text" class="form-control mt-3"  placeholder="Nhập mã phòng"> 
			</div>
			
			
				<form:errors path="maPhong" cssClass="errors text-danger mt-4 d-block" />
			<button type="submit" class="btn btn-primary mt-4">Submit</button>
			
			
		</form:form>
	</div>
</body>
</html>