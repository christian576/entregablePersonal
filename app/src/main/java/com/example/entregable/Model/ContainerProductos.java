package com.example.entregable.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ContainerProductos {
    @SerializedName("results")
    private List<Producto> productoList;

    public List<Producto> getProductoList() {
        return productoList;
    }

    public void setProductoList(List<Producto> productoList) {
        this.productoList = productoList;
    }

    @SerializedName("thumbnail")
    private String urlImagen;

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }

    public ContainerProductos(){
        productoList = new ArrayList<>();
    }

    public void agregarProducto(Producto producto){
        productoList.add(producto);
    }

    public void quitarProducto(Producto producto){
        productoList.remove(producto);
    }

    public Boolean contieneProducto (Producto producto){
        return productoList.contains(producto);
    }

}
