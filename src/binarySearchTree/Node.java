package binarySearchTree;

public class Node<T extends Comparable<T>> {

    private T key;
    private Node<T> left;
    private Node<T> right;

    public Node(T key) {
        this.key = key;
    }

    public T getKey() {
        return key;
    }

    public Node<T> getLeft() {
        return left;
    }

    public Node<T> getRight() {
        return right;
    }

    public void setLeft(Node<T> newLeft) {
        this.left = newLeft;
    }

    public void setRight(Node<T> newRight) {
        this.right = newRight;
    }


    /*
     * Slightly adapted from http://www.algolist.net/Data_structures/Binary_search_tree/Removal
     */

    public boolean remove(T key, Node<T> parent) {

        if(key.compareTo(this.key) < 0) {
            
            if(left != null) {
                return left.remove(key, this);
            } else {
                return false;
            }

        } else if(key.compareTo(this.key) > 0) {

            if(right != null) {
                return right.remove(key, this);
            } else {
                return false;
            }

        } else {

            if(left != null && right != null) {
                
                this.key = right.getMinimumKey();
                right.remove(this.key, this);

            } else if(parent.getLeft() == this) {
                parent.setLeft( (left != null) ? left : right );
            } else if(parent.getRight() == this) {
                parent.setRight( (left != null) ? left : right );
            }

            return true;
        }
    }

    /*
     * Slightly adapted from http://www.algolist.net/Data_structures/Binary_search_tree/Removal
     */

    private T getMinimumKey() {
        
        if(left == null) {
            return key;
        } else {
            return left.getMinimumKey();
        }
    }
}
