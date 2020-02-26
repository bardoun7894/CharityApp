package com.example.charityapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class LoginPage extends AppCompatActivity {
    TextView createCompteTv;
    ImageView enterIv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        createCompteTv=findViewById(R.id.RegisterPageTV);
        enterIv=findViewById(R.id.enterId);

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
}
