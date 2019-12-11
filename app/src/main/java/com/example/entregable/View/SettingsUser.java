package com.example.entregable.View;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.entregable.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.annotation.Nullable;

import androidx.appcompat.app.AppCompatActivity;

public class SettingsUser extends AppCompatActivity {

    private Uri uri;
    private int PICK_IMAGE_REQUEST = 1;
    private ImageView fotoPerfil;
    private Button botonCargarImagen;
    private Button botonSubirimagen;
    private EditText editTextNombre;
    private EditText editTextApellido;
    private FirebaseFirestore firestore;
    private FirebaseUser currentUser;
    private FirebaseStorage storage;
    private FirebaseAuth mAuth;

    public static final String CLAVE = "CLAVE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settingsuser);
        mAuth = FirebaseAuth.getInstance();

        InfloVistas();

        storage = FirebaseStorage.getInstance();
        currentUser = FirebaseAuth.getInstance().getCurrentUser();
        firestore = FirebaseFirestore.getInstance();

        botonCargarImagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseImage();
            }
        });

        botonSubirimagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cargarImagenAFirebase();

                String nombreUsuario = editTextNombre.getText().toString();
                String apellidoUsuario = editTextApellido.getText().toString();
                Usuario usuario = new Usuario(nombreUsuario, apellidoUsuario);
                guardarInfoUsuario(usuario);

                comunicacion("name");
            }
        });
        traerUsuarioLogueado();
    }

    private void chooseImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Seleccionar Imagen"), PICK_IMAGE_REQUEST);
    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            uri = data.getData();
            try {

                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                fotoPerfil.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

        private void cargarImagenAFirebase() {

            StorageReference path = storage.getReference().child("ProfiePics").child(currentUser.getUid());

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();


            Bitmap bm = ((BitmapDrawable) fotoPerfil.getDrawable()).getBitmap();
            bm.compress(Bitmap.CompressFormat.JPEG, 10, byteArrayOutputStream);
            //lo Tranforma en un array de datos
            byte[] data = byteArrayOutputStream.toByteArray();
            UploadTask uploadTask = path.putBytes(data);
            //intenta subir a la referencia que creamos la imagen como es asyncronico le creamos un on complete listener para ver que hace cunado termina
            uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Toast.makeText(SettingsUser.this, "imagen Cargada exitosamente", Toast.LENGTH_SHORT).show();
                }
            });
        }
            private void guardarInfoUsuario ( final Usuario usuario){
                firestore.collection("Users").document(currentUser.getUid()).set(usuario);
                cargarImagenDelStorageAlDatabase();

            }

    private void cargarImagenDelStorageAlDatabase() {

        StorageReference path = storage.getReference().child("ProfiePics").child(currentUser.getUid());
        path.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                firestore.collection("Users")
                        .document(currentUser.getUid())
                        .update("imagenUrl",uri.toString());
            }
        });
    }

    private void traerUsuarioLogueado () {
        firestore.collection("Users").document(currentUser.getUid()).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Usuario usuario = documentSnapshot.toObject(Usuario.class);

                if (usuario != null) {
                    editTextNombre.setHint(usuario.getNombre());
                    editTextApellido.setHint(usuario.getApellido());
                    if (usuario.getUrlImagen() != null) {
                        Glide.with(SettingsUser.this).load(usuario.getUrlImagen()).into(fotoPerfil);
                    }
                }
            }
        });
    }

        @Override
        public void onStart () {
            super.onStart();
            // Check if user is signed in (non-null) and update UI accordingly.
            FirebaseUser currentUser = mAuth.getCurrentUser();
            //   updateUI(currentUser);
        }

        private void updateUI (FirebaseUser currentUser){
            if (currentUser != null) {
                startActivity(new Intent(this, SettingsUser.class));
            }
        }




        private void InfloVistas () {

            editTextNombre = findViewById(R.id.editText_nombreUsuario);
            editTextApellido = findViewById(R.id.editText_telefonoUsuario);
            botonSubirimagen = findViewById(R.id.botonSubirImagen);
            botonCargarImagen = findViewById(R.id.botonCargarImagen);
            fotoPerfil = findViewById(R.id.MainActicityImagen);
        }

    private void comunicacion(String userName) {

        Bundle bundle = new Bundle();
        bundle.putString(CLAVE, userName);

        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtras(bundle);

        startActivity(intent);

    }







    }
