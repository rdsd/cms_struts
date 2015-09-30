<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="cmsStyle.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Course Details | Course Management System</title>
</head>
<body>
	<header>
		<jsp:include page="logo.jsp"></jsp:include>
		<h1>Course Management System</h1>
	</header>

	<div id="wrapper">
		<div id="main">
			<div id="heading">Course Details</div>
			<section>
				<div id="courseDetails">
					<table>
						<tr>
							<th>Course Code</th>
							<th>Course Name</th>
							<th>Course Description</th>
							<th>Number Of Participants</th>
							<th>Course Type</th>
							<th>Duration</th>
							<th>Course Fees</th>
						</tr>
						
						<tr>
							<td><c:out value="${requestScope.courseVO.courseCode}"></c:out></td>
							<td><c:out value="${requestScope.courseVO.courseName}"></c:out></td>
							<td><c:out value="${requestScope.courseVO.courseDescription}"></c:out></td>
							<td><c:out value="${requestScope.courseVO.noOfParticipants}"></c:out></td>
							<td><c:out value="${requestScope.courseVO.courseType}"></c:out></td>
							<td><c:out value="${requestScope.courseVO.courseDuration}"></c:out></td>
							<td><c:out value="${requestScope.courseVO.courseFee}"></c:out></td>
						</tr>
					</table>
			</section>
		</div>
		<div id="footer">
			<jsp:include page="./footer.html"></jsp:include>
		</div>
	</div>
</body>
</html>