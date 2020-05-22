
<%@page import="java.util.Map"%>
<%@page import="java.util.LinkedHashMap"%>

<%-- 
    Document   : message
    Created on : May 8, 2020, 10:57:40 AM
    Author     : Rota
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Upload</title>
        <link href="bootstrap\css\bootstrap.min.css" rel="stylesheet">
        <link href="bootstrap\css\bootstrap.css" rel="stylesheet" type="text/css">
        <link href="Custom Css\style.css" rel="stylesheet" type="text/css">
        <link href="Custom Css\button.css" rel="stylesheet" type="text/css">

        <link rel="stylesheet" href="View\bootstrap.min.css">


    </head>
    <body>
    <nav class="navbar navbar-expand-lg navbar-light bg-dark table-responsive-sm">
        <a class="navbar-brand text-white" href="Home.html"><h3>EASY U COMPLEXER</h3></a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo02" aria-controls="navbarTogglerDemo02" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarTogglerDemo02">
            <ul class="navbar-nav ml-auto mt-2 mt-lg-0">






                <li class="nav-item">
                    <a class="nav-link text-white-50 ml-3" href="">Home</a>
                </li>

                <li class="nav-item">
                    <a class="nav-link text-white-50 ml-3 mr-5" href="#">Contact-us</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link text-white-50 ml-3 mr-5" href="editValueInheritance.jsp">Edit Weights</a>
                </li>


            </ul>

        </div>
    </nav>
    <center>
    <div class="container-fluid bg-light">
	   
		  <div class="mb-5">
	<a class="nav-link text-white-50 ml-3 mr-5" href="editValueInheritance.jsp"><button type="button" class="btn btn-outline-success active">Edit Weights</button></a>


	</div>
	
	  
	  </div>
    </center>

    <center class="mt-5">

        <div class="container">
            <table class="table table-bordered table-dark"> 


                <tr>
                    <th scope="col">Count</th>
                    <th scope="col">Class Name</th>
                    <th scope="col">No of direct inheritance</th>
                    <th scope="col">No of indirect inheritance</th>
                    <th scope="col">Total inheritance</th>
                    <th scope="col">Ci</th>



                </tr>


                <%! int count = 0;%>
                <%! int comp = 0;%>
                <%! int fine = 0;%>
                <%! int nod = 0;%>
                <%! int noi = 0;%>




                <%

                    // retrieve your list from the request, with casting 
                    //ArrayList<CodeLine> list = (ArrayList<CodeLine>) request.getAttribute("message");
                    Map<String, Integer> score = (LinkedHashMap<String, Integer>) request.getAttribute("message");
                    // print the information about every category of the list

                    for (String i : score.keySet()) {

                        if (score.get(i) != 0) {
                            count++;
                        }

                    }
                    Map<String, Integer> complexity = (LinkedHashMap<String, Integer>) request.getAttribute("message");
                    // print the information about every category of the list

                    for (String i : score.keySet()) {

                        if (score.get(i) != 0) {
                            comp++;
                        }
                        if (comp == 1) {
                            fine = 1;
                        } else if (comp == 2) {
                            fine = 2;
                        } else if (comp == 3) {
                            fine = 3;
                        } else {
                            fine = 3;
                        }

                    }
                    if (count >= 2) {
                        nod = count - 1;
                        noi = count - nod;
                    } else if (count < 2) {
                        nod = 1;
                        noi = 0;
                    }

                %>   
                <%                    %>

                <tr><td> 1</td>
                    <td>  <%out.println(request.getAttribute("name"));%></td>
                    <td> <%out.println(nod);%></td>
                    <td><%out.println(noi);%></td>
                    <td><%out.println(count);%></td>
                    <td><%out.println(fine);%></td>
                </tr>


            </table>
        </div>
    </center>
</body>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<footer>

    <div class="container-fluid bg-dark mt-5">
        <div class="row">
            <div class="col-12 text-center text-white mt-1 mb-1">

                <!-- Copyright -->
                <div class="footer-copyright text-center py-3">© 2020 Copyright:
                    <a href="https://mdbootstrap.com/"> EasyYouComplexer.com</a>
                </div>
                <!-- Copyright -->

            </div>


        </div>


    </div>


</footer>
</html>