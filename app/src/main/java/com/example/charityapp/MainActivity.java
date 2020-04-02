package com.example.charityapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.charityapp.ViewHolder.NewsViewHolder;
import com.example.charityapp.model.AdminModel;
import com.example.charityapp.model.News;
import com.example.charityapp.model.User;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import io.paperdb.Paper;

public class MainActivity extends AppCompatActivity {



ImageView donateIV ,contactIV,aboutJamIv,newsIV,signout;
 Intent intent;
DatabaseReference reference;
    @Override
 public void onBackPressed()
 {
  this.finishAffinity();
 }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        signout=findViewById(R.id.signoutId);
        donateIV=findViewById(R.id.donateId);
        contactIV=findViewById(R.id.contactId);
        aboutJamIv=findViewById(R.id.aboutId);
        newsIV=findViewById(R.id.newsId);
        Paper.init(this);
        final String userPhonekey= Paper.book().read(Prevalent.UserPhoneKey);
        final String userNameKey= Paper.book().read(Prevalent.UserNameKey);
        final String userPasswordkey= Paper.book().read(Prevalent.UserPasswordKey);
        final String adminPasswordKey= Paper.book().read(Prevalent.AdminPassword);

        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Paper.book().destroy();
                Intent intent=new Intent(MainActivity.this,LoginPage.class);
                startActivity(intent);
            }
        });
        reference=FirebaseDatabase.getInstance().getReference("credentiels");

        newsIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent=new Intent(MainActivity.this,AdminPanel.class);
                if (userNameKey != ""  && userNameKey!=null) {
                    intent.putExtra("useradmin","admin");
                    startActivity(intent);
                     }else{
                    intent.putExtra("useradmin","user");
                    startActivity(intent);
                }
            }
        });
        aboutJamIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent=new Intent(MainActivity.this,jAAMAIYAACTIVITY.class);
                startActivity(intent);
            }
        });
        contactIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent=new Intent(MainActivity.this,ContactUsActivity.class);
                startActivity(intent);
            }
        });
        donateIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                     if(userPasswordkey!="" && userPhonekey!=""){
                       if(!TextUtils.isEmpty(userPhonekey) && !TextUtils.isEmpty(userPasswordkey)){
                        AllowAccess(userPhonekey,userPasswordkey,"Users");
                    }else{
                      intent =new Intent(MainActivity.this,LoginPage.class);
                        startActivity(intent);
                        finish();
                       }
                }
                   if(userNameKey!="" && adminPasswordKey!=""){
                        if  (!TextUtils.isEmpty(userNameKey) && !TextUtils.isEmpty(adminPasswordKey)){
                           AllowAccess(userNameKey,adminPasswordKey,"Admin");
                    }else{
                        intent =new Intent(MainActivity.this, LoginPage.class);
                        startActivity(intent);
                        finish(); }
                        }


                      }
        });



    }


    private void AllowAccess(final String userphone, final String password, final String parent) {
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                     if ((dataSnapshot.child(parent).child(userphone)).exists()) {

                     User userdata = dataSnapshot.child(parent).child(userphone).getValue(User.class);
                     AdminModel admindata = dataSnapshot.child(parent).child(userphone).getValue(AdminModel.class);
                     if(parent.equals("Admin")){
                         if (admindata.getUsername().equals(userphone)){
                             if(admindata.getPassword().equals(password)){
                                 Intent intent=new Intent(getApplicationContext(),AdminPanel.class);
//                                 Bundle b=new Bundle();
//                                 b.putString("useradmin","admin");
                                 intent.putExtra("useradmin","admin");
                                 startActivity(intent);
                             }else{
                                 Toast.makeText(MainActivity.this,"معلومات غير صحيحة",Toast.LENGTH_LONG).show();
                             }
                         }
                     }else{
                         if (userdata.getNumberPhone().equals(userphone)){
                             if(userdata.getPassword().equals(password)){
                                 Intent intent=new Intent(getApplicationContext(),ActivityCategory.class);
                                 startActivity(intent);
                             }else{
                                 Toast.makeText(MainActivity.this,"معلومات غير صحيحة",Toast.LENGTH_LONG).show();
                             }
                         }

                     }

                        }





                    }




            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(MainActivity.this, " on cancelled ", Toast.LENGTH_SHORT).show();

            }
        });


    }}
