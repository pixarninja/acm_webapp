<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ACM Demo</title>
    </head>
    <body>
        <h1>NMT ACM Registration Page</h1>
		<h3>Use the space below to register for this site!</h3>
		<br>
		<p>
			<form action="UserServlet" method="POST">
                            <input type="hidden" name="action" value="register">
				Username: <input type="text" name="username">
				<br><br>
				Password: <input type="text" name="password">
				<br><br>
				<button type="submit">Submit</button>
			</form>
		</p>
		<br>
		<p>Take me <a href="index.jsp">back</a>!</p>
    </body>
</html>
