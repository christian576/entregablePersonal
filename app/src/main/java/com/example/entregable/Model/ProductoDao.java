package com.example.entregable.Model;

import android.util.Log;

import com.example.entregable.Utils.ResultListener;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductoDao extends ProductoRetrofitDAO {
    private static final String BASE_URL_API= "https://api.mercadolibre.com";

    public ProductoDao() {
        super(BASE_URL_API);
    }

    public void traerListaRelevantes(final ResultListener<List<Producto>> listenerDelController){
        Call<ContainerProductos> call= serviceRetro.traerListaDeProductosPerros();
        call.enqueue(new Callback<ContainerProductos>() {
            @Override
            public void onResponse(Call<ContainerProductos> call, Response<ContainerProductos> response) {
                ContainerProductos containerProductos = response.body();
                listenerDelController.finish(containerProductos.getProductoList());
            }

            @Override
            public void onFailure(Call<ContainerProductos> call, Throwable t) {


            }
        });


    }

    public void traerProductoMedianteID(String productoID, final ResultListener<Producto> listenerDelControler) {

        Call<Producto> call = serviceRetro.traerProductoMedianteID(productoID);

        call.enqueue(new Callback<Producto>() {
            @Override
            public void onResponse(Call<Producto> call, Response<Producto> response) {
                Producto producto= response.body();
                listenerDelControler.finish(producto);
            }

            @Override
            public void onFailure(Call<Producto> call, Throwable t) {
                Log.d(",o,fw","dss");
            }
        });
    }

    public void traerDetalleProductoMedianteID(String productoID, final ResultListener<List<Descripcion>> listenerDelControler) {

        Call<List<Descripcion>> call = serviceRetro.traerDetalleProductoMedianteID(productoID);

        call.enqueue(new Callback<List<Descripcion>>() {
            @Override
            public void onResponse(Call<List<Descripcion>> call, Response<List<Descripcion>> response) {
                List<Descripcion> producto = response.body();
                listenerDelControler.finish(producto);
            }

            @Override
            public void onFailure(Call<List<Descripcion>> call, Throwable t) {
                Log.d(",o,fw","dss");
            }
        });
    }
}
