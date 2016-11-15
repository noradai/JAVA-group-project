/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Group.model;

/**
 *
 * @author Alexis
 */
public class QuestionInfo {
    private String questionType;
    private String difficultyLevel;
    private String description;
    private String answer1;
    private String validity1;
    private String answer2;
    private String validity2;
    private String answer3;
    private String validity3;
    private String answer4;
    private String validity4;
    private String TFanswer;
    private String FIBanswer;

    public String getQuestionType() {
        return questionType;
    }

    public String getDifficultyLevel() {
        return difficultyLevel;
    }

    public String getDescription() {
        return description;
    }
    
    public String getAnswer1() {
        return answer1;
    }

    public String getValidity1() {
        return validity1;
    }

    public String getAnswer2() {
        return answer2;
    }

    public String getValidity2() {
        return validity2;
    }

    public String getAnswer3() {
        return answer3;
    }

    public String getValidity3() {
        return validity3;
    }

    public String getAnswer4() {
        return answer4;
    }

    public String getValidity4() {
        return validity4;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public void setDifficultyLevel(String difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public void setAnswer1(String answer1) {
        this.answer1 = answer1;
    }

    public void setValidity1(String validity1) {
        this.validity1 = validity1;
    }

    public void setAnswer2(String answer2) {
        this.answer2 = answer2;
    }

    public void setValidity2(String validity2) {
        this.validity2 = validity2;
    }

    public void setAnswer3(String answer3) {
        this.answer3 = answer3;
    }

    public void setValidity3(String validity3) {
        this.validity3 = validity3;
    }

    public void setAnswer4(String answer4) {
        this.answer4 = answer4;
    }

    public void setValidity4(String validity4) {
        this.validity4 = validity4;
    }
   
    public QuestionInfo(String questionType, String difficultyLevel, String description, String answer1, String validity1, String answer2, String validity2, String answer3, String validity3, String answer4, String validity4) {
        this.questionType = questionType;
        this.difficultyLevel = difficultyLevel;
        this.description = description;
        this.answer1 = answer1;
        this.validity1 = validity1;
        this.answer2 = answer2;
        this.validity2 = validity2;
        this.answer3 = answer3;
        this.validity3 = validity3;
        this.answer4 = answer4;
        this.validity4 = validity4;
    }
   
    public QuestionInfo(){}
    
    
    public String getAnswer(){
    String s="";
    if(questionType.equals("FIB")) s+=FIBanswer.toUpperCase();
    else if (questionType.equals("TF"))  s+=TFanswer.toUpperCase();
    else if (questionType.equals("MC")){
    if (validity1.equals("correct")) s+=("A");
    else if (validity2.equals("correct")) s+=("B");
    else if (validity3.equals("correct")) s+=("C");
    else if (validity4.equals("correct")) s+=("D");
    }
    else{
   
    if (validity1.equals("correct")) s+="A";
    if (validity2.equals("correct")) s+="B";
    if (validity3.equals("correct")) s+="C";
    if (validity4.equals("correct")) s+="D";
   
    }
    return s;
    }
    
    
    public boolean checkCorrection(String s){
    if (s.toUpperCase().equals(getAnswer()))
            return true;
    else return false;
    
    
    }
    
}
