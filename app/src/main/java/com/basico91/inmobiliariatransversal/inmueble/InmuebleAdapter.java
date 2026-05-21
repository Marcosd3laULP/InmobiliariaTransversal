package com.basico91.inmobiliariatransversal.inmueble;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.basico91.inmobiliariatransversal.databinding.CardInmuebleBinding;
import com.basico91.inmobiliariatransversal.modelos.Inmueble;

import java.util.ArrayList;
import java.util.List;

public class InmuebleAdapter extends RecyclerView.Adapter<InmuebleAdapter.InmuebleViewHolder> {

    private List<Inmueble> listaDeInmuebles = new ArrayList<>();

    public void setListaInmuebles(List<Inmueble> inmuebles){
        this.listaDeInmuebles = inmuebles;

    }

    @NonNull
    @Override
    public InmuebleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardInmuebleBinding binding = CardInmuebleBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new InmuebleViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull InmuebleAdapter.InmuebleViewHolder holder, int position) {
        Inmueble inmueble = listaDeInmuebles.get(position);
        holder.bind(inmueble);
    }

    @Override
    public int getItemCount() {
        return listaDeInmuebles.size();
    }
    public class InmuebleViewHolder extends RecyclerView.ViewHolder{
    private CardInmuebleBinding binding;
        public InmuebleViewHolder(@NonNull CardInmuebleBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Inmueble inmueble) {
        binding.tvDireccion.setText(inmueble.getDirrecion());
        binding.tvValor.setText("$ " + inmueble.getValor());
        }
    }
}
