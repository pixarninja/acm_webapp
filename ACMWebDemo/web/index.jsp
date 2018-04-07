<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ACM Demo</title>
    </head>
    <body>
        <%
            if(request.getAttribute("error") != null) {
                Exception ex = (Exception)request.getAttribute("error");
                %><%=ex.toString()%><%
            }
        %>
        <h1>NMT ACM Login Page</h1>
            <h3>Use the space below to login to this site!</h3>
            <br>
            <p>
                <form action="UserServlet" method="POST">
                    <input type="hidden" name="action" value="login">
                    Username: <input type="text" name="username">
                    <br><br>
                    Password: <input type="text" name="password">
                    <br><br>
                    <button type="submit">Submit</button>
                </form>
            </p>
            <br>
            <h3>Not yet registered?</h3>
            <p>No problem! Use <a href="registration.jsp">this link</a> to go to the registration page.</p>
    </body>
</html>