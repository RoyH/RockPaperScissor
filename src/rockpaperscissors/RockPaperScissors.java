
/*
 * Rock Paper Scissors by Roy H & Kanga 
 */
package rockpaperscissors;

import java.awt.Component;
import java.util.*;
import java.io.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;


/**
 *
 * @author Roy & Kanga
 */
public class RockPaperScissors {

    /**
     * @param args the command line arguments
     */
    public static void mainProg(String move) {
        // how to add to global array
        // global.list.add("rock");



        // Reads in input and assigns move. 
        // Input move


        move = move.toLowerCase();

        global.list.add(move);

        calculate(move);






        // Error Checks and adding move to arraylist.
        // checkInput(move);



    }

    /*  public static String input() {

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

     */
    public static void checkInput(String move) {

        if ((!"print".equals(move))
                || (!"rock".equals(move))
                || (!"paper".equals(move))
                || (!"scissors".equals(move))) {
            System.out.println("ERROR, bad input");


        } else {
            global.list.add(move);
        }









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
        for (int i = start; i < (list_c.length - 1); i++) {
            if ((list_c[list_c.length - 2]).equals(list_c[i])) {
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

        sum = num_rock + num_paper + num_scissors;
        // calculating percentages
        per_rock = ((double) num_rock / sum) * 100;
        per_paper = ((double) num_paper / sum) * 100;
        per_scissors = ((double) num_scissors / sum) * 100;

        // print max
        String prob_max = null;
        String CPU_move = null;
        int CPU_prob = 0;

        if ((per_rock > per_paper) && (per_rock > per_scissors)) {
            prob_max = "rock";
            CPU_move = "paper";
            CPU_prob = (int) per_rock;
        }
        if ((per_paper > per_scissors) && (per_paper > per_rock)) {
            prob_max = "paper";
            CPU_move = "scissors";
            CPU_prob = (int) per_paper;
        }
        if ((per_scissors > per_paper) && (per_scissors > per_rock)) {
            prob_max = "scissors";
            CPU_move = "rock";
            CPU_prob = (int) per_scissors;
        }

        Random generator = new Random();
        if (CPU_move == null) {
            double random = generator.nextDouble();
            //Debug
            //System.out.println(random);
            System.out.println("Prediction Algorithm produced null result, switching to failsafe. RNG ");
            if ((random > 0) || (random <= 0.33)) {
                CPU_move = "rock";
            }
            if ((random > 33) || (random <= 0.66)) {
                CPU_move = "paper";
            }

            if ((random > 0.66)) {
                CPU_move = "scissors";
            }
            
        }

        // Code below is for debugging purposes    
        //System.out.println(list_sorted);
        //System.out.println("Probability of paper : " +  per_paper);
        //System.out.println("Probability of rock : " + per_rock);
        //System.out.println("Probability of scissors : " + per_scissors);       
        System.out.println("CPU  predicts : " + prob_max);
        RockPaperScissorsUI.textCPUMOVE.setText(CPU_move);
        System.out.println("Therefore I play : " + CPU_move);
        System.out.println("Probability of success is " + CPU_prob + "%");
        
        WinCount(CPU_move, move);


        return CPU_move;

    }

    public static void WinCount(String CPU_move, String move) {
        if ((CPU_move.equals("rock"))
                && (move.equals("paper"))) {
            global.PlayerWin++;
        }

        if ((CPU_move.equals("paper"))
                && (move.equals("scissors"))) {
            global.PlayerWin++;
        }

        if ((CPU_move.equals("scissors"))
                && (move.equals("rock"))) {
            global.PlayerWin++;
        }

        if ((CPU_move.equals("rock"))
                && (move.equals("scissors"))) {
            global.CPUwin++;
        }

        if ((CPU_move.equals("paper"))
                && (move.equals("rock"))) {
            global.CPUwin++;
        }

        if ((CPU_move.equals("scissors"))
                && (move.equals("paper"))) {
            global.CPUwin++;
        }



        if (global.CPUwin == 25) {
            Component frame = null;
            JOptionPane.showMessageDialog(frame,
    "Youve been beaten by an Asian designed Asian artificial Intelligence. ");
                    
        }


        RockPaperScissorsUI.CPUwins.setText(Integer.toString(global.CPUwin));
        RockPaperScissorsUI.PlayerWins.setText(Integer.toString(global.PlayerWin));

        double progress = (((double) global.CPUwin / ((double) global.CPUwin + (double) global.PlayerWin)) * 100);
        RockPaperScissorsUI.progress.setValue((int) progress);


    }
}
