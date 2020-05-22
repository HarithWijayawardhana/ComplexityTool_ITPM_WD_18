<%-- 
    Document   : editValueSizeZip
    Created on : May 21, 2020, 8:11:28 PM
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
        <h1>Edit weights when according to size factor</h1>
        <form method="post" action="zipFileRead">
            <div class="form-group">
                <table border="0">
                    <tr>
                        <td>Keyword: </td>
                        <td><input type="text" name="Keyword" size="50"/></td>
                    </tr>
                    <tr>
                        <td>Identifier: </td>
                    <a href="../src/java/zipFile.java"></a>
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
