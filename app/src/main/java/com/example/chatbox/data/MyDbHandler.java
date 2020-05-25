package com.example.chatbox.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.chatbox.model.Message;
import com.example.chatbox.model.User;
import com.example.chatbox.params.Params;

import java.util.ArrayList;
import java.util.List;

public class MyDbHandler extends SQLiteOpenHelper {
    public MyDbHandler( Context context) {
        super(context, Params.DB_NAME, null, Params.DB_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create1= "CREATE TABLE "+ Params.TABLE_NAME+"("+Params.KEY_USER_NAME+" TEXT PRIMARY KEY, "+
                Params.KEY_NAME+" TEXT, "+Params.KEY_EMAIL+" TEXT, "+ Params.KEY_PASSWORD+" TEXT"+")";
        db.execSQL(create1);
        String create2= "CREATE TABLE "+ Params.TABLE_MSG+"("+Params.KEY_ID+" INTEGER PRIMARY KEY, "+
                Params.KEY_USER1+" TEXT, "+Params.KEY_USER2+" TEXT, "+ Params.KEY_MSG+" TEXT"+")";
        db.execSQL(create2);


    }
    public void addUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Params.KEY_USER_NAME, user.getUser());
        values.put(Params.KEY_NAME, user.getName());
        values.put(Params.KEY_EMAIL, user.getEmail());
        values.put(Params.KEY_PASSWORD, user.getPassword());

        db.insert(Params.TABLE_NAME, null, values);
        db.close();
    }
    public List<User> getAllUsers(){
        List<User> userList = new ArrayList<User>();
        SQLiteDatabase db= this.getReadableDatabase();
        String select = "SELECT * FROM "+ Params.TABLE_NAME;
        Cursor cursor = db.rawQuery(select, null);
        if(cursor.moveToFirst()){
            do{
                User user = new User();
                user.setUser(cursor.getString(cursor.getColumnIndex(Params.KEY_USER_NAME)));
                user.setName(cursor.getString(cursor.getColumnIndex(Params.KEY_NAME)));
                user.setEmail(cursor.getString(cursor.getColumnIndex(Params.KEY_EMAIL)));
                user.setPassword(cursor.getString(cursor.getColumnIndex(Params.KEY_PASSWORD)));
                userList.add(user);
            }while(cursor.moveToNext());
        }
        return userList;
    }
    public int getCount(){
        String query= "SELECT * FROM "+ Params.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor= db.rawQuery(query, null);
        return cursor.getCount();
    }

    public boolean userValidation(String username, String password ){
        String query="SELECT * FROM "+ Params.TABLE_NAME+" WHERE "+ Params.KEY_USER_NAME+"=? AND "+
                Params.KEY_PASSWORD+ "=?";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor= db.rawQuery(query, new String[]{username, password});
        if(cursor.getCount()>0){
            return true;
        }
        else{
            return false;
        }
    }
    public void addMsg(Message message){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Params.KEY_USER1, message.getUser1());
        values.put(Params.KEY_USER2, message.getUser2());
        values.put(Params.KEY_MSG, message.getMsg());
        db.insert(Params.TABLE_MSG, null, values);
        db.close();
    }

    public List<Message> getAllMsgs(String user_1, String user_2){
        List<Message> message_list = new ArrayList<>();
        SQLiteDatabase db= this.getReadableDatabase();
        String select = "SELECT * FROM "+ Params.TABLE_MSG+ " WHERE "+ Params.KEY_USER1+"=? AND "
                         + Params.KEY_USER2+"=?";
        Cursor cursor = db.rawQuery(select, new String[]{user_1,user_2});
        if(cursor.moveToFirst()){
            do{
                Message message = new Message();
                message.setUser1(cursor.getString(cursor.getColumnIndex(Params.KEY_USER1)));
                message.setUser2(cursor.getString(cursor.getColumnIndex(Params.KEY_USER2)));
                message.setMsg(cursor.getString(cursor.getColumnIndex(Params.KEY_MSG)));
                message_list.add(message);
            }while(cursor.moveToNext());
        }
        return message_list;
    }
    public boolean sameUser(String username ){
        String query="SELECT * FROM "+ Params.TABLE_NAME+" WHERE "+ Params.KEY_USER_NAME+ "=?";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor= db.rawQuery(query, new String[]{username});
        if(cursor.getCount()>0){
            return true;
        }
        else{
            return false;
        }
    }
}

