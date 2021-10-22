public class IntStack {
    // May create private data here.
    private int[] intStack;
    private int intIndex;

    public IntStack() {
        intStack = new int[100];
        intIndex = 0; //0 to 99, want it to point to the 0 index
    }

    public void push(int x) {
        intStack[intIndex + 1] = x; //adds an index for the number
        intIndex++;   
    }

    public int pop() {
        if(intIndex == -1){
            return -1;
        }
        else{
            int poppedInt = intStack[intIndex]; //pops from the top of the stack using intIndex so we know its the top
            intIndex--;
            return poppedInt;
        }
    }
}
