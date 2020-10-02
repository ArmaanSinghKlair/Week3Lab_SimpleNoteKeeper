<%-- 
    Document   : editnote.jsp
    Created on : Oct 1, 2020, 1:43:14 PM
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
        <title>EDIT NOTE</title>
    </head>
    <body>
        <form action="note" method="post">
            <h1>Edit Note ${note.id}</h1>
           
            <input type='hidden' name='id' value='${note.id}' />
            <label>
                Title
                <input type='text' value="${note.title}" name='title' />
            </label>
            
            <label>
                Content
                <textarea rows="7" cols="50" name='content' value="">${note.content}</textarea>
            </label>
            
            <input type="submit" value="Edit & Save" />
            <%--<c:forEach var="i" begin="0" end="${fn:length(content)}" >${content[i]}</c:forEach>--%>
            
            <c:if test="${invalid == true}">
                <div class='error'>
                    Please do not leave any field Empty !!
                </div>
            </c:if>
        </form>
    </body>
</html>