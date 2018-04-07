/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Wesley
 */
@WebServlet(urlPatterns = {"/UserServlet"})
public class UserServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UserServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UserServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        String action = request.getParameter("action"); // used to know how to treat the request
        String username = "";
        if(request.getParameter("username") != null) {
            username = request.getParameter("username"); // example for retrieving a variable from JSP
        }
        String password = "";
        if(request.getParameter("password") != null) {
            password = request.getParameter("password"); // example for retrieving a variable from JSP
        }
        String url = "/";
        if(action.equals("login")) {
            try {
                String driver = "com.mysql.jdbc.Driver"; // the MySQL Driver you're using
                Class.forName(driver); // open the driver
                String dbURL = "jdbc:mysql://phpmyadminacm.cjuk6hbfhzmg.us-east-1.rds.amazonaws.com:3306/ACMwebapp";
                String user = "phpMyAdminACM";
                String pass = "NMTACMroot";
                Connection connection = DriverManager.getConnection(dbURL, user, pass);
                
                String query = "SELECT * FROM `Users` WHERE id = ?";
                PreparedStatement ps = connection.prepareStatement(query);
                ps.setString(1, username); // can also use setInt, setString, etc.
                ResultSet rs = ps.executeQuery();
                
                rs.next();
                if(rs.getString("password").equals(password)) {
                    url = "/homepage.jsp";
                }
                else {
                    url = "/index.jsp";
                }
            } catch (ClassNotFoundException ex) {
                url = "/index.jsp";
                request.setAttribute("error", ex);
                Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                url = "/index.jsp";
                request.setAttribute("error", ex);
                Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(action.equals("register")) {
            try {
                String driver = "com.mysql.jdbc.Driver"; // the MySQL Driver you're using
                Class.forName(driver); // open the driver
                String dbURL = "jdbc:mysql://phpmyadminacm.cjuk6hbfhzmg.us-east-1.rds.amazonaws.com:3306/ACMwebapp";
                String user = "phpMyAdminACM";
                String pass = "NMTACMroot";
                Connection connection = DriverManager.getConnection(dbURL, user, pass);
                
                String query = "INSERT INTO `Users` (`id`, `password`) VALUES (?, ?);";
                PreparedStatement ps = connection.prepareStatement(query);
                ps.setString(1, username); // can also use setInt, setString, etc.
                ps.setString(2, password); // can also use setInt, setString, etc.
                ps.execute();
                ps.close();

                url = "/registration.jsp"; // the JSP page to go to, given there were no errors
            } catch (ClassNotFoundException ex) {
                url = "/index.jsp";
                request.setAttribute("error", ex);
                Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                url = "/index.jsp";
                request.setAttribute("error", ex);
                Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        /* take us to the specified URL */
        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
