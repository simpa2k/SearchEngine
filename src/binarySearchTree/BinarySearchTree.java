package binarySearchTree;

/*
 * Translated to Java from http://www.cprogramming.com/tutorial/lesson18.html
 * except where otherwise stated.
 */

public class BinarySearchTree<T extends Comparable<T>> {

    private Node<T> root;

    public BinarySearchTree() {
        root = null;
    }

    private Node<T> search(T key, Node<T> leaf) {
        
        if(leaf != null) {

            if(key.compareTo(leaf.getKey()) == 0) {

                return leaf;

            } else if(key.compareTo(leaf.getKey()) < 0) {

                return search(key, leaf.getLeft());

            } else {

                return search(key, leaf.getRight());

            }
        } else {
            return null;
        }
    }

    public Node<T> search(T key) {
        return search(key, root);
    }

    private void insert(T key, Node<T> leaf) {

        if(key.compareTo(leaf.getKey()) < 0) {

            if(leaf.getLeft() != null) {
                insert(key, leaf.getLeft());
            } else {
                leaf.setLeft(new Node<T>(key));
            }

        } else if(key.compareTo(leaf.getKey()) >= 0) {

            if(leaf.getRight() != null) {
                insert(key, leaf.getRight());
            } else {
                leaf.setRight(new Node<T>(key));
            }
        }
    }

    public void insert(T key) {
       
        if(root != null) {
            insert(key, root);
        } else {
            root = new Node<T>(key);
        }
    }

    /*
     * Slightly adapted from http://www.algolist.net/Data_structures/Binary_search_tree/Removal
     */

    public boolean remove(T key) {
        
        if(root == null) {
            return false;
        } else {

            if(key.compareTo(root.getKey()) == 0) {

                Node<T> auxRoot = new Node<>(null);
                auxRoot.setLeft(root);

                boolean result = root.remove(key, auxRoot);
                root = auxRoot.getLeft();

                return result;

            } else {
                return root.remove(key, null);
            }
        }
    }
}
