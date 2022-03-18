package com.example.firebasse;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    Button btnDangNhap;
    TextView useremail, userpass;
    EditText edtEmail, edtPass;
    FirebaseDatabase fDatabase;
    DatabaseReference dRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnDangNhap = findViewById(R.id.buttonDangNhap);
        edtEmail = findViewById(R.id.editTextEmail);
        edtPass = findViewById(R.id.editTextPassword);
        useremail = findViewById(R.id.textViewUserEmail);
        userpass = findViewById(R.id.textViewUserPass);

        fDatabase = FirebaseDatabase.getInstance();
        dRef = fDatabase.getReference().child("account2");

        dRef.child("name").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    useremail.setText(snapshot.getValue(String.class));
                }
                else {
                    useremail.setText("Not Found");

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        dRef.child("phone").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    userpass.setText(snapshot.getValue(String.class));
                }
                else {
                    useremail.setText("Not Found");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = edtEmail.getText().toString();
                String pass = edtPass.getText().toString();

                dRef.child("name").setValue(email);
                dRef.child("phone").setValue(pass);


            }
        });
    }
}