package beans; // for organization purposes

import java.io.Serializable;
import java.sql.*;
import java.util.LinkedHashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserInfo implements Serializable{
    
    private static LinkedHashMap beansById = new LinkedHashMap(); // map of MyBean classes
    private Connection connection;
    
    /* DB variables (columns in the DB) */
    private String id; // for simplicity, the primary key is called "id"
    private String password;
    
    public UserInfo() { // an empty constructor is needed if this class is a Java bean
        try {
            beansById = new LinkedHashMap();
            String driver = "com.mysql.jdbc.Driver"; // the MySQL Driver you're using
            Class.forName(driver); // open the driver
            String dbURL = "jdbc:mysql://phpmyadminacm.cjuk6hbfhzmg.us-east-1.rds.amazonaws.com:3306/ACMwebapp";
            String user = "phpMyAdminACM";
            String pass = "NMTACMroot";
            connection = DriverManager.getConnection(dbURL, user, pass); // open the connection
        
            Statement statement = connection.createStatement();
            int num = 1;

            String query = "SELECT * FROM `Users`"; // SELECT all data from a table in the DB
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) { // must call rs.next() at least once, to grab the first row of data
                String id = rs.getString("id"); // create local DB variables and initialize them from data in the DB
                String password = rs.getString("password");
                
                beansById.put(num, new UserInfo(id, password)); // store the data saved from the DB variables in a constructor
                num++;
            }
            rs.close();
            connection.close();
        } catch (ClassNotFoundException ex) { // catch Driver errors
            Logger.getLogger(UserInfo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) { // catch SQL errors
            Logger.getLogger(UserInfo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public UserInfo(String id, String password) { // constructor for storing a MyBean object
        this.id = id;
        this.password = password;
    }
    
    public static UserInfo getUserByNum(int num) {
        return (UserInfo)beansById.get(num);
    }
    
    public String getId() {
        return id;
    }
    
    public String getPassword() {
        return password;
    }
}