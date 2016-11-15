package Group.model;
import Group.model.QuestionInfo;
import Group.model.Instructor;
import java.util.*;
import java.sql.*;
/**
 *
 * @author jinleiw
 */
public class Quiz {
    private ArrayList<QuestionInfo> questions=new ArrayList<QuestionInfo>();
    private ArrayList<Boolean> answers=new ArrayList<Boolean>();
    private Instructor instructor;
    private Student student;
    private String DifficultyLevel;
    private int questionNumber;
    private Connection con;    
    private java.sql.Date time;
    private int count=0;
    
    private Scanner in=new Scanner(System.in);   
    
    public static void main(String[] args)throws Exception{
        Quiz q=new Quiz();
        q.setDifficultyLevel("E");
        q.setQuestionNumber(5);
        q.setConnection();
        q.setQuestionsFromDatabase();
        q.dropConnection();
        System.out.println(q.getQuestions().size());
        for(int i=0;i<q.getQuestions().size();i++){
            System.out.print(q.getQuestions().get(i).getQuestionType());
            System.out.print(q.getQuestions().get(i).getDifficultyLevel());
            System.out.print(q.getQuestions().get(i).getDescription());
            System.out.print(q.getQuestions().get(i).getAnswer1());
            System.out.print(q.getQuestions().get(i).getValidity1());
            System.out.print(q.getQuestions().get(i).getAnswer2());
            System.out.print(q.getQuestions().get(i).getValidity2());
            System.out.print(q.getQuestions().get(i).getAnswer3());
            System.out.print(q.getQuestions().get(i).getValidity3());
            System.out.print(q.getQuestions().get(i).getAnswer4());
            System.out.print(q.getQuestions().get(i).getValidity4());
            System.out.println();
        }
    }


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
     * @throws java.lang.Exception
     */
    public void setQuestionsFromDatabase()throws Exception{
        Statement stmt = con.createStatement();
        if(DifficultyLevel.equals("M")){
        String query = "SELECT * FROM APP.QUESTION ";
        ResultSet rs = stmt.executeQuery(query);
        for(int i=0;i<questionNumber;i++){
        if(!rs.next()) return;
        questions.add(new QuestionInfo(rs.getString("QUESTIONTYPE"),rs.getString("DIFFICULTYLEVEL"),rs.getString("DESCRIPTION"),rs.getString("ANSWER1"),rs.getString("VALIDITY1"),rs.getString("ANSWER2"),rs.getString("VALIDITY2"),rs.getString("ANSWER3"),rs.getString("VALIDITY3"),rs.getString("ANSWER4"),rs.getString("VALIDITY4")));   
        }
        }
        else{
        String query = "SELECT * FROM APP.QUESTION" ;
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
                    + "DIFFICULTYLEVEL VARCHAR (10) )";
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
            query+=this.student.getUserName()+"','";
            query+=this.DifficultyLevel+"')";
            stmt.executeUpdate(query);
    }
    
    public void getNextQuestion(){
        
    }

    /**
     * @return the questions
     */
    public ArrayList<QuestionInfo> getQuestions() {
        return questions;
    }

    /**
     * @param questions the questions to set
     */
    public void setQuestions(ArrayList<QuestionInfo> questions) {
        this.questions = questions;
    }

    /**
     * @return the DifficultyLevel
     */
    public String getDifficultyLevel() {
        return DifficultyLevel;
    }

    /**
     * @param DifficultyLevel the DifficultyLevel to set
     */
    public void setDifficultyLevel(String DifficultyLevel) {
        this.DifficultyLevel = DifficultyLevel;
    }

    /**
     * @return the questionNumber
     */
    public int getQuestionNumber() {
        return questionNumber;
    }

    /**
     * @param questionNumber the questionNumber to set
     */
    public void setQuestionNumber(int questionNumber) {
        this.questionNumber = questionNumber;
    }
}
