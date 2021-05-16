package org.loose.fis.sre.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import org.loose.fis.sre.exceptions.IncorrectPasswordException;
import org.loose.fis.sre.exceptions.InvalidUsernameException;
import org.loose.fis.sre.services.UserService;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LoginController {
    @FXML
    private Label logMessage;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button logButton;

    public void handleRegistrationView() throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("register.fxml"));
        Stage scene= (Stage) logButton.getScene().getWindow();
        scene.setTitle("Welcome");
        scene.setScene(new Scene(root,725,490));
    }

    public void  handleLoginAction() throws IOException {
        try
        {
            UserService.userExists(usernameField.getText(),passwordField.getText());
            //logMessage.setText("Successful log in");
            if(UserService.checkIsAdmin(usernameField.getText()))
            {
                Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("admin_main_page.fxml"));
                Stage scene= (Stage) logButton.getScene().getWindow();
                scene.setTitle("Admin");
                scene.setScene(new Scene(root,1200,1000));
                scene.setResizable(false);
                scene.setMinHeight(900);
                scene.setMinWidth(1000);
                scene.setMaxHeight(1080);
                scene.setMaxWidth(1920);
                scene.setTitle("Admin");
                scene.setFullScreen(true);
            }
            else
            {
                Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("client_main_page.fxml"));
                Stage scene= (Stage) logButton.getScene().getWindow();
                scene.setScene(new Scene(root,1200,1000));
                scene.setResizable(false);
                scene.setMinHeight(900);
                scene.setMinWidth(1000);
                scene.setMaxHeight(1080);
                scene.setMaxWidth(1920);
                scene.setTitle("Client");
                scene.setFullScreen(true);
            }
        }
        catch(InvalidUsernameException e)
        {
            logMessage.setText(e.getMessage());
        }catch(IncorrectPasswordException e)
        {
            logMessage.setText(e.getMessage());
        }
    }
}