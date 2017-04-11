package com.example.clarinetmaster.simplecalculator.Tools;

import android.content.Context;

import com.example.clarinetmaster.simplecalculator.Tools.DataStructures.Stack;
import com.example.clarinetmaster.simplecalculator.Tools.DataStructures.Tree;

import java.util.StringTokenizer;

public class Expression {

    private Definer definer;

    private final ExpressionTree expressionTree;

    public Expression(String infixExpression, Context context) {
        definer = Definer.getInstance(context);
        String postfix = new PostfixGenerator(infixExpression).getPostfix();
        expressionTree = new ExpressionTree(postfix);
    }

    public String solve() {
        double ans = expressionTree.solve();
        if (isInteger(ans)) return Integer.toString((int) ans);
        return Double.toString(ans);
    }

    private boolean isInteger(double d){
        return d == Math.round(d);
    }

    private class PostfixGenerator {

        private final String expression;
        private String spacedInfix;
        private String postfix;

        public PostfixGenerator(String expression) {
            this.expression = expression;
            addSpace();
            try {
                generatePostfix();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private void addSpace() {
            String prev = expression.charAt(0) + "";
            String temp = prev + "";
            for (int i = 1; i < expression.length(); ++i) {
                String current = expression.charAt(i) + "";
                if (definer.isBracket(prev) || definer.isOperator(prev)) {
                    temp += " ";
                }
                if (definer.isOperand(prev) && !definer.isOperand(current)) {
                    temp += " ";
                }
                temp += current;
                prev = current;
            }
            spacedInfix = temp;
        }

        private void generatePostfix() throws Exception {
            StringTokenizer input = new StringTokenizer(spacedInfix);
            String output = "";
            Stack stack = new Stack();
            while (input.hasMoreElements()) {
                String next = input.nextToken();
                if (definer.isOperand(next)) {
                    output += next + " ";
                }
                if (definer.isOpeningBracket(next)) {
                    stack.push(next);
                }
                if (definer.isClosingBracket(next)) {
                    while (!stack.isEmpty() && !definer.isOpeningBracket((String) stack.peek())) {
                        output += stack.pop() + " ";
                    }
                    stack.pop();
                }
                if (definer.isOperator(next)) {
                    if (stack.isEmpty() || definer.isOpeningBracket((String) stack.peek())) {
                        stack.push(next);
                    } else {
                        int priority1;
                        if (next.equals("+") || next.equals("-")) {
                            priority1 = 1;
                        } else {
                            priority1 = 2;
                        }
                        int priority2;
                        String opTop = (String) stack.peek();
                        if (opTop.equals("+") || opTop.equals("-")) {
                            priority2 = 1;
                        } else {
                            priority2 = 2;
                        }
                        while (!stack.isEmpty()
                                && !definer.isOpeningBracket((String) stack.peek())
                                && priority1 <= priority2) {
                            output += stack.pop() + " ";
                        }
                        stack.push(next);
                    }
                }
            }
            while (!stack.isEmpty()) {
                output += stack.pop() + " ";
            }
            postfix = output;
        }

        public String getExpression() {
            return expression;
        }

        public String getSpacedInfix() {
            return spacedInfix;
        }

        public String getPostfix() {
            return postfix;
        }

    }

    private class ExpressionTree {

        private Stack stack;

        public ExpressionTree(String postfix) {
            stack = new Stack();
            createTree(postfix);
        }

        private void createTree(String postfix) {
            StringTokenizer st = new StringTokenizer(postfix);
            while (st.hasMoreElements()) {
                try {
                    push(st.nextToken());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        public void push(String s) throws Exception {
            if (definer.isOperand(s)) {
                stack.push(new Tree(s));
            }
            if (definer.isOperator(s)) {
                Tree newTree = new Tree(s);
                Tree t1 = (Tree) stack.pop();
                Tree t2 = (Tree) stack.pop();
                newTree.setLeft(t2);
                newTree.setRight(t1);
                stack.push(newTree);
            }
        }

        public double solve() {
            return solveTree(getTree());
        }

        private Tree getTree() {
            Tree t = null;
            try {
                t = (Tree) stack.peek();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return t;
        }

        private double solveTree(Tree current) {
            if (current != null) {
                if (definer.isOperand(current.getValue())) {
                    return Integer.parseInt(current.getValue());
                }
                double left = solveTree(current.getLeft());
                double right = solveTree(current.getRight());
                if (definer.isPlus(current.getValue())) {
                    return left + right;
                }
                if (definer.isMinus(current.getValue())) {
                    return left - right;
                }
                if (definer.isMultiply(current.getValue())) {
                    return left * right;
                }
                if (definer.isDivide(current.getValue())) {
                    return left / right;
                }
                if (definer.isPower(current.getValue())) {
                    return Math.pow(left, right);
                }
            }
            return 0;
        }

    }

}