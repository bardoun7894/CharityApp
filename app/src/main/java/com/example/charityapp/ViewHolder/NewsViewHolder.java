package com.example.charityapp.ViewHolder;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.charityapp.Interface.ItemClickListener;
import com.example.charityapp.R;

public class NewsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public ItemClickListener listener;
    public TextView textTitle,textBody,time,date;
    public ImageView imageView;
    public ImageView removeImage;
    public NewsViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView=itemView.findViewById(R.id.showimageId);
        textBody=itemView.findViewById(R.id.showbodyId);
        textTitle=itemView.findViewById(R.id.showtitleId);
        time=itemView.findViewById(R.id.timeId);
        date=itemView.findViewById(R.id.dateid);
        removeImage=itemView.findViewById(R.id.removeItemId);
    }

    public  void setOnItemClickListener(ItemClickListener listener){
        this.listener=listener;
    }
    @Override
    public void onClick(View v) {
   listener.onClick(v,getAdapterPosition(),false);
    }
}
