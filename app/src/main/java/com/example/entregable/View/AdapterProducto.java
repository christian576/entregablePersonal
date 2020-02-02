package com.example.entregable.View;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.entregable.Model.Producto;
import com.example.entregable.R;

import java.util.ArrayList;
import java.util.List;

public class AdapterProducto extends RecyclerView.Adapter <AdapterProducto.ProductoViewHolder> {

    private List<Producto> productoList;
    private ListenerDelAdapter listenerDelAdapter;



    public AdapterProducto(ListenerDelAdapter listenerDelAdapter) {
        this.listenerDelAdapter = listenerDelAdapter;
    }

    public AdapterProducto() {

    }

    public List<Producto> getProductoList() {
        return productoList;
    }

    public void addProductoList(List<Producto> productoList) {
        this.productoList.addAll(productoList);
        notifyDataSetChanged();
    }
    public void setProductoList(List<Producto> productoList) {
        this.productoList = productoList;
        notifyDataSetChanged();
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
        Producto unProducto = productoList.get(position);
        holder.CargarDatosDelProducto(unProducto);
    }

    @Override
    public int getItemCount() {
        return productoList.size();
    }

    public class ProductoViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageViewCeldaProducto;
        private TextView textViewNombreCeldaProducto;
        private TextView textViewPrecioCeldaProducto;

        public ProductoViewHolder(@NonNull View itemView) {
            super(itemView);

            encontrarVistas(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Producto productoSeleccionado = productoList.get(getAdapterPosition());
                    listenerDelAdapter.informarProductoSeleccionado(productoSeleccionado);
                }
            });
        }


        public void CargarDatosDelProducto(Producto unProducto) {
            this.textViewNombreCeldaProducto.setText(unProducto.getNombreProducto());
            this.textViewPrecioCeldaProducto.setText("$ " + unProducto.getPrecioProducto());
            Glide.with(imageViewCeldaProducto.getContext()).load(unProducto.getUrlImagen()).placeholder(R.drawable.loading).into(imageViewCeldaProducto);
        }


        private void encontrarVistas(View vista) {
            imageViewCeldaProducto = vista.findViewById(R.id.imagenViewProducto);
            textViewNombreCeldaProducto = vista.findViewById(R.id.NombreProducto);
            textViewPrecioCeldaProducto = vista.findViewById(R.id.PrecioProducto);
        }

    }

    public interface ListenerDelAdapter{
        void informarProductoSeleccionado(Producto producto);
    }



}
