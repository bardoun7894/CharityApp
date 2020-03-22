package com.example.charityapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.charityapp.model.User;

import java.util.ArrayList;

public class ActivityElectric extends AppCompatActivity {
    ImageView backIv;
    private Intent intentToDonateClass;
    private Intent intentToOtherClass;
    LinearLayout tvln,ironln,machineLn,refrigeratorLn,kitchenLn,otherElectricLn;
    private User user;
    private ArrayList<String> a;
    private Bundle electricToolsExtras,electricToolsOtherExtras;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_electric);
        backIv=findViewById(R.id.backElectId);
        backIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
           Intent intent=new Intent(ActivityElectric.this,ActivityCategory.class);
        startActivity(intent);
            }
        });
        intentToDonateClass=new Intent(ActivityElectric.this,CartDonations.class);
        intentToOtherClass=new Intent(ActivityElectric.this,Others.class);
        tvln=findViewById(R.id.tvlnId);
        ironln=findViewById(R.id.ironLnId);
        machineLn=findViewById(R.id.machineLnId);
        refrigeratorLn=findViewById(R.id.refrigeratorLnId);
        kitchenLn=findViewById(R.id.kitchenLnId);
        otherElectricLn=findViewById(R.id.otherElectricLnId);
        user=new User();
        a = user.getA();
        electricToolsExtras = new Bundle();
        electricToolsOtherExtras = new Bundle();
        electricToolsExtras.putString("class","ElectricToolsClass");
        electricToolsOtherExtras.putString("class","ElectricToolsClass");
        intentToDonateClass.putStringArrayListExtra("المتبرع به",a);

        tvln.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tv_text=getString(R.string.tv);
                if(!a.contains(tv_text)) a.add(tv_text);
                intentToDonateClass.putExtras(electricToolsExtras);
                startActivity(intentToDonateClass);
            }});
        ironln.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String iron_text=getString(R.string.iron);
                if(!a.contains(iron_text)) a.add(iron_text);
                intentToDonateClass.putExtras(electricToolsExtras);
                startActivity(intentToDonateClass);
            }} );
        machineLn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String machine_text=getString(R.string.wach_machine);
                if(!a.contains(machine_text)) a.add(machine_text);
                intentToDonateClass.putExtras(electricToolsExtras);
                startActivity(intentToDonateClass);
            }
        } );
        refrigeratorLn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String refrigator_text=getString(R.string.refrigerator);
                if(!a.contains(refrigator_text)) a.add(refrigator_text);
                intentToDonateClass.putExtras(electricToolsExtras);
                startActivity(intentToDonateClass);
            }
        } );
        kitchenLn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String kitcheen_tools_text=getString(R.string.kitcheen_tools);
                if(!a.contains(kitcheen_tools_text)) a.add(kitcheen_tools_text);
                intentToDonateClass.putExtras(electricToolsExtras);
                startActivity(intentToDonateClass);
            }
        } );
        otherElectricLn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentToOtherClass.putExtras( electricToolsOtherExtras);
                startActivity(intentToOtherClass);
            }
        } );
    }


}


