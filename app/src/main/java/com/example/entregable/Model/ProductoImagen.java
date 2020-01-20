package com.example.entregable.Model;

import com.google.gson.annotations.SerializedName;

public class ProductoImagen {


    @SerializedName("secure_url")
    private String urlImagen;



    public ProductoImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }

    public ProductoImagen() {
    }

    public String getUrlImagen() {
        return urlImagen;
    }
}
