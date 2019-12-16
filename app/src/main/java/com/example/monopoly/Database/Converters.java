package com.example.monopoly.Database;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import androidx.room.TypeConverter;

public class Converters {
    @TypeConverter
    public static ArrayList<Integer> fromString(Integer value) {
        Type listType = new TypeToken<ArrayList<Integer>>() {}.getType();
        return new Gson().fromJson(String.valueOf(value), listType);
    }

    @TypeConverter
    public static String fromArrayList(ArrayList<Integer> list) {
        Gson gson = new Gson();
        String json = gson.toJson(list);
        return json;
    }
}