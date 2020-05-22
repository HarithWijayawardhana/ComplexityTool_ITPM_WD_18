<%-- 
    Document   : zipFileMessageMethod
    Created on : May 22, 2020, 5:27:18 PM
    Author     : Harith
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.LinkedHashMap"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>

        <link rel="stylesheet" href="View\bootstrap.min.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
                        <th>Files You have uploaded</th>

                    </tr>

                    <%
                        // retrieve your list from the request, with casting 
                        //ArrayList<CodeLine> list = (ArrayList<CodeLine>) request.getAttribute("message");
                        ArrayList<String> score2 = (ArrayList<String>) request.getAttribute("Hello");

                        // retrieve your list from the request, with casting 
                        int k = 0;

                        // print the information about every category of the list
                        for (String i : score2) {

                    %>   
                    <tr><td><input type="submit" name="b1"value="<%=k%>" id="" action="zipFileRead"><button><%out.println(i); %></button></td>

                    </tr>

                    <%
                            k++;

                        }
                    %>




                </table>


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
