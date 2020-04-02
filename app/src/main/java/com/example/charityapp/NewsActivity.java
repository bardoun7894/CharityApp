package com.example.charityapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.charityapp.model.News;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class NewsActivity extends AppCompatActivity {

    ImageView imageView;
    TextView title,body,date, time;
    String newsId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        imageView=findViewById(R.id.lastimageId);
        title =findViewById(R.id.lasttitleId);
        body=findViewById(R.id.lastbodyId);
        date=findViewById(R.id.lastdateid);
        time=findViewById(R.id.lasttimeId);
        newsId=getIntent().getStringExtra("nid");
        Log.i("nid",newsId);
        getNewsDetails(newsId);
    }
    private void getNewsDetails(String newsId) {
        DatabaseReference ref= FirebaseDatabase.getInstance().getReference().child("News");
        ref.child(newsId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    News news=dataSnapshot.getValue(News.class);
                    title.setText(news.getTitleNews());
                    body.setText(news.getBodyNews());
                    date.setText(news.getDate());
                    time.setText(news.getTime());
                    Picasso.get().load(news.getmImageUrl()).into(imageView);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}
