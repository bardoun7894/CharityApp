package com.example.charityapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginPage extends AppCompatActivity {
    TextView createCompteTv;
    EditText numberEt;
    EditText passwordEt;
    ImageView enterIv;
    private FirebaseAuth mauth;
    FirebaseAuth.AuthStateListener mAuthStateListener;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        createCompteTv=findViewById(R.id.RegisterPageTV);
        enterIv=findViewById(R.id.enterId);
        numberEt=findViewById(R.id.mobileLoginId);
        passwordEt=findViewById(R.id.passwordLoginId);
        mAuthStateListener=new FirebaseAuth.AuthStateListener() {
            FirebaseUser userFirebase =mauth.getCurrentUser();
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
               if(userFirebase!=null){
            Toast.makeText(LoginPage.this, getString(R.string.registration_success), Toast.LENGTH_LONG).show();
               }else{
             Toast.makeText(LoginPage.this, getString(R.string.registration_success), Toast.LENGTH_LONG).show();
               }
            }
        };
        createCompteTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),RegisterPage.class);
                startActivity(intent);
            }
        });
        enterIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(getApplicationContext(),ActivityCategory.class);
                startActivity(intent);
            }
        });
    }

    public void loginBtn(){
      String number =numberEt.getText().toString().trim();
        String password =passwordEt.getText().toString().trim();

        if (password.isEmpty()) {
            passwordEt.setError(getString(R.string.input_error_name));
            passwordEt.requestFocus();
            return;
        }
        if (number.isEmpty()) {
            numberEt.setError(getString(R.string.input_error_name));
            numberEt.requestFocus();
            return;
        }
        if (number.length() != 10) {
            numberEt.setError(getString(R.string.input_error_phone_invalid));
            numberEt.requestFocus();
            return;
        }
     reference=FirebaseDatabase.getInstance().getReference("Users");


    }
}
