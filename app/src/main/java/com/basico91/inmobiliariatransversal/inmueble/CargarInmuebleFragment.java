package com.basico91.inmobiliariatransversal.inmueble;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.net.Uri;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.basico91.inmobiliariatransversal.databinding.FragmentInmuebleCargarBinding;
import com.bumptech.glide.Glide;

public class CargarInmuebleFragment extends Fragment {

    private FragmentInmuebleCargarBinding binding;
    private CargarInmuebleViewModel vm;
    private ActivityResultLauncher<String> abrirGaleriaLauncher;

    private Uri uriImagenSeleccionada;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Configuramos el launcher para la galería
        abrirGaleriaLauncher = registerForActivityResult(
                new ActivityResultContracts.GetContent(),
                uri -> {
                    if (uri != null) {
                        uriImagenSeleccionada = uri;

                        // Mostramos la vista previa en el ImageView
                        Glide.with(requireContext())
                                .load(uri)
                                .into(binding.ivCargarImagen); // Tu ImageView del XML
                    }
                }
        );
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentInmuebleCargarBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        vm = new ViewModelProvider(this).get(CargarInmuebleViewModel.class);

        binding.btCargarImagen.setOnClickListener(v -> {
            abrirGaleriaLauncher.launch("image/*");
        });
        binding.btCargar.setOnClickListener(v -> {

            String direccion = binding.etCargarDireccion.getText().toString();
            String valor = binding.etCargarValor.getText().toString();
            String uso =  binding.etCargarUso.getText().toString();
            String tipo = binding.etCargarTipo.getText().toString();
            String ambientes = binding.etCargarAmbiente.getText().toString();
            String superficie = binding.etCargarSuperficie.getText().toString();
            String latitud = binding.etCargarLatitud.getText().toString();
            String longitud = binding.etCargarLongitud.getText().toString();

            vm.cargarInmueble(
                    uriImagenSeleccionada,
                    direccion,
                    valor,
                    uso,
                    tipo,
                    ambientes,
                    superficie,
                    latitud,
                    longitud
            );
        });
    vm.getError().observe(getViewLifecycleOwner(), new Observer<String>() {
        @Override
        public void onChanged(String s) {
            binding.tvCargarInmueble.setText(s);
        }
    });
    }
}
