package com.example.entregable.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Producto implements Serializable {

    @SerializedName("title")
    private String nombreProducto;
    @SerializedName("price")
    private Integer precioProducto;
    @SerializedName("thumbnail")
    private String urlImagen;

    public Producto(String nombreProducto, Integer precioProducto, String urlImagen) {
        this.nombreProducto = nombreProducto;
        this.precioProducto = precioProducto;
        this.urlImagen = urlImagen;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public Integer getPrecioProducto() {
        return precioProducto;
    }

    public String getUrlImagen() {
        return urlImagen;
    }
}
