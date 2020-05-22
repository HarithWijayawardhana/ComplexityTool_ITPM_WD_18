<%-- 
    Document   : index
    Created on : Apr 21, 2020, 9:43:18 PM
    Author     : Harith
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>

        <link rel="stylesheet" href="View\bootstrap.min.css">

        <link href="bootstrap\css\bootstrap.min.css" rel="stylesheet">
        <link href="bootstrap\css\bootstrap.css" rel="stylesheet" type="text/css">
        <link href="Custom Css\style.css" rel="stylesheet" type="text/css">
        <link href="Custom Css\button.css" rel="stylesheet" type="text/css">

        <link rel="stylesheet" href="View\bootstrap.min.css">

        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>File Upload</title>
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

        <div>



        </div>




        <div class="container mt-5">
            <div class="row mt-3">
                <div class="col-6">
                    <div class="text-center">
                        <h5 class="lead btn-outline-success">Size,Variables,Methods</h5>
                        <p><small>Measuring the time complexity of the programme due to size,variables and methods</small></p>
                        <button type="button" class="btn btn-success text-center" onclick="location.href = 'sizeVariableMethodsSelect.jsp'">Check Complexity</button>
                    </div>
                </div>


                <div class="col-6">
                    <div class="text-center">
                        <h5 class="lead btn-outline-success">Inheritance</h5>
                        <p><small>Measuring the time complexity of the programme due to Inheritance</small></p>
                        <button type="button" class="btn btn-success text-center" onclick="location.href = 'inheritancefiletypeselect.jsp'">Check Complexity</button>
                    </div>
                </div>


            </div>
            <div class="row mt-5 mb-5">
                <div class="col-6">
                    <div class="text-center">
                        <h5 class="lead btn-outline-success">Coupling</h5>
                        <p><small>Measuring the time complexity of the programme due to Coupling</small></p>
                        <button type="button" class="btn btn-success text-center">Check Complexity</button>
                    </div>

                </div>


                <div class="col-6">

                    <div class="text-center">
                        <h5 class="lead btn-outline-success">Control Structure</h5>
                        <p><small>Measuring the time complexity of the programme due to Control Structure</small></p>
                        <button type="button" class="btn btn-success text-center" onclick="location.href = 'index2222.jsp'">Check Complexity</button>

                    </div>
                </div>

            </div>

        </div>
        <div class="text-center mb-5">
            <h5 class="lead btn-outline-success">Total Complexity</h5>
            <p><small>Measuring the time complexity of the programme due to all factors and get the total complexity easily</small></p>
            <button type="button" class="btn btn-success text-center">Check Complexity</button>

        </div>


        <div class="container-fluid">


        </div>
        <!-- body code goes here -->


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


</body>
</html>
