package com.example.ambulance;

public class Drivers {
    public String name,email,password,phone, latitude, longitude;

    public Drivers(String name, String email, String password, String phone)
    {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone=phone;
        this.latitude = "";
        this.longitude = "";
    }

    public Drivers(String latitude, String longitude)
    {
        this.latitude = latitude;
        this.longitude = longitude;
    }
}

