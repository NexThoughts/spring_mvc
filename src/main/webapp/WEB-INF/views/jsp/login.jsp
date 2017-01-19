<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet"
          type="text/css"
          href="<s:url value="/resources/bootstrap.min.css" />">
</head>
<body>
<div class="container-fluid">
    <div class="page-header" style="background-color: #66afe9;margin: 0px !important;">
        <div class="row">
            <div class="col-lg-6 col-lg-offset-3 text-center text-capitalize" style=""><h1><b>Spring Security Demo</b>
            </h1></div>
        </div>
    </div>
    <br>
    <br>
    <div class="row">
        <div class="col-lg-6 col-lg-offset-3">
            <h2 class="text-center"><s:message code="user.login"/></h2>
            <form method="post" class="form-horizontal">
                <div class="col-lg-6 col-lg-offset-3">
                    <c:if test="${logout != null}" >
                        <div class="alert alert-success">
                            <strong>Success!</strong> You have been successfully logged out.
                        </div>
                    </c:if>
                    <c:if test="${error != null}" >
                        <div class="alert alert-danger">
                            <strong>Oops!</strong> Could not find a user with these credentials.
                        </div>
                    </c:if>
                    <br>
                    <fieldset>
                        <label class="text-left control-label" for="username">Username</label>
                        <input class="pull-right form-control input-md" type="text" name="username" id="username"
                               placeholder="Username"/>
                        <br>
                    </fieldset>

                    <fieldset>
                        <label class="text-left control-label" for="password">Password</label>
                        <input class="pull-right form-control input-md" type="password" name="password" id="password"
                               placeholder="Password"/>
                        <br>
                    </fieldset>

                    <fieldset>
                        <br>
                        <div class="pull-left">
                            <input type="submit" value="Login" class="btn btn-success ">
                        </div>
                        <div class="pull-right">
                            &nbsp;<a class="btn btn-info" href="<s:url value="/signup"/>"><s:message
                                code="user.signup"/></a>
                        </div>
                    </fieldset>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
