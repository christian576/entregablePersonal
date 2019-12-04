package com.example.entregable.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.entregable.Model.Producto;
import com.example.entregable.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements FragmentListaProductos.ListenerDelFragment, NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;

    public static final String CLAVE_LOGIN = "clave_login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigationView);
        toolbar = findViewById(R.id.ToolBarActivityMain);
        pegarFragment(new FragmentListaProductos());
        //setSupportActionBar(toolbar);


        ActionBarDrawerToggle toggle =
                new ActionBarDrawerToggle(
                        this,
                        drawerLayout,
                        toolbar,
                        R.string.open_drawer,
                        R.string.close_drawer);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);


        ActionBar supportActionBar = getSupportActionBar();


    }

    private void pegarFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().add(R.id.ContenedorDelMain, fragment).replace(R.id.ContenedorDelMain, fragment).addToBackStack(null).commit();

    }

    @Override
    public void recibirProducto(Producto producto) {
        FragmentDetalleArticulo fragmentDetalleArticulo = new FragmentDetalleArticulo();
        Bundle bundle = new Bundle();
        bundle.putSerializable(FragmentDetalleArticulo.CLAVE_ARTICULO, producto);
        fragmentDetalleArticulo.setArguments(bundle);
        pegarFragment(fragmentDetalleArticulo);


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Integer id = menuItem.getItemId();
        switch (id) {
            case R.id.menuPrincipal_Home:
                pegarFragment(new FragmentListaProductos());
                break;

            case R.id.menuPrincipal_acerca_mercado:
                pegarFragment(new FragmentSomos());
                drawerLayout.closeDrawers();
                break;

            case R.id.menuPrincipal_logout:

                FirebaseAuth.getInstance();
                cambiarDeActivity();
                break;

        }
        drawerLayout.closeDrawers();
        return true;

    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    private void cambiarDeActivity() {
        String username = null;
        Intent intent = new Intent(this, Login.class);
        Bundle bundle = new Bundle();
        bundle.putString(Login.CLAVE, username);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
