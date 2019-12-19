package com.example.monopoly.Game;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.widget.TextView;

import com.example.monopoly.Database.User;
import com.example.monopoly.R;

import java.util.ArrayList;

public class Conditionals extends BoardCreating {

    public Activity activity;
    private Context context;

    public Conditionals(Activity activityCurrent, Context contextCurrent){
        this.activity = activityCurrent;
        this.context = contextCurrent;
    }

    public static int nextPlayer(int currentPlayer){
        return (currentPlayer + 1) % playersCount;
    }

    public static void checkx2(){
        int count;
        int owner;
        for (int[] element : x2){
            count = 0;
            owner = possession[element[0]];

            for (int number : element)
                if (owner == possession[number])
                    count++;

            if (count == element.length)
                for (int number : element)
                    xPrice[number]=2;
        }
    }

    public void checkLoop(int idAfterStep) {

        if (idAfterStep > maxId-1) {
            logger.info(numberPlayer + " next loop");
            moneyPlayer[numberPlayer] += 200;

            new HandlingClick(activity, context).rewrite(numberPlayer);
        }

    }

    public void checkPlayerEnd(int numberPlayer){

        if (!outOfGame[numberPlayer] && moneyPlayer[numberPlayer] <= 0){
            logger.info(numberPlayer + " endPlayer");

            outOfGame[numberPlayer]=true;
            endPlayer(numberPlayer);
            endGame++;

            if (endGame==playersCount-1){
                int last = 0;
                while (moneyPlayer[last] <= 0){
                    last++;
                }
                new Conditionals(activity, context).addBD(last, true);
            }
            //numberPlayer = (numberPlayer + 1) % 6;
        }
    }

    private void endPlayer(int number){

        addBD(number,false);
        logger.info("user added successfully");


        for (int i = 0; i < maxId; i++) {
            if(possession[i] == number){

                possession[i] = -1;
                xPrice[i] = 1;

                TextView textViewCurrent = activity.findViewById(i);
                textViewCurrent.setBackground(Drawable.createFromPath("?attr/backgroundcolor"));

                if (mode) {
                    textViewCurrent.setBackgroundResource(R.drawable.back_white);
                } else {
                    textViewCurrent.setBackgroundResource(R.drawable.back_black);
                }
            }
        }
    }

    private void addBD(int number, boolean end){

        User user = new User();
        user.setId(myAppDatabase.myDataObject().getUsers().size()+1);
        user.setNumberUser(number);
        user.setColorUser(colorPlayer[number]);
        user.setEpochMoney((ArrayList<Integer>) moneyPlayersEpoch.get(number));
        user.setFinishNumber(endGame);

        myAppDatabase.myDataObject().addUser(user);

        if (end) {
            Intent intent = new Intent(activity, EndGame.class);
            logger.info("2");
            context.startActivity(intent);
            finish();
        }
    }
}
