package com.wildrunners.wildrunners.entities;

public class Dice{

    public static int launch(int max){

        return 1 + (int) (Math.random() * max);
        
    }
}

// Dice.launch(6);
// Dice.launch(3);