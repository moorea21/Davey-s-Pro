<%-- 
    Document   : displayRecords
    Created on : Nov 3, 2015, 4:52:40 PM
    Author     : John Phillips
--%>

<%@page import="java.util.List, model.Dog"%>
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
        <h2>Dog Report</h2>
        <%
            List<Dog> mydata = (List<Dog>) request.getAttribute("mydata");
            out.println("<table>");
            for (Dog dog : mydata) {
                out.println(dog.inHTMLRowFormat());
            }
            out.println("</table>");
        %>
    </body>
</html>
