package com.example.clarinetmaster.simplecalculator.Tools;

import android.content.Context;
import com.example.clarinetmaster.simplecalculator.R;

public class Definer {

    private static Definer instance;
    private Context context;

    public Definer(Context context) {
        this.context = context;
    }

    public static Definer getInstance(Context context) {
        if (instance == null) instance = new Definer(context);
        return instance;
    }

    public boolean isOperand(String s) {
        return !isOperator(s) && !isBracket(s);
    }

    public boolean isOperator(String s) {
        return isPlus(s)
                || isMinus(s)
                || isMultiply(s)
                || isDivide(s)
                || isPower(s);
    }

    public boolean isPlus(String s) {
        return s.equals(context.getString(R.string.plus));
    }

    public boolean isMinus(String s) {
        return s.equals(context.getString(R.string.minus));
    }

    public boolean isMultiply(String s) {
        return s.equals(context.getString(R.string.multiply));
    }

    public boolean isDivide(String s) {
        return s.equals(context.getString(R.string.divide));
    }

    public boolean isPower(String s) {
        return s.equals(context.getString(R.string.power));
    }

    public boolean isBracket(String s) {
        return isOpeningBracket(s) || isClosingBracket(s);
    }

    public boolean isOpeningBracket(String s) {
        return s.equals(context.getString(R.string.openedBracket));
    }

    public boolean isClosingBracket(String s) {
        return s.equals(context.getString(R.string.closedBracket));
    }

    public boolean isDot(String s){
        return s.equals(context.getString(R.string.dot));
    }

}
