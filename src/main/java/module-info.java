module com.wilson901015.lesotho_trivia_game {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;


    opens com.wilson901015.lesotho_trivia_game to javafx.fxml;
    exports com.wilson901015.lesotho_trivia_game;
}