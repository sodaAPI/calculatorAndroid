package com.cal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class MainActivity extends AppCompatActivity {

    TextView inputCal;
    TextView resultCal;

    String inputs = "";
    String formula = "";
    String tempFormula = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initTextViews();
    }

    private void initTextViews() {
        inputCal = (TextView)findViewById(R.id.inputTextView);
        resultCal = (TextView) findViewById(R.id.resultTextView);
    }

    private void setInputs(String givenValue){
        inputs = inputs + givenValue;
        inputCal.setText(inputs);
    }

    public void samadenganOnClick(View view) {

        Double result = null;
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("rhino");
        checkForPowerOf();

        try {
            result = (double)engine.eval(formula);
        } catch (ScriptException e)
        {
            Toast.makeText(this, "Invalid Input", Toast.LENGTH_SHORT).show();
        }

        if(result != null)
            resultCal.setText(String.valueOf(result.doubleValue()));

    }

    private void checkForPowerOf()
    {
        ArrayList<Integer> indexOfPowers = new ArrayList<>();
        for(int i = 0; i < inputs.length(); i++)
        {
            if (inputs.charAt(i) == '^')
                indexOfPowers.add(i);
        }

        formula = inputs;
        tempFormula = inputs;
        for(Integer index: indexOfPowers)
        {
            changeFormula(index);
        }
        formula = tempFormula;
    }

    private void changeFormula(Integer index)
    {
        String numberLeft = "";
        String numberRight = "";

        for(int i = index + 1; i< inputs.length(); i++)
        {
            if(isNumeric(inputs.charAt(i)))
                numberRight = numberRight + inputs.charAt(i);
            else
                break;
        }

        for(int i = index - 1; i >= 0; i--)
        {
            if(isNumeric(inputs.charAt(i)))
                numberLeft = numberLeft + inputs.charAt(i);
            else
                break;
        }

        String original = numberLeft + "^" + numberRight;
        String changed = "Math.pow("+numberLeft+","+numberRight+")";
        tempFormula = tempFormula.replace(original,changed);
    }

    private boolean isNumeric(char c)
    {
        if((c <= '9' && c >= '0') || c == '.')
            return true;
        return false;
    }

    public void clearOnclick(View view) {
        inputCal.setText(" ");
        inputs = " ";
        resultCal.setText(" ");
        leftBracket = true;
    }

    boolean leftBracket = true;

    public void bracketOnClick(View view) {
        if(leftBracket)
        {
            setInputs("(");
            leftBracket = false;
        }
        else
        {
            setInputs(")");
            leftBracket = true;
        }
    }

    public void pangkatOnClick(View view) {
        setInputs("^");
    }

    public void bagiOnClick(View view) {
        setInputs("/");
    }

    public void tujuhOnClick(View view) {
        setInputs("7");
    }

    public void delapanOnClick(View view) {
        setInputs("8");
    }

    public void sembilanOnCLick(View view) {
        setInputs("9");
    }

    public void kaliOnClick(View view) {
        setInputs("*");
    }

    public void empatOnClick(View view) {
        setInputs("4");
    }

    public void limaOnClick(View view) {
        setInputs("5");
    }

    public void enamOnClick(View view) {
        setInputs("6");
    }

    public void tambahOnClick(View view) {
        setInputs("+");
    }

    public void satuOnClick(View view) {
        setInputs("1");
    }

    public void duaOnClick(View view) {
        setInputs("2");
    }

    public void tigaOnClick(View view) {
        setInputs("3");
    }

    public void minusOnClick(View view) {
        setInputs("-");
    }

    public void nolOnClick(View view) {
        setInputs("0");
    }

    public void titikOnClick(View view) {
        setInputs(".");
    }


}