package com.example.entregable.Model;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ProductoService {

    @GET("/sites/MLA/search?q=perros")
    Call<ContainerProductos>traerListaDeProductosPerros();


}


