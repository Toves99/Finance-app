package com.example.bluecommunity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MySavingBalanceInfo extends AppCompatActivity {
    private TextView etLastName,etEmail,etTotal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_saving_balance_info);
        etLastName=findViewById(R.id.etLastName);
        etEmail=findViewById(R.id.etEmail);
        etTotal=findViewById(R.id.etTotal);
        showAlldata();
    }
    public void showAlldata(){
        Intent intent=getIntent();
        String user_lastname=intent.getStringExtra("lastname");
        String user_email=intent.getStringExtra("email");
        String user_total=intent.getStringExtra("email");
        etLastName.setText(user_lastname);
        etEmail.setText(user_email);
        etTotal.setText(user_total);


    }
}