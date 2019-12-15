package com.example.monopoly;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class PlayerColor extends AppCompatActivity implements View.OnClickListener {

    int[] colorlayout2 = {Color.YELLOW,Color.GREEN,Color.RED,Color.MAGENTA,Color.BLUE,
            Color.CYAN};

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player_color);
        dinamicCreation();
        //GameLogic gameLogic = new GameLogic();
        //gameLogic.startGame();

    }

    private void dinamicCreation() {
        int[] layout = {R.id.linearLayoutColor1, R.id.linearLayoutColor2};
        int lengthStart = 0;
        int lengthEnd = colorlayout2.length / 2;
        for (int value : layout) {
            LinearLayout linearCurrent = findViewById(value);

            for (int i = lengthStart; i < lengthEnd; i++) {
                Button button = new Button(this);
                button.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1.0f));
                button.setBackgroundColor(colorlayout2[i]);
                button.setTextColor(Color.BLACK);
                button.setText(R.string.my);
                linearCurrent.addView(button);

                button.setOnClickListener(this);
            }
            lengthStart = colorlayout2.length / 2;
            lengthEnd = colorlayout2.length;
        }
    }

    @Override
    public void onClick(View view) {
        try {

            Intent intent = new Intent(PlayerColor.this, BoardCreating.class);
            //colorlayout2[view.getId()]
            intent.putExtra("color",((ColorDrawable)view.getBackground()).getColor());
            startActivity(intent);
            finish();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
