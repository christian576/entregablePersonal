package com.example.entregable.View;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.entregable.R;

class ImagenDetalleProductoFragment extends Fragment {
    public static final String IMAGEN_PRODUCTO = "CLAVE_IMAGEN_PRODUCTO";
    private ImageView imageView;


    public ImagenDetalleProductoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista = inflater.inflate(R.layout.fragment_imagen_detalle_producto, container, false);

        imageView = vista.findViewById(R.id.ImageViewDetalleProductoViewPager);

        Bundle unBundle = getArguments();
        String unaImagen = unBundle.getString(IMAGEN_PRODUCTO);

        Glide.with(getContext()).load(unaImagen).into(imageView);


        return vista;
    }

    public static ImagenDetalleProductoFragment dameUnFragment(String imagen) {
        ImagenDetalleProductoFragment fragment = new ImagenDetalleProductoFragment();
        Bundle bundle = new Bundle();
        bundle.putString(IMAGEN_PRODUCTO, imagen);
        fragment.setArguments(bundle);
        return fragment;
    }
}
