package com.example.demo;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;

public class Board {
   ArrayList<Pair<Integer,Integer>> positioncoordinate;

   //for mapping snake and ladder
    ArrayList<Integer>snakeladderposition;


   //constructor to store the  co-ordinate in the vector
   public Board(){
   positioncoordinate = new ArrayList<>();
   populatepositionCoordinate();
   populateSnakeLadder();
   }
   private void populatepositionCoordinate(){
       positioncoordinate.add(new Pair<>(0,0)); // dummy value
       //i==y-co-ordinate (representation)
       //j== x-co-ordinate
       for (int i = 0; i < Snakeladder.height; i++) {
           for (int j = 0; j < Snakeladder.width; j++) {
              //x coordinate
              //y coordinate
               int xcor =0;
               if(i%2==0){
                  xcor = j*Snakeladder.tilesize+Snakeladder.tilesize/2;
               }else{
                  xcor =  Snakeladder.tilesize*Snakeladder.height - (j*Snakeladder.tilesize)-Snakeladder.tilesize/2;
               }
              int ycor = Snakeladder.tilesize*Snakeladder.height - (i*Snakeladder.tilesize)-Snakeladder.tilesize/2;
              positioncoordinate.add(new Pair<>(xcor,ycor));

           }
       }
   }



   //snake and ladder
    private void populateSnakeLadder()
    {
        snakeladderposition = new ArrayList<>();
        for (int i = 0; i < 101; i++) {
        snakeladderposition.add(i);

        }

        snakeladderposition.set(4,25);
        snakeladderposition.set(27,5);
        snakeladderposition.set(13,46);
        snakeladderposition.set(33,49);
        snakeladderposition.set(40,3);
        snakeladderposition.set(42,63);
        snakeladderposition.set(43,18);
        snakeladderposition.set(50,69);
        snakeladderposition.set(54,31);
        snakeladderposition.set(62,81);
        snakeladderposition.set(66,45);
        snakeladderposition.set(76,58);
        snakeladderposition.set(74,92);
        snakeladderposition.set(89,53);
       // snakeladderposition.set(99,41);

    }

public int getnewposition(int currentposition)
{
    if(currentposition>0 && currentposition<=100)
        return snakeladderposition.get(currentposition);
    return -1;
}




//getter method to get the x and y co-ordinate value;

    int getxCoordinate(int position)
    {
        if(position>=1 && position<=100)
            return positioncoordinate.get(position).getKey();
        else return -1;
    }

    int getyCoordinate(int position)
    {
        if(position>=1 && position<=100)
            return positioncoordinate.get(position).getValue();
        else return -1;
    }




   //starting function -> only for testing purpose
//    public static void main(String[] args) {
//        Board board = new Board();
//        for (int i = 0; i < board.positioncoordinate.size(); i++) {
//            System.out.println(i+ "$ x: "+board.positioncoordinate.get(i).getKey() + "y :"+ board.positioncoordinate.get(i).getValue());
//
//        }
//    }
}
