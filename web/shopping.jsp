<%-- 
    Document   : shopping
    Created on : Mar 11, 2022, 5:56:34 AM
    Author     : Nhan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
    <head>
        <title>Book Store</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="CSS/shopping_style.css">
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
        <c:set var="books" value="${sessionScope.BOOK}"/>
        <div class="main-body">
            <div class="title">Software Engineer Book Store</div>
            <div class="form">
                <form action="cartController">
                    <span> Choose book: </span> <select name="cboItem">
                        <c:forEach var="id" items="${books}" varStatus="counter">
                            <c:if test="${books.get(counter.count-1).getAvailableQuantity() > 0}">
                                <option>${books.get(counter.count-1).getName()} (storage: ${books.get(counter.count-1).getAvailableQuantity()})</option>
                            </c:if>
                        </c:forEach>

                    </select> <br/>
                    <div class="button-part">
                        <input type="submit" name="btAction" value="Add Book To Your Cart" />
                        <input type="submit" name="btAction" value="View Your Cart" />
                    </div>

                </form>
            </div>

        </div>
        <img src="Images/shopping.svg" />
        <div class="mesg">${requestScope.SUCCESSFULL}</div>
        <div class="mesg">${requestScope.FAILED}</div>
    </body>
</html>
