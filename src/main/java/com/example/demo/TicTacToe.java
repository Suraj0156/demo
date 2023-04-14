package com.example.demo;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;

public class TicTacToe extends Application {
    private Button buttons[][] = new Button[3][3];
    private Label playerxScoreLabel,playeroScoreLabel;
    private int playerXScore=0,PlayerOScore=0;
    private boolean playerXturn =true;
    private BorderPane createContent(){
        BorderPane root =new BorderPane();
        //Title
        Label titleLabel = new Label("Tic Tac Toe");
        titleLabel.setStyle("-fx-font-size :24pt; -fx-font-weight : bold;");
        root.setTop(titleLabel);
        //game board
        GridPane gridPane=new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Button button = new Button();
                button.setPrefSize(100,100);
                button.setStyle("-fx-font-size :24pt; -fx-font-weight : bold;");
                button.setOnAction(event->buttonClick(button));
                buttons[i][j] = button;
                gridPane.add(button,j,i);
            }
        }

        root.setCenter(gridPane);

        //Score
        HBox scoreboard = new HBox(20);
        playerxScoreLabel =new Label("Player X :0");
        playerxScoreLabel.setStyle("-fx-font-size :16pt; -fx-font-weight : bold;");
        playeroScoreLabel=new Label("Player O: 0");
        playeroScoreLabel.setStyle("-fx-font-size :16pt; -fx-font-weight : bold;");
        scoreboard.getChildren().addAll(playerxScoreLabel,playeroScoreLabel);
        root.setBottom(scoreboard);

        return root;

    }
    private void buttonClick(Button button){
        if(button.getText().equals("")){

            if (playerXturn){
                button.setText("X");
            }
            else{
                button.setText("O");
            }
            playerXturn=!playerXturn;
            checkWinner();

        }
        return;
    }
    private void checkWinner(){
        //row
        for (int row = 0; row < 3; row++) {
            if(buttons[row][0].getText().equals(buttons[row][1].getText())
            && buttons[row][1].getText().equals(buttons[row][2].getText())
            && !buttons[row][0].getText().isEmpty()){
                // we will have winner
                String winner= buttons[row][0].getText();
                showWinnerDialog(winner);
                updateScore(winner);
                resetBoard();
                return;
            }

        }
        //coloumn
        for (int col = 0; col < 3; col++) {
            if(buttons[0][col].getText().equals(buttons[1][col].getText())
                    && buttons[1][col].getText().equals(buttons[2][col].getText())
                    && !buttons[0][col].getText().isEmpty()){
                // we will have winner
                String winner= buttons[0][col].getText();
                showWinnerDialog(winner);
                updateScore(winner);
                resetBoard();
                return;
            }

        }
        //Diogonal
            if (buttons[0][0].getText().equals(buttons[1][1].getText())
                    && buttons[1][1].getText().equals(buttons[2][2].getText())
                    && !buttons[0][0].getText().isEmpty()) {
                // we will have winner
                String winner = buttons[0][0].getText();
                showWinnerDialog(winner);
                updateScore(winner);
                resetBoard();
                return;
            }
        if (buttons[2][0].getText().equals(buttons[1][1].getText())
                && buttons[1][1].getText().equals(buttons[0][2].getText())
                && !buttons[2][0].getText().isEmpty()) {
            // we will have winner
            String winner = buttons[2][0].getText();
            showWinnerDialog(winner);
            updateScore(winner);
            resetBoard();
            return;
        }
        //tile
        boolean tie=true;
        for(Button row[]: buttons){
            for(Button button:row){
               if (button.getText().isEmpty()){
                    tie = false;
                    break;
                }
            }
        }
        if(tie){
            showtieDialog();
            resetBoard();
        }

        }
    private void showWinnerDialog(String winner){
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("winner");
        alert.setContentText("Congratulation"+ winner+"! You Won The Game");
        alert.setHeaderText("");
        alert.showAndWait();
    }
    private void showtieDialog(){
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Tie");
        alert.setContentText("Game Over ! Its a tie");
        alert.setHeaderText("");
        alert.showAndWait();
    }
    private void updateScore(String winner){
        if(winner.equals("X")){
            //playerXScore++;
            playerXScore++;
            playerxScoreLabel.setText("Player X : "+playerXScore);
        }
        else{
            PlayerOScore++;
            playeroScoreLabel.setText("Player0: "+PlayerOScore);
        }
    }
    private void resetBoard(){
        for(Button row[]: buttons){
            for(Button button:row){
                button.setText("");
            }
        }
    }
    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = new Scene( createContent());
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}