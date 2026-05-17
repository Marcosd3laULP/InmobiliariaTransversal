package com.basico91.inmobiliariatransversal.perfil;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.basico91.inmobiliariatransversal.R;
import com.basico91.inmobiliariatransversal.databinding.FragmentEditarPerfilBinding;
import com.basico91.inmobiliariatransversal.databinding.FragmentPerfilBinding;


public class EditarPerfilFragment extends Fragment {

    private EditarPerfilViewModel vm;
    private FragmentEditarPerfilBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentEditarPerfilBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        vm = new ViewModelProvider(this).get(EditarPerfilViewModel.class);

        vm.RecuperarPropietarioBundle(getArguments());

        vm.getPropietarioEdit().observe(getViewLifecycleOwner(), propietario -> {
            binding.etNombreEdit.setText(propietario.getNombre());
            binding.etApellidoEdit.setText(propietario.getApellido());
            binding.etDniEdit.setText(propietario.getDni());
            binding.etTelefonoEdit.setText(propietario.getTelefono());
            binding.etEmailPerfilEdit.setText(propietario.getEmail());
        });

        binding.btGuardarCambios.setOnClickListener(v -> {
            vm.guardarCambios(
                    binding.etNombreEdit.getText().toString(),
                    binding.etApellidoEdit.getText().toString(),
                    binding.etDniEdit.getText().toString(),
                    binding.etTelefonoEdit.getText().toString(),
                    binding.etEmailPerfilEdit.getText().toString()
            );
        });
        return root;
    }
    @Override
    public void onDestroyView(){
        super.onDestroyView();
        binding = null;
    }
}