package com.example.ambulance;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Profile extends AppCompatActivity
{

    FirebaseAuth auth;
    FirebaseUser user;
    String user_id;
    DatabaseReference reference;
    public TextView t1,t2,t3,t4;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        auth = FirebaseAuth.getInstance();
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        user = FirebaseAuth.getInstance().getCurrentUser();
        user_id = user.getUid();
        reference = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id);
        reference.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                String name = dataSnapshot.child("name").getValue(String.class);
                String email = dataSnapshot.child("email").getValue(String.class);
                String password = dataSnapshot.child("password").getValue(String.class);
                String phone = dataSnapshot.child("phone").getValue(String.class);
                t1 = findViewById(R.id.name_user);
                t2 = findViewById(R.id.email_user);
                t3 = findViewById(R.id.password_user);
                t4 = findViewById(R.id.phone_user);
                t1.setText(name);
                t2.setText(email);
                t3.setText(password);
                t4.setText(phone);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError)
            {

            }
        });
    }

    public void open_home(View v)
    {
        Intent home=new Intent(Profile.this,MainPageActivity.class);
        startActivity(home);
        finish();
    }
}
