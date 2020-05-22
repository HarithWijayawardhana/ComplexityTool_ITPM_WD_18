<%-- 
    Document   : methods
    Created on : Apr 23, 2020, 9:49:23 PM
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
        <div class="container-fluid bg-light">

            <div class="mb-5 mt-5">
                <button type="button" class="btn btn-outline-primary active"><a href="sizeVariable.jsp">SIZE</a></button>
                <button type="button" class="btn btn-outline-primary"><a href="methods.jsp">METHODS</a></button>
                <button type="button" class="btn btn-outline-primary"><a href="variables.jsp">VARIABLES</a></button>
                <a href="editValueSize.jsp"><button type="button" class="btn btn-danger">Edit weights</button></a>

            </div>


        </div>

        <div>
            <center>
                <h1>Methods</h1>
                <h2>Upload zip File</h2>
                <form method="post" action="unzipFileMethod"
                      enctype="multipart/form-data">
                    Select file to upload: <input type="file"  name="file" size="60" /><br />
                    <br /> <input type="submit" value="Upload" class="btn btn-primary text-center" />
                </form>
            </center>
        </div>


    </body>
</html>
