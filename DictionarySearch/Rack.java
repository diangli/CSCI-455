//Name: Diang Li
//USC NetID: diangli
//CS 455 PA4
//Fall 2017

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * A Rack of Scrabble tiles
 */

public class Rack {
   
   /**
    * Finds all subsets of the multiset starting at position k in unique and mult.
    * unique and mult describe a multiset such that mult[i] is the multiplicity of the char
    *      unique.charAt(i).
    * PRE: mult.length must be at least as big as unique.length()
    *      0 <= k <= unique.length()
    * @param unique a string of unique letters
    * @param mult the multiplicity of each letter from unique.  
    * @param k the smallest index of unique and mult to consider.
    * @return all subsets of the indicated multiset
    * @author Claire Bono
    */
   private static ArrayList<String> allSubsets(String unique, int[] mult, int k) {
      ArrayList<String> allCombos = new ArrayList<>();
      
      if (k == unique.length()) {  // multiset is empty
         allCombos.add("");
         return allCombos;
      }
      
      // get all subsets of the multiset without the first unique char
      ArrayList<String> restCombos = allSubsets(unique, mult, k+1);
      
      // prepend all possible numbers of the first char (i.e., the one at position k) 
      // to the front of each string in restCombos.  Suppose that char is 'a'...
      
      String firstPart = "";          // in outer loop firstPart takes on the values: "", "a", "aa", ...
      for (int n = 0; n <= mult[k]; n++) {   
         for (int i = 0; i < restCombos.size(); i++) {  // for each of the subsets 
                                                        // we found in the recursive call
            // create and add a new string with n 'a's in front of that subset
            allCombos.add(firstPart + restCombos.get(i));  
         }
         firstPart += unique.charAt(k);  // append another instance of 'a' to the first part
      }
      
      return allCombos;
   }
   
   /**
    * use a recursion to find out the subsets
    * @param s the user's input
    * @return return all the subsets of the given rack
    */
   public static ArrayList<String> getSubSet(String s){
      Map<Character,Integer> map=new HashMap<>();
	 for(int i=0;i<s.length();i++){
	    if(map.containsKey(s.charAt(i))){
	       map.put(s.charAt(i), map.get(s.charAt(i))+1);
	    }
	    else{
	       map.put(s.charAt(i), 1);
	    }
	 }
	 String unique="";
	    int[] mult=new int[map.size()];
	    int i=0;
	    for(Character key : map.keySet()) {
	       unique+=key;
	       mult[i++]=map.get(key);
	    }
	    ArrayList<String> subSets=allSubsets(unique,mult,0);
	    return subSets;
	   
        }

   }
