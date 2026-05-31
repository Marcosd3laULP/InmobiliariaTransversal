package com.basico91.inmobiliariatransversal.inquilino;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.basico91.inmobiliariatransversal.R;
import com.basico91.inmobiliariatransversal.databinding.CardInquilinoBinding;
import com.basico91.inmobiliariatransversal.modelos.Inmueble;
import com.basico91.inmobiliariatransversal.request.ApiClientt;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class InquilinoAdapter extends RecyclerView.Adapter<InquilinoAdapter.InquilinoViewHolder> {
    private List<Inmueble> listaDeInmueblesConInquilino = new ArrayList<>();
    private OnVerInquilinoListener listener;

    public interface OnVerInquilinoListener{
        void onverInquilinoClick(Inmueble inmueble);
    }
    public InquilinoAdapter(OnVerInquilinoListener listener){this.listener = listener; }

    public void setListaDeInmueblesConInquilino(List<Inmueble> inmuebles){
        this.listaDeInmueblesConInquilino = inmuebles;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public InquilinoAdapter.InquilinoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardInquilinoBinding binding = CardInquilinoBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);

        return new InquilinoViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull InquilinoAdapter.InquilinoViewHolder holder, int position) {
    Inmueble inmueble = listaDeInmueblesConInquilino.get(position);
    holder.bind(inmueble);
    }

    @Override
    public int getItemCount() {return listaDeInmueblesConInquilino.size();}

    public class InquilinoViewHolder extends RecyclerView.ViewHolder{
        private CardInquilinoBinding binding;

        public InquilinoViewHolder(@NonNull CardInquilinoBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Inmueble inmueble){
            binding.tvDirInmuebleAlquilado.setText(inmueble.getDireccion());

            String rutaImagen = inmueble.getImagen() != null ? inmueble.getImagen().trim() : "";
            String urlImagen = ApiClientt.BASE_URL + "/" + rutaImagen;

            Glide.with(itemView.getContext())
                    .load(urlImagen)
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .error(R.drawable.ic_slideshow_black_24dp)
                    .into(binding.ivInmuebleAlquilado);

            binding.btVerInquilino.setOnClickListener(v -> {
                if(listener != null){
                    listener.onverInquilinoClick(inmueble);
                }
            });
        }
    }
}
