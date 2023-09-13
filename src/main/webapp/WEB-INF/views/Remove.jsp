<%@page import="com.jspiders.springmvc4.pojo.StudentPojo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%
	List<StudentPojo> students = (List<StudentPojo>) request.getAttribute("students");
	String msg = (String) request.getAttribute("msg");
	%>
<jsp:include page="NavBar.jsp"></jsp:include>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
form {
	margin-top: 10px;
}

form table {
	margin: auto;
	width: 100%;
}

tr {
	text-align: center;
}

fieldset table {
	margin: auto;
	text-align: left;
}

fieldset {
	margin: 15px 520px;
	text-align: center;
}

legend {
	color: white;
	background-color: #333;
}

#data {
	background-color: white;
	border: 1px solid black;
	width: 100%;
	border: 1px solid black;
}

#data td {
	border: 1px solid black;
	text-align: center;
}
</style>
</head>
<body>
	<div align="center">
		<fieldset>
			<legend>Remove Student Details</legend>
			<form action="./remove" method="post">
				<table>
					<tr>
						<td>Enter ID</td>
						<td><input type="text" name="id"></td>
					</tr>
				</table>
				<input type="submit" value="REMOVE">
			</form>
		</fieldset>
		<%
		if(msg!= null){
		%>
		<h4>
		<%=msg%>
		</h4>
		<%
		}
		%>
		<%
		if (students!=null){
		%>
		<table id="data">
		<tr>
		        <th>ID</th>
				<th>NAME</th>
				<th>EMAIL</th>
				<th>CONTACT</th>
				<th>ADDRESS</th>
		</tr>
		<%
		 for(StudentPojo pojo: students){
		%>
		  <tr>
				<td><%=pojo.getId()%></td>
				<td><%=pojo.getName()%></td>
				<td><%=pojo.getEmail()%></td>
				<td><%=pojo.getContact()%></td>
				<td><%=pojo.getAddress()%></td>
		  </tr>
		<%
		 }
		%>
		</table>
		<%
		}
		%>
		
	</div>
</body>
</html>