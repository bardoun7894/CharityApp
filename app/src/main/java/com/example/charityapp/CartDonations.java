package com.example.charityapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.charityapp.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import io.paperdb.Paper;

public class CartDonations extends AppCompatActivity {
    Object s ;
   ListView listView;
   ArrayList<String> myArayList=new ArrayList<>();
   Button btnNext,btnBack;
   Intent removeDataIntent ,backIntent;
DatabaseReference ref;
    String donString="";
    User user;
    String d=null;

    private ImageView removeExsBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_donations);
        listView = findViewById(R.id.cartListId);
btnBack=findViewById(R.id.backDonateId);
        removeDataIntent=new Intent(CartDonations.this,BabyTools.class);
        user=new User();
        btnNext = findViewById(R.id.nextId);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backIntent=new Intent(CartDonations.this,ActivityCategory.class);
                startActivity(backIntent);
            }
        });
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        removeExsBtn=findViewById(R.id.removeExs);
       final String classdonation = extras.getString("class");
       s =new Object();
       ArrayList<String> stringArrayList =extras.getStringArrayList("المتبرع به");
   //     d = extras.getString("BabyOtherMsg");
        Toast.makeText(this, d, Toast.LENGTH_SHORT).show();

            assert classdonation != null;
            switch (classdonation) {
                case "clothesClass":
                    s = extras.getSerializable("clothesSelected");
                    break;
                case "shoesClass":
                    s = extras.getSerializable("shoesSelected");
                    break;
                case "FoodClass":
                    s = extras.getString("food");
                    break;
                case "AwaniClass":
                    s = extras.getString("awani");
                    break;
                case "BooksClass":
                    s = extras.getString("books");
                    break;
                case "OthersClass":
                    s = extras.getString("CategoryOtherMsg");
                    break;
                case "BabyToolsClass":
                     d = extras.getString("BabyOtherMsg");
                     s=null;
                 break;
                case "ElectricToolsClass":
                    d = extras.getString("ElectricOtherMsg");
                    s=null;
                      break;
                case "FornitureToolsClass":
                    d = extras.getString("FornitureOtherMsg");
                    s=null;
                    break;
            }
            if (classdonation.toLowerCase().contains("class")) {
                donString = classdonation.toLowerCase().replaceFirst("class", "").toString();
            addToCart(s, classdonation,stringArrayList,d);
        }
        String numberPhone = Paper.book().read(Prevalent.UserPhoneKey);
        ref = FirebaseDatabase.getInstance().getReference().child("Cart").child(numberPhone);
        final CustomCartAdapter customAdapter = new CustomCartAdapter();
        removeExsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ref.removeValue();
                listView.setAdapter(null);
                user.getA().clear();

            }
        });
        listView.setAdapter(customAdapter);

       ref.addListenerForSingleValueEvent(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue();
               for (String key: map.keySet()) {
                   if(!key.equals("time") && !key.equals("date") && !key.equals("deliveryOption"))
                   {
          if (key.equals("أخرى") ) {
               myArayList.add(dataSnapshot.child("أخرى").child("المتبرع به").getValue().toString());
                 }else{
                     myArayList.add(key);
                 }




                   }
               customAdapter.notifyDataSetChanged();
               }

           }
           @Override
       public void onCancelled(@NonNull DatabaseError databaseError) {

       }
   });
       btnNext.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent1=new Intent(CartDonations.this,Donate.class);
            startActivity(intent1);
        }
    });

    }


    class CustomCartAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return myArayList.size();
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
            View view =getLayoutInflater().inflate(R.layout.cart_item_list,null);
            String data=myArayList.get(position);
            TextView textView=view.findViewById(R.id.text122);
            textView.setText(data);
            return view;
        }
    }
    private void addToCart(final Object donateType, String classdonation, ArrayList<String> stringArrayList, String sss) {
        String donString="";

        if(classdonation.toLowerCase().contains("class")){
            donString =classdonation.toLowerCase().replaceFirst("class","");
            switch (donString.toLowerCase()){
                case "clothes":
                    donString="ملابس";
                    break;
                case "shoes":
                    donString="أحذية";
                    break;
                case "food":
                    donString="تمور و أغذية";
                    break;
                case "books":
                    donString="كتب";
                    break;
                case "awani":
                    donString="أواني";
                    break;
                case "others":
                    donString="أخرى";
                    break;
                case "babytools":
                    donString="أدوات أطفال";
                    break;
                case "electrictools":
                    donString="أدوات كهربائية";
                    break;
                case "fornituretools":
                    donString="أثاث";
                    break;
            }
        }
        String  saveCurrentTime,saveCurrentDate;
        Calendar calendar=Calendar.getInstance();
        SimpleDateFormat currentDate=new SimpleDateFormat("MMM dd,yyyy");
        saveCurrentDate=currentDate.format(calendar.getTime());
        SimpleDateFormat currentTime=new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime=currentTime.format(calendar.getTime());
        String numberPhone = Paper.book().read(Prevalent.UserPhoneKey);
        final DatabaseReference  donateRef= FirebaseDatabase.getInstance().getReference().child("Cart").child(numberPhone).child(donString);
        final HashMap<String,Object> donateMap=new HashMap<>();
        donateMap.put("time",saveCurrentTime);
        donateMap.put("date",saveCurrentDate);
        if(stringArrayList!=null){
        donateMap.put("المتبرع به",stringArrayList);
        }
        if(donateType!=null){
   donateMap.put("المتبرع به",donateType);
        }
         if(sss!=null){
        donateMap.put("المتبرع به",sss);
        }
        donateRef.updateChildren(donateMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                     Toast.makeText( CartDonations.this, "تمت الاضافة بنجاح .", Toast.LENGTH_SHORT).show();
                }
            }
        });

}


}
