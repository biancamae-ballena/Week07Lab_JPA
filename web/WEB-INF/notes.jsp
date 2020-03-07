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
            </tr>
        </table>
        <h2><b>Add Note</b></h2>
        <form method="GET">
            <input type="text" name="title" placeholder="Title"/><br/>
            <textarea rows="10" cols="40" placeholder="Type a note"></textarea><br/><br/>
            <input type="submit" name="btnSubmit" value="Add"/>
        </form>
    </body>
</html>
