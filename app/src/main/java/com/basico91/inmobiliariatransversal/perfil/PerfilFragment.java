package com.basico91.inmobiliariatransversal.perfil;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.basico91.inmobiliariatransversal.R;
import com.basico91.inmobiliariatransversal.databinding.FragmentPerfilBinding;
import com.basico91.inmobiliariatransversal.modelos.Propietario;
import com.basico91.inmobiliariatransversal.perfil.PerfilViewModel;

public class PerfilFragment extends Fragment {
    private FragmentPerfilBinding binding;
    private PerfilViewModel vm;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPerfilBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        vm = new ViewModelProvider(this).get(PerfilViewModel.class);

        // 1. Observador para pintar los datos cuando Retrofit responda exitosamente
        vm.getPropiertario().observe(getViewLifecycleOwner(), new Observer<Propietario>() {
            @Override
            public void onChanged(Propietario propietario) {
                if (propietario != null) {
                    binding.etNombre.setText(propietario.getNombre());
                    binding.etApellido.setText(propietario.getApellido());
                    binding.etDni.setText(propietario.getDni());
                    binding.etEmailPerfil.setText(propietario.getEmail());
                    binding.etTelefono.setText(propietario.getTelefono());
                }
            }
        });

        // 2. NUEVO: Observador de errores para saber EXACTAMENTE qué está fallando
        vm.getMensaje().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String mensaje) {
                if (mensaje != null) {
                    Toast.makeText(getContext(), mensaje, Toast.LENGTH_LONG).show();
                }
            }
        });

        // 3. Disparamos la carga de datos de la API
        vm.cargarDatos();

        // 4. Configuración del botón de edición corregido (Clave minúscula y control de nulos)
        binding.btEditarPerfil.setOnClickListener(v -> {
            Propietario actual = vm.getPropiertario().getValue();

            if (actual != null) {
                Bundle bundle = new Bundle();
                // CORREGIDO: "propietario" con p minúscula para coincidir con el EditarPerfilViewModel
                bundle.putSerializable("propietario", actual);

                NavController nav = Navigation.findNavController(v);
                nav.navigate(R.id.action_nav_perfil_to_editarPerfilFragment, bundle);
            } else {
                Toast.makeText(getContext(), "Espera a que carguen los datos para poder editarlos", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}