package com.example.chatbox;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.chatbox.data.MyDbHandler;
import com.example.chatbox.model.Message;

import java.util.ArrayList;
import java.util.List;

public class MessageActivity extends AppCompatActivity {
    ListView messageList;
    EditText message;
    Button send;
    MyDbHandler db= new MyDbHandler(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        Intent intent=getIntent();
        final String user_1= intent.getStringExtra(Main2Activity.Logged_in);
        final String user_2= intent.getStringExtra(Main2Activity.other_user);

        message=findViewById(R.id.message);

        send=findViewById(R.id.send);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Message=message.getText().toString();
                insert(Message, user_1, user_2);
                renew(user_1, user_2);
                message.setText("");
            }
        });

        ArrayList<String> msgs= new ArrayList<>();
        messageList=findViewById(R.id.listMessage);

        List<Message> allMsgs= db.getAllMsgs(user_1, user_2);
        for(Message message: allMsgs){
            msgs.add(message.getMsg());
        }
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, msgs);
        messageList.setAdapter(arrayAdapter);




    }
    private void insert(String msg, String user_1, String user_2 ){
        if(!(msg.equals(""))){
            String msgFinal=user_1+": "+msg;
            Message message= new Message();
            message.setUser1(user_1);
            message.setUser2(user_2);
            message.setMsg(msgFinal);
            db.addMsg(message);
            Message message1= new Message();
            message1.setUser1(user_2);
            message1.setUser2(user_1);
            message1.setMsg(msgFinal);
            db.addMsg(message1);


        }

    }

    private void renew(String user_1, String user_2){
        ArrayList<String> msgs= new ArrayList<>();
        messageList=findViewById(R.id.listMessage);

        List<Message> allMsgs= db.getAllMsgs(user_1, user_2);
        for(Message message: allMsgs){
            msgs.add(message.getMsg());
        }
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, msgs);
        messageList.setAdapter(arrayAdapter);
        arrayAdapter.notifyDataSetChanged();

    }
}
