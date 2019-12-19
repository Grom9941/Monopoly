package com.example.monopoly.Game;

import android.view.View;
import android.widget.TextView;

import com.example.monopoly.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyHolder extends RecyclerView.ViewHolder {
    TextView textView;

    public MyHolder(@NonNull View itemView) {
        super(itemView);
        this.textView = itemView.findViewById(R.id.title_text_view);
    }
}
