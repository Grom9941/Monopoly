package com.example.monopoly.Dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.View;

import com.example.monopoly.Game.HandlingClick;

import cn.pedant.SweetAlert.SweetAlertDialog;

import static com.example.monopoly.Game.BoardCreating.buttonRand;

public class Dialogs {

    private final int moneyCard;
    private final int moneyPlay;
    private Activity activity;
    private Context context;
    private String[] textButton = {"Pass", "Buy", "Pass", "Build", "Roll", "Pay"};
    private String[] textTitle = {"Do you want to buy?", "Do you want to build?", "Do you want to pay to leave prison?"};

    public Dialogs(int moneyCard, int moneyPlay, Activity activity, Context context){
        this.moneyCard = moneyCard;
        this.moneyPlay = moneyPlay;

        this.activity = activity;
        this.context = context;
    }

    public void dialogCreate(final int numberDialogs){
        buttonRand.setVisibility(View.GONE);
        new SweetAlertDialog(activity, SweetAlertDialog.WARNING_TYPE)
                .setTitleText(textTitle[numberDialogs])
                .setContentText("Its cost is " + moneyCard + "\nYou have " + moneyPlay)
                .setCancelButton(textButton[numberDialogs*2], new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.dismissWithAnimation();
                        if(numberDialogs == 0)
                            HandlingClick.notBuyClick();
                        else if(numberDialogs == 1)
                            HandlingClick.notBuyBuilding();
                        else
                            HandlingClick.rollAgain();
                    }
                })
                .setConfirmText(textButton[numberDialogs*2+1])
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.dismissWithAnimation();
                        if(numberDialogs == 0)
                            new HandlingClick(activity, context).buyClick();
                        else if(numberDialogs == 1)
                            new HandlingClick(activity, context).buyBuilding();
                        else
                            new HandlingClick(activity, context).payForPrison();
                    }
                })

                .show();
    }
}
