import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Stack;

abstract class Tree implements TreeInterface {

    String name;
    Node root= null;
    public Stats stats= new Stats();

    @Override
    public void inOrder(){
        stats.inorderOps++;
        walkIterative(this.root);
    }

    protected void walkIterative(Node root){
        Stack<Node> stack=new Stack<>();
        Node current= root;

        while(!stack.empty() || current!=null){
            stats.substitutes++;
            if(current!=null){
                stack.push(current);
                current= current.left;
            }
            else{
                current= stack.pop();
                System.out.println(current.key+" ");
                current=current.right;
            }
        }

    }

    @Override
    public void load(String path) throws IOException{
        stats.loadOps++;
        BufferedReader reader= new BufferedReader(new FileReader(path));
        String line= reader.readLine();
        while (line!=null){
            String[] lineArr= line.split("[\\s,:;.?!\"]");
            for(String string: lineArr){
                string = string.replaceAll("^[^a-zA-Z]*", "");
                if(string.compareToIgnoreCase("") != 0)
                    insert(string);
            }
            line=reader.readLine();
        }
    }

    @Override
    public boolean find(String key) {
        stats.findOps++;
        return findNode(key)!=null;
    }

    public Node findNode(String key){
        Node node = this.root;
        stats.comp++;
        while(node != null && key.compareTo(node.key) != 0) {
            stats.comp +=2;
            if(key.compareToIgnoreCase(node.key) < 0) {
                node = node.left;
            } else {
                node = node.right;
            }
        }
        return node;
    }

    @Override
    public String min() {
        stats.minOps++;
        if(root==null) return "";
        Node node= this.root;
        stats.substitutes++;
        while(node.left!=null){
            stats.substitutes++;
            node=node.left;
        }
        return node.key;
    }

    @Override
    public String max() {
        stats.maxOps++;
        if(root==null) return "";
        Node node= this.root;
        stats.substitutes++;
        while(node.right!=null){
            stats.substitutes++;
            node=node.right;
        }
        return node.key;
    }

    @Override
    public String successor(String key) {
        stats.successorOps++;
        Node node = findNode(key);
        stats.substitutes++;
        if(node != null) {
            Node suc = successorNode(node);
            return suc != null ? suc.key : "";
        }
        return "";
    }

    public Node successorNode(Node node) {
        stats.substitutes++;
        if(node.right != null) return treeMin(node.right);
        Node successor = node.parent;
        stats.substitutes++;
        while (successor != null && node == successor.right) {
            stats.substitutes++;
            node = successor;
            successor = successor.parent;
        }
        return successor;
    }

    public Node treeMin(Node x) {
        while(x.left != null) {
            x = x.left;
        }
        return x;
    }

    public Node treeMax(Node x) {
        while(x.right != null) {
            x = x.right;
        }
        return x;
    }

    static class Node{
        String key;
        Node parent;
        Node left;
        Node right;
        Boolean isBlack;

        Node(String key){
            this.key=key;
            right=null;
            left=null;
            parent=null;
        }

        Node(String key, boolean isBlack){
            this.key=key;
            this.isBlack=isBlack;
            parent=null;
            left=null;
            right=null;
        }

    }


}
