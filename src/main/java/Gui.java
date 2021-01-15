import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.text.Font;

/**
 * Gui class
 * Class to launch the GUI.
 * @author listya
 */
public class Gui extends Application {

    public void start(Stage stage) throws Exception {
        Scene scene = new Scene(gui());
        stage.setScene(scene);
        stage.show();
    }

    public BorderPane gui() {
        //title
       BorderPane borderPane = new BorderPane();
       HBox horizontal = new HBox();
       horizontal.setSpacing(10);
       horizontal.setPadding(new Insets(10));
       VBox vertical = new VBox();
       vertical.setSpacing(10);
       vertical.setPadding(new Insets(10));
       Label title = new Label("Natural Language Understanding");
       title.setFont(Font.font("Arial",FontWeight.BOLD, 15));
       horizontal.getChildren().add(title);
       horizontal.setAlignment(Pos.CENTER);
       vertical.getChildren().add(horizontal);
       vertical.getChildren().add(new Separator());

       //textfield
        Label text = new Label("Text Input:");
        TextArea textArea = new TextArea();
        vertical.getChildren().addAll(text, textArea);

        //keyphrase
        Label keyphrase = new Label("Keyword to analyze:");
        TextField textField = new TextField();
        vertical.getChildren().addAll(keyphrase, textField);

        //things to check
        HBox horizontal1 = new HBox();
        Label analyze = new Label("Analyze for:");
        RadioButton emotion = new RadioButton("Emotion");

        /**
         * default: emotion is selected.
         */
        emotion.setSelected(true);
        horizontal1.getChildren().addAll(analyze, emotion);
        vertical.getChildren().addAll(analyze, emotion);




        borderPane.setTop(vertical);
        return borderPane;
    }
}
