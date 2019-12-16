package com.example.monopoly.Database;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.TypeConverters;

@Dao
@TypeConverters({Converters.class})
public interface MyDataObject {

    @Insert
    public void addUser(User user);

    @Query("select * from users")
    public List<User> getUsers();

    @Query("DELETE FROM users")
    void delete();
}
