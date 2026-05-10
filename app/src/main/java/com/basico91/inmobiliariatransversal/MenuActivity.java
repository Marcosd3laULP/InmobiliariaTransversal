package com.basico91.inmobiliariatransversal;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.basico91.inmobiliariatransversal.databinding.ActivityMenuBinding;
import com.google.android.material.navigation.NavigationView;

public class MenuActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMenuBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());

        binding = ActivityMenuBinding.inflate(getLayoutInflater());
        // Configurar la Toolbar
        setSupportActionBar(binding.appBarGeneral.toolbar);

        // Configurar el NavController
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment_content_menu);
        NavController navController = navHostFragment.getNavController();

        // Vincular el DrawerLayout y el NavigationView
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;

        // Definir qué fragmentos NO mostrarán la flecha de atrás (mostrarán el menú lateral)
        mAppBarConfiguration = new AppBarConfiguration.Builder() // Agrega tus IDs de fragmentos aquí
                .setOpenableLayout(drawer)
                .build();

        // Configurar la barra superior para que funcione con el menú lateral
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    // Importante para que el botón de menú funcione al tocarlo
    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_menu);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
    }
