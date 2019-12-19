package com.example.monopoly.Game;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.monopoly.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class PlayerColor extends AppCompatActivity implements View.OnClickListener {

    public static int[] colorlayout2 = {Color.YELLOW,Color.GREEN,Color.RED,Color.MAGENTA,Color.BLUE,
            Color.CYAN};
    private int numberPlayer;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {

        if (AppCompatDelegate.getDefaultNightMode()==AppCompatDelegate.MODE_NIGHT_YES){
            setTheme(R.style.darktheme);
        } else {
            setTheme(R.style.AppTheme);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.player_color);
        Intent intent = getIntent();
        numberPlayer = intent.getIntExtra("count", 6);
        dinamicCreation();
    }

    private void dinamicCreation() {

         LinearLayout linearCurrent = findViewById(R.id.linearLayoutColor1);

        for (int i = 0; i < numberPlayer; i++) {
            Button button = new Button(this);
            button.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1.0f));
            button.setBackgroundColor(colorlayout2[i]);
            button.setTextColor(Color.BLACK);
            button.setText(R.string.my);
            linearCurrent.addView(button);
            button.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(PlayerColor.this, BoardCreating.class);
        intent.putExtra("color", ((ColorDrawable) view.getBackground()).getColor());
        intent.putExtra("count", numberPlayer);
        startActivity(intent);
        finish();
    }
}
