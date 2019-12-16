package com.example.monopoly.Database;

import androidx.room.Dao;
import androidx.room.Insert;

@Dao
public interface MyDataObject {

    @Insert
    public void addUser(User user);

}
