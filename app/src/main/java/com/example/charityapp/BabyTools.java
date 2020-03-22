package com.example.charityapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.charityapp.model.User;

import java.util.ArrayList;
public class BabyTools extends AppCompatActivity {
    LinearLayout  bedBabyLn,babyWalkln,babyRoadln,babyChairln,otherBabyln;
    Intent intentToDonateClass,intentToOtherClass;
    Bundle babyToolsExtras,babyToolsOtherExtras;
    User user;
    ArrayList<String> a;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baby_tools);
        intentToDonateClass=new Intent(BabyTools.this,CartDonations.class);
        intentToOtherClass=new Intent(BabyTools.this,Others.class);
        bedBabyLn=findViewById(R.id.bedBabyId);
        babyWalkln=findViewById(R.id.babyWalkId);
        babyRoadln=findViewById(R.id.babyRoadId);
        babyChairln=findViewById(R.id.babyChairId);
        otherBabyln=findViewById(R.id.otherChildID);
        user=new User();
        a = user.getA();
        babyToolsExtras = new Bundle();
        babyToolsOtherExtras = new Bundle();
        babyToolsExtras.putString("class","BabyToolsClass");
        babyToolsOtherExtras.putString("class","BabyToolsClass");
        intentToDonateClass.putStringArrayListExtra("المتبرع به",a);

        bedBabyLn.setOnClickListener(new View.OnClickListener() {
              @Override
               public void onClick(View v) {
                String baby_bed_text=getString(R.string.baby_bed);
                if(!a.contains(baby_bed_text)) a.add(baby_bed_text);
                intentToDonateClass.putExtras(babyToolsExtras);
                startActivity(intentToDonateClass);
                 }});
        babyWalkln.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String baby_walk_text=getString(R.string.baby_set);
                if(!a.contains(baby_walk_text)) a.add(baby_walk_text);
                intentToDonateClass.putExtras(babyToolsExtras);
                startActivity(intentToDonateClass);
                               }} );
        babyRoadln.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String baby_walk_text=getString(R.string.baby_car);
                if(!a.contains(baby_walk_text)) a.add(baby_walk_text);
                intentToDonateClass.putExtras(babyToolsExtras);
                startActivity(intentToDonateClass);
            }
        } );
        babyChairln.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String baby_chair_text=getString(R.string.baby_chair);
                if(!a.contains(baby_chair_text)) a.add(baby_chair_text);
                intentToDonateClass.putExtras(babyToolsExtras);
                startActivity(intentToDonateClass);
            }
        } );
        otherBabyln.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String others=getString(R.string.others);
//                if(!a.contains(others)) a.add(others);
                intentToOtherClass.putExtras( babyToolsOtherExtras);
                startActivity(intentToOtherClass);
            }
        } );
    }


}
