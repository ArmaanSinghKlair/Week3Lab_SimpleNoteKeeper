<%-- 
    Document   : viewnote.jsp
    Created on : Oct 1, 2020, 1:42:57 PM
    Author     : 839645
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="base.css" />
        <title>VIEW NOTE</title>
    </head>
   
    <body>
        
        <h1 style="padding:1vmax 10%;background:white">Your Notes</h1>
        
        <c:forEach var="i" begin="0" end="${notes.size()-1}" >
            <div class="note">
                <h3>
                    <c:out value="${notes[i].title}" default="Note Title"/>
                </h3>
                <p>
                    ${notes[i].content}
                </p>
                <a href="note?edit=${notes[i].id}"> Edit</a>
            </div>
        </c:forEach>
    </body>
</html>
