package com.example.entregable.Model;

import androidx.annotation.NonNull;

import com.example.entregable.Utils.ResultListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class FirestoreDao {

    public static final String PRODUCTOS_FAVORITOS = "Productos_Favoritos";
    private FirebaseFirestore firestore;
    private FirebaseUser currentUser;
    private ContainerProductos containerDeProductos;

    public FirestoreDao(){
        firestore = FirebaseFirestore.getInstance();
        currentUser = FirebaseAuth.getInstance().getCurrentUser();
        traerProductosFavoritos();
    }


    public void agregarProductoAFav(Producto producto) {
        if (containerDeProductos.contieneProducto(producto)){
            containerDeProductos.quitarProducto(producto);
        }else {
            containerDeProductos.agregarProducto(producto);
        }

        firestore.collection(PRODUCTOS_FAVORITOS)
                .document(currentUser.getUid())
                .set(containerDeProductos);
    }

    private void traerProductosFavoritos(){
        firestore.collection(PRODUCTOS_FAVORITOS)
                .document(currentUser.getUid())
                .get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                containerDeProductos = documentSnapshot.toObject(ContainerProductos.class);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                containerDeProductos = new ContainerProductos();
            }
        });
    }

    public void traerProductosFavoritos(final ResultListener<List<Producto>> listResultListener) {
        firestore.collection(PRODUCTOS_FAVORITOS)
                .document(currentUser.getUid())
                .get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                containerDeProductos = documentSnapshot.toObject(ContainerProductos.class);
                if (containerDeProductos == null){
                    containerDeProductos = new ContainerProductos();
                }
                listResultListener.finish(containerDeProductos.getProductoList());
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                containerDeProductos = new ContainerProductos();
                listResultListener.finish(containerDeProductos.getProductoList());
            }
        });
    }
}
