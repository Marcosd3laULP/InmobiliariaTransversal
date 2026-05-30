package com.basico91.inmobiliariatransversal.contrato;

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
import com.basico91.inmobiliariatransversal.databinding.FragmentContratoBinding;

public class ContratoFragment extends Fragment {
    private ContratoViewModel vm;
    private ContratoAdapter adaptador;
    private FragmentContratoBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentContratoBinding.inflate(inflater, container, false);
        adaptador = new ContratoAdapter(inmueble -> {
            Bundle bundle = new Bundle();
            bundle.putSerializable("idInmueble", inmueble.getIdInmueble());

            NavController nav = Navigation.findNavController(requireView());
            nav.navigate(R.id.action_nav_contrato_to_contratoFragmentDetalle, bundle);
        });
        binding.rvContrato.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rvContrato.setAdapter(adaptador);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        vm = new ViewModelProvider(this).get(ContratoViewModel.class);

        vm.getListaInmuebleAlquier().observe(getViewLifecycleOwner(), inmueblesAlquilados -> {
            adaptador.setListaInmueblesAlquilados(inmueblesAlquilados);
        });
        vm.CargarInmueblesAlquilados();
    }
}