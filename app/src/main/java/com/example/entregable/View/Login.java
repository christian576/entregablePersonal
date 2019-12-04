package com.example.entregable.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.entregable.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {

    private EditText editTextUsuarioLogin;
    private EditText editTextPasswordLogin;
    private Button buttonRegistrarLogin;
    private Button buttonIngresarLogin;
    private FirebaseAuth mAuth;

    private FirebaseFirestore firestore;
    private FirebaseUser currentUsuer;
    public static final String CLAVE = "CLAVE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        getIntent();

        encontrarComponentes();
        mAuth = FirebaseAuth.getInstance();
        //storage = FirebaseStorage.getInstance();
        currentUsuer = FirebaseAuth.getInstance().getCurrentUser();
        firestore = FirebaseFirestore.getInstance();



        buttonRegistrarLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validacionRegister();
                String email = editTextUsuarioLogin.getText().toString();
                String password = editTextPasswordLogin.getText().toString();

                crearUsuarioDeFirebase(email, password);
            }
        });

        buttonIngresarLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validacionDeLogin();
                String mail = editTextUsuarioLogin.getText().toString();
                String password = editTextPasswordLogin.getText().toString();
                logInUsuarioDeFirebase(mail,password);

            }
        });
    }


    private void   validacionRegister() {
        if (editTextUsuarioLogin != null && editTextPasswordLogin != null) {
            register();
        } else {
            Toast.makeText(Login.this, "MAIL O PASSWORD INVALIDO", Toast.LENGTH_SHORT).show();
        }
    }


    private boolean validacionDeLogin() {
        if (editTextUsuarioLogin != null && editTextPasswordLogin != null) {
            login();
            return true;
        } else {
            Toast.makeText(Login.this, "MAIL O PASSWORD INVALIDO", Toast.LENGTH_SHORT).show();
            return false;
        }



    }


    private void logInUsuarioDeFirebase(String email,String password){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information

                            FirebaseUser user = mAuth.getCurrentUser();
                            //  updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.

                            Toast.makeText(Login.this, task.getException().getMessage(),
                                    Toast.LENGTH_SHORT).show();
                            //   updateUI(null);
                        }

                        // ...
                    }
                });
    }


    private void login() {

        String email = editTextUsuarioLogin.getText().toString();
        String password = editTextPasswordLogin.getText().toString();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            irAlHomeDeLaApp(user.getEmail());
                            Toast.makeText(Login.this, "INICIO OK", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(Login.this, "El usuario no se pudo loguear", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void register() {

        String email = editTextUsuarioLogin.getText().toString();
        String password = editTextPasswordLogin.getText().toString();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            irAlHomeDeLaApp(user.getEmail());
                            Toast.makeText(Login.this, "USUARIO REGISTRADO", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(Login.this, "El usuario no pudo registrarse", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (currentUser != null) {
            irAlHomeDeLaApp(currentUser.getEmail());
        }
    }

    private void irAlHomeDeLaApp(String userName) {

        Bundle bundle = new Bundle();
        bundle.putString("USER_NAME", userName);

        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtras(bundle);

        limpiarCampos();
        startActivity(intent);

    }

    private void crearUsuarioDeFirebase(String mail,String password){
        mAuth.createUserWithEmailAndPassword(mail, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            //   updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(Login.this, task.getException().getMessage(),
                                    Toast.LENGTH_SHORT).show();
                            // updateUI(null);
                        }

                        // ...
                    }
                });
    }

    private void limpiarCampos() {
        editTextUsuarioLogin.setText("");
        editTextPasswordLogin.setText("");
    }

    private void encontrarComponentes() {
        editTextUsuarioLogin = findViewById(R.id.editTextFragmentLoginUsuario);
        editTextPasswordLogin = findViewById(R.id.editTextFragmentLoginPassword);
        buttonIngresarLogin = findViewById(R.id.ButtonLoginFragmentIngresar);
        buttonRegistrarLogin = findViewById(R.id.ButtonLoginFragmentRegistrar);
    }




}
