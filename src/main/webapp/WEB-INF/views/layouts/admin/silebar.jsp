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
				<a class="action-item" href="/QuanLyThietBi/quan-tri/quan-ly-thiet-bi">Quản lý thiết bị</a>
				<a class="action-item" href="/QuanLyThietBi/quan-tri/quan-ly-nhanvien">Quản lý nhân viên</a>
				<a class="action-item" href="/QuanLyThietBi/quan-tri/quan-ly-phieu-muon">Lập phiếu mượn</a>
				<a class="action-item" href="/ratings/examId">Thống kê phiếu mượn</a>
		</div>
	</div>
	
<script>
    // Lấy ra danh sách các thẻ <a> trong list-action
    var actionItems = document.querySelectorAll('.list-action .action-item');

    // Lặp qua từng thẻ <a>
    actionItems.forEach(function(item) {
        // Thêm sự kiện click vào mỗi thẻ <a>
        item.addEventListener('click', function(event) {
            // Ngăn chặn hành vi mặc định của thẻ <a> (chuyển hướng trang)
            event.preventDefault();
            
            // Lấy href của thẻ <a> được click
            var href = this.getAttribute('href');
            
            // Lưu href vào sessionStorage
            sessionStorage.setItem('activeLink', href);

            // Loại bỏ lớp 'active' từ tất cả các thẻ <a> trước
            actionItems.forEach(function(item) {
                item.classList.remove('active');
            });

            // Thêm lớp 'active' vào thẻ <a> được click
            this.classList.add('active');

            // Chuyển hướng đến href
            window.location.href = href;
        });

        // Kiểm tra xem có href đã được lưu trong sessionStorage hay không
        var savedHref = sessionStorage.getItem('activeLink');
        if (savedHref === item.getAttribute('href')) {
            // Nếu có, thêm lớp 'active' cho thẻ <a> tương ứng
            item.classList.add('active');
        } else {
            // Nếu không, đảm bảo rằng tất cả các thẻ <a> không được active
            item.classList.remove('active');
        }
    });
</script>


</body>
</html>