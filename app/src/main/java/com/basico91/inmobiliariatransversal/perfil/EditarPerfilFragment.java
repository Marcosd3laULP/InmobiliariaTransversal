package com.basico91.inmobiliariatransversal.perfil;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.basico91.inmobiliariatransversal.R;
import com.basico91.inmobiliariatransversal.databinding.FragmentEditarPerfilBinding;
import com.basico91.inmobiliariatransversal.databinding.FragmentPerfilBinding;


public class EditarPerfilFragment extends Fragment {
    private FragmentEditarPerfilBinding binding;

    private EditarPerfilViewModel vm;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentEditarPerfilBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        vm = new ViewModelProvider(this).get(EditarPerfilViewModel.class);

        vm.RecuperarPropietarioBundle(getArguments());

        vm.getPropietarioEdit().observe(getViewLifecycleOwner(), propietario -> {
            binding.etNombreEditar.setText(propietario.getNombre());
            binding.etApellidoEditar.setText(propietario.getApellido());
            binding.etDniEditar.setText(propietario.getDni());
            binding.etEmailEditar.setText(propietario.getEmail());
            binding.etTelefonoEditar.setText(propietario.getTelefono());

        });

        binding.btGuardarCambios.setOnClickListener(v -> {
            vm.guardarCambios(
                    binding.etNombreEditar.getText().toString(),
                    binding.etApellidoEditar.getText().toString(),
                    binding.etDniEditar.getText().toString(),
                    binding.etTelefonoEditar.getText().toString(),
                    binding.etEmailEditar.getText().toString()


            );
        });

        vm.getMensaje().observe(getViewLifecycleOwner(), textoMensaje -> {

                binding.tvFeedbackUsuario.setText(textoMensaje);
                binding.tvFeedbackUsuario.setTextColor(Color.RED);
                binding.tvFeedbackUsuario.setVisibility(View.VISIBLE);
                });


        vm.getExito().observe(getViewLifecycleOwner(), unicamenteSiEsExitoso -> {
            Toast.makeText(getContext(), "Perfil actualizado con éxito", Toast.LENGTH_SHORT).show();
            Navigation.findNavController(getView()).popBackStack();
        });
    }
    @Override
    public void onDestroyView(){
        super.onDestroyView();
        binding = null;
    }
}