package com.example.monopoly.Database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {User.class}, version = 2)
@TypeConverters({Converters.class})
public abstract class MyAppDatabase extends RoomDatabase {

    public abstract MyDataObject myDataObject();

}
