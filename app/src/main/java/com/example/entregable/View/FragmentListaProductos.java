package com.example.entregable.View;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.entregable.Controller.ProductoController;
import com.example.entregable.Model.Producto;
import com.example.entregable.R;
import com.example.entregable.Utils.ResultListener;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentListaProductos extends Fragment implements ProductoAdapter.ListenderDelAdapter {


    private RecyclerView recyclerViewFragmentListaProducto1;
    private ListenerDelFragment listenerDelFragment;
    private TextView textViewProducto;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vistaDelFragment = inflater.inflate(R.layout.fragment_fragment_lista_productos,container,false);
        inflarVistas(vistaDelFragment);

        final ProductoAdapter productoAdapter= new ProductoAdapter(this);
        final ProductoController productoController = new ProductoController();

        productoController.traerProductos(new ResultListener<List<Producto>>() {
            @Override
            public void finish(List<Producto> result) {
                productoAdapter.setProductoList(result);


            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL, false);
        recyclerViewFragmentListaProducto1.setLayoutManager(linearLayoutManager);
        recyclerViewFragmentListaProducto1.setAdapter(productoAdapter);
        return vistaDelFragment;

    }

    private void inflarVistas(View vistaDelFragment) {
        recyclerViewFragmentListaProducto1= vistaDelFragment.findViewById(R.id.RecyclerViewFragmentListaProductos);

    }

    @Override
    public void onAttach(@NonNull Context context){
        super.onAttach(context);
        listenerDelFragment= (ListenerDelFragment) context;
    }

    @Override
    public void informarProductoSeleccionado(Producto producto) {
        listenerDelFragment.recibirProducto(producto);

    }

    public interface ListenerDelFragment{
        void recibirProducto (Producto producto);
    }
}
