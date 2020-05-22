<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.LinkedHashMap"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>

        <link rel="stylesheet" href="View\bootstrap.min.css">

        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Upload</title>
    </head>
    <body>


        <div class="container-fluid bg-light">

            <div class="mb-5 mt-5">
                <button type="button" class="btn btn-outline-primary active"><a href="sizeVariable.jsp">SIZES </a></button>
                <button type="button" class="btn btn-outline-primary">METHODS</button>
                <button type="button" class="btn btn-outline-primary">VARIABLES</button>
                <a href="EditvalueSize.jsp"><button type="button" class="btn btn-danger">Edit weights</button></a>

            </div>


        </div>
        
        <div  class="col-6 align-self-center" style="text-align:center;">

        <center>


            <table border="2px" class="table table-dark"> 

                <tr>
                    <th>Code</th>
                    <th>nkw</th>
                    <th>nid</th>
                    <th>nop</th>
                    <th>nnv</th>
                    <th>nsl</th>
                    <th>Cs</th>
                </tr>

                <%
                    // retrieve your list from the request, with casting 
                    //ArrayList<CodeLine> list = (ArrayList<CodeLine>) request.getAttribute("message");
                    Map<String, Integer> score = (LinkedHashMap<String, Integer>) request.getAttribute("message");

                    // retrieve your list from the request, with casting 
                    Map<String, Integer> operators = (LinkedHashMap<String, Integer>) request.getAttribute("operators");

                    Map<String, Integer> identifiers = (LinkedHashMap<String, Integer>) request.getAttribute("identifiers");

                    Map<String, Integer> numerics = (LinkedHashMap<String, Integer>) request.getAttribute("numerics");

                    Map<String, Integer> stringLiterals = (LinkedHashMap<String, Integer>) request.getAttribute("strings");

                    int c = 0;

                    // print the information about every category of the list
                    for (String i : score.keySet()) {

                %>   
                <tr><td> <%out.println(i); %></td>
                    <td> <%out.println(score.get(i)); %></td>
                    <td> <%out.println(identifiers.get(i)); %></td>
                    <td> <%out.println(operators.get(i)); %></td>
                    <td> <%out.println(numerics.get(i)); %></td>
                    <td> <%out.println(stringLiterals.get(i)); %></td>
                    <td> <%out.println(score.get(i) + operators.get(i) + identifiers.get(i) + numerics.get(i) + stringLiterals.get(i)); %></td>

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