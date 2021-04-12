package com.example.changskitchen.models;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static com.google.firebase.auth.FirebaseAuth.getInstance;

public class Customer {
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference("server/saving-data/fireblog");

    public String customerID;
    public String customerFirstName;
    public String customerLastName;
    public String customerAddress;
    public String customerPhone;

    public Customer() { customerID =  getInstance().getCurentCustomer().getorderID();   }

    public void saveToDatabase() {
        DatabaseReference usersRef = ref.child("customers");
        usersRef.child(customerID).setValue(this);
    }

}
