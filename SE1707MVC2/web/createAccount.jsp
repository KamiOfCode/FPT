<%-- 
    Document   : createAccount
    Created on : Oct 27, 2023, 10:03:07 AM
    Author     : LE HUU HUY
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create</title>
    </head>
    <body>
        <h1>Create New Account</h1>
        <form action="DispatchServlet" method="POST">
            Username* <input type="text" name="txtUsername" value="" />(6-30 chars)<br/>
            Password* <input type="password" name="txtPassword" value="" />(6-20 chars)<br/>
            Confirm* <input type="password" name="txtConfirm" value="" /><br/>
            Full name* <input type="text" name="txtFullname" value="" />(2-50 chars)<br/>
            <input type="submit" value="Create New Account" name="btAction" />
            <input type="reset" value="Reset" />
        </form>
    </body>
</html>
