package com.example.monopoly.Database;

import java.util.ArrayList;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "users")
public class User {

    @PrimaryKey
    private int id;

    @ColumnInfo(name = "user")
    private int numberUser;

    @ColumnInfo(name = "color")
    private String colorUser;

    @ColumnInfo(name = "money_for_player")
    private ArrayList<Integer> epochMoney;

    @ColumnInfo(name = "finish")
    private int finishNumber;

    public int getNumberUser() {
        return numberUser;
    }

    public void setNumberUser(int numberUser) {
        this.numberUser = numberUser;
    }

    public String getColorUser() {
        return colorUser;
    }

    public void setColorUser(String colorUser) {
        this.colorUser = colorUser;
    }

    public ArrayList<Integer> getEpochMoney() {
        return epochMoney;
    }

    public void setEpochMoney(ArrayList<Integer> epochMoney) {
        this.epochMoney = epochMoney;
    }

    public int getFinishNumber() {
        return finishNumber;
    }

    public void setFinishNumber(int finishNumber) {
        this.finishNumber = finishNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
