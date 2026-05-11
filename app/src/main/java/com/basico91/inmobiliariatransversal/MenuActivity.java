package com.basico91.inmobiliariatransversal;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.basico91.inmobiliariatransversal.databinding.ActivityMenuBinding;

public class MenuActivity extends AppCompatActivity {

    //private AppBarConfiguration mAppBarConfiguration;
    //private ActivityMenuBinding binding;

    //@Override
    /*protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMenuBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarGeneral.toolbar);

        // Vinculación del NavHostFragment y NavController
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment_content_menu);
        assert navHostFragment != null;
        NavController navController = navHostFragment.getNavController();

        // Configuración de los destinos del menú lateral
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_inicio, R.id.nav_perfil, R.id.nav_inmueble,
                R.id.nav_inquilino, R.id.nav_contrato, R.id.nav_logOut, R.id.nav_navega)
                .setOpenableLayout(binding.drawerLayout)
                .build();

        // Configuración de la ActionBar y el NavigationView
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

        navController.addOnDestinationChangedListener((controller, destination, arguments) -> {
            Log.d("Navegacion", "Cambiando a: " + destination.getLabel());
        });
    }*/


    /*@Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_menu);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }*/
}
