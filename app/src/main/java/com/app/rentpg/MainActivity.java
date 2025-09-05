package com.app.rentpg;

import android.graphics.Color;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        getWindow().setStatusBarColor(Color.parseColor("#FF000000"));
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.fragment_container), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });



        String screen= getIntent().getStringExtra("screen");

        Fragment fragment;

        if (screen == null) {
            screen = "default";
        }

        switch (screen) {
            case "home":
                newLoadFragment(new HomeFragment());
                break;

            case "setting":
                newLoadFragment(new SettingFragment());
                break;

            case "notic":
                // call the fragment's own newInstance() method
                newLoadFragment(NoticFragment.newInstance("param1", "param2"));
                break;

            default:
                newLoadFragment(new SplashFragment());
        }
    }

    private void newLoadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.commit();
    }
}