<%-- 
    Document   : EditvalueVariable
    Created on : May 22, 2020, 11:08:45 AM
    Author     : Harith
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
    <center>
        <h1>Edit weights according to Variable factor</h1>
        <form method="post" action="readTextFileToEditVariable">
            <div class="form-group">
                <table border="0">
                    <tr>
                        <td>Global variable : </td>
                        <td><input type="text" name="Keyword" size="50"/></td>
                    </tr>
                    <tr>
                        <td>Local variable: </td>
                    <a href="../src/java/ServletUpload.java"></a>
                    <td><input type="text" name="Identifier" size="50"/></td>
                    </tr>
                    <tr>
                        <td>Primitive data type variable: </td>
                        <td><input type="text" name="Operator" size="50"/></td>
                    </tr>
                    <tr>
                        <td>Composite data type variable: </td>
                        <td><input type="text" name="Numericalvalue" size="50"/></td>
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
