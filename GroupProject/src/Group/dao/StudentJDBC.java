/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Group.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author jasmine
 */
public class StudentJDBC {

    private Connection con;
    private final Map<String, String> students = new HashMap<String, String>();

    public StudentJDBC() {
        String url = "jdbc:derby://localhost:1527/ExamDB";
        try {
            con = DriverManager.getConnection(url);
        } catch (SQLException se) {
            System.out.println("Error obtaining connection with the database: " + se);
            System.exit(-1);
        }
    }
    
    public Map getStudentList() throws Exception {
        try (Statement stmt = con.createStatement()) {
            String query = "SELECT * FROM STUDENT";
            ResultSet rs = stmt.executeQuery(query);
            if (!rs.next()) {
                return null;
            }
            while(rs.next()){
                String username = rs.getString("USERNAME");        
                String psw = rs.getString("PASSWORD");
                students.put(username, psw);
            }
            return students;
        } catch (SQLException se) {
            //se.printStackTrace();
            throw new Exception("Error getting difficulty level information", se);
        }
    }
}
