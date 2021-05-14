package org.loose.fis.sre.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.loose.fis.sre.exceptions.UncompletedFieldsException;
import org.loose.fis.sre.exceptions.WrongFieldsException;
import org.loose.fis.sre.services.BookService;
import javafx.scene.control.ListView;

public class DeleteBookController {

    @FXML
    private TextField deleteBooksField;
    @FXML
    private Text deleteBookMessage;
    @FXML
    private ListView listOfBooks;

    /*public void handleDeleteBookAction() {
        try {
            BookService.deleteBook(deleteBooksField.getText());
            deleteBookMessage.setText("Book deleted successfully!");
            listOfBooks.getItems().clear();
        } catch (UncompletedFieldsException e2) {
            deleteBookMessage.setText(e2.getMessage());
        } catch (WrongFieldsException e1) {
            deleteBookMessage.setText(e1.getMessage());
        }
    }*/
}

