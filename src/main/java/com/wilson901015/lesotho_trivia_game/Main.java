package com.wilson901015.lesotho_trivia_game;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
//initialization of questionscreen class
    private QuestionsScreen questionsScreen;

    @Override
    public void start(Stage primaryStage) {
        // Creation of QuestionsScreen instance
        questionsScreen = new QuestionsScreen();

        // Creation of layout
        BorderPane borderPane = new BorderPane();

        // Setting background image
        Image backgroundImage = new Image(getClass().getResourceAsStream("/images/LESOTHO.jpg"));
        ImageView backgroundImageView = new ImageView(backgroundImage);
        backgroundImageView.fitWidthProperty().bind(primaryStage.widthProperty());
        backgroundImageView.fitHeightProperty().bind(primaryStage.heightProperty());
        borderPane.getChildren().add(backgroundImageView);

        VBox layout = new VBox(20);
        layout.setAlignment(Pos.CENTER);

        // Addition of left top icon
        HBox leftTopIconBox = new HBox();
        leftTopIconBox.setAlignment(Pos.TOP_LEFT);
        leftTopIconBox.setPadding(new Insets(10));
        ImageView leftTopIcon = new ImageView(new Image(getClass().getResourceAsStream("/images/icon01.png")));
        leftTopIcon.setFitHeight(40);
        leftTopIcon.setFitWidth(40);
        leftTopIconBox.getChildren().add(leftTopIcon);

        // Addition of right top icons with its adjustments
        HBox rightTopIconBox = new HBox(20);
        rightTopIconBox.setAlignment(Pos.TOP_RIGHT); // Align icons to the top right
        rightTopIconBox.setPadding(new Insets(10)); // Add padding
        ImageView rightTopIcon1 = new ImageView(new Image(getClass().getResourceAsStream("/images/icon3.png")));
        ImageView rightTopIcon2 = new ImageView(new Image(getClass().getResourceAsStream("/images/icon2.png")));
        rightTopIcon1.setFitHeight(40);
        rightTopIcon1.setFitWidth(40);
        rightTopIcon2.setFitHeight(40);
        rightTopIcon2.setFitWidth(40);
        rightTopIconBox.getChildren().addAll(rightTopIcon1, rightTopIcon2);

        // Addition of right bottom icons with its adjustments
        HBox rightBottomIconBox = new HBox(30);
        rightBottomIconBox.setAlignment(Pos.BOTTOM_CENTER);
        rightBottomIconBox.setPadding(new Insets(15));
        ImageView rightBottomIcon1 = new ImageView(new Image(getClass().getResourceAsStream("/images/game.png")));
        ImageView rightBottomIcon2 = new ImageView(new Image(getClass().getResourceAsStream("/images/game1.png")));
        rightBottomIcon1.setFitHeight(70);
        rightBottomIcon1.setFitWidth(70);
        rightBottomIcon2.setFitHeight(70);
        rightBottomIcon2.setFitWidth(70);
        rightBottomIconBox.getChildren().addAll(rightBottomIcon1, rightBottomIcon2);

        // Creation of welcome label and start button
        Label welcome = new Label("Welcome To Lesotho Trivia Game");
        welcome.getStyleClass().add("label");
        Button startButton = new Button("Play Game");
        startButton.getStyleClass().add("button-start"); // Add CSS class to button

        // Event handler for start button
        startButton.setOnAction(e -> questionsScreen.showTriviaQuestions(primaryStage));

        // Add nodes to layout
        layout.getChildren().addAll(welcome, startButton);
        borderPane.setLeft(leftTopIconBox);
        borderPane.setRight(rightTopIconBox);
        borderPane.setBottom(rightBottomIconBox);
        borderPane.setCenter(layout);

        // Setting  up the scene
        Scene scene = new Scene(borderPane, 750, 700);
        scene.getStylesheets().add(getClass().getResource("/com/wilson901015/lesotho_trivia_game/Styles.css").toExternalForm()); // Load CSS file

        // Setting the scene to the primary stage
        primaryStage.setScene(scene);
        primaryStage.setTitle("Lesotho Trivia Game");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
