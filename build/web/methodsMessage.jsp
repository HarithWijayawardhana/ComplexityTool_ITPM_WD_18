<%-- 
    Document   : methodsMessage
    Created on : Apr 24, 2020, 10:28:43 AM
    Author     : Harith
--%>

<%@page import="java.util.Map"%>
<%@page import="java.util.LinkedHashMap"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <link rel="stylesheet" href="View\bootstrap.min.css">

        <title>JSP Page</title>
    </head>
    <body>

        <div class="container-fluid bg-light">

            <div class="mb-5 mt-5">
                <button type="button" class="btn btn-outline-primary active"><a href="sizeVariable.jsp">SIZES </a></button>
                <button type="button" class="btn btn-outline-primary">METHODS</button>
                <button type="button" class="btn btn-outline-primary">VARIABLES</button>
                <a href="EditvalueMethods.jsp"><button type="button" class="btn btn-danger">Edit weights</button></a>

            </div>


        </div>

        <div  class="col-6 align-self-center" style="text-align:center;">
            <center>


                <table border="2px" class="table table-dark"> 

                    <tr>
                        <th>Code</th>
                        <th>Wmrt</th>
                        <th>Npdtp</th>
                        <th>Ncdtp</th>
                    </tr>

                    <%
                        // retrieve your list from the request, with casting 
                        //ArrayList<CodeLine> list = (ArrayList<CodeLine>) request.getAttribute("message");
                        Map<String, Integer> score = (LinkedHashMap<String, Integer>) request.getAttribute("methodReturnType");

                        Map<String, Integer> scorePrimitive = (LinkedHashMap<String, Integer>) request.getAttribute("primitiveParameter");

                        Map<String, Integer> scoreComposite = (LinkedHashMap<String, Integer>) request.getAttribute("compositeParameter");

                        // print the information about every category of the list
                        for (String i : score.keySet()) {

                    %>   
                    <tr><td> <%out.println(i); %></td>
                        <td> <%out.println(score.get(i)); %></td>
                        <td> <%out.println(scorePrimitive.get(i)); %></td>
                        <td> <%out.println(scoreComposite.get(i)); %></td>

                    </tr>

                    <%
                        }
                    %>



                </table>
            </center>
        </div>        

    </body>
</html>
