package com.example.entregable.View;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.entregable.Controller.FirestoreController;
import com.example.entregable.Controller.ProductoController;
import com.example.entregable.Model.Descripcion;
import com.example.entregable.Model.Producto;
import com.example.entregable.R;
import com.example.entregable.Utils.ResultListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.w3c.dom.Text;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentDetalleArticulo extends Fragment {

    private TextView textViewArticuloNombre;
    private TextView textViewArticuloDescprition;
    private Producto unProducto;
    private TextView descripcionProducto;
    private ViewPager viewPagerImagenProducto;
    private FragmentListaProductos.ListenerDelFragment listenerDelFragment;
    private TextView textViewArticuloPrecio;
    private FloatingActionButton floatingActionButtonFavoritos;
    private FirestoreController firestoreController;
    private Boolean esFavorita;

    public static final String CLAVE_ARTICULO = "CLAVE_ARTICULO";

    public static FragmentDetalleArticulo dameUnFragmentDetalleArticulo(Producto unProducto) {
        FragmentDetalleArticulo unFragmentDetalleArticulo = new FragmentDetalleArticulo();

        Bundle bundle = new Bundle();
        bundle.putSerializable(CLAVE_ARTICULO, unProducto);
        unFragmentDetalleArticulo.setArguments(bundle);

        return unFragmentDetalleArticulo;
    }

    public FragmentDetalleArticulo() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vistaFragment = inflater.inflate(R.layout.fragment_fragment_detalle_articulo, container, false);
        inflarVistas(vistaFragment);

        firestoreController = new FirestoreController();

        ProductoController productoController = new ProductoController();

        unProducto = recepcionarProducto();
        textViewArticuloNombre.setText(unProducto.getNombreProducto());


        productoController.traerDetalleProductoMedianteID(unProducto.getId(), new ResultListener <List<Descripcion>>() {

            @Override
            public void finish(List<Descripcion> result) {
                descripcionProducto.setText(result.get(0).getDescripcionProducto());
            }
        });
         textViewArticuloDescprition.setText(unProducto.getPrecioProducto());

        floatingActionButtonFavoritos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firestoreController.agregarProductoAFav(unProducto);
                esFavorita = !esFavorita;
                actualizarFav();
            }
        });

        CargarImagen(unProducto);

        floatingActionButtonFavoritos.setClickable(false);

        return vistaFragment;
    }

    private void actualizarFav() {
        if (esFavorita){
            floatingActionButtonFavoritos.setImageResource(R.drawable.bien);
        }else{
            floatingActionButtonFavoritos.setImageResource(R.drawable.mal);
        }
    }

    private void habilitarOnClickDeFavoritos(){
        floatingActionButtonFavoritos.setClickable(true);
    }

     /*   CargarImagen(unProducto);


        return vistaFragment;
    }
*/



    private void CargarImagen(Producto unProducto) {

    }

    private Producto recepcionarProducto() {

        Bundle bundle = getArguments();
        Producto productoSeleccionado = (Producto) bundle.getSerializable(CLAVE_ARTICULO);
        return productoSeleccionado;
    }

    private void inflarVistas(View vistaFragment) {
        textViewArticuloNombre = vistaFragment.findViewById(R.id.TextViewNombreProducto);
        textViewArticuloDescprition = vistaFragment.findViewById(R.id.TextViewDetalleProductoDescripcion);
        descripcionProducto = vistaFragment.findViewById(R.id.TextViewDetalleProductoDescripcion);

    }

    public void informarProductoSeleccionado (Producto producto){
        listenerDelFragment.recibirProducto(producto);
    }
}
