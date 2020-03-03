package com.example.charityapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterPage extends AppCompatActivity {
    EditText nameEt,emailEt,passwordEt,rePasswordEt,numberPhoneEt;
    private FirebaseAuth mauth;
    ImageView registerbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);
        nameEt=findViewById(R.id.nameId);
        emailEt=findViewById(R.id.emailId);
        passwordEt=findViewById(R.id.passwordLoginId);
        rePasswordEt=findViewById(R.id.rePasswordId);
        numberPhoneEt=findViewById(R.id.phoneId);

        mauth=FirebaseAuth.getInstance();

        registerbtn=findViewById(R.id.registerButton) ;
        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
    }
private void registerUser(){
        final String name =nameEt.getText().toString().trim();
        final String email =emailEt.getText().toString().trim();
        String password =passwordEt.getText().toString().trim();
        String rePassword =rePasswordEt.getText().toString().trim();
        final String numberPhone=numberPhoneEt.getText().toString().trim();
    if (name.isEmpty()) {
        nameEt.setError(getString(R.string.input_error_name));
        nameEt.requestFocus();
        return;
    }
    if (email.isEmpty()) {
        emailEt.setError(getString(R.string.input_error_email));
        emailEt.requestFocus();
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
    mauth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {

    @Override
     public void onComplete(@NonNull Task<AuthResult> task) {
      if (task.isSuccessful()) {
          System.out.println("oncomplete user get instance .");

          System.out.println(FirebaseAuth.getInstance().getCurrentUser().getUid());
       User user = new User(name,email,numberPhone);
         FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
          .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
              @Override public void onComplete(@NonNull Task<Void> task) {
         if (task.isSuccessful()) {
             System.out.println("task is saccusss .");
             Toast.makeText(RegisterPage.this, getString(R.string.registration_success), Toast.LENGTH_LONG).show();
             }
         else {
             Toast.makeText(RegisterPage.this, getString(R.string.registration_failed), Toast.LENGTH_LONG).show();
             System.out.println("failed .");
             //display a failure message
                          }
                            }
                        });

                    } else {
                        Toast.makeText(RegisterPage.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            });

}




}
