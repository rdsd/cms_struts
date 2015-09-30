<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="cmsStyle.css">
<title>Course Added | Course Management System</title>
</head>
<body>
	<header> <jsp:include page="logo.jsp"></jsp:include>
	<h1>Course Management System</h1>
	</header>
	<div id="wrapper">
		<div id="main">
			<div id="courseAddedMessage">
				Course Successfully Added in the System. <a href="welcomeAdmin.jsp">Click
					here</a> to go to the Admin page
			</div>
		</div>
		<div id="footer">
			<jsp:include page="./footer.html"></jsp:include>
		</div>
	</div>
</body>
</html>