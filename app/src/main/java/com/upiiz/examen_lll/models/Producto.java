package com.upiiz.examen_lll.models;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class Producto implements Serializable {

    @SerializedName("id")
    private int id;

    @SerializedName("title")
    private String nombre;

    @SerializedName("description")
    private String descripcion;

    @SerializedName("price")
    private double precio;

    @SerializedName("category")
    private String categoria;

    @SerializedName("brand")
    private String marca;

    @SerializedName("rating")
    private double calificacion;

    @SerializedName("thumbnail")
    private String imagenUrl;

    public Producto() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    public String getMarca() { return marca != null ? marca : "Sin marca"; }
    public void setMarca(String marca) { this.marca = marca; }

    public double getCalificacion() { return calificacion; }
    public void setCalificacion(double calificacion) { this.calificacion = calificacion; }

    public String getImagenUrl() { return imagenUrl; }
    public void setImagenUrl(String imagenUrl) { this.imagenUrl = imagenUrl; }
}