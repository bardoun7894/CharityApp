package com.example.charityapp;

public class User {
   public String  fullname;
    public String email;
public String     password;
    public String numberPhone;

    public User(){

    }
    public User(String fullname, String email,String  password, String numberPhone) {
        this.fullname = fullname;
        this.email = email;
       this.password=password;
        this.numberPhone = numberPhone;
    }
}
