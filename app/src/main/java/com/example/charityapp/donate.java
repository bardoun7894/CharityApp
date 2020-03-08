package com.example.charityapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

import io.paperdb.Paper;

public class donate extends AppCompatActivity {
    Drawable whiteDrawable ;
    Drawable darkDrawable ;
    String deliveryOption="";
    ImageView whiteCarCircle,whiteDelivereItCircle;
    Button sureBtn;
    DonationsClothes donationsClothes;
    Object s;
    DonationsShoes donationsShoes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donate);
        whiteCarCircle =findViewById(R.id.pointCarId);
        whiteDelivereItCircle =findViewById(R.id.pointDeliveryId);
        whiteDrawable = getResources().getDrawable(R.drawable.white_point);
        darkDrawable= getResources().getDrawable(R.drawable.dark_point);
        sureBtn=findViewById(R.id.sureDeliveryId);
        //TODO implement this intent
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

 final String classdonation=extras.getString("class");

    assert classdonation != null;
    switch (classdonation){
     case  "clothesClass":
     s =  extras.getSerializable("clothesSelected");
         break;
     case  "shoesClass":
     s =  extras.getSerializable("shoesSelected");
       break;
     case  "FoodClass":
         s =  extras.getString("food");
            break;
        case  "BooksClass":
            s =  extras.getString("books");
            break;
    }

        sureBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO:complete malbis
                addToDataBase(s,deliveryOption,classdonation);
            }
        });
        whiteCarCircle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                whiteCarCircle.setImageDrawable(darkDrawable);
                whiteDelivereItCircle.setImageDrawable(whiteDrawable);
                deliveryOption=getString(R.string.get_car);
            }
        });
        whiteDelivereItCircle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                whiteDelivereItCircle.setImageDrawable(darkDrawable);
                whiteCarCircle.setImageDrawable(whiteDrawable);
                deliveryOption=getString(R.string.i_delivere_it);
            }
        });
    }
    private void addToDataBase(Object donateType, String deliveryOption, String classdonation) {
        String donString="";
        if(classdonation.toLowerCase().contains("class")){
            donString =classdonation.toLowerCase().replaceFirst("class","").toString();
        }
        String  saveCurrentTime,saveCurrentDate;
        Calendar calendar=Calendar.getInstance();
        SimpleDateFormat currentDate=new SimpleDateFormat("MMM dd,yyyy");
        saveCurrentDate=currentDate.format(calendar.getTime());
        SimpleDateFormat currentTime=new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime=currentTime.format(calendar.getTime());
        String numberPhone = Paper.book().read(Prevalent.UserPhoneKey);
        DatabaseReference  donateRef= FirebaseDatabase.getInstance().getReference().child("Donations").child(numberPhone).child(donString);

        final HashMap<String,Object> donateMap=new HashMap<>();
        donateMap.put("time",saveCurrentTime);
        donateMap.put("date",saveCurrentDate);
        donateMap.put("المتبرع به",donateType);
        donateMap.put("deliveryOption",deliveryOption);
        donateRef.updateChildren(donateMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
               if(task.isSuccessful()){
    Toast.makeText(donate.this, "added .", Toast.LENGTH_SHORT).show();
                                       }
            }
        });


    }
}
