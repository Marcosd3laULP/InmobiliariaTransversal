package com.basico91.inmobiliariatransversal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.basico91.inmobiliariatransversal.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {
    LoginActivityViewModel vm;
    ActivityLoginBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        vm = new ViewModelProvider(this).get(LoginActivityViewModel.class);

        binding.btInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usuario = binding.etEmail.getText().toString();
                String pass = binding.etpClave.getText().toString();

                vm.login(usuario, pass);
            }
        });

        vm.getToken().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);

                finish();
            }
        });

        vm.getErrorM().observe(this, mensaje -> {
            Toast.makeText(LoginActivity.this,mensaje, Toast.LENGTH_SHORT).show();
        });
    }
}