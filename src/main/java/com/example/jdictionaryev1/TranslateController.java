package com.example.jdictionaryev1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import module.GoogleTranslateAPI;

import javax.swing.plaf.SplitPaneUI;
import java.awt.*;
import java.awt.geom.Area;
import java.io.IOException;

public class TranslateController {
    @FXML
    public Button translateButton;
    private final String VIETNAMESE = "vi";
    private final String ENGLISH = "en";
    private final String JAPANESE = "ja";
    private final String KOREAN = "ko";
    private final String CHINESE = "zh-CN";

    @FXML
    private TextArea sourceDoc, targetDoc;
    @FXML
    private Pane rightFlat, leftFlat;
    private String langTarget = "vi";

    @FXML
    public void translateFunction() throws Exception {
        String source = sourceDoc.getText();
        source = source.replace(". ", ",");
        targetDoc.setText(GoogleTranslateAPI.translate(source,langTarget));
    }

    @FXML
    public void gotoSearch() throws IOException {
        HelloController helloController = new HelloController();
        helloController.switchToDictionary();
    }
    @FXML
    public void gotoGame() throws IOException {
        HelloController helloController = new HelloController();
        helloController.switchToGame();
    }
    @FXML
    public void gotoEdit(){
        HelloController helloController = new HelloController();
        helloController.toggleButtonClicked();
    }
    @FXML
    public void gotoLogOut(){
        HelloController helloController = new HelloController();
        helloController.logout();
    }
    @FXML
    public SplitMenuButton splitMenuButtonLeft;
    @FXML
    private MenuBar menuBar;
    @FXML
    public Menu menu ;
    public RadioMenuItem englishLeft;
    public RadioMenuItem vietnameseLeft;
    public RadioMenuItem japaneseLeft;
    public RadioMenuItem koreanLeft;
    public RadioMenuItem chineseLeft;

    @FXML
    public SplitMenuButton splitMenuButtonRight;

    public MenuBar menuBarRight;
    @FXML
    public Menu menuRight ;
    public RadioMenuItem englishRight;
    public RadioMenuItem vietnameseRight;
    public RadioMenuItem japaneseRight;
    public RadioMenuItem koreanRight;
    public RadioMenuItem chineseRight;


    // chuyển ngôn ngữ bên trái
    @FXML
    public void selectEnglishLeft(){
        englishLeft.setSelected(true);
        vietnameseLeft.setSelected(false);
        japaneseLeft.setSelected(false);
        koreanLeft.setSelected(false);
        chineseLeft.setSelected(false);
//        menuLeft.setLabel("English");
        splitMenuButtonLeft.setText("English");
    }
    @FXML
    public void selectVietnameseLeft(){
        englishLeft.setSelected(false);
        vietnameseLeft.setSelected(true);
        japaneseLeft.setSelected(false);
        koreanLeft.setSelected(false);
        chineseLeft.setSelected(false);
//        menuLeft.setLabel("English");
        splitMenuButtonLeft.setText("Vietnamese");

    }
    @FXML
    public void selectJapaneseLeft(){
        englishLeft.setSelected(false);
        vietnameseLeft.setSelected(false);
        japaneseLeft.setSelected(true);
        koreanLeft.setSelected(false);
        chineseLeft.setSelected(false);
//        menuLeft.setLabel("English");
        splitMenuButtonLeft.setText("Japanese");

    }
    @FXML
    public void selectKoreanLeft(){
        englishLeft.setSelected(false);
        vietnameseLeft.setSelected(false);
        japaneseLeft.setSelected(false);
        koreanLeft.setSelected(true);
        chineseLeft.setSelected(false);
//        menuLeft.setLabel("English");
        splitMenuButtonLeft.setText("Korean");

    }
    @FXML
    public void selectChineseLeft() {
        englishLeft.setSelected(false);
        vietnameseLeft.setSelected(false);
        japaneseLeft.setSelected(false);
        koreanLeft.setSelected(false);
        chineseLeft.setSelected(true);
//        menuLeft.setLabel("English");
        splitMenuButtonLeft.setText("Chinese");
    }

    // Chuyển ngoon ngữ bên phải
    @FXML
    public void selectEnglishRight(){
        englishRight.setSelected(true);
        vietnameseRight.setSelected(false);
        japaneseRight.setSelected(false);
        koreanRight.setSelected(false);
        chineseRight.setSelected(false);
        splitMenuButtonRight.setText("English");
        langTarget = ENGLISH;
    }
    @FXML
    public void selectVietnameseRight(){
        englishRight.setSelected(false);
        vietnameseRight.setSelected(true);
        japaneseRight.setSelected(false);
        koreanRight.setSelected(false);
        chineseRight.setSelected(false);
        splitMenuButtonRight.setText("Vietnamese");
        langTarget = VIETNAMESE;
    }
    @FXML
    public void selectJapaneseRight(){
        englishRight.setSelected(false);
        vietnameseRight.setSelected(false);
        japaneseRight.setSelected(true);
        koreanRight.setSelected(false);
        chineseRight.setSelected(false);
        splitMenuButtonRight.setText("Japanese");
        langTarget = JAPANESE;
    }
    @FXML
    public void selectKoreanRight(){
        englishRight.setSelected(false);
        vietnameseRight.setSelected(false);
        japaneseRight.setSelected(false);
        koreanRight.setSelected(true);
        chineseRight.setSelected(false);
        splitMenuButtonRight.setText("Korean");
        langTarget = KOREAN;
    }
    @FXML
    public void selectChineseRight(){
        englishRight.setSelected(false);
        vietnameseRight.setSelected(false);
        japaneseRight.setSelected(false);
        koreanRight.setSelected(false);
        chineseRight.setSelected(true);
        splitMenuButtonRight.setText("Chinese");
        langTarget = CHINESE;
    }

}
