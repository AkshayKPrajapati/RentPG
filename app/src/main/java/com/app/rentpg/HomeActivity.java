package com.app.rentpg;

import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {

    // Declare IDs as constants
    private final int navHome = R.id.nav_home;
    private final int navSetting = R.id.nav_settings;
    private final int navProfile = R.id.nav_profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);

        // Handle system bars padding
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        BottomNavigationView bottomNav = findViewById(R.id.bottom_nav);

        // Load default fragment
        loadFragment(new HomeFragment());

        // Handle bottom navigation item clicks
        bottomNav.setOnItemSelectedListener(item -> {
            Fragment fragment = null;

            if (item.getItemId() == navHome) {
                fragment = new HomeFragment();
            } else if (item.getItemId() == navSetting) {
                fragment = new SettingFragment();
            } else if (item.getItemId() == navProfile) {
                fragment = new NoticFragment();
            }

            if (fragment != null) {
                loadFragment(fragment);
                return true;
            }

            return false;
        });

    }

    // Helper method to load fragments
    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.commit();
    }

}
