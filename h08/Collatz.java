import java.util.*;

public class Collatz{

    static int max = 10000000;
    static int[] numLengthArray = new int[max];
    static long[] numArray = new long[max];

    static int index = 0;

    public static int collatzLength(long n){
        int length;
        if(n == 1){
            return 1;
        }
        for(int i = 0; i < index; i++){
            if(n == numArray[i]){
                return numLengthArray[i];
            }
        }

        if((n % 2) == 0)
            length = 1 + collatzLength(n / 2);
        else
            length = 1 + collatzLength((3 * n) + 1);
        
        numArray[index] = n;
        numLengthArray[index] = length;
        index++;
        return length;
    }

    public static void main(String[] args){
        Scanner in = new Scanner(System.in);

        System.out.println("Enter the range: ");
        int num1 = in.nextInt();
        int num2 = in.nextInt();
        int highVal = collatzLength(num1);
        int highSource = num1;

        for(int i = num1 + 1; i <= num2; i++){
            int collatedNum = collatzLength(i);

            if(collatedNum > highVal){
                highVal = collatedNum;      
                highSource = i;
            }
        }
        System.out.println("The number with the maximum Collatz length in the range: " + highSource);
        System.out.println("Its Collatz length: " + highVal);
    }
}
