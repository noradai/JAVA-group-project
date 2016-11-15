package Group.model;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author yanyandai
 */
public class Student {

    private String userName;
    private String Password;

    public Student() {

    }

    public void getQuiz() {

    }

    // retur type? Or should it be here? or in quiz?
    public void setResult() {

    }

    public void logIn() {

    }

    public void logOut() {

    }

    // add instructor info from CSV files
    public void addInfo() {
        try {
            String connUrl = "jdbc:derby://localhost:1527/studentDB";
            java.sql.Connection conn = DriverManager.getConnection(connUrl);

            // Open Question.csv
            FileReader f = new FileReader("../Assignment_5_2/src/Employee.csv");
            BufferedReader brdFile = new BufferedReader(f);
            String strLine = null;

            //Read Column by Column
            while ((strLine = brdFile.readLine()) != null) {

                String[] array = strLine.split(",");
                String qryInsert = "insert into SAILOR values(?,?,?,?)";
                PreparedStatement pstmt = conn.prepareStatement(qryInsert);

                pstmt.setString(1, array[0]);
                pstmt.setString(2, array[1]);
                pstmt.setString(3, array[2]);
                int i = Integer.valueOf(array[3]);
                pstmt.setInt(4, i);

                pstmt.execute();
            };
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the Password
     */
    public String getPassword() {
        return Password;
    }

    /**
     * @param Password the Password to set
     */
    public void setPassword(String Password) {
        this.Password = Password;
    }

}
