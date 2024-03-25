<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="container mt-5 ms-5" style="min-height: 600px">
		<h2 class="title mt-3">
			Sửa phòng học: <span>${ maPhong }</span>
		</h2>
		<form:form method="POST"
			action="/QuanLyThietBi/quan-tri/quan-ly-phong/cap-nhat-phong/${ maPhong }"
			modelAttribute="updateRoomForm">


			<h5 class="mt-3">Chỉnh sửa trạng thái phòng:</h5>

			<select class="form-select mt-4" style="width: 500px"
				 id="cars">

				<c:forEach var="item" items="${trangThai}">
					<option value="${item.maTinhTrang}"
						${item.maTinhTrang == optionActive ? 'selected' : ''}>
						${item.tenTinhTrang}</option>
				</c:forEach>


			</select>






			<button type="submit" class="btn btn-primary mt-4">Submit</button>


		</form:form>
	</div>
</body>
</html>