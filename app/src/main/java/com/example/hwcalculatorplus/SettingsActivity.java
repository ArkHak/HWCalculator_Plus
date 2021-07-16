package com.example.hwcalculatorplus;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity implements ConstansTheme {
    private RadioButton lightRB;
    private RadioButton darkRB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Settings");
        setContentView(R.layout.activity_settings);

        initView();
    }

    private void initView() {
        RadioGroup radioGroup = findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case -1:
                    Toast.makeText(getApplicationContext(), "Ничего не выбрано",
                            Toast.LENGTH_SHORT).show();
                    break;
                case R.id.radiobutton_light_theme:
                    Toast.makeText(getApplicationContext(), "Первый переключатель",
                            Toast.LENGTH_SHORT).show();
                    break;
                case R.id.radiobutton_dark_theme:
                    Toast.makeText(getApplicationContext(), "Второй переключатель",
                            Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        });

        RadioButton lightRB = findViewById(R.id.radiobutton_light_theme);
        RadioButton darkRB = findViewById(R.id.radiobutton_dark_theme);
        if (CODE_DEFAULT_THEME == 1) {
            darkRB.setChecked(true);
        }
        if (CODE_DEFAULT_THEME == 0) {
            lightRB.setChecked(true);
        }


        Button buttonApply = findViewById(R.id.apply_settings);
    }


}