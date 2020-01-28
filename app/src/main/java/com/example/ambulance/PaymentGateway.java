package com.example.ambulance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class PaymentGateway extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_gateway);
    }

    public void goToRideConfirm(View view)
    {
        Intent intent = new Intent(PaymentGateway.this, RideConfirmActivity.class);
        startActivity(intent);
    }
}
