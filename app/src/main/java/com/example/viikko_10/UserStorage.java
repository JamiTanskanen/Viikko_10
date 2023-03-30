package com.example.viikko_10;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class UserStorage {
    private static UserStorage instance;
    private ArrayList<User> users;
    private SharedPreferences sharedPreferences;
    private static final String SHARED_PREFS_NAME = "user_storage_prefs";
    private static final String USER_LIST_KEY = "user_list_key";

    private UserStorage(Context context) {
        sharedPreferences = context.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE);
        loadUsers();
    }

    public static UserStorage getInstance(Context context) {
        if (instance == null) {
            instance = new UserStorage(context);
        }
        return instance;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void addUser(User user) {
        users.add(user);
        saveUsers();
    }

    public void removeUser(int id) {
        users.remove(id);
        saveUsers();
    }

    private void saveUsers() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(users);
        editor.putString(USER_LIST_KEY, json);
        editor.apply();
    }

    private void loadUsers() {
        Gson gson = new Gson();
        String json = sharedPreferences.getString(USER_LIST_KEY, null);
        Type type = new TypeToken<ArrayList<User>>() {}.getType();
        users = gson.fromJson(json, type);

        if (users == null) {
            users = new ArrayList<>();
        }
    }
}
