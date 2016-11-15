/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package groupproject;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Alexis
 */
public class QuestionMain {
    
    private static String dbURL = "jdbc:derby://localhost:1527/QuestionDB;create=true;user=me;password=mine";
    private static String tableName = "question";
    //use the network Java DB mode (running on localhost) to implement this database.
    static String driver = "org.apache.derby.jdbc.ClientDriver";

    // jdbc Connection
    private static Connection conn = null;
    private static Statement stmt = null;
    static ArrayList<Statement> statements = new ArrayList<Statement>(); // list of Statements, PreparedStatements

    /**
     *purpose: check whether table exists.
     * if the table exist, drop it
     * else, create table and columns.
     * @param tableName
     * @throws SQLException
     */
    public void isTableExit(String tableName) throws SQLException {
        if (conn != null) {
            DatabaseMetaData meta = conn.getMetaData();
            ResultSet res = meta.getTables(null, null, tableName.toUpperCase(), null);
            if (res.next()) {
                String drop = "drop table sailor";
                stmt.execute(drop);
            }
            String createTable = "CREATE TABLE \"QUESTION\" ("
                    + "\"QUESTIONTYPE\" VARCHAR(10), "
                    + "\"DIFFICULTYLEVEL\" VARCHAR(10),"
                    + "\"DESCRIPTION\" VARCHAR(300),"
                    + "\"ANSWER1\" VARCHAR(200),"
                    + "\"VALIDITY1\" VARCHAR(20),"
                    + "\"ANSWER2\" VARCHAR(200),"
                    + "\"VALIDITY2\" VARCHAR(20),"
                    + "\"ANSWER3\" VARCHAR(200),"
                    + "\"VALIDITY3\" VARCHAR(20),"
                    + "\"ANSWER4\" VARCHAR(200),"
                    + "\"VALIDITY4\" VARCHAR(20))";
            stmt.executeUpdate(createTable);
            statements.add(stmt);
        }
    }
    
    public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
        ArrayList<QuestionInfo> questionList=new ArrayList();
        ReadQuestion readQuestion=new ReadQuestion();
        //??how to define path of question.csv file
        questionList=readQuestion.readQuestion("question.txt");
        
        //access javaDB
        Class.forName(driver);
        conn = DriverManager.getConnection(dbURL);
        stmt = conn.createStatement();
        
        QuestionMain task=new QuestionMain();
        task.isTableExit(tableName);
        
        PreparedStatement psInsert;
        PreparedStatement psUpdate;

        //use preparedStatement to insert values into quiz table.
        psInsert = conn.prepareStatement("insert into sailor values (?,?,?,?,?,?,?,?,?,?,?)");

        for (int i = 0; i < questionList.size(); i++) {
            psInsert.setString(1, questionList.get(i).getQuestionType());
            psInsert.setString(2, questionList.get(i).getDifficultyLevel());
            psInsert.setString(3, questionList.get(i).getDescription());
            psInsert.setString(4, questionList.get(i).getAnswer1());
            psInsert.setString(4, questionList.get(i).getValidity1());
            psInsert.setString(4, questionList.get(i).getAnswer2());
            psInsert.setString(4, questionList.get(i).getValidity2());
            psInsert.setString(4, questionList.get(i).getAnswer3());
            psInsert.setString(4, questionList.get(i).getValidity3());
            psInsert.setString(4, questionList.get(i).getAnswer4());
            psInsert.setString(4, questionList.get(i).getValidity4());
            // add batch
            psInsert.addBatch();
        }
         // batch processing
        psInsert.executeBatch();

        statements.add(psInsert);
    }
   
}
