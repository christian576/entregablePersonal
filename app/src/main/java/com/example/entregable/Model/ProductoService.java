package com.example.entregable.Model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ProductoService {

    @GET("/sites/MLA/search?q=motos")
    Call<ContainerProductos>traerListaDeProductosPerros();

    @GET("items/{id}/descriptions")
    Call<List<Descripcion>> traerDetalleProductoMedianteID (@Path("id") String idProducto);

    @GET("items/{id}")
    Call<Producto> traerProductoMedianteID (@Path("id") String idProducto);

    /*@GET("sites/MLA/search")
    Call<ContainerDeProductos> traerProductoPorBusqueda(@Query("q") String query,
                                                        @Query("offset") Integer offset,
                                                        @Query("limit") Integer limit);
*/


}


