package com.example.chatbox.params;

public class Params {
    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "users_db";
    public static final String TABLE_NAME = "users_table";


    //keys of our table in database
    public static final String KEY_USER_NAME="user";
    public static final String KEY_NAME="name";
    public static final String KEY_EMAIL="email";
    public static final String KEY_PASSWORD="password";

    //creating another table to store msgs
    public static final String TABLE_MSG = "users_msg";
    public static final String KEY_ID = "id";
    public static final String KEY_USER1="user1";
    public static final String KEY_USER2="user2";
    public static final String KEY_MSG="message";

}
