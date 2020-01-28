package com.example.ambulance.adapter;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;

import com.example.ambulance.AutocompleteActivity;
import com.example.ambulance.Place;
import com.example.ambulance.models.PlacesApi;
import java.util.ArrayList;

public class PlaceAutoSuggestionAdapter extends ArrayAdapter<Place> implements Filterable
{
    public ArrayList<Place> results;
    Context context;
    PlacesApi placesApi = new PlacesApi();
    int resourse;
    public Place place;

    public PlaceAutoSuggestionAdapter(Context context, int resId)
    {
        super(context, resId);
        this.context = context;
        this.resourse = resId;
    }

    @Override
    public int getCount()
    {
        return results.size();
    }

    @Override
    public Place getItem(int pos)
    {
        return results.get(pos);
    }


    public ArrayList<String> getStringList(ArrayList<Place> t)
    {
        ArrayList<String> list = new ArrayList<String>();

        for(int i = 0; i < results.size(); i++)
            list.add(results.get(i).toString());
        return list;
    }


    @Override
    public Filter getFilter()
    {
        Filter filter = new Filter()
        {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults filterResults = new FilterResults();
                if(constraint != null)
                {
                    results = placesApi.autocomplete(constraint.toString());
                    filterResults.values = getStringList(results);
                    filterResults.count = results.size();
                }
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results)
            {
                if(results != null && results.count > 0)
                    notifyDataSetChanged();
                else
                    notifyDataSetInvalidated();
            }
        };
        return filter;
    }
}
