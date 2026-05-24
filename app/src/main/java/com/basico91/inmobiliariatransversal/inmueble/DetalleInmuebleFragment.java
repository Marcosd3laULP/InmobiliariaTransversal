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
import com.basico91.inmobiliariatransversal.request.ApiClientt;
import com.bumptech.glide.Glide;

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
            String urlImagen = ApiClientt.BASE_URL + inmueble.getImagen();
            Glide.with(requireContext())
                            .load(urlImagen)
                                    .into(binding.imageView2);
            binding.tvDetalleDireccion.setText(inmueble.getDireccion());
            binding.tvDetalleValor.setText("$ " +inmueble.getValor());
            binding.tvDetalleUso.setText(inmueble.getUso());
            binding.tvDetalleTipo.setText(inmueble.getTipo());
            binding.tvDetallesAmbientes.setText("" +inmueble.getAmbientes());
            binding.tvDetalleSuperficie.setText("" +inmueble.getSuperficie());
            binding.tvDetalleLatitud.setText(String.valueOf(inmueble.getLatitud()));
            binding.tvDetalleLongitud.setText(String.valueOf(inmueble.getLongitud()));

        });
    }
}
