package com.example.bluecommunity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LoanBalanceInfo extends AppCompatActivity {
    TextView etAmount,etPaid,etTotal,etProcessingFee,etDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan_balance_info);
        etAmount=findViewById(R.id.etAmount);
        etPaid=findViewById(R.id.etPaid);
        etTotal=findViewById(R.id.etTotal);
        etProcessingFee=findViewById(R.id.etProcessingFee);
        etDate=findViewById(R.id.etDate);
        showAlldata();
    }
    public void showAlldata(){
        Intent intent=getIntent();
        String user_amount=intent.getStringExtra("amount");
        String user_paid=intent.getStringExtra("paid");
        String user_processingFee=intent.getStringExtra("processingFee");
        String user_total=intent.getStringExtra("total");
        String user_date=intent.getStringExtra("date");
        etAmount.setText(user_amount);
        etPaid.setText(user_paid);
        etProcessingFee.setText(user_processingFee);
        etTotal.setText(user_total);
        etDate.setText(user_date);
    }
}