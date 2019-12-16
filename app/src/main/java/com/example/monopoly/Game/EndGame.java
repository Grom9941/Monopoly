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

public class EndGame extends AppCompatActivity {

    LineGraphSeries<DataPoint> series1, series2, series3, series4, series5, series6;

    int maxPlayer=6;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.end_game);

        TextView textViewData = findViewById(R.id.data);
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
            int number = usr.getNumberUser();
            String color = usr.getColorUser();
            ArrayList<Integer> money = usr.getEpochMoney();
            lists.add(money);
            if (max < money.size()){
                max = money.size();
            }
            int finish = usr.getFinishNumber();

            info.append("\n\nnumber: ").append(number).append("\n color: ").append(color).append("\nfinish: ").append(finish);
        }
        textViewData.setText(info.toString());

        for (int x=0; x < max; x++){
            for (int j=0; j<maxPlayer;j++) {
                if (lists.get(j).size() > x){
                    series.get(j).appendData(new DataPoint(x, lists.get(j).get(x)), true, 10);
                } else {
                    series.get(j).appendData(new DataPoint(x, 0), true, 10);
                }
            }
        }

        PlayerColor playerColor = new PlayerColor();
        for (int j=0; j<maxPlayer;j++) {
            series.get(j).setColor(playerColor.colorlayout2[j]);
            graph.addSeries(series.get(j));
        }

    }
}
