<%-- 
    Document   : updateRecord1
    Created on : Nov 3, 2015, 8:37:40 PM
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
        <h2>Update Dog Record</h2>
        <form action="update" method="get">
            Dog ID: <input type="number" name="dogId" placeholder="Dog id to update" required>
            <br><br>

            <input type="hidden" name="action" value="updateRecord1">
            
            <input type="submit" name="submit" value="Submit">
            <br><br>
        </form>
    </body>
</html>
