package com.example.monopoly.Dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.monopoly.Game.BoardCreating;
import com.example.monopoly.Game.PlayerColor;
import com.example.monopoly.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDialogFragment;
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

    public Dialogs(int moneyCard, int moneyPlay, Context context){
        this.moneyCard = moneyCard;
        this.moneyPlay = moneyPlay;

        this.context = context;
    }

    public void buyGround(){
        new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE)
                .setTitleText("Do you want to buy?")
                .setContentText("Its cost is " + moneyCard + "\nYou have " + moneyPlay)
                .setCancelButton("Pass", new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.dismissWithAnimation();

                        blurView.setVisibility(View.GONE);
                        possession[locationId[numberPlayer]]=numberPlayer;
                        moneyPlayer[numberPlayer]=moneyPlayer[numberPlayer]-priceCard[locationId[numberPlayer]];

                        TextView currentPlayerView = findViewById(listIdMoney[numberPlayer]);
                        currentPlayerView.setText(Integer.toString(moneyPlayer[numberPlayer]));
                        buttonRand.setVisibility(View.VISIBLE);

                        TextView textViewCurrent = findViewById(locationId[numberPlayer]);
                        PlayerColor playerColor = new PlayerColor();
                        textViewCurrent.setBackgroundColor(colorlayout2[numberPlayer]);
                        //item1 = item;
                        //checkPlayerEnd(numberBefore);
                        //checkx2();
                        numberPlayer = (numberPlayer + 1)%6;
                        //if (inPrison[numberPlayer]) buttonPrison.setVisibility(View.VISIBLE);
                    }
                })
                .setConfirmText("Buy")
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.dismissWithAnimation();
                    }
                })

                .show();
    }

    public void buyBuilding(){
        new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE)
                .setTitleText("Do you want to build?")
                .setContentText("Its cost is " + moneyCard + "\nYou have " + moneyPlayer)
                .setCancelButton("Pass", new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.dismissWithAnimation();
                    }
                })
                .setConfirmText("Build")
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.dismissWithAnimation();
                    }
                })

                .show();
    }

    public void inPrison(){
        new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE)
                .setTitleText("Do you want to pay to leave prison?")
                .setContentText("Its cost is " + 50 + "\nYou have " + moneyPlayer)
                .setCancelButton("Roll", new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.dismissWithAnimation();
                    }
                })
                .setConfirmText("Pay")
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.dismissWithAnimation();
                    }
                })

                .show();
    }
//    @Override
//    public Dialog onCreateDialog(Bundle savedInstanceState) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//        builder.setTitle("Its cost is " + money)
//                .setPositiveButton("buy", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int which) {
//
//                    }
//                })
//                .setNegativeButton("pass", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//
//                    }
//                });
//        return builder.create();
//    }

}
