package groupproject;
import java.util.*;
import java.sql.*;
/**
 *
 * @author jinleiw
 */
public class Quiz {
    
    public static void main(String[] args)throws Exception{

    }
    // set attributes
    private ArrayList<QuestionInfo> questions=new ArrayList<QuestionInfo>();
    private ArrayList<Boolean> answers=new ArrayList<Boolean>();
    private Instructor instructor;
    private Student student=new Student();
    private int DifficultyLevel;
    private int questionNumber;
    private Connection con;
    private Scanner in=new Scanner(System.in);
    private java.sql.Date time;
    

    /**
     * This is the method that start quiz for the student.
     */
    public void startQuiz()throws Exception{
        
    }
    
    /**
     *  This is the method that choose different mode for the student.
     */
    public void chooseDifficultyLevel(){
        
    }

    /**
     * This is the method that set questions from the database for the student by the mode.
     */
    public void setQuestionsFromDatabase()throws Exception{
        Statement stmt = con.createStatement();
        if(DifficultyLevel==4){
        String query = "SELECT * FROM APP.QUESTION ORDER BY RAND()";
        ResultSet rs = stmt.executeQuery(query);
        for(int i=0;i<questionNumber;i++){
        if(!rs.next()) return;
        questions.add(new QuestionInfo(rs.getString("QUESTIONTYPE"),rs.getString("DIFFICULTYLEVEL"),rs.getString("DESCRIPTION"),rs.getString("ANSWER1"),rs.getString("VALIDITY1"),rs.getString("ANSWER2"),rs.getString("VALIDITY2"),rs.getString("ANSWER3"),rs.getString("VALIDITY3"),rs.getString("ANSWER4"),rs.getString("VALIDITY4")));   
        }
        }
        else{
        String query = "SELECT * FROM APP.QUESTION WHERE DIFFICULTYLEVEL= "+DifficultyLevel+" ORDER BY RAND()";
        ResultSet rs = stmt.executeQuery(query);
        for(int i=0;i<questionNumber;i++){
        if(!rs.next()) return;
        questions.add(new QuestionInfo(rs.getString("QUESTIONTYPE"),rs.getString("DIFFICULTYLEVEL"),rs.getString("DESCRIPTION"),rs.getString("ANSWER1"),rs.getString("VALIDITY1"),rs.getString("ANSWER2"),rs.getString("VALIDITY2"),rs.getString("ANSWER3"),rs.getString("VALIDITY3"),rs.getString("ANSWER4"),rs.getString("VALIDITY4")));   
        }
        }
    }
    
    /**
     * This is the method that can display can get answers during the quiz.
     */
    public void showQuestionsAndGetAnswers(){
        
    }
    
    /**
     * This is the method that can show the final result of the student.
     */
    public void showResult(){
        
    }
    
    /**
     * 
     * @return the accuracy of this test
     */
    public double getRate(){
        int Right=0;
        int Wrong=0;
        for(boolean a:answers){
            if(a) Right++;
            else Wrong++;
        }
        return (double)Right/(Right+Wrong);
    }
    
    /**
     * This method can load all the information into the database.
     */
    public void loadIntoDatabase()throws Exception{
        this.setConnection();
        if(!this.hasTable()) this.createTable();
        this.insertResultToDatabase();
        this.dropConnection();
    }
    
    /**
     * set connection with database
     * @throws Exception 
     */   
    public void setConnection() throws Exception{
        con=DriverManager.getConnection("jdbc:derby://localhost:1527/ExamDB;create=true");       
    }
    
    /**
     * drop the connection with database
     * @throws Exception 
     */
    public void dropConnection() throws Exception{
        con.close();
    }

    /**
     * create table for database
     * @throws Exception 
     */
    public void createTable()throws Exception{
            Statement stmt = con.createStatement();
            String s="CREATE TABLE RESULT"
                    + "(TIME DATE,"
                    + "RATE DOUBLE,"
                    + "STUDENT VARCHAR (40),"
                    + "DIFFICULTYLEVEL INT )";
            stmt.execute(s);        
    }
    
    /**
     * check if the database already has the table
     * @return whether it has table
     * @throws Exception 
     */
    public boolean hasTable()throws Exception{
        boolean result=false;
        DatabaseMetaData meta=con.getMetaData();
        ResultSet set =meta.getTables(null, null, "RESULT", null);
        while(set.next()){
            result=true;
        }    
        return result;
    }

    /**
     * insert the data into database
     * @throws Exception 
     */    
    public void insertResultToDatabase() throws Exception{  
            Statement stmt = con.createStatement();
            String query="INSERT INTO RESULT (TIME , RATE, STUDENT, DIFFICULTYLEVEL) VALUES (";
            query+=this.time+",";
            query+=this.getRate()+",'";
            query+=this.student.getUsername()+"',";
            query+=this.DifficultyLevel+")";
            stmt.executeUpdate(query);
    }
}
