<%-- 
    Document   : newpost
    Created on : Mar 8, 2016, 10:57:32 PM
    Author     : kevin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Post new thread</title>
    </head>
    <body>
        <h3>Make new post as ${username}</h3>
        
        <form method="POST" action="SubmitPost">
            <label for="newpost">New post:</label>
            <textarea rows="4" cols="50" name="text"></textarea>
            <input type="submit">
            <input type="hidden" name="username" value="${username}">
        </form>
        
        <h4><a href="view.jsp">Skip straight to posts</a></h4>
    </body>
</html>
