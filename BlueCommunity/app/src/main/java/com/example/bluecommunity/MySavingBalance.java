package com.example.bluecommunity;

import android.annotation.SuppressLint;
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

public class MySavingBalance extends AppCompatActivity {
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://sunrisecommunity-95d96-default-rtdb.firebaseio.com/");
    private EditText etPasscode;
    private Button request;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_saving_balance);
        etPasscode=findViewById(R.id.etPasscode);
        request=findViewById(R.id.request);
        request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String passcode = etPasscode.getText().toString();
                databaseReference=FirebaseDatabase.getInstance().getReference("savings");
                Query checkId=databaseReference.orderByChild("passcode").equalTo(passcode);
                checkId.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()){
                            etPasscode.setError(null);
                            String lastnameFromDb=snapshot.child(passcode).child("passcode").getValue(String.class);
                            if(lastnameFromDb.equals(passcode)){
                                String firstname=snapshot.child(passcode).child("firstname").getValue(String.class);
                                String lastname1=snapshot.child(passcode).child("lastname").getValue(String.class);
                                String email=snapshot.child(passcode).child("email").getValue(String.class);
                                String date=snapshot.child(passcode).child("date").getValue(String.class);
                                Intent intent=new Intent(getApplicationContext(),MySavingBalanceInfo.class);
                                intent.putExtra("firstname",firstname);
                                intent.putExtra("lastname",lastname1);
                                intent.putExtra("email",email);
                                intent.putExtra("date",date);
                                startActivity(intent);
                            }
                            else {
                                etPasscode.setError("Invalid passcode");
                                etPasscode.requestFocus();
                            }
                        }
                        else {
                            etPasscode.setError("Your passcode does not exist");
                            etPasscode.requestFocus();
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