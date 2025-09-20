package com.app.rentpg;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class HomeActivity extends AppCompatActivity {

    LinearLayout navHome, navSettings, navProfile;
    GradientDrawable defaultDrawable, selectedDrawable;

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

        navHome = findViewById(R.id.nav_home);
        navSettings = findViewById(R.id.nav_settings);
        navProfile = findViewById(R.id.nav_profile);

        // Create default & selected drawables
        defaultDrawable = new GradientDrawable();
        defaultDrawable.setColor(Color.parseColor("#2196F3")); // Blue


        selectedDrawable = new GradientDrawable();
        selectedDrawable.setColor(Color.parseColor("#1976D2")); // Darker Blue when selected
        selectedDrawable.setCornerRadius(100);

        // Set click listeners
        setClick(navHome, new HomeFragment());
        setClick(navSettings, new SettingFragment());
        setClick(navProfile, new NoticFragment());

        // Load default fragment
        selectNav(navHome);
        newLoadFragment(new HomeFragment());
    }

    // Utility method for click + animation + fragment load
    private void setClick(View view, Fragment fragment) {
        view.setBackground(defaultDrawable);
        view.setOnClickListener(v -> {
            animateClick(v, () -> {
                newLoadFragment(fragment);
                selectNav(view);
            });
        });
    }

    // Highlight selected nav
    private void selectNav(View selectedView) {
        navHome.setBackground(defaultDrawable);
        navSettings.setBackground(defaultDrawable);
        navProfile.setBackground(defaultDrawable);

        selectedView.setBackground(selectedDrawable);
    }

    // Load fragment helper
    private void newLoadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.commit();
    }

    // Smooth modern click animation with callback
    private void animateClick(View view, Runnable onEnd) {
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(view, "scaleX", 1f, 0.9f, 1f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(view, "scaleY", 1f, 0.9f, 1f);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(scaleX, scaleY);
        animatorSet.setDuration(200);
        animatorSet.setInterpolator(new AccelerateDecelerateInterpolator());
        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override public void onAnimationStart(Animator animation) { }
            @Override public void onAnimationEnd(Animator animation) { onEnd.run(); }
            @Override public void onAnimationCancel(Animator animation) { }
            @Override public void onAnimationRepeat(Animator animation) { }
        });
        animatorSet.start();
    }
}
