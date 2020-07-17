<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="model.Product" %>
<%@ page import=" java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>E - WHOLE SALE SYSTEM</title>
<link rel="stylesheet" href="./style/cartStyle.css" type="text/css">

</head>
<body >
<div id="cart_image_only">
	<p>
		<h1>Your Cart</h1>
	</p>
	<div class="segment home">
		<a href="${pageContext.request.contextPath}/home">
			<button type="button" class="unit">Home</button>
		</a>
	</div>
	<div class="container">
		<hr>
		<div class="card head">
			ItemNo |<span> Product Name</span> | <span>Price</span> | <span>Quantity</span>|
		</div> 
		<hr>
	</div>
	
	<div class="container">
	 <c:if test="${updatedCart.size() != null}"> 
		<c:forEach items="${updatedCart}" var="product">
			<div class="card list">
				<span>Item </span>| <span> <c:out value="${product.getProductName()}" /></span> |
				<span><c:out value="${product.getPrice()}" /></span> | <span>Pieces </span> | 
			</div> <span><a href="cart?id=<c:out value='${product.getProductId()}' />"> <button class="remove">Remove</button></a> </span>
			<br>
		</c:forEach>
	  </c:if> 
</div>


<div class="container">
	<div class="card">
        <span><h2>Total Amount :  Rs. <c:out value='${total}' /></h2></span>
	</div>
</div>

	<c:if test="${cartList.size() == null}">  
		<div class="card">
			<h3>Cart is empty</h3>
		</div>
	</c:if> 
	
	 <c:if test="${updatedCart.size() != null}"> 
	 <div class="segment register">
	 <a href="cart?id=placeOrder" >
        <button type="button" class="unit" >
            Place Order
        </button></a>
    </div>
    </c:if>
</div>
</body>
</html>