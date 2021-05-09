package com.example.changskitchen.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.changskitchen.R;
import com.example.changskitchen.databinding.ActivityMainBinding;
import com.example.changskitchen.fragments.CartFragment;
import com.example.changskitchen.fragments.ContactFragment;
import com.example.changskitchen.fragments.MenuFragment;
import com.example.changskitchen.fragments.FutureMenusFragment;
import com.example.changskitchen.fragments.HistoryFragment;
import com.example.changskitchen.fragments.ProfileFragment;
import com.example.changskitchen.helpers.DateHelper;
import com.example.changskitchen.storage.CurrentOrder;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;


import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private Fragment currentMenuFragment;
    private Fragment futureMenusFragment;
    private Fragment historyFragment;
    private Fragment profileFragment;
    private Fragment contactFragment;
    private Fragment cartFragment;


    private static Toolbar toolbar;
    private FirebaseAuth mAuth;
    private String title;

    public static BottomNavigationView bottomNavigation;
    public static SearchView searchView;
    private static FragmentManager fragmentManager;
    private ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());
        mAuth = FirebaseAuth.getInstance();

        currentMenuFragment = MenuFragment.newInstance(DateHelper.convertToMenuId(new Date()));
        futureMenusFragment = FutureMenusFragment.newInstance();
        historyFragment = HistoryFragment.newInstance();
        profileFragment = ProfileFragment.newInstance();
        contactFragment = ContactFragment.newInstance("");
        cartFragment = CartFragment.newInstance();

        bottomNavigation = findViewById(R.id.bottomNavigation);
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Your Profile");
        setSupportActionBar(toolbar);
        setUpBottomBar();
        CurrentOrder.fetchAvailableDish(DateHelper.convertToMenuId(new Date()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        MenuItem searchItem = menu.findItem(R.id.miSearch);
        searchView = (SearchView) searchItem.getActionView();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.miCart:
                switchFragment(cartFragment, "Your Cart");
                break;
            case R.id.miProfile:
                switchFragment(profileFragment, "Your Profile");
                break;
        }
        bottomNavigation.getMenu().setGroupCheckable(0, false, true);
        return super.onOptionsItemSelected(item);
    }

    private void setUpBottomBar() {
        fragmentManager = getSupportFragmentManager();
        bottomNavigation = findViewById(R.id.bottomNavigation);
        bottomNavigation.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        Fragment fragment = null;
                        bottomNavigation.getMenu().setGroupCheckable(0, true, true);
                        switch (item.getItemId()) {
                            case R.id.miHistory:
                                fragment = historyFragment;
                                title = "Your Orders";
                                break;
                            case R.id.miCurrent:
                                fragment = currentMenuFragment;
                                title = "Today's Menu";
                                break;
                            case R.id.miFuture:
                                fragment = futureMenusFragment;
                                title = "This Week's Menus";
                                break;
                            case R.id.miContact:
                                fragment = contactFragment;
                                title = "Contact Restaurant";
                                break;
                            default:
                                break;
                        }
                        switchFragment(fragment, title);
                        return true;
                    }
                });
        bottomNavigation.setSelectedItemId(R.id.miCurrent);
    }

    public static void switchFragment(Fragment fragment, String title) {
        fragmentManager.beginTransaction()
                .replace(R.id.flContainer, fragment)
                .addToBackStack(null)
                .commit();
        toolbar.setTitle(title);
    }

    public static void switchToContactFragment(String orderId) {
        Fragment contactFragment = ContactFragment.newInstance(orderId);
        fragmentManager.beginTransaction()
                .replace(R.id.flContainer, contactFragment)
                .addToBackStack(null)
                .commit();
        toolbar.setTitle("Contact Restaurant");
    }

    public static void showDialogFragment(DialogFragment fragment, String tag) {
        fragment.show(fragmentManager, tag);
    }
}