package com.example.bluecommunity;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class UserLoanMore extends AppCompatActivity {
private Button loanBalance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_loan_more);
        loanBalance=findViewById(R.id.loanBalance);

    }
}