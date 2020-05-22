<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>

        <link href="bootstrap\css\bootstrap.min.css" rel="stylesheet">
        <link href="bootstrap\css\bootstrap.css" rel="stylesheet" type="text/css">
        <link href="Custom Css\style.css" rel="stylesheet" type="text/css">
        <link href="Custom Css\button.css" rel="stylesheet" type="text/css">

        <link rel="stylesheet" href="View\bootstrap.min.css">

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>CS</title>
    </head>
    <body>
    <center>
        <h1>control structures</h1>  
        <h2>File Upload</h2>
        <form method="post" action="Process" enctype="multipart/form-data">
            Select file to upload: <input type="file" name="file" size="60"/><br/>
            <br/> <input type="submit" value="Upload"/>
        </form>
    </center>
</body>
</html>
