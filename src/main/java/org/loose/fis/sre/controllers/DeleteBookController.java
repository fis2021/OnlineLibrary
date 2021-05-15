package org.loose.fis.sre.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.loose.fis.sre.exceptions.UncompletedFieldsException;
import org.loose.fis.sre.exceptions.UncompletedTitleException;
import org.loose.fis.sre.exceptions.WrongFieldsException;
import org.loose.fis.sre.services.BookService;
import javafx.scene.control.ListView;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class DeleteBookController {

    @FXML
    private Text deleteBookMessage;
    @FXML
    private ListView listOfBooks;
    @FXML
    private TextField titluField;
    @FXML
    private Text bookMessage;

    /*public void selectFileAction() {

        Stage stage = (Stage) selectFileButton.getScene().getWindow();

        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );
        File selectedFile = fileChooser.showOpenDialog(stage);
        fxImage = new Image(new File(selectedFile.getAbsolutePath()).toURI().toString());
        bookImage.setImage(fxImage);
    }*/

    public void handleDeleteBook() {
        try {
            BookService.deleteBook(titluField.getText());
            deleteBookMessage.setText("Book deleted successfully!");
            listOfBooks.getItems().clear();
        } catch (UncompletedTitleException e2) {
            deleteBookMessage.setText(e2.getMessage());
        } catch (UncompletedFieldsException e1) {
            deleteBookMessage.setText(e1.getMessage());
        }
    }
}

