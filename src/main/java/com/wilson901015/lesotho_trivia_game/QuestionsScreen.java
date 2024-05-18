package com.wilson901015.lesotho_trivia_game;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.util.Duration;

public class QuestionsScreen {
    private Scene scene;

    private int currentQuestionIndex = 0;
    private int correctAnswersCount = 0;
    private int questionsAnswered = 0;
    private MediaPlayer mediaPlayer;
    private MediaPlayer mediaPlayer2;

    private String[] questionLists = {
            "1. Based on the image below what is the name of the dam?",
            "2. Below is a Picture of one of Basotho cultural villages therefore, What is the name of this village?",
            "3. Who was the founder of the Basotho Nation?",
            "4. Study clearly below Basotho traditional pots then tell us the name?",
            "5. What is the name of below traditional musical instrumental?",
            "6. In which district is the cave found and the name of cave?",
            "7. Based on the video below, what is the name of the place?",
            "8. Which historical site is a fortress mountain used by King Moshoeshoe I?",
            "9. What is the traditional Basotho beer called?",
            "10. Study the video clearly and tell us the name of pass and where it is found?"
    };

    private Image[] questionImages = {
            new Image(getClass().getResourceAsStream("/images/KATSE.jpg")),
            new Image(getClass().getResourceAsStream("/images/bosiu.png")),
            new Image(getClass().getResourceAsStream("/images/moshoe.jpeg")),
            new Image(getClass().getResourceAsStream("/images/clay1.jpg")),
            new Image(getClass().getResourceAsStream("/images/Mamokhorong.jpg")),
            new Image(getClass().getResourceAsStream("/images/liphofu.jpg")),
            null, // Placeholder for video question
            new Image(getClass().getResourceAsStream("/images/lesotho88.jpg")),
            new Image(getClass().getResourceAsStream("/images/beer.jpg")),
            null // Placeholder for video question
    };

    private String[][] questionAnswerOptions = {
            {"Mohale Dam", "Polihali Dam", "Katse Dam", "None Of the Above"},
            {"MORIJA VILLAGE", "THABA BOSIU CULTURAL VILLAGE", "LESOTHO VILLAGE", "METEBONG VILLAGE"},
            {"King Moshoeshoe III", "King Letsie III", "King Moshoeshoe II", "King Moshoeshoe I"},
            {"BASOTHO CASTROL", "MOPOTJOANA", "CLAY POT", "LERITSHOANA"},
            {"Mamokhorong", "Katara", "Lesiba", "Mokorotlo"},
            {"Maseru, Kome Cave", "Botha Bothe, Liphofung Cave", "Berea", "None Of the above"},
            {"Mabita", "Hlotse", "THABA BOSIU CULTURAL VILLAGE", "MOKHOTLONG"},
            {"Thaba Bosiu", "Maloti-Drakensberg Park", "Bokong Nature Reserve", "Tsehlanyane National Park"},
            {"Leting", "Khera", "Sesotho", "Hopose"},
            {"BOTHA BOTHE, MMOTENG", "Hlotse", "Maseru", "MOKHOTLONG, SANI PASS"}
    };

    private int[] correctAnswerLists = {2, 1, 3, 2, 0, 1, 2, 0, 3, 3};

    public QuestionsScreen() {
        VBox layout = new VBox(20);
        layout.setAlignment(Pos.CENTER);
        layout.getStyleClass().add("main-layout");

        scene = new Scene(layout, 750, 800);
        scene.getStylesheets().add(getClass().getResource("/com/wilson901015/lesotho_trivia_game/questions.css").toExternalForm());

        Image backgroundImage = new Image(getClass().getResourceAsStream("/images/LESOTHO.jpg"));
        ImageView backgroundImageView = new ImageView(backgroundImage);
        backgroundImageView.fitWidthProperty().bind(scene.widthProperty());
        backgroundImageView.fitHeightProperty().bind(scene.heightProperty());
        layout.getChildren().add(backgroundImageView);
    }

    public void showTriviaQuestions(Stage primaryStage) {
        VBox layout = (VBox) scene.getRoot();
        layout.getChildren().clear();

        Image backIconImage = new Image(getClass().getResourceAsStream("/images/back.png"));
        Button backButton = new Button();
        backButton.setGraphic(new ImageView(backIconImage));
        backButton.setOnAction(e -> {
            if (currentQuestionIndex > 0) {
                currentQuestionIndex--;
                correctAnswersCount--;
                questionsAnswered--;
                showTriviaQuestions(primaryStage);
            }
        });
        HBox backButtonContainer = new HBox(backButton);
        backButtonContainer.setAlignment(Pos.TOP_LEFT);
        layout.getChildren().add(backButtonContainer);

        Label levelLabel = new Label("Level: " + (currentQuestionIndex + 1) + " of " + questionLists.length);
        levelLabel.getStyleClass().add("level-label");
        layout.getChildren().add(levelLabel);

        Label question = new Label(questionLists[currentQuestionIndex]);
        question.getStyleClass().add("question-label");
        layout.getChildren().add(question);

        if (questionImages[currentQuestionIndex] == null) {
            String videoPath;
            if (currentQuestionIndex == 6) {
                videoPath = getClass().getResource("/videos/village.mp4").toExternalForm();
            } else {
                videoPath = getClass().getResource("/videos/pass.mp4").toExternalForm();
            }
            Media media = new Media(videoPath);
            mediaPlayer = new MediaPlayer(media);
            MediaView mediaView = new MediaView(mediaPlayer);
            mediaView.setFitWidth(300);
            mediaView.setPreserveRatio(true);

            Button playButton = new Button("Play");
            playButton.getStyleClass().add("play-button");
            playButton.setOnAction(event -> mediaPlayer.play());

            Button replayButton = new Button("Replay");
            replayButton.getStyleClass().add("replay-button");
            replayButton.setOnAction(event -> {
                mediaPlayer.seek(mediaPlayer.getStartTime());
                mediaPlayer.play();
            });

            VBox vbox = new VBox(question, mediaView, playButton, replayButton);
            vbox.setAlignment(Pos.CENTER);
            vbox.setSpacing(10);

            layout.getChildren().add(vbox);
        } else {
            ImageView imageView = new ImageView(questionImages[currentQuestionIndex]);
            imageView.setFitWidth(400);
            imageView.setFitHeight(300);
            HBox imageBox = new HBox(imageView);
            imageBox.setAlignment(Pos.CENTER);
            layout.getChildren().add(imageBox);
        }

        GridPane questionAnswerOptionsGrid = new GridPane();
        questionAnswerOptionsGrid.setAlignment(Pos.CENTER);
        questionAnswerOptionsGrid.setHgap(30);
        questionAnswerOptionsGrid.setVgap(30);

        for (int i = 0; i < questionAnswerOptions[currentQuestionIndex].length; i++) {
            final int questionAnswerOptionsIndex = i;
            Button questionAnswerOptionsButton = new Button(questionAnswerOptions[currentQuestionIndex][i]);
            questionAnswerOptionsButton.getStyleClass().add("answer-button");
            questionAnswerOptionsButton.setOnMouseEntered(e -> questionAnswerOptionsButton.getStyleClass().add("hover"));
            questionAnswerOptionsButton.setOnMouseExited(e -> questionAnswerOptionsButton.getStyleClass().remove("hover"));
            questionAnswerOptionsButton.setOnAction(e -> checkAnswer(questionAnswerOptions[currentQuestionIndex]
                                                                             [questionAnswerOptionsIndex], primaryStage));
            questionAnswerOptionsGrid.add(questionAnswerOptionsButton, i % 2, i / 2);
        }

        layout.getChildren().add(questionAnswerOptionsGrid);

        ProgressBar progressBar = new ProgressBar((double) (questionsAnswered) / questionLists.length);
        progressBar.setPrefWidth(600);
        progressBar.setPrefHeight(10);
        layout.getChildren().add(progressBar);

        Label correctionLabel = new Label();
        layout.getChildren().add(correctionLabel);

        Label time = new Label("Time Remaining: 10 seconds");
        time.getStyleClass().add("timer-label");
        layout.getChildren().add(time);

        Label levelsLabel = new Label("Levels: 1 - " + questionLists.length);
        levelsLabel.getStyleClass().add("levels-label");
        layout.getChildren().add(levelsLabel);

        CountdownTimer timer = new CountdownTimer(10, time, primaryStage, this);
        timer.startTime();

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void checkAnswer(String selectedQuestionAnswerOptions, Stage primaryStage) {
        VBox layout = (VBox) scene.getRoot();
        Label correctionLabel = (Label) layout.getChildren().get(layout.getChildren().size() - 3);

        boolean isCorrect = selectedQuestionAnswerOptions.equals(questionAnswerOptions[currentQuestionIndex][correctAnswerLists[currentQuestionIndex]]);
        if (isCorrect) {
            correctionLabel.setText("Correct!");
            correctionLabel.getStyleClass().add("correct-label");
            correctAnswersCount++;
        } else {
            correctionLabel.setText("Incorrect!");
            correctionLabel.getStyleClass().add("incorrect-label");
        }

        questionsAnswered++;
        ProgressBar progressBar = (ProgressBar) layout.getChildren().get(layout.getChildren().size() - 4);

        Alert alert = new Alert(isCorrect ? Alert.AlertType.INFORMATION : Alert.AlertType.ERROR);
        alert.setTitle("Answer Result");
        alert.setHeaderText(null);
        alert.setContentText(isCorrect ? "Your answer is correct!" : "Sorry, your answer is incorrect.");
        alert.showAndWait();

        progressBar.setProgress((double) (questionsAnswered) / questionLists.length);

        if (currentQuestionIndex < questionLists.length - 1) {
            currentQuestionIndex++;
            showTriviaQuestions(primaryStage);
        } else {
            boolean passed = correctAnswersCount >= questionLists.length / 2;
            showEndSummary(primaryStage, passed);
        }
    }

    private void showEndSummary(Stage primaryStage, boolean passed) {
        VBox layout = (VBox) scene.getRoot();
        layout.getChildren().clear();

        Label endLabel = new Label("End of questions.");
        endLabel.getStyleClass().add("end-label");

        Label scoreLabel = new Label("Your score: " + correctAnswersCount + " / " + questionLists.length);
        scoreLabel.getStyleClass().add("score-label");

        Button playAgainButton = new Button("Play Again");
        playAgainButton.getStyleClass().add("play-again-button");
        playAgainButton.setOnMouseEntered(e -> playAgainButton.getStyleClass().add("hover"));
        playAgainButton.setOnMouseExited(e -> playAgainButton.getStyleClass().remove("hover"));
        playAgainButton.setOnAction(e -> {
            currentQuestionIndex = 0;
            correctAnswersCount = 0;
            questionsAnswered = 0;
            showTriviaQuestions(primaryStage);
        });

        Button quitButton = new Button("Quit");
        quitButton.getStyleClass().add("quit-button");
        quitButton.setOnMouseEntered(e -> quitButton.getStyleClass().add("hover"));
        quitButton.setOnMouseExited(e -> quitButton.getStyleClass().remove("hover"));
        quitButton.setOnAction(e -> primaryStage.close());

        Label messageLabel = new Label(passed ? "Congratulations! You passed!" : "Pull up the socks and try again!");
        messageLabel.getStyleClass().add(passed ? "passed-label" : "failed-label");

        VBox buttonBox = new VBox(20);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.getChildren().addAll(playAgainButton, quitButton);

        layout.getChildren().addAll(endLabel, scoreLabel, buttonBox, messageLabel);
    }

    private static class CountdownTimer {
        private int timeSeconds;
        private Label time;
        private Stage primaryStage;
        private QuestionsScreen questionsScreen;
        private Timeline timeline;

        public CountdownTimer(int timeSeconds, Label time, Stage primaryStage, QuestionsScreen questionsScreen) {
            this.timeSeconds = timeSeconds;
            this.time = time;
            this.primaryStage = primaryStage;
            this.questionsScreen = questionsScreen;
        }

        public void startTime() {
            timeline = new Timeline();
            timeline.setCycleCount(Timeline.INDEFINITE);
            timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1), event -> {
                timeSeconds--;
                time.setText("Time Remaining: " + timeSeconds + " seconds");

                if (timeSeconds <= 0) {
                    timeline.stop();
                }
            }));
            timeline.playFromStart();
        }
    }
}
