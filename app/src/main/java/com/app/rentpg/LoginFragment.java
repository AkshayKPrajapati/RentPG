package com.app.rentpg;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class LoginFragment extends Fragment {

    private EditText etUsername, etPassword;
    private RadioGroup roleGroup;
    private Button btnLogin;
    private TextView signUpAccount;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public LoginFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        etUsername = view.findViewById(R.id.etUsername);
        etPassword = view.findViewById(R.id.etPassword);
        roleGroup = view.findViewById(R.id.roleGroup);
        btnLogin = view.findViewById(R.id.btnLogin);
        signUpAccount = view.findViewById(R.id.signUpAccount);

        sharedPreferences = requireActivity().getSharedPreferences("user_pref", getContext().MODE_PRIVATE);
        editor = sharedPreferences.edit();

        // Auto-login if already logged in as customer
        boolean isCustomerLoggedIn = sharedPreferences.getBoolean("isCustomerLogin", false);
        if (isCustomerLoggedIn) {
            startActivity(new Intent(requireActivity(), CustomerDashBoardActivity.class));
            requireActivity().finish();
            return;
        }

        btnLogin.setOnClickListener(v -> loginUser());
        signUpAccount.setOnClickListener(v -> navigateToSignUp());
    }

    private void loginUser() {
        String username = etUsername.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(getContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        int selectedRoleId = roleGroup.getCheckedRadioButtonId();
        String role = "Customer";
        if (selectedRoleId == R.id.rbOwner) {
            role = "Owner";
        } else if (selectedRoleId == R.id.rbAdmin) {
            role = "Admin";
        }

        // Dummy login check
        boolean valid = username.equals("admin") && password.equals("admin");

        if (valid) {
            editor.putString("username", username);
            editor.putString("role", role);

            // Save login status for customer only (can be expanded for other roles too)
            if (role.equalsIgnoreCase("Customer")) {
                editor.putBoolean("isCustomerLogin", true);
            }

            editor.apply();

            Intent intent;
            switch (role.toLowerCase()) {
                case "customer":
                    intent = new Intent(requireActivity(), CustomerDashBoardActivity.class);
                    break;
                case "owner":
                    intent = new Intent(requireActivity(), OwnerDashboardActivity.class);
                    break;
                case "admin":
                    intent = new Intent(requireActivity(), AdminDashboardActivity.class);
                    break;
                default:
                    Toast.makeText(getContext(), "Unknown role", Toast.LENGTH_SHORT).show();
                    return;
            }

            Toast.makeText(getContext(), "Login successful as " + role, Toast.LENGTH_SHORT).show();
            startActivity(intent);
            requireActivity().finish();
        } else {
            Toast.makeText(getContext(), "Invalid credentials", Toast.LENGTH_SHORT).show();
        }
    }

    private void navigateToSignUp() {
        int selectedRoleId = roleGroup.getCheckedRadioButtonId();
        String role = "Customer";
        if (selectedRoleId == R.id.rbOwner) {
            role = "Owner";
        } else if (selectedRoleId == R.id.rbAdmin) {
            role = "Admin";
        }

        Intent intent = new Intent(requireActivity(), MainActivity.class);
        intent.putExtra("screen", role.toLowerCase() + "SignUp");
        startActivity(intent);
        requireActivity().finish();
    }
}
