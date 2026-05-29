package com.basico91.inmobiliariatransversal.inmueble;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.basico91.inmobiliariatransversal.R;
import com.basico91.inmobiliariatransversal.databinding.CardInmuebleBinding;
import com.basico91.inmobiliariatransversal.modelos.Inmueble;
import com.basico91.inmobiliariatransversal.request.ApiClientt;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class InmuebleAdapter extends RecyclerView.Adapter<InmuebleAdapter.InmuebleViewHolder> {

    private List<Inmueble> listaDeInmuebles = new ArrayList<>();
    private OnItemClickListener listener;
    public interface OnItemClickListener {
        void onItemClick(Inmueble inmueble);
    }
    public InmuebleAdapter(OnItemClickListener listener){
        this.listener = listener;
    }
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
    public void onBindViewHolder(@NonNull InmuebleViewHolder holder, int position) {
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
            binding.tvDireccion.setText(inmueble.getDireccion().trim());
            binding.tvValor.setText("$ " + inmueble.getValor());

            if (inmueble.isDisponible()) {
                binding.tvDisponibilidad.setText("Disponible");
                binding.tvDisponibilidad.setTextColor(itemView.getContext().getResources().getColor(android.R.color.holo_green_dark));
            } else {
                binding.tvDisponibilidad.setText("No Disponible");
                binding.tvDisponibilidad.setTextColor(itemView.getContext().getResources().getColor(android.R.color.holo_red_dark));
            }

            //Esto es porque no se guardaba bien la imagen
            String rutaImagen = inmueble.getImagen() != null ? inmueble.getImagen().trim() : "";
            String urlImagen = ApiClientt.BASE_URL + "/" + rutaImagen;

            //Esto como seguridad extra por las dudas
            urlImagen = urlImagen.replaceAll("(?<!https?|ftp):/+", "/");

            Glide.with(itemView.getContext())
                    .load(urlImagen)
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .error(R.drawable.ic_slideshow_black_24dp)
                    .into(binding.ivFotoInmueble);

            itemView.setOnClickListener(v -> {
                if(listener != null){
                    listener.onItemClick(inmueble);
                }
            });
        }
    }
}
