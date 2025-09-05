package com.app.rentpg;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        EdgeToEdge.enable(this);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });



        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                // Your custom back press logic
                finishAffinity(); // closes all activities
                // System.exit(0); // not recommended, but optional
            }
        });
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);

        // Load default fragment
        if (savedInstanceState == null) {
            loadFragment(new HomeFragment());
        }

        bottomNav.setOnItemSelectedListener(item -> {
            // Animate selected icon
            int itemId = item.getItemId();
            View view = bottomNav.findViewById(itemId);
            view.animate().scaleX(1.2f).scaleY(1.2f).setDuration(200).withEndAction(() ->
                    view.animate().scaleX(1.0f).scaleY(1.0f).setDuration(200)
            ).start();

            // Switch fragment
            Fragment selectedFragment = null;
            if (itemId == R.id.nav_home) {
                selectedFragment = new HomeFragment();
            } else if (itemId == R.id.nav_settings) {
                selectedFragment = new SettingFragment();
            } else if (itemId == R.id.nav_profile) {
                selectedFragment = new NoticFragment();
            }
            if (selectedFragment != null) {
                loadFragment(selectedFragment);
                return true;
            }
            return false;
        });

    }



    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(
                        android.R.anim.slide_in_left,   // enter animation
                        android.R.anim.fade_out,        // exit animation
                        android.R.anim.fade_in,         // pop enter animation
                        android.R.anim.slide_out_right  // pop exit animation
                )
                .replace(R.id.fragment_screen, fragment)
                .addToBackStack(null)
                .commit();
    }

}
