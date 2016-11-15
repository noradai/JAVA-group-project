/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Group.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author jasmine
 */
public class JDBC {

    private Connection con = null;
    
    JDBC() {
        String url = "jdbc:derby://localhost:1527/ExamDB";
        try {
            con = DriverManager.getConnection(url);
        } catch (SQLException se) {
            System.out.println("Error obtaining connection with the database: " + se);
            System.exit(-1);
        }
    }
}
