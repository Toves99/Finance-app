package com.example.bluecommunity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AdminSignIn extends AppCompatActivity {
    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReferenceFromUrl("https://sunrisecommunity-95d96-default-rtdb.firebaseio.com/");
    private EditText etUserName;
    private EditText etEmail;
    private EditText etPassword;
    private Button signBtn;
    ProgressDialog progressDialog;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_sign_in);
        progressDialog=new ProgressDialog(this);
        etUserName=findViewById(R.id.etUserName);
        etEmail=findViewById(R.id.etEmail);
        etPassword=findViewById(R.id.etPassword);
        signBtn=findViewById(R.id.signBtn);
        signBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sign();
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
    public boolean validateEmail(){
        String email=etEmail.getText().toString();
        if(email.isEmpty()){
            etEmail.setError("Enter your Email");
            etEmail.requestFocus();
            return false;
        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            etEmail.setError("Please enter a valid email");
            etEmail.requestFocus();
            return false;
        }
        else {
            etEmail.setError(null);
            return true;
        }
    }
    public void sign(){
        String userName=etUserName.getText().toString();
        String email=etEmail.getText().toString();
        String password=etPassword.getText().toString();
        if(!validateUserName()|!validatePassword()|!validateEmail()){
            return;
        }
        else {
            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.hasChild(email)){
                        progressDialog.setMessage("Creating Your account");
                        progressDialog.setTitle("Please Wait...");
                        progressDialog.setIndeterminate(true);
                        progressDialog.show();
                        etEmail.setError("Email already exist");
                        progressDialog.dismiss();
                    }
                    else {
                        databaseReference.child("admin").child(email).child("email").setValue(userName);
                        databaseReference.child("admin").child(email).child("password").setValue(password);
                        progressDialog.setMessage("Creating Your Account");
                        progressDialog.setTitle("Please Wait...");
                        progressDialog.setIndeterminate(true);
                        progressDialog.show();
                        Toast.makeText(AdminSignIn.this,"Account created",Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(AdminSignIn.this,AdminLogin.class);
                        startActivity(intent);
                        finish();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(AdminSignIn.this,"Check your connection",Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}