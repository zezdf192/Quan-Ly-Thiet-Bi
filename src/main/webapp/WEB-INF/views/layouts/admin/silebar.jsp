<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>


<style>
	.sidebar-container {
    background-color: #fff;
    min-width: 310px;
}

.sidebar-container .list-action {
    display: flex;
    flex-direction: column;
    margin-top: 40px;
    padding-left: 0; }

.sidebar-container .list-action .action-item.active {
    background-color: #e1f8ff;
    color: #0281a6;
}

.sidebar-container .list-action .action-item {
    border-radius: 10px;
    color: #41454c;
    font-size: 18px;
    font-weight: 500;
    list-style-type: none;
    padding: 14px 16px 14px 20px;
    text-decoration: none;
}

</style>

</head>
<body>
	<div class="sidebar-container">
		<div class="list-action">
				<a class="action-item active" href="/QuanLyThietBi/quan-tri/quan-ly-phong"">Quản lý phòng</a>
				<a class="action-item" href="/QuanLyThietBi/trang-chu/test">Quản lý thiết bị</a>
				<a class="action-item" href="/QuanLyThietBi/quan-tri/quan-ly-nhanvien">Quản lý nhân viên</a>
				<a class="action-item" href="/ratings/examId">Lập phiếu mượn</a>
				<a class="action-item" href="/ratings/examId">Thống kê phiếu mượn</a>
		</div>
	</div>
	
	<script type="text/javascript">
	let elements = document.querySelectorAll(".list-action .action-item");
    elements.forEach((item) => {
        item.addEventListener("click", () => {
            let activeItem = document.querySelector(".list-action .active");
            if (activeItem) { // Kiểm tra xem có phần tử "active" nào không
                activeItem.classList.remove("active"); // Loại bỏ lớp "active" từ phần tử hiện tại
            }
            item.classList.add("active"); // Thêm lớp "active" cho phần tử được nhấp vào
        });
    });
	</script>

</body>
</html>