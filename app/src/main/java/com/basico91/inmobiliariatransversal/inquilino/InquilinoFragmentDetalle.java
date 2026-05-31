package com.basico91.inmobiliariatransversal.inquilino;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.basico91.inmobiliariatransversal.contrato.ContratoViewModel;
import com.basico91.inmobiliariatransversal.databinding.FragmentContratoDetalleBinding;
import com.basico91.inmobiliariatransversal.databinding.FragmentInquilinoDetalleBinding;


public class InquilinoFragmentDetalle extends Fragment {

    private FragmentInquilinoDetalleBinding binding;
    private InquilinoViewModel vm;
    private int idContratoActual = -1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentInquilinoDetalleBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        vm = new ViewModelProvider(this).get(InquilinoViewModel.class);

        vm.recuperarInquilino(getArguments());

        vm.getInquilino().observe(getViewLifecycleOwner(), inquilinoContrato ->{
            binding.tvCodigoCampo.setText(String.valueOf(inquilinoContrato.getIdInquilino()));
            binding.tvNombreCampo.setText(inquilinoContrato.getInquilino().getNombre());
            binding.tvApellidoCampo.setText(inquilinoContrato.getInquilino().getApellido());
            binding.tvDniCampo.setText(inquilinoContrato.getInquilino().getDni());
            binding.tvEmailCampo.setText(inquilinoContrato.getInquilino().getEmail());
            binding.tvTelefonoCampo.setText("" + inquilinoContrato.getInquilino().getTelefono());
            binding.tvGaranteCampo.setText(inquilinoContrato.getInmueble().getDuenio().getNombre());
            binding.tvTelGaranteCampo.setText("" + inquilinoContrato.getInmueble().getDuenio().getTelefono());
        });
    }
}