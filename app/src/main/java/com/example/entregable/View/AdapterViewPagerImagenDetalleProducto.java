package com.example.entregable.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.entregable.Model.Producto;
import com.example.entregable.Model.ProductoImagen;

import java.util.ArrayList;
import java.util.List;

public class AdapterViewPagerImagenDetalleProducto extends FragmentStatePagerAdapter {
    List<Fragment> fragmentList;

    public AdapterViewPagerImagenDetalleProducto(FragmentManager fm, Producto producto) {
        super(fm);
        this.fragmentList = new ArrayList<>();


        for(ProductoImagen unaImagen : producto.getListImagenes()){

            ImagenDetalleProductoFragment fragment = ImagenDetalleProductoFragment.dameUnFragment(unaImagen.getUrlImagen());

            fragmentList.add(fragment);
        }

    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }


}
