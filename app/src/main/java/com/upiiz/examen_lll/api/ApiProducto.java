package com.upiiz.examen_lll.api;

import com.upiiz.examen_lll.models.Producto;
import com.upiiz.examen_lll.models.RespuestaProductos;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiProducto {

    @GET("products")
    Call<RespuestaProductos> obtenerProductos();

    @GET("products/{id}")
    Call<Producto> obtenerProductoPorId(@Path("id") int id);
}