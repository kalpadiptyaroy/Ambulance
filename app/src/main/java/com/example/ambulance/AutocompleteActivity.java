package com.example.ambulance;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.AutoCompleteTextView;

import com.example.ambulance.adapter.PlaceAutoSuggestionAdapter;

public class AutocompleteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autocomplete2);

        AutoCompleteTextView autoCompleteTextView = findViewById(R.id.autocomplete);
        autoCompleteTextView.setAdapter(new PlaceAutoSuggestionAdapter(AutocompleteActivity.this, android.R.layout.simple_list_item_1));
    }
}
