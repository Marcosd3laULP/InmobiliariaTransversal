package com.basico91.inmobiliariatransversal.contrato;

import static java.security.AccessController.getContext;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.basico91.inmobiliariatransversal.databinding.CardPagoBinding;
import com.basico91.inmobiliariatransversal.modelos.Pago;

import java.util.ArrayList;
import java.util.List;

public class PagoAdapter extends RecyclerView.Adapter<PagoAdapter.PagoViewHolder>{
    private List<Pago> listaDepagos = new ArrayList<>();

    public void setListaDepagos(List<Pago> pagos){
        this.listaDepagos = pagos;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PagoAdapter.PagoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardPagoBinding binding = CardPagoBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);

        return new PagoViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PagoAdapter.PagoViewHolder holder, int position) {
    Pago pago = listaDepagos.get(position);
    holder.bind(pago);
    }

    @Override
    public int getItemCount() {
        return listaDepagos.size();
    }

    public class PagoViewHolder extends RecyclerView.ViewHolder{
        private CardPagoBinding binding;

        public PagoViewHolder(@NonNull CardPagoBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
        public void bind(Pago pago){
        binding.tvCodigoPago.setText(pago.getIdPago());
        binding.tvNumPago.setText(pago.getIdContrato());
        binding.tvFechaPago.setText(pago.getFechaPago());
        binding.tvPagoImporte.setText((int) pago.getMonto());
        binding.tvPagoContratoCod.setText(pago.getIdContrato());
        }
    }
}
