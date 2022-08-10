import java.util.Stack;

public class RedBlackTree extends Tree {

    Node guard= new Node("",true);
    Node root= guard;

    RedBlackTree(){
        name="rbt";
    }

    @Override
    public void insert(String key) {
        stats.insertOps++;
        stats.currElements++;
        stats.maxElements = Math.max(stats.currElements, stats.maxElements);
        Node newNode = new Node(key,true);
        Node guardNode = guard;
        Node rootNode = root;
        while(rootNode != guard) {
            guardNode = rootNode;
            stats.substitutes++;
            stats.comp++;
            rootNode = newNode.key.compareToIgnoreCase(rootNode.key) < 0 ? rootNode.left : rootNode.right;
        }
        newNode.parent = guardNode;
        stats.substitutes++;
        stats.comp++;
        if(guardNode == guard) {
            root = newNode;
            stats.substitutes--;
            stats.comp--;
        } else if (newNode.key.compareToIgnoreCase(guardNode.key) < 0) {
            guardNode.left = newNode;
        } else {
            guardNode.right = newNode;
        }
        newNode.left = guard;
        newNode.right = guard;
        newNode.isBlack = false;
        insertFixup(newNode);
    }

    public boolean find(String key) {
        stats.findOps++;
        return findNode(key) != guard;
    }

    public Node findNode(String key){
        Node node = this.root;
        stats.comp++;
        stats.substitutes++;
        while(node != guard && key.compareTo(node.key) != 0) {
            stats.comp +=2;
            if(key.compareToIgnoreCase(node.key) < 0) {
                node = node.left;
            } else {
                node = node.right;
            }
        }
        return node;
    }

    private void insertFixup(Node node) {
        while(!node.parent.isBlack) {
            if(node.parent == node.parent.parent.left) {
                Node temp = node.parent.parent.right;
                stats.substitutes++;
                if(!temp.isBlack) {
                    node.parent.isBlack = true;
                    temp.isBlack = true;
                    node.parent.parent.isBlack = false;
                    node = node.parent.parent;
                } else {
                    stats.substitutes++;
                    if(node == node.parent.right) {
                        node = node.parent;
                        leftRotate(node);
                    }
                    node.parent.isBlack = true;
                    node.parent.parent.isBlack = false;
                    rightRotate(node.parent.parent);
                }
            } else {
                Node t = node.parent.parent.left;
                stats.substitutes++;
                if(!t.isBlack) {
                    node.parent.isBlack = true;
                    t.isBlack = true;
                    node.parent.parent.isBlack = false;
                    node = node.parent.parent;

                } else {
                    stats.substitutes++;
                    if(node == node.parent.left) {
                        node = node.parent;
                        rightRotate(node);
                    }
                    node.parent.isBlack = true;
                    node.parent.parent.isBlack = false;
                    leftRotate(node.parent.parent);
                }
            }
        }
        root.isBlack = true;
        root.parent = guard;
    }

    @Override
    public void inOrder() {
        stats.inorderOps++;
        walkIterative(this.root);
    }

    @Override
    protected void walkIterative(Node root) {
        Stack<Node> stack = new Stack<>();
        Node curr = root;


        while (!stack.empty() || curr != guard )
        {
            if (curr != guard)
            {
                stack.push(curr);
                curr = curr.left;
            }
            else {
                curr = stack.pop();
                System.out.println(curr.key);
                curr = curr.right;
            }
//            System.out.println("STACK SIZE: "+stack.size());
        }
    }

    public String successor(String key) {
        stats.successorOps++;
        Node node = findNode(key);
        if(node != guard) {
            Node suc = successorNode(node);
            return suc != guard ? suc.key : "";
        }
        return "";
    }

    public Node successorNode(Node node) {
        if(node.right != guard) return treeMin(node.right);
        Node temp = node.parent;
        while (temp != guard && node == temp.right) {
            node = temp;
            temp = temp.parent;
        }
        return temp;
    }

    public String min() {
        stats.minOps++;
        Node node = this.root;
        stats.substitutes++;
        while(node.left != guard) {
            stats.substitutes++;
            node = node.left;
        }
        return node.key;
    }

    public String max() {
        stats.maxOps++;
        stats.substitutes++;
        if(root == guard) return "";
        Node node = this.root;
        stats.substitutes++;
        while(node.right != guard) {
            stats.substitutes++;
            node = node.right;
        }
        return node.key;
    }

    public Node treeMin(Node node) {
        stats.substitutes++;
        while(node.left != guard) {
            stats.substitutes++;
            node = node.left;
        }
        return node;
    }

    @Override
    public void delete(String key) {
        stats.deleteOps++;
        stats.currElements--;
        Node node = findNode(key);
        if(node == guard) return;
        Node x;
        Node y = node;
        boolean yCol = y.isBlack;
        stats.substitutes +=2;
        if(node.left == guard) {
            stats.substitutes--;
            x = node.right;
            transplant(node,node.right);
        } else if(node.right == guard) {
            x = node.left;
        } else {
            y= treeMin(node.right);
            yCol = y.isBlack;
            x = y.right;
            stats.substitutes++;
            if(y.parent == node) {
                x.parent = y;
            } else {
                transplant(y,y.right);
                y.right = node.right;
                y.right.parent = y;
            }
            transplant(node,y);
            y.left = node.left;
            y.left.parent = y;
            y.isBlack = node.isBlack;
        }
        if(yCol){
            deleteFix(x);
        }
    }

    private void deleteFix(Node node) {
        stats.substitutes++;
        while(node != root && node.isBlack) {
            stats.substitutes++;
            if(node == node.parent.left) {
                Node right = node.parent.right;
                if(!right.isBlack) {
                    right.isBlack = true;
                    node.parent.isBlack = false;
                    leftRotate(node.parent);
                    right = node.parent.right;
                }
                if(right.left.isBlack && right.right.isBlack) {
                    right.isBlack = false;
                    node = node.parent;
                } else {
                    if (right.right.isBlack) {
                        right.left.isBlack = true;
                        right.isBlack = false;
                        rightRotate(right);
                        right = node.parent.right;
                    }
                    right.isBlack = node.parent.isBlack;
                    node.parent.isBlack = true;
                    right.right.isBlack = true;
                    leftRotate(node.parent);
                    node = root;
                }
            }
            else {
                Node left = node.parent.left;
                if(!left.isBlack) {
                    left.isBlack = true;
                    node.parent.isBlack = false;
                    rightRotate(node.parent);
                    left = node.parent.left;
                }
                if(left.right.isBlack && left.left.isBlack) {
                    left.isBlack = false;
                    node = node.parent;
                } else {
                    if (left.left.isBlack) {
                        left.right.isBlack = true;
                        left.isBlack = false;
                        leftRotate(left);
                        left = node.parent.left;
                    }
                    left.isBlack = node.parent.isBlack;
                    node.parent.isBlack = true;
                    left.left.isBlack = true;
                    rightRotate(node.parent);
                    node = root;
                }
            }
        }
        node.isBlack = true;
    }

    public void transplant(Node u, Node v) {
        stats.substitutes +=2;
        if(u.parent == guard) {
            root = v;
            stats.substitutes--;
        } else if(u == u.parent.left) {
            u.parent.left = v;
        } else {
            u.parent.right = v;
        }
        v.parent = u.parent;
    }

    private void leftRotate(Node node) {
        Node temp =  node.right;
        node.right = temp.left;
        stats.substitutes++;
        if(temp.left != guard) {
            temp.left.parent = node;
        }
        temp.parent = node.parent;
        stats.substitutes += 2;
        if(node.parent == guard) {
            this.root = temp;
            stats.substitutes--;
        } else if(node == node.parent.left) {
            node.parent.left = temp;
        } else {
            node.parent.right = temp;
        }
        temp.left = node;
        node.parent = temp;
    }

    private void rightRotate(Node node) {
        Node temp = node.left;
        node.left = temp.right;
        stats.substitutes++;
        if(temp.right != guard) {
            temp.right.parent = node;
        }
        temp.parent = node.parent;
        stats.substitutes +=2;
        if(node.parent == guard) {
            this.root = temp;
            stats.substitutes--;
        } else if(node == node.parent.right) {
            node.parent.right = temp;
        } else {
            node.parent.left = temp;
        }
        temp.right = node;
        node.parent = temp;
    }
}
