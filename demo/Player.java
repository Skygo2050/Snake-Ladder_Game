package com.example.demo;

import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class Player {

     private Circle coin;

    private int currentposition;

    private String name;

    static Board gameBoard = new Board(); //creating object of board class

    //player constructor (setting all its properties)
    public Player(int tileSize , Color coinColor , String playername)
    {
        coin = new Circle(tileSize/2) ; // setting radius to half of radius
        coin.setFill(coinColor);
        currentposition = 0; //you can set anynumber 0 , out of any boardnumber
        moveplayer(1); //call move player to place the coin at correct place
        name = playername;
    }

    //method  to move the player
   public void moveplayer(int diceValue)
   {
       if(currentposition+diceValue<=100)
           currentposition+=diceValue;

       TranslateTransition secondmove= null,firstMove = translateAnimation(diceValue);// call animate function

//       int x= gameBoard.getxCoordinate(currentposition);
//       int y=gameBoard.getyCoordinate(currentposition);
//       coin.setTranslateX(x);
//       coin.setTranslateY(y);

       int newposition = gameBoard.getnewposition(currentposition);
       if(newposition!=currentposition && newposition!=-1)
       {
           currentposition = newposition;
          secondmove= translateAnimation(6);
       }
       if(secondmove==null)
       {
           firstMove.play();
       }
       else
       {   //SequentialTransition used to play animation in line (onr after another)
           SequentialTransition sequentialTransition = new SequentialTransition(firstMove,new PauseTransition(Duration.millis(700)),secondmove);
           sequentialTransition.play();
       }
   }



   private TranslateTransition translateAnimation(int dicevalue){
       TranslateTransition animate = new TranslateTransition(Duration.millis(200*dicevalue),coin);  //time to slow down the animation
       animate.setToX(gameBoard.getxCoordinate(currentposition));
       animate.setToY(gameBoard.getyCoordinate(currentposition));
       animate.setAutoReverse(false);
       return animate;

   }

   //after winning let set the game again
    public void startingposition()
    {
        currentposition=0;
        moveplayer(1);

    }







//for winning checking ***************  if current player will wonned they made winner
 boolean playerWOn()
 {
     if(currentposition==100)
         return true;
     return false;
 }

   //getter for parameter
    public Circle getCoin() {
        return coin;
    }

    public int getCurrentposition() {
        return currentposition;
    }

    public String getName() {
        return name;
    }
}
