package com.example.clarinetmaster.simplecalculator.Tools.DataStructures;

public class Tree {

    private final String value;
    private Tree left;
    private Tree right;

    public Tree(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public Tree getLeft() {
        return left;
    }

    public Tree getRight() {
        return right;
    }

    public void setLeft(Tree left) {
        this.left = left;
    }

    public void setRight(Tree right) {
        this.right = right;
    }

}
