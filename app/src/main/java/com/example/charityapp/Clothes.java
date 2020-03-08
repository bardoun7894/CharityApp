package com.example.charityapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Clothes extends AppCompatActivity {
ImageView whiteChildCircle,whiteManCircle,whiteWomenCircle;
   public int[] images = {R.drawable.white_point, R.drawable.dark_point};
 int currentImage=0;
    String manName="";
    String womenName="";
    String childName="";

Button sureBtn;
DonationsClothes donationsClothes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clothes);
         donationsClothes =new DonationsClothes(manName,womenName,childName);
        whiteChildCircle =findViewById(R.id.pointChildId);
        whiteManCircle =findViewById(R.id.pointManId);
        whiteWomenCircle =findViewById(R.id.pointWomanId);
        sureBtn=findViewById(R.id.sureClothesId);
        sureBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(Clothes.this,donate.class);
                Bundle extras = new Bundle();
                extras.putString("class","clothesClass");
                extras.putSerializable("clothesSelected", donationsClothes);
                intent.putExtras(extras);
                startActivity(intent);
            }
        });

        whiteManCircle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectCurrentImage();
                whiteManCircle.setImageResource(images[currentImage]);
               if(currentImage==1){
                donationsClothes.setManClothes(getString(R.string.clothes_man_text));
               }else{
                   donationsClothes.setManClothes("");
               }
            }
        });
        whiteWomenCircle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectCurrentImage();
                whiteWomenCircle.setImageResource(images[currentImage]);
                if(currentImage==1){
                    donationsClothes.setWomenClothes(getString(R.string.clothes_women_text));
                }else{
                    donationsClothes.setWomenClothes("");
                }
            }
        });
        whiteChildCircle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectCurrentImage();
                whiteChildCircle.setImageResource(images[currentImage]);
                if(currentImage==1){
                    donationsClothes.setChildClothes(getString(R.string.childreen_clothes_text));
                }else{
                    donationsClothes.setChildClothes("");
                }

            }
        });



    }

    private void selectCurrentImage() {
        currentImage++;
        if (currentImage == 2) {
            currentImage = 0;
        }
    }

}

