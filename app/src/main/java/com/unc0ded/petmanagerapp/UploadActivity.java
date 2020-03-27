package com.unc0ded.petmanagerapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.esafirm.imagepicker.features.ImagePicker;
import com.esafirm.imagepicker.model.Image;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;

public class UploadActivity extends AppCompatActivity {

    TextView imgSelect;
    Button upload;
    String imgPath;
    boolean imgFlag=false;
    StorageReference storageReference;
    Image image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        imgSelect=findViewById(R.id.image_select);
        upload=findViewById(R.id.upload_btn);

        imgSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImagePicker.create(UploadActivity.this)
                        .single()
                        .folderMode(true)
                        .start();
            }
        });

        storageReference= FirebaseStorage.getInstance().getReference();
        final StorageReference imgRef=storageReference.child("images");

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!imgFlag){
                    Toast.makeText(UploadActivity.this,"No image selected",Toast.LENGTH_SHORT).show();
                }
                else {
                    Uri file = Uri.fromFile(new File(imgPath));
                    StorageReference newImageRef = imgRef.child(image.getName());

                    newImageRef.putFile(file)
                            .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                    Toast.makeText(UploadActivity.this,"Upload Successful",Toast.LENGTH_SHORT).show();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(UploadActivity.this,"Upload Failed",Toast.LENGTH_SHORT).show();
                                }
                            });
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(ImagePicker.shouldHandle(requestCode,resultCode,data)){
            image= ImagePicker.getFirstImageOrNull(data);
            imgPath=image.getPath();
            imgSelect.setText(imgPath);
            imgFlag=true;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}

