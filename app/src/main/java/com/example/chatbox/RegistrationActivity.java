package com.example.chatbox;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chatbox.data.MyDbHandler;
import com.example.chatbox.model.User;

public class RegistrationActivity extends AppCompatActivity {
    private TextView msg;
    private EditText name;
    private EditText email;
    private EditText user;
    private EditText pass1;
    private EditText pass2;
    private Button signup;
    MyDbHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        Intent intent= getIntent();
        db= new MyDbHandler(RegistrationActivity.this);


        name=(EditText)findViewById(R.id.name);
        email=(EditText)findViewById(R.id.email);
        user=(EditText)findViewById(R.id.user_name);
        pass1=(EditText)findViewById(R.id.pass1);
        pass2=(EditText)findViewById(R.id.pass2);
        signup=(Button) findViewById(R.id.signup);


       signup.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               insert();
               name.setText("");
               email.setText("");
               user.setText("");
               pass1.setText("");
               pass2.setText("");
           }
       });






    }
    private void insert(){
        if(validate()){
            //uploading data to the database here
            User newuser = new User();
            newuser.setName(name.getText().toString());
            newuser.setEmail(email.getText().toString());
            newuser.setUser(user.getText().toString());
            newuser.setPassword(pass1.getText().toString());
            db.addUser(newuser);
            Toast.makeText(this, "Registered Successfully", Toast.LENGTH_SHORT).show();

        }
    }

    private Boolean validate(){
        Boolean validation= false;
        String Name= name.getText().toString();
        String Email= email.getText().toString();
        String User= user.getText().toString();
        String Pass1= pass1.getText().toString();
        String Pass2= pass2.getText().toString();
        Boolean ans=db.sameUser(User);

        if(Name.isEmpty() || Email.isEmpty() || User.isEmpty() || Pass1.isEmpty() || Pass2.isEmpty()){
            Toast.makeText(this, "Please fill all the details", Toast.LENGTH_SHORT).show();
        }
        else if(!(Pass1.equals(Pass2))){
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
        }
        else if(ans==true) {
            Toast.makeText(this, "A user with this username already exists. Please choose another username", Toast.LENGTH_SHORT).show();
        }
        else{
            validation = true;
        }

        return validation;

    }

}
