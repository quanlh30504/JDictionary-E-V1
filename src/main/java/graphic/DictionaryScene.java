package graphic;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

public class DictionaryScene {

    // hàm tạo mục hỏi logout cho nút X trên window
    public void logout(Stage stage){
        //Tạo hộp xác nhận
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout");

        // Icon setting of logout alert
        //Image image = new Image(this.getClass().getResourceAsStream("flat_cross_icon.png"));
        //ImageView imageView = new ImageView(image);
        //alert.setGraphic(imageView);

        alert.setHeaderText("You're about to logout!");
        alert.setContentText("Do you want to save before exiting!: ");
        //System.out.println("im here");
        if(alert.showAndWait().get() == ButtonType.OK){
            System.out.println("You successfully logged out");
            stage.close();
        }
    }
}
