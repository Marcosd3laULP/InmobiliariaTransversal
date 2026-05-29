package com.basico91.inmobiliariatransversal.contrato;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.basico91.inmobiliariatransversal.databinding.FragmentPagoBinding;

public class PagoFragment extends Fragment {
    private FragmentPagoBinding binding;
    private ContratoViewModel vm;
    private PagoAdapter pagoAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentPagoBinding.inflate(inflater, container, false);
        pagoAdapter = new PagoAdapter();

        binding.rvPagos.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rvPagos.setAdapter(pagoAdapter);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        vm = new ViewModelProvider(this).get(ContratoViewModel.class);
        vm.getListaPagos().observe(getViewLifecycleOwner(), listaDePagos ->{
            pagoAdapter.setListaDepagos(listaDePagos);
        });
        vm.recuperarPagosDeUnContrato(getArguments());
    }
}
