package com.example.changskitchen.models;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class User {

    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference();

    public String uid;
    public String email;
    public String password;
    public String name;
    public String phone;

    public User() {
        uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
    }

    public void saveToDatabase() {
        DatabaseReference usersRef = ref.child("users");
        usersRef.child(uid).setValue(this);
    }
}
