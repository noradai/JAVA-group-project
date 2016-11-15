/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Group.model;

import Group.dao.StudentJDBC;
import Group.model.Instructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author jasmine
 */
public class Authenticator {

    StudentJDBC studentJDBC = new StudentJDBC();

    private static final Instructor instructor = new Instructor();
    private static Map<String, String> students = new HashMap<String, String>();

    public static boolean validateInstructor(String user, String password) {
        String validUserPassword = instructor.getPassword();
        return validUserPassword != null && validUserPassword.equals(password);
    }

    public Map getStudentList(){
        try{
            students = studentJDBC.getStudentList();
        }catch(Exception e){
            System.out.print(e);
            System.exit(-1);
        }
        return students;
    }

    public static boolean validateStudent(String user, String password) throws Exception {
        if (students.containsKey(user)) {
            if(students.get(user).equals(password) ){
                return true;
            }
        }
        return false;
    }

}
