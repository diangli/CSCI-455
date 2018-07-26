// Name: Diang Li
// USC NetID: diangli
// CS 455 PA4
// Fall 2017

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


/**
 * A dictionary of all anagram sets. 
 * Note: the processing is case-sensitive; so if the dictionary has all lower
 * case words, you will likely want any string you test to have all lower case
 * letters too, and likewise if the dictionary words are all upper case.
 */

public class AnagramDictionary {

   /**
    * Create an anagram dictionary from the list of words given in the file
    * indicated by fileName.  
    * PRE: The strings in the file are unique.
    * @param fileName  the name of the file to read from
    * @throws FileNotFoundException  if the file is not found
    */
	private static Set<String> set=new HashSet<String>();
	public AnagramDictionary(String fileName) throws FileNotFoundException {
	    BufferedReader reader = new BufferedReader(new FileReader(fileName));
	    String word;
	    try {
		while ((word = reader.readLine()) != null){
		    set.add(word);
	        }
	    } catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
       }

/**
    * Get all anagrams of the given string. This method is case-sensitive.
    * E.g. "CARE" and "race" would not be recognized as anagrams.
    * @param s string to process
    * @return a list of the anagrams of s
    * 
    */
	public ArrayList<String> getAnagramsOf(String s) {	
	    char[] str = s.toCharArray();
	    Arrays.sort(str);
	    ArrayList<String> vec = new ArrayList<>();
	    if (str.length == 0){
		return vec;
	    }
	    change(str, 0, str.length, vec);
	    return vec;
	}
   
	
	/**
	 * a helper method to find all the anagrams
	 * @param s a char array that transformed from the given string 
	 * @param begin the begin position 
	 * @param end the end position
	 * @param vec an ArrayList to store all the anagrams0
	 */
	void change(char[] letters, int begin, int end, ArrayList<String> vec) {
	     if (begin == end){
		 String ans=new String(letters);
		 if(set.contains(ans)){
		      vec.add(ans);
		 }
	     }
	     for (int i = begin; i < end; ++i) {
		 if (i != begin && letters[i] == letters[begin]) {
		     continue;
		 }
		 char tmp = letters[begin];
		 letters[begin] = letters[i];
		 letters[i] = tmp;
		 change(letters.clone(), begin + 1, end, vec);
	     }

	}   
    }
