package com.magicmed.data;

/**
 * Created by bin on 5/9/15.
 */
public class User {
    private int _user_id;

    public User(){
        _user_id = 1;
    }

    public int get_user_id() { return _user_id;}
    public void set_user_id(int id) {  _user_id = id;}
}
