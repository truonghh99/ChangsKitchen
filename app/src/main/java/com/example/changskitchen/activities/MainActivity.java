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
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
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
                            case R.id.miHistory:
                                fragment = historyFragment;
                                toolbar.setTitle("Your Orders");
                                break;
                            case R.id.miCurrent:
                                fragment = currentMenuFragment;
                                toolbar.setTitle("Today's Menu");
                                break;
                            case R.id.miFuture:
                                fragment = futureMenusFragment;
                                toolbar.setTitle("This week's Menus");
                                break;
                            case R.id.miContact:
                                fragment = contactFragment;
                                toolbar.setTitle("Contact Restaurant");
                                break;
                            default:
                                break;
                        }
                        switchFragment(fragment);
                        return true;
                    }
                });

        bottomNavigation.setSelectedItemId(R.id.miCurrent);
    }

    public static void switchFragment(Fragment fragment) {
        fragmentManager.beginTransaction()
                .replace(R.id.flContainer, fragment)
                .addToBackStack(null)
                .commit();
    }
}