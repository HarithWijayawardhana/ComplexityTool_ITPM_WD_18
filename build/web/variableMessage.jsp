<%-- 
    Document   : variableMessage
    Created on : Apr 23, 2020, 8:01:32 PM
    Author     : Harith
--%>

<%@page import="java.util.ArrayList"%>
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
                <a href="EditvalueVariableZip.jsp"><button type="button" class="btn btn-danger">Edit Weights</button></a>

            </div>


        </div>


        <div  class="col-6 align-self-center" style="text-align:center;">


            <center>




                <table border="2px" class="table table-dark"> 

                    <tr>
                        <th>Code</th>
                        <th>Wvs</th>
                        <th>Npdtv</th>
                        <th>Ncdtv</th>
                        <th>Cv</th>

                    </tr>




                    <%
                        // retrieve your list from the request, with casting 
                        //ArrayList<CodeLine> list = (ArrayList<CodeLine>) request.getAttribute("message");
                        Map<String, Integer> score = (LinkedHashMap<String, Integer>) request.getAttribute("variable");

                        // retrieve your list from the request, with casting 
                        Map<String, Integer> primitive = (LinkedHashMap<String, Integer>) request.getAttribute("primitive");

                        Map<String, Integer> composite = (LinkedHashMap<String, Integer>) request.getAttribute("composite");
                        //
                        //                    ArrayList<Integer> identifiers = (ArrayList<Integer>) request.getAttribute("identifiers");
                        //
                        //                    ArrayList<Integer> stringLiterals = (ArrayList<Integer>) request.getAttribute("strings");

                        int c = 0;

                        // print the information about every category of the list
                        for (String i : score.keySet()) {

                    %>   
                    <tr><td> <%out.println(i); %></td>
                        <td> <%out.println(score.get(i)); %></td>
                        <td> <%out.println(primitive.get(i)); %></td>
                        <td> <%out.println(composite.get(i)); %></td>
                        <td> <%out.println(score.get(i) + composite.get(i) + primitive.get(i)); %></td>

                        <%
                            c++;
                        %>

                    </tr>

                    <%
                        }
                    %>



                </table>
            </center>
        </div>         

    </body>
</html>
