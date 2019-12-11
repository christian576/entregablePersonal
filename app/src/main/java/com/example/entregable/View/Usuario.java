package com.example.entregable.View;

import com.example.entregable.Model.Producto;

import java.util.List;

public class Usuario {
    private String nombre;
    private String apellido;
    private String urlImagen;
    private List<Producto> listaProductosFav;

    public Usuario(String nombre, String apellido) {
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }

    public List<Producto> getListaProductosFav() {
        return listaProductosFav;
    }

    public void setListaProductosFav(List<Producto> listaProductosFav) {
        this.listaProductosFav = listaProductosFav;
    }
}
