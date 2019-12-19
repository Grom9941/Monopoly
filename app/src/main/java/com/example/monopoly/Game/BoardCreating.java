package com.example.monopoly.Game;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.monopoly.Database.MyAppDatabase;
import com.example.monopoly.Database.User;
import com.example.monopoly.Dialogs.Dialogs;
import com.example.monopoly.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.room.Room;
import eightbitlab.com.blurview.BlurView;
import eightbitlab.com.blurview.RenderScriptBlur;

import static com.example.monopoly.Game.Conditionals.checkx2;
import static com.example.monopoly.Game.Conditionals.nextPlayer;

public class BoardCreating extends AppCompatActivity {

    public static final Logger logger = Logger.getGlobal();
    public static MyAppDatabase myAppDatabase;
    public static List<List<Integer>> moneyPlayersEpoch = new ArrayList<>() ;
    public static boolean mode = true;

    private final int[] colorlayout1 = {0xFFFFA500,0xFFFFA500,0xFFB0B0B0,0xFFFFA500,0xFFB0B0B0,0xFFFFC0CB,
            0xFFFFC0CB,0xFFB0B0B0,0xFFFFC0CB,0xFFB0B0B0,0xFFADD8E6,0xFFADD8E6,0xFFB0B0B0,0xFFADD8E6,0xFFB0B0B0,
            0xFFB0B0B0,0xFFA0522D,0xFFB0B0B0,0xFFA0522D,0xFFB0B0B0};
    private final int[] colorlayout2 = {0xFFB0B0B0,0xFFFF0000,0xFFB0B0B0,0xFFFF0000,0xFFFF0000,0xFFB0B0B0,
            0xFFFFFF00,0xFFFFFF00,0xFFB0B0B0,0xFFFFFF00,0xFFB0B0B0,0xFF90EE90,0xFF90EE90,0xFFB0B0B0,0xFF90EE90,0xFFB0B0B0,0xFFB0B0B0,
            0xFF0000FF,0xFFB0B0B0,0xFF0000FF};
    private final String[] nameslayout1 = {"NEW YORK AVENUE","TENNESSEE AVENUE","COMMUNITY CHEST","ST.JAMES PLACE",
            "PENNSYLVANIA RAILROAD","VIRGINIA AVENUE","STATES AVENUE","ELECTRIC COMPANY","ST.CHARLES PLACE",
            "IN JAIL","CONNECTICUT AVENUE","VERMONT AVENUE","CHANCE","ORIENTAL AVENUE","READING RAILROAD",
            "INCOME TAX","BALTIC AVENUE","COMMUNITY CHEST","MEDITER-RANEAN AVENUE","COLLECT SALARY"};
    private  final String[] nameslayout2 = {"FREE PARKING","KENTUCKY AVENUE","CHANCE","INDIANA AVENUE","ILLINOIS AVENUE",
            "B & O RAILROAD","ATLANTIC AVENUE","VENTNOR AVENUE","WATER WORKS","MARVIN GARDENS","GO TO JAIL",
            "PACIFIC AVENUE","NORTH CAROLINA AVENUE","COMMUNITY CHEST","PENNSYLVANIA AVENUE","SHORT LINE","CHANCE",
            "PARK PALCE","LUXURY TAX","BOARDWALK"};
    public static final String[] colorPlayer = {"YELLOW","GREEN","RED","MAGENTA","BLUE", "CYAN"};

    public static final int maxId = 40;
    public static int playersCount;
    public static int[] possession = new int[maxId];
    public static int[] xPrice = new int[maxId];
    public static int[] countBuildings = new int[maxId];
    public static boolean[] inPrison = new boolean[maxId];
    public static boolean[] outOfGame = new boolean[maxId];
    public static int[] locationId ;
    public static int[] moneyPlayer;
    public static int numberPlayer = 0;
    public static int endGame = 0;
    public static int numberBefore = 0;
    public static int startIdThisGame = 0;
    public static int[] layout = {R.id.linearLayout1, R.id.linearLayout2};
    public static int[] listIdMoney =  {R.id.textViewboard1,R.id.textViewboard2,R.id.textViewboard3,
            R.id.textViewboard4,R.id.textViewboard5,R.id.textViewboard6};
    public static int[] priceCard = {0,60,0,60,-200,200,100,0,100,120,0,140,150,140,160,200,
            180,0,180,200,0,220,0,220,240,200,260,260,150,280,0,300,300,0,320,200,0,350,-100,400};
    public static int[][] x2 = {{1,3},{6,8,9},{11,13,14},{16,18,19},{21,23,24},{26,27,29},{31,32,34},{37,39}};

    public static Button buttonRand;
    public static int rand;
    public static int rand1;
    public static int rand2;


    @RequiresApi(api = Build.VERSION_CODES.O)
    protected void onCreate(Bundle savedInstanceState) {

        if (AppCompatDelegate.getDefaultNightMode()==AppCompatDelegate.MODE_NIGHT_YES){
            setTheme(R.style.darktheme);
            mode=false;
        } else
            setTheme(R.style.AppTheme);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.board);

        Intent intent = getIntent();
        playersCount = intent.getIntExtra("count", 6);
        locationId = new int[playersCount];
        moneyPlayer = new int[playersCount];

        myAppDatabase = Room.databaseBuilder(this, MyAppDatabase.class, "userdb").allowMainThreadQueries().fallbackToDestructiveMigration().build();
        startIdThisGame = myAppDatabase.myDataObject().getUsers().size();
        buttonRand = findViewById(R.id.buttonroll);

        for (int i = 0; i < playersCount; i++) {
            List<Integer> list = new ArrayList<>();
            moneyPlayersEpoch.add(list);
            locationId[i] = 0;
            inPrison[i] = false;
            outOfGame[i] = false;
            moneyPlayer[i] = 200;
        }

        for (int i = 0; i < maxId; i++) {
            possession[i] = -1;
            xPrice[i] = 1;
            countBuildings[i] = 1;
        }

        dinamicCreation();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private  void dinamicCreation() {
        ArrayList<String[]> names = new ArrayList<>();
        names.add(nameslayout1);
        names.add(nameslayout2);

        ArrayList<int[]> colors = new ArrayList<>();
        colors.add(colorlayout1);
        colors.add(colorlayout2);

        int id = maxId/2-1;
        for (int i = 0; i < layout.length; i++) {
            LinearLayout myRoot = findViewById(layout[i]);
            if (i == 1)
                id = maxId/2;

            for (int j = 0; j < maxId/2; j++) {

                TextView textView = new TextView(this);

                textView.setText(names.get(i)[j]);
                textView.setAutoSizeTextTypeWithDefaults(TextView.AUTO_SIZE_TEXT_TYPE_UNIFORM);

                if(mode) {
                    textView.setBackgroundResource(R.drawable.back_white);
                    textView.setTextColor(Color.BLACK);
                } else {
                    textView.setBackgroundResource(R.drawable.back_black);
                    textView.setTextColor(Color.WHITE);
                }

                textView.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1.0f));
                textView.setId(id);

                if (i == 0)
                    id--;
                else
                    id++;

                View view = new View(this);
                view.setLayoutParams(new LinearLayout.LayoutParams(1, ViewGroup.LayoutParams.MATCH_PARENT, 0.05f));
                view.setBackgroundColor(colors.get(i)[j]);

                LinearLayout temporary = new LinearLayout(this);
                temporary.setOrientation(LinearLayout.HORIZONTAL);

                temporary.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 1.0f));
                temporary.addView(view);
                temporary.addView(textView);

                myRoot.addView(temporary);
            }
        }

        logger.info("created board");

        for (int i = 0;i < playersCount;i++){

            TextView textView = findViewById(listIdMoney[i]);
            textView.setText(Integer.toString(moneyPlayer[i]));
            textView.setBackgroundColor(PlayerColor.colorlayout2[i]);
        }

        logger.info("created info panel");

    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    public  void roll(View v){

        while (moneyPlayer[numberPlayer] <= 0)
            numberPlayer = nextPlayer(numberPlayer);

        moneyPlayersEpoch.get(numberPlayer).add(moneyPlayer[numberPlayer]);
        numberBefore = numberPlayer;

        if (moneyPlayer[numberPlayer]>0) {

            rand1 = 1 + new Random().nextInt(6);
            rand2 = 1 + new Random().nextInt(6);
            rand = rand1 + rand2;

            new Conditionals(this, this).checkLoop(locationId[numberPlayer] + rand);
            new Conditionals(this, this).checkLoop(locationId[numberPlayer] + rand);
            locationId[numberPlayer] = (locationId[numberPlayer] + rand) % (maxId);
            Dialogs dialogs = new Dialogs(priceCard[locationId[numberPlayer]], moneyPlayer[numberPlayer], this, this);


           if (!inPrison[numberPlayer]) {

               int prisonCard1 = 10;
               int prisonCard2 = 30;
               if (possession[locationId[numberPlayer]] == numberPlayer && countBuildings[locationId[numberPlayer]] < 5) {

                        logger.info(numberPlayer + " can build");
                        dialogs.dialogCreate(1);

                } else if (locationId[numberPlayer] == prisonCard1 || locationId[numberPlayer] == prisonCard2) {

                    logger.info(numberPlayer + " to prison");
                    locationId[numberPlayer] = prisonCard1;
                    inPrison[numberPlayer] = true;
                    numberPlayer = nextPlayer(numberPlayer);

                } else if (possession[locationId[numberPlayer]] == -1 && priceCard[locationId[numberPlayer]] > 0) {

                    logger.info(numberPlayer + " can buy");
                    dialogs.dialogCreate(0);

                } else if (possession[locationId[numberPlayer]] == -1 && priceCard[locationId[numberPlayer]] < 0) {

                    logger.info(numberPlayer + " taxation");
                    moneyPlayer[numberPlayer] += priceCard[locationId[numberPlayer]];
                    new HandlingClick(this, this).rewrite(numberPlayer);
                    numberPlayer = nextPlayer(numberPlayer);

                } else if (priceCard[locationId[numberPlayer]] > 0) {

                    logger.info(numberPlayer + " should pay a tax other player");
                    int playerGetMoney = possession[locationId[numberPlayer]];
                    int tax = (int) (0.1 * xPrice[locationId[numberPlayer]] * countBuildings[locationId[numberPlayer]] * priceCard[locationId[numberPlayer]]);
                    moneyPlayer[numberPlayer] -= tax;
                    moneyPlayer[playerGetMoney] += tax;

                    new HandlingClick(this, this).rewrite(numberPlayer);
                    new HandlingClick(this, this).rewrite(playerGetMoney);

                    numberPlayer = nextPlayer(numberPlayer);
                } else {
                    numberPlayer = nextPlayer(numberPlayer);
                }
            } else {

                logger.info(numberPlayer + " sitting in prison");
                dialogs = new Dialogs(50, moneyPlayer[numberPlayer], this, this);
                dialogs.dialogCreate(2);

            }
        }
        new Conditionals(this, this).checkPlayerEnd(numberBefore);
    }

}
