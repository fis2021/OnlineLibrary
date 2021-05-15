package org.loose.fis.sre.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.loose.fis.sre.exceptions.BookExistsException;
import org.loose.fis.sre.exceptions.UncompletedFieldsException;
import org.loose.fis.sre.services.BookService;
import javafx.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddBookController implements Initializable{
    @FXML
    private ComboBox LanguageBox;
    @FXML
    private Button selectFileButton;
    @FXML
    private ImageView bookImage;
    @FXML
    private TextField titluField;
    @FXML
    private TextField autorField;
    @FXML
    private TextArea  descrArea;
    @FXML
    private  Image fxImage;
    @FXML
    private Text bookMessage;

    public void selectFileAction() {

        Stage stage = (Stage) selectFileButton.getScene().getWindow();

        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );
        File selectedFile = fileChooser.showOpenDialog(stage);
        fxImage = new Image(new File(selectedFile.getAbsolutePath()).toURI().toString());
        bookImage.setImage(fxImage);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        LanguageBox.getItems().addAll("Română", "Engleză", "Franceză", "Germană");
    }
    public void handleAddBook()
    {
        try
        {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("AddBook.fxml"));
            BookService.addBook(titluField.getText(),autorField.getText(),(String) LanguageBox.getValue(),fxImage.getUrl(), descrArea.getText().replaceAll("\n", System.getProperty("line.separator")));
            bookMessage.setText("Carte adăugată cu succes!");
            Stage scene= (Stage) titluField.getScene().getWindow();
            scene.setTitle("Add Book");
            scene.setScene(new Scene(root,730,700));
            scene.setFullScreen(false);
            scene.setResizable(false);
            scene.setMinHeight(700);
            scene.setMinWidth(730);
            scene.setMaxHeight(700);
            scene.setMaxWidth(730);

        }
        catch(UncompletedFieldsException e)
        {
            bookMessage.setText(e.getMessage());
        }
        catch(BookExistsException e)
        {
            bookMessage.setText("The book could not be added");
        }
        catch(NullPointerException e)
        {
            bookMessage.setText("You must complete all the fields!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}