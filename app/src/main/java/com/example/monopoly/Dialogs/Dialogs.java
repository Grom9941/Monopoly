package com.example.monopoly.Dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.monopoly.Game.HandlingClick;
import com.example.monopoly.Game.PlayerColor;

import androidx.appcompat.app.AppCompatActivity;
import cn.pedant.SweetAlert.SweetAlertDialog;

import static com.example.monopoly.Game.BoardCreating.blurView;
import static com.example.monopoly.Game.BoardCreating.buttonRand;
import static com.example.monopoly.Game.BoardCreating.inPrison;
import static com.example.monopoly.Game.BoardCreating.listIdMoney;
import static com.example.monopoly.Game.BoardCreating.locationId;
import static com.example.monopoly.Game.BoardCreating.moneyPlayer;
import static com.example.monopoly.Game.BoardCreating.numberPlayer;
import static com.example.monopoly.Game.BoardCreating.possession;
import static com.example.monopoly.Game.BoardCreating.priceCard;
import static com.example.monopoly.Game.PlayerColor.colorlayout2;

public class Dialogs extends AppCompatActivity {


    private final int moneyCard;
    private final int moneyPlay;
    private Context context;
    private String[] textButton = {"Pass", "Buy", "Pass", "Build", "Roll", "Pay"};
    private String[] textTitle = {"Do you want to buy?", "Do you want to build?", "Do you want to pay to leave prison?"};

    public Dialogs(int moneyCard, int moneyPlay, Context context){
        this.moneyCard = moneyCard;
        this.moneyPlay = moneyPlay;

        this.context = context;
    }

    public void dialogCreate(final int numberDialog){
        new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE)
                .setTitleText(textTitle[numberDialog])
                .setContentText("Its cost is " + moneyCard + "\nYou have " + moneyPlay)
                .setCancelButton(textButton[numberDialog*2], new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.dismissWithAnimation();
                        if(numberDialog == 1){
                            new HandlingClick().notBuyClick();
                        } else if(numberDialog == 2){
                            HandlingClick.notBuyBuilding();
                        } else {
                            //roll again
                        }
                    }
                })
                .setConfirmText(textButton[numberDialog*2+1])
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.dismissWithAnimation();
                        if(numberDialog == 1){
                            new HandlingClick().buyClick();
                        } else if(numberDialog == 2){
                            HandlingClick.buyBuilding();
                        } else {
                            new HandlingClick().payForPrison();
                        }
                    }
                })

                .show();
    }
}
