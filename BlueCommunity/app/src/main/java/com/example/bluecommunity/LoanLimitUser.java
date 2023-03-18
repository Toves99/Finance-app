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

public class LoanLimitUser extends AppCompatActivity {
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://sunrisecommunity-95d96-default-rtdb.firebaseio.com/");
    private EditText etPassword;
    private Button request;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan_limit_user);
        etPassword=findViewById(R.id.etPassword);
        request=findViewById(R.id.request);
        request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String password = etPassword.getText().toString();
                databaseReference=FirebaseDatabase.getInstance().getReference("allocateLoans");
                Query checkId=databaseReference.orderByChild("password").equalTo(password);
                checkId.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()){
                            etPassword.setError(null);
                            String nameFromDb=snapshot.child(password).child("password").getValue(String.class);
                            if(nameFromDb.equals(password)){
                                String Name=snapshot.child(password).child("password").getValue(String.class);
                                String limit=snapshot.child(password).child("limit").getValue(String.class);
                                Intent intent=new Intent(getApplicationContext(),MyLoanLimitInfo.class);
                                intent.putExtra("name",Name);
                                intent.putExtra("limit",limit);
                                startActivity(intent);
                            }
                            else {
                                etPassword.setError("Invalid passcode");
                                etPassword.requestFocus();
                            }
                        }
                        else {
                            etPassword.setError("Your Passcode does not exist");
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