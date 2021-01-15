import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.text.Font;


/**
 * Gui class
 * Class to launch the GUI.
 * @author listya
 */
public class Gui extends Application {

    private Process process;
    private Button button;
    private TextArea textArea;
    private TextField emailField;
    private TextField keywords;
    private Label end;
    private BorderPane borderPane;


    public void start(Stage stage) throws Exception {
        Scene scene = new Scene(gui());
        runButton();
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
        textArea = new TextArea();
        vertical.getChildren().addAll(text, textArea);

        //keyphrase
        Label keyphrase = new Label("Keyword to analyze:");
        keywords = new TextField();

        vertical.getChildren().addAll(keyphrase, keywords);

        //email address

        Label emailName = new Label("Your email address:");
        emailField = new TextField();
        vertical.getChildren().addAll(emailName, emailField);

        //option to analyze
        HBox horizontal1 = new HBox();
        Label analyze = new Label("Analyze for:");
        RadioButton emotion = new RadioButton("Emotion");

        /**
         * default: emotion is selected.
         * This can be changed later
         */
        emotion.setSelected(true);
        horizontal1.getChildren().addAll(analyze, emotion);
        vertical.getChildren().addAll(analyze, emotion);

        //notification sign
        HBox horizontal2 = new HBox();
        horizontal2.setAlignment(Pos.CENTER);
        end = new Label();
        end.setAlignment(Pos.CENTER);
        end.setTextFill(Paint.valueOf("green"));
        horizontal2.getChildren().add(end);
        vertical.getChildren().add(horizontal2);


        //button to say done
        HBox horizontal3 = new HBox();
        button = new Button("Analyze and send result to email");
        button.setMaxWidth(300);
        button.setAlignment(Pos.CENTER);
        horizontal3.setAlignment(Pos.CENTER);
        horizontal3.setPadding(new Insets(20));
        horizontal3.getChildren().add(button);
        vertical.getChildren().add(horizontal3);


        borderPane.setTop(vertical);
        return borderPane;
    }

    private void runButton() {
        button.setOnAction(actionEvent -> {
            button.setDisable(true);
            process = new Process(new Text(textArea.getText()), new DestinationEmail(emailField.getText()),
                    new KeyPhrase(keywords.getText()));
            textArea.setDisable(true);
            emailField.setDisable(true);
            keywords.setDisable(true);
            end.setText("Thank you. An email with the result will be sent to you shortly.");

        });
    }

}
