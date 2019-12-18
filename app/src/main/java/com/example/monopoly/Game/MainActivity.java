package com.example.monopoly.Game;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.example.monopoly.R;

public class MainActivity extends AppCompatActivity {
    Switch aSwitch;
    boolean lightMode = true;

    @RequiresApi(api = Build.VERSION_CODES.O)
    protected void onCreate(Bundle savedInstanceState) {
        if (AppCompatDelegate.getDefaultNightMode()==AppCompatDelegate.MODE_NIGHT_YES){
            setTheme(R.style.darktheme);
            lightMode = false;
        } else {
            setTheme(R.style.AppTheme);
            lightMode = true;
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_screen);
        aSwitch = findViewById(R.id.switch1);


        if (AppCompatDelegate.getDefaultNightMode()==AppCompatDelegate.MODE_NIGHT_YES){
            aSwitch.setChecked(true);
        }
        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    restartApp();
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    restartApp();
                }
            }
        });
    }
    public void restartApp(){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void clickStatistics(View view) {

        try {

            Intent intent = new Intent(MainActivity.this, Statistics.class);
            startActivity(intent);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clickPlay(View view) {

        try {

            Intent intent = new Intent(MainActivity.this, PlayerColor.class);
            startActivity(intent);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
