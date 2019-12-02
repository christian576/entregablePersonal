package com.example.entregable.View;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.entregable.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentSomos extends Fragment {


    public FragmentSomos() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista = inflater.inflate(R.layout.fragment_fragment_somos, container, false);

        return vista;
    }

}
