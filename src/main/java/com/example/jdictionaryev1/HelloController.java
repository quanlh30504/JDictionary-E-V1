package com.example.jdictionaryev1;

import com.almasb.fxgl.audio.Audio;
import dictionary.DictionaryManagement;
import dictionary.Word;
import dictionary.Dictionary;
import dictionary.DictionaryManagement;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Popup;
import javafx.stage.PopupWindow;
import javafx.stage.Stage;
import javafx.util.Pair;
import module.SQLite.SQLiteConnection;
import org.jetbrains.annotations.NotNull;
import searchingAlgorithm.Trie;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class HelloController extends DictionaryManagement {

    //--------------------------------tạo chuyển cảnh---------------------------
    @FXML
    public Stage stage;
    public Scene scene;
    public Parent root;

    //----------------------------- chuyển sang từ điển---------------------
    @FXML
    public void switchToDictionary(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Dictionary.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root, 840, 540);
        stage.setScene(scene);
        stage.show();
    }

    // -----------------------------chuyển sang game------------------------
    @FXML
    public void switchToGame(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Game.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root, 840, 540);
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
    //------------------------------ start game-------------------------
    @FXML
    public void startGame(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("QuizGame.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root, 840, 540);
        stage.setScene(scene);
        stage.show();
    }


    //--------------------Logout/exit button----------------
    @FXML
    public Button logoutButton;
    @FXML
    public AnchorPane scenePane;

    public void logout(ActionEvent event) {
        //Tạo hộp xác nhận
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setHeaderText("You're about to logout!");
        alert.setContentText("Do you want to save before exiting!: ");
        //System.out.println("im here");
        if (alert.showAndWait().get() == ButtonType.OK) {
            stage = (Stage) scenePane.getScene().getWindow(); //đang lỗi ở đây help me!!!!!
            System.out.println("You successfully logged out");

            stage.close();
        }
    }


    //-----------------TextField Searching-----------------------
    @FXML
    public Label labelSearch;
    public TextField textFieldSearch ;
    public Button buttonSearch;
    public ListView<String> listVocab;
    String wordSearch;

    // --wordSearch nhận đầu vào văn bản nhập từ bàn phím
    public void search(ActionEvent event) throws IOException {
        DictionaryManagement dictionaryManagement = new DictionaryManagement();
        SQLiteConnection sqLiteConnection3 = new SQLiteConnection();
        sqLiteConnection3.setConnection(dbName);
        dictionaryManagement.getAllWord(dbName);
        dictionaryManagement.setTrie();
        Trie trie1 = dictionaryManagement.getTrie();
        try {
            //textFieldSearch.textProperty().addListener((observableValue, oldvalue, newvalue) -> {//
                wordSearch = textFieldSearch.getText();
                System.out.println(wordSearch);
            //});//
        } catch (Exception e) {
            System.out.println(e);  // xử lí ngoại lệ nhập văn bản

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
        List<String> searchResult = trie1.autoComplete(wordSearch);
        if (searchResult != null) {
            ObservableList<String> res = FXCollections.observableArrayList(searchResult);
            listVocab.setItems(res);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Word not found!");
            alert.show();
        }
    }
    //-----------------------------table view hiện nghĩa của từ-------------------------



    //-------------------Tính năng chuyển đổi sáng tối - check box-------------------
    //Ứng dụng làm chức năng lưu từ vựng, game -- làm sau

    //---------------------------tạo container button để hiện các nút add,delete,edit---------------
    @FXML
    private Button toggleButton;

    @FXML
    private VBox buttonContainer;

    private boolean buttonsVisible = false;

    @FXML
    void toggleButtonClicked(ActionEvent event) {
        buttonContainer.setSpacing(20);
        if (buttonsVisible) {
            buttonContainer.getChildren().clear();
            buttonsVisible = false;
            //toggleButton.setText("Hiển thị Nút Con");
        } else {

            Button button1 = createImageButton("EditVocab/add.png","");
            Button button2 = createImageButton("EditVocab/bin.png","");
            Button button3 = createImageButton("EditVocab/edit.png","");

            // Đặt style cho nút
            button1.setStyle("-fx-background-color: #DDDDDD; -fx-background-radius: 50;");
            button2.setStyle("-fx-background-color: #DDDDDD; -fx-background-radius: 50;");
            button3.setStyle("-fx-background-color: #DDDDDD; -fx-background-radius: 50;");

            // Bắt sự kiện hover
            button1.setOnMouseEntered(e -> {
                button1.setStyle("-fx-background-color: #B5B5B5; -fx-background-radius: 50;");
            });

            button1.setOnMouseExited(e -> {
                button1.setStyle("-fx-background-color: #DDDDDD; -fx-background-radius: 50;");
            });

            button2.setOnMouseEntered(e -> {
                button2.setStyle("-fx-background-color: #B5B5B5; -fx-background-radius: 50;");
            });

            button2.setOnMouseExited(e -> {
                button2.setStyle("-fx-background-color: #DDDDDD; -fx-background-radius: 50;");
            });

            button3.setOnMouseEntered(e -> {
                button3.setStyle("-fx-background-color: #B5B5B5; -fx-background-radius: 50;");
            });

            button3.setOnMouseExited(e -> {
                button3.setStyle("-fx-background-color: #DDDDDD; -fx-background-radius: 50;");
            });

            VBox.setMargin(button2, new Insets(0, 0, 0, 20));

            button1.setOnAction(e -> setAddVocab());
            button2.setOnAction(e -> setDeleteVocab());
            button3.setOnAction(e -> setEditVocabVocab());


            buttonContainer.getChildren().addAll(button1, button2, button3);
            buttonsVisible = true;
            //toggleButton.setText("Ẩn Nút Con");
        }
    }
    private Button createImageButton(String URL, String text) {
        // Tải hình ảnh từ tệp
        Image image = new Image(getClass().getResource("/" + URL).toExternalForm());

        // Tạo một ImageView để hiển thị hình ảnh
        ImageView imageView = new ImageView(image);

        // Tạo một nút và đặt ImageView làm nội dung
        Button button = new Button(text, imageView);
        imageView.setFitWidth(32); // Độ rộng
        imageView.setFitHeight(32); // Độ cao
        button.setMinWidth(50); // Kích thước tối thiểu chiều rộng
        button.setMinHeight(50); // Kích thước tối thiểu chiều cao
        return button;
    }

    //--------------------------------Tạo edit thêm từ----------------------------------
    //@FXML
    //Button addVocab = new Button();

    @FXML
    private void setAddVocab() {
        // Create the custom dialog
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Add Vocabulary");

        // Set the button types (OK and Cancel)
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        // Create the labels and text fields
        Label nameLabel = new Label("New Vocab:");
        nameLabel.setStyle("-fx-font-size: 15px;-fx-font-weight: bold;");
        TextField nameField = new TextField();
        nameField.setStyle("-fx-background-radius: 40;-fx-font-weight: bold;");

        Label descriptionLabel = new Label("Meaning:");
        descriptionLabel.setStyle("-fx-font-size: 15px;-fx-font-weight: bold;");
        TextArea descriptionArea = new TextArea();
        descriptionArea.setStyle("-fx-background-color: #f0f0f0;-fx-text-fill: #333;-fx-font-size: 20p;-fx-border-width: 2px;-fx-border-color: #ccc;-fx-font-weight: bold;");

        // Add the labels and text fields to the dialog pane
        VBox content = new VBox(10, nameLabel, nameField, descriptionLabel, descriptionArea);
        content.setStyle("-fx-background-color: #CC99FF;-fx-padding: 10;-fx-spacing: 10;-fx-pref-width: 450px; -fx-pref-height: 250px;");
        dialog.getDialogPane().setContent(content);

        // Request focus on the name field by default
        Platform.runLater(() -> nameField.requestFocus());

        // Convert the result to a pair of Strings when the OK button is clicked
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == ButtonType.OK) {
                return new Pair<>(nameField.getText(), descriptionArea.getText());
            }
            return null;
        });

        // Show the dialog and wait for the user's response
        Optional<Pair<String, String>> result = dialog.showAndWait();

        // Process the result
        result.ifPresent(pair -> {
            System.out.println("Entered new vocab: " + pair.getKey());
            System.out.println("Entered meaning: " + pair.getValue());
            Word newWord = new Word(pair.getKey(), pair.getValue());
            try {
                insertWord(newWord);
                Alert alert =new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Add word successfully");
                alert.show();
            } catch (SQLException e) {
                throw new RuntimeException("Help me ");
            }
        });
    }


    //--------------------------------Tạo edit xóa----------------------------------

//    @FXML
//    public Button deleteVocab = new Button();

    @FXML
    private void setDeleteVocab() {
        // Create the custom dialog
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Delete Vocabulary");

        // Set the button types (OK and Cancel)
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        // Create the labels and text fields
        Label nameLabel = new Label("Vocab:");
        nameLabel.setStyle("-fx-font-size: 15px;-fx-font-weight: bold;");
        TextField nameField = new TextField();
        nameField.setStyle("-fx-background-radius: 40;-fx-pref-width: 250px; -fx-pref-height: 35px;-fx-font-weight: bold;");

        // Add the labels and text fields to the dialog pane
        VBox content = new VBox(30, nameLabel, nameField);

        content.setStyle("-fx-background-color: #CC99FF;-fx-padding: 10;-fx-spacing: 10;-fx-pref-width: 350px; -fx-pref-height: 120px;");

        dialog.getDialogPane().setContent(content);

        // Request focus on the name field by default
        Platform.runLater(() -> nameField.requestFocus());

        // Convert the result to a string when the OK button is clicked
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == ButtonType.OK) {
                return nameField.getText();
            }
            return null;
        });

        // Show the dialog and wait for the user's response
        Optional<String> result = dialog.showAndWait();

        // Process the result
        result.ifPresent(name -> {
            System.out.println("Entered delete word: " + name);
                    deleteWord(name);
                    Alert alert =new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText("Delete word successfully");
                    alert.show();
                }
        );
    }

    //-----------------------------sửa từ ---------------------------
//    @FXML
//    Button editVocab = new Button();

    @FXML
    private void setEditVocabVocab() {
        // Create the custom dialog
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Edit Vocabulary");
        // Set the button types (OK and Cancel)
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        // Create the labels and text fields
        Label nameLabel = new Label("Vocabulary:");
        nameLabel.setStyle("-fx-font-size: 15px;-fx-font-weight: bold;");
        TextField nameField = new TextField();
        nameField.setStyle("-fx-background-radius: 40;-fx-font-weight: bold;");


        Label descriptionLabel = new Label("New Meaning:");
        descriptionLabel.setStyle("-fx-font-size: 15px;-fx-font-weight: bold;");
        TextArea descriptionArea = new TextArea();
        descriptionArea.setStyle("-fx-background-color: #f0f0f0;-fx-text-fill: #333;-fx-font-size: 20p;-fx-border-width: 2px;-fx-border-color: #ccc;-fx-font-weight: bold;");

        // Add the labels and text fields to the dialog pane
        VBox content = new VBox(10, nameLabel, nameField, descriptionLabel, descriptionArea);
        content.setStyle("-fx-background-color: #CC99FF;-fx-padding: 10;-fx-spacing: 10;-fx-pref-width: 450px; -fx-pref-height: 250px;");
        dialog.getDialogPane().setContent(content);

        // Request focus on the name field by default
        Platform.runLater(() -> nameField.requestFocus());

        // Convert the result to a pair of Strings when the OK button is clicked
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == ButtonType.OK) {
                return new Pair<>(nameField.getText(), descriptionArea.getText());
            }
            return null;
        });

        // Show the dialog and wait for the user's response
        Optional<Pair<String, String>> result = dialog.showAndWait();

        // Process the result
        result.ifPresent(pair -> {
            System.out.println("Entered vocab: " + pair.getKey());
            System.out.println("Entered meaning: " + pair.getValue());
        });
    }
    //-----------------Add Question into database-----------------------




}