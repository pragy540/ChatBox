package com.example.chatbox;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.chatbox.data.MyDbHandler;
import com.example.chatbox.model.User;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {
    ListView listView;
    MyDbHandler db;
    public static final String Logged_in="com.example.chatbox.Logged_in";
    public static final String other_user="com.example.chatbox.other_user";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        db = new MyDbHandler(Main2Activity.this);

        Intent intent=getIntent();
        final String Logged_in_user= intent.getStringExtra(MainActivity.Logged_in_user);



        ArrayList<String> users= new ArrayList<>();
        listView=findViewById(R.id.listView);

        List<User> allUsers= db.getAllUsers();
        for(User user: allUsers){
            if(!(user.getUser()).equals(Logged_in_user)) {
                users.add(user.getUser());
            }
        }
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, users);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String user_2= ((TextView)view).getText().toString();
                Intent intent=new Intent(Main2Activity.this, MessageActivity.class);
                intent.putExtra(Logged_in, Logged_in_user);
                intent.putExtra(other_user, user_2);
                startActivity(intent);
            }
        });



    }
}
