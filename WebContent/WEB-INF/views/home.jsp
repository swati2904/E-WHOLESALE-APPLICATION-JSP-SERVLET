<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>E- WHOLE SALE SYSTEM</title>
	<link rel="stylesheet" href="./style/home.css" type="text/css">


    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
</head>

<body>
    <!----------------------------------- header section ---------------------------------->
    <div class="header">
        <div class="logo">
            <p>
            <div><i class="fa fa-gg" aria-hidden="true"></i> E- WHOLE SALE</div>
            </p>
        </div>
        
        <div class="log-reg">
            <div class="segment register">
                <a href="${pageContext.request.contextPath}/login">
                    <button type="button" class="unit">Login</button>
                </a>
            </div>
            <div class="segment register">
                <a href="${pageContext.request.contextPath}/signup">
                    <button type="button" class="unit">Sign Up</button>
                </a>
            </div>
        </div>
    </div>



    <!------------------------------------ navigation menu section ----------------------->
    <div class="nav">
        <div class="nav-container">
            <ul>

                <li><a href="${pageContext.request.contextPath}/home"> <i class="fa fa-home" aria-hidden="true"></i>
                        <div class="icons">
                            <p>Home</p>
                        </div>
                    </a></li>

                <li><a href="${pageContext.request.contextPath}/cart"><i class="fa fa-shopping-cart"
                            aria-hidden="true"></i>
                        <div class="icons">
                            <p>Cart</p>
                        </div>
                    </a></li>

                <li><i class="fa fa-user" aria-hidden="true"></i>
                    <div class="icons">
                        <p>Profile</p>
                    </div>
                </li>

                <li><i class="fa fa-history" aria-hidden="true"></i>
                    <div class="icons">
                        <p>Order History</p>
                    </div>
                </li>

                <li><i class="fa fa-sign-out" aria-hidden="true"></i>
                    <div class="icons">
                        <p>Logout</p>
                    </div>
                </li>

            </ul>
        </div>
    </div>

    <!------------------------------- main page section ---------------------------------------->
    <div class="background">
        <div class="home-container">
            <div class="container" >

                <c:forEach var="prod" items="${productList}">
                    <div class="card" id="cart_section">
                        <div class="imgbox">
                            <img class="img" src="./assets/<c:out value=" ${prod.getProductName()}" />.jpg" alt="">
                        </div>
                        <div class="contentbox">
                            <h2 class="item-name">
                                <c:out value="${prod.getProductName()}" />
                            </h2>
                            <p class="discription">
                                Rs.
                                <c:out value="${prod.getPrice()}" />
                            </p>
                            <a href="addToCart?id=<c:out value='${prod.getProductId()}' />"><span>Add
                                    to cart</span></a>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>

</body>

</html>