package com.andreat.data_structures;

public class BinarySearchTree <T extends Comparable<T>> {

    class TreeNode<T extends Comparable<T>>{
        T data;
        TreeNode<T> left;
        TreeNode<T> right;

        public TreeNode(T data){
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    private TreeNode<T> root;

    public BinarySearchTree() {
        this.root = null;
    }

    public void insert(T data) {
        root = insertRec(root, data);
    }

    private TreeNode<T> insertRec(TreeNode<T> root, T data) {
        if(root == null) {
            root = new TreeNode<>(data);
            return root;
        }

        if(data.compareTo(root.data) < 0) {
            root.left = insertRec(root.left, data);
        } else if(data.compareTo(root.data) > 0) {
            root.right = insertRec(root.right, data);
        }

        return root;
    }

    public boolean search(T data) {
        return searchRec(root, data);
    }

    private boolean searchRec(TreeNode<T> root, T data) {
        if(root == null) {
            return false;
        }

        if(root.data.equals(data)) {
            return true;
        }

        if(data.compareTo(root.data) < 0) {
            return searchRec(root.left, data);
        } else {
            return searchRec(root.right, data);
        }
    }

    public void delete(T data) {
        root = deleteRec(root, data);
    }

    private TreeNode<T> deleteRec(TreeNode<T> root, T data) {
        if(root == null) {
            return null;
        }

        if(data.compareTo(root.data) < 0) {
            root.left = deleteRec(root.left, data);
        } else if(data.compareTo(root.data) > 0) {
            root.right = deleteRec(root.right, data);
        } else {
            if(root.left == null) {
                return root.right;
            } else if(root.right == null) {
                return root.left;
            }

            root.data = minValue(root.right);

            root.right = deleteRec(root.right, root.data);
        }

        return root;
    }

    private T minValue(TreeNode<T> root) {
        T minValue = root.data;
        while(root.left != null) {
            minValue = root.left.data;
            root = root.left;
        }
        return minValue;
    }

    public void inorderTraversal(){
        inorderRec(root);
        System.out.println();
    }

    private void inorderRec(TreeNode<T> root) {
        if(root != null) {
            inorderRec(root.left);
            System.out.print(root.data + " ");
            inorderRec(root.right);
        }
    }
}