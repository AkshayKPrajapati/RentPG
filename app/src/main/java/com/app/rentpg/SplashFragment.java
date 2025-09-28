package com.app.rentpg;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class SplashFragment extends Fragment {

    public SplashFragment() {}

    @SuppressLint("ResourceAsColor")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_splash, container, false);

        TextView versionName = view.findViewById(R.id.versionName);
        TextView developBy = view.findViewById(R.id.developBy);
        TextView appName = view.findViewById(R.id.appName);

        try {
            Context context = requireContext();
            PackageInfo pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            String vName = pInfo.versionName;
            int vCode = pInfo.versionCode;

            versionName.setText("v" + vName + "." + vCode);
            developBy.setText("Developed by Akshay");
            developBy.setTextColor(Color.parseColor("#FFE400"));

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        appName.setAlpha(0f);
        appName.animate()
                .alpha(1f)
                .setDuration(2000)
                .setListener(null);

        // Navigate after delay
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("user_pref", Context.MODE_PRIVATE);
            boolean isCustomerLogin = sharedPreferences.getBoolean("isCustomerLogin", false);

            Intent intent;
            if (isCustomerLogin) {
                intent = new Intent(requireActivity(), CustomerDashBoardActivity.class);
            } else {
                intent = new Intent(requireActivity(), MainActivity.class);
                intent.putExtra("screen", "login");
            }

            startActivity(intent);
            requireActivity().finish();
        }, 3000);

        return view;
    }
}
