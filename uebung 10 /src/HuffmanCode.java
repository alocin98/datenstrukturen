import java.util.ArrayList;
import java.util.PriorityQueue;


public class HuffmanCode {

    public Node root;
    private static int numberOfCaracters = 0;
    static ArrayList<String> array = new ArrayList();

    void prefixCode (String s) {

        PriorityQueue<Node> Queue = new PriorityQueue<Node>();

        char c;
        int distinct = 0;

        while (s.length()>0) {
            c = s.charAt(0);
            String tempS = "" + c;
            int replace = s.length() - s.replace(tempS, "").length();
            Node nodi = new Node(c, replace);
            Queue.add(nodi);

            distinct ++;

            s = s.replace(tempS, "");
        }
        buildHuffmanTree(Queue,distinct);

        root = Queue.poll();
    }

    private void buildHuffmanTree(PriorityQueue<Node> Queue, int distinct){
        for (int i=1; i<distinct; i++) {
            Node x = Queue.poll();
            Node y = Queue.poll();
            Node nodi = new Node('§', x.frequency+y.frequency);
            nodi.left = x;
            nodi.right = y;
            Queue.add(nodi);
        }
    }

    void printCode (String s) {

        numberOfCaracters = 0;

        System.out.println("Input-String: \n" + s);

        prefixCode(s);

        setHuffmanCodes(root, "");

        String c = "";
        for (int i=0; i<s.length(); i++) {
            c += traverseTree(root, s.charAt(i));
        }
        System.out.println("Huffman coded: \n" + c + "\n");
    }

    private String setHuffmanCodes(Node node, String code) {
        if(node.left!=null){
            setHuffmanCodes(node.left, code + "0");
        }
        if(node.right!=null){
            setHuffmanCodes(node.right, code + "1");
        }
        if(node.left==null && node.right==null) {
            node.code=code;
        }
        if(node.name != '§') {
            array.add(node.code);
            numberOfCaracters++;
        }
        return null;
    }

    private String traverseTree(Node n, char c) {
        if (n.name==c) {
            return n.code;
        }
        if (n.left==null && n.right==null)
            return null;

        String left = traverseTree(n.left, c);
        String right = traverseTree(n.right, c);

        if (left!=null)
            return left;
        if (right!=null)
            return right;
        return null;
    }

    public static void main(String[] args) {
        HuffmanCode huffman = new HuffmanCode();
        String s1 = "Jobs launched into a sermon about how the Macintosh and its software would be so easy to use that there would be no manuals." + "\n";
        String s2 = "An academic career in which a person is forced to produce scientific writings in great amounts creates a danger of intellectual superficiality, Einstein said." + "\n";
        huffman.printCode(s1);
        System.out.println("Average of bits per caracter: \n" + calculateAverage() + "\n\n");
        array.clear();
        huffman.printCode(s2);
        System.out.println("Average of bits per caracter: \n" + calculateAverage() + "\n\n");
    }

    public static int calculateAverage(){
        Integer sum = 0;
        if(!array.isEmpty()) {
            for (String a : array) {
                sum += a.length();
            }
            return sum / array.size();
        }
        return sum;
    }

}