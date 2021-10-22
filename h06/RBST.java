import java.util.Random;

public class RBST {
	
	private Node root;		// Head node of the tree.
	private Random rand;	// A random object - required to randomly insert nodes into the tree.
	
	// Constructors
	public RBST() {
		root = null;
		rand = new Random();
	}
	public RBST(Node _root) {
		root = _root;
		rand = new Random();
	}

	/**
		Wraper print method to print the contents of the tree. Calls the private print method.
	 */
	public void print() {
		print(root);
		System.out.println();
	}
	/**
		Print method to print the contents of the tree.
	*/
    //do left side first then right
	private void print(Node T) {
	    if(T == null)
            return;
        print(T.getLeft()); //traverse print through the left
        System.out.print(T.getTeam() + " "); //prints the root
        print(T.getRight()); //traverse print through right
    }

	/**
		Wrapper for insertNormal method.
	*/
	public void insertNormal(int team, int rank) {
		root = insertNormal(root, team, rank);
	}
	/**
		Insert the data team at position rank into node T. This is the normal insert routine without any balancing.
	*/
	private Node insertNormal(Node T, int team, int rank) {
        int rankValue;
        if(T == null) //if root is null
            return new Node(team);
		assert (rank >= 1 && rank <= T.getSize() + 1) : "rank should be between 1 and size of the tree <" + (T.getSize()+1) + ">";
        //code for assigning the rankvalue   
        if(T.getLeft() != null) //if T has a left subtree
            rankValue = T.getLeft().getSize() + 1; //the value is the size of the left subtree + 1
        else 
            rankValue = 1; //else the value is 1

        if(rank <= rankValue)//if rank is less than(or equal to I THINK) rankValue
            T.setLeft(insertNormal(T.getLeft(), team, rank)); //traverse to set to the left
        else
            T.setRight(insertNormal(T.getRight(), team, rank - rankValue)); //else traverse set to the right
        T.incSize(); //increment the size of the tree
		return T;	// Need to return the actual tree. 
	}
	
	/**
		Split the tree at psition rank. It returns RET, a RBST array of length two. RET[0] is the left side of the split,
		and RET[1] is the right side of the split. This is a wrapper method that calls the private split method.
	*/
	public RBST[] split(int rank) {
		Node [] ret = split(root, rank);
		RBST [] RET = {null, null};
		RET[0] = new RBST(ret[0]);
		RET[1] = new RBST(ret[1]);
		return RET;
	}
	/**
		The private split method that splits tree T at position rank. 
		It returns an array ret, of two nodes -- ret[0] is the root of the left tree, and
		ret[1] is the root of the right tree of the split.
	*/
	private Node[] split(Node T, int rank) {
		Node [] ret = {null, null};	// ret[0] is the root node to the left side of the split, ret[1] is the right side.	
	    int rankVal;	
		// TODO: Fill your code here for the split method. It is easy to implement this recursively.
		// Your base case will be an empty tree. Your recursive case will have three cases -- think 
		// what happens if the rank of the root == rank, or if rank is smaller or larger than the rank
		// of the root.
	    if(T == null)
            return ret;
        if(T.getLeft() != null)
            rankVal = T.getLeft().getSize() + 1;
        else
            rankVal = 1; // same code as insertNormal


        //juicy meat
        if(rank <= rankVal){ //if rankvalue is greater than rank
            Node[] nodeList = split(T.getLeft(), rank); //left split of the tree put into an array
            T.setLeft(nodeList[1]);
            ret[1] = T;
            ret[0] = nodeList[0];
        }
        else if(rank > rankVal){
            Node[] nodeList = split(T.getRight(), rank - rankVal); //split the tree to the right an into an array
            T.setRight(nodeList[0]); 
            ret[0] = T; //root is root of left
            ret[1] = nodeList[1];
        }
        T.updateSize(); //update size of tree
	    return ret;
    }
	
	/**
		Insert the data team at position rank in the tree. This is a wrapper method that calls the private insert method.
	*/
	public void insert(int team, int rank) {
		root = insert(root, team, rank);
	}
	/**
		The private insert method, that inserts the data team at position rank in the tree rooted at node T. 
		team is inserted at the root with probability 1/(T.getSize()+1). This is done by splitting the tree T
		at position rank-1, creating a new node for team, and attaching the left and right sides of the split as
		the two subtrees of the new node. Otherwise, with probability 1 - 1/(T.getSize()+1), insert recursively
		at either the left tree (rank <= rank of root) or at the right tree (rank > rank of root).
	*/
	private Node insert(Node T, int team, int rank) {
        int treeRank;
        if(T == null) //if tree is null
            return new Node(team); //create a new node
		assert (rank >= 1 && rank <= T.getSize() + 1) : "rank should be between 1 and size of the tree <" + (T.getSize()+1) + ">";
        int randNum = rand.nextInt(T.getSize() + 1);
	
        if(T.getLeft() != null)
            treeRank = T.getLeft().getSize() + 1;
        else
            treeRank = 1;
        
        
        if(randNum == 1){
            Node[] nList = split(T, rank); 
            T = new Node(team, nList[0], nList[1]);
        }
        else if(rank <= treeRank){//if rank is less than(or equal to I THINK) rankValue
            T.setLeft(insert(T.getLeft(), team, rank)); //traverse to set to the left
            T.incSize(); //increment size
        }
        else{
            T.setRight(insert(T.getRight(), team, rank - treeRank)); //else traverse set to the right
		    T.incSize(); //increment size
        }
        return T;	// Need to return the actual tree. 
	}

	/**
		Return the node at position rank in the tree. This is a wrapper method that calls the private select method.
	*/	
	public Node select(int rank) {
		return select(root, rank);
	}
	/**
		The select method that returns the node in the tree at position rank. 
	*/
	private Node select(Node T, int rank) {
	    int rankValue3;	
        if(T == null)
            return null;
		assert (rank >= 1 && rank <= T.getSize()) : "rank should be between 1 and size of the tree <" + T.getSize() + "> ";
		
        if(T.getLeft() != null)
            rankValue3 = T.getLeft().getSize() + 1;
        else
            rankValue3 = 1; // same code as insertNormal
		
        // TODO: Recursive case. Return T if rank is equal to the rank of the root. Else
		// revursively select in either the left tree (rank < rank of root) or the right
		// tree (rank > rank of the root).
        if(rankValue3 == rank)
            return T;
        if(rank > rankValue3) // if rank is greater than rank of root
            return select(T.getRight(), rank - rankValue3);
        if(rank < rankValue3) //if rank is less than rank of root
            return select(T.getLeft(), rank);
		return T;	// Need to change this to return the correct node.
	}

	/**
		Returns the size of the tree.
	*/
	public int getSize() {
		if (root == null) return 0;
		return root.getSize();
	}
}
