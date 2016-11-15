/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Group.controller;
import java.awt.Color;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.TextFlow;

/**
 * FXML Controller class
 *
 * @author 45357
 */
public class Quiz_MultiController extends AnchorPane implements Initializable {

    @FXML
    private RadioButton Choice1;
    @FXML
    private RadioButton choice2;
    @FXML
    private RadioButton choice3;
    @FXML
    private RadioButton choice4;
    @FXML
    private Label lable1;
    @FXML
    private Label lable2;
    @FXML
    private Label lable3;
    @FXML
    private Label lable4;
    @FXML
    private Button NextButton;

    @FXML
    private TextFlow MyTextflow;
    
    private GroupProject application;
    
    public void setApplication(GroupProject application){
        this.application=application;
    }



    @FXML
    private void HandleNextButton(ActionEvent event) {   
        System.out.println("aaa");
        System.out.println(application);
        System.out.println(application.getQ().getQuestions().get(0).getDescription());
        lable1.setText(application.getQ().getQuestions().get(0).getDescription());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {   

    }
    

}
