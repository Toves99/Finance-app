package com.example.bluecommunity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserMenu extends AppCompatActivity {
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://sunrisecommunity-95d96-default-rtdb.firebaseio.com/");
    private Button payLoan,borrowLoan,loanMore,save,balanceSave,paySubscription,subscriptionBalance;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_menu);
        payLoan=findViewById(R.id.payLoan);
        borrowLoan=findViewById(R.id.borrowLoan);
        loanMore=findViewById(R.id.loanMore);
        save=findViewById(R.id.save);
        balanceSave=findViewById(R.id.balanceSave);
        paySubscription=findViewById(R.id.paySubscription);
        payLoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserMenu.this,PayLoan.class));
                finish();
            }
        });
        borrowLoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserMenu.this,BorrowLoan.class));
                finish();
            }
        });
        loanMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserMenu.this,UserLoanMore.class));
                finish();
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserMenu.this,Save.class));
                finish();
            }
        });
        balanceSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserMenu.this,MySavingBalance.class));
                finish();
            }
        });
        paySubscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserMenu.this,PaySubscription.class));
                finish();
            }
        });
    }
}