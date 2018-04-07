<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="beans.*"%> // import all of our Java beans
<jsp:useBean id="userInfo" class="beans.UserInfo" scope="request"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h2>
            <%
                int count = 1;
                UserInfo user;
                while((user = userInfo.getUserByNum(count)) != null) {
                    %>
                        Username: <%=user.getId()%><br>
                        Password: <%=user.getPassword()%><br>
                    <%
                    count++;
                }
            %>
        </h2>
    </body>
</html>
