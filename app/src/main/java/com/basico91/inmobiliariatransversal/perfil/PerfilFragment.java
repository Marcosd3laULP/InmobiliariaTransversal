package com.basico91.inmobiliariatransversal.perfil;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.basico91.inmobiliariatransversal.databinding.FragmentPerfilBinding;
import com.basico91.inmobiliariatransversal.modelos.Propietario;

public class PerfilFragment extends Fragment {
    private FragmentPerfilBinding binding;
    private PerfilViewModel vm;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPerfilBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        vm = new ViewModelProvider(this).get(PerfilViewModel.class);


        vm.getPropiertario().observe(getViewLifecycleOwner(), new Observer<Propietario>() {
            @Override
            public void onChanged(Propietario propietario) {
                binding.etNombre.setText(propietario.getNombre());
                binding.etApellido.setText(propietario.getApellido());
                binding.etDni.setText(propietario.getDni());
                binding.etEmailPerfil.setText(propietario.getEmail());
            }
        });
        vm.cargarDatos();

        binding.btEditarPerfil.setOnClickListener(v ->{
            Propietario actual = vm.getPropiertario().getValue();

            Bundle bundle = new Bundle();
            bundle.putSerializable("Propietario", actual);
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}