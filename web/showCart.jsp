<%-- 
    Document   : showCart
    Created on : Feb 21, 2022, 10:13:48 AM
    Author     : Nhan
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.Map"%>
<%@page import="nhanht.cart.CartObject"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cart</title>
        <link rel="stylesheet" href="CSS/showCart_style.css">
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;600;700&family=Salsa&display=swap" rel="stylesheet">
    </head>
    <body>
        <div class="header">
            <div class="nav-bar">
                <a href="searchPage">SEARCH</a>
                <a href="shoppingPage">SHOPPING</a>
            </div>
            <c:if test="${sessionScope.USERNAME ne null}">
                <div class="logout-part">
                    <div>
                        Welcome, ${sessionScope.USERNAME}
                    </div>
                    <div>
                        <a class="logout-link" href="logoutController">Log out</a>
                    </div> 
                </div>
            </c:if>
            <c:if test="${sessionScope.USERNAME eq null}">
                <div class="login-part">
                    <a class="login-link" href="loginPage">Log in</a>
                </div>
            </c:if>
        </div>

        <c:if test="${not empty sessionScope}">
            <c:set var="cart" value="${sessionScope.CART}"/>
            <c:if test="${not empty cart}">
                <c:set var="items" value="${cart.getItems()}"/>
                <c:set var="bookList" value="${cart.getBookList()}" />
                <c:if test="${not empty items}">
                    <div class="cart">
                        <div class="title">Software Engineer Book Store</div>
                        <form action="removeItemFromCartController  ">
                            <table border="1">
                                <thead>
                                    <tr>
                                        <th>No</th>
                                        <th>Title</th>
                                        <th>Price</th>
                                        <th>Quantity</th>
                                        <th>Total</th>
                                        <th>Remove</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="id" items="${items.keySet()}" varStatus="counter">
                                        <c:set var="total" value="${total +(bookList.get(id))*(items.get(id))}"/>
                                        <tr>
                                            <td>
                                                ${counter.count}
                                            </td>
                                            <td>
                                                ${id}
                                            </td>
                                            <td>
                                                ${bookList.get(id)}
                                            </td>
                                            <td>
                                                ${items.get(id)}
                                            </td>
                                            <td>$${(bookList.get(id))*(items.get(id))}</td>
                                            <td>
                                                <input type="checkbox"
                                                       name="chkItem" value="${id}"/>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    <tr>
                                        <td colspan="4">
                                            Total price of your cart:
                                        </td>
                                        <td>
                                            $${total}
                                        </td>
                                        <td>
                                            <input type="submit" value="Remove Selected Items" name="btAction" />
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                        </form>


                    </c:if>
                </c:if>
            </c:if>
            <c:if test="${empty items}">
                <div class="no-cart-container">
                    <img class="no-cart" src="Images/empty_cart.svg"/>
                </div>

            </c:if>
            <div class="shopping-link">
                <c:if test="${empty items}">
                    <span> Your cart is empty!!! </span>
                    <a href="shoppingPage">Click to continue shopping</a>
                </c:if>
            </div>

            <c:if test="${not empty items}">
                <img class="not-empty-cart" src="Images/no_empty_cart.svg" />
                <c:if test="${sessionScope.USERNAME ne null}">
                    <a class="body-link" href="checkoutController">Click to Check out</a>
                    <a class="body-link" href="shoppingPage">Click to continue shopping</a>
                </c:if>
            </c:if>
        </div>
        <c:if test="${sessionScope.USERNAME eq null && not empty items}">
            <div class="require-login">
                <div>YOU MUST LOGIN TO CHECK OUT! </div>
                <a class="body-link" href="shoppingPage">Click to continue shopping</a>
            </div>
        </c:if>
    </body>

</html>
