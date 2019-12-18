package com.example.monopoly.Game;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import static com.example.monopoly.Game.Conditionals.checkx2;
import static com.example.monopoly.Game.Conditionals.nextPlayer;
import static com.example.monopoly.Game.PlayerColor.colorlayout2;

public class HandlingClick extends BoardCreating {

    private Activity activity;

    public HandlingClick(Activity activityCurrent){
        this.activity = activityCurrent;
    }

    public void rewrite(int numberPlayer){
        TextView currentPlayerView = activity.findViewById(listIdMoney[numberPlayer]);
        currentPlayerView.setText(Integer.toString(moneyPlayer[numberPlayer]));
    }

    public void buyClick() {
        logger.info("buy");
        possession[locationId[numberPlayer]] = numberPlayer;
        moneyPlayer[numberPlayer] = moneyPlayer[numberPlayer] - priceCard[locationId[numberPlayer]];

        rewrite(numberPlayer);

        TextView textViewCurrent = activity.findViewById(locationId[numberPlayer]);
        textViewCurrent.setBackgroundColor(colorlayout2[numberPlayer]);

        new Conditionals(activity).checkPlayerEnd(numberPlayer);
        checkx2();
        numberPlayer = nextPlayer(numberPlayer);

        buttonRand.setVisibility(View.VISIBLE);
    }

    public static void notBuyClick() {
        logger.info("not buy");
        numberPlayer = nextPlayer(numberPlayer);

        buttonRand.setVisibility(View.VISIBLE);
    }

    public static void rollAgain() {
        logger.info("roll");

        if (rand1 == rand2)
            inPrison[numberPlayer] = false;
        numberPlayer = nextPlayer(numberPlayer);

        buttonRand.setVisibility(View.VISIBLE);
    }

    public void buyBuilding(){
        logger.info(numberPlayer + " before buy building" + countBuildings[locationId[numberPlayer]]);

        moneyPlayer[numberPlayer]-=priceCard[locationId[numberPlayer]];
        countBuildings[locationId[numberPlayer]]++;
        rewrite(numberPlayer);

        logger.info(numberPlayer + " after buy building" + countBuildings[locationId[numberPlayer]]);
        numberPlayer = nextPlayer(numberPlayer);

        buttonRand.setVisibility(View.VISIBLE);
    }

    public static void notBuyBuilding(){
        logger.info(numberPlayer + " not buy building " + countBuildings[locationId[numberPlayer]]);
        numberPlayer = nextPlayer(numberPlayer);

        buttonRand.setVisibility(View.VISIBLE);
    }

    public void payForPrison(){
        logger.info(numberPlayer + " pay for prison");
        moneyPlayer[numberPlayer]-=50;
        inPrison[numberPlayer]=false;
        rewrite(numberPlayer);

        buttonRand.setVisibility(View.VISIBLE);
    }
}
