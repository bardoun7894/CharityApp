package com.example.charityapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegisterPage extends AppCompatActivity {
    EditText nameEt,emailEt,passwordEt,rePasswordEt,numberPhoneEt;
    private FirebaseAuth mauth;
    ImageView registerbtn;
    DatabaseReference reference;
    private String email;
    private String password;
    private String rePassword;
    private String numberPhone;
    public boolean found=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);
        nameEt=findViewById(R.id.nameId);
        emailEt=findViewById(R.id.emailId);
        passwordEt=findViewById(R.id.passwordLoginId);
        rePasswordEt=findViewById(R.id.rePasswordId);
        numberPhoneEt=findViewById(R.id.phoneId);
reference=FirebaseDatabase.getInstance().getReference("credentiels");
       // mauth=FirebaseAuth.getInstance();
        registerbtn=findViewById(R.id.registerButton) ;
        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
    }
//    ValueEventListener valueEventListener=new ValueEventListener() {
//        @Override
//        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//            if (dataSnapshot.exists()){
//                String numbe=dataSnapshot.child("numberPhone").getValue(String.class);
//                System.out.println(numbe+" :  mobileNum");
//              //  assert numbe != null;
//                if(numbe.equals(numberPhone)){
//                    Toast.makeText(RegisterPage.this, "number equal", Toast.LENGTH_SHORT).show();
//                             found = true ;
//                              //   passwordEt.setError("هذا الرقم محجوز مسبقا");
//                              //   passwordEt.requestFocus();
//                               }else {
//                             found =false ;
//                             Toast.makeText(RegisterPage.this, "number not equal", Toast.LENGTH_SHORT).show();
//                              }
//            }else {   Toast.makeText(RegisterPage.this, "data not exist", Toast.LENGTH_SHORT).show(); }
//                  }
//
//        @Override
//        public void onCancelled(@NonNull DatabaseError databaseError) {
//            Toast.makeText(RegisterPage.this, " on cancelled ", Toast.LENGTH_SHORT).show();
//        }
//    };
private void registerUser(){

    String name =  nameEt.getText().toString().trim();
        email  =   emailEt.getText().toString().trim();
       password =  passwordEt.getText().toString().trim();
      rePassword = rePasswordEt.getText().toString().trim();
        numberPhone=numberPhoneEt.getText().toString().trim();
    if (name.isEmpty()) {
        nameEt.setError(getString(R.string.input_error_name));
        nameEt.requestFocus();
        return;
    }

    if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
        emailEt.setError(getString(R.string.input_error_email_invalid));
        emailEt.requestFocus();
        return;
    }
    if (password.isEmpty()) {
        passwordEt.setError(getString(R.string.input_error_password));
        passwordEt.requestFocus();
        return;
    }
    if (!password.equals(rePassword)) {
        passwordEt.setError(getString(R.string.not_equals));
        rePasswordEt.setError(getString(R.string.not_equals));
        passwordEt.requestFocus();
        return;
    }

    if (password.length() < 6) {
        passwordEt.setError(getString(R.string.input_error_password_length));
        passwordEt.requestFocus();
        return;
    }

    if (numberPhone.isEmpty()) {
        numberPhoneEt.setError(getString(R.string.input_error_phone));
        numberPhoneEt.requestFocus();
        return;
    }


    if (numberPhone.length() != 10) {
        numberPhoneEt.setError(getString(R.string.input_error_phone_invalid));
        numberPhoneEt.requestFocus();
        return;
    }

    User user =new User(name,email,password,numberPhone);
  //  reference.child("Users").child(numberPhone).addListenerForSingleValueEvent(valueEventListener);
//    if( found==true ){
//    // numberPhoneEt.setBackgroundColor(getResources().getColor(R.color.lemonColor));
//        numberPhoneEt.requestFocus();
//        return;
//    }else{
//  //      numberPhoneEt.setError("الرقم غير موجود");
//        numberPhoneEt.requestFocus();
//    }
    reference.child("Users").child(numberPhone).setValue(user).addOnFailureListener(new OnFailureListener() {
        @Override
        public void onFailure(@NonNull Exception e) {
    Toast.makeText(RegisterPage.this, "لقد بأت العملية بالفشل ", Toast.LENGTH_SHORT).show();
        }
    }).addOnSuccessListener(new OnSuccessListener<Void>() {
        @Override
        public void onSuccess(Void aVoid) {
            Intent intent=new Intent(getApplicationContext(),ActivityCategory.class);
            startActivity(intent);
    Toast.makeText(RegisterPage.this, " تم التسجيل بنجاح ", Toast.LENGTH_SHORT).show();
        }
    });





}




}
