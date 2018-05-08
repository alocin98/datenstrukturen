public class Node implements Comparable<Node> {

    public char name;
    public int frequency;
    public Node left;
    public Node right;
    public String code;

    public Node(char i, int m){
        this.name = i;
        this.frequency = m;
    }
    @Override
    public  int compareTo(Node n){
        return this.frequency - n.frequency;
    }
}
