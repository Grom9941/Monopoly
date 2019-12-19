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

    LineGraphSeries<DataPoint> series1, series2, series3, series4, series5, series6;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {

        if (AppCompatDelegate.getDefaultNightMode()==AppCompatDelegate.MODE_NIGHT_YES){
            setTheme(R.style.darktheme);
        } else {
            setTheme(R.style.AppTheme);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.end_game);

        List<User> users = BoardCreating.myAppDatabase.myDataObject().getUsers();

        GraphView graph = findViewById(R.id.graph);
        series1 = new LineGraphSeries<>();
        series2 = new LineGraphSeries<>();
        series3 = new LineGraphSeries<>();
        series4 = new LineGraphSeries<>();
        series5 = new LineGraphSeries<>();
        series6 = new LineGraphSeries<>();

        List<LineGraphSeries<DataPoint> > series = new ArrayList<>();
        series.add(series1);
        series.add(series2);
        series.add(series3);
        series.add(series4);
        series.add(series5);
        series.add(series6);

        List<List<Integer>> lists = new ArrayList<>();

        StringBuilder info = new StringBuilder();
        int max = 0;
        for (User usr : users){
            if (usr.getId() > startIdThisGame) {
                ArrayList<Integer> money = usr.getEpochMoney();
                lists.add(money);
                if (max < money.size()) {
                    max = money.size();
                }
            }
        }

        for (int x=0; x < max; x++){
            for (int j=0; j<playersCount;j++) {

                if (lists.get(j).size() > x){
                    series.get(j).appendData(new DataPoint(x, lists.get(j).get(x)), true, 10);
                } else {
                    series.get(j).appendData(new DataPoint(x, 0), true, 10);
                }

            }
        }

        for (int j=0; j<playersCount;j++) {
            series.get(j).setColor(colorlayout2[j]);
            graph.addSeries(series.get(j));
        }
    }
}
