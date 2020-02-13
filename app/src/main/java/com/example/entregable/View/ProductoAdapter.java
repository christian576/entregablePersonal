package com.example.entregable.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.entregable.Model.Producto;
import com.example.entregable.R;

import java.util.ArrayList;
import java.util.List;

public class ProductoAdapter extends RecyclerView.Adapter<ProductoAdapter.ProductoViewHolder> {
    protected List<Producto> productoList = new ArrayList<>();
    private ListenderDelAdapter listenerDelAdapter;

    public ProductoAdapter() {


    }

    public ProductoAdapter(ListenderDelAdapter listenerDelAdapter) {

        this.listenerDelAdapter = listenerDelAdapter;
    }

    @NonNull
    @Override
    public ProductoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflador = LayoutInflater.from(parent.getContext());
        View view = inflador.inflate(R.layout.celda_producto, parent, false);
        ProductoViewHolder productoViewHolder = new ProductoViewHolder(view);
        return productoViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductoViewHolder holder, int position) {
        Producto unProductoDeLaLista = productoList.get(position);
        holder.cargarImagen(unProductoDeLaLista);

    }

    @Override
    public int getItemCount() {
        return productoList.size();
    }

    public void setProductoList(List<Producto> productoList) {
        this.productoList = productoList;
        notifyDataSetChanged();
    }

    public class ProductoViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewProducto;
        private ImageView imageViewProducto;
        private TextView textViewPrecio;

        public ProductoViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewProducto = itemView.findViewById(R.id.imagenViewProducto);
            textViewProducto = itemView.findViewById(R.id.NombreProducto);
            textViewPrecio = itemView.findViewById(R.id.PrecioProducto);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Producto productoSeleccionado = productoList.get(getAdapterPosition());
                    listenerDelAdapter.informarProductoSeleccionado(productoSeleccionado);
                }
            });


        }


        public void cargarImagen(Producto producto) {
            Glide.with(imageViewProducto.getContext()).load(producto.getUrlImagen()).placeholder(R.drawable.loading).into(imageViewProducto);
            textViewProducto.setText(producto.getNombreProducto());
            textViewPrecio.setText("U$S " + producto.getPrecioProducto().toString());
        }
    }

    public interface ListenderDelAdapter {
        public void informarProductoSeleccionado(Producto producto);
    }
}
