package com.example.chatbox;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chatbox.data.MyDbHandler;
import com.example.chatbox.params.Params;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    EditText username;
    EditText password;
    Button Submit;
    MyDbHandler db;
    Cursor cursor;
    public static final String Logged_in_user="com.example.chatbox.Logged_in_user";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void signup(View View){
        Intent intent = new Intent(MainActivity.this, RegistrationActivity.class);
        startActivity(intent);
    }

    public void login(View view){
        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
        String user=username.getText().toString();
        String pass=password.getText().toString();
        username.setText("");
        password.setText("");
        db = new MyDbHandler(this);
        Boolean ans = db.userValidation(user, pass);
        if(ans == true) {
            Intent intent = new Intent(MainActivity.this, Main2Activity.class);
            intent.putExtra(Logged_in_user, user);
            startActivity(intent);
        }
        else {
            Toast.makeText(this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
        }


    }

}
