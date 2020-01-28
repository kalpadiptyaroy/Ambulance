package com.example.ambulance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import com.example.ambulance.adapter.PlaceAutoSuggestionAdapter;

public class AutocompleteActivity extends AppCompatActivity
{
    private Place place;
    private PlaceAutoSuggestionAdapter adapter;
    private Intent intent = new Intent();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autocomplete2);

        AutoCompleteTextView autoCompleteTextView = findViewById(R.id.autocomplete);
        Button button = findViewById(R.id.confirmbtn);
        autoCompleteTextView.setAdapter(new PlaceAutoSuggestionAdapter(getApplicationContext(), android.R.layout.simple_list_item_1));

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                adapter = (PlaceAutoSuggestionAdapter) autoCompleteTextView.getAdapter();
                place = adapter.results.get(0);
                Bundle bundle = new Bundle();
                bundle.putString("name", place.getName());
                bundle.putString("latitude", place.getLat() + "");
                bundle.putString("longitude", place.getLng() + "");
                intent.putExtra("place", bundle);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

}
