/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Group.controller;

import Group.model.Student;
import Group.controller.Quiz_MultiController;
import Group.model.Quiz;
import Group.controller.LoginController;
import Group.model.Instructor;
import Group.model.Authenticator;
import java.io.InputStream;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author
 */
public class GroupProject extends Application {

    Authenticator a = new Authenticator();
    Map student = a.getStudentList();

    //   UserJDBC studentDB = new UserJDBC();
    private Stage stage;
    private final double MINIMUM_WINDOW_WIDTH = 400.0;
    private final double MINIMUM_WINDOW_HEIGHT = 300.0;

    private Student loggedStudent;
    private Instructor instructor;
    private Quiz q=new Quiz();

    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        launch(args);
    }


    public boolean instructorLogging(String username, String password) throws Exception {
        boolean result = Authenticator.validateInstructor(username, password);
        if (result != true) {
            return false;
        } else {
            //gotoInstrutorMain();
            return true;
        }
    }

    public boolean studentLogging(String username, String password) throws Exception {
        boolean result = Authenticator.validateStudent(username, password);
        if (result != true) {
            return false;
        } else {
            //gotoStudentMain();
            return true;
        }
    }

    public Student getLoggedStudent() {
        return loggedStudent;
    }

    public Instructor getLoggedInstructor() {
        return instructor;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        q.setDifficultyLevel("E");
        q.setQuestionNumber(5);
        q.setConnection();
        q.setQuestionsFromDatabase();
        q.dropConnection();
        try {
            stage = primaryStage;
            stage.setTitle("Login");
            stage.setMinWidth(MINIMUM_WINDOW_WIDTH);
            stage.setMinHeight(MINIMUM_WINDOW_HEIGHT);
            Parent root = FXMLLoader.load(getClass().getResource("quiz_Multi.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            //gotoLogin();
            goToQuiz();
            primaryStage.show();
        } catch (Exception ex) {
            Logger.getLogger(GroupProject.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void gotoLogin() {
        try {
            LoginController login =  (LoginController) replaceSceneContent("Login.fxml");
            login.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(GroupProject.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    private Initializable replaceSceneContent(String fxml) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        InputStream in = GroupProject.class.getResourceAsStream(fxml);
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(GroupProject.class.getResource(fxml));
        AnchorPane page;
        try {
            page = (AnchorPane) loader.load(in);
        } finally {
            in.close();
        }
        Scene scene = new Scene(page, 600, 400);
        stage.setScene(scene);
        stage.sizeToScene();
        return (Initializable) loader.getController();
    }

    private void goToQuiz() {
        try {           
            Quiz_MultiController QMC = (Quiz_MultiController) replaceSceneContent("quiz_Multi.fxml");
            QMC.setApplication(this);
        } catch (Exception ex) {
            Logger.getLogger(GroupProject.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * @return the q
     */
    public Quiz getQ() {
        return q;
    }

    /**
     * @param q the q to set
     */
    public void setQ(Quiz q) {
        this.q = q;
    }
}
