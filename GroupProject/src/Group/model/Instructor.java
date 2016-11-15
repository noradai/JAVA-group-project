package Group.model;

import java.util.HashMap;
import java.util.Map;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author yanyandai
 */
public class Instructor {

    
    private String userName;
    private String password;

    public Instructor() {
        this.userName = "instructor";
        this.password = "123";
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    

    public void logOut() {
       

    }
    public void addQuestion() {
    }

    public void getReport() {

    }
    public void showPerformance() {
    }

}
