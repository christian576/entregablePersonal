package com.example.entregable.Controller;

import com.example.entregable.Model.FirestoreDao;
import com.example.entregable.Model.Producto;
import com.example.entregable.Utils.ResultListener;

import java.util.List;

public class FirestoreController {
    private FirestoreDao firestoreDao;

    public FirestoreController() {
        firestoreDao = new FirestoreDao();
    }


    public void agregarProductoAFav(Producto producto){
        firestoreDao.agregarProductoAFav(producto);
    }

    public void traerListaDeFavorito(final ResultListener<List<Producto>> listenerDeLaVista){
        firestoreDao.traerProductosFavoritos(new ResultListener<List<Producto>>() {
            @Override
            public void finish(List<Producto> result) {
                listenerDeLaVista.finish(result);
            }
        });
    }
}
