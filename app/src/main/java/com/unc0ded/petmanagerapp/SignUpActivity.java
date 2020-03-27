package com.unc0ded.petmanagerapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {

    EditText email, pass, name;
    Button signup;
    FirebaseAuth auth;
    DatabaseReference userReference= FirebaseDatabase.getInstance().getReference().child("users");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        attachID();

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                auth.createUserWithEmailAndPassword(email.getText().toString(),pass.getText().toString())
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                userReference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("name").setValue(name.getText().toString());
                                userReference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("email").setValue(email.getText().toString());
                                Toast.makeText(SignUpActivity.this,"User Created",Toast.LENGTH_SHORT).show();
                                Intent main=new Intent(SignUpActivity.this,MainActivity.class);
                                startActivity(main);
                                finish();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(SignUpActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }

    private void attachID() {
        email=findViewById(R.id.email_field);
        name=findViewById(R.id.name_field);
        pass=findViewById(R.id.pass_field);
        signup=findViewById(R.id.signup_btn);
        auth=FirebaseAuth.getInstance();
    }
}
