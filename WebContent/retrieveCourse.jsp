<%@taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="cmsStyle.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Retrieve Course | Course Management System</title>
</head>
<body>
	<header>
		<jsp:include page="logo.jsp"></jsp:include>
		<h1>Course Management System</h1>
	</header>
	<div id="wrapper">
		<div id="main">
			<div id="heading">Retrieve Course</div>

			<section>
				<c:if test="${requestScope.message!=null}">
					<div id="errorMessage">
						<c:out value="${requestScope.message}"></c:out>
					</div>
				</c:if>

				<s:div id="retrieveCourseSection">
					<s:form method="post" action="./retrieveCourse">
								<s:textfield name="courseCode" key="COURSECODE"></s:textfield>
								<s:submit value="Retrieve Course"></s:submit>
								<s:reset value="Reset"></s:reset>
					</s:form>
				</s:div>
				</section>
			
		</div>

		<div id="footer">
			<jsp:include page="./footer.html"></jsp:include>
		</div>
	</div>
</body>
</html>