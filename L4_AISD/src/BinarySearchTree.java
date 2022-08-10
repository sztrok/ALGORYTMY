public class BinarySearchTree extends Tree {

    BinarySearchTree(){
        name="bst";
    }

    @Override
    public void insert(String key) {
        stats.insertOps++;
        stats.currElements++;
        stats.maxElements = Math.max(stats.currElements, stats.maxElements);

        Node t = null;
        Node n = new Node(key);
        Node x = this.root;
        while(x != null) {
            stats.substitutes++;
            t = x;
            stats.comp++;
            if(n.key.compareToIgnoreCase(x.key) < 0) {
                x = x.left;
            } else {
                x = x.right;
            }
        }
        n.parent = t;
        stats.substitutes++;
        stats.comp++;
        if(t == null) {
            this.root = n;
            stats.comp--;
        } else if(n.key.compareToIgnoreCase(t.key) < 0) {
            t.left = n;
        } else {
            t.right = n;
        }
    }

    public void transplant(Node u, Node v) {
        stats.substitutes +=2;
        if(u.parent == null) {
            this.root = v;
            stats.substitutes--;
        } else if(u == u.parent.left) {
            u.parent.left = v;
        } else {
            u.parent.right = v;
        }
        stats.substitutes++;
        if(v != null) {
            v.parent = u.parent;
        }
    }

    @Override
    public void delete(String key) {
        stats.deleteOps++;
        stats.currElements--;
        Node z = findNode(key);
        stats.substitutes++;
        if(z != null) {
            stats.substitutes +=2;
            if(z.left == null) {
                transplant(z,z.right);
                stats.substitutes--;
            } else if(z.right == null) {
                transplant(z,z.left);
            } else {
                Node t = treeMin(z.right);
                stats.substitutes++;
                if(t.parent != z) {
                    transplant(t,t.right);
                    t.right = z.right;
                    t.right.parent = t;
                }
                transplant(z,t);
                t.left = z.left;
                t.left.parent = t;
            }
        }
    }
}
