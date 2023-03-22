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

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.regex.Pattern;

public class AdminSignIn extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[@#$%^&+=])" +
                    "(?=\\S+$)" +
                    ".{6,}" +
                    "$"
            );
    /**
     developer clinton tovesi
     *
     */
    //variables declaration
    private EditText etUserName;
    private EditText etEmail;
    private EditText etPassword;
    private Button signBtn;
    ProgressDialog progressDialog;
    public void onBackPressed(){
        super.onBackPressed();
        startActivity(new Intent(AdminSignIn.this,AdminLogin.class));
        finish();
    }
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_sign_in);
        progressDialog=new ProgressDialog(this);
        //initializing id
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseFirestore=FirebaseFirestore.getInstance();
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
    //validating inputs from users
    public boolean validateUserName(){
        String username=etUserName.getText().toString();
        if(username.isEmpty()){
            etUserName.setError("Provide userName please");
            etUserName.requestFocus();
            return false;
        }
        else {
            etUserName.setError(null);
            return true;
        }
    }
    public boolean validateEmail(){
        String email=etEmail.getText().toString();
        if(email.isEmpty()){
            etEmail.setError("Provide your email please");
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
    public boolean validatePassword(){
        String password=etPassword.getText().toString();
        if(password.isEmpty()){
            etPassword.setError("Provide your password please");
            etPassword.requestFocus();
            return false;
        }
        else if(!PASSWORD_PATTERN.matcher(password).matches()){
            etPassword.setError("Please enter a strong password");
            etPassword.requestFocus();
            return false;
        }
        else if(password.length()<8){
            etPassword.setError("Password is too short");
            etPassword.requestFocus();
            return false;
        }
        else {
            etPassword.setError(null);
            return true;
        }

    }
    //Method for registering
    public void sign(){
        String username=etUserName.getText().toString();
        String email=etEmail.getText().toString();
        String password=etPassword.getText().toString();
        if(!validateUserName()|!validateEmail()|!validatePassword()){
            return;
        }
        else {
            progressDialog.show();
            firebaseAuth.createUserWithEmailAndPassword(email,password)
                    .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            startActivity(new Intent(AdminSignIn.this,AdminLogin.class));
                            finish();
                            progressDialog.cancel();
                            firebaseFirestore.collection("user")
                                    .document(FirebaseAuth.getInstance().getUid())
                                    .set(new UserModel(username,email,password));
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(AdminSignIn.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                            progressDialog.cancel();
                        }
                    });
        }

    }
}