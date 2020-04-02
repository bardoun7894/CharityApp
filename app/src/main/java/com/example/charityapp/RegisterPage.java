package com.example.charityapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.charityapp.model.User;
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

import java.util.HashMap;

public class RegisterPage extends AppCompatActivity {
    EditText nameEt,emailEt,passwordEt,rePasswordEt,numberPhoneEt;

    ImageView registerbtn;
    DatabaseReference reference;
    private String email,password,rePassword,numberPhone,name;
    private int id;


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

private void registerUser(){

      name =  nameEt.getText().toString().trim();
      email  =   emailEt.getText().toString().trim();
       password =  passwordEt.getText().toString().trim();
      rePassword = rePasswordEt.getText().toString().trim();
        numberPhone=numberPhoneEt.getText().toString().trim();
    if (name.isEmpty()) {
        nameEt.setError(getString(R.string.input_error_name));
        nameEt.requestFocus();
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

     validateNumberPhone();
}

public void validateNumberPhone(){

    reference.addListenerForSingleValueEvent(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            if (!(dataSnapshot.child("Users").child(numberPhone)).exists()) {

                HashMap<String,Object> userDataMap =new HashMap<>();
                userDataMap.put("numberPhone",numberPhone);
                userDataMap.put("name",name);
                userDataMap.put("password",password);
                userDataMap.put("email",email);
reference.child("Users").child(numberPhone).updateChildren(userDataMap).addOnCompleteListener(new OnCompleteListener<Void>() {
    @Override
    public void onComplete(@NonNull Task<Void> task) {
        if (task.isSuccessful()){
            Toast.makeText(RegisterPage.this, "تم التسجيل بنجاح ", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(RegisterPage.this, "مشكل في الشبكة", Toast.LENGTH_SHORT).show();
        }
    }
});
            }else{
                numberPhoneEt.setError(" جرب رقم اخر هذا الرقم محجوز");
                numberPhoneEt.requestFocus();
                }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {
            Toast.makeText(RegisterPage.this, " on cancelled ", Toast.LENGTH_SHORT).show();

        }
    });


}

}
