package com.basico91.inmobiliariatransversal;

import android.os.Bundle;
import android.util.Log;

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
        binding = ActivityMenuBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        setSupportActionBar(binding.appBarGeneral.toolbar);

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment_content_menu);
        NavController navController = navHostFragment.getNavController();


        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_inicio, R.id.nav_perfil, R.id.nav_inmueble, R.id.nav_inquilino, R.id.nav_contrato, R.id.nav_logOut)
                .setOpenableLayout(binding.drawerLayout)
                .build();


        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);


        // Sustituye la línea NavigationUI.setupWithNavController(binding.navView, navController);
// por este bloque que fuerza la navegación manual si la automática falla:

        binding.navView.setNavigationItemSelectedListener(item -> {
            // 1. Intentamos navegar al destino que coincide con el ID del item
            boolean handled = NavigationUI.onNavDestinationSelected(item, navController);

            // 2. Si navegó correctamente, cerramos el menú lateral
            if (handled) {
                binding.drawerLayout.closeDrawers();
            }
            return handled;
        });
        navController.addOnDestinationChangedListener((controller, destination, arguments) -> {
            // Esto te dirá en el LogCat a qué ID intentó ir
            Log.d("Navegacion", "Cambiando a: " + destination.getLabel());
        });
    }


    @Override
    public boolean onSupportNavigateUp() {
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment_content_menu);
        NavController navController = navHostFragment.getNavController();
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}
