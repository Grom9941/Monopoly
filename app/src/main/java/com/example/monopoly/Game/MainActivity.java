package com.example.monopoly.Game;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.example.monopoly.R;

public class MainActivity extends AppCompatActivity {
    @RequiresApi(api = Build.VERSION_CODES.O)
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_screen);

    }

    public void clickSeveralPhone(View view) {

        try {

            Intent intent = new Intent(MainActivity.this, Connection.class);
            startActivity(intent);
            finish();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clickOnePhone(View view) {

        try {

            Intent intent = new Intent(MainActivity.this, PlayerColor.class);
            startActivity(intent);
            finish();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
