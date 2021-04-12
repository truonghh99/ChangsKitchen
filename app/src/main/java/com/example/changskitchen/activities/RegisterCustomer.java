package com.example.changskitchen.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.changskitchen.R;
import com.example.changskitchen.models.Customer;
import com.example.changskitchen.models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterCustomer extends AppCompatActivity {

    private FirebaseDatabase mCustomer;
    private DatabaseReference databaseReference;
    private Customer customer;




    private String customerFirstName;
    private String customerLastName;
    private String customerAddress;
    private String customerPhone;
    private Button registerButton;
    private TextView tvLogin;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCustomer = FirebaseDatabase.getInstance();
        registerButton = findViewById(R.id.idregisterButton);
        tvLogin = findViewById(R.id.idtvLogin);
        registerButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                customerFirstName = findViewById(R.id.idcustomerFirstName);
                customerLastName = findViewById(R.id.idcustomerLastName);
                customerAddress = findViewById(R.id.idcustomerAddress);
                customerPhone = findViewById(R.id.idcustomerPhone);
                register(customerFirstName, customerLastName, customerAddress, customerPhone);

            }
        });

        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToRegister();
            }
        });


    }

    private void register(final String first_name,final String last_name,final String address, final String phone) {
        .addOnCompleteListener(this, new OnCompleteListener<CustomerResult>() {
            @Override
            public void onComplete(@NonNull Task<CustomerResult> task) {
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    Customer customer = new Customer();
                    customer.customerFirstName = first_name;
                    customer.customerLastName = last_name;
                    customer.customerAddress = address;
                    customer.customerPhone = phone;
                    customer.saveToDatabase();
                    goToMain();
                } else {

                    Toast.makeText(RegisterCustomer.this, "Registration failed.",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void goToMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void goToRegister() {
        Intent intent = new Intent(this, RegisterCustomer.class);
        startActivity(intent);
    }

}
