
public class PriorityQueue {
	private Interval [] heap; // An array that encodes a max-heap data structure.
	private int size;	// The size of allocated buffer for the heap.
	private int numElements;	// The number of elements currently stored. 

	/**
		Constructor: s is the initial size of the heap.
	*/
	public PriorityQueue(int s) {
		size = s;
		heap = new Interval[size + 1];	// 1 extra element allows us to use 1-based indexing. The max heap stores intervals keyed on their lengths.
		numElements = 1;
	}

	/**
		Inserts a new Interval k into the heap. Automatically expands the heap if the buffer allocated is full.
	*/
	public void insert(Interval k) {
		if (numElements == size) {
			// Expand the buffer allocated for the heap to another buffer that is twice as big.
		    Interval[] expansion = new Interval[(size * 2) + 1]; //create a new array with *2 + 1 for 1-based indexing
            for(int i = 0; i < size; i++){
                expansion[0] = heap[0]; //puts the old element into the same position in the new array
            }
            size *= 2; // and doubles the size
        }
		// Insert without buffer expansion here.
        heap[numElements + 1] = k; //puts the element into the heap at numElements + 1 because we dont need buffer expansion
	    siftUp(++numElements);
    }

	/**
		Returns the maximum Interval from the heap (usually the one with the largest length. See the compareTo function of Interval for more details on the comparison.
	TODO: Please complete this method.
	*/
	public Interval remove_max() {
		if (numElements == 0) return null; // Retuns null if heap is empty.
		// Remove_max code here.
        
        Interval maxElem = heap[1]; //puts the first element into max
        Interval swi = heap[1]; //puts A[1] into switch
        heap[1] = heap[numElements--];//puts the num elements decremented by 1
        heap[numElements] = swi;
        siftDown(1); //i think we sify up with remove min so it should be down for max. DOUBLE CHECK tho.
        
        return maxElem; // Replace this statement with returning the max element (root) in the heap.
	}

	/**
		This function prints the contents of the array that encodes a heap.
	*/
	public void print() {
		System.out.println("Printing heap:");
		for (int i = 1; i < numElements; ++i)
			System.out.println(heap[i]);
	}

    //sift up
    public void siftUp(int i){
       int parent = (int)Math.floor((i - 1) / 2);
       Interval s = heap[i];
       Interval e = heap[parent];
       if(s.compareTo(e) > 0 && e != null){
           Interval swit = heap[i];
           heap[i] = heap[parent];
           heap[parent] = swit;
           
           siftUp(parent);          
        }
    }
    int saveInt;
    //sift down
    public void siftDown(int i){
        int leftbebe = (2 * i) + 1;
        int rightbebe = (2 * i) + 2;
        
        
        if(rightbebe <= numElements && leftbebe >= numElements) //if numElements is larger than right bebe and the element of right bebe is larger than left bebe in heap
            saveInt = rightbebe; //save the rightbebe into an int variable
        else if(leftbebe <= numElements && rightbebe >= numElements)    //else
            saveInt = leftbebe; //save the leftbebe into an int variable
        else if(leftbebe >= numElements && rightbebe >= numElements)
            return;

        Interval switcher = heap[i]; //swap again
        heap[i] = heap[saveInt];
        heap[saveInt] = switcher;
        siftDown(saveInt);
    }
}
