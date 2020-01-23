package com.example.ambulance.models;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class PlacesApi
{
    public ArrayList<String> autocomplete(String input)
    {
        ArrayList<String> arrayList = new ArrayList<String>();
        HttpURLConnection connection = null;
        StringBuilder jsonResult = new StringBuilder();
        try
        {
            //https://api.tomtom.com/search/2/nearbySearch/.json?key=ujV2KUVdMGBp556ezFziHtApzPuGoUzq&lat=22.57&lon=88.36&radius=10000&categorySet=7321005,7321004,7321003,7321002,9373002,9373003,7303002,9663005
            //https://api.tomtom.com/search/2/autocomplete/Apollo.json?key=ujV2KUVdMGBp556ezFziHtApzPuGoUzq&language=en-GB
            //https://api.tomtom.com/search/2/poiSearch/apollo.json?key=ujV2KUVdMGBp556ezFziHtApzPuGoUzq&language=en-US&countrySet=IN&lat=22.57&lon=88.36&radius=20000&categorySet=7321005,7321004,7321003,7321002,9373002,9373003,7303002,9663005

            StringBuilder sb=new StringBuilder("https://maps.googleapis.com/maps/api/place/findplacefromtext/json?&inputtype=textquery");
            sb.append("&input="+input);
            sb.append("&key=AIzaSyBt6dqua_Hr_AhCk0gJm9Kxh5X6DJBLYz8&fields=formatted_address,geometry,icon,name,permanently_closed,photos,place_id,plus_code,types");
            URL url=new URL(sb.toString());
            connection=(HttpURLConnection)url.openConnection();
            InputStreamReader inputStreamReader=new InputStreamReader(connection.getInputStream());

            int read;
            char[] buff=new char[1024];
            while ((read=inputStreamReader.read(buff))!=-1)
            {
                jsonResult.append(buff,0,read);
            }
        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
        }

        catch (IOException e)
        {
            e.printStackTrace();
        }

        finally
        {
            if(connection!=null)
            {
                connection.disconnect();
            }
        }

        try
        {
            JSONObject jsonObject=new JSONObject(jsonResult.toString());
            JSONArray prediction=jsonObject.getJSONArray("candidates");
            for(int i=0;i<prediction.length();i++)
            {
                arrayList.add(prediction.getJSONObject(i).getString("name"));
            }
        }

        catch (JSONException e)
        {
            e.printStackTrace();
        }

        return arrayList;

    }
}

