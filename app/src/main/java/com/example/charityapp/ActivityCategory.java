package com.example.charityapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import io.paperdb.Paper;

public class ActivityCategory extends AppCompatActivity {
    ImageView backIv;
    ListView lv;
    Intent intentToDonateClass;
    Bundle foodExtras,booksExtras;
    int[] images = {
          R.drawable.fourniture, R.drawable.clothes_new, R.drawable.foods_new,R.drawable.about_new,
            R.drawable.books_new, R.drawable.awani_new, R.drawable.shoes_new, R.drawable.baby_new, R.drawable.others,
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        lv=findViewById(R.id.lvId);
        backIv=findViewById(R.id.backId2);
        intentToDonateClass=new Intent(ActivityCategory.this,donate.class);
        backIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Paper.book().destroy();
                Intent intent =new Intent(ActivityCategory.this,MainActivity.class);
                startActivity(intent);
            }
        });
        CustomAdapter customAdapter =new CustomAdapter();
        lv.setAdapter(customAdapter);
    lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
         if(position ==0){
             //اثاث
             Intent intent = new Intent(ActivityCategory.this, Forniture.class);
             startActivity(intent); }
         if(position ==1){
             ///ملابس
                Intent intent = new Intent(ActivityCategory.this, Clothes.class);
                startActivity(intent); }
         if (position==2){
             //طعام
               foodExtras = new Bundle();
             foodExtras.putString("class","FoodClass");
             foodExtras.putString("food","طعام");
             intentToDonateClass.putExtras(foodExtras);
             startActivity(intentToDonateClass);
         }
            if(position ==3){
                Intent intent = new Intent(ActivityCategory.this, ActivityElectric.class);
                startActivity(intent);
            }
            if (position==4){
                //كتب
                  booksExtras = new Bundle();
                booksExtras.putString("class","BooksClass");
                booksExtras.putString("books","كتب و أوراق");
                intentToDonateClass.putExtras(booksExtras);
                startActivity(intentToDonateClass);
            }
            if(position ==6){
                Intent intent = new Intent(ActivityCategory.this, Shoes.class);
                startActivity(intent);   }   }
         });   }
    class CustomAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return images.length;
        }
        @Override
        public Object getItem(int position) {
            return null;
        }
        @Override
        public long getItemId(int position) {
            return 0;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
           View view =getLayoutInflater().inflate(R.layout.custom_list,null);
            ImageView imgV=view.findViewById(R.id.imgId);
            imgV.setImageResource(images[position]);
            return view;
        }
    }
}