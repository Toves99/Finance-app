package com.example.bluecommunity;

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
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class LoanBalance extends AppCompatActivity {
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://sunrisecommunity-95d96-default-rtdb.firebaseio.com/");
    private EditText etPassword;
    private Button request;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan_balance);
        etPassword=findViewById(R.id.etPassword);
        request=findViewById(R.id.request);
        request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String password = etPassword.getText().toString();
                databaseReference=FirebaseDatabase.getInstance().getReference("debt");
                Query checkId=databaseReference.orderByChild("name").equalTo(password);
                checkId.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()){
                            etPassword.setError(null);
                            String nameFromDb=snapshot.child(password).child("password").getValue(String.class);
                            if(nameFromDb.equals(password)){
                                String amount=snapshot.child(password).child("amount").getValue(String.class);
                                String paid=snapshot.child(password).child("paid").getValue(String.class);
                                String processingFee=snapshot.child(password).child("processingFee").getValue(String.class);
                                String total=snapshot.child(password).child("total").getValue(String.class);
                                String date=snapshot.child(password).child("date").getValue(String.class);
                                Intent intent=new Intent(getApplicationContext(),LoanBalanceInfo.class);
                                intent.putExtra("amount",amount);
                                intent.putExtra("paid",paid);
                                intent.putExtra("processingFee",processingFee);
                                intent.putExtra("total",total);
                                intent.putExtra("date",date);
                                startActivity(intent);
                            }
                            else {
                                etPassword.setError("Invalid passcode");
                                etPassword.requestFocus();
                            }
                        }
                        else {
                            etPassword.setError("Your passcode does not exist");
                            etPassword.requestFocus();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
    }
}