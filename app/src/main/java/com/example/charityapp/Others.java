package com.example.charityapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Others extends AppCompatActivity {
EditText msg;
Button sendOther;
String classdonation="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_others);
        msg=findViewById(R.id.writeOthersEt);
        sendOther=findViewById(R.id.sendOthersId);

        final Intent intentSend=new Intent(Others.this, CartDonations.class);
        final Bundle bundleOthers = new Bundle();
        final Intent intentget = getIntent();
        final Bundle extras = intentget.getExtras();
      classdonation = extras.getString("class");
        sendOther.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              if(classdonation.equals("BabyToolsClass")){
                     bundleOthers.putString("class", "BabyToolsClass");
                   bundleOthers.putString("BabyOtherMsg",msg.getText().toString());
                   intentSend.putExtras(bundleOthers);
                        startActivity(intentSend);
                }
                if(classdonation.equals("ElectricToolsClass")){
                    bundleOthers.putString("class", "ElectricToolsClass");
                     bundleOthers.putString("ElectricOtherMsg",msg.getText().toString());
                    intentSend.putExtras(bundleOthers);
                    startActivity(intentSend);
                }
                if(classdonation.equals("FornitureToolsClass")){
                    bundleOthers.putString("class", "FornitureToolsClass");
                     bundleOthers.putString("FornitureOtherMsg",msg.getText().toString());
                    intentSend.putExtras(bundleOthers);
                    startActivity(intentSend);
                }
              else{
                        bundleOthers.putString("class","OthersClass");
                         bundleOthers.putString("CategoryOtherMsg",msg.getText().toString());
                        intentSend.putExtras(bundleOthers);
                        startActivity(intentSend);   }
            }
        });
    }
}
