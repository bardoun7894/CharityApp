package com.example.charityapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import io.paperdb.Paper;

public class MainActivity extends AppCompatActivity {
ImageView donateIV ;
DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        donateIV=findViewById(R.id.donateId);

        reference=FirebaseDatabase.getInstance().getReference("credentiels");
        Paper.init(this);

        donateIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String userPhonekey= Paper.book().read(Prevalent.UserPhoneKey);
                final String userPasswordkey= Paper.book().read(Prevalent.UserPasswordKey);
                if(userPasswordkey!="" && userPhonekey!=""){
                    if(!TextUtils.isEmpty(userPhonekey) && !TextUtils.isEmpty(userPasswordkey)){
                        AllowAccess(userPhonekey,userPasswordkey);
                    }else{
                        Intent intent =new Intent(MainActivity.this,LoginPage.class);
                        startActivity(intent);
                        finish();
                       }
                }else{
                    Toast.makeText(MainActivity.this, "user password null", Toast.LENGTH_SHORT).show();
                }

            }
        });



    }


    private void AllowAccess(final String numberPhone, final String password) {
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if ((dataSnapshot.child("Users").child(numberPhone)).exists()) {
                    User userdata = dataSnapshot.child("Users").child(numberPhone).getValue(User.class);
if (userdata.getNumberPhone().equals(numberPhone)){

    if(userdata.getPassword().equals(password)){
        Intent intent=new Intent(getApplicationContext(),ActivityCategory.class);
            startActivity(intent);
        Toast.makeText(MainActivity.this, "exist", Toast.LENGTH_SHORT).show();

    }else{
        Toast.makeText(MainActivity.this,"not",Toast.LENGTH_LONG).show();
    }
}
//                    String pass=dataSnapshot.child("password").getValue(String.class);
//                    if(pass.equals(password)){
//
//                    }else{
//                        Toast.makeText(MainActivity.this, "pass not equal", Toast.LENGTH_SHORT).show();
//                    }
//                }else{
//                    Toast.makeText(MainActivity.this, "datasnasp shot nooooo", Toast.LENGTH_SHORT).show();
//                }
                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(MainActivity.this, " on cancelled ", Toast.LENGTH_SHORT).show();

            }
        });


    }}
