
//Name:Diang Li
//USC NetID: diangli
//CSCI455 PA2
//Fall 2017

import java.util.ArrayList;

/**
   class SolitaireBoard
   The board for Bulgarian Solitaire.  You can change what the total number of cards is for the game
   by changing NUM_FINAL_PILES, below.  Don't change CARD_TOTAL directly, because there are only some values
   for CARD_TOTAL that result in a game that terminates.
   (See comments below next to named constant declarations for more details on this.)
	
   @param  pile  an ArrayList data structure to reserve number of cards in each pile
   @param  pileArray  an Array data structure to reserve number of cards in each pile
   @param  currentSize  the current number of piles after each round
*/


public class SolitaireBoard {
    public static final int NUM_FINAL_PILES = 9;
    // number of piles in a final configuration
    // (note: if NUM_FINAL_PILES is 9, then CARD_TOTAL below will be 45)

    public static final int CARD_TOTAL = NUM_FINAL_PILES * (NUM_FINAL_PILES + 1) / 2;
    // bulgarian solitaire only terminates if CARD_TOTAL is a triangular number.
    // see: http://en.wikipedia.org/wiki/Bulgarian_solitaire for more details
    // the above formula is the closed form for 1 + 2 + 3 + . . . + NUM_FINAL_PILES

    // Note to students: you may not use an ArrayList -- see assgt description for details.
    //public static int roundNum = 1;
	  

/**
   Representation invariant:
   --currentSize is the number of current piles after each round
   --if currentSize>0, the piles are in
   --pileArray[i]>0, (i is from 0 to currentSize-1)the number of cards in each pile must larger than 0
   --the sum of the value from pileArray[0] to pileArray[currentSize-1]
     should be equal to CARD_TOTAL
*/

// <add instance variables here>
    private ArrayList<Integer> pile;
    private int[] pileArray;
    private int currentSize; 

    /**
       Creates a solitaire board with the configuration specified in piles.
       piles has the number of cards in the first pile, then the number of cards in the second pile, etc.
       PRE: piles contains a sequence of positive numbers that sum to SolitaireBoard.CARD_TOTAL
    */
    public SolitaireBoard(ArrayList<Integer> piles) {
	pileArray = new int[CARD_TOTAL+1];
        pile = piles;
	int i = 0;
	for(int aPile: pile){
	    pileArray[i++] = aPile;
	}
	for(int j=0;j<CARD_TOTAL+1;j++){
	    if(pileArray[j]!=0) {
		currentSize++;
	    }
	}
	assert isValidSolitaireBoard();    // sample assert statement (you will be adding more of these calls)
    }


    /**
       Creates a solitaire board with a random initial configuration.
    */
    public SolitaireBoard() {
	pileArray = new int[CARD_TOTAL+1];
	int remainingNumOfCards = CARD_TOTAL;
	int i = 0;
	while(remainingNumOfCards>0){
	    int cardsInPile = (int)(Math.random()*remainingNumOfCards)+1;
	    pileArray[i++] = cardsInPile;
	    remainingNumOfCards -= cardsInPile;
	}	
	currentSize = i;
	
        assert isValidSolitaireBoard();
    }

    /**
       Plays one round of Bulgarian solitaire.  Updates the configuration according to the rules
       of Bulgarian solitaire: Takes one card from each pile, and puts them all together in a new pile.
       The old piles that are left will be in the same relative order as before, 
       and the new pile will be at the end.
    */
    public void playRound() {
	int count0 = 0;
	int numOfCards = 0;
	for(int i = 0; i < currentSize; i++) {
	    pileArray[i] = pileArray[i]-1;
		numOfCards++;
	}
	int size = currentSize;
	for(int i = 0; i < size; i++) {
	    if(pileArray[i] == 0){
		count0++;
		currentSize--;
	    }
	    else{
		pileArray[i-count0] = pileArray[i];
	    }
	}
	for(int i = size-1;i>=currentSize;i--){
	    pileArray[i]=0;
	}
	currentSize = currentSize+1;
	pileArray[currentSize-1] = numOfCards;	
		
	assert isValidSolitaireBoard();
    }
    /**
       Returns true iff the current board is at the end of the game.  That is, there are NUM_FINAL_PILES
       piles that are of sizes 1, 2, 3, . . . , NUM_FINAL_PILES, in any order.
    */

    public boolean isDone() {
	Boolean[] value = new Boolean[NUM_FINAL_PILES];
	for(int i=0;i<NUM_FINAL_PILES;i++){
	    value[i] = false;
	}
	if(currentSize!=NUM_FINAL_PILES){
	    return false;
	}
	else{
	    for(int k=0;k<NUM_FINAL_PILES;k++){
		int val = pileArray[k];
		if(val>NUM_FINAL_PILES){
		    return false;
		}
		else if(!value[val-1]){//index of value[] is from 0 to 8, val is from 1 to 9
		    value[val-1] = true;
		}
	    }
	    for(int k=0;k<NUM_FINAL_PILES;k++){
		if(value[k]!=true) return false;
	    }
			
	    assert isValidSolitaireBoard();
	    return true; // dummy code to get stub to compile
	}
    }
	 

    /**
       Returns current board configuration as a string with the format of
       a space-separated list of numbers with no leading or trailing spaces.
       The numbers represent the number of cards in each non-empty pile.
    */
    public String configString() {
	String s = new String();
	for(int i=0;i<currentSize-1;i++){	
	    s += pileArray[i]+" ";		
	} 
	s += pileArray[currentSize-1];
		
	assert isValidSolitaireBoard();
	return s; // dummy code to get stub to compile
	
	}

    /**
       Returns true if the solitaire board data is in a valid state
       (See representation invariant comment for more details.)
    */
    private boolean isValidSolitaireBoard() {
        int sum = 0;
	for (int i=0;i<currentSize;i++){
	    sum += pileArray[i];
	    if(pileArray[i]<=0) {return false;}
	}
	if(sum != CARD_TOTAL){
	    return false;
	}
        return true;  // dummy code to get stub to compile
    }
}
