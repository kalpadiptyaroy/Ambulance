package com.example.ambulance;

public class Place
{
    String name;
    double lat, lng;

    public Place()
    {
        name = "";
        lat = 0.0d;
        lng = 0.0d;
    }

    public Place(String name, double lat, double lng)
    {
        this.name = name;
        this.lat = lat;
        this.lng = lng;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setLatLng(double lat, double lng)
    {
        this.lat = lat;
        this.lng = lng;
    }

    public String getName()
    {
        return name;
    }

    public double getLat()
    {
        return lat;
    }

    public double getLng()
    {
        return lng;
    }

    public String toString()
    {
        return name;
    }
}
