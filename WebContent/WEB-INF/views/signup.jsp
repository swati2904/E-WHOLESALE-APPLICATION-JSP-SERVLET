<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>E- WHOLESALE SYSTEM</title>
<link rel="stylesheet" href="./style/home.css" type="text/css">
</head>
<body>

<%-- <img class="signup_background" src="https://review.chinabrands.com/chinabrands/seo/image/20181015/20181015042535155769-1.jpg" alt ="" /> --%>
<div id="image-only">
	  <div class="segment register">
		<a href="${pageContext.request.contextPath}/login">
			<button type="button" class="unit">Login</button>
		</a>
	</div>
   <%--  <div class="segment home">
		<a href="${pageContext.request.contextPath}/home">
			<button type="button" class="unit">Home</button>
		</a>
	   </div> --%>	

	<form class="background_set" action="${pageContext.request.contextPath}/signup" method="post">
		<div class="segment">
			<h1>Sign-up</h1>
		</div>
		${message} 
		<br> 
		
		
		
		<label for="userName" class="label"> <input type="text" name="userName" placeholder="User name " id="userName"> </label>
		
		<label for="email" class="label"> <input type="text" name="email" placeholder="Email Address" id="email"> </label>
		
	     <label for="password" class="label"> <input type="password" name="password" placeholder="password" id="password"> </label> 
	     
	     <label for="confirmPassword" class="label"> <input type="password" name="confirmPassword" placeholder="confirm password" id="confirmPassword"> </label>
	     
		<div class="password-hint">Must consist of atleast 6 characters,
			a symbol, an upper and a lower case letter</div>
		<br>

		<button class="red" type="submit">
			<i class="fa fa-unlock-alt"> Sign Up </i>
		</button>
	</form>
</div>
</body>
</html>