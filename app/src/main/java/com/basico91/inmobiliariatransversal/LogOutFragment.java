package com.basico91.inmobiliariatransversal;

import android.content.DialogInterface;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.basico91.inmobiliariatransversal.R;

public class LogOutFragment extends Fragment {

    public LogOutFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_log_out, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        mostrarDialogoCerrarApp(view);
    }

    private void mostrarDialogoCerrarApp(View vista) {
        new AlertDialog.Builder(requireContext())
                .setTitle("Cerrar Aplicación")
                .setMessage("¿Estás seguro de que deseas salir y cerrar la app?")
                .setCancelable(false) // Obliga a responder Sí o No
                .setPositiveButton("Sí, salir", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                        requireActivity().finishAndRemoveTask();
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                        Navigation.findNavController(vista).popBackStack();
                    }
                })
                .show();
    }
}