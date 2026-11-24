package com.upiiz.examen_lll.views;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.upiiz.examen_lll.R;
import com.upiiz.examen_lll.models.Producto;

import java.util.Locale;

public class DetalleProductoActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_producto);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Detalle del Producto");
        }
        ImageView ivImagen = findViewById(R.id.ivImagenDetalle);
        TextView tvNombre = findViewById(R.id.tvNombreDetalle);
        TextView tvDescripcion = findViewById(R.id.tvDescripcionDetalle);
        TextView tvPrecio = findViewById(R.id.tvPrecioDetalle);
        TextView tvCategoria = findViewById(R.id.tvCategoriaDetalle);
        TextView tvMarca = findViewById(R.id.tvMarcaDetalle);
        TextView tvCalificacion = findViewById(R.id.tvCalificacionDetalle);
        Button btnRegresar = findViewById(R.id.btnRegresar);

        btnRegresar.setOnClickListener(this);

        Producto producto = (Producto) getIntent().getSerializableExtra("producto_key");

        if (producto != null) {
            tvNombre.setText(producto.getNombre());
            tvDescripcion.setText(producto.getDescripcion());
            tvPrecio.setText(String.format(Locale.US, "$%.2f", producto.getPrecio()));
            tvCategoria.setText(producto.getCategoria());
            tvMarca.setText(producto.getMarca());
            tvCalificacion.setText(String.format(Locale.US, "★ %.1f", producto.getCalificacion()));

            Glide.with(this)
                    .load(producto.getImagenUrl())
                    .placeholder(android.R.drawable.ic_menu_gallery)
                    .error(android.R.drawable.ic_menu_report_image)
                    .into(ivImagen);
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()== R.id.btnRegresar){
            finish();
        }
    }
}