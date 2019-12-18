package com.example.monopoly.Dialogs;

import android.app.Activity;
import android.view.View;

import com.example.monopoly.Game.HandlingClick;

import cn.pedant.SweetAlert.SweetAlertDialog;

import static com.example.monopoly.Game.BoardCreating.buttonRand;

public class Dialogs {

    private final int moneyCard;
    private final int moneyPlay;
    private Activity activity;
    private String[] textButton = {"Pass", "Buy", "Pass", "Build", "Roll", "Pay"};
    private String[] textTitle = {"Do you want to buy?", "Do you want to build?", "Do you want to pay to leave prison?"};

    public Dialogs(int moneyCard, int moneyPlay, Activity activity){
        this.moneyCard = moneyCard;
        this.moneyPlay = moneyPlay;

        this.activity = activity;
    }

    public void dialogCreate(final int numberDialog){
        buttonRand.setVisibility(View.GONE);
        new SweetAlertDialog(activity, SweetAlertDialog.WARNING_TYPE)
                .setTitleText(textTitle[numberDialog])
                .setContentText("Its cost is " + moneyCard + "\nYou have " + moneyPlay)
                .setCancelButton(textButton[numberDialog*2], new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.dismissWithAnimation();
                        if(numberDialog == 0)
                            HandlingClick.notBuyClick();
                        else if(numberDialog == 1)
                            HandlingClick.notBuyBuilding();
                        else
                            HandlingClick.rollAgain();
                    }
                })
                .setConfirmText(textButton[numberDialog*2+1])
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.dismissWithAnimation();
                        if(numberDialog == 0)
                            new HandlingClick(activity).buyClick();
                        else if(numberDialog == 1)
                            new HandlingClick(activity).buyBuilding();
                        else
                            new HandlingClick(activity).payForPrison();
                    }
                })

                .show();
    }
}
