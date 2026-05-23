package com.basico91.inmobiliariatransversal.inmueble;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.basico91.inmobiliariatransversal.R;
import com.basico91.inmobiliariatransversal.databinding.FragmentInmuebleBinding;

public class InmuebleFragment extends Fragment {

    private FragmentInmuebleBinding binding;
    private InmuebleAdapter adaptador;
    private InmuebleViewModel vm;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentInmuebleBinding.inflate(inflater, container, false);
        adaptador = new InmuebleAdapter(inmueble -> {
            Bundle bundle = new Bundle();
            bundle.putSerializable("inmueble", inmueble);

        NavController nav = Navigation.findNavController(requireView());
        nav.navigate(R.id.action_nav_inmueble_to_detalleInmuebleFragment, bundle);


        });
        binding.rvInmuebles.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rvInmuebles.setAdapter(adaptador);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        vm = new ViewModelProvider(this).get(InmuebleViewModel.class);
        vm.getListadoInmuebles().observe(getViewLifecycleOwner(), inmuebles -> {
            adaptador.setListaInmuebles(inmuebles);
        });
        vm.cargarInmuebles();

        binding.fabNuevoInmueble.setOnClickListener(v -> {
           NavController nav = Navigation.findNavController(requireView());
            nav.navigate(R.id.action_nav_inmueble_to_cargarInmuebleFragment);
        });
    }
}