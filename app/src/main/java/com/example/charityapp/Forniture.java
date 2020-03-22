package com.example.charityapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.charityapp.model.User;

import java.util.ArrayList;

public class Forniture extends AppCompatActivity {
    ImageView fourniturebackIv;
    private Intent intentToDonateClass;
    private Intent intentToOtherClass;
    LinearLayout curtainLn,matressLn,chairLn,bedLn,sofaLn,otherFournitureLn;
    private User user;
    private ArrayList<String> a;
    private Bundle furnitureToolsExtras, furnitureToolsOtherExtras;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scro);
        fourniturebackIv=findViewById(R.id.fourniturebackId);
        fourniturebackIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Forniture.this,ActivityCategory.class);
                startActivity(intent);
            }
        });
        user=new User();
        a = user.getA();
        intentToDonateClass=new Intent(Forniture.this,CartDonations.class);
        intentToOtherClass=new Intent(Forniture.this,Others.class);
        furnitureToolsExtras = new Bundle();
        furnitureToolsOtherExtras = new Bundle();
        furnitureToolsExtras.putString("class","FornitureToolsClass");
        furnitureToolsOtherExtras.putString("class","FornitureToolsClass");
        intentToDonateClass.putStringArrayListExtra("المتبرع به",a);
        curtainLn=findViewById(R.id.curtainLnId);
        matressLn=findViewById(R.id.matressLnId);
        chairLn=findViewById(R.id.chairLnId);
        bedLn=findViewById(R.id.bedLnId);
        sofaLn=findViewById(R.id.sofaLnId);
        otherFournitureLn=findViewById(R.id.otherFournitureLnId);

        curtainLn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String curtain_text=getString(R.string.curtain);
                if(!a.contains(curtain_text)) a.add(curtain_text);
                intentToDonateClass.putExtras(furnitureToolsExtras);
                startActivity(intentToDonateClass);
            }});
        matressLn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String matress_text=getString(R.string.matress);
                if(!a.contains(matress_text)) a.add(matress_text);
                intentToDonateClass.putExtras(furnitureToolsExtras);
                startActivity(intentToDonateClass);
            }} );
        chairLn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String chair_text=getString(R.string.chair);
                if(!a.contains(chair_text)) a.add(chair_text);
                intentToDonateClass.putExtras(furnitureToolsExtras);
                startActivity(intentToDonateClass);
            }
        } );
        bedLn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String bed_text=getString(R.string.bed);
                if(!a.contains(bed_text)) a.add(bed_text);
                intentToDonateClass.putExtras(furnitureToolsExtras);
                startActivity(intentToDonateClass);
            }
        } );
        sofaLn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sofa_text=getString(R.string.sofa);
                if(!a.contains(sofa_text)) a.add(sofa_text);
                intentToDonateClass.putExtras(furnitureToolsExtras);
                startActivity(intentToDonateClass);
            }
        } );
        otherFournitureLn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentToOtherClass.putExtras(furnitureToolsOtherExtras);
                startActivity(intentToOtherClass);
            }
        } );
    }


}


