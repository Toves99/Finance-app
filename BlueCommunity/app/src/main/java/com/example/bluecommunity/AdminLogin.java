package com.example.bluecommunity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AdminLogin extends AppCompatActivity {
    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReferenceFromUrl("https://sunrisecommunity-95d96-default-rtdb.firebaseio.com/");
    private EditText etUserName;
    private EditText etPassword;
    private Button loginBtn,SignBtn;
    ProgressDialog progressDialog;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        progressDialog=new ProgressDialog(this);
        etUserName=findViewById(R.id.etUserName);
        etPassword=findViewById(R.id.etPassword);
        loginBtn=findViewById(R.id.loginBtn);
        SignBtn=findViewById(R.id.SignBtn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
        SignBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminLogin.this,AdminSignIn.class));
                finish();
            }
        });
    }
    public boolean validateUserName(){
        String userName=etUserName.getText().toString();
        if(userName.isEmpty()){
            etUserName.setError("Enter your Username");
            etUserName.requestFocus();
            return false;
        }
        else {
            etUserName.setError(null);
            return true;
        }
    }
    public boolean validatePassword(){
        String password=etPassword.getText().toString();
        if(password.isEmpty()){
            etPassword.setError("Enter your Password");
            etPassword.requestFocus();
            return false;
        }
        else {
            etPassword.setError(null);
            return true;
        }
    }
    public void login(){
        String userName=etUserName.getText().toString();
        String password=etPassword.getText().toString();
        if(!validateUserName()|!validatePassword()){
            return;
        }
        else {
            databaseReference.child("admin").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.hasChild(userName)){
                        final String getPassword=snapshot.child(userName).child("password").getValue(String.class);
                        if(getPassword.equals(password)){
                            progressDialog.setMessage("Checking your details");
                            progressDialog.setTitle("Please wait...");
                            progressDialog.setIndeterminate(true);
                            progressDialog.show();
                            startActivity(new Intent(AdminLogin.this,AdminDashboard.class));
                            finish();
                        }
                        else {
                            etPassword.setError("Enter Valid Password");
                            etPassword.requestFocus();
                        }
                    }
                    else {
                        etUserName.setError("Enter valid username");
                        etUserName.requestFocus();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }
}