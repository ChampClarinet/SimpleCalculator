package com.example.clarinetmaster.simplecalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.clarinetmaster.simplecalculator.Tools.Expression;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView prevExpressionView, expressionView, answerView;
    private Button btnZero, btnOne, btnTwo, btnThree, btnFour, btnFive, btnSix, btnSeven, btnEight, btnNine;
    private Button btnPlus, btnMinus, btnMultiply, btnDivide, btnEqual, btnClear, btnDot, btnOpenedBracket, btnClosedBracket, btnPower;

    private String prevExpression;
    private String prevAnswer;
    private String displayedExpression;
    private boolean isExpressionEmpty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindWidgets();
        setOnClick();
        isExpressionEmpty = true;
        initialize();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.buttonClear) {
            initialize();
        } else if (id == R.id.buttonEqual) {
            if (!isExpressionEmpty) {
                Expression expression = new Expression(displayedExpression, this);
                String answer = expression.solve();
                answerView.setText(answer);
                prevExpression = displayedExpression;
                prevAnswer = answer;
            } else {
                Toast.makeText(this, R.string.emptyExpression, Toast.LENGTH_SHORT).show();
            }
        } else {
            Button b = (Button) findViewById(id);
            displayedExpression += b.getText();
            expressionView.setText(displayedExpression);
            isExpressionEmpty = false;
        }
    }

    private String getPrevExpressionText() {
        if (prevAnswer == null || prevExpression.equals("")) return "";
        String s = prevExpression + " = " + prevAnswer;
        prevAnswer = null;
        return s;
    }

    private void initialize() {
        if(isExpressionEmpty){
            prevExpression = "";
            prevAnswer = "";
        }
        displayedExpression = "";
        isExpressionEmpty = true;
        expressionView.setText("");
        answerView.setText(R.string.zero);
        prevExpressionView.setText(getPrevExpressionText());
    }

    private void setOnClick() {
        btnZero.setOnClickListener(this);
        btnOne.setOnClickListener(this);
        btnTwo.setOnClickListener(this);
        btnThree.setOnClickListener(this);
        btnFour.setOnClickListener(this);
        btnFive.setOnClickListener(this);
        btnSix.setOnClickListener(this);
        btnSeven.setOnClickListener(this);
        btnEight.setOnClickListener(this);
        btnNine.setOnClickListener(this);
        btnPlus.setOnClickListener(this);
        btnMinus.setOnClickListener(this);
        btnMultiply.setOnClickListener(this);
        btnDivide.setOnClickListener(this);
        btnPower.setOnClickListener(this);
        btnEqual.setOnClickListener(this);
        btnClear.setOnClickListener(this);
        btnDot.setOnClickListener(this);
        btnOpenedBracket.setOnClickListener(this);
        btnClosedBracket.setOnClickListener(this);
    }

    private void bindWidgets() {
        prevExpressionView = (TextView) findViewById(R.id.prevExpressionView);
        expressionView = (TextView) findViewById(R.id.expressionView);
        answerView = (TextView) findViewById(R.id.answerView);
        btnZero = (Button) findViewById(R.id.buttonZero);
        btnOne = (Button) findViewById(R.id.buttonOne);
        btnTwo = (Button) findViewById(R.id.buttonTwo);
        btnThree = (Button) findViewById(R.id.buttonThree);
        btnFour = (Button) findViewById(R.id.buttonFour);
        btnFive = (Button) findViewById(R.id.buttonFive);
        btnSix = (Button) findViewById(R.id.buttonSix);
        btnSeven = (Button) findViewById(R.id.buttonSeven);
        btnEight = (Button) findViewById(R.id.buttonEight);
        btnNine = (Button) findViewById(R.id.buttonNine);
        btnPlus = (Button) findViewById(R.id.buttonPlus);
        btnMinus = (Button) findViewById(R.id.buttonMinus);
        btnMultiply = (Button) findViewById(R.id.buttonMultiply);
        btnDivide = (Button) findViewById(R.id.buttonDivide);
        btnPower = (Button) findViewById(R.id.buttonPower);
        btnEqual = (Button) findViewById(R.id.buttonEqual);
        btnClear = (Button) findViewById(R.id.buttonClear);
        btnDot = (Button) findViewById(R.id.buttonDot);
        btnOpenedBracket = (Button) findViewById(R.id.buttonOpenedBracket);
        btnClosedBracket = (Button) findViewById(R.id.buttonClosedBracket);
    }

}
