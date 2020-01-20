package com.example.entregable.Model;

import com.google.gson.annotations.SerializedName;

public class Descripcion {

    @SerializedName("plain_text")
    private String descripcionProducto;


    public String getDescripcionProducto() {
        return descripcionProducto;
    }
}
