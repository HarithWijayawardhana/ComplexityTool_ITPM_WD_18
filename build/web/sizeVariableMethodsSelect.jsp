<%-- 
    Document   : sizeVariableMethodsSelect
    Created on : May 9, 2020, 11:22:56 PM
    Author     : Harith
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <link rel="stylesheet" href="View\bootstrap.min.css">
        
                <link href="bootstrap\css\bootstrap.min.css" rel="stylesheet">
        <link href="bootstrap\css\bootstrap.css" rel="stylesheet" type="text/css">
        <link href="Custom Css\style.css" rel="stylesheet" type="text/css">
        <link href="Custom Css\button.css" rel="stylesheet" type="text/css">

        <link rel="stylesheet" href="View\bootstrap.min.css">
        
        <title>JSP Page</title>
    </head>
    <body>
         <div class="container-fluid">
            <nav class="navbar navbar-light bg-light justify-content-between">
                <a class="navbar-brand text-danger"><h3>EASY YOU COMPLEXER</h3></a>

            </nav>
        </div>
        <div class="containerhead">
            <img src="Img\45596.jpg" alt="" style="height:400px" width="100%">
            <div class="container1">
                <div class="centered">
                    <span class="text1">Select your Metric</span>



                </div>
            </div>

        </div>
        
    
        
        <div class="container mt-5">
            <div class="row mt-3">
                <div class="col-6">
                    <div class="text-center">
                        <h5 class="lead btn-outline-success">Size</h5>
                        <p><small>Measuring the time complexity of the programme due to size</small></p>
                        <button type="button" class="btn btn-success text-center" onclick="location.href = 'selectFileTypeSize.jsp'">Check Complexity</button>
                    </div>
                </div>


                <div class="col-6">
                    <div class="text-center">
                        <h5 class="lead btn-outline-success">Variable</h5>
                        <p><small>Measuring the time complexity of the programme due to Variable</small></p>
                        <button type="button" class="btn btn-success text-center" onclick="location.href = 'selectFileTypeVariable.jsp'">Check Complexity</button>
                    </div>
                </div>


            </div>
            <div class="row mt-5 mb-5">
                <div class="col-6">
                    <div class="text-center">
                        <h5 class="lead btn-outline-success">Methods</h5>
                        <p><small>Measuring the time complexity of the programme due to Methods</small></p>
                        <button type="button" class="btn btn-success text-center" onclick="location.href = 'selectFileTypeMethod.jsp'">Check Complexity</button>
                    </div>

                </div>




            </div>

        </div>
    </body>
</html>
