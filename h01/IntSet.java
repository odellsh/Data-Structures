

public class IntSet {
    //private int [] set;     // An array to represernt the set. This is always maintained in sorted order.
    //private int capacity;   // The maximum allocated memory for the set.
    private int size;       // The number of elements currently stored in the set.
    private Node head;

    public IntSet() {
        size = 0;
        head = new Node();
        //capacity = 10;
        //set = new int [capacity];
    }
    
    /* Find if a key is present in the set. Returns true if the key is present, otehrwise false.*/
    public boolean find(int key) {
        //binary search
        //boolean for check
        boolean found = false;
        Node pointer = head;

        for(int i = 0; i < size; i++) {
            if (pointer.getData() == key){
                found = true;
            }
            else {
                pointer = pointer.getLink();
            }
        }
        return found;
    }

   
    Node noValue = null; 
    /* Insert a key into the set. */
    public void insert(int key) {
        Node pointer = head;
        
        if(size == 0){
            noValue = new Node(key, null);
            head = noValue;
        }
        else if(key < head.getData()){
            noValue = new Node(key, head);
            head = noValue;
        }
        else{
            while (pointer.getLink() != null && pointer.getLink().getData() < key){ //go until you find something bigger than key
                pointer = pointer.getLink();
            }
            Node add_in = new Node(key, pointer.getLink()); //add a new node where pointer is
            pointer.setLink(add_in); //create a new link
        }
        size++;
    }
    
    /* Remove a key from the set. */
    public void remove(int key) {
        Node pointer = head;
        Node last = null;
        
        if(key == pointer.getData()){
            head = head.getLink();
        }
        else{
            while (pointer.getData() != key){
                last = pointer;
                pointer = pointer.getLink();
            }
            last.setLink(pointer.getLink());
        }
        size--;
    }
    
    /* Print the contents of the set in sorted order. */
    public void print() {
        Node pointer = head;
        while (pointer != null){
            System.out.print(pointer.getData() + ", ");
            pointer = pointer.getLink();
        }
        System.out.println();
    }
}
