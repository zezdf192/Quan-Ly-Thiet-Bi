<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>



<html>
<head>
<title>Slide Navbar</title>

<link href="https://fonts.googleapis.com/css2?family=Jost:wght@500&display=swap" rel="stylesheet">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">

<link href="../../resource/assets/css/login.css" rel="stylesheet">


<style>

body {

	overflow-x: hidden;
	height: 100%;
	background-repeat: no-repeat;
	margin: 0;
    font-family: -apple-system,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,"Noto Sans",sans-serif,"Apple Color Emoji","Segoe UI Emoji","Segoe UI Symbol","Noto Color Emoji";
    font-size: 1rem;
    font-weight: 400;
    line-height: 1.5;
    color: #212529;
    text-align: left;
    background-color: #fff;
}

.img_user {
width:22px;
height:22ppx;
}

.row>* {
	flex-shrink: 1;
}

.card0 {
	box-shadow: 0px 4px 8px 0px #757575;
	border-radius: 0px
}
.border-line {
    border-right: 1px solid #5139ab !important;
}

.card2 {
	margin: 0px 16px 0 0;
	margin-top:100px;
}

.logo-sign-in img {
	border-radius: 50%;
	background-color: aquamarine;
	border-style: inset;
}

.logo {
	width: 76px;
	height: 76px;
	padding-left: 0;
	padding-right: 0;
}

.logo-content {
	font-family: cursive;
}

.logo-text {
	width:auto;
	margin-left: 1.5rem;
	margin-top: 1rem;
	padding-left: 0;
	padding-right: 0;
}

.logo-content h5 {
	margin-bottom: 0px
}

.image {
	width: 94%;
	padding-left: 19px;
}

.border-line {
	border-right: 1px solid #EEEEEE
}

.logo-sign-in {
	width: 100%;
	display: flex;
	justify-content: center;
}

.facebook {
	background-color: #3b5998;
	color: #fff;
	font-size: 18px;
	padding-top: 5px;
	border-radius: 50%;
	width: 35px;
	height: 35px;
	cursor: pointer
}

.twitter {
	background-color: #1DA1F2;
	color: #fff;
	font-size: 18px;
	padding-top: 5px;
	border-radius: 50%;
	width: 35px;
	height: 35px;
	cursor: pointer
}

.linkedin {
	background-color: #2867B2;
	color: #fff;
	font-size: 18px;
	padding-top: 5px;
	border-radius: 50%;
	width: 35px;
	height: 35px;
	cursor: pointer
}

.line {
	height: 1px;
	width: 35%;
	background-color: #E0E0E0;
	margin-top: 10px
}

.or {
	width: 24%;
	padding-left:3%;
	padding-right:3%;
	font-weight: bold;
	font-size: 16px;
	line-height: 18px;
}

.text-sm {
	font-size: 14px !important;
}

.social-contact a {
	color: lightskyblue;
}

::placeholder {
	color: #BDBDBD;
	opacity: 1;
	font-weight: 300
}

:-ms-input-placeholder {
	color: #BDBDBD;
	font-weight: 300
}

::-ms-input-placeholder {
	color: #BDBDBD;
	font-weight: 300
}

input, textarea {
	padding: 10px 12px 10px 12px;
	border: 1px solid lightgrey;
	border-radius: 2px;
	margin-bottom: 5px;
	margin-top: 2px;
	width: 100%;
	box-sizing: border-box;
	color: #2C3E50;
	font-size: 14px;
	letter-spacing: 1px
}

input:focus, textarea:focus {
	-moz-box-shadow: none !important;
	-webkit-box-shadow: none !important;
	box-shadow: none !important;
	border: 1px solid #304FFE;
	outline-width: 0
}

button:focus {
	-moz-box-shadow: none !important;
	-webkit-box-shadow: none !important;
	box-shadow: none !important;
	outline-width: 0
}

a {
	color: inherit;
	cursor: pointer
}

.btn-blue {
	background-color: #1A237E;
	width: 150px;
	color: #fff;
	border-radius: 2px
}

.btn-blue:hover {
	background-color: #000;
	cursor: pointer
}

.bg-blue {
	color: #fff;
	background-color: #1A237E
}

.errors {
	color: #ff0036;
	font-style: italic;
}

.buttons {
	width: 100%;
	margin: 10%;
	text-align: center;
}

.btn-hover {
	width: 100%;
	min-width: 220px;
	font-size: 16px;
	font-weight: 600;
	color: #fff;
	cursor: pointer;
	margin: 0px 20%;
	height: 55px;
	text-align: center;
	border: none;
	background-size: 300% 100%;
	border-radius: 50px;
	moz-transition: all .4s ease-in-out;
	-o-transition: all .4s ease-in-out;
	-webkit-transition: all .4s ease-in-out;
	transition: all .4s ease-in-out;
}

.btn-hover:hover {
	background-position: 100% 0;
	moz-transition: all .4s ease-in-out;
	-o-transition: all .4s ease-in-out;
	-webkit-transition: all .4s ease-in-out;
	transition: all .4s ease-in-out;
}

.btn-hover:focus {
	outline: none;
}

.btn-hover.color-1 {
	background-image: linear-gradient(to right, #25aae1, #40e495, #30dd8a, #2bb673);
	box-shadow: 0 4px 15px 0 rgba(49, 196, 190, 0.75);
}

@media screen and (max-width: 991px) {
	.row .bg-img {
		margin-top: 12px !important;
		margin-bottom: 0px !important;
	}
	.row .logo-content {
		display: flex;
		justify-content: center;
		margin-left: 0px !important;
		align-items: center;
		flex-direction: column;
	}
	.logo-text {
		margin-top: 10px;
		margin-left: 0;
		display: flex;
		flex-direction: column;
		align-items: center;
	}
	.card1 {
		padding-bottom: 0
	}
	.image {
		width: 84%;
		padding-left: 0;
	}
	.border-line {
		border-right: none !important;
	}
	.card2 {
		border-top: 1px solid #EEEEEE !important;
		margin: 0px 15px
	}
	.row .img-bk {
		margin-bottom: 0 !important;
	}
}
</style>

</head>

<body>
	<div class="container">
		<div class="container-fluid px-1 px-md-5 px-lg-1 px-xl-5 py-5 mx-auto">
			<div class="card card0 border-0">
				<div class="row d-flex">
					<div class="col-lg-7">
						<div class="card1">
							<div class="row logo-content mt-4 " style="margin-left: 38px">
								<img
									src="https://user-images.githubusercontent.com/81857289/161365448-2d3a897e-8a96-4525-86b5-a7aa0cd7bb4f.png"
									class="logo">
								<div class="logo-text">
									<h5>Học Viện Công Nghệ Bưu Chính Viễn Thông</h5>
									<h5>Cơ sở TP.HCM</h5>
								</div>
							</div>
						</div>
						<div
							class="row px-3 img-bk justify-content-center mt-4 mb-5 border-line">
							<img
								src="https://user-images.githubusercontent.com/81857289/161364965-b845a6d4-d9b6-4510-b344-1f0d0ce8e0ff.png"
								class="image">
						</div>
					</div>

					<div class="col-lg-5">
						<div class="card2 card border-0 px-4 py-4">
							<div class="row mb-1 px-3">
								<div class="logo-sign-in">
									<img
										src="https://res.cloudinary.com/dwtlgxpxt/image/upload/e_gen_restore/c_limit,w_828/f_auto/q_auto/v1/imaginify/t13wg85pr5p409qwwbpk?_a=BAVCcWBy0" class="img_user">
								</div>
							</div>
							<div class="row px-3 mb-4">
								<div class="line"></div>
								<small class="or text-center">Sign In</small>
								<div class="line"></div>
							</div>
							<form:form method="POST" action="login" modelAttribute="loginForm" class="form" id="form-1">
								<div class="row px-3 mb-4">
									<label class="mb-1">
										<h6 class="mb-0 text-sm">Tên Đăng Nhập</h6>
									</label>
									 <input id="email" name="email" required  type="text" placeholder="VD: taikhoan" >
									
								</div>
								<div class="row px-3">
									<label class="mb-1">
										<h6 class="mb-0 text-sm">Mật Khẩu</h6>
									</label>
									<input id="password" required  name="password" type="password" placeholder="Nhập mật khẩu">
									<form:errors path="email" cssClass="errors mt-2" ></form:errors>
								</div>
								
								<div class="row px-3 mb-4">
									<!-- 			<div
										class="custom-control custom-checkbox custom-control-inline">
										<input id="chk1" type="checkbox" name="chk"
											class="custom-control-input"> <label for="chk1"
											class="custom-control-label text-sm">Remember me</label>
									</div>
									<a href="#" class="ms-auto mb-0 text-sm">Forgot Password?</a> -->
								</div>

								<div class="g-recaptcha d-flex justify-content-center"
									data-sitekey="6LcyNQMfAAAAANfcuBz9e1G4FIbW755pqOfvY6X_"></div>

								<label class="mb-1">
									<h6 class="text-sm errors">${tb }</h6>
								</label>
								<div class="row mb-3 px-3 d-flex justify-content-center">
									<button type="submit" class="btn-hover color-1 text-center">Login</button>
								</div>
							</form:form>
							<div class="row mb-4 px-3">
								<!-- <small class="font-weight-bold">Don't have an account? <a
									class="text-danger ">Register</a>
								</small> -->
							</div>
						</div>
					</div>
				</div>
				
			</div>
		</div>
	</div>
	
</body>




</html>