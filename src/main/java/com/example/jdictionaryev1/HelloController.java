package com.example.jdictionaryev1;

import com.almasb.fxgl.audio.Audio;
import dictionary.Dictionary;
import dictionary.DictionaryManagement;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Popup;
import javafx.stage.PopupWindow;
import javafx.stage.Stage;
import module.SQLite.SQLiteConnection;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;

public class HelloController {
    @FXML
    //--------------------------------tạo chuyển cảnh---------------------------
    public Stage stage;
    public Scene scene;
    public Parent root;
    // chuyển sang từ điển
    public void switchToDictionary(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Dictionary.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root,840,540);
        stage.setScene(scene);
        stage.show();
    }
    // chuyển sang game
    public void switchToGame(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("Game.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root,840,540);
        stage.setScene(scene);
        stage.show();

    }

    public void switchToAdd(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("Addq.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root,840,540);
        stage.setScene(scene);
        stage.show();

    }
    //----------------------------------------------------------------

    //--------------------Đăng nhập- Account-----------------------


    //--------------------Logout/exit button---------------- đang lỗi
    @FXML
    public Button logoutButton;
    @FXML
    public AnchorPane scenePane ;
    public void logout(ActionEvent event){
        //Tạo hộp xác nhận
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setHeaderText("You're about to logout!");
        alert.setContentText("Do you want to save before exiting!: ");
        //System.out.println("im here");
        if(alert.showAndWait().get() == ButtonType.OK){
            stage = (Stage) scenePane.getScene().getWindow(); //đang lỗi ở đây help me!!!!!
            System.out.println("You successfully logged out");

            stage.close();
        }
    }


    //-----------------TextField Searching-----------------------
    @FXML
    public Label labelSearch;
    public TextField textFieldSearch = new TextField();
    public Button buttonSearch;
    String wordSearch;
    // --wordSearch nhận đầu vào văn bản nhập từ bàn phím
    public void search(ActionEvent event){
        try {
            wordSearch = textFieldSearch.getText();
            System.out.println(wordSearch);
        }catch (Exception e){
            System.out.println(e);  // xử lí ngoại lệ nhập văn bản
                                    // , về sau thêm sử lí ngoại lệ chỉ nhập kí tự ko dc nhập số
        }

        /*Đã thêm xử lí ngoại lệ ký tự số,hiện lên alert thay vì dùng exception (Muốn dùng ngoại lệ
        dùng throw new Exepction("Message")
         */
        for (char c : textFieldSearch.getText().toCharArray()) {
            if (Character.isDigit(c)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Number is not a word, please try again");
                alert.show();
            }
        }
    }


    //-------------------Tính năng chuyển đổi sáng tối - check box-------------------
    //Ứng dụng làm chức năng lưu từ vựng, game -- làm sau





    // nút bấm
//    public void up(ActionEvent e){
//        myCircle.setCenterY(y-=10);
//    }
//    public void down(ActionEvent e){
//        myCircle.setCenterY(y+=10);
//    }
//    public void left(ActionEvent e){
//        myCircle.setCenterX(x-=10);
//    }
//    public void right(ActionEvent e){
//        myCircle.setCenterX(x+=10);
//    }
//
//    public Circle myCircle;
//    public double x;
//    public double y;

    //-----------------Add Question into database-----------------------




}