package com.example.changskitchen.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.changskitchen.R;
import com.example.changskitchen.databinding.ActivityMainBinding;
import com.example.changskitchen.fragments.ContactFragment;
import com.example.changskitchen.fragments.CurrentMenuFragment;
import com.example.changskitchen.fragments.FutureMenusFragment;
import com.example.changskitchen.fragments.HistoryFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    final Fragment contactFragment = ContactFragment.newInstance();
    final Fragment currentMenuFragment = CurrentMenuFragment.newInstance();
    final Fragment futureMenusFragment = FutureMenusFragment.newInstance();
    final Fragment historyFragment = HistoryFragment.newInstance();


    private Toolbar toolbar;
    private FirebaseAuth mAuth;

    public static BottomNavigationView bottomNavigation;
    private static FragmentManager fragmentManager;
    private ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());
        mAuth = FirebaseAuth.getInstance();

        bottomNavigation = findViewById(R.id.bottomNavigation);
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Your Profile");
        setSupportActionBar(toolbar);
        setUpBottomBar();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.miLogout:
                mAuth.signOut();
                goToLogin();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void goToLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    private void setUpBottomBar() {
        fragmentManager = getSupportFragmentManager();
        bottomNavigation = findViewById(R.id.bottomNavigation);
        bottomNavigation.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        Fragment fragment = null;
                        switch (item.getItemId()) {
                            case R.id.miCompose:
                                fragment = composeFragment;
                                toolbar.setTitle("Add new image");
                                break;
                            case R.id.miProfile:
                                fragment = profileFragment;
                                toolbar.setTitle("Your Profile");
                                break;
                            case R.id.miLibrary:
                                fragment = libraryFragment;
                                toolbar.setTitle("Image Library");
                                break;
                            default:
                                break;
                        }
                        switchFragment(fragment);
                        return true;
                    }
                });

        bottomNavigation.setSelectedItemId(R.id.miLibrary);
    }

    public static void switchFragment(Fragment fragment) {
        fragmentManager.beginTransaction()
                .replace(R.id.flContainer, fragment)
                .addToBackStack(null)
                .commit();
    }
}