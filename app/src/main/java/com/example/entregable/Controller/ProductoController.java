package com.example.entregable.Controller;

import com.example.entregable.Model.Descripcion;
import com.example.entregable.Model.Producto;
import com.example.entregable.Model.ProductoDao;
import com.example.entregable.Utils.ResultListener;

import java.util.List;

public class ProductoController {

    public void traerProductos(final ResultListener<List<Producto>> listenerDeLaVista) {
        ProductoDao productoDao = new ProductoDao();
        productoDao.traerListaRelevantes(new ResultListener<List<Producto>>() {
            @Override
            public void finish(List<Producto> result) {
                listenerDeLaVista.finish(result);
            }
        });
    }

        public void traerProductoMedianteID(String productoID, final ResultListener<Producto> listenerDeLaVista ){

            ProductoDao productoDAO = new ProductoDao();

            productoDAO.traerProductoMedianteID(productoID, new ResultListener<Producto>() {
                @Override
                public void finish(Producto result) {
                    listenerDeLaVista.finish(result);
                }
            });
        }

        public void traerDetalleProductoMedianteID(String productoID, final ResultListener<List<Descripcion>> listenerDeLaVista ){

            ProductoDao productoDAO = new ProductoDao();

            productoDAO.traerDetalleProductoMedianteID(productoID, new ResultListener<List<Descripcion>>() {
                @Override
                public void finish(List<Descripcion> result) {
                    listenerDeLaVista.finish(result);
                }
            });
        }

    }

