package com.upiiz.examen_lll.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.upiiz.examen_lll.R;
import com.upiiz.examen_lll.models.Producto;

import java.util.List;
import java.util.Locale;

public class ProductoAdapter extends RecyclerView.Adapter<ProductoAdapter.ProductoViewHolder> {

    private final List<Producto> listaProductos;
    private final OnProductoClickListener listener;

    public interface OnProductoClickListener {
        void onProductoClick(Producto producto);
    }

    public ProductoAdapter(List<Producto> listaProductos, OnProductoClickListener listener) {
        this.listaProductos = listaProductos;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ProductoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_producto, parent, false);
        return new ProductoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductoViewHolder holder, int position) {
        Producto producto = listaProductos.get(position);

        holder.tvNombre.setText(producto.getNombre());
        holder.tvDescripcion.setText(producto.getDescripcion());
        holder.tvCategoria.setText(producto.getCategoria());
        holder.tvPrecio.setText(String.format(Locale.US, "$%.2f", producto.getPrecio()));

        Glide.with(holder.itemView.getContext())
                .load(producto.getImagenUrl())
                .placeholder(android.R.drawable.ic_menu_gallery)
                .error(android.R.drawable.ic_menu_report_image)
                .into(holder.ivImagen);

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onProductoClick(producto);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaProductos.size();
    }

    public static class ProductoViewHolder extends RecyclerView.ViewHolder {
        ImageView ivImagen;
        TextView tvNombre, tvDescripcion, tvPrecio, tvCategoria;

        public ProductoViewHolder(@NonNull View itemView) {
            super(itemView);
            ivImagen = itemView.findViewById(R.id.ivImagenProducto);
            tvNombre = itemView.findViewById(R.id.tvNombreProducto);
            tvDescripcion = itemView.findViewById(R.id.tvDescripcionProducto);
            tvPrecio = itemView.findViewById(R.id.tvPrecioProducto);
            tvCategoria = itemView.findViewById(R.id.tvCategoriaProducto);
        }
    }
}