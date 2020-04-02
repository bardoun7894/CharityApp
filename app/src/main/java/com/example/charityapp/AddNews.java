package com.example.charityapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.charityapp.model.News;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import java.util.Calendar;

public class AddNews extends AppCompatActivity {
ImageView addNewsImage;
private static final int GalleryPick=1;
private Uri imageUri;
EditText titleEt;
 EditText bodyET;
 String saveCurrentDate;
 String saveCurrentTime;
 private ProgressDialog loadingBar;
 private StorageTask mUploadTask;
 ProgressBar mProgressBar;
String title,body;
Button addNewsBtn;

private DatabaseReference databaseReference;
private  String downloadImageUrl;
private  StorageReference newsImageRefr;
    @Override
    public void onBackPressed() {
        Intent intent=new Intent(this,AdminPanel.class);
        intent.putExtra("useradmin","admin");
        startActivity(intent);
        super.onBackPressed();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_news);
        addNewsImage=findViewById(R.id.newsImageId);
        titleEt=findViewById(R.id.titleEt);
        bodyET=findViewById(R.id.bodyId);
        mProgressBar = findViewById(R.id.progressBarId);

        addNewsBtn=findViewById(R.id.saveNewsId);
        loadingBar=new ProgressDialog(this);

        Calendar calendar=Calendar.getInstance();
        SimpleDateFormat currentDate=new SimpleDateFormat("MMM dd,yyyy");
        saveCurrentDate=currentDate.format(calendar.getTime());
        SimpleDateFormat currentTime=new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime=currentTime.format(calendar.getTime());
        databaseReference=FirebaseDatabase.getInstance().getReference().child("News");
        newsImageRefr= FirebaseStorage.getInstance().getReference().child("NewsImages");
        addNewsImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGalery();
            }
        });
        addNewsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewsBtn.setVisibility(View.INVISIBLE);
                mProgressBar.setVisibility(View.VISIBLE);
                validateNewsData();
            }
        });
    }
    private void validateNewsData() {

          body=bodyET.getText().toString();
          title=titleEt.getText().toString();
          if(imageUri==null){
              mProgressBar.setVisibility(View.INVISIBLE);
              addNewsBtn.setVisibility(View.VISIBLE);
              Toast.makeText(getApplicationContext(),"لا يوجد صورة",Toast.LENGTH_SHORT).show();
          }
          else if(TextUtils.isEmpty(title)){
              Toast.makeText(this, "المرجو كتابة العنوان ", Toast.LENGTH_SHORT).show();
          }else if(TextUtils.isEmpty(body)){
              Toast.makeText(this, "المرجو كتابة الوصف ", Toast.LENGTH_SHORT).show();
          }else {
              if(mUploadTask!=null && mUploadTask.isInProgress()){

                 }
              else{
                  StoreNewsInfo();

              }

          }

    }
    private  String getFileExtension(Uri uri){
        ContentResolver cr = getContentResolver();
        MimeTypeMap mime= MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(uri));
  }
    private void StoreNewsInfo() {

final StorageReference filePath= newsImageRefr.child(System.currentTimeMillis()+"."+getFileExtension(imageUri));
final UploadTask uploadTask =filePath.putFile(imageUri);

uploadTask.addOnFailureListener(new OnFailureListener() {
    @Override
    public void onFailure(@NonNull Exception e) {
   String message =e.toString();
        Toast.makeText(AddNews.this, message, Toast.LENGTH_SHORT).show();
    }
}).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
              @Override
          public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
          Handler handler =new Handler();
                 handler.postDelayed(new Runnable() {
                     @Override
                     public void run() {
                      mProgressBar.setProgress(0);
                     }
                 } ,500);

    Task<Uri> uriTask=uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
        @Override
        public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
            if(!task.isSuccessful()){
              throw task.getException();
            }

            return filePath.getDownloadUrl();
        }
    });
    }
}).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
    @Override
    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

        //here
        Task<Uri> urlTask = taskSnapshot.getStorage().getDownloadUrl();
        while (!urlTask.isSuccessful());{
            Uri downloadUrl = urlTask.getResult();
            downloadImageUrl= String.valueOf(downloadUrl);
        }
    }
}).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
    @Override
    public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {
        double progress=(100.0*taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());
      mProgressBar.setProgress((int)progress);

    }
}).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
    @Override
    public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
        if(task.isSuccessful()){
            Toast.makeText(AddNews.this, "تم الحفظ", Toast.LENGTH_SHORT).show();
            addNewsBtn.setVisibility(View.VISIBLE);
            addNewsImage.setImageResource(R.drawable.gallery);
            mProgressBar.setVisibility(View.INVISIBLE);
            imageUri=null;
            titleEt.setText("");
            bodyET.setText("");
            String uploadId =databaseReference.push().getKey();
            News upload =new News(uploadId,title,body,downloadImageUrl,saveCurrentTime,saveCurrentDate);
            databaseReference.child(uploadId).setValue(upload);
            Intent intent =new Intent(AddNews.this,AdminPanel.class);
            intent.putExtra("useradmin","admin");
            startActivity(intent);

        }
    }
});

    }

    private void openGalery(){
      Intent galleryIntent =new Intent();
      galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
      galleryIntent.setType("image/*");
       startActivityForResult(galleryIntent,GalleryPick);
 }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
       if(requestCode==GalleryPick && resultCode==RESULT_OK && data!=null){
       imageUri=data.getData();
       addNewsImage.setImageURI(imageUri);
       }
    }
}
