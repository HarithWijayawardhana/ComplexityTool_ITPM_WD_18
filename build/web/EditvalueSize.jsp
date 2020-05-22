<%-- 
    Document   : EditvalueSize
    Created on : May 7, 2020, 9:51:19 PM
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
        <!--		<center>
                                <table border="1">
                                        <tr>
                                                <td align="center"><b>Multiple image upload</b></td>
                                        </tr>
                                        <tr>
                                                <td>
                                                        Specify file : <input name="file" type="file" id="file" multiple>
                                                </td>
                                        </tr>
                                        <tr>
                                                <td align="center">
                                                        <input type="submit" name="Submit" value="Submit Files">
                                                </td>
                                        </tr>
                                </table>
                        </center>-->

    <center>
        <h1>Edit weights according to size factor</h1>
        <form method="post" action="readTextFileToEdit">
            <div class="form-group">
            <table border="0">
                <tr>
                    <td>Keyword: </td>
                    <td><input type="text" name="Keyword" size="50"/></td>
                </tr>
                <tr>
                    <td>Identifier: </td>
                <a href="../src/java/ServletUpload.java"></a>
                    <td><input type="text" name="Identifier" size="50"/></td>
                </tr>
                <tr>
                    <td>Operator: </td>
                    <td><input type="text" name="Operator" size="50"/></td>
                </tr>
                <tr>
                    <td>Numerical value: </td>
                    <td><input type="text" name="Numericalvalue" size="50"/></td>
                </tr>
                <tr>
                    <td>String literal : </td>
                    <td><input type="text" name="Stringliteral" size="50"/></td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input type="submit" value="Save">
                    </td>
                </tr>
            </table>
            </div>
        </form>
    </center>


</body>
</html>
