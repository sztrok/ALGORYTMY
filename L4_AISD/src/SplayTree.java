
public class SplayTree extends Tree{

    SplayTree(){
        name="splay";
    }

    @Override
    public void insert(String key) {
        stats.insertOps++;
        stats.currElements++;
        stats.maxElements = Math.max(stats.currElements, stats.maxElements);
        Node node = new Node(key);
        Node newNode = null;
        Node temp = root;
        stats.substitutes++;
        while(temp != null) {
            stats.substitutes++;

            newNode = temp;
            stats.comp++;
            if(node.key.compareToIgnoreCase(temp.key) < 0 )
                temp = temp.left;
            else
                temp = temp.right;
        }
        node.parent = newNode;

        stats.substitutes++;
        stats.comp++;
        if(newNode == null){
            root = node;
            stats.comp--;
        }
        else if(node.key.compareToIgnoreCase(newNode.key) < 0 )
            newNode.left = node;
        else
            newNode.right = node;

        splay(node);
    }

    @Override
    public void delete(String key) {
        stats.deleteOps++;
        stats.currElements++;
        Node node = findNode(key);
        if(node == null) return;
        this.splay(node);

        SplayTree leftSubtree = new SplayTree();
        leftSubtree.root = this.root.left;
        if(leftSubtree.root != null)
            leftSubtree.root.parent = null;

        SplayTree rightSubtree = new SplayTree();
        rightSubtree.root = this.root.right;
        if(rightSubtree.root != null)
            rightSubtree.root.parent = null;

        if(leftSubtree.root != null) {
            Node max = leftSubtree.treeMax(leftSubtree.root);
            leftSubtree.splay(max);
            leftSubtree.root.right = rightSubtree.root;
            this.root = leftSubtree.root;
        }
        else {
            this.root = rightSubtree.root;
        }
    }

    @Override
    public boolean find(String key) {
        Node node = findNode(key);
        stats.substitutes++;
        if(node != null) splay(node);
        return node != null;
    }

    public void splay(Node node) {
        while(node.parent != null) {
            if(node.parent == this.root) {
                if(node == node.parent.left) {
                    rightRotate(node.parent);
                }
                else {
                    leftRotate(node.parent);
                }
            }
            else {
                Node parent = node.parent;
                Node grandParent = parent.parent;

                if(node.parent.left == node && parent.parent.left == parent) {
                    rightRotate(grandParent);
                    rightRotate(parent);
                }
                else if(node.parent.right == node && parent.parent.right == parent) {
                    leftRotate(grandParent);
                    leftRotate(parent);
                }
                else if(node.parent.right == node && parent.parent.left == parent) {
                    leftRotate(parent);
                    rightRotate(grandParent);
                }
                else if(node.parent.left == node && parent.parent.right == parent) {
                    rightRotate(parent);
                    leftRotate(grandParent);
                }
            }
        }
    }

    private void leftRotate(Node node) {
        Node traversal =  node.right;
        node.right = traversal.left;
        stats.substitutes++;
        if(traversal.left != null) {
            traversal.left.parent = node;
        }
        traversal.parent = node.parent;
        stats.substitutes += 2;
        if(node.parent == null) {
            this.root = traversal;
            stats.substitutes--;
        } else if(node == node.parent.left) {
            node.parent.left = traversal;
        } else {
            node.parent.right = traversal;
        }
        traversal.left = node;
        node.parent = traversal;
    }

    private void rightRotate(Node node) {
        Node traversal = node.left;
        node.left = traversal.right;
        stats.substitutes++;
        if(traversal.right != null) {
            traversal.right.parent = node;
        }
        traversal.parent = node.parent;
        stats.substitutes +=2;
        if(node.parent == null) {
            this.root = traversal;
            stats.substitutes--;
        } else if(node == node.parent.right) {
            node.parent.right = traversal;
        } else {
            node.parent.left = traversal;
        }
        traversal.right = node;
        node.parent = traversal;
    }
}
