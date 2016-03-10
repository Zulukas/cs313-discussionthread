<%-- 
    Document   : index
    Created on : Mar 8, 2016, 10:48:54 PM
    Author     : kevin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sign Inninator</title>
    </head>
    <body>
        <h1>Sign in!</h1>
        
        <form action="LoginValidate" method="POST">
            <table>
                <tr>
                    <td>Username: </td>
                    <td><input type="text" name="username"></td>
                </tr>
                <tr>
                    <td>Password: </td>
                    <td><input type="password" name="password"</td>
                </tr>
                <tr>
                    <td><input type="submit"</td>
                </tr>
            </table>
        </form>        
    </body>
</html>
