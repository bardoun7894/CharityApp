package com.example.charityapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.charityapp.ViewHolder.NewsViewHolder;
import com.example.charityapp.model.News;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import io.paperdb.Paper;

public class AdminPanel extends AppCompatActivity {
ImageView addnews;
LinearLayout linearLayoutAdmin ;
RecyclerView recyclerView;
    String use;
    DatabaseReference newsRefrence;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<News> options = new
                FirebaseRecyclerOptions.Builder<News>().setQuery(newsRefrence, News.class).build();
        FirebaseRecyclerAdapter<News, NewsViewHolder> adapter=
                new FirebaseRecyclerAdapter<News, NewsViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull final NewsViewHolder newsViewHolder, int i, @NonNull final News news) {

                        newsViewHolder.textTitle.setText(news.getTitleNews());
                        String s =news.getBodyNews();
                        String bodyy="";
                        if(s.length()>30){
                         bodyy=  s.substring(0,30)+"...";
                           Log.d("s",bodyy);
                        }else{
                            bodyy=  s;
                        }
                        newsViewHolder.textBody.setText(bodyy);
                        Picasso.get().load(news.getmImageUrl()).into(newsViewHolder.imageView);
                        newsViewHolder.time.setText(news.getTime());
                        newsViewHolder.date.setText(news.getDate());
                        if(!use.equals("admin")) {
                            newsViewHolder.removeImage.setVisibility(View.GONE);
                        }else{
                            newsViewHolder.removeImage.setVisibility(View.VISIBLE);

                        }
                        newsViewHolder.removeImage.setOnClickListener(new View.OnClickListener() {

                            @Override
                            public void onClick(View v) {
                                CharSequence options[] =new CharSequence[]{   "حذف" };
                                AlertDialog.Builder builder=new AlertDialog.Builder(AdminPanel.this);
                                builder.setTitle("حذف الخبر");
                                builder.setItems(options, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        newsRefrence.child(news.getNid()).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()){
                                                    Toast.makeText(AdminPanel.this, "تم حذف الخبر", Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        });
                                    }
                                });
                                builder.show();
                            }
                        });
                        newsViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {


                              Intent intent=new Intent(AdminPanel.this,NewsActivity.class);
                              intent.putExtra("nid",news.getNid());
                              startActivity(intent);
                            }
                        });
                    }
                    @NonNull
                    @Override
                    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_items,parent,false);
                         NewsViewHolder holder= new NewsViewHolder(view);
                        return  holder;
                    }
                };
        recyclerView.setAdapter(adapter);
        Log.i("rec2","rec");
        adapter.startListening();


         }



       public void onBackPressed()
       {
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
        super.onBackPressed();
       }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_panel);
        recyclerView=findViewById(R.id.newsRecyclerViewId);
        recyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);
        Log.i("rec","rec");
        addnews=findViewById(R.id.addNewsId);
        linearLayoutAdmin=findViewById(R.id.LinuarAdminId);

        Intent intent = getIntent();
       use= intent.getStringExtra("useradmin");

        if(!use.equals("admin")) {

            linearLayoutAdmin.setVisibility(View.GONE);
        }else{
            linearLayoutAdmin.setVisibility(View.VISIBLE);

        }


        newsRefrence= FirebaseDatabase.getInstance().getReference().child("News");

        addnews.setOnClickListener(new View.OnClickListener() {
         @Override
            public void onClick(View v) {
                Toast.makeText(AdminPanel.this,"انت الان أدمين لديك كل الصلاحيات",Toast.LENGTH_LONG).show();
                Intent intent=new Intent(getApplicationContext(),AddNews.class);
                startActivity(intent);
            }
        });

    }
}
