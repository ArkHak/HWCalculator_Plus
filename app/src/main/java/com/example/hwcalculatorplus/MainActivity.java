package com.example.hwcalculatorplus;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private TextView textCounterA;
    private TextView textCounterAction;
    private EditText textCounterB;
    private СalculatorАctions calculatorActions;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        initViewFields();
        initViewBts();
    }

    private void initViewFields() {
        textCounterA = findViewById(R.id.view_variable_a);
        textCounterB = findViewById(R.id.view_variable_b);
        textCounterAction = findViewById(R.id.view_action);
    }

    private void initViewBts() {
        Button button0 = findViewById(R.id.button_0);
        Button button1 = findViewById(R.id.button_1);
        Button button2 = findViewById(R.id.button_2);
        Button button3 = findViewById(R.id.button_3);
        Button button4 = findViewById(R.id.button_4);
        Button button5 = findViewById(R.id.button_5);
        Button button6 = findViewById(R.id.button_6);
        Button button7 = findViewById(R.id.button_7);
        Button button8 = findViewById(R.id.button_8);
        Button button9 = findViewById(R.id.button_9);
        Button buttonPoint = findViewById(R.id.button_point);


        button0.setOnClickListener(v -> enterInInputField(СalculatorАctions.NUM0.toString()));
        button1.setOnClickListener(v -> enterInInputField(СalculatorАctions.NUM1.toString()));
        button2.setOnClickListener(v -> enterInInputField(СalculatorАctions.NUM2.toString()));
        button3.setOnClickListener(v -> enterInInputField(СalculatorАctions.NUM3.toString()));
        button4.setOnClickListener(v -> enterInInputField(СalculatorАctions.NUM4.toString()));
        button5.setOnClickListener(v -> enterInInputField(СalculatorАctions.NUM5.toString()));
        button6.setOnClickListener(v -> enterInInputField(СalculatorАctions.NUM6.toString()));
        button7.setOnClickListener(v -> enterInInputField(СalculatorАctions.NUM7.toString()));
        button8.setOnClickListener(v -> enterInInputField(СalculatorАctions.NUM8.toString()));
        button9.setOnClickListener(v -> enterInInputField(СalculatorАctions.NUM9.toString()));
        buttonPoint.setOnClickListener(v -> enterInInputField(СalculatorАctions.POINT.toString()));
    }

    private void enterInInputField(String counter) {
        String str = textCounterB.getText().toString();
        if (counter == СalculatorАctions.POINT.toString()) {
            str = pointCheck(counter, str);
        } else {
            str += counter;
        }
        textCounterB.setText(String.format(Locale.getDefault(), "%s",
                str));
    }

    private String pointCheck(String counter, String str) {
        if (!str.contains(counter)) {
            str += counter;
        }
        return str;
    }
}