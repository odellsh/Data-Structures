import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class WordLadder {
	private static String start;
	private static String end;
	private static StringMap T;			// This map stores the dictionary of words.
	private static StringMap R;			// This map keeps track of all the words that are visited during breadth-first-search.
																	// The key field is the word that is visited, and its value field can hold the predecessor pointer.
	private static Queue Q;					// A queue to perform the breadth-first-search.
    
    public static void search(QNode q){
        R = new StringMap();
        Q = new Queue();
        
        Q.enqueue(q); //enqueue the QNode
        while(!Q.isEmpty()){ //while the queue isnt empty
            QNode qn = Q.dequeue();

            for(int i = 0; i < qn.getWord().length(); i++){
                for(char f = 'a'; f <= 'z'; f++){
                    
                    StringBuffer sb = new StringBuffer(qn.getWord());
                    sb.setCharAt(i, f);
                    String currWord = sb.toString();

                    StringNode sn = T.find(currWord);
                    StringNode rn = R.find(currWord);

                    if(sn != null && rn == null){
                        QNode nNode = new QNode(qn.getDist() + 1, currWord);
                        R.insert(currWord, qn.getWord());
                        Q.enqueue(nNode);
                    }
                }
            }
        }
        StringNode answer = R.find(end);

        if(answer == null) //if word is null
            System.out.println("Duh! Impossible.");
        else{
            System.out.println();
            System.out.println("Yay! A word ladder is possible.");
            print(end);
        }
    }

    public static void print(String s){
        if(s.equals(start)){
            System.out.println(start);
            return; //base case
        }
        StringNode word = R.find(s);
        print(word.getValue());
        System.out.println(s);
    }
	
    public static void main(String [] args) throws IOException {
        // Loading the dictionary of words into the StringMap T.
		T = new StringMap();
		File file = new File("dictionary4");
		Scanner f = new Scanner(file);
		while (f.hasNext()) {
			String word = f.nextLine();
			T.insert(word, "");
		}
		f.close();

		Scanner kb = new Scanner(System.in);
    	System.out.print("Enter the start word: ");
		start = kb.nextLine();
		System.out.print("Enter the end word: ");
		end = kb.nextLine();
        
        QNode st = new QNode(0, start);
        search(st);        
		// TODO: Solution to find the shortest set of words that transforms the start word to the end word.
        //if the start and end are the same word
        
	}
}
