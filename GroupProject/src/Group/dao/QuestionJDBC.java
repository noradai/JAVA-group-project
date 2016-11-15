/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Group.dao;

import Group.model.QuestionInfo;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author 45357
 */
public class QuestionJDBC {
        private Connection con;
        
    public static void main(String[] args) throws Exception{
        QuestionJDBC q=new QuestionJDBC();
        q.setConnection();
        
        
        ArrayList<QuestionInfo> a=new ArrayList<QuestionInfo>();       
        a=readQuestion("question.txt");
        if(q.hasTable())q.createTable();
        q.insertQuestionToDatabase(a);
        q.dropConnection();
    }
    public void setConnection() throws Exception{
        con=DriverManager.getConnection("jdbc:derby://localhost:1527/ExamDB;create=true");       
    }

    public void dropConnection() throws Exception{
        con.close();
    }
    
    public void createTable()throws Exception{
            Statement stmt = con.createStatement();
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
            stmt.execute(createTable);        
    }
    
    public boolean hasTable()throws Exception{
        boolean result=false;
        DatabaseMetaData meta=con.getMetaData();
        ResultSet set =meta.getTables(null, null, "question", null);
        while(set.next()){
            result=true;
        }    
        return result;
    }

    public void insertQuestionToDatabase(ArrayList<QuestionInfo> questionList) throws Exception{                     
        PreparedStatement psInsert;
        
        //use preparedStatement to insert values into quiz table.
        psInsert = con.prepareStatement("insert into question values (?,?,?,?,?,?,?,?,?,?,?)");

        for (int i = 0; i < questionList.size(); i++) {
            psInsert.setString(1, questionList.get(i).getQuestionType());
            psInsert.setString(2, questionList.get(i).getDifficultyLevel());
            psInsert.setString(3, questionList.get(i).getDescription());
            psInsert.setString(4, questionList.get(i).getAnswer1());
            psInsert.setString(5, questionList.get(i).getValidity1());
            psInsert.setString(6, questionList.get(i).getAnswer2());
            psInsert.setString(7, questionList.get(i).getValidity2());
            psInsert.setString(8, questionList.get(i).getAnswer3());
            psInsert.setString(9, questionList.get(i).getValidity3());
            psInsert.setString(10, questionList.get(i).getAnswer4());
            psInsert.setString(11, questionList.get(i).getValidity4());
            // add batch
            psInsert.addBatch();
        }
         // batch processing
        psInsert.executeBatch();
    }
    
    public static ArrayList<QuestionInfo> readQuestion(String filePath) throws FileNotFoundException, IOException {
        ArrayList<QuestionInfo> questionList = new ArrayList();
       
        String pathname = filePath;
        File filename = new File(pathname);
        InputStreamReader reader = new InputStreamReader(new FileInputStream(filename));
        BufferedReader br = new BufferedReader(reader);
        String line = "";
        String[] parts;
        line = br.readLine();
        
        while (line != null) {

            parts = line.split(",");
           
            if(parts[0].equals("MC")||parts[0].equals("MA")){
                QuestionInfo question=new QuestionInfo(parts[0],parts[1],"",parts[parts.length-8],parts[parts.length-7],parts[parts.length-6],parts[parts.length-5],parts[parts.length-4],parts[parts.length-3],parts[parts.length-2],parts[parts.length-1]);
                String descriptionTemp="";
                for(int temp=2; temp<parts.length-8;temp++){
                    descriptionTemp+=parts[temp];
                }
                question.setDescription(descriptionTemp);
                questionList.add(question);
            }

            else {
               
                QuestionInfo question=new QuestionInfo(parts[0],parts[1],"",parts[parts.length-1],"","","","","","","");
                String descriptionTemp="";
                for(int temp=2; temp<parts.length-1;temp++){
                    descriptionTemp+=parts[temp];
                }
                question.setDescription(descriptionTemp);
                questionList.add(question);
            }
            line = br.readLine();
            

        }
        return questionList;
    }
}
