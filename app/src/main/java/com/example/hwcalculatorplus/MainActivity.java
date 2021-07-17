package com.example.hwcalculatorplus;

import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.VoiceInteractor;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements ConstansTheme {

    private TextView textCounterA;
    private TextView textCounterAction;
    private EditText textCounterB;
    private Variables variables;
    private TextCounters textCounters;
    private final int[] numberButtonIds = new int[]{R.id.button_0, R.id.button_1, R.id.button_2,
            R.id.button_3, R.id.button_4, R.id.button_5, R.id.button_6, R.id.button_7,
            R.id.button_8, R.id.button_9};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(getAppTheme(R.style.MyLightTheme));
        setContentView(R.layout.activity_main);

        variables = new Variables();
        textCounters = new TextCounters();

        initView();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.settings:
                Intent runSettings = new Intent(this, SettingsActivity.class);
                startActivityForResult(runSettings, REQUEST_CODE_SETTING_ACTIVITY);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode != REQUEST_CODE_SETTING_ACTIVITY) {
            super.onActivityResult(requestCode, resultCode, data);
            return;
        }
        if (resultCode == RESULT_OK){
            recreate();
        }
    }



    private int getAppTheme(int codeStyle) {
        return codeStyleToStyleId(getCodeStyle(codeStyle));
    }

    private int getCodeStyle(int codeStyle) {
        SharedPreferences sharedPref = getSharedPreferences(NameSharedPreference,
                MODE_PRIVATE);
        return sharedPref.getInt(APP_THEME_CODE, codeStyle);
    }

    private int codeStyleToStyleId(int codeStyle) {
        switch (codeStyle) {
            case CODE_LIGHT_THEME:
                return R.style.MyLightTheme;
            case CODE_DARK_THEME:
                return R.style.MyDarkTheme;
            default:
                return R.style.MyLightTheme;
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        textCounters.setTextCounterA(textCounterA.getText().toString());
        textCounters.setTextCounterAction(textCounterAction.getText().toString());
        textCounters.setTextCounterB(textCounterB.getText().toString());
        outState.putParcelable(PARAM_COUNTER, textCounters);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        textCounters = savedInstanceState.getParcelable(PARAM_COUNTER);
        if (textCounters == null) {
            textCounters = new TextCounters();
        }
        updateCounters();
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
        Button buttonPoint = findViewById(R.id.button_point);
        Button buttonPlus = findViewById(R.id.button_plus);
        Button buttonMinus = findViewById(R.id.button_minus);
        Button buttonMultiply = findViewById(R.id.button_multiply);
        Button buttonDivision = findViewById(R.id.button_division);
        Button buttonClear = findViewById(R.id.button_clear);
        Button buttonBackStep = findViewById(R.id.button_back_step);
        Button buttonCalculate = findViewById(R.id.button_calculate);
        Button buttonChangeSign = findViewById(R.id.button_change_sign);

        setNumberButtonListeners();
        buttonPoint.setOnClickListener(v -> enterInInputField(СalculatorАctions.POINT.toString()));
        buttonClear.setOnClickListener(v -> clearAll());
        buttonBackStep.setOnClickListener(v -> backStep());
        buttonPlus.setOnClickListener(this::actionBtnClick);
        buttonMinus.setOnClickListener(this::actionBtnClick);
        buttonMultiply.setOnClickListener(this::actionBtnClick);
        buttonDivision.setOnClickListener(this::actionBtnClick);
        buttonCalculate.setOnClickListener(v -> calculate());
        buttonChangeSign.setOnClickListener(v -> changeSign());
    }

    private void actionBtnClick(View v) {
        textCounterA.setText(textCounterB.getText().toString());
        variables.setAction(searchAction(v.getId()));
        textCounterAction.setText(variables.getAction());
        textCounterB.getText().clear();
    }

    private void calculate() {
        variables.setVariableA(textCounterA.getText().toString());
        variables.setAction(textCounterAction.getText().toString());
        variables.setVariableB(textCounterB.getText().toString());
        textCounterA.setText(null);
        variables.calculate();
        textCounterAction.setText(СalculatorАctions.CALCULATE.toString());
        textCounterB.setText(String.format(Locale.getDefault(), "%s",
                convert(variables.getResult())));
    }

    private void changeSign() {
        variables.setVariableB(textCounterB.getText().toString());
        variables.changeSignB();
        textCounterB.setText(String.format(Locale.getDefault(), "%s",
                convert(variables.getVariableB())));
    }

    private void setNumberButtonListeners() {
        for (int numberButtonId : numberButtonIds) {
            findViewById(numberButtonId).setOnClickListener(v -> {
                Button btn = (Button) v;
                enterInInputField(btn.getText().toString());
            });
        }
    }

    private void enterInInputField(String counter) {
        String str = textCounterB.getText().toString();
        str = correctInputValue(counter, str);
        textCounterB.setText(String.format(Locale.getDefault(), "%s", str));
    }

    private String correctInputValue(String counter, String str) {
        if (counter.equals(СalculatorАctions.POINT.toString())) {
            str = pointCheck(counter, str);
        } else if (counter.equals(СalculatorАctions.NUM0.toString())) {
            str = zeroCheck(counter, str);
        } else {
            if (str.equals(СalculatorАctions.NUM0.toString())) {
                str = counter;
            } else {
                str += counter;
            }
        }
        return str;
    }

    private String pointCheck(String counter, String str) {
        if (!str.contains(counter)) {
            str += counter;
        }
        return str;
    }

    private String zeroCheck(String counter, String str) {
        if (textCounterB.getText().toString().isEmpty() || str.equals(counter)) {
            return counter;
        }
        return str + counter;
    }

    private void clearAll() {
        variables.clear();
        textCounterA.setText(null);
        textCounterB.getText().clear();
        textCounterAction.setText(null);
    }

    private void backStep() {
        String MINUS_CHECK = "-";
        int DOUBLE = 2;
        if ((textCounterB.getText().length() == DOUBLE) &&
                (textCounterB.getText().toString().contains(MINUS_CHECK))) {
            textCounterB.getText().clear();
        }
        int ZERO = 0;
        if (!(textCounterB.getText().length() == ZERO)) {
            textCounterB.getText().delete(textCounterB.getText().length() - 1,
                    textCounterB.getText().length());
        }
    }

    private String convert(double num) {
        if (num == (int) num) return (Integer.toString((int) num));
        else return Double.toString(num);
    }

    @SuppressLint("NonConstantResourceId")
    private String searchAction(int id) {
        switch (id) {
            case R.id.button_division:
                return СalculatorАctions.DIVISION.toString();
            case R.id.button_multiply:
                return СalculatorАctions.MULTIPLY.toString();
            case R.id.button_minus:
                return СalculatorАctions.MINUS.toString();
            case R.id.button_plus:
                return СalculatorАctions.PLUS.toString();
        }
        return null;
    }

    private void updateCounters() {
        textCounterA.setText(textCounters.getTextCounterA());
        textCounterAction.setText(textCounters.getTextCounterAction());
        textCounterB.setText(textCounters.getTextCounterB());
    }
}