package com.example.charityapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
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

import io.paperdb.Paper;

public class LoginPage extends AppCompatActivity {
    TextView createCompteTv,textadmintv,textUsertv,textLikeadmintv;
    EditText numberEt,usernameEt;
    EditText passwordEt;
    ImageView enterIv;
    private DatabaseReference reference;
    private String number;
    private String username;
    private String password;
   String  parentDbName="Users";
    String parentChildName;



    public void onBackPressed()
    {
        this.finishAffinity();
        super.onBackPressed();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        createCompteTv=findViewById(R.id.RegisterPageTV);
        textUsertv=findViewById(R.id.textUserId);
        textLikeadmintv=findViewById(R.id.likeAdminId);
        enterIv=findViewById(R.id.enterId);
        numberEt=findViewById(R.id.mobileLoginId);
        usernameEt=findViewById(R.id.userLoginId);
        passwordEt=findViewById(R.id.passwordLoginId);
        textadmintv=findViewById(R.id.adminTextId);

        textadmintv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textadmintv.setVisibility(View.GONE);
                textUsertv.setVisibility(View.VISIBLE);
                textLikeadmintv.setVisibility(View.VISIBLE);
                usernameEt.setVisibility(View.VISIBLE);
                numberEt.setVisibility(View.GONE);
                parentDbName="Admin";
                parentChildName=username;
            }
        });
        textUsertv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textadmintv.setVisibility(View.VISIBLE);
                textUsertv.setVisibility(View.GONE);
                textLikeadmintv.setVisibility(View.GONE);
                usernameEt.setVisibility(View.GONE);
                numberEt.setVisibility(View.VISIBLE);
                parentDbName="Users";
                parentChildName=number;

            }
        });

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
        Paper.init(this);
    }
        public void loginBtn(){

         number =numberEt.getText().toString().trim();
         password =passwordEt.getText().toString().trim();
         username=usernameEt.getText().toString().trim();

        if (password.isEmpty()) {
            passwordEt.setError(getString(R.string.input_error_name));
            passwordEt.requestFocus();
            return;
        }
        if(numberEt.getVisibility()==View.VISIBLE){
            System.out.println("number visibilty");
            System.out.println("number "+number);
//            Paper.book().write(Prevalent.UserPhoneKey,number);
//            Paper.book().write(Prevalent.UserPasswordKey,password);
            parentChildName=number;
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
        }
            if(usernameEt.getVisibility()== View.VISIBLE){


            System.out.println("username visibilty");
            parentChildName=username;
            if (username.isEmpty()) {
                usernameEt.setError(getString(R.string.input_error_name));
                usernameEt.requestFocus();
                return;
            }

        }

    reference.child(parentDbName).child(parentChildName).addListenerForSingleValueEvent(valueEventListener);

        }

        ValueEventListener valueEventListener=new ValueEventListener() {
            @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            if (dataSnapshot.exists()){
                String pass=dataSnapshot.child("password").getValue(String.class);

                if(pass.equals(password)){
                    if(usernameEt.getVisibility()==View.VISIBLE){

                        Paper.book().write(Prevalent.UserNameKey,username);
                        Paper.book().write(Prevalent.AdminPassword,password);
                        Toast.makeText(LoginPage.this,"انت الان أدمين لديك كل الصلاحيات",Toast.LENGTH_LONG).show();
                        Intent intent=new Intent(getApplicationContext(),AdminPanel.class);
                            intent.putExtra("useradmin","admin");
                             startActivity(intent);
                    }else{
                        Paper.book().write(Prevalent.UserPhoneKey,number);
                        Paper.book().write(Prevalent.UserPasswordKey,password);
                        Intent intent=new Intent(getApplicationContext(),ActivityCategory.class);
                        intent.putExtra("useradmin","user");
                        startActivity(intent); }
                }else {
                       Toast.makeText(LoginPage.this, "كلمة السر خاطئة", Toast.LENGTH_SHORT).show();
                      }
            }else{
                Toast.makeText(LoginPage.this, "هذا الحساب غير موجود", Toast.LENGTH_SHORT).show();   }
        }
        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {
            Toast.makeText(LoginPage.this, " تم الالغاء", Toast.LENGTH_SHORT).show();
        }
    };

}
