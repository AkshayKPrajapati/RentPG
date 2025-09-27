package com.app.rentpg;

import android.content.Intent;
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
    private RadioGroup radioGroup;
    private Button button;
    private TextView forgotPassword, signUpAccount;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        // Initialize views
        etUsername = view.findViewById(R.id.etUsername);
        etPassword = view.findViewById(R.id.etPassword);
        radioGroup = view.findViewById(R.id.roleGroup);
        button = view.findViewById(R.id.btnLogin);
        forgotPassword = view.findViewById(R.id.forgotPassword);
        signUpAccount = view.findViewById(R.id.signUpAccount);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Set input hints
        etUsername.setHint("Enter Username");
        etPassword.setHint("Enter Password");

        // Login Button Click
        button.setOnClickListener(v -> {
            String username = etUsername.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(getContext(), "Please enter username and password", Toast.LENGTH_SHORT).show();
                return;
            }

            // Get selected role
            int selectedId = radioGroup.getCheckedRadioButtonId();
            String role = "Customer"; // Default

            if (selectedId == R.id.rbOwner) {
                role = "Owner";
            } else if (selectedId == R.id.rbAdmin) {
                role = "Admin";
            }

            // Dummy credential check (you can replace this with real DB/Firebase logic)
            boolean valid = username.equals("admin") && password.equals("admin");

            if (valid) {
                if(role.equalsIgnoreCase("Customer")){
                    Toast.makeText(getContext(), "Login successful as " + role, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(requireActivity(), CustomerDashBoardActivity.class);
                    startActivity(intent);
                    requireActivity().finish();

                }


            } else {
                Toast.makeText(getContext(), "Invalid credentials for " + role, Toast.LENGTH_SHORT).show();
            }
        });

        // SignUp Click Listener
        signUpAccount.setOnClickListener(v -> {
            int selectedId = radioGroup.getCheckedRadioButtonId();
            String role = "Customer"; // default

            if (selectedId == R.id.rbOwner) {
                role = "Owner";
            } else if (selectedId == R.id.rbAdmin) {
                role = "Admin";
            }

            switch (role.toLowerCase()) {
                case "customer":
                    Toast.makeText(getContext(), "Customer account selected", Toast.LENGTH_SHORT).show();
                    Intent customerIntent = new Intent(requireActivity(), MainActivity.class);
                    customerIntent.putExtra("screen", "customerSignUp");
                    startActivity(customerIntent);
                    requireActivity().finish();
                    break;

                case "owner":
                    Toast.makeText(getContext(), "Owner account selected", Toast.LENGTH_SHORT).show();
                    // TODO: Navigate to OwnerSignUpActivity
                    break;

                case "admin":
                    Toast.makeText(getContext(), "Admin account selected", Toast.LENGTH_SHORT).show();
                    // TODO: Navigate to AdminSignUpActivity or handle logic
                    break;

                default:
                    Toast.makeText(getContext(), "Invalid role selected", Toast.LENGTH_SHORT).show();
                    break;
            }
        });
    }
}
