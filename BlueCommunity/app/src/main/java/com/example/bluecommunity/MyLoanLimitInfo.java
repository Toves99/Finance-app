package com.example.bluecommunity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MyLoanLimitInfo extends AppCompatActivity {
    TextView etName,etLimit;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_loan_limit_info);
        etName=findViewById(R.id.etName);
        etLimit=findViewById(R.id.etLimit);
        showAlldata();
    }
    public void showAlldata(){
        Intent intent=getIntent();
        String user_name=intent.getStringExtra("name");
        String user_limit=intent.getStringExtra("limit");
        etName.setText(user_name);
        etLimit.setText(user_limit);
    }
}