import java.lang.Integer;

public class Prog {
    public static final int PNUM = 128189;

    public static void main(String [] args) {
        
        if (args.length != 2) {
            System.out.println("Please execute: java Prog <n> <p>");
            System.out.println("n is the input size, and p is the program number.");
            System.exit(0);
        }

        int n = Integer.parseInt(args[0]);
        int p = Integer.parseInt(args[1]);

        switch(p) {
            case 1: prog1(n);
                            break;
            case 2: prog2(n);
                            break;
            case 3: prog3(n);
                            break;
            case 4: prog4(n);
                            break;
            default: System.out.println("Invalid program number. Only 1-4.");
        }
    }

    private static void prog1(int n) {
        for(int i = 0; i < n; i++){
            System.out.println(i * n);       
        }
    }

    private static void prog2(int n) {
        for(int i = 0; i < n; i++){
            System.out.println(i);
        }
    }

    private static void prog3(int n) { 
        HashFunctions hf = new HashFunctions(n);
        int[] acceptableHash = new int[PNUM];
        int initialIndex = hf.hash3(0);
        int arrayIndex = 1;
        acceptableHash[0] = 0;
        int numKeys = 0;
         
        for(int i = 1; i < PNUM; i++){
            if (hf.hash3(i) == initialIndex){
                acceptableHash[arrayIndex] = i;
                arrayIndex++;
            }
        }
        
        for(int i = 0; i < n; i++){
            for(int j = 0; j < arrayIndex; j++){
                if(numKeys == n){
                    break;
                }
                int multCalc = i * PNUM;
                int key = multCalc + acceptableHash[j];
                System.out.println(key + " " + hf.hash3(key));  //print key
                numKeys++;       
                
            }
        }

    }

    private static void prog4(int n) {   
        HashFunctions hf = new HashFunctions(n);
        int numKeys = 0;
        int[] goodNum = new int[n];

        for(int i = 0; i < n; i++){
            int initialIndex = hf.hash4(i);
            for(int j = 0; j < (n * n); j++){
                if (numKeys >= n)
                    break;
                if(hf.hash4(j) == initialIndex){
                    goodNum[numKeys] = j;
                    numKeys++;
                }          
            }
            if (numKeys >= n)
                break;
            numKeys = 0;
        }
        for(int i = 0; i < n; i++){
            System.out.println(goodNum[i]);
        }
    }
}
