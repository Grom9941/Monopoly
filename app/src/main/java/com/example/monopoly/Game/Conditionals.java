package com.example.monopoly.Game;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.monopoly.Database.User;
import com.example.monopoly.R;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import eightbitlab.com.blurview.RenderScriptBlur;

import static com.example.monopoly.Game.BoardCreating.blurView;
import static com.example.monopoly.Game.BoardCreating.buttonRand;
import static com.example.monopoly.Game.BoardCreating.colorPlayer;
import static com.example.monopoly.Game.BoardCreating.endGame;
import static com.example.monopoly.Game.BoardCreating.listIdMoney;
import static com.example.monopoly.Game.BoardCreating.locationId;
import static com.example.monopoly.Game.BoardCreating.logger;
import static com.example.monopoly.Game.BoardCreating.maxId;
import static com.example.monopoly.Game.BoardCreating.moneyPlayer;
import static com.example.monopoly.Game.BoardCreating.moneyPlayersEpoch;
import static com.example.monopoly.Game.BoardCreating.myAppDatabase;
import static com.example.monopoly.Game.BoardCreating.numberPlayer;
import static com.example.monopoly.Game.BoardCreating.outOfGame;
import static com.example.monopoly.Game.BoardCreating.possession;
import static com.example.monopoly.Game.BoardCreating.priceCard;
import static com.example.monopoly.Game.BoardCreating.x2;
import static com.example.monopoly.Game.BoardCreating.xPrice;
import static com.example.monopoly.Game.BoardCreating.mode;
import static com.example.monopoly.Game.PlayerColor.colorlayout2;

public class Conditionals extends AppCompatActivity{

private static final int maxPlayer = 6;

    public static void checkx2(){
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

    public static int nextPlayer(int currentPlayer){
        return (currentPlayer + 1) % maxPlayer;
    }
    public void blurBackground(){
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

    public void checkLoop(int idAfterStep) {
        if (idAfterStep > maxId-1) {
            logger.info(numberPlayer + " next loop");
            moneyPlayer[numberPlayer] += 200;
            TextView currentPlayerView = findViewById(listIdMoney[numberPlayer]);
            currentPlayerView.setText(Integer.toString(moneyPlayer[numberPlayer]));
        }
    }

    public void checkPlayerEnd(int numberBefore){

        logger.info(outOfGame[numberBefore] + " endPlayer" +  moneyPlayer[numberBefore]);
        if (!outOfGame[numberBefore] && moneyPlayer[numberBefore] <= 0){
            logger.info(numberBefore + " endPlayer");
            outOfGame[numberBefore]=true;
            endPlayer(numberBefore);
            endGame++;
            if (endGame==5){
                int findLast = 0;
                while (moneyPlayer[findLast] <= 0){
                    findLast = (findLast + 1) % 6;
                }
                endGame(findLast);
            }
            //numberPlayer = (numberPlayer + 1) % 6;
        }
    }

    private static void endGame(int number){
        logger.info( "end Game");
        User user = new User();
        user.setNumberUser(number);
        user.setColorUser(colorPlayer[number]);
        user.setEpochMoney((ArrayList<Integer>) moneyPlayersEpoch.get(number));
        user.setFinishNumber(endGame);

        myAppDatabase.myDataObject().addUser(user);

        BoardCreating boardCreating = new BoardCreating();
        boardCreating.stopGame();
    }

    private void endPlayer(int number){

        User user = new User();
        user.setNumberUser(number);
        user.setColorUser(colorPlayer[number]);
        user.setEpochMoney((ArrayList<Integer>) moneyPlayersEpoch.get(number));
        user.setFinishNumber(endGame);

        myAppDatabase.myDataObject().addUser(user);
        logger.info("user added successfully");


        for (int i = 0; i < possession.length; i++) {
            if(possession[i] == number){
                possession[i] = -1;
                xPrice[i] = 1;
                TextView textViewCurrent = findViewById(i);
                textViewCurrent.setBackground(Drawable.createFromPath("?attr/backgroundcolor"));
                if (mode) {
                    textViewCurrent.setBackgroundResource(R.drawable.back_white);
                } else {
                    textViewCurrent.setBackgroundResource(R.drawable.back_black);
                }
            }
        }
    }
}
