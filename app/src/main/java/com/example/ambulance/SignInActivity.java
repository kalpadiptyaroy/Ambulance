package com.example.ambulance;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.app.ProgressDialog;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignInActivity extends AppCompatActivity
{

    EditText e1,e2;
    FirebaseAuth auth;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        e1=findViewById(R.id.email_in);
        e2=findViewById(R.id.password_in);
        auth=FirebaseAuth.getInstance();
        dialog = new  ProgressDialog(this);
    }

    public void signinUser(View V)
    {
        dialog.setMessage("Signing in... Please wait");
        dialog.show();
        if(e1.getText().toString().equals("")&& e2.getText().toString().equals(""))
        {
            Toast.makeText(getApplicationContext(),"Field cannot be empty!",Toast.LENGTH_SHORT).show();
        }
        else
        {
            auth.signInWithEmailAndPassword(e1.getText().toString(), e2.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>()
            {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task)
                {
                    if(task.isSuccessful())
                    {

                        dialog.hide();
                        Toast.makeText(getApplicationContext(),"User Successfully sign in!",Toast.LENGTH_SHORT).show();
                        Intent i=new Intent(SignInActivity.this,MainPageActivity.class);
                        startActivity(i);
                        finish();
                    }
                    else
                    {
                        dialog.hide();
                        Toast.makeText(getApplicationContext(), "User not found", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    public void signUpUser(View v)
    {
        Intent intent = new Intent(SignInActivity.this, SignUpActivity.class);
        startActivity(intent);
        finish();
    }
}
