package com.example.charityapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.charityapp.model.DonationsShoes;

public class Shoes extends AppCompatActivity {
    ImageView whiteChildCircle,whiteManCircle,whiteWomenCircle;
    public int[] images = {R.drawable.white_point, R.drawable.dark_point};
    int currentImage=0;
    String manShoes="";
    String womenShoes="";
    String childShoes="";
    DonationsShoes donationsShoes;

    Button sureBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoes);
        donationsShoes = new DonationsShoes(manShoes,womenShoes,childShoes);
        whiteChildCircle =findViewById(R.id.shoesChildId);
        whiteManCircle =findViewById(R.id.shoesManId);
        whiteWomenCircle =findViewById(R.id.shoesWomanId);
        sureBtn=findViewById(R.id.sureShoesId);
        sureBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(Shoes.this, CartDonations.class);
                Bundle extras = new Bundle();
                extras.putString("class","shoesClass");
                extras.putSerializable("shoesSelected", donationsShoes);
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
                    donationsShoes.setManShoes(getString(R.string.shoes_man_text));
                }else{
                    donationsShoes.setManShoes("");
                }
            }
        });
        whiteWomenCircle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectCurrentImage();
                whiteWomenCircle.setImageResource(images[currentImage]);
                if(currentImage==1){
                    donationsShoes.setWomenShoes(getString(R.string.shoes_women_text));
                }else{
                    donationsShoes.setWomenShoes("");
                }
            }
        });
        whiteChildCircle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectCurrentImage();
                whiteChildCircle.setImageResource(images[currentImage]);
                if(currentImage==1){
                    donationsShoes.setChildShoes(getString(R.string.shoes_childreen_text));
                }else{
                    donationsShoes.setChildShoes("");
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