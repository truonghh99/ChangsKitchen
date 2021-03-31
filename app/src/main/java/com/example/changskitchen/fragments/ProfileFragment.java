package com.example.changskitchen.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.changskitchen.R;
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

        getInfo();

        return fragmentProfileBinding.getRoot();
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