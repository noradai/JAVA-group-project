/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Group.controller;

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

/**
 *
 * @author yanyandai
 */
public class LoginController implements Initializable {

    @FXML
    TextField username;
    @FXML
    PasswordField password;
    @FXML
    Button login;
    @FXML
    RadioButton student;
    @FXML
    RadioButton instructor;
    @FXML
    Label errorMessage;

    private GroupProject application;

    public void setApp(GroupProject application) {
        this.application = application;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        errorMessage.setText("");
        username.setPromptText("");
        password.setPromptText("");
    }

    public void processLogin(ActionEvent event) throws Exception {
        
        if (student.isSelected()) {
            if (!application.studentLogging(username.getText(), password.getText())) {
                errorMessage.setText("Invalid username or password!");
            } else {
                errorMessage.setText("success");
            }
        }
        if (instructor.isSelected()) {
            if (!application.instructorLogging(username.getText(), password.getText())) {
                errorMessage.setText("Invalid username or password!");
            }
        }
    }

}
