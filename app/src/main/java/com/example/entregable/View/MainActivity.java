package com.example.entregable.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import com.example.entregable.Model.Producto;
import com.example.entregable.R;

public class MainActivity extends AppCompatActivity implements FragmentListaProductos.ListenerDelFragment{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pegarFragment(new FragmentListaProductos());
    }

    private void pegarFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().add(R.id.ContenedorDelMain,fragment).replace(R.id.ContenedorDelMain,fragment).addToBackStack(null).commit();

    }

    @Override
    public void recibirProducto(Producto producto) {


    }
}
