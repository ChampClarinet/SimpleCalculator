package com.example.clarinetmaster.simplecalculator.Tools.DataStructures;

public class Stack {

    private final String STACK_OVERFLOW = "STACK OVERFLOW";
    private Node stackTop;

    public Stack() {
        initialize();
    }

    public void initialize() {
        stackTop = null;
    }

    public boolean isEmpty() {
        return stackTop == null;
    }

    public void push(Object newValue) {
        Node newNode = new Node(newValue);
        newNode.setNext(stackTop);
        stackTop = newNode;
    }

    public Object pop() throws Exception {
        Object s = peek();
        stackTop = stackTop.getNext();
        return s;
    }

    public Object peek() throws Exception {
        if (isEmpty()) throw new Exception(STACK_OVERFLOW);
        return stackTop.getValue();
    }

}
