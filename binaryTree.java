/* Node of a doubly linked list */
import java.util.Scanner;
class Node
{
    int data;
    Node left; // Pointer to next node in DLL
    Node right; // Pointer to previous node in DLL
    Node(int d) {
        data = d;
        left = null;
        right = null;
    }
};
class binaryTree
{
    Node root = null;
    Node chead = null;
    Node ctail = null;
    
    public void insertData (int value) {
        if (root == null) {
            Node newdata = new Node(value);
            root = newdata;
        } else {
            Node leaf = root;
            while(leaf != null) {
                
                if (leaf.data < value) {
                    if (leaf.right == null) {
                        Node newdata = new Node(value);
                        leaf.right = newdata;
                        break;
                    } else {
                        leaf = leaf.right;
                        continue;
                    }
                } else {
                    if (leaf.left == null) {
                        Node newdata = new Node(value);
                        leaf.left = newdata;
                        break;
                    } else {
                        leaf = leaf.left;
                        continue;
                    }
                }
            }
        }
    }
    
    public void printTree(Node leaf) {
        if (leaf.left != null)
            printTree(leaf.left);
        System.out.println(leaf.data);
        if (leaf.right != null)
            printTree(leaf.right);
    }
    public void delete(int value) {
        deleteNode(root, value);
    }
    
    public void deleteNode (Node leaf, int value) {
        if (leaf.data > value) {
            if (leaf.left.data == value) {
                Node successor = null;
                if (leaf.left.right != null) {
                    successor = getSuccessor(leaf.left.right, null); 
                    successor.right = leaf.left.right;
                    successor.left = leaf.left.left;
                    leaf.left = successor;
                }
                else if (leaf.left.left != null)
                    leaf.left = leaf.left.left;
                else
                    leaf.left = successor;
                
            } else {
                deleteNode(leaf.left, value);
            }
        } else {
            if (leaf.right.data == value) {
                Node successor = null;
                if (leaf.right.right != null) {
                    successor = getSuccessor(leaf.right.right, null); 
                    successor.right = leaf.right.right;
                    successor.left = leaf.right.left;
                    leaf.right = successor;
                }
                else if (leaf.right.left != null)
                    leaf.right = leaf.left.left;
                else
                    leaf.right = successor;
                
            } else {
                deleteNode(leaf.right, value);
            }
        }
    }
    
    public Node getSuccessor (Node leaf, Node last) {
        if (leaf.left != null) {
            last = leaf;
            return getSuccessor(leaf.left, last);
        } else {
            if (last != null) {
                if (last.left != null)
                    last.left = null;
                else
                    last.right = null;
            }
            return leaf;
        }
    }
    
}

class binaryTreeDemo {
    public static void main(String args[]){
        Scanner scanner = new Scanner(System.in);
        binaryTree dl = new binaryTree();
        int nodeCount = Integer.parseInt(scanner.nextLine());
        while (nodeCount > 0) {
            dl.insertData(Integer.parseInt(scanner.nextLine()));
            nodeCount--;
        }
        dl.delete(7);
        dl.printTree(dl.root);
    }
}
