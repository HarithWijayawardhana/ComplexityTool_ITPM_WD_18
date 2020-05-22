<%-- 
    Document   : sizeVariable
    Created on : Apr 23, 2020, 6:04:14 PM
    Author     : Harith
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>

        <link rel="stylesheet" href="View\bootstrap.min.css">

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <!-- body code goes here -->
        <div class="container-fluid bg-light">

            <div class="mb-5 mt-5">
                <button type="button" class="btn btn-outline-primary active"><a href="sizeVariable.jsp">SIZE</a></button>
                <button type="button" class="btn btn-outline-primary"><a href="methods.jsp">METHODS</a></button>
                <button type="button" class="btn btn-outline-primary"><a href="variables.jsp">VARIABLES</a></button>
 
            </div>


        </div>

        <div>
            <center>
                <h1>Size</h1>
                <h2>File Upload</h2>
                <form method="post" action="ServletUpload"
                      enctype="multipart/form-data">


                    Select file to upload: <input type="file"  name="file" size="60" /><br />
                    <br /> <input type="submit" value="Upload" class="btn btn-primary text-center" />
                </form>
            </center>
        </div>















        <!-- jQuery (necessary for Bootstrap's JavaScript plugins) --> 
        <script src="../../ASL/pages/js/jquery-3.3.1.min.js"></script>

        <!-- Include all compiled plugins (below), or include individual files as needed -->
        <script src="../../ASL/pages/js/popper.min.js"></script> 
        <script src="../../ASL/pages/js/bootstrap-4.3.1.js"></script>
    </body>
</html>
