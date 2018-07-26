
//Name: Diang Li
//USC NetID: diangli
//CSCI455 PA2
//Fall 2017

import java.util.ArrayList;
import java.util.Scanner;


/**
   A main program that does a Bulgarian Solitaire Simulation. 
   Program will be able to be run in a few different modes, each of these controlled by a command-line argument.
   Argument "-u" means the initial configuration should be set up by user, instead of generating a random configuration.
   Argument "-s" means Stops between every round of the game. The game only continues when the user hits enter.
   Everytime the user input initial numbers, the class will call judgeValidLine() method to judge numbers are legal,
   and then return a new SolitaireBoard object. If initial configuration is created by program, it returns a new SolitaireBoard object directly.
   Finally, call run() method that includes playRound() method of SolitaireBoard class to display results.  
*/

public class BulgarianSolitaireSimulator {
    /**
       Choose a mode and run the game;
    */
    public static void main(String[] args) {

        boolean singleStep = false;
        boolean userConfig = false;

        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-u")) {
                userConfig = true;
            }
            else if (args[i].equals("-s")) {
                singleStep = true;
            }
        }

        // <add code here>
        SolitaireBoard boardConfiguration = configurationSet(userConfig);
        run(boardConfiguration, singleStep);
    }

 // <add private static methods here>
    /**
       To judge whether the input numbers of users are right, including whether each character of the whole input string is a number or space
       and whether the sum of input numbers are 45.
		
       @param lines input lines from console through users
    */
    private static Boolean judgeValidLine(String lines){
	int sum = 0;
	for(int i = 0; i < lines.length(); i++) {
	    char character = lines.charAt(i);
	    if(!(Character.isDigit(character)||character==' ')) {
	        return false; 
	    }
	}
	Scanner lineScanner = new Scanner(lines);
	while(lineScanner.hasNextInt()){
            sum += lineScanner.nextInt();
        }          
	if(sum>SolitaireBoard.CARD_TOTAL) {return false;}
	    else {return true;}
    }

    /**
       To create initial configuration in 3 steps:
       Choose a mode of initial configuration; 
       Judge the input is legal; 
       Return a new SolitaireBoard object to realize initialization.
	 	
       @param userConfiguration the boolean value to judge which mode is selected about argument "-u"
    */
    private static SolitaireBoard configurationSet(boolean userConfiguration) {
	ArrayList<Integer> list = new ArrayList<>();
	if(!userConfiguration) { // -u mode is disabled,generating a random configuration 
	    return new SolitaireBoard();
	}
	else{// users choose initial configuration by themselves
	    System.out.println("Number of total cards is " + SolitaireBoard.CARD_TOTAL);
            System.out.println ("You will be entering the initial configuration of the cards (i.e., how many in each pile).");
            System.out.println("Please enter a space-separated list of positive integers followed by newline:");
            Scanner in = new Scanner(System.in);
            String line = in.nextLine();
    		
            while(!judgeValidLine(line)){
            	System.out.println("ERROR: Each pile must have at least one card and the total number of cards must be 45");
                System.out.println("Please enter a space-separated list of positive integers followed by newline:");
                line = in.nextLine(); 
    	    }
            Scanner lineScanner1 = new Scanner(line);
            while(lineScanner1.hasNextInt()){
            	list.add(lineScanner1.nextInt());
            }          
            return new SolitaireBoard(list);
        }		
    }

    /**
       Choose a mode to print result, through hitting enter or displaying directly.
		
       @param board  the refer of new SolitaireBoard object
       @param singleStep  the boolean value to judge which mode is selected about argument "-s"
    */
    private static void run(SolitaireBoard board, boolean singleStep) {
        System.out.println("Initial configuration: " + board.configString());       
        int round = 1;
        while(!board.isDone()) {
            board.playRound();
            System.out.println("[" + round++ +"] Current configuration: "+ board.configString());          
            if(singleStep) { 
                System.out.print("<Type return to continue>");
                Scanner in = new Scanner(System.in); 
                in.nextLine();
            }
        }
        System.out.println("Done!");
    }   

}
