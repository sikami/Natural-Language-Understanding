import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javax.swing.text.LabelView;
import java.awt.*;

public class Gui extends Application {

    public void start(Stage stage) throws Exception {
        Scene scene = new Scene(gui());
        stage.setScene(scene);
        stage.show();
    }

    public BorderPane gui() {
        BorderPane borderPane = new BorderPane();
        Label title = new Label("Natural Language Understanding");
        title.setStyle("-fx-font-weight: bold");
        title.setFont(new Font("Arial", 16));
        borderPane.setTop(title);S
        return borderPane;
    }
}
