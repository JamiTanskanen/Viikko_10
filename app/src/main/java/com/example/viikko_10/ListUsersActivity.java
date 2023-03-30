package com.example.viikko_10;

import android.content.Context;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ListUsersActivity extends AppCompatActivity {

    private RecyclerView recyclerViewUsersList;
    private UserAdapter userAdapter;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_users);

        context = this;

        recyclerViewUsersList = findViewById(R.id.recyclerViewUsersList);
        recyclerViewUsersList.setLayoutManager(new LinearLayoutManager(this));
        // Pass the context and an empty list to the UserAdapter constructor
        userAdapter = new UserAdapter(this, new ArrayList<User>());
        recyclerViewUsersList.setAdapter(userAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateUsersList();
    }

    private void updateUsersList() {
        ArrayList<User> users = UserStorage.getInstance(context).getUsers();
        Collections.sort(users, new Comparator<User>() {
            @Override
            public int compare(User u1, User u2) {
                return u1.getLastName().compareTo(u2.getLastName());
            }
        });

        userAdapter.setUsers(users);
    }
}


