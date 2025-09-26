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
import android.widget.Toast;

public class LoginFragment extends Fragment {

    private EditText etUsername, etPassword;
    private RadioGroup radioGroup;
    private Button button;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        // Initialize views
        etUsername = view.findViewById(R.id.etUsername);
        etPassword = view.findViewById(R.id.etPassword);
        radioGroup = view.findViewById(R.id.roleGroup);
        button = view.findViewById(R.id.btnLogin);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Set hints
        etUsername.setHint("Enter Username");
        etPassword.setHint("Enter Password");

        // Handle login button click
        button.setOnClickListener(v -> {
            String username = etUsername.getText().toString().trim();

            String password = etPassword.getText().toString().trim();

            if(username.isEmpty() || password.isEmpty()) {
                Toast.makeText(getContext(), "Please enter username and password", Toast.LENGTH_SHORT).show();
                return;
            }

            // Get selected role
            int selectedId = radioGroup.getCheckedRadioButtonId();
            String role = "Customer"; // default
            if(selectedId == R.id.rbOwner) {
                role = "Owner";
            } else if(selectedId == R.id.rbAdmin) {
                role = "Admin";
            }

            // Validate credentials based on role
            boolean valid = false;
            if(role.equalsIgnoreCase("Admin") && username.equals("admin") && password.equals("admin")) {
                valid = true;
            } else if(role.equalsIgnoreCase("Owner") && username.equals("admin") && password.equals("admin")) {
                valid = true;
            } else if(role.equalsIgnoreCase("Customer") && username.equals("admin") && password.equals("admin")) {
                valid = true;
            }

            if(valid) {
                Toast.makeText(getContext(), "Login successful as " + role, Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(requireActivity(), MainActivity.class);
//                intent.putExtra("screen", role); // pass the role to MainActivity
//                startActivity(intent);
//                requireActivity().finish();
            } else {
                Toast.makeText(getContext(), "Invalid credentials for " + role, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
