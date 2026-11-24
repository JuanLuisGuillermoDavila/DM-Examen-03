package com.upiiz.examen_lll.models;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class RespuestaProductos {

    @SerializedName("products")
    private List<Producto> productos;

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }
}