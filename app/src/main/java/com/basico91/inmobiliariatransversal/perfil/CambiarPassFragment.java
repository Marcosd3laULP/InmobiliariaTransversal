package com.basico91.inmobiliariatransversal.perfil;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.basico91.inmobiliariatransversal.R;
import com.basico91.inmobiliariatransversal.databinding.FragmentCambiarPassBinding;

public class CambiarPassFragment extends Fragment {
    CambiarPassViewModel vm;
    FragmentCambiarPassBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
    binding = FragmentCambiarPassBinding.inflate(inflater, container, false);
    vm = new ViewModelProvider(this).get(CambiarPassViewModel.class);

    binding.btCambiarContrasenia.setOnClickListener(v -> {
        vm.cambiarPasswords(binding.etActual.getText().toString(),
                binding.etNueva.getText().toString());
    });
    return binding.getRoot();
    }
}