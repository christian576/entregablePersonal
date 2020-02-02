package com.example.entregable.View;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.entregable.Controller.FirestoreController;
import com.example.entregable.R;
import com.example.entregable.View.AdapterProducto;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentFavoritos extends Fragment {

    private RecyclerView recyclerViewFavoritos;
    private AdapterProducto adapterProducto;
    private FirestoreController firestoreController;


    public FragmentFavoritos() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View vista = inflater.inflate(R.layout.fragment_fragment_favoritos, container, false);

        recyclerViewFavoritos = vista.findViewById(R.id.RecyclerViewListaFavoritos);

        adapterProducto = new AdapterProducto();
        firestoreController = new FirestoreController();

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2,RecyclerView.VERTICAL,false);
        recyclerViewFavoritos.setLayoutManager(gridLayoutManager);
        recyclerViewFavoritos.setAdapter(adapterProducto);




        return vista;
    }
}
