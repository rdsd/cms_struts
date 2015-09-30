<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="cmsStyle.css">
<title>Fatal Error | Course Management System</title>
</head>
<body>
	<header>
		<jsp:include page="logo.jsp"></jsp:include>
		<h1>Course Management System</h1>
	</header>
	<div id="wrapper">
		<div id="errorMessage">Sorry A Fatal Error has occured. Please
			try after some time.</div>
		<div id="footer">
			<jsp:include page="./footer.html"></jsp:include>
		</div>
	</div>
</body>
</html>