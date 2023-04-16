package com.example.demo;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class Snakeladder extends Application {

    public static  final  int tilesize = 60 , width = 10,height =10;
    public static final int Buttonline = height*tilesize+50;

    public static final int infoline = Buttonline-30;

    private static Dice dice = new Dice();
    private  Player playerOne, playerTwo;    //playerone and playertwo object from the payer class


    //making player one started
    private boolean gamestarted = false,playeroneTurn = false , playerTwoTurn = false;




    private Pane createContent(){
        Pane root = new Pane();
        root.setPrefSize(width*tilesize , height*tilesize+100); //setting the tile size and all it's properties

        for (int i = 0; i < height; i++) {   //creating loops for the fill the tile throughout  the game board
            for (int j = 0; j < width; j++) {
                Tile tile = new Tile(tilesize);
                tile.setTranslateX(j*tilesize);
                tile.setTranslateY(i*tilesize);
                root.getChildren().add(tile);

            }

        }

        //Image
        Image img = new Image("C:\\Users\\ADMIN\\IdeaProjects\\demo\\src\\main\\java\\com\\example\\demo\\snake.jpg");
        ImageView board = new ImageView();
        board.setImage(img);
        board.setFitHeight(height*tilesize);
        board.setFitWidth(width*tilesize);

        //buttons
        Button playerone = new Button("player one");
        Button playertwo = new Button("player two");
        Button startButton = new Button("Start");

        playerone.setTranslateY(Buttonline);
        playerone.setTranslateX(40);
        playertwo.setTranslateY(Buttonline);
        playertwo.setTranslateX(500);
        startButton.setTranslateY(Buttonline);
        startButton.setTranslateX(275);


        //Information displayed area
        Label playeronelabel = new Label("It's your turn");
        Label playertwolabel = new Label("It's your turn");
        Label dicelabel =     new Label("start the game");

        playeronelabel.setTranslateY(infoline);
        playeronelabel.setTranslateX(40);
        playertwolabel.setTranslateY(infoline);
        playertwolabel.setTranslateX(500);
        dicelabel.setTranslateY(infoline);
        dicelabel.setTranslateX(260);

        // sending player properties to set
     playerOne = new Player(tilesize, Color.BLACK , "Aakash");
     playerTwo  = new Player(tilesize,Color.YELLOW,"Suraj");

     //******************player Action********************
        //for playerone action

        playerone.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                if(gamestarted){
                    if(playeroneTurn)
                    {
                        int diceValue = dice.getRolledValue();
                        dicelabel.setText("Dice Value:" + diceValue);
                        playerOne.moveplayer(diceValue);
                        //wininning condition
                        if(playerOne.playerWOn())
                        {
                            dicelabel.setText("Winner is "+playerOne.getName());

                            //make everything disable
                            playeroneTurn = false; //if player one started then it should turned off so that another player can play
                            playerone.setDisable(true);
                            playeronelabel.setText("");

                            //player two
                            playerTwoTurn =false;
                            playertwo.setDisable(true);
                            playertwolabel.setText("");

                            startButton.setDisable(false);
                            startButton.setText("Play Again");
                            gamestarted=false;

                        }
                        else
                        {
                            playeroneTurn = false; //if player one started then it should turned off so that another player can play
                            playerone.setDisable(true);
                            playeronelabel.setText("");

                            //player two
                            playerTwoTurn =true;
                            playertwo.setDisable(false);
                            playertwolabel.setText("Your Turn "+ playerTwo.getName());
                        }

                    }
                }

            }
        });
//for the playerTwo
        playertwo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                if(gamestarted){
                    if(playerTwoTurn)
                    {
                        int diceValue = dice.getRolledValue();
                        dicelabel.setText("Dice Value:" + diceValue);
                        playerTwo.moveplayer(diceValue);
                        //wininning condition

                        if(playerTwo.playerWOn())
                        {
                            dicelabel.setText("Winner is "+playerTwo.getName());

                            //make everything disable
                            playeroneTurn = false; //if player one started then it should turned off so that another player can play
                            playerone.setDisable(true);
                            playeronelabel.setText("");

                            //player two
                            playerTwoTurn =false;
                            playertwo.setDisable(true);
                            playertwolabel.setText("");

                            startButton.setDisable(false);
                            startButton.setText("Play Again");
                            gamestarted=false;

                        }
                        else
                        {
                            playeroneTurn = true; //if player one started then it should turned off so that another player can play
                            playerone.setDisable(false);
                            playeronelabel.setText("Your Turn "+ playerOne.getName());

                            //player two
                            playerTwoTurn = false;
                            playertwo.setDisable(true);
                            playertwolabel.setText("");

                        }


                    }
                }

            }
        });
  //for the start button

        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                gamestarted = true;
                dicelabel.setText("Game Started!");
                startButton.setDisable(true);
                playeroneTurn = true;
                playeronelabel.setText("Your Turn " + playerOne.getName());
                playerone.setDisable(false);
                playerOne.startingposition();

                playerTwoTurn = false;
                playertwolabel.setText("");
                playertwo.setDisable(true);
                playerTwo.startingposition();
            }
        });

        root.getChildren().addAll(board,playerone,playertwo,startButton,playeronelabel,playertwolabel,dicelabel
        ,playerOne.getCoin(),playerTwo.getCoin());  //adding the board with all features loaded and using root object of pane it will display it.


        return root;
    }
    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = new Scene(createContent());
        stage.setTitle("Snake & ladder");           //starting Function  (By default function to start the application)
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}