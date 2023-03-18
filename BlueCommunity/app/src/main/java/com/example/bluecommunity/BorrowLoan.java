package com.example.bluecommunity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class BorrowLoan extends AppCompatActivity {
    private EditText etUserName;
    private EditText etPhone;
    private EditText etAmount;
    private Button request;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrow_loan);
        etUserName=findViewById(R.id.etUserName);
        etPhone=findViewById(R.id.etPhone);
        etAmount=findViewById(R.id.etAmount);
        request=findViewById(R.id.request);
        request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                borrow();
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
    public boolean validatePhone(){
        String phone=etPhone.getText().toString();
        if(phone.isEmpty()){
            etPhone.setError("Enter your phone number");
            etPhone.requestFocus();
            return false;
        }
        else if(phone.length()>10 && phone.length()<10){
            etPhone.setError("Enter a valid phone number");
            etPhone.requestFocus();
            return false;
        }
        else {
            etPhone.setError(null);
            return true;
        }
    }
    public  boolean validateAmount(){
        String amount=etAmount.getText().toString();
        if(amount.isEmpty()){
            etAmount.setError("Amount required");
            etAmount.requestFocus();
            return false;
        }
        else {
            etAmount.setError(null);
            return true;
        }
    }
    public void borrow(){
        if(!validateUserName()|!validatePhone()|!validateAmount()){
            return;
        }
        else{
            //excute database
        }
    }
}