<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<!DOCTYPE html>
<html lang="en">
<head>
<title>CSS Template</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">


<style>
* {
  box-sizing: border-box;
}

body {
  font-family: Arial, Helvetica, sans-serif;
  font-size: 16px;
}

/* Style the header */
header {
 	background-color: #fff;
    box-shadow: 0 2px 5px rgba(0,0,0,.1);
    left: 0;
    position: fixed;
    right: 0;
    top: 0;
    z-index: 100;
}

.header {
	align-items: center;
    display: flex;
    height: 80px;
    justify-content: space-between;
    margin: 0 100px;
}

header h2 {
	margin: 0;
	text-align: left; 
}

/* Create two columns/boxes that floats next to each other */
nav {
  float: left;
  width: 30%;
  height: 300px; /* only for demonstration, should be removed */
  background: #ccc;
  padding: 20px;
}

/* Style the list inside the menu */
nav ul {
  list-style-type: none;
  padding: 0;
}

article {
  float: left;
  padding: 20px;
  width: 70%;
  background-color: #f1f1f1;
  height: 300px; /* only for demonstration, should be removed */
}

/* Clear floats after the columns */
section::after {
  content: "";
  display: table;
  clear: both;
}

/* Style the footer */
footer {
  background-color: #777;
  padding: 10px;
  text-align: center;
  color: white;
}

.content {
	margin-top: 80px;
	display: flex;
	justify-content: center;
}

/* Responsive layout - makes the two columns/boxes stack on top of each other instead of next to each other, on small screens */
@media (max-width: 600px) {
  nav, article {
    width: 100%;
    height: auto;
  }
}

.main {
	padding: 20px;
    width: 80%;
}


.manage-body {
    margin: 0 90px;
}

.manage-body .title {
    font-size:26px;
    margin: 10px 0 40px;
    text-align: center;
}

.header-exam {
 display: flex;
    align-items: center;
   
    justify-content: space-between;
}

.add-exam {
    color: #616161;
    font-size: 19px;
    font-weight: 600;
    text-decoration: none;
}

.manage-body .filter {
    margin: 20px 0 12px;
    padding: 6px;
    text-align: right;
}

.manage-body .table-container {
    font-size: 18px;
    position: relative;
}

.manage-body .table-container .table-head {
    box-shadow: 0 0 30px 0 rgba(0,0,0,.15);
}

.manage-body .table-container .table-body {
    box-shadow: 0 0 10px 1px rgba(0,0,0,.15);
    max-height: 400px;
    overflow: auto;
    position: relative;
}

table {
    border-collapse: collapse;
    border-top-left-radius: 10px;
    border-top-right-radius: 10px;
    color: gray;
    overflow: hidden;
    position: relative;
    table-layout: fixed;
    width: 100%;
}

tbody, td, tfoot, th, thead, tr {
    border: 0 solid;
    border-color: inherit;
    text-align: center;
}

td {
	padding: 14px 0;
}

.manage-body .table-container .table-head table .table-header {
    background-color: #6c7ae0;
    color: #fff;
}

.manage-body .table-container .table-head table .table-header th {
    padding: 16px 12px;
    text-align: center;
}

</style>
</head>
<body>



<%@include file="/WEB-INF/views/layouts/admin/header.jsp" %>

<div class="content">
	<%@include file="/WEB-INF/views/layouts/admin/silebar.jsp" %>
	<div class="main"> <decorator:body />
	</div>
	
</div>



<%@include file="/WEB-INF/views/layouts/admin/footer.jsp" %>

</body>
</html>