<%-- 
    Document   : createRecord
    Created on : Nov 3, 2015, 5:19:26 PM
    Author     : John Phillips
--%>

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
        <h2>Create New Dog Record</h2>
        <form action="create" method="get">

            Name: <input type="text" name="name" size="100" placeholder="Enter Dog Name" required>
            <br><br>
            Species: <input type="text" name="species" size="100" placeholder="Enter Species" required>
            <br><br>            
            Color: <input type="text" name="color" size="100" placeholder="Enter Color" required>
            <br><br>
            Age: <input type="number" name="age" placeholder="age" required>
            <br><br>

            <input type="hidden" name="action" value="createRecord">

            <input type="submit" name="submit" value="Submit">
            <br><br>
        </form>
    </body>
</html>

