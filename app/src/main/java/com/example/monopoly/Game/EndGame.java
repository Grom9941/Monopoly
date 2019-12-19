package com.example.monopoly.Game;

import android.os.Bundle;
import android.widget.TextView;

import com.example.monopoly.Database.User;
import com.example.monopoly.R;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import static com.example.monopoly.Game.BoardCreating.playersCount;
import static com.example.monopoly.Game.BoardCreating.startIdThisGame;
import static com.example.monopoly.Game.PlayerColor.colorlayout2;

public class EndGame extends AppCompatActivity {

    public static List<User> users;
    public static List<List<Integer>> lists;
    public static int max;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {

        if (AppCompatDelegate.getDefaultNightMode()==AppCompatDelegate.MODE_NIGHT_YES){
            setTheme(R.style.darktheme);
        } else {
            setTheme(R.style.AppTheme);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.end_game);

        TextView string = findViewById(R.id.text_info);
        string.setText(findNecesary());
    }

    public StringBuilder findNecesary (){
        users = BoardCreating.myAppDatabase.myDataObject().getUsers();
        lists = new ArrayList<>();
        StringBuilder str = new StringBuilder();

        max = 0;
        for (User usr : users){
            if (usr.getId() > startIdThisGame) {
                ArrayList<Integer> money = usr.getEpochMoney();
                lists.add(money);
                if (max < money.size()) {
                    max = money.size();
                }
                str.append("\nnumber: ").append(usr.getNumberUser()).append(" finish: ").append(usr.getFinishNumber()).append(" color: ").append(usr.getColorUser());
            }
        }
        return str;
    }

}
