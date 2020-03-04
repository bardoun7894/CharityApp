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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginPage extends AppCompatActivity {
    TextView createCompteTv;
    EditText numberEt;
    EditText passwordEt;
    ImageView enterIv;
    private FirebaseAuth mauth;
    private DatabaseReference reference;
    private String number;
    private String password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        createCompteTv=findViewById(R.id.RegisterPageTV);
        enterIv=findViewById(R.id.enterId);
        numberEt=findViewById(R.id.mobileLoginId);
        passwordEt=findViewById(R.id.passwordLoginId);

        reference=FirebaseDatabase.getInstance().getReference("credentiels");
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
loginBtn();

            }
        });
    }

    public void loginBtn(){
        number =numberEt.getText().toString().trim();
          password =passwordEt.getText().toString().trim();

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
    reference.child("Users").child(number).addListenerForSingleValueEvent(valueEventListener);

    }
        ValueEventListener valueEventListener=new ValueEventListener() {
            @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            if (dataSnapshot.exists()){
                String pass=dataSnapshot.child("password").getValue(String.class);
                System.out.println(pass+" :  mobileNum");
                if(pass.equals(password)){
                    Intent intent=new Intent(getApplicationContext(),ActivityCategory.class);
                    startActivity(intent);
             //       passwordEt.setError("هذا الرقم محجوز مسبقا");
              //      passwordEt.requestFocus();
                }else
                {   Toast.makeText(LoginPage.this, "password not equal", Toast.LENGTH_SHORT).show(); }
            }else{
                Toast.makeText(LoginPage.this, "data not exist", Toast.LENGTH_SHORT).show();   }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {
            Toast.makeText(LoginPage.this, " on cancelled ", Toast.LENGTH_SHORT).show();
        }
    };

}
