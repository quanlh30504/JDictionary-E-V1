package com.example.jdictionaryev1;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class OlympiaStartController extends OlympiaController{
//    //--------------------------tạo count down -------------------
//    @FXML
//    public Label countdownLabel;
//    public Button countdownButton;
//    public Label countdownNextRound;
//    @FXML
//    public void Countdown(int seconds,Label labelCountDown) {
//        Timer timer = new Timer();
//
//        timer.scheduleAtFixedRate(new TimerTask() {
//            int remainingSeconds = seconds;
//
//            @Override
//            public void run() {
//                if (remainingSeconds > 0) {
//                    Platform.runLater(() -> {
//                        labelCountDown.setText(String.valueOf(remainingSeconds));
//                    });//                    if (remainingSeconds > 5) {
////                        System.out.println("Thời gian còn lại: " + remainingSeconds + " giây");
////                    } else if (remainingSeconds >= 0 && remainingSeconds <= 5) {
////                        // You can remove this part if not needed
////                    }
//
//                    remainingSeconds--;
//                } else {
////                    System.out.println("Hết cứu");
//                    Platform.runLater(() -> {
//                        if(labelCountDown == countdownLabel) {
//                            labelCountDown.setText("Time's up!");
//                        }else if(labelCountDown == countdownNextRound){
//                            labelCountDown.setText("Go....");
//                        }else{
//                            labelCountDown.setText("Start");
//                        }
//                    });
//                    timer.cancel();
//                }
//            }
//        }, 0, 1000);
//    }
//


    //----------------------------Round 1--------------------------
//    @FXML
//    public Button clickToContinue;
//    @FXML
//    public void gotoRound1Review(ActionEvent event) throws IOException {
//        HelloApplication helloApplication = new HelloApplication();
//        helloApplication.changeScreen("round1Review.fxml",1080,608);
//    }

//    public void loadQuestion(){
//        Answer.setVisible(false);
//        correctImage.setVisible(false);
//        wrongImage.setVisible(false);
//        alertAnswer.setVisible(false);
//        if (OlympiaController.counter == 0) { //Question 1
//            Question.setText("1.What is the opposite of \"big\"?");
//        }
//        if (OlympiaController.counter == 1){
//            Question.setText("What is the opposite of \"hot\"?");
//        }
//    }
//    public boolean checkAlertAnswer(String ans){
//        if(ans.equals("")){
//            return true;
//        }else return false;
//    }
//    public boolean checkAnswerRound1(String ans){
//        ans.toLowerCase();
//        if(counter == 0){
//            if(checkAlertAnswer(ans)){
//                alertAnswer.setText("Invalid answer");
//                alertAnswer.setVisible(true);
//                return false;
//            }
//            if(ans.equals("small")){
//                Answer.setText("small");
//                Answer.setVisible(true);
//                return true;
//            }else {
//                Answer.setText("small");
//                Answer.setVisible(true);
//                return false;
//            }
//        }
//        if(counter == 1){
//            if(ans.equals("cold")){
//                Answer.setText("cold");
//                Answer.setVisible(true);
//                return true;
//            }else {
//                Answer.setText("cold");
//                Answer.setVisible(true);
//                return false;
//            }
//        }
//        return false;
//    }
//
//    @FXML
//    public void submitAnswerRound1() throws IOException {
//        String ans = myAnswer.getText();
//        ans.toLowerCase();
//        if(checkAnswerRound1(ans)){
//            correctImage.setVisible(true);
//            Mark += 20;    // 20 score/ correct in round 1
//        }else{
//            wrongImage.setVisible(true);
//        }
//        if(counter == 10){
//            annouceNextRound.setText("Next round after: ");
//            annouceNextRound.setVisible(true);
//            int seconds = 4;
//            Countdown(seconds,countdownNextRound);
//            counter = 0;
//            //set up lai Rule
//            imageReady.setVisible(true);
//            readyButton.setVisible(true);
//            HelloApplication helloApplication = new HelloApplication();
//            helloApplication.changeScreen("round2Review.fxml",1080,608);
//        }else{
//            counter++;
//            loadQuestion();
//        }
//    }
//
//
//    @FXML
//    public void gotoRound1(ActionEvent event) throws IOException {
//        HelloApplication helloApplication = new HelloApplication();
//        helloApplication.changeScreen("round1.fxml",1080,608);
//
//    }
//    @FXML
//    public void startCountDownRound1() {
//        int seconds = 10;
//        Countdown(seconds,countdownLabel);
//    }
//    @FXML
//    public void readyRound1(){
//        countdownLabelReady.setVisible(true);
//        Countdown(3,countdownLabelReady);
//        imageReady.setVisible(false);
//        readyButton.setVisible(false);
//        countdownLabelReady.setVisible(false);
//        startRound1();
//    }
//    @FXML
//    public void startRound1() {
//        // tạo 2 ảnh correct, wrong ở chế độ visible(false)
//        //sau khi submit hiện ảnh đúng sai và hiện đáp án, chú ý khi chuyển sang câu hỏi mới phải xóa đáp án câu cũ và ảnh correct/wrong
//        // tao điều kiện khi hoàn thành câu cuối cùng sẽ hiện ra chữ + nút để chuyển round
//        // nên viết hết vào lớp Starting trong game.Olympia để khời chạy
//
//
//    }
//    @FXML
//    public Button clickToContinue;
//

}
