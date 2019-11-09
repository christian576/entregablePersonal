package com.example.entregable.Model;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProductoRetrofitDAO {

    private Retrofit retrofit;
    protected ProductoService productoService;

    public ProductoRetrofitDAO(String baseUrl){
        retrofit=new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        productoService= retrofit.create(ProductoService.class);
    }
}
