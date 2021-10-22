import java.util.Scanner;
import java.io.*;

/*
 * This class implements a spell checker application. 
 * This class requires a proper implementation to the StirngSet class.
 */
public class SpellChecker {

  public static void main(String [] args) {

    File f = new File("dictionary");
    StringBuffer holder;

    try {
      Scanner sk = new Scanner(f);
        
      StringSet x = new StringSet();
    
      // Read in the entire dictionary...
      while (sk.hasNext()) {
        String word = sk.next();
        x.insert(word.toLowerCase());      
      }
      System.out.println("Dicitonary loaded...");
     
      sk = new Scanner(System.in);
      
      // Keep suggesting alternatives as long as the user makes an input.
      while (sk.hasNext()) {
        String word = sk.next();
        word = word.toLowerCase();
        if (x.find(word))
	  			System.out.println(word + " is correct.");
        else {
            System.out.println("Suggesting alternatives ...");

            for(int i = 0; i < word.length(); i++){ //for each letter in the word
                for(char alphabet = 'a'; alphabet <= 'z'; alphabet++){ //itterate throught the alphabet
	                holder = new StringBuffer(word); //puts the wrongly spelled word into a buffer
                    holder.setCharAt(i, alphabet); //replace i with the letter of the alphabet currently on
                    String correctedWord = holder.toString(); //turn the StringBuffer word back into a string

                    if (x.find(correctedWord)) //if its in the dictionary
                        System.out.println(correctedWord); //print the word
                }
            }       
        }
      }
			
    } catch (FileNotFoundException e) {
      System.out.println("Cannot open file " + f.getAbsolutePath());
      System.out.println(e);
    } 
  } 
}
