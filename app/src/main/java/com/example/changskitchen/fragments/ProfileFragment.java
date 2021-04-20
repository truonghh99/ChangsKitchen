package com.example.changskitchen.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.changskitchen.R;
import com.example.changskitchen.activities.LoginActivity;
import com.example.changskitchen.activities.MainActivity;
import com.example.changskitchen.databinding.FragmentProfileBinding;
import com.example.changskitchen.models.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    FragmentProfileBinding fragmentProfileBinding;
    EditText etName;
    EditText etEmail;
    EditText etPhone;
    EditText etPassword;
    Button btUpdate;
    Button btLogout;

    public ProfileFragment() {
    }

    public static ProfileFragment newInstance() {
        ProfileFragment fragment = new ProfileFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentProfileBinding = FragmentProfileBinding.inflate(getLayoutInflater());
        etName = fragmentProfileBinding.etName;
        etEmail = fragmentProfileBinding.etEmail;
        etPassword = fragmentProfileBinding.etPassword;
        etPhone = fragmentProfileBinding.etPhone;
        btUpdate = fragmentProfileBinding.btUpdate;
        btLogout = fragmentProfileBinding.btLogout;

        getInfo();
        btUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveInfo();
            }
        });
        btLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });
        return fragmentProfileBinding.getRoot();
    }

    private void logout() {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mAuth.signOut();
        goToLogin();
    }

    private void goToLogin() {
        Intent intent = new Intent(getContext(), LoginActivity.class);
        startActivity(intent);
    }

    private void saveInfo() {
        User user = new User();
        user.name = etName.getText().toString();
        user.password = etPassword.getText().toString();
        user.email = etEmail.getText().toString();
        user.phone = etPhone.getText().toString();
        user.saveToDatabase();
        Toast.makeText(getContext(), "Your information has been updated!", Toast.LENGTH_SHORT).show();
    }

    public void getInfo() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("server/saving-data/fireblog");
        DatabaseReference usersRef = ref.child("users").child(user.getUid());
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                etName.setText(user.name);
                etEmail.setText(user.email);
                etPassword.setText(user.password);
                etPhone.setText(user.phone);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
            }
        };
        usersRef.addValueEventListener(postListener);
    }
}