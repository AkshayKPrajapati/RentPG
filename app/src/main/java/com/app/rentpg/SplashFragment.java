package com.app.rentpg;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Debug;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.app.rentpg.admin.AdminActivity;

import java.util.Timer;
import java.util.TimerTask;


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


        appName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent= new Intent(requireContext(),AdminActivity.class);
                requireActivity().startActivity(intent);


               // requireActivity().startActivity(new Intent(requireContext(), AdminActivity.class));
                Toast.makeText(requireActivity(), "AdminScreenOpen", Toast.LENGTH_SHORT).show();
            }
        });


        // Debug checks
        /*
        if (BuildConfig.DEBUG) {
            Toast.makeText(requireContext(), "App built in Debug mode", Toast.LENGTH_SHORT).show();
        }

         */

        if (Debug.isDebuggerConnected()) {
            Toast.makeText(requireContext(), "Debugger is attached", Toast.LENGTH_SHORT).show();
        }
        /*
        Switch to the main Screen
         */
        view.postDelayed(() -> {
            if (isAdded()) {
                Intent intent = new Intent(requireActivity(), HomeActivity.class);
                startActivity(intent);
                requireActivity().finish();
            }
        }, 3000);


        return  view;
    }
}