<%-- 
    Document   : login
    Created on : Mar 11, 2022, 4:02:33 AM
    Author     : Nhan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Login Page</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="CSS/login_style.css">
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;600;700&family=Salsa&display=swap" rel="stylesheet">
    </head>
    <body>
        <div class="title">Welcome to Software Engineer Book Store</div>
        <a href="shoppingPage">Click here to visit our shop!</a> <br/>
        <div class="register-link">
            <span>Don't have an account? </span>
            <a href="registerPage">Click here to register</a>
        </div>

        <div class="form">
            <form action="loginController" method="POST">
                <div class="form-username">
                    <input id="username" placeholder=" " class="input-field username" type="text" name="txtUsername" value="" /> 
                    <label for="username" class="label">Username</label>
                </div>
                <div class="form-password">
                    <input id="password" placeholder=" " class="input-field" type="password" name="txtPass" value="" /> 
                    <label for="password" class="label">Password</label>
                </div>

                <div class="button">
                    <input class="submit-button" type="submit" value="Login" name="btAction" /> 
                </div>
            </form>
        </div>        
        <img src="Images/login.svg"/>
        <br/>
    </body>
    
    <script>
        if ("${sessionScope.USERNAME}" !== "") {
            window.location.assign("searchPage");
        }
    </script>
</html>
