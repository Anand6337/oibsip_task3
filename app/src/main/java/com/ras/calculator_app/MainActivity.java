package com.ras.calculator_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView result_screen,solution_screen;
    MaterialButton button_c,
            button_openBracket,
            button_closeBracket,button_division,
            button_7,button_8,button_9,button_multiplication,
            button_4,button_5,button_6,button_addition,
            button_1,button_2,button_3,button_subtraction,
            button_allClear,button_0,button_point,button_answer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        result_screen = findViewById(R.id.result_screen);
        solution_screen = findViewById(R.id.solution_screen);

        assignId(button_c, R.id.button_c);
        assignId(button_openBracket, R.id.button_openbracket);
        assignId(button_closeBracket, R.id.button_closeBracket);
        assignId(button_division, R.id.button_divide);
        assignId(button_multiplication, R.id.button_multiplication);
        assignId(button_addition, R.id.button_addition);
        assignId(button_subtraction, R.id.button_subtraction);
        assignId(button_answer, R.id.button_answer);
        assignId(button_allClear, R.id.button_allClear);
        assignId(button_point, R.id.button_point);
        assignId(button_point, R.id.button_point);
        assignId(button_0, R.id.button_0);
        assignId(button_1, R.id.button_1);
        assignId(button_2, R.id.button_2);
        assignId(button_3, R.id.button_3);
        assignId(button_4, R.id.button_4);
        assignId(button_5, R.id.button_5);
        assignId(button_6, R.id.button_6);
        assignId(button_7, R.id.button_7);
        assignId(button_8, R.id.button_8);
        assignId(button_9, R.id.button_9);

    }
    void assignId(MaterialButton btn,int id)
    {
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
    MaterialButton button = (MaterialButton) view;
    String buttonText = button.getText().toString();
    String datatoCalcualte = solution_screen.getText().toString();

    if (buttonText.equals("AC"))
    {
        solution_screen.setText("");
        result_screen.setText("0");
        return;
    }
    if(buttonText.equals("="))
    {
        solution_screen.setText(result_screen.getText());
        return;
    }
    if (buttonText.equals("C"))
    {
        datatoCalcualte = datatoCalcualte.substring(0,datatoCalcualte.length()-1);

    }
    else
    {
        datatoCalcualte = datatoCalcualte+buttonText;
    }

    solution_screen.setText(datatoCalcualte);
    String finalresult = getResult(datatoCalcualte);

    if (!finalresult.equals("Error"))
    {
        result_screen.setText(finalresult);
    }

    }

    String getResult(String data)
    {
       try {
           Context context = Context.enter();
           context.setOptimizationLevel(-1);
           Scriptable scriptable = context.initSafeStandardObjects();
           String finalResult = context.evaluateString(scriptable,data,
                   "JavaScript", 1, null).toString();

           if (finalResult.endsWith(".0"))
           {
               finalResult = finalResult.replace(".0", "");
           }
           return finalResult;
       }
       catch (Exception e)
       {
           return "Error";
       }
    }
}