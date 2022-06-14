<%-- 
    Document   : createAccount
    Created on : Mar 2, 2022, 9:44:27 AM
    Author     : Nhan
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="CSS/register_style.css">
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;600;700&family=Salsa&display=swap" rel="stylesheet">
        <title>Create Account</title>
    </head>
    <body>
        <div class="title">
            Welcome!
        </div>
        <div class="sign-in-div">
            <span>Already a user? </span> 
            <a href="loginPage">Sign In</a>
        </div>
        <a href="shoppingPage">Click here to visit our shop!</a>
        <div class="form">
            <form action="createAccountController" method="POST">
                <c:set var="errors" value="${requestScope.CREATEERRORS}"/>
                <div class="input-username">
                    <input id="username" class="input-field" placeholder=" " type="text" name="txtUserName" value="${param.txtUserName}" />
                    <label for="username" class="label">Username</label> <br/>
                    <c:if test="${not empty errors.usernameLengthErr}">
                        <font color="red">
                        ${errors.usernameLengthErr}
                        </font> <br/>
                    </c:if>
                    <c:if test="${not empty errors.usernameIsExisted}">
                        <font color="red">
                        ${errors.usernameIsExisted}
                        </font> <br/>
                    </c:if>
                </div>
                <div class="input-password">
                    <input id="password" class="input-field" placeholder=" " type="password" name="txtPassword" value="" />
                    <label for="password" class="label">Password</label> <br/>
                    <c:if test="${not empty errors.passwordLengthErr}">
                        <font color="red">
                        ${errors.passwordLengthErr}
                        </font> <br/>
                    </c:if>
                </div>
                <div class="input-confirm">
                    <input id="confirm" class="input-field" placeholder=" " type="password" name="txtConfirm" value="" /> 
                    <label for="confirm" class="label">Confirm</label> <br/>
                    <c:if test="${not empty errors.confirmNotMatched}">
                        <font color="red">
                        ${errors.confirmNotMatched}
                        </font> <br/>
                    </c:if>
                </div>
                <div class="input-fullName">
                    <input id="fullname" class="input-field" placeholder=" " type="text" name="txtFullName" value="${param.txtFullName}" /> 
                    <label for="fullname" class="label">Fullname</label> <br/>
                    <c:if test="${not empty errors.fullNameLengthErr}">
                        <font color="red">
                        ${errors.fullNameLengthErr}
                        </font> <br/>
                    </c:if>
                </div>
                <div class="button">
                    <input class="submit-button" type="submit" value="Create New Account" name="btAction" />
                </div>
            </form>
        </div>

        <img src="Images/register.svg"/>
    </body>
</html>
