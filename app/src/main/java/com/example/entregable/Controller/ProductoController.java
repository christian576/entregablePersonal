package com.example.entregable.Controller;

import com.example.entregable.Model.Producto;
import com.example.entregable.Model.ProductoDao;
import com.example.entregable.Utils.ResultListener;

import java.util.List;

public class ProductoController {

    public void traerProductos(final ResultListener<List<Producto>> listenerDeLaVista){
        ProductoDao productoDao = new ProductoDao();
        productoDao.traerListaRelevantes(new ResultListener<List<Producto>>() {
            @Override
            public void finish(List<Producto> result) {
                listenerDeLaVista.finish(result);
            }
        });

    }
}
