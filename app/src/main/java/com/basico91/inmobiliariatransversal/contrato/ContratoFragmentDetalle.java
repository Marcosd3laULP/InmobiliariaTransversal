package com.basico91.inmobiliariatransversal.contrato;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.basico91.inmobiliariatransversal.R;
import com.basico91.inmobiliariatransversal.databinding.FragmentContratoDetalleBinding;


public class ContratoFragmentDetalle extends Fragment {

    private FragmentContratoDetalleBinding binding;
    private ContratoViewModel vm;
    private int idContratoActual = -1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
    binding = FragmentContratoDetalleBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    vm = new ViewModelProvider(this).get(ContratoViewModel.class);

    vm.RecuperarContrato(getArguments());


    vm.getContratoMutable().observe(getViewLifecycleOwner(), contrato -> {
        idContratoActual = contrato.getIdContrato();
        binding.tvCodigoCampoContrato.setText(String.valueOf(contrato.getIdContrato()));
        binding.tvFechaInicioCampo.setText(contrato.getFechaInicio());
        binding.tvFechaFinCampo.setText(contrato.getFechaFinalizacion());
        binding.tvMontoCampo.setText(String.valueOf(contrato.getMontoAlquler()));
        binding.tvInquilinoCampoContrato.setText(contrato.getInquilino().getNombre());
        binding.tvInmuebleCampoContrato.setText(contrato.getInmueble().getDireccion());

        binding.btPagosContrato.setEnabled(true);
    });
    binding.btPagosContrato.setOnClickListener(v ->{
     Bundle bundle = new Bundle();
     bundle.putInt("idContrato", idContratoActual);
        NavController nav = Navigation.findNavController(v);
        nav.navigate(R.id.action_contratoFragmentDetalle_to_pagoFragment, bundle);
         });
    }
}