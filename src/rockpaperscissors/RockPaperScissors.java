



/*
 * Rock Paper Scissors by Roy H & Kanga 
 */
package rockpaperscissors;
import java.io.*;
/**
 *
 * @author Roy & Kanga
 */
public class RockPaperScissors {
    
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
     input();
     
        
    }
    
    
    public static void input () {
        
          System.out.print("Enter your move: ");

      //  open up standard input
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

      String move = null;

      try {
         move = br.readLine();
      } catch (IOException ioe) {
         System.out.println("IO error trying to read your name!");
         System.exit(1);
      }

    }
    
    public static boolean checkInput(String move) {
        
        return false;
    }
    
    
    
}
