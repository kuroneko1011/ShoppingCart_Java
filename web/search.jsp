<%-- 
    Document   : search
    Created on : Feb 11, 2022, 9:40:28 AM
    Author     : Nhan
--%>

<%--<%@page import="nhanht.registration.RegistrationDTO"%>
<%@page import="java.util.List"%>--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search</title>
        <link rel="stylesheet" href="CSS/search_style.css">
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;600;700&family=Salsa&display=swap" rel="stylesheet">
    </head>
    <body>
        <c:if test="${sessionScope.USERNAME ne null}">
            <div class="header">
                <div class="nav-bar">
                    <a href="searchPage">SEARCH</a>
                    <a href="shoppingPage">SHOPPING</a>
                </div>
                <div class="logout-part">
                    <div>
                        Welcome, ${sessionScope.USERNAME}
                    </div>
                    <div>
                        <a class="logout-link" href="logoutController">Log out</a>
                    </div> 
                </div>
            </div>
            <div class="main-body">
                <div class="search-area">
                    <img src="Images/search.svg">
                    <c:if test="${sessionScope.ROLE eq true}">
                        <form action="searchController">
                            <div class="search-form">
                                <input class="search-input "type="text" placeholder="  Search Key" name="txtSearchValue" 
                                       value="${param.txtSearchValue}" />
                                <input class="body-button" type="submit" value="Search" name="btAction" />
                            </div>

                        </form> <br/>

                        <c:set var="searchValue" value="${param.txtSearchValue}"/>
                        <c:if test="${not empty searchValue}">
                            <c:set var="result" value="${requestScope.SEARCHRESULT}"/>

                            <c:if test="${not empty result}">
                                <table border="1">
                                    <thead>
                                        <tr>
                                            <th>No.</th>
                                            <th>Username</th>
                                            <th>Password</th>
                                            <th>Full name</th>
                                            <th>Role</th>
                                            <th>Delete</th>
                                            <th>Update</th>                         
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="dto" items="${result}" varStatus="counter">
                                        <form action="updateAccountController">
                                            <tr>
                                                <td>${counter.count}</td>
                                                <td>
                                                    ${dto.username}
                                                    <input type="hidden" name="txtUserName" 
                                                           value="${dto.username}" />
                                                </td>
                                                <td><input type="text" name="txtPassword" 
                                                           value="${dto.password}" /></td>
                                                <td>${dto.fullName}</td>
                                                <td>
                                                    <input type="checkbox" name="chkAdmin" 
                                                           value="ON" class="${dto.role}" />   
                                                </td>                                      
                                                <td>
                                                    <c:if test="${dto.username ne sessionScope.USERNAME}">
                                                        <c:url var="deleteAccount" 
                                                               value="deleteController">
                                                            <c:param name="btAction" value="Delete"/>
                                                            <c:param name="pk" value="${dto.username}"/>
                                                            <c:param name="lastSearchValue" 
                                                                     value="${searchValue}"/>
                                                        </c:url>
                                                        <a class="body-button-form" href="${deleteAccount}">Delete</a>
                                                    </c:if>
                                                </td>
                                                <td>
                                                    <input class="body-button-form" type="submit" value="Update" name="btAction" />
                                                    <input type="hidden" name="lastSearchValue" 
                                                           value="${searchValue}" />
                                                </td>
                                            </tr>
                                        </form>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </c:if>
                            <c:if test="${empty result}">
                                <h2>
                                    No record is matched!!!
                                </h2>
                            </c:if>
                        </c:if>
                    </div>
                </div>
            </c:if>
            <c:if test="${sessionScope.ROLE ne true}">
                <div class="no-permission">
                    You have no permission to this feature
                </div>
            </c:if>
        </c:if>
        <c:if test="${sessionScope.USERNAME eq null}">
            <a href="loginPage">Click here to login</a>
        </c:if>

    </body>
    <script>
        let checked = document.querySelectorAll(".true");
        checked.forEach(check => check.checked = true);
    </script>
</html>
