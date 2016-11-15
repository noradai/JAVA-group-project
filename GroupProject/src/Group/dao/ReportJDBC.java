/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Group.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.Timestamp;
import java.time.LocalDate;

/**
 * JDBC
 *
 * @author jasmine
 */
public class ReportJDBC{

    private Connection con;
//
//    ReportJDBC() {
//        String url = "jdbc:derby://localhost:1527/ExamDB";
//        try {
//            con = DriverManager.getConnection(url);
//        } catch (SQLException se) {
//            System.out.println("Error obtaining connection with the database: " + se);
//            System.exit(-1);
//        }
//    }

    public double getPerformanceByLevel(int level) throws Exception {
        String query = null;
        try (Statement stmt = con.createStatement()) {
             switch (level) {
                case 1:
                    query = "SELECT AVG(RATE) AS AVERAGE FROM RESULT WHERE DIFFICULTYLEVEL = 'H'";
                    break;
                case 2:
                    query ="SELECT AVG(RATE) AS AVERAGE FROM RESULT WHERE DIFFICULTYLEVEL = 'M'";
                    break;
                case 3:
                    query = "SELECT AVG(RATE) AS AVERAGE FROM RESULT WHERE DIFFICULTYLEVEL = 'H'";
                    break;
                case 4:
                    query = "SELECT AVG(RATE) AS AVERAGE FROM RESULT WHERE DIFFICULTYLEVEL = 'X'";
                    break;
            }
            ResultSet rs = stmt.executeQuery(query);
            if (!rs.next()) {
                return 0;
            }
            double average = rs.getDouble("AVERAGE");
            return average;
        } catch (SQLException se) {
            //se.printStackTrace();
            throw new Exception("Error getting difficulty level information", se);
        }
    }

    public int getCountByTime(int i) throws Exception {
        try (Statement stmt = con.createStatement()) {
            LocalDate currentDate = LocalDate.now();
            LocalDate time = null;
            String query = null;
            int count = 0;
            switch (i) {
                case 1:
                    time = currentDate.minusMonths(1);
                    query = "SELECT COUNT(*) AS NUMBER FROM RESULT where time between DATE('" + time + "') AND DATE('" + currentDate + "')";
                    break;
                case 2:
                    time = currentDate.minusMonths(3);
                    query = "SELECT COUNT(*) AS NUMBER FROM RESULT where time between DATE('" + time + "') AND DATE('" + currentDate + "')";
                    break;
                case 3:
                    time = currentDate.minusYears(1);
                    query = "SELECT COUNT(*) AS NUMBER FROM RESULT where time between DATE('" + time + "') AND DATE('" + currentDate + "')";
                    break;
            }
            ResultSet rs = stmt.executeQuery(query);
            if (!rs.next()) {
                return 0;
            }
            count = rs.getInt("NUMBER");
            return count;
        } catch (SQLException se) {
            //se.printStackTrace();
            throw new Exception("Error getting test number information", se);
        }
    }

    public double getAverageByTime(int i) throws Exception {
        try (Statement stmt = con.createStatement()) {
            LocalDate currentDate = LocalDate.now();
            LocalDate time = null;
            if (i == 1) {
                time = currentDate.minusMonths(1);
            }
            if (i == 2) {
                time = currentDate.minusMonths(3);
            }
            if (i == 3) {
                time = currentDate.minusYears(1);
            }
            String query = "SELECT AVG(RATE) AS AVERAGE FROM RESULT WHERE time between DATE('" + time + "') AND DATE('" + currentDate + "')";
            ResultSet rs = stmt.executeQuery(query);
            if (!rs.next()) {
                return 0;
            }
            double average = rs.getDouble("AVERAGE");
            return average;
        } catch (SQLException se) {
            //se.printStackTrace();
            throw new Exception("Error getting average score information", se);
        }
    }

    public List getStudentList() throws Exception {
        List<String> student = new ArrayList();
        try (Statement stmt = con.createStatement()) {
            String query = "SELECT STUDENT FROM RESULT ";
            ResultSet rs = stmt.executeQuery(query);
            if (!rs.next()) {
                return null;
            }
            while (rs.next()) {
                student.add(rs.getString("STUDENT"));
            }
            return student;
        } catch (SQLException se) {
            //se.printStackTrace();
            throw new Exception("Error getting student name list score information", se);
        }
    }

    public int getPassingCount(String student, int i) throws Exception {
        try (Statement stmt = con.createStatement()) {
            LocalDate currentDate = LocalDate.now();
            LocalDate time = null;
            if (i == 1) {
                time = currentDate.minusMonths(1);
            }
            if (i == 2) {
                time = currentDate.minusMonths(3);
            }
            if (i == 3) {
                time = currentDate.minusYears(1);
            }
            String query = "select count(*) AS NUMBER from result where student = '" + student + "' and rate > 0.6 and time between DATE('" + time + "') AND DATE('" + currentDate + "')";
            ResultSet rs = stmt.executeQuery(query);
            if (!rs.next()) {
                return 0;
            }
            int count = rs.getInt("NUMBER");
            return count;
        } catch (SQLException se) {
            //se.printStackTrace();
            throw new Exception("Error getting student name list score information", se);
        }
    }

    public int getFailingCount(String student, int i) throws Exception {
        try (Statement stmt = con.createStatement()) {
            LocalDate currentDate = LocalDate.now();
            LocalDate time = null;
            if (i == 1) {
                time = currentDate.minusMonths(1);
            }
            if (i == 2) {
                time = currentDate.minusMonths(3);
            }
            if (i == 3) {
                time = currentDate.minusYears(1);
            }
            String query = "select count(*) AS NUMBER from result where student = '" + student + "' and rate < 0.6 and time between DATE('" + time + "') AND DATE('" + currentDate + "')";
            ResultSet rs = stmt.executeQuery(query);
            if (!rs.next()) {
                return 0;
            }
            int count = rs.getInt("NUMBER");
            return count;
        } catch (SQLException se) {
            //se.printStackTrace();
            throw new Exception("Error getting student name list score information", se);
        }
    }
}
