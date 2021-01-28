import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.FontWeight;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.text.Font;

import java.io.File;
import java.nio.file.Path;


/**
 * Gui class
 * Class to launch the GUI.
 * @author listya
 */
public class Gui extends Application {

    private Process process;
    private Text textInput;
    private Button button;
    private Button fileButton;
    private TextField pathName;
    private String pathNameLocation;
    private TextArea textArea;
    private TextField emailField;
    private TextField keywords;
    private Label end;
    private BorderPane borderPane;


    public void start(Stage stage) {
        Scene scene = new Scene(gui());
        runFileChooser(stage);
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

       //textfield & Button added to choose for text file
        HBox horizontal1 = new HBox();
        horizontal1.setSpacing(10);
        Label option = new Label("Note: You can select text file from your system or manually input text in the text area below.");
        fileButton = new Button("Choose text file");
        pathName = new TextField();
        horizontal1.getChildren().addAll(fileButton, pathName);
        Label text = new Label("Text Input:");
        textArea = new TextArea();
        textArea.setWrapText(true);
        vertical.getChildren().addAll(option, horizontal1, text, textArea);

        //keyphrase
        Label keyphrase = new Label("Keyword(s) to analyze:");
        keywords = new TextField();

        vertical.getChildren().addAll(keyphrase, keywords);

        //email address

        Label emailName = new Label("Your email address:");
        emailField = new TextField();
        vertical.getChildren().addAll(emailName, emailField);

        //option to analyze
        HBox boxAnalysisOption = new HBox();
        boxAnalysisOption.setSpacing(10);
        boxAnalysisOption.setPadding(new Insets(10));
        Label analyze = new Label("Analyze for:");
        ToggleGroup toggleGroupAnalysis = new ToggleGroup();

        RadioButton emotion = new RadioButton("Emotion");
        RadioButton syntax = new RadioButton("Syntax");
        emotion.setToggleGroup(toggleGroupAnalysis);
        syntax.setToggleGroup(toggleGroupAnalysis);

        syntax.setOnAction(obj -> {
            keywords.setDisable(true);
        });
        emotion.setOnAction(obj -> {
            keywords.setDisable(false);
        });
        boxAnalysisOption.getChildren().addAll(emotion, syntax);

        /**
         * default: emotion is selected.
         * This can be changed later
         */
        emotion.setSelected(true);
        vertical.getChildren().addAll(analyze, boxAnalysisOption);

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

    private void runFileChooser(Stage stage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));

        fileButton.setOnAction(actionEvent -> {

            File selected = fileChooser.showOpenDialog(stage);
            //update pathNameLocation
            try {
                pathNameLocation = selected.getAbsolutePath();
                if (pathNameLocation.isEmpty() || pathName.getText().isEmpty()) {
                    textArea.setDisable(false);
                } else {
                    textArea.setDisable(true);
                }
                //update the gui to show pathname in the textfield
                pathName.setText(pathNameLocation);
                //read the file and input to Text
                FileInput fileInput = new FileInput(pathNameLocation);
                textInput = new Text(fileInput.getText());
            } catch (Exception e) {

            }
        });
    }

    private void runButton() {
        button.setOnAction(actionEvent -> {
            //if test empty then reinput
            if (pathName.getText().isEmpty()) {
                textInput = new Text(textArea.getText());
            }

            if(!checkIfTextFieldsAreEmpty()) {
                button.setDisable(true);
                process = new Process(textInput, new DestinationEmail(emailField.getText()),
                        new KeyPhrase(keywords.getText()));

                // send connect to watson and send email to
                process.sendEmail();
                textArea.setDisable(true);
                emailField.setDisable(true);
                keywords.setDisable(true);
                end.setText("Thank you. An email with the result will be sent to you shortly.");
            }
        });
    }

    private boolean checkIfTextFieldsAreEmpty() {
        //check if pathName is empty, when its empty check text area etc
        if (pathName.getText().isEmpty()) {
            textArea.setDisable(false);
            if (textArea.getText().isEmpty() && emailField.getText().isEmpty() && keywords.getText().isEmpty() || textArea.getText().equals("This field cannot be empty!") &&
                    emailField.getText().equals("This field cannot be empty!") && keywords.getText().equals("This field cannot be empty!")) {
                warning(textArea);
                warning(emailField);
                warning(keywords);
                return true;
            } else if (emailField.getText().isEmpty() || emailField.getText().equals("This field cannot be empty!")) {
                warning(emailField);
                return true;
            } else if (textArea.getText().isEmpty() || textArea.getText().equals("This field cannot be empty!")) {
                warning(textArea);
                return true;
            } else if (keywords.getText().isEmpty() || keywords.getText().equals("This field cannot be empty!")) {
                warning(keywords);
                return true;
            }
        } else {
            //if pathName is not empty, then textArea set disabled, then check if keywords is empty and email address is empty
            textArea.setDisable(true);
            textArea.setText("");
            if (emailField.getText().isEmpty() || emailField.getText().equals("This field cannot be empty!") && keywords.getText().isEmpty() || keywords.getText().equals("This field cannot be empty!")) {
                warning(emailField);
                warning(keywords);
                return true;
            }
            else if (emailField.getText().isEmpty() || emailField.getText().equals("This field cannot be empty!")) {
                warning(emailField);
                return true;
            } else if (keywords.getText().isEmpty() || keywords.getText().equals("This field cannot be empty!")) {
                warning(keywords);
                return true;
            }
        }
        return false;
    }

    private void warning(Object d) {
        if (d instanceof TextArea) {
            TextArea changed = (TextArea) d;
            changed.setText("This field cannot be empty!");
        } else if (d instanceof TextField) {
            TextField changed = (TextField) d;
            changed.setText("This field cannot be empty!");
        }
    }

}
