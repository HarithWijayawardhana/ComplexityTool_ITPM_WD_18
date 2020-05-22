<%-- 
    Document   : variables
    Created on : Apr 23, 2020, 6:55:54 PM
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
                <button type="button" class="btn btn-outline-primary active">SIZES</button>
                <button type="button" class="btn btn-outline-primary">METHODS</button>
                <button type="button" class="btn btn-outline-primary">VARIABLES</button>

            </div>


        </div>

        
        <div>
            <center>
                <h1>Variable</h1>
                <h2>File Upload</h2>
                <form method="post" action="variables"
                      enctype="multipart/form-data">


                    Select file to upload: <input type="file"  name="file" size="60" /><br />
                    <br /> <input type="submit" value="Upload" class="btn btn-primary text-center" />
                </form>
            </center>
        </div>
        
        
    </body>  
</html>
