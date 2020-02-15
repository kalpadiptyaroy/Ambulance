package com.example.ambulance.models;

import android.util.Log;

import com.example.ambulance.Place;

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
    public ArrayList<Place> autocomplete(String input)
    {
        ArrayList<Place> arrayList = new ArrayList<Place>();
        HttpURLConnection connection = null;
        StringBuilder jsonResult = new StringBuilder();
        try
        {
            //https://api.tomtom.com/search/2/nearbySearch/.json?key=ujV2KUVdMGBp556ezFziHtApzPuGoUzq&lat=22.57&lon=88.36&radius=10000&categorySet=7321005,7321004,7321003,7321002,9373002,9373003,7303002,9663005
            //https://api.tomtom.com/search/2/autocomplete/Apollo.json?key=ujV2KUVdMGBp556ezFziHtApzPuGoUzq&language=en-GB
            //https://api.tomtom.com/search/2/poiSearch/apollo.json?key=ujV2KUVdMGBp556ezFziHtApzPuGoUzq&language=en-US&countrySet=IN&lat=22.57&lon=88.36&radius=20000&categorySet=7321005,7321004,7321003,7321002,9373002,9373003,7303002,9663005

            /*StringBuilder sb=new StringBuilder("https://maps.googleapis.com/maps/api/place/findplacefromtext/json?&inputtype=textquery");
            sb.append("&input="+input);
            sb.append("&key=AIzaSyDTkAHYLk8jqeArbE91EzII4aPxlnqEdAU&fields=formatted_address,geometry,icon,name,permanently_closed,photos,place_id,plus_code,types&sessiontoken=12345");
            */

            StringBuilder sb = new StringBuilder("https://api.tomtom.com/search/2/poiSearch/");
            sb.append(input);
            sb.append(".json?key=ujV2KUVdMGBp556ezFziHtApzPuGoUzq&language=en-US&countrySet=IN&lat=22.57&lon=88.36&radius=2000&categorySet=7321005,7321004,7321003,7321002,9373002,9373003,7303002,9663005&limit=10");
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
            JSONArray results=jsonObject.getJSONArray("results");

            //Commented it out bevause there is only on elelemt in candidate json array.
            for(int i=0;i<results.length();i++)
            {
                /*String lat = candidates.getJSONObject(0).getJSONObject("geometry").getJSONObject("location").getString("lat");
                String lng = candidates.getJSONObject(0).getJSONObject("geometry").getJSONObject("location").getString("lng");
                String name = candidates.getJSONObject(0).getString("name");
                 */

                String name = results.getJSONObject(i).getJSONObject("poi").getString("name");
                String lat = results.getJSONObject(i).getJSONObject("position").getString("lat");
                String lng = results.getJSONObject(i).getJSONObject("position").getString("lon");
                Place p = new Place(name, Double.parseDouble(lat), Double.parseDouble(lng));
                arrayList.add(p);
            }
        }

        catch (JSONException e)
        {
            e.printStackTrace();
        }

        return arrayList;

    }
}

