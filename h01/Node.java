public class Node{
    private Node node_link;
    private int data;

    public Node(){
        node_link = null;
    }

    public Node getLink(){
        return node_link;
    }

    public int getData(){
        return data;
    }

    public void setLink(Node newLink){
        node_link = newLink;
    }

    public void setData(int newData){
        data = newData;
    }

    public Node(int initialData, Node initialLink){
        data = initialData;
        node_link = initialLink;
    }
}
