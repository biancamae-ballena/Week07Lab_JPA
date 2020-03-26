<%-- 
    Document   : notes
    Created on : Mar 6, 2020, 9:47:13 PM
    Author     : 785284
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Notes</title>
    </head>
    <body>
        <h1><b>Notes!</b></h1>
        <table>
            <tr>
                <th>Date Created</th>
                <th>Title</th>
                <th></th>
            </tr>
            <c:forEach var="notes" items="${noteList}">
                <tr>
                    <td>${notes.datecreated}</td>
                    <td>${notes.title}</td>
                    <td>
                        <form method="POST">
                            <input type="submit" name="edit" value="Edit"/>
                            <input type="hidden" name="noteId" value="${notes.noteid}"/>
                        </form>
                    </td>
                </tr>
        </table>
    <c:if test="${mode == 'view'}">
        <h2><b>Add Note</b></h2>
        <form method="POST">
            <input type="text" name="title" placeholder="Title"/><br/>
            <textarea name="content" rows="10" cols="40" placeholder="Type a note"></textarea><br/><br/>
            <input type="submit" name="btnAdd" value="Add"/>
        </form>
    </c:if>
    <c:if test="${mode == 'edit'}">
        <h2><b>Edit Note</b></h2>
        <form method="POST">
            <input type="hidden" name="selectedNote" placeholder="Title" value="${selectedNote}"/><br/>
            <input type="submit" name="btnDelete" value="Delete Note"/><br/>
            <input type="text" name="title" value="${title}"/><br/>
            <textarea name="content" rows="10" cols="40" placeholder="Type a note">${content}</textarea><br/><br/>
            <input type="submit" name="btnSave" value="Save"/>
        </form>
    </c:if>
    </body>
</html>
