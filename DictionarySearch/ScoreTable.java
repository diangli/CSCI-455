//Name: Diang Li
//USC NetID: diangli
//CS 455 PA4
//Fall 2017
/**
 * a score table that store the score value for every single letter
 * get the score of the word given 
 */
public class ScoreTable {
   static int[] scores={1,3,3,2,1,4,2,4,1,8,5,1,3,1,1,3,10,1,1,1,1,4,4,8,4,10};
	
   /**
     * compute the score
     *@param S the word given
     *@return return the score of the word 
    */
    public static int getScore(String wordScore){
       wordScore=wordScore.toLowerCase();  //transform the string into lower case
       int score=0;
       for(int i=0;i<wordScore.length();i++){
	  score+=scores[wordScore.charAt(i)-'a'];
       }
       return score;
    }	
}
