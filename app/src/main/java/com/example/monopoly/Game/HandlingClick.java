package com.example.monopoly.Game;

import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import static com.example.monopoly.Game.BoardCreating.blurView;
import static com.example.monopoly.Game.BoardCreating.buttonRand;
import static com.example.monopoly.Game.BoardCreating.countBuildings;
import static com.example.monopoly.Game.BoardCreating.inPrison;
import static com.example.monopoly.Game.BoardCreating.listIdMoney;
import static com.example.monopoly.Game.BoardCreating.locationId;
import static com.example.monopoly.Game.BoardCreating.logger;
import static com.example.monopoly.Game.BoardCreating.moneyPlayer;
import static com.example.monopoly.Game.BoardCreating.numberBefore;
import static com.example.monopoly.Game.BoardCreating.numberPlayer;
import static com.example.monopoly.Game.BoardCreating.possession;
import static com.example.monopoly.Game.BoardCreating.priceCard;
import static com.example.monopoly.Game.Conditionals.checkx2;
import static com.example.monopoly.Game.PlayerColor.colorlayout2;

public class HandlingClick extends AppCompatActivity {

    public void buyClick() {
        blurView.setVisibility(View.GONE);
        possession[locationId[numberPlayer]] = numberPlayer;
        moneyPlayer[numberPlayer] = moneyPlayer[numberPlayer] - priceCard[locationId[numberPlayer]];

        TextView currentPlayerView = findViewById(listIdMoney[numberPlayer]);
        currentPlayerView.setText(Integer.toString(moneyPlayer[numberPlayer]));
        buttonRand.setVisibility(View.VISIBLE);

        TextView textViewCurrent = findViewById(locationId[numberPlayer]);
        PlayerColor playerColor = new PlayerColor();
        textViewCurrent.setBackgroundColor(colorlayout2[numberPlayer]);
        Conditionals conditionals = new Conditionals();
        conditionals.checkPlayerEnd(numberBefore);
        checkx2();
        numberPlayer = (numberPlayer + 1) % 6;
        //if (inPrison[numberPlayer]) buttonPrison.setVisibility(View.VISIBLE);
    }

    public void notBuyClick() {
        blurView.setVisibility(View.GONE);
        buttonRand.setVisibility(View.VISIBLE);
        numberPlayer = (numberPlayer + 1)%6;
        //if (inPrison[numberPlayer]) buttonPrison.setVisibility(View.VISIBLE);
    }

    public static void buyBuilding(){
        logger.info(numberPlayer + " before buy building" + countBuildings[locationId[numberPlayer]]);
        moneyPlayer[numberPlayer]-=priceCard[locationId[numberPlayer]];
        countBuildings[locationId[numberPlayer]]++;

        //buttonBuyBuilding.setVisibility(View.GONE);
        //notButtonBuyBuilding.setVisibility(View.GONE);
        logger.info(numberPlayer + " after buy building" + countBuildings[locationId[numberPlayer]]);
        numberPlayer = (numberPlayer + 1) % 6;
        //if (inPrison[numberPlayer]) buttonPrison.setVisibility(View.VISIBLE);
        buttonRand.setVisibility(View.VISIBLE);

    }

    public static void notBuyBuilding(){
        logger.info(numberPlayer + " not buy building " + countBuildings[locationId[numberPlayer]]);

        //buttonBuyBuilding.setVisibility(View.GONE);
        //notButtonBuyBuilding.setVisibility(View.GONE);
        numberPlayer = (numberPlayer + 1) % 6;
        //if (inPrison[numberPlayer]) buttonPrison.setVisibility(View.VISIBLE);
        buttonRand.setVisibility(View.VISIBLE);
    }

    public void payForPrison(){
        logger.info(numberPlayer + " pay for prison");
        moneyPlayer[numberPlayer]-=50;
        TextView currentPlayerView = findViewById(listIdMoney[numberPlayer]);
        currentPlayerView.setText(Integer.toString(moneyPlayer[numberPlayer]));
        inPrison[numberPlayer]=false;
        //buttonPrison.setVisibility(View.GONE);
    }
}
