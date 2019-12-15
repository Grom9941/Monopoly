package com.example.monopoly;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import eightbitlab.com.blurview.BlurView;
import eightbitlab.com.blurview.RenderScriptBlur;

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

import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Logger;

public class BoardCreating extends AppCompatActivity {

    public static final Logger logger = Logger.getGlobal();

    int[] colorlayout1 = {0xFFFFA500,0xFFFFA500,0xFFB0B0B0,0xFFFFA500,0xFFB0B0B0,0xFFFFC0CB,
            0xFFFFC0CB,0xFFB0B0B0,0xFFFFC0CB,0xFFB0B0B0,0xFFADD8E6,0xFFADD8E6,0xFFB0B0B0,0xFFADD8E6,0xFFB0B0B0,
            0xFFB0B0B0,0xFFA0522D,0xFFB0B0B0,0xFFA0522D,0xFFB0B0B0};
    int[] colorlayout2 = {0xFFB0B0B0,0xFFFF0000,0xFFB0B0B0,0xFFFF0000,0xFFFF0000,0xFFB0B0B0,
            0xFFFFFF00,0xFFFFFF00,0xFFB0B0B0,0xFFFFFF00,0xFFB0B0B0,0xFF90EE90,0xFF90EE90,0xFFB0B0B0,0xFF90EE90,0xFFB0B0B0,0xFFB0B0B0,
            0xFF0000FF,0xFFB0B0B0,0xFF0000FF};
    String[] nameslayout1 = {"NEW YORK AVENUE","TENNESSEE AVENUE","COMMUNITY CHEST","ST.JAMES PLACE",
            "PENNSYLVANIA RAILROAD","VIRGINIA AVENUE","STATES AVENUE","ELECTRIC COMPANY","ST.CHARLES PLACE",
            "IN JAIL","CONNECTICUT AVENUE","VERMONT AVENUE","CHANCE","ORIENTAL AVENUE","READING RAILROAD",
            "INCOME TAX","BALTIC AVENUE","COMMUNITY CHEST","MEDITER-RANEAN AVENUE","COLLECT SALARY"};
    String[] nameslayout2 = {"FREE PARKING","KENTUCKY AVENUE","CHANCE","INDIANA AVENUE","ILLINOIS AVENUE",
            "B & O RAILROAD","ATLANTIC AVENUE","VENTNOR AVENUE","WATER WORKS","MARVIN GARDENS","GO TO JAIL",
            "PACIFIC AVENUE","NORTH CAROLINA AVENUE","COMMUNITY CHEST","PENNSYLVANIA AVENUE","SHORT LINE","CHANCE",
            "PARK PALCE","LUXURY TAX","BOARDWALK"};

    int[] possession = new int[40];
    int[] priceCard = {0,60,0,60,-200,200,100,0,100,120,0,140,150,140,160,200,180,0,180,200,0,220,0,220,240,200,260,260,150,280,0,300,300,0,320,200,0,350,-100,400};
    int[] xPrice = {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
    int[] locationId = {0,0,0,0,0,0};
    boolean[] inPrison = {false,false,false,false,false,false};
    boolean[] outOfGame = {false,false,false,false,false,false};
    int numberPlayer = 0;
    final int maxId = 40;
    int[] moneyPlayer = {500,500,500,500,500,500};
    int[] layout = {R.id.linearLayout1, R.id.linearLayout2};
    int[] listIdMoney =  {R.id.textViewboard1,R.id.textViewboard2,R.id.textViewboard3,R.id.textViewboard4,R.id.textViewboard5,R.id.textViewboard6};
    int[][] x2 = {{1,3},{6,8,9},{11,13,14},{16,18,19},{21,23,24},{26,27,29},{31,32,34},{37,39}};
    int endGame = 0;
    int numberBefore = 0;

    BlurView blurView;
    Button buttonPrison;
    //MenuItem item1;
    //MenuItem item2;
    @RequiresApi(api = Build.VERSION_CODES.O)
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.board);
        blurView = findViewById(R.id.blurView);
        buttonPrison = findViewById(R.id.payprison);
        for (int i = 0; i < possession.length; i++)
            possession[i] = -1;

        blurBackground();
        dinamicCreation();
        //GameLogic gameLogic = new GameLogic();
        //gameLogic.startGame();
        //TextView textView = findViewById(0);
        //textView.setBackgroundColor(0xFFFFFF00);
        //textView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.type4, R.drawable.type4, R.drawable.type4, R.drawable.type4);

    }
    private void blurBackground(){
        float radius = 1f;

        View decorView = getWindow().getDecorView();
        ViewGroup rootView = decorView.findViewById(android.R.id.content);

        Drawable windowBackground = decorView.getBackground();

        blurView.setupWith(rootView)
                .setFrameClearDrawable(windowBackground)
                .setBlurAlgorithm(new RenderScriptBlur(this))
                .setBlurRadius(radius)
                .setHasFixedTransformationMatrix(false);

        blurView.setVisibility(View.GONE);
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
            if (i == 1){
                id = maxId/2;
            }
            for (int j = 0; j < maxId/2; j++) {

                TextView textView = new TextView(this);

                textView.setText(names.get(i)[j]);
                textView.setAutoSizeTextTypeWithDefaults(TextView.AUTO_SIZE_TEXT_TYPE_UNIFORM);
                textView.setBackgroundResource(R.drawable.back);

                textView.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1.0f));
                textView.setId(id);
                if (i == 0) {
                    id--;
                } else {
                    id++;
                }

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

        for (int i = 0;i < listIdMoney.length;i++){
            TextView textView = findViewById(listIdMoney[i]);
            textView.setText(Integer.toString(moneyPlayer[i]));
            PlayerColor playerColor = new PlayerColor();
            textView.setBackgroundColor(playerColor.colorlayout2[i]);
        }
        buttonPrison.setVisibility(View.GONE);

        logger.info("created info panel");

    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    public void buyClick(MenuItem item) {

        blurView.setVisibility(View.GONE);
        possession[locationId[numberPlayer]]=numberPlayer;
        moneyPlayer[numberPlayer]=moneyPlayer[numberPlayer]-priceCard[locationId[numberPlayer]];

        TextView currentPlayerView = findViewById(listIdMoney[numberPlayer]);
        currentPlayerView.setText(Integer.toString(moneyPlayer[numberPlayer]));
        Button random = findViewById(R.id.button);
        random.setVisibility(View.VISIBLE);

        TextView textViewCurrent = findViewById(locationId[numberPlayer]);
        PlayerColor playerColor = new PlayerColor();
        textViewCurrent.setBackgroundColor(playerColor.colorlayout2[numberPlayer]);
        //item1 = item;
        checkPlayerEnd(numberBefore);
        checkx2();
        numberPlayer = (numberPlayer + 1)%6;
    }


    public void notBuyClick(MenuItem item) {
        blurView.setVisibility(View.GONE);
        Button random = findViewById(R.id.button);
        random.setVisibility(View.VISIBLE);
        numberPlayer = (numberPlayer + 1)%6;
        //item2 = item;
    }

    public void prison(View v){
        logger.info(numberPlayer + " pay for prison");
        moneyPlayer[numberPlayer]-=50;
        inPrison[numberPlayer]=false;
        buttonPrison.setVisibility(View.GONE);
    }
    private void checkLoop(int idAfterStep) {
        if (idAfterStep > maxId-1) {
            logger.info(numberPlayer + " next loop");
            moneyPlayer[numberPlayer] += 200;
            TextView currentPlayerView = findViewById(listIdMoney[numberPlayer]);
            currentPlayerView.setText(Integer.toString(moneyPlayer[numberPlayer]));
        }
//        } else {
//            TextView currentPlayerView = findViewById(listIdMoney[numberPlayer]);
//            currentPlayerView.setText(Integer.toString(locationId[numberPlayer]));
//        }
    }

    private void checkx2(){
        for (int[] element : x2){
            int count = 0;
            int owner = -1;
            for (int number : element){
                if (owner == -1){
                    owner=possession[number];
                    count++;
                } else if (owner == possession[number]) {
                    count++;
                }
            }
            if (count == element.length){
                for (int number : element){
                    xPrice[number]=2;
                }
            }
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void endGame(){
        logger.info( "end Game");

        //if (Arrays.stream(moneyPlayer).sum()==0){
            //Intent intent = new Intent(BoardCreating.this, EndGame.class);
            //startActivity(intent);
            //finish();
        //}
    }

    private void endPlayer(int number){
        for (int i = 0; i < possession.length; i++) {
            if(possession[i] == number){
                possession[i] = -1;
                xPrice[i] = 1;
                TextView textViewCurrent = findViewById(i);
                textViewCurrent.setBackgroundColor(Color.WHITE);
                textViewCurrent.setBackgroundResource(R.drawable.back);
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void checkPlayerEnd(int numberBefore){
        logger.info(outOfGame[numberBefore] + " endPlayer" +  moneyPlayer[numberBefore]);
        if (!outOfGame[numberBefore] && moneyPlayer[numberBefore] < 0){
            logger.info(numberBefore + " endPlayer");
            outOfGame[numberBefore]=true;
            endPlayer(numberBefore);
            endGame++;
            if (endGame==5){
                endGame();
            }
            //numberPlayer = (numberPlayer + 1) % 6;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public  void roll(View v){
        while (moneyPlayer[numberPlayer] < 0){
            numberPlayer = (numberPlayer + 1) % 6;
        }
        numberBefore = numberPlayer;
        if (moneyPlayer[numberPlayer]>0) {
            //logger.info("clicked roll's button");
            if (inPrison[(numberPlayer + 1) % 6]) buttonPrison.setVisibility(View.VISIBLE);

            TextView currentPlayerView = findViewById(listIdMoney[numberPlayer]);
            int prison1 = 10;
            int prison2 = 30;
            int rand1 = 1 + new Random().nextInt(6);
            int rand2 = 1 + new Random().nextInt(6);
            int rand = rand1 + rand2;

            checkLoop(locationId[numberPlayer] + rand);
            locationId[numberPlayer] = (locationId[numberPlayer] + rand) % (maxId - 1);

            if (!inPrison[numberPlayer]) {
                if (locationId[numberPlayer] == prison1 || locationId[numberPlayer] == prison2) {
                    logger.info(numberPlayer + " to prison");
                    locationId[numberPlayer] = prison1;
                    inPrison[numberPlayer] = true;
                    numberPlayer = (numberPlayer + 1) % 6;
                } else if (possession[locationId[numberPlayer]] == -1 && priceCard[locationId[numberPlayer]] > 0) {
                    logger.info(numberPlayer + " can buy");
                    blurView.setVisibility(View.VISIBLE);
                    v.setVisibility(View.GONE);
                    //item1.setEnabled(true);
                    //item2.setEnabled(true);

                } else if (possession[locationId[numberPlayer]] == -1 && priceCard[locationId[numberPlayer]] < 0) {
                    logger.info(numberPlayer + " taxation");
                    moneyPlayer[numberPlayer] += priceCard[locationId[numberPlayer]];


                    currentPlayerView.setText(Integer.toString(moneyPlayer[numberPlayer]));
                    numberPlayer = (numberPlayer + 1) % 6;
                } else if (possession[locationId[numberPlayer]] > -1 && priceCard[locationId[numberPlayer]] > 0) {
                    logger.info(numberPlayer + " should pay a tax other player");

                    int playerGetMoney = possession[locationId[numberPlayer]];
                    int tax = (int) (0.1 * xPrice[locationId[numberPlayer]] * priceCard[locationId[numberPlayer]]);
                    moneyPlayer[numberPlayer] -= tax;
                    moneyPlayer[playerGetMoney] += tax;

                    currentPlayerView.setText(Integer.toString(moneyPlayer[numberPlayer]));
                    TextView currentPlayerView1 = findViewById(listIdMoney[playerGetMoney]);
                    currentPlayerView1.setText(Integer.toString(moneyPlayer[playerGetMoney]));
                    numberPlayer = (numberPlayer + 1) % 6;
                } else {
                    numberPlayer = (numberPlayer + 1) % 6;
                }
            } else {
                logger.info(numberPlayer + " sitting in prison");
                buttonPrison.setVisibility(View.GONE);
                if (rand1 == rand2) {
                    inPrison[numberPlayer] = false;
                }
                numberPlayer = (numberPlayer + 1) % 6;
            }
        }
        checkPlayerEnd(numberBefore);
    }
}
