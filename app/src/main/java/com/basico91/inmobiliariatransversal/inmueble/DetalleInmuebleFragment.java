package com.basico91.inmobiliariatransversal.inmueble;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.basico91.inmobiliariatransversal.databinding.FragmentInmuebleDetalleBinding;

public class DetalleInmuebleFragment extends Fragment {
    private FragmentInmuebleDetalleBinding binding;
    private InmuebleViewModel vm;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = FragmentInmuebleDetalleBinding.inflate(inflater,container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        vm = new ViewModelProvider(this).get(InmuebleViewModel.class);

        vm.recuperarInmueble(getArguments());

        vm.getInmuebleDetalle().observe(getViewLifecycleOwner(), inmueble -> {
            binding.tvDetalleDireccion.setText(inmueble.getDirrecion());
            binding.tvDetalleValor.setText("$ " +inmueble.getValor());
            binding.tvDetalleUso.setText(inmueble.getUso());
            binding.tvDetalleTipo.setText(inmueble.getTipo());
            binding.tvDetallesAmbientes.setText("Ambientes " +inmueble.getAmbientes());
            binding.tvDetalleSuperficie.setText("Superficie " +inmueble.getSuperficie());
            binding.tvDetalleLatitud.setText(inmueble.getLatitud());
            binding.tvDetalleLongitud.setText(inmueble.getLongitud());

        });
    }
}
