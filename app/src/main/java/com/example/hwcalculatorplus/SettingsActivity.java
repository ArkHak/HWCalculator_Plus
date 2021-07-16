package com.example.hwcalculatorplus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity implements ConstansTheme {
    private int codeStyleNew;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(getAppTheme(R.style.MyLightTheme));
        setTitle("Settings");
        setContentView(R.layout.activity_settings);

        getCodeStyle();
        initView();

    }

    private void installCheckRB() {
        RadioButton lightRB = findViewById(R.id.radiobutton_light_theme);
        RadioButton darkRB = findViewById(R.id.radiobutton_dark_theme);
        switch (codeStyleNew) {
            case CODE_LIGHT_THEME:
                lightRB.setChecked(true);
                break;
            case CODE_DARK_THEME:
                darkRB.setChecked(true);
                break;
            default:
                break;
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

    private void getCodeStyle() {
        SharedPreferences sharedPref = getSharedPreferences(NameSharedPreference,
                MODE_PRIVATE);
        codeStyleNew = sharedPref.getInt(APP_THEME_CODE, CODE_DEFAULT_THEME);
    }


    private void initView() {
        initRadioGroup();
        installCheckRB();

        Button buttonApply = findViewById(R.id.apply_settings);
        buttonApply.setOnClickListener(v -> {
            SharedPreferences sharedPref = getSharedPreferences(NameSharedPreference,
                    MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putInt(APP_THEME_CODE, codeStyleNew);
            editor.apply();
            recreate();
        });
    }

    private void initRadioGroup() {
        RadioGroup radioGroup = findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.radiobutton_light_theme:
                    codeStyleNew = CODE_LIGHT_THEME;
                    break;
                case R.id.radiobutton_dark_theme:
                    codeStyleNew = CODE_DARK_THEME;
                    break;
                default:
                    break;
            }
        });
    }


}