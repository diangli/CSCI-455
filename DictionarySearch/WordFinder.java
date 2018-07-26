//Name: Diang Li
//USC NetID: diangli
//CS 455 PA4
//Fall 2017
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

/**
 * This class contains the main method. 
 * 
 * If the command-line argument is left off. it will use the Scrabble dictionary
 * file sowpods.txt from the same directory as you are running your program.
 * If the dictionary file specified does not exist, your program will print an 
 * informative error message(that includes the file name) and exit.
 * 
 * Find all the legal words as well as their scores according to the anagramDictioary 
 * and all the subsets of the rack   
 * 
 */
public class WordFinder {

    public static void main(String[] args) {
	try {
	    String fileName;
		if(args.length<1) {
		    fileName="sowpods.txt";
		}
		else {
		    fileName=args[0];
		}
		AnagramDictionary anagramDictionary=new AnagramDictionary(fileName);
		@SuppressWarnings("resource")
		Scanner input=new Scanner(System.in);
		System.out.println("Type . to quit.");
		System.out.print("Rack? ");
		String s=input.next();
		while(!s.equals(".")){  //quit the loop when user inputs "."
		ArrayList<String> subSets = Rack.getSubSet(s);
		ArrayList<String> result=new ArrayList<>();
		for (String string : subSets) {
		ArrayList<String> list=anagramDictionary.getAnagramsOf(string);
		for (String o : list) {
		    result.add(o);
		}
	    }
	    Collections.sort(result, new Comparator<String>() {  //sort with a comparator for printing the result  
		@Override
		public int compare(String o1, String o2) {
		    // TODO Auto-generated method stub
		    if(ScoreTable.getScore(o2)-ScoreTable.getScore(o1)!=0)
			return ScoreTable.getScore(o2)-ScoreTable.getScore(o1);
		    else{
			return o1.compareTo(o2);
		    }
		}
	     });
	     char[] chars=s.toCharArray();
	     Arrays.sort(chars);
	     System.out.printf("We can make %d words from \"%s\"\n",result.size(),new String(chars));
	     System.out.println("All of the words with their scores (sorted by score):");
	     for(int i=0;i<result.size();i++){
		 System.out.printf("%d: %s\n",ScoreTable.getScore(result.get(i)),result.get(i));
	     }		
	     System.out.print("Rack? ");
	     s=input.next();	
	 }
     } catch (FileNotFoundException e) {
	 System.out.println("the file is not exist");
     }
  }
}
