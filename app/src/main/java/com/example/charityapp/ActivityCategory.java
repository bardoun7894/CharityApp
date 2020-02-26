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

public class ActivityCategory extends AppCompatActivity {
    ListView lv;
    int[] images = {
          R.drawable.fourni, R.drawable.clothes, R.drawable.foods, R.drawable.about,
            R.drawable.books, R.drawable.awani, R.drawable.shoes, R.drawable.money, R.drawable.childreen_tool,
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        lv=findViewById(R.id.lvId);
        CustomAdapter customAdapter =new CustomAdapter();
        lv.setAdapter(customAdapter);
    lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
         if(position ==0){
             Intent intent = new Intent(ActivityCategory.this, Scro.class);
             startActivity(intent); }
            if(position ==3){
                Intent intent = new Intent(ActivityCategory.this, ActivityElectric.class);
                startActivity(intent);
            }
        }
    });
    }

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