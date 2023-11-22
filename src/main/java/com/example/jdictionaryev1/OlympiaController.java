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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.*;

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
    public Label countdownLabel1;
    public Label countdownLabel2;
    public Label countdownLabel3;
    public Label countdownLabel4;
    public Button countdownButton;
    public Label countdownNextRound;

    @FXML
    public void Countdown(int seconds, Label labelCountDown) {
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
                        if (labelCountDown == countdownLabel1 || labelCountDown == countdownLabel2 ) {
                            labelCountDown.setText("Time's up!");
                        }else if(labelCountDown == countdownLabel3){
                            labelCountDown.setText("Bump!!!");
                            try {
                                submitAnswerRound3();
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }else if(labelCountDown == countdownLabel4) {
                            labelCountDown.setText("Bump!!!");
                            try {
                                submitAnswerRound4();
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        else if (labelCountDown == countdownNextRound) {
                            labelCountDown.setText("Go....");
                        } else {
                            labelCountDown.setText("Start");
                        }
                    });
                    timer.cancel();
                }
            }
        }, 0, 1000);
    }
    //------------------------tạo delay----------------------------------
    public static void delay(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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
    public Label AnswerWrong;

    public Label score;
    public Button submit;
    @FXML
    public ImageView correctImage;
    public ImageView wrongImage;

    //------------------------------------các hàm show chỉ số-------------------

    public void showQuestion() {
        Question.setVisible(true);
    }

    public void showScore(int mark) {
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

    public void hideImageCheck() {
        wrongImage.setVisible(false);
        correctImage.setVisible(false);
    }
    public void hideImageWrong(){
        wrongImage.setVisible(false);
    }
    public void hideImageCorrect(){
        correctImage.setVisible(false);
    }

    public void showAnswer() {
        Answer.setVisible(true);
    }
    public void showAnswerWrong(){
        AnswerWrong.setVisible(true);
    }

    public void hideAnswer() {
        Answer.setVisible(false);
    }
    public void hideAnswerWrong(){
        AnswerWrong.setVisible(false);
    }

    public void showNextButton(Button nextButton) {
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
    }

    public boolean endR1 = false;

    public void loadQuestion1() {


        if (counter == 0) { //Question 1
            Question.setText("1." + "What is the opposite of \"big\"?");
            showQuestion();
        }
        if (counter == 1) {
            Question.setText("2." + "What is the opposite of \"hot\"?");
            showQuestion();
        }
        if (counter == 2) {
            Question.setText("3." + "What is the opposite of \"up\"?");
            showQuestion();
        }
        if (counter == 3) {
            Question.setText("Go to next round.");
            showQuestion();
            endR1 = true;
            counter = 0;
            submit.setDisable(true);
            myAnswer.setDisable(true);
            showNextButton(nextToRound2Review);
        }
    }

    public boolean checkAlertAnswer(String ans) {
        if (ans.equals("")) {
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
                Answer.setText("small");
                return false;
            }
            if (ans.equals("small")) {
                Answer.setText("small");
                alertAnswer.setVisible(false);
                return true;
            } else {
                Answer.setText("small");
                alertAnswer.setVisible(false);
                return false;
            }
        }
        if (counter == 1) {
            if (checkAlertAnswer(ans)) {
                alertAnswer.setText("Invalid answer");
                alertAnswer.setVisible(true);
                Answer.setText("cold");
                return false;
            }
            if (ans.equals("cold")) {
                Answer.setText("cold");
                alertAnswer.setVisible(false);
                return true;
            } else {
                Answer.setText("cold");
                alertAnswer.setVisible(false);
                return false;
            }
        }
        if (counter == 2) {
            if (checkAlertAnswer(ans)) {
                alertAnswer.setText("Invalid answer");
                alertAnswer.setVisible(true);
                Answer.setText("down");
                return false;
            }
            if (ans.equals("down")) {
                Answer.setText("down");
                alertAnswer.setVisible(false);
                return true;
            } else {
                Answer.setText("down");
                alertAnswer.setVisible(false);
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
        myAnswer.clear();
        counter++;
        loadQuestion1();
    }

    @FXML
    public void gotoRound1(ActionEvent event) throws IOException {
        HelloApplication helloApplication = new HelloApplication();
        helloApplication.changeScreen("round1.fxml", 1080, 608);
    }

    public static boolean start = true;

    @FXML
    public void startCountDownRound1() {
        int seconds = 60;
        if (start) {
            Countdown(seconds, countdownLabel1);
            //System.out.println(seconds);
            start = false;
        }
    }

    public static boolean readyR1 = true;

    @FXML
    public void readyRound1() {
        readyButton.setDisable(true);
        countdownLabelReady.setVisible(true);
        if (readyR1) {
            Countdown(6, countdownLabelReady);
            readyR1 = false;
        }
        // Tạo một Timeline với một KeyFrame để thực hiện việc ẩn các thành phần sau khi hàm Countdown kết thúc
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(6), new EventHandler<ActionEvent>() {
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
        loadQuestion1();
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
    public Label myAnswerLabel;
    public Label myanswerIs;
    public Label correctAnswerIs;
    @FXML
    public Button nextToRound3Review;


    @FXML
    public void gotoRound3Review() throws IOException {
        HelloApplication helloApplication = new HelloApplication();
        helloApplication.changeScreen("round3Review.fxml", 1080, 608);
    }

    public boolean endR3 = false;
    //Tạo và show các hình ảnh câu hỏi
    @FXML
    public ImageView imageViewQuestion;
    private List<List<Image>> listImages = new ArrayList<>();
    private int currentIndex;
    Timeline timeline;

    // Need to modify
    @FXML
    private void loadImageQuestion() {
        // Tạo list 3 ảnh 1
        // List 1
        List<Image> images1 = new ArrayList<>();
        images1.add(new Image(getClass().getResource("/GameOlympia/QuestionRound3/question1/1.png").toExternalForm()));
        images1.add(new Image(getClass().getResource("/GameOlympia/QuestionRound3/question1/2.png").toExternalForm()));
        images1.add(new Image(getClass().getResource("/GameOlympia/QuestionRound3/question1/3.png").toExternalForm()));
        images1.add(new Image(getClass().getResource("/GameOlympia/QuestionRound3/question1/4.png").toExternalForm()));

        // List 2
        List<Image> images2 = new ArrayList<>();
        images2.add(new Image(getClass().getResource("/GameOlympia/QuestionRound3/question2/1.png").toExternalForm()));
        images2.add(new Image(getClass().getResource("/GameOlympia/QuestionRound3/question2/2.png").toExternalForm()));
        images2.add(new Image(getClass().getResource("/GameOlympia/QuestionRound3/question2/3.png").toExternalForm()));
        images2.add(new Image(getClass().getResource("/GameOlympia/QuestionRound3/question2/4.png").toExternalForm()));
        // List 3
        List<Image> images3 = new ArrayList<>();
        images3.add(new Image(getClass().getResource("/GameOlympia/QuestionRound3/question3/1.png").toExternalForm()));
        images3.add(new Image(getClass().getResource("/GameOlympia/QuestionRound3/question3/2.png").toExternalForm()));
        images3.add(new Image(getClass().getResource("/GameOlympia/QuestionRound3/question3/3.png").toExternalForm()));
        images3.add(new Image(getClass().getResource("/GameOlympia/QuestionRound3/question3/4.png").toExternalForm()));


        //Thêm các list vào 1 list chung
        listImages.add(images1);
        listImages.add(images2);
        listImages.add(images3);

        // Initialize currentIndex
        currentIndex = 0;

        // Display the first image
        imageViewQuestion.setImage(listImages.get(counter).get(currentIndex));
        // Set up a Timeline to switch images every 5 seconds
        timeline = new Timeline(
                new KeyFrame(
                        Duration.seconds(60 / listImages.size()),
                        this::nextImage
                )
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    @FXML
    public void nextQuestionImage() {
        // check counter == 3 thì show nút next to round 4
        if (counter == 3) {
            // chuyển màn
            counter = 0;
            endR3 = true;
            submit.setDisable(true);
            myAnswer.setDisable(true);
            showNextButton(nextToRound4Review);
            imageViewQuestion.setVisible(false);
            return;
        }
        delay(3000);
        currentIndex = 0;
        // Display the first image
        imageViewQuestion.setImage(listImages.get(counter).get(currentIndex));

        System.out.println(listImages.size());
        startCountDownRound3();
        // Set up a Timeline to switch images every 5 seconds
        timeline = new Timeline(
                new KeyFrame(
                        Duration.seconds(6),
                        this::nextImage
                )
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    @FXML
    public void nextImage(ActionEvent event) {
        currentIndex = (currentIndex + 1) % listImages.get(counter).size();
        imageViewQuestion.setImage(listImages.get(counter).get(currentIndex));

        // Kiểm tra nếu đã đến hình ảnh cuối cùng, thì dừng Timeline
        if (currentIndex == listImages.get(counter).size() - 1) {
            // khi đến ảnh cuối cùng sẽ bắt đầu đếm thời gian
            //startCountDownRound3();
            timeline.stop();
        }
    }

    // Hard-code
    public boolean checkAnswerRound3(String ans) {
        ans = ans.toLowerCase();
        if (counter == 0) {
            if (checkAlertAnswer(ans)) {
                alertAnswer.setText("Invalid answer");
                alertAnswer.setVisible(true);
                AnswerWrong.setText("whale");
                return false;
            }
            if (ans.equals("whale")) {
                Answer.setText("whale");
                alertAnswer.setVisible(false);
                return true;
            } else {
                AnswerWrong.setText("whale");
                alertAnswer.setVisible(false);
                return false;
            }
        }
        if (counter == 1) {
            if (checkAlertAnswer(ans)) {
                alertAnswer.setText("Invalid answer");
                alertAnswer.setVisible(true);
                AnswerWrong.setText("football / soccer");
                return false;
            }
            if (ans.equals("football") || ans.equals("soccer")) {
                Answer.setText("football / soccer");
                alertAnswer.setVisible(false);
                return true;
            } else {
                AnswerWrong.setText("football / soccer");
                alertAnswer.setVisible(false);
                return false;
            }
        }
        if (counter == 2) {
            if (checkAlertAnswer(ans)) {
                alertAnswer.setText("Invalid answer");
                alertAnswer.setVisible(true);
                AnswerWrong.setText("hospital");
                return false;
            }
            if (ans.equals("hospital")) {
                Answer.setText("hospital");
                alertAnswer.setVisible(false);
                return true;
            } else {
                AnswerWrong.setText("hospital");
                alertAnswer.setVisible(false);
                return false;
            }
        }
        return false;
    }
    @FXML
    public void showMyAnsRound3(){
        hideImageCheck();
        alertAnswer.setVisible(false);
        correctAnswerIs.setVisible(false);
        Answer.setVisible(false);
        AnswerWrong.setVisible(false);
        myanswerIs.setVisible(true);
        String ans = myAnswer.getText();
        myAnswerLabel.setText(ans);
        myAnswerLabel.setVisible(true);
        myAnswer.clear();
    }

    @FXML
    public void submitAnswerRound3() throws IOException {
        myAnswerLabel.setVisible(false);
        myanswerIs.setVisible(false);
        String ans = myAnswerLabel.getText().trim();
        if (checkAnswerRound3(ans)) {
            showAnswer();
            correctAnswerIs.setVisible(true);
            showImageCorrect();
            Mark += 20;
            showScore(Mark);
        } else {
            showAnswerWrong();
            correctAnswerIs.setVisible(true);
            Mark -= 5;
            showImageWrong();
            showScore(Mark);
        }
        timeline.stop();
        myAnswer.clear();
        counter++;
        countdownLabel3.setText("60");
//        delay(2000);
//        correctAnswerIs.setVisible(false);
//        Answer.setVisible(false);
        nextQuestionImage();
    }

//    public static boolean start = true;

    @FXML
    public void startCountDownRound3() {
        int seconds = 61;
//        if (start) {
//            Countdown(seconds, countdownLabel3);
//            //System.out.println(seconds);
//            start = false;
//        }
        Countdown(seconds,countdownLabel3);
    }

    @FXML
    public void gotoRound3(ActionEvent event) throws IOException {
        HelloApplication helloApplication = new HelloApplication();
        helloApplication.changeScreen("round3.fxml", 1080, 608);
    }

    public static boolean readyR3 = true;

    @FXML
    public void readyRound3() {
        readyButton.setDisable(true);
        countdownLabelReady.setVisible(true);
        if (readyR3) {
            Countdown(6, countdownLabelReady);
            readyR3 = false;
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
                delay(2000);
                startRound3();
                start = true;
            }
        }));

        // Bắt đầu Timeline
        timeline.play();
        readyR3 = true;
    }

    @FXML
    public void startRound3() {
        myAnswer.setDisable(false);
        submit.setDisable(false);
        countdownLabel3.setVisible(true);
        hideImageCheck();
        hideAnswer();
        showScore(Mark);
        loadImageQuestion();
        startCountDownRound3();
    }

    //----------------------------------Round 4--------------------------
    @FXML
    public Button nextToRound4Review;

    @FXML
    public int questionScore;

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
    private CheckBox checkBox1;

    @FXML
    private CheckBox checkBox2;

    @FXML
    private CheckBox checkBox3;

    @FXML
    private CheckBox checkBox4;

    @FXML
    private CheckBox checkBox5;

    @FXML
    private CheckBox checkBox6;

    @FXML
    private CheckBox checkBox7;

    @FXML
    private CheckBox checkBox8;

    @FXML
    private CheckBox checkBox9;

    @FXML
    private Button playRound4;

    public static List<String> listQuestion = new ArrayList<>() ;
    public CheckBox[] checkBoxes = new CheckBox[11];
    @FXML
    public void loadCheckBox() {
//        listQuestion = new ArrayList<>();
        // checkbox
        checkBox1.setOnAction(event -> handleCheckBoxAction(checkBox1));
        checkBoxes[1] = checkBox1;
        checkBox2.setOnAction(event -> handleCheckBoxAction(checkBox2));
        checkBoxes[2] = checkBox2;
        checkBox3.setOnAction(event -> handleCheckBoxAction(checkBox3));
        checkBoxes[3] = checkBox3;
        checkBox4.setOnAction(event -> handleCheckBoxAction(checkBox4));
        checkBoxes[4] = checkBox4;
        checkBox5.setOnAction(event -> handleCheckBoxAction(checkBox5));
        checkBoxes[5] = checkBox5;
        checkBox6.setOnAction(event -> handleCheckBoxAction(checkBox6));
        checkBoxes[6] = checkBox6;
        checkBox7.setOnAction(event -> handleCheckBoxAction(checkBox7));
        checkBoxes[7] = checkBox7;
        checkBox8.setOnAction(event -> handleCheckBoxAction(checkBox8));
        checkBoxes[8] = checkBox8;
        checkBox9.setOnAction(event -> handleCheckBoxAction(checkBox9));
        checkBoxes[9] = checkBox9;
        //nút "Next"
        playRound4.setOnAction(event -> {
            try {
                gotoRound4();
                Thread.sleep(4000);
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @FXML
    public void handleCheckBoxAction(CheckBox checkBox) {
        if (checkBox.isSelected()) {
            // Nếu ô checkbox được chọn, thêm giá trị của ô đó vào danh sách
            listQuestion.add(checkBox.getText());
            if (listQuestion.size() == 3) {
                checkBox1.setDisable(true);
                checkBox2.setDisable(true);
                checkBox3.setDisable(true);
                checkBox4.setDisable(true);
                checkBox5.setDisable(true);
                checkBox6.setDisable(true);
                checkBox7.setDisable(true);
                checkBox8.setDisable(true);
                checkBox9.setDisable(true);
                playRound4.setVisible(true);
            }
        } else {
            // Nếu ô checkbox bị hủy chọn, loại bỏ giá trị của ô đó khỏi danh sách
            listQuestion.remove(checkBox.getText());
        }
        setSwitchOnOffOtherBox(checkBox);
    }

    public void setSwitchOnOffOtherBox(CheckBox checkBox) {
        if (checkBox.equals(checkBox1)) {
            //checkBox2.setDisable(bool(1 - convertToInt(checkBox2.isDisable())));
            //checkBox3.setDisable(bool(1 - convertToInt(checkBox3.isDisable())));
            checkBox4.setDisable(bool(1 - convertToInt(checkBox4.isDisable())));
            checkBox7.setDisable(bool(1 - convertToInt(checkBox7.isDisable())));
        } else if (checkBox.equals(checkBox2)) {
            //checkBox1.setDisable(bool(1 - convertToInt(checkBox1.isDisable())));
            //checkBox3.setDisable(bool(1 - convertToInt(checkBox3.isDisable())));
            checkBox5.setDisable(bool(1 - convertToInt(checkBox5.isDisable())));
            checkBox8.setDisable(bool(1 - convertToInt(checkBox8.isDisable())));
        } else if (checkBox.equals(checkBox3)) {
            //checkBox1.setDisable(bool(1 - convertToInt(checkBox1.isDisable())));
            //checkBox2.setDisable(bool(1 - convertToInt(checkBox2.isDisable())));
            checkBox6.setDisable(bool(1 - convertToInt(checkBox6.isDisable())));
            checkBox9.setDisable(bool(1 - convertToInt(checkBox9.isDisable())));
        } else if (checkBox.equals(checkBox4)) {
            checkBox5.setDisable(bool(1 - convertToInt(checkBox5.isDisable())));
            checkBox6.setDisable(bool(1 - convertToInt(checkBox6.isDisable())));
//            checkBox1.setDisable(bool(1 - convertToInt(checkBox1.isDisable())));
//            checkBox7.setDisable(bool(1 - convertToInt(checkBox7.isDisable())));
        } else if (checkBox.equals(checkBox5)) {
//            checkBox6.setDisable(bool(1 - convertToInt(checkBox6.isDisable())));
//            checkBox4.setDisable(bool(1 - convertToInt(checkBox4.isDisable())));
            checkBox2.setDisable(bool(1 - convertToInt(checkBox2.isDisable())));
            checkBox8.setDisable(bool(1 - convertToInt(checkBox8.isDisable())));
        } else if (checkBox.equals(checkBox6)) {
//            checkBox4.setDisable(bool(1 - convertToInt(checkBox4.isDisable())));
//            checkBox5.setDisable(bool(1 - convertToInt(checkBox5.isDisable())));
            checkBox3.setDisable(bool(1 - convertToInt(checkBox3.isDisable())));
            checkBox9.setDisable(bool(1 - convertToInt(checkBox9.isDisable())));
        } else if (checkBox.equals(checkBox7)) {
//            checkBox8.setDisable(bool(1 - convertToInt(checkBox8.isDisable())));
//            checkBox9.setDisable(bool(1 - convertToInt(checkBox9.isDisable())));
            checkBox1.setDisable(bool(1 - convertToInt(checkBox1.isDisable())));
            checkBox4.setDisable(bool(1 - convertToInt(checkBox4.isDisable())));
        } else if (checkBox.equals(checkBox8)) {
//            checkBox9.setDisable(bool(1 - convertToInt(checkBox9.isDisable())));
//            checkBox7.setDisable(bool(1 - convertToInt(checkBox7.isDisable())));
            checkBox2.setDisable(bool(1 - convertToInt(checkBox2.isDisable())));
            checkBox5.setDisable(bool(1 - convertToInt(checkBox5.isDisable())));
        } else {
//            checkBox8.setDisable(bool(1 - convertToInt(checkBox8.isDisable())));
//            checkBox7.setDisable(bool(1 - convertToInt(checkBox7.isDisable())));
            checkBox3.setDisable(bool(1 - convertToInt(checkBox3.isDisable())));
            checkBox6.setDisable(bool(1 - convertToInt(checkBox6.isDisable())));
        }

    }

    private int getIndex(CheckBox checkBox) {
        if (checkBox.equals(checkBox1)) {
            return 1;
        } else if (checkBox.equals(checkBox2)) {
            return 2;
        } else if (checkBox.equals(checkBox3)) {
            return 3;
        } else if (checkBox.equals(checkBox4)) {
            return 4;
        } else if (checkBox.equals(checkBox5)) {
            return 5;
        } else if (checkBox.equals(checkBox6)) {
            return 6;
        } else if (checkBox.equals(checkBox7)) {
            return 7;
        } else if (checkBox.equals(checkBox8)) {
            return 8;
        } else {
            return 9;
        }
    }

    private boolean bool(int i) {
        return (i == 0);
    }

    private int convertToInt(boolean bool) {
        if (bool) {
            return 0;
        } else return 1;
    }

    private void checkIfSelected(CheckBox checkBox) {
        if (!checkBox.isSelected()) {
            checkBox.setDisable(bool(1 - convertToInt(checkBox.isDisable())));
        }
    }

    public static boolean readyR4 = true;

    @FXML
    public void readyRound4() {
        readyButton.setDisable(true);
        countdownLabelReady.setVisible(true);
        if (readyR4) {
            Countdown(6, countdownLabelReady);
            readyR4 = false;
        }
        // Tạo một Timeline với một KeyFrame để thực hiện việc ẩn các thành phần sau khi hàm Countdown kết thúc
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(7), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                imageReady.setVisible(false);
                readyButton.setVisible(false);
                countdownLabelReady.setVisible(false);
                loadCheckBox();
            }
        }));
        // Bắt đầu Timeline
        timeline.play();
        readyR4 = true;
    }

    @FXML
    public void gotoRound4() throws IOException {
        // In ra giá trị đã chọn
//        System.out.println("Selected values: " + listQuestion);

        HelloApplication helloApplication = new HelloApplication();
        helloApplication.changeScreen("round4.fxml", 1080, 608);
    }

    public boolean endR4 = false;

    List<String> package10 ;
    List<String> ansPackage10;
    List<String> package20 ;
    List<String> ansPackage20;
    List<String> package30 ;
    List<String> ansPackage30;

    public void loadPackageQuesion() {
        System.out.println("counter: " + counter);
        System.out.println("go to loadPackageQuestion");

        package10 = new ArrayList<>();
        package20 = new ArrayList<>();
        package30 = new ArrayList<>();
        ansPackage10 = new ArrayList<>();
        ansPackage20 = new ArrayList<>();
        ansPackage30 = new ArrayList<>();

        // Them cau hoi package 10
        package10.add("What is the opposite of \"fast\"");
        ansPackage10.add("Slow");
        package10.add("Provide a synonym for the word \"happy.\"");
        ansPackage10.add("Joyful");
        package10.add("What is the opposite of \"dark\"");
        ansPackage10.add("Light");

        // them cau hoi package 20
        package20.add(" Identify the antonym for the word \"expand.\"");
        ansPackage20.add("Contract");
        package20.add(" Identify the antonym for the word \"generous.\"");
        ansPackage20.add("Stingy");
        package20.add("Give a one-word synonym for \"enthusiastic.\"");
        ansPackage20.add("Eager");
        // them cau hoi package 30
        package30.add("Provide a word that means \"the ability to speak or write several languages.\"");
        ansPackage30.add("Multilingualism");
        package30.add("Provide a word that means \"the simultaneous occurrence of events that appear significantly related but have no discernible causal connection.\"");
        ansPackage30.add("Synchronicity");
        package30.add("What is the term for the study of the origins and historical development of words?");
        ansPackage30.add("Etymology");
    }

    public int idQuestion10 = -1;
    public int idQuestion20 = -1;
    public int idQuestion30 = -1;

    public int randomQuestion(List mylist) {
        if (mylist == null || mylist.isEmpty()) {
            throw new IllegalArgumentException("List is null or empty");
        }
        Random random = new Random();
        int index = random.nextInt(mylist.size());
        return index;

    }

    public void loadQuestion4() {
//        System.out.println("go to load question");
//        alertAnswer.setVisible(false);
        if (counter == 3) {
            Question.setText("Congratulate!");
            showQuestion();
            endR4 = true;
            counter = 0;
            submit.setDisable(true);
            myAnswer.setDisable(true);
            Answer.setDisable(true);
            starHopeButton.setDisable(true);
            showNextButton(resultButton); // hiện nút endgame dẫn ra bảng kết quả
        }else {
            countdownLabel4.setText("20");
            startCountDownRound4(21);

            if (counter == 0) { //Question 1

                if (listQuestion.get(counter).equals("10")) {
                    if (idQuestion10 == -1) {
                        idQuestion10 = randomQuestion(package10);
                    } else {
                        int oldID = idQuestion10;
                        idQuestion10 = randomQuestion(package10);
                        while (idQuestion10 == oldID) {
                            idQuestion10 = randomQuestion(package10);
                        }
                    }
                    QSscore.setText("10");
                    String ques = package10.get(idQuestion10);
                    Question.setText("1." + ques);
                    showQuestion();
                } else if (listQuestion.get(counter).equals("20")) {
                    if (idQuestion20 == -1) {
                        idQuestion20 = randomQuestion(package20);
                    } else {
                        int oldID = idQuestion20;
                        idQuestion20 = randomQuestion(package20);
                        while (idQuestion20 == oldID) {
                            idQuestion20 = randomQuestion(package20);
                        }
                    }
                    QSscore.setText("20");
                    String ques = package20.get(idQuestion20);
                    Question.setText("1." + ques);
                    showQuestion();
                } else if (listQuestion.get(counter).equals("30")) {
                    if (idQuestion30 == -1) {
                        idQuestion30 = randomQuestion(package30);
                    } else {
                        int oldID = idQuestion30;
                        idQuestion30 = randomQuestion(package30);
                        while (idQuestion30 == oldID) {
                            idQuestion30 = randomQuestion(package30);
                        }
                    }
                    QSscore.setText("30");
                    String ques = package30.get(idQuestion30);
                    Question.setText("1." + ques);
                    showQuestion();
                }
            }
            if (counter == 1) {
                if (listQuestion.get(counter).equals("10")) {
                    if (idQuestion10 == -1) {
                        idQuestion10 = randomQuestion(package10);
                    } else {
                        int oldID = idQuestion10;
                        idQuestion10 = randomQuestion(package10);
                        while (idQuestion10 == oldID) {
                            idQuestion10 = randomQuestion(package10);
                        }
                    }
                    QSscore.setText("10");
                    String ques = package10.get(idQuestion10);
                    Question.setText("2." + ques);
                    showQuestion();
                } else if (listQuestion.get(counter).equals("20")) {
                    if (idQuestion20 == -1) {
                        idQuestion20 = randomQuestion(package20);
                    } else {
                        int oldID = idQuestion20;
                        idQuestion20 = randomQuestion(package20);
                        while (idQuestion20 == oldID) {
                            idQuestion20 = randomQuestion(package20);
                        }
                    }
                    QSscore.setText("20");
                    String ques = package20.get(idQuestion20);
                    Question.setText("2." + ques);
                    showQuestion();
                } else if (listQuestion.get(counter).equals("30")) {
                    if (idQuestion30 == -1) {
                        idQuestion30 = randomQuestion(package30);
                    } else {
                        int oldID = idQuestion30;
                        idQuestion30 = randomQuestion(package30);
                        while (idQuestion30 == oldID) {
                            idQuestion30 = randomQuestion(package30);
                        }
                    }
                    QSscore.setText("30");
                    String ques = package30.get(idQuestion30);
                    Question.setText("2." + ques);
                    showQuestion();
                }
            }
            if (counter == 2) {
                if (listQuestion.get(counter).equals("10")) {
                    if (idQuestion10 == -1) {
                        idQuestion10 = randomQuestion(package10);
                    } else {
                        int oldID = idQuestion10;
                        idQuestion10 = randomQuestion(package10);
                        while (idQuestion10 == oldID) {
                            idQuestion10 = randomQuestion(package10);
                        }
                    }
                    QSscore.setText("10");
                    String ques = package10.get(idQuestion10);
                    Question.setText("3." + ques);
                    showQuestion();
                } else if (listQuestion.get(counter).equals("20")) {
                    if (idQuestion20 == -1) {
                        idQuestion20 = randomQuestion(package20);
                    } else {
                        int oldID = idQuestion20;
                        idQuestion20 = randomQuestion(package20);
                        while (idQuestion20 == oldID) {
                            idQuestion20 = randomQuestion(package20);
                        }
                    }
                    QSscore.setText("20");
                    String ques = package20.get(idQuestion20);
                    Question.setText("3." + ques);
                    showQuestion();
                } else if (listQuestion.get(counter).equals("30")) {
                    if (idQuestion30 == -1) {
                        idQuestion30 = randomQuestion(package30);
                    } else {
                        int oldID = idQuestion30;
                        idQuestion30 = randomQuestion(package30);
                        while (idQuestion30 == oldID) {
                            idQuestion30 = randomQuestion(package30);
                        }
                    }
                    QSscore.setText("30");
                    String ques = package30.get(idQuestion30);
                    Question.setText("3." + ques);
                    showQuestion();
                }
            }
        }
    }


    public boolean checkAnswerRound4(String ans) {
        ans = ans.toLowerCase();
        if (counter == 0) {
            if (checkAlertAnswer(ans)) {
                alertAnswer.setText("Invalid answer");
                alertAnswer.setVisible(true);
                return false;
            }
            alertAnswer.setVisible(false);
            if (listQuestion.get(counter).equals("10")) {
                String ansCorrect = ansPackage10.get(idQuestion10).toLowerCase();
                if (ans.equals(ansCorrect)) {
                    Answer.setText(ansCorrect);
                    return true;
                } else {
                    AnswerWrong.setText(ansCorrect);
                    return false;
                }
            } else if (listQuestion.get(counter).equals("20")) {
                String ansCorrect = ansPackage20.get(idQuestion20).toLowerCase();
                if (ans.equals(ansCorrect)) {
                    Answer.setText(ansCorrect);
                    return true;
                } else {
                    AnswerWrong.setText(ansCorrect);
                    return false;
                }
            } else if (listQuestion.get(counter).equals("30")) {
                String ansCorrect = ansPackage30.get(idQuestion30).toLowerCase();
                if (ans.equals(ansCorrect)) {
                    Answer.setText(ansCorrect);
                    return true;
                } else {
                    AnswerWrong.setText(ansCorrect);
                    return false;
                }
            }

        }
        if (counter == 1) {
            if (checkAlertAnswer(ans)) {
                alertAnswer.setText("Invalid answer");
                alertAnswer.setVisible(true);
                return false;
            }
            alertAnswer.setVisible(false);
            if (listQuestion.get(counter).equals("10")) {
                String ansCorrect = ansPackage10.get(idQuestion10).toLowerCase();
                if (ans.equals(ansCorrect)) {
                    Answer.setText(ansCorrect);
                    return true;
                } else {
                    AnswerWrong.setText(ansCorrect);
                    return false;
                }
            } else if (listQuestion.get(counter).equals("20")) {
                String ansCorrect = ansPackage20.get(idQuestion20).toLowerCase();
                if (ans.equals(ansCorrect)) {
                    Answer.setText(ansCorrect);
                    return true;
                } else {
                    AnswerWrong.setText(ansCorrect);
                    return false;
                }
            } else if (listQuestion.get(counter).equals("30")) {
                String ansCorrect = ansPackage30.get(idQuestion30).toLowerCase();
                if (ans.equals(ansCorrect)) {
                    Answer.setText(ansCorrect);
                    return true;
                } else {
                    AnswerWrong.setText(ansCorrect);
                    return false;
                }
            }
        }
        if (counter == 2) {
            if (checkAlertAnswer(ans)) {
                alertAnswer.setText("Invalid answer");
                alertAnswer.setVisible(true);
                return false;
            }
            alertAnswer.setVisible(false);
            if (listQuestion.get(counter).equals("10")) {
                String ansCorrect = ansPackage10.get(idQuestion10).toLowerCase();
                if (ans.equals(ansCorrect)) {
                    Answer.setText(ansCorrect);
                    return true;
                } else {
                    AnswerWrong.setText(ansCorrect);
                    return false;
                }
            } else if (listQuestion.get(counter).equals("20")) {
                String ansCorrect = ansPackage20.get(idQuestion20).toLowerCase();
                if (ans.equals(ansCorrect)) {
                    Answer.setText(ansCorrect);
                    return true;
                } else {
                    AnswerWrong.setText(ansCorrect);
                    return false;
                }
            } else if (listQuestion.get(counter).equals("30")) {
                String ansCorrect = ansPackage30.get(idQuestion30).toLowerCase();
                if (ans.equals(ansCorrect)) {
                    Answer.setText(ansCorrect);
                    return true;
                } else {
                    AnswerWrong.setText(ansCorrect);
                    return false;
                }
            }
        }
        return false;
    }
    @FXML
    public void showMyAnsRound4(){
        hideImageCheck();
        alertAnswer.setVisible(false);
        correctAnswerIs.setVisible(false);
        Answer.setVisible(false);
        AnswerWrong.setVisible(false);
        myanswerIs.setVisible(true);
        String ans = myAnswer.getText();
        myAnswerLabel.setText(ans);
        myAnswerLabel.setVisible(true);
        myAnswer.clear();
    }
    public boolean checkStar = false;
    @FXML
    public void submitAnswerRound4() throws IOException {
        myAnswerLabel.setVisible(false);
        myanswerIs.setVisible(false);
        starHopeImage.setVisible(false);
        String ans = myAnswerLabel.getText().trim();
        if (checkAnswerRound4(ans)) {
            showAnswer();
            correctAnswerIs.setVisible(true);
            hideAnswerWrong();
            showImageCorrect();
            hideImageWrong();
            int add = 0;
            if(listQuestion.get(counter).equals("10")){
                if(checkStar){
                    add = 20;
                    checkStar = false;
                }else{
                    add = 10;
                }
            }else if(listQuestion.get(counter).equals("20")){
                if(checkStar){
                    add = 40;
                    checkStar = false;
                }else{
                    add = 20;
                }
            }else if(listQuestion.get(counter).equals("30")){
                if(checkStar){
                    add = 60;
                    checkStar = false;
                }else{
                    add = 30;
                }
            }
            Mark += add;
            showScore(Mark);
        } else {
            correctAnswerIs.setVisible(true);
            showAnswerWrong();
            hideAnswer();
            showImageWrong();
            hideImageCorrect();
            int subtract = 0;
            if(listQuestion.get(counter).equals("10")){
                if(checkStar){
                    subtract = 10;
                    checkStar = false;
                }
            }else if(listQuestion.get(counter).equals("20")){
                if(checkStar){
                    subtract = 20;
                    checkStar = false;
                }
            }else if(listQuestion.get(counter).equals("30")){
                if(checkStar){
                    subtract = 30;
                    checkStar = false;
                }
            }
            Mark -= subtract;
            showScore(Mark);
        }
        myAnswer.clear();
        counter++;
        loadQuestion4();
    }

    @FXML
    public Button startRound4Button;
    public Button starHopeButton;
    public ImageView starHopeImage;
    public Label labelPackage1;
    public Label labelPackage2;
    public Label labelPackage3;

    public void startCountDownRound4(int time) {
        int seconds = time;
//        if (start) {
//            Countdown(seconds, countdownLabel3);
//            //System.out.println(seconds);
//            start = false;
//        }
        Countdown(seconds,countdownLabel4);
    }

    @FXML
    public void starHopeFunction(){
        starHopeImage.setVisible(true);
        checkStar = true;
        starHopeButton.setDisable(true);
    }
    @FXML
    public void startRound4() {
        // show các câu hỏi đã lựa chọn vào ô tròn
        labelPackage1.setVisible(true);
        labelPackage2.setVisible(true);
        labelPackage3.setVisible(true);
        labelPackage1.setText(listQuestion.get(0));
        labelPackage2.setText(listQuestion.get(1));
        labelPackage3.setText(listQuestion.get(2));
        // load các hàm nạp dữ liệu câu hỏi và các nút
        loadPackageQuesion();
        startRound4Button.setVisible(false);
        Question.setVisible(true);
        submit.setDisable(false);
        myAnswer.setDisable(false);
        showScore(Mark);
        // hiện nút ngôi sao hy vọng
        starHopeButton.setVisible(true);
        //count down
//        startCountDownRound4(16);
        // load câu hỏi ra màn hình
        loadQuestion4();

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
    public void startCountDownRound4() {
        if (listQuestion.get(counter).equals("10")) {
            Countdown(11, countdownLabel4);
        } else if (listQuestion.get(counter).equals("20")) {
            Countdown(16, countdownLabel4);
        } else {
            Countdown(21, countdownLabel4);
        }
    }
}
