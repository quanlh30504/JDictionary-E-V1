module com.example.jdictionaryev1 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;
    requires java.sql;
    requires annotations;
    requires org.jsoup;
    requires json.simple;
    requires jsapi;

    opens com.example.jdictionaryev1 to javafx.fxml;
    exports com.example.jdictionaryev1;
    exports controller;
    opens controller to javafx.fxml;
    exports controller.QuizGame;
    opens controller.QuizGame to javafx.fxml;
}