<%-- 
    Document   : multipleFilesUpload
    Created on : May 9, 2020, 9:30:03 PM
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
	<form action="UploadServlet" method="post" enctype="multipart/form-data" name="form1" id="form1">
		<center>
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
		</center>
	</form>
</body>
</html>
