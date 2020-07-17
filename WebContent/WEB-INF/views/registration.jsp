<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>E-WHOLE SALE SYSTEM</title>
<link rel="stylesheet" href="./style/home.css" type="text/css">

</head>
<body>
<div id="register_image_only">
	<form action="${pageContext.request.contextPath}/register" method="post">
		<div class="segment">
			<h1>REGISTRATION DETAILS </h1>
			<br>
			${message}
			<br>
			<div class="hidden">
			<label for="userName" class="label"> <input type="text" name="userName"  value="${userName}" placeholder=" " id="userName"> </label>
			<label for="email" class="label"> <input type="text" name="email"  value="${email}" placeholder=" " id="email"> </label>
			<label for="password" class="label"> <input type="text" name="password"  value="${password}" placeholder=" " id="password"> </label>
		</div>
			
		</div>
				<label for="firstName" class="label"> <input type="text" name="firstName" placeholder="First name " id="firstName"> </label>
				
				<label for="lastName" class="label"> <input type="text" name="lastName" placeholder="Last name " id="lastName"> </label>
				
				<label for="gender" class="label"> <input type="text" name="gender" placeholder=" Gender " id="gender"> </label>
				
				<label for="number" class="label"> <input type="text" name="number" placeholder="Contact Number " id="number"> </label>
				
				<label for="address" class="label"> <input type="text" name="address" placeholder="Address " id="address"> </label>
				
				<label for="age" class="label"> <input type="text" name="age" placeholder="Age " id="age"> </label>
				<br>
			
				
				<button class="red" type="submit">
						<i class="fa fa-unlock-alt"> Register </i>
				</button>
		
		</form>
		</div>
</body>
</html>