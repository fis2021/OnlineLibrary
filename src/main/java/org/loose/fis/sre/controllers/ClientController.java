package org.loose.fis.sre.controllers;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.loose.fis.sre.model.Book;
import org.loose.fis.sre.model.ClickedBook;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

import static org.loose.fis.sre.services.BookService.getLast;

public class ClientController implements Initializable {
    @FXML
    Text labelMain;
    @FXML
    private ImageView imgView;
    private Book currentBook;
    public void handleImg() throws IOException {
        ClickedBook.selectedBook=currentBook;
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("book.fxml"));
        Stage scene= (Stage) imgView.getScene().getWindow();
        scene.setScene(new Scene(root,1920,1080));
        scene.setResizable(false);
        scene.setMinHeight(1080);
        scene.setMinWidth(1920);
        scene.setMaxHeight(1080);
        scene.setMaxWidth(1920);
        scene.setTitle("Book");
        scene.setFullScreen(true);

    }
    public void handleTransition2(){


        double msgWidth = imgView.getLayoutBounds().getWidth();
        KeyValue initKeyValue = new KeyValue(imgView.translateXProperty(), 800);
        KeyFrame initFrame = new KeyFrame(Duration.ZERO, initKeyValue);
        KeyValue endKeyValue = new KeyValue(imgView.translateXProperty(), -0.3
                * msgWidth);
        KeyFrame endFrame = new KeyFrame(Duration.seconds(10), endKeyValue);
        Timeline timeline = new Timeline(initFrame, endFrame);

        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();



    }
    public void handleTransition(){


        double msgWidth = labelMain.getLayoutBounds().getWidth();
        KeyValue initKeyValue = new KeyValue(labelMain.translateXProperty(), 1600);
        KeyFrame initFrame = new KeyFrame(Duration.ZERO, initKeyValue);
        KeyValue endKeyValue = new KeyValue(labelMain.translateXProperty(), -1.0
                * msgWidth);
        KeyFrame endFrame = new KeyFrame(Duration.seconds(10), endKeyValue);
        Timeline timeline = new Timeline(initFrame, endFrame);

        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();



    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        handleTransition2();
        handleTransition();
        Thread thread = new Thread(){
            public void run() {
                String[] messages = new String[4];
                messages[0] = "“So many books, so little time”";
                messages[1] = "“A room without books is like a body without a soul”";
                messages[2] = "“I have always imagined that Paradise will be a kind of library”";
                messages[3] = "“There is no friend as loyal as a book”";
                Image[]img=new Image[3];
                ArrayList<Book> a=getLast();
                img[0]= new Image(a.get(0).getPhoto_path());
                img[1]=new Image(a.get(1).getPhoto_path());
                img[2]=new Image(a.get(2).getPhoto_path());
                int cnt=0;
                while (true) {
                    Random rand = new Random();
                    labelMain.setText(messages[rand.nextInt(messages.length)]);
                    imgView.setImage(img[cnt]);
                    currentBook=a.get(cnt);
                    cnt++;
                    if(cnt==3)
                        cnt=0;

                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        };
        thread.start();

    }
}