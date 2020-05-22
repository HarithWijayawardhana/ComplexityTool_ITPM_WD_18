<%-- 
    Document   : pass
    Created on : Apr 24, 2020, 12:25:23 AM
    Author     : MUNZIR
--%>

<%@page import="java.util.Map"%>
<%@page import="java.util.LinkedHashMap"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
    <head>
    <meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Control Structures</title>
    <!-- Bootstrap -->
        <link href="bootstrap\css\bootstrap.min.css" rel="stylesheet">
        <link href="bootstrap\css\bootstrap.css" rel="stylesheet" type="text/css">
        <link href="Custom Css\style.css" rel="stylesheet" type="text/css">
        <link href="Custom Css\button.css" rel="stylesheet" type="text/css">

        <link rel="stylesheet" href="View\bootstrap.min.css">
  </head>
    <body>
       
        
        <div class="container-fluid bg-light">
	  
		  <div class="mb-5 mt-5">
	<button type="button" class="btn btn-outline-primary active">CONTROL STRUCTURES</button>


	</div>
	
	  
	  </div>
	  

	  </div>
        
        
        
        
        
        <div class="container mt-5 mb-5">
        <table class="table table-bordered table-dark">
            
            
            <tr>
               <th>Program statements</th>
               <th>Wtcs</th>
               <th>NC</th>
               <th>Ccspps</th>
               <th>Ccs</th>              
           </tr>
            
            <%
                    // retrieve your list from the request, with casting 
                    //ArrayList<CodeLine> list = (ArrayList<CodeLine>) request.getAttribute("message");
                    Map<String, Integer> score = (LinkedHashMap<String, Integer>) request.getAttribute("pass");
                    // print the information about every category of the list
                    for (String i : score.keySet()) {
             %>
           
               
           <tr>
                <td><%out.println(i);%></td>
                <td><%out.println(score.get(i));%></td>
                <td><%out.println(score.get(i));%></td>
                <td><%out.println(score.get(i));%></td>
                <td><%out.println(score.get(i));%></td>
            </tr>
             <%
                    }
             %>
            
            
        </table> 
             
       </div>      
       <!-- jQuery (necessary for Bootstrap's JavaScript plugins) --> 
	<script src="../../ASL/pages/js/jquery-3.3.1.min.js"></script>

	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="../../ASL/pages/js/popper.min.js"></script> 
	<script src="../../ASL/pages/js/bootstrap-4.3.1.js"></script>
  </body>
			<div class="container-fluid bg-dark">
	  <div class="row">
		<div class="col-12 text-center text-white mt-3 mb-3">
			
		<!-- Copyright -->
  <div class="footer-copyright text-center py-3">© 2020 Copyright:
    <a href="https://mdbootstrap.com/"> EasyYouComplexer.com</a>
  </div>
  <!-- Copyright -->

		  </div>
		
		
		</div>
	  
	  
	  </div> 
    
</html>
