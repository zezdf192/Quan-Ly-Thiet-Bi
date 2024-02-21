<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<!DOCTYPE html>
<html>
<head>


<meta charset="UTF-8">

</head>

<body>
	<div class="manage-body">
		<h2 class="title">Quản lý phòng học</h2>
		<div class="header-exam">
			<a class="add-exam" href="/createExam">Thêm mới phòng học</a>
			<div class="filter">
				<div class="filter-container">
					<span class="filter-button"><span class="search">Lọc
							phòng</span></span>
				</div>
			</div>
		</div>
		<div class="table-container">
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
							<td class="none-m">${item.trangThai }</td>
							
							<td class="action" style="gap: 30px;"><button
									class="btn btn-success">Xem
									</button>
								<button class=" btn btn-warning">
									Sửa</button>
								<button class=" btn btn-danger">
									Xóa</button></td>
						</tr>
						</c:forEach>
						
						
					</tbody>
				</table>
			</div>

		</div>
	</div>
</body>
</html>