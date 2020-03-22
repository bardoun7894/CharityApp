package com.example.charityapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

import io.paperdb.Paper;

public class ActivityCategory extends AppCompatActivity {
    ImageView backIv;
    ListView lv;
    LinearLayout ln;
    TextView btnNext;
    Intent intentToDonateClass,intentToOtherClass;
    Bundle foodExtras,booksExtras,othersExtras,awanyExtras;

    int[] images = {
          R.drawable.fourniture, R.drawable.clothes_new, R.drawable.foods_new,R.drawable.about_new,
            R.drawable.books_new, R.drawable.awani_new, R.drawable.shoes_new, R.drawable.baby_new, R.drawable.others,
    };
    private DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        lv=findViewById(R.id.lvId);
        ln=findViewById(R.id.linerNext);
        btnNext=findViewById(R.id.nextId);
        backIv=findViewById(R.id.backId2);
        String numberPhone = Paper.book().read(Prevalent.UserPhoneKey);
        ref = FirebaseDatabase.getInstance().getReference().child("Cart").child(numberPhone);
        intentToDonateClass=new Intent(ActivityCategory.this,CartDonations.class);
        intentToOtherClass=new Intent(ActivityCategory.this,Others.class);
        backIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Intent intent =new Intent(ActivityCategory.this,MainActivity.class);
                startActivity(intent);
            }
        });
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkData();        }
        });
        final CustomAdapter customAdapter =new CustomAdapter();
        lv.setAdapter(customAdapter);

//        int rowsOnScreen =  Math.abs(lv.getLastVisiblePosition() - lv.getFirstVisiblePosition());
//        int rowToStartOn = 8 - rowsOnScreen / 2;
     lv.setOnScrollListener(new AbsListView.OnScrollListener() {
         @Override
         public void onScrollStateChanged(AbsListView view, int scrollState) {
             if(scrollState == 0){

                ln.setVisibility(View.VISIBLE);
             }
         }
         @Override
         public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

         }
     });
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
            if(position ==5){
                //اواني
                awanyExtras = new Bundle();
                awanyExtras.putString("class","AwaniClass");
                awanyExtras.putString("awani","أواني ");
                intentToDonateClass.putExtras(awanyExtras);
                startActivity(intentToDonateClass); }
            if(position ==6){
                Intent intent = new Intent(ActivityCategory.this, Shoes.class);
                startActivity(intent);   }
            if(position ==7){
                Intent intent = new Intent(ActivityCategory.this, BabyTools.class);
                startActivity(intent);   }
            if(position ==8){
                othersExtras=new Bundle();
                othersExtras.putString("class","OthersClass");
                intentToOtherClass.putExtras(othersExtras);
                startActivity(intentToOtherClass);   }
        }
         }
         );

    }

public void checkData() {
    ref.addListenerForSingleValueEvent(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue();
            if (map==null) {
                Toast.makeText(ActivityCategory.this, "المرجو اختيار الاشياء الذي تريد التطوع بها", Toast.LENGTH_SHORT).show();
            }else{
                Intent intent =new Intent(ActivityCategory.this,Donate.class);
                startActivity(intent);
            }
        }
        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

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