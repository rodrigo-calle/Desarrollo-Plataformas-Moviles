package com.upc.sqliteapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.upc.sqliteapp.entidades.Libro;
import com.upc.sqliteapp.modelo.DAOLibro;

public class AgregarActivity extends AppCompatActivity {
    EditText txtTitulo, txtAutor, txtPaginas;
    Button btnGuardar;


    DAOLibro daoLibro = new DAOLibro(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar);
        asignarReferencias();

    }

    private void asignarReferencias(){
        txtTitulo = findViewById(R.id.txtTitulo);
        txtAutor = findViewById(R.id.txtAutor);
        txtPaginas = findViewById(R.id.txtPaginas);
        btnGuardar = findViewById(R.id.btnGuardar);
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //abrir la base de datos
                    daoLibro.abrirDB();
                //preparar el objeto
                Libro libro = new Libro(txtTitulo.getText().toString(), txtAutor.getText().toString(), Integer.parseInt(txtPaginas.getText().toString()));
                //enviar  el objeto a insertar
                daoLibro.registrarLibro(libro);
            }
        });
    }
}