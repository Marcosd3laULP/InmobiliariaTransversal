package com.basico91.inmobiliariatransversal.perfil;

import static android.graphics.Color.RED;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
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
    return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        vm = new ViewModelProvider(this).get(CambiarPassViewModel.class);

        binding.btCambiarContrasenia.setOnClickListener(v -> {
            vm.cambiarPasswords(binding.etActual.getText().toString(),
                    binding.etNueva.getText().toString());
        });

        vm.getError().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                binding.tvMensajeFeedback.setTextColor(RED);
                binding.tvMensajeFeedback.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public void onDestroyView(){
        super.onDestroyView();
        binding = null;
    }
}
