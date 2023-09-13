<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
fieldset table {
	margin: auto;
	text-align: left;
}

fieldset {
	/* margin: 15px 520px; */
	text-align: center;
}

legend {
	color: white;
	background-color: #333;
}

body {
	background-image:
		url('https://www.xmple.com/wallpaper/linear-blue-white-highlight-gradient-1920x1080-c2-ffffff-e0ffff-l-50-a-165-f-21.svg');
	background-size: 100%;
}
</style>	
</head>
<body>
	<div align="center">
		<fieldset>
			<legend>Create Admin Account</legend>
			<form action="./createAccount" method="post">
				<table>
					<tr>
						<td>Username</td>
						<td><input type="text" name="username"></td>
					</tr>
					<tr>
						<td>Password</td>
						<td><input type="text" name="password"></td>
					</tr>
				</table>
				<input type="submit" value="CREATE ACCOUNT">
			</form>
		</fieldset>
	</div>
</body>
</html>