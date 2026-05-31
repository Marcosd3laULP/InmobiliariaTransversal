package com.basico91.inmobiliariatransversal.inquilino;

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
import com.basico91.inmobiliariatransversal.databinding.FragmentInquilinoBinding;


public class InquilinoFragment extends Fragment {

    private InquilinoViewModel vm;
    private InquilinoAdapter adaptador;
    private FragmentInquilinoBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentInquilinoBinding.inflate(inflater, container, false);
        adaptador = new InquilinoAdapter(inmueble ->{
            Bundle bundle = new Bundle();
            bundle.putSerializable("idInmueble", inmueble.getIdInmueble());

            NavController nav = Navigation.findNavController(requireView());
            nav.navigate(R.id.action_nav_inquilino_to_inquilinoFragmentDetalle, bundle);
        });
        binding.rvInquilino.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rvInquilino.setAdapter(adaptador);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        vm = new ViewModelProvider(this).get(InquilinoViewModel.class);

        vm.getListaInmuebleConInquilino().observe(getViewLifecycleOwner(), inmueblesEInquilinos ->{
            adaptador.setListaDeInmueblesConInquilino(inmueblesEInquilinos);
        });
        vm.cargarInmueblesConInquilinos();
    }
}