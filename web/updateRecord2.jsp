<%-- 
    Document   : updateRecord2
    Created on : Nov 3, 2015, 8:54:49 PM
    Author     : John Phillips
--%>

<%@page import="model.Dog" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Dave's Dog Rescue</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="mystyle.css">
    </head>
    <body>
        <h1><a href="home.html">Dave's Dog Rescue</a></h1>
        <h2>Update Dog Record</h2>
        <form action="update" method="get">
            <% Dog dog = (Dog) request.getAttribute("dog");%>
            Dog Id: <input type="text" name="empId" value="<%= dog.getDogId() %>" readonly>
            <br><br>
            Name: <input type="text" name="title" size="100" value="<%= dog.getName() %>" required>
            <br><br>
            Species: <input type="text" name="author" size="100" value="<%= dog.getSpecies() %>" required>
            <br><br>            
            Color: <input type="text" name="subject" size="100" value="<%= dog.getColor() %>" required>
            <br><br>
            Age: <input type="number" name="age" value="<%= dog.getAge() %>" required>
            <br><br>

            <input type="hidden" name="action" value="updateRecord2">

            <input type="submit" name="submit" value="Submit">
            <br><br>
        </form>
    </body>
</html>
