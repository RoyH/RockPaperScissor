
/*
 * Rock Paper Scissors by Roy H & Kanga 
 */
package rockpaperscissors;

import java.io.*;
import java.util.ArrayList;

/**
 *
 * @author Roy & Kanga
 */
public class RockPaperScissors {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // how to add to global array
        // global.list.add("rock");

        boolean x = false;
        int moveCount = 0;
        // Reads in input and assigns move. 
        do {

            // Shows current state of play
            System.out.println("Current move : " + moveCount);
           // Input move

            String move = input();
            move = move.toLowerCase();

           // playes move.
             System.out.println(calculate(move));
            
            // Checks for exit clause
           
            
            if ("exit".equals(move)) {
                x = true;
            }

            if ("print".equals(move)) {
                System.out.println(global.list);
            }

            if ("calc".equals(move)) {
                calculate(move);
            }
            
              
            
            
            
            // Error Checks and adding move to arraylist.
             checkInput(move);

            // logs into array
            // Increments move counter
            // Note to self: move Counter should only increment if a valid move is played.
            moveCount++;

        } while (x == false);
    }

    public static String input() {

        System.out.print("Enter your move: ");

        //  open up standard input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String move = null;

        try {
            move = br.readLine();
        } catch (IOException ioe) {
            System.out.println("IO error call tech support" + ioe);
            System.exit(1);
        }
        return move;

    }

    public static void checkInput(String move) {
        
       if ((!"print".equals(move)) || 
          (!"rock".equals(move)) || 
          (!"paper".equals(move)) || 
          (!"scissors".equals(move))) {
           System.out.println("ERROR, bad input");
           
        
       }else{
           global.list.add(move);
       }
        
        
        
        
        
        // UGLY implementation
        /*
        if (!"print".equals(move))  {
            if (!"rock".equals(move)) {
                if (!"paper".equals(move)) {
                    if (!"scissors".equals(move)) {
                        // System.out.println("Error, not a valid Input.. Use rock, Paper, or scissors");
                    } else {
                        global.list.add(move);
                    }
                } else {
                    global.list.add(move);
                }
            } else {
                global.list.add(move);
            }
        }
        
        */
    
    
    
    }

    public static String calculate(String move) {
        // intialize variables
        int num_rock = 0;
        int num_paper = 0;
        int num_scissors = 0;
        double per_rock = 0;
        double per_paper = 0;
        double per_scissors = 0;
        int sum = 0;



        ArrayList list_sorted = new ArrayList();

        String[] list_c = new String[global.list.size()];

        list_c = (String[]) global.list.toArray(list_c);


        
        
         //take a sample size of data
        int start = 0;
        if (list_c.length > 30) {
            start = list_c.length - 29;
        } else {
            start = 0;
            
        }
        // Gets last move. 
        // searches through array, and gets the next move
        // puts the move into an array called list_sorted
        for ( int i = start; i < (list_c.length - 1); i++) {
            if ((list_c[list_c.length - 1]).equals(list_c[i])) {
                list_sorted.add(list_c[i + 1]);
            }

        }
        // Convert list of data into array

        String[] array_sorted = new String[list_sorted.size()];
        array_sorted = (String[]) list_sorted.toArray(array_sorted);


       
        
        // calculate probabilities

        for (int i = 0; i < array_sorted.length; i++) {
            if ("rock".equals(array_sorted[i])) {
                num_rock++;
            }
            if ("scissors".equals(array_sorted[i])) {
                num_scissors++;
            }
            if ("paper".equals(array_sorted[i])) {
                num_paper++;
            }
        }
        
        sum = num_rock + num_paper + num_scissors ;
        // calculating percentages
        per_rock =  ((double) num_rock/sum)*100;
        per_paper =  ((double) num_paper/sum)*100;
        per_scissors =  ( (double) num_scissors/sum)*100;
        
        // print max
        String prob_max = null;
        String CPU_move = null;
        int CPU_prob = 0 ;
        
        if ((per_rock > per_paper) && (per_rock > per_scissors)){
            prob_max = "rock";
            CPU_move = "paper";
            CPU_prob = (int) per_rock;
        }
        if ((per_paper > per_scissors) && (per_paper > per_rock)){
            prob_max = "paper";
            CPU_move = "scissors";
            CPU_prob = (int) per_paper;
        }
        if ((per_scissors > per_paper) && (per_scissors > per_rock)){
            prob_max = "scissors";
            CPU_move = "rock";
            CPU_prob = (int) per_scissors;
        }
            
            
        // Code below is for debugging purposes    
        //System.out.println(list_sorted);
        //System.out.println("Probability of paper : " +  per_paper);
        //System.out.println("Probability of rock : " + per_rock);
        //System.out.println("Probability of scissors : " + per_scissors);       
        System.out.println("CPU  predicts : "  + prob_max);
        System.out.println("Therefore I play : " +  CPU_move);
        System.out.println("Probability of success is " + CPU_prob + "%");
        
        return CPU_move;
        

    }
}
