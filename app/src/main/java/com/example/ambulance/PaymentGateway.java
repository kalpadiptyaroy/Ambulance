package com.example.ambulance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class PaymentGateway extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_gateway);

        TextView rate = findViewById(R.id.rate_text);
        TextView distance = findViewById(R.id.distance);
        TextView amount = findViewById(R.id.amountToPay);

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("ride");

        rate.setText("Fare : Rs. 9.00 per KM");
        double pickup_lat = Double.parseDouble(bundle.getString("pickup_latitude"));
        double pickup_lon = Double.parseDouble(bundle.getString("pickup_longitude"));
        double destination_lat = Double.parseDouble(bundle.getString("destination_latitude"));
        double destination_lon = Double.parseDouble(bundle.getString("destination_longitude"));

        distance.setText("Distance : " + ((int)(calculateDistance(pickup_lat, pickup_lon, destination_lat, destination_lon) * 100) * 1.0 / 100) + " KM");
        amount.setText("Amount Payable : Rs. " + 9 * ((int)(calculateDistance(pickup_lat, pickup_lon, destination_lat, destination_lon) * 100) * 1.0 / 100));
    }

    public void goToRideConfirm(View view)
    {
        Intent intent = new Intent(PaymentGateway.this, RideConfirmActivity.class);
        startActivity(intent);
    }

    public double calculateDistance(double lat1, double lon1, double lat2, double lon2)
    {
        double phi1 = lat1 * Math.PI / 180;
        double phi2 = lat2 * Math.PI / 180;
        double d_phi = (lat2 - lat1) * Math.PI / 180;
        double d_lambda = (lon2 - lon1) * Math.PI / 180;
        double a = Math.sin(d_phi / 2) * Math.sin(d_phi / 2) + Math.cos(phi1) * Math.cos(phi2) * Math.sin(d_lambda / 2) * Math.sin(d_lambda / 2);
        return (6371 * 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a)));
    }
}
