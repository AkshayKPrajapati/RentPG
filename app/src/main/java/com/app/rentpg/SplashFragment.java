package com.app.rentpg;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
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

    public SplashFragment() {
        // Required empty public constructor
    }



    @SuppressLint("ResourceAsColor")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_splash, container, false);

        TextView versionName = view.findViewById(R.id.versionName);
        TextView developBy = view.findViewById(R.id.developBy);


        try {
            Context context = getContext(); // or requireContext()
            PackageInfo pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);

            String vName = pInfo.versionName;
            int vCode = pInfo.versionCode;

            versionName.setText("v" + vName +"."+ vCode);
            developBy.setText("Develop By Akshay");
            developBy.setTextColor(Color.parseColor("#FFE400"));

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        TextView appName=view.findViewById(R.id.appName);
        appName.setAlpha(0f);
        appName.animate()
                .alpha(1f)
                .setDuration(2000) // 2 seconds
                .setListener(null);

        /*
        Switch to the main Screen
         */
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            if (getActivity() != null) {
                Intent intent = new Intent(requireActivity(), MainActivity.class);
                intent.putExtra("screen", "login");
                startActivity(intent);
                requireActivity().finish();
            }
        }, 3000);


        return  view;
    }
}