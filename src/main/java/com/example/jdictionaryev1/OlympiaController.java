package com.example.jdictionaryev1;

import game.Olympia.Round.Starting;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class OlympiaController {
    @FXML
    Button tutorialButton;
    @FXML
    public Stage stage;
    @FXML
    public Scene scene;
    public Parent root;

    @FXML
    public void backToDictionary(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Game.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root, 840, 540);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    public void switchToTutorial(@NotNull ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Tutorial.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root, 1080, 608);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void switchToOlympiaGame(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("OlympiaStart.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root, 1080, 608);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void switchToSetUp(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Addq.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root, 1080, 608);
        stage.setScene(scene);
        stage.show();
    }

    //------------------------- tạo cảnh báo thoát game ---------------------
    @FXML
    public Button endGameButton;

    @FXML
    public void endGame(ActionEvent event) throws IOException {
        //Tạo hộp xác nhận
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("End Game");
        alert.setHeaderText("You're about to end game!");
        alert.setContentText("If you out game, you'll lose this Game. Click CANCEL to continue!");
        //System.out.println("im here");
        if (alert.showAndWait().get() == ButtonType.OK) {
            HelloApplication helloApplication = new HelloApplication();
            helloApplication.changeScreen("OlympiaStart.fxml", 1080, 608);
        }
    }
    //--------------------------tạo count down -------------------
    @FXML
    public Label countdownLabel;
    public Button countdownButton;
    public Label countdownNextRound;
    @FXML
    public void Countdown(int seconds,Label labelCountDown) {
        Timer timer = new Timer();

        timer.scheduleAtFixedRate(new TimerTask() {
            int remainingSeconds = seconds;

            @Override
            public void run() {
                if (remainingSeconds > 0) {
                    Platform.runLater(() -> {
                        labelCountDown.setText(String.valueOf(remainingSeconds));
                    });//                    if (remainingSeconds > 5) {
//                        System.out.println("Thời gian còn lại: " + remainingSeconds + " giây");
//                    } else if (remainingSeconds >= 0 && remainingSeconds <= 5) {
//                        // You can remove this part if not needed
//                    }
                    if (endR1) {
                        remainingSeconds = 0;
                    } else {
                        remainingSeconds--;
                    }
                } else {
//                    System.out.println("Hết cứu");
                    Platform.runLater(() -> {
                        if(labelCountDown == countdownLabel) {
                            labelCountDown.setText("Time's up!");
                        } else if(labelCountDown == countdownNextRound){
                            labelCountDown.setText("Go....");
                        } else{
                            labelCountDown.setText("Start");
                        }
                    });
                    timer.cancel();
                }
            }
        }, 0, 1000);
    }


    //--------------------------set up thông số ---------------------------
    public static int counter = 0;
    public static int correct = 0;
    public static int wrong = 0;
    public static int Mark = 0;
    // ------------------------set up button, label, textfield, image ---------------------
    @FXML
    public Label countdownLabelReady;
    public ImageView imageReady;
    public Button readyButton;
    public Label annouceNextRound;
    public Label alertAnswer;
    @FXML
    public Label Question;
    public TextField myAnswer;
    public Label Answer;
    public Label score;
    public Button submit;
    @FXML
    public ImageView correctImage;
    public ImageView wrongImage;

    //------------------------------------các hàm show chỉ số-------------------

    public void showQuestion(){
        Question.setVisible(true);
    }
    public void showScore(int mark){
        score.setText(String.valueOf(mark));
        score.setVisible(true);
    }

    @FXML
    public void showImageCorrect() {
        correctImage.setVisible(true);   // hàm hiện ảnh biểu thị correct
        wrongImage.setVisible(false);
    }

    @FXML
    public void showImageWrong() {
        wrongImage.setVisible(true);    // hàm hiện ảnh biểu thị wrong
        correctImage.setVisible(false);
    }

    public void hideImageCheck(){
        wrongImage.setVisible(false);
        correctImage.setVisible(false);
    }
    public void showAnswer(){
        Answer.setVisible(true);
    }
    public void hideAnswer(){
        Answer.setVisible(false);
    }

    public void showNextButton(Button nextButton){
        nextButton.setVisible(true);
    }


    //    //----------------------------Round 1--------------------------
    @FXML
    public Button clickToContinue;
    public Starting starting = new Starting(0);

    @FXML
    public void gotoRound1Review(ActionEvent event) throws IOException {
        HelloApplication helloApplication = new HelloApplication();
        helloApplication.changeScreen("round1Review.fxml", 1080, 608);
//        myAnswer.setOnKeyPressed(new EventHandler<KeyEvent>() {
//            @Override
//            public void handle(KeyEvent keyEvent) {
//                if (keyEvent.getCode().equals(KeyCode.ENTER)) {
//                    try {
//                        submitAnswerRound1();
//                    } catch (IOException e) {
//                        throw new RuntimeException(e);
//                    }
//                }
//            }
//        });
    }

    public boolean endR1 = false;

    public void loadQuestion(){
        if (counter == 0) { //Question 1
            Question.setText("1."+"What is the opposite of \"big\"?");
            showQuestion();
        }
        if (counter == 1){
            Question.setText("2."+"What is the opposite of \"hot\"?");
            showQuestion();
        }
        if(counter == 2){
            Question.setText("3."+"What is the opposite of \"up\"?");
            showQuestion();
        }
        if (counter == 3){
            Question.setText("Go to next round.");
            showQuestion();
            endR1 = true;
            submit.setDisable(true);
            myAnswer.setDisable(true);
            showNextButton(nextToRound2Review);
        }
    }
    public boolean checkAlertAnswer(String ans){
        if (ans.equals("")){
            return true;
        } else {
            return false;
        }
    }
    public boolean checkAnswerRound1(String ans) {
        ans = ans.toLowerCase();
        if (counter == 0) {
            if (checkAlertAnswer(ans)) {
                alertAnswer.setText("Invalid answer");
                alertAnswer.setVisible(true);
                return false;
            }
            if (ans.equals("small")) {
                Answer.setText("small");
                return true;
            } else {
                Answer.setText("small");
                return false;
            }
        }
        if (counter == 1) {
            if (checkAlertAnswer(ans)) {
                alertAnswer.setText("Invalid answer");
                alertAnswer.setVisible(true);
                return false;
            }
            if (ans.equals("cold")) {
                Answer.setText("cold");
                return true;
            } else {
                Answer.setText("cold");
                return false;
            }
        }
        if (counter == 2) {
            if (checkAlertAnswer(ans)) {
                alertAnswer.setText("Invalid answer");
                alertAnswer.setVisible(true);
                return false;
            }
            if (ans.equals("down")) {
                Answer.setText("down");
                return true;
            } else {
                Answer.setText("down");
                return false;
            }
        }
        return false;
    }

    @FXML
    public void submitAnswerRound1() throws IOException {
        String ans = myAnswer.getText().trim();
        if (checkAnswerRound1(ans)) {
            showAnswer();
            showImageCorrect();
            Mark = starting.correctAnsPlus(Mark);
            showScore(Mark);
        } else {
            showAnswer();
            Mark = starting.incorrectAnsMinus(Mark);
            showImageWrong();
            showScore(Mark);
        }
//        if(counter == 3){
////
//        }else{
//            myAnswer.clear();
//            counter++;
//            loadQuestion();
//        }
        myAnswer.clear();
        counter++;
        loadQuestion();
    }

    @FXML
    public void gotoRound1(ActionEvent event) throws IOException {
        HelloApplication helloApplication = new HelloApplication();
        helloApplication.changeScreen("round1.fxml",1080,608);
    }

    public static boolean start = true;

    @FXML
    public void startCountDownRound1() {
        int seconds = 60;
        if (start) {
            Countdown(seconds, countdownLabel);
            //System.out.println(seconds);
            start = false;
        }
    }

    public static boolean readyR1 = true;
    @FXML
    public void readyRound1(){
        countdownLabelReady.setVisible(true);
        if (readyR1) {
            Countdown(6, countdownLabelReady);
            readyR1 = false;
        }
        // Tạo một Timeline với một KeyFrame để thực hiện việc ẩn các thành phần sau khi hàm Countdown kết thúc
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(7), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                imageReady.setVisible(false);
                readyButton.setVisible(false);
                countdownLabelReady.setVisible(false);
                correctImage.setVisible(false);
                wrongImage.setVisible(false);

//                nextToRound2Review.setVisible(false);
                startRound1();
                //submit.setDisable(true);
                start = true;
            }
        }));

        //if (readyR1) {
            // Bắt đầu Timeline
        timeline.play();
        readyR1 = true;
    }
    @FXML
    public void startRound1() {
        // tạo 2 ảnh correct, wrong ở chế độ visible(false)
        //sau khi submit hiện ảnh đúng sai và hiện đáp án, chú ý khi chuyển sang câu hỏi mới phải xóa đáp án câu cũ và ảnh correct/wrong
        // tao điều kiện khi hoàn thành câu cuối cùng sẽ hiện ra chữ + nút để chuyển round
        // nên viết hết vào lớp Starting trong game.Olympia để khời chạy
        hideImageCheck();
        hideAnswer();
        startCountDownRound1();
        showScore(Mark);
        loadQuestion();
    }


    //----------------------------------------round 2---------------------------
    @FXML
    public Button nextToRound2Review;

    @FXML
    public void gotoRound2Review() throws IOException {
        HelloApplication helloApplication = new HelloApplication();
        helloApplication.changeScreen("round2Review.fxml", 1080, 608);
    }

    @FXML
    public void gotoRound2(ActionEvent event) throws IOException {
        HelloApplication helloApplication = new HelloApplication();
        helloApplication.changeScreen("round2.fxml", 1080, 608);
    }

    @FXML
    public void startRound2() {
        // tạo 2 ảnh correct, wrong ở chế độ visible(false)
        //sau khi submit hiện ảnh đúng sai và hiện đáp án, chú ý khi chuyển sang câu hỏi mới phải xóa đáp án câu cũ và ảnh correct/wrong
        // tao điều kiện khi hoàn thành câu cuối cùng sẽ hiện ra chữ + nút để chuyển round
        // nên viết hết vào lớp Starting trong game.Olympia để khời chạy


    }

    //----------------------------------Round 3--------------------------
    @FXML
    public Button nextToRound3Review;

    @FXML
    public void gotoRound3Review() throws IOException {
        HelloApplication helloApplication = new HelloApplication();
        helloApplication.changeScreen("round3Review.fxml", 1080, 608);
    }

    @FXML
    public void gotoRound3(ActionEvent event) throws IOException {
        HelloApplication helloApplication = new HelloApplication();
        helloApplication.changeScreen("round3.fxml", 1080, 608);
    }

    @FXML
    public void startRound3() {
        // tạo 2 ảnh correct, wrong ở chế độ visible(false)
        //sau khi submit hiện ảnh đúng sai và hiện đáp án, chú ý khi chuyển sang câu hỏi mới phải xóa đáp án câu cũ và ảnh correct/wrong
        // tao điều kiện khi hoàn thành câu cuối cùng sẽ hiện ra chữ + nút để chuyển round
        // nên viết hết vào lớp Starting trong game.Olympia để khời chạy

    }

    //----------------------------------Round 4--------------------------
    @FXML
    public Button nextToRound4Review;
    @FXML
    public int packageQuestion;
    @FXML
    public int questionScore;

    public Label PackageQS;
    public Label QSscore;

    @FXML
    public void gotoRound4Review() throws IOException {
        HelloApplication helloApplication = new HelloApplication();
        helloApplication.changeScreen("round4Review.fxml", 1080, 608);
    }

    @FXML
    public void gotoPackageQuestion(ActionEvent event) throws IOException {
        HelloApplication helloApplication = new HelloApplication();
        helloApplication.changeScreen("packageQuestionRound4.fxml", 1080, 608);
    }

    @FXML
    public void gotoPackage40(ActionEvent event) throws IOException {
        packageQuestion = 40;
        HelloApplication helloApplication = new HelloApplication();
        helloApplication.changeScreen("round4.fxml", 1080, 608);
    }

    @FXML
    public void gotoPackage60(ActionEvent event) throws IOException {
        packageQuestion = 60;
        HelloApplication helloApplication = new HelloApplication();
        helloApplication.changeScreen("round4.fxml", 1080, 608);
    }

    @FXML
    public void gotoPackage80(ActionEvent event) throws IOException {
        packageQuestion = 80;
        HelloApplication helloApplication = new HelloApplication();
        helloApplication.changeScreen("round4.fxml", 1080, 608);
    }


    @FXML
    public void startRound4() {
        // tạo 2 ảnh correct, wrong ở chế độ visible(false)
        //sau khi submit hiện ảnh đúng sai và hiện đáp án, chú ý khi chuyển sang câu hỏi mới phải xóa đáp án câu cũ và ảnh correct/wrong
        // tao điều kiện khi hoàn thành câu cuối cùng sẽ hiện ra chữ + nút để chuyển round
        // nên viết hết vào lớp Starting trong game.Olympia để khời chạy
        // chú ý kiểm tra package question đã chọn để đưa ra gói câu hỏi
        if (packageQuestion == 40) {
            //thực hiện


            // gán lại giá trị cũ
            packageQuestion = 0;
        } else if (packageQuestion == 60) {
            //thuc hien


            //reset
            packageQuestion = 0;
        } else if (packageQuestion == 80) {
            //thuc hien

            //reset
            packageQuestion = 0;

        }

    }

    //--------------------------------------bảng điểm-------------
    @FXML
    public Button resultButton;

    @FXML
    public void gotoResult(ActionEvent event) throws IOException {
        HelloApplication helloApplication = new HelloApplication();
        helloApplication.changeScreen("resultOlympia.fxml", 1080, 608);
    }

    @FXML
    public void resultGame() {
        score.setText(String.valueOf(Mark));
    }

    @FXML
    public void returnMenuGame(ActionEvent event) throws IOException {
        HelloApplication helloApplication = new HelloApplication();
        helloApplication.changeScreen("OlympiaStart.fxml", 1080, 608);
    }

}
