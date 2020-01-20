package com.example.entregable.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ContainerImagenes {

    @SerializedName("pictures")
    private List<ProductoImagen> imagenList;


    public List<ProductoImagen> getImagenList() {
        return imagenList;
    }
}
