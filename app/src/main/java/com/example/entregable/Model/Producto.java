package com.example.entregable.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Producto implements Serializable {

    @SerializedName("title")
    private String nombreProducto;
    @SerializedName("price")
    private Integer precioProducto;
    @SerializedName("thumbnail")
    private String urlImagen;

    @SerializedName("id")
    private String id;

    @SerializedName("pictures")
    private List<ProductoImagen> listImagenes;

    public Producto() {
    }

    public Producto(String nombreProducto, Integer precioProducto, String urlImagen, String id, List<ProductoImagen> imagenList ) {
        this.nombreProducto = nombreProducto;
        this.precioProducto = precioProducto;
        this.urlImagen = urlImagen;
        this.id=id;
        this.listImagenes = imagenList;
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

    public String getId() {
        return id;
    }
    public List<ProductoImagen> getListImagenes() {
        return listImagenes;
    }
}
