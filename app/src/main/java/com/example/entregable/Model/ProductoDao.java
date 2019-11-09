package com.example.entregable.Model;

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
        Call<ContainerProductos> call= productoService.traerListaDeProductosPerros();
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
}
