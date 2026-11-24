package com.upiiz.examen_lll;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.upiiz.examen_lll.adapters.ProductoAdapter;
import com.upiiz.examen_lll.models.Producto;
import com.upiiz.examen_lll.models.RespuestaProductos;
import com.upiiz.examen_lll.network.RetrofitClient;
import com.upiiz.examen_lll.views.DetalleProductoActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnConsultarProductos;
    private RecyclerView rvProductos;
    private ProgressBar progressBar;
    private ProductoAdapter adapter;
    private final List<Producto> listaProductos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnConsultarProductos = findViewById(R.id.btnConsultarProductos);
        rvProductos = findViewById(R.id.rvProductos);
        progressBar = findViewById(R.id.progressBar);

        rvProductos.setLayoutManager(new LinearLayoutManager(this));

        adapter = new ProductoAdapter(listaProductos, producto -> {
            Intent intent = new Intent(MainActivity.this, DetalleProductoActivity.class);
            intent.putExtra("producto_key", producto);
            startActivity(intent);
        });
        rvProductos.setAdapter(adapter);

        // Consultar / actualizar productos al pulsar el botón
        btnConsultarProductos.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnConsultarProductos) {
            consultarProductosApi();
        }
    }

    private void consultarProductosApi() {
        progressBar.setVisibility(View.VISIBLE);
        btnConsultarProductos.setEnabled(false);

        RetrofitClient.getApiService().obtenerProductos().enqueue(new Callback<RespuestaProductos>() {
            @Override
            public void onResponse(Call<RespuestaProductos> call, Response<RespuestaProductos> response) {
                progressBar.setVisibility(View.GONE);
                btnConsultarProductos.setEnabled(true);

                if (response.isSuccessful() && response.body() != null && response.body().getProductos() != null) {
                    listaProductos.clear();
                    listaProductos.addAll(response.body().getProductos());
                    adapter.notifyDataSetChanged();

                    Toast.makeText(MainActivity.this,
                            "Productos cargados: " + listaProductos.size(),
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this,
                            "No se pudo obtener la información (Código: " + response.code() + ")",
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RespuestaProductos> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                btnConsultarProductos.setEnabled(true);
                Toast.makeText(MainActivity.this,
                        "Error de conexión: verifica tu internet e intenta de nuevo",
                        Toast.LENGTH_LONG).show();
            }
        });
    }
}