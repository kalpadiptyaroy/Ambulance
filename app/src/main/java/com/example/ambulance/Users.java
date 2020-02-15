package com.example.ambulance;

public class Users
{
    public String name,email,password,phone, latitude ,longitude;

    public Users(String name, String email, String password, String phone)
    {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone=phone;
        this.latitude="";
        this.longitude="";
    }

    public Users(String latitude, String longitude)
    {
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
