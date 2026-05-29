package com.basico91.inmobiliariatransversal.contrato;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.basico91.inmobiliariatransversal.R;
import com.basico91.inmobiliariatransversal.databinding.CardContratoBinding;
import com.basico91.inmobiliariatransversal.databinding.CardInmuebleBinding;
import com.basico91.inmobiliariatransversal.modelos.Inmueble;
import com.basico91.inmobiliariatransversal.request.ApiClientt;
import com.bumptech.glide.Glide;

import org.jetbrains.annotations.UnknownNullability;

import java.util.ArrayList;
import java.util.List;

public class ContratoAdapter extends RecyclerView.Adapter<ContratoAdapter.ContratoViewHolder> {

    private List<Inmueble> listaDeInmueblesConAlquiler = new ArrayList<>();
    private OnVerContratoListener listener;

    public interface OnVerContratoListener{
        void onVerContratoClick(Inmueble inmueble);
    }

    public ContratoAdapter(OnVerContratoListener listener){
        this.listener = listener;
    }

    public void setListaInmueblesAlquilados(List<Inmueble> inmuebles){
        this.listaDeInmueblesConAlquiler = inmuebles;
    }

    @NonNull
    @Override
    public ContratoAdapter.ContratoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardContratoBinding binding = CardContratoBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ContratoViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ContratoAdapter.ContratoViewHolder holder, int position) {
        Inmueble inmueble = listaDeInmueblesConAlquiler.get(position);
        holder.bind(inmueble);
    }


    @Override
    public int getItemCount() {
        return listaDeInmueblesConAlquiler.size();
    }

    public class ContratoViewHolder extends RecyclerView.ViewHolder{
    private CardContratoBinding binding;
        public ContratoViewHolder(@NonNull CardContratoBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Inmueble inmueble){
            binding.tvInmuebleContrato.setText(inmueble.getDireccion().trim());

            String rutaImagen = inmueble.getImagen() != null ? inmueble.getImagen().trim() : "";
            String urlImagen = ApiClientt.BASE_URL + "/" + rutaImagen;

            Glide.with(itemView.getContext())
                    .load(urlImagen)
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .error(R.drawable.ic_slideshow_black_24dp)
                    .into(binding.ivContratoInmueble);

            binding.btVerContrato.setOnClickListener(v -> {
                if(listener != null){
                    listener.onVerContratoClick(inmueble);
                }
            });
        }
    }
}
