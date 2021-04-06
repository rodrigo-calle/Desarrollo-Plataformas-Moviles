package com.upc.sqliteapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.upc.sqliteapp.entidades.Libro;
import com.upc.sqliteapp.modelo.DAOLibro;

public class ActualizarActivity extends AppCompatActivity {
    EditText editTitulo, editAutor, editPaginas;
    Button btnActualizar, btnEliminar;
    String titulo, autor;
    int id, paginas;

    Libro libro;
    DAOLibro daoLibro = new DAOLibro(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar);
        asignarReferencias();
        recibirInformacion();
    }

    private void asignarReferencias(){
        editTitulo = findViewById(R.id.editTitulo);
        editAutor = findViewById(R.id.editAutor);
        editPaginas = findViewById(R.id.editPaginas);
        btnActualizar = findViewById(R.id.btnActualizar);
        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                daoLibro.abrirDB();
                capturarDatos();
                daoLibro.modificarLibro(libro);
            }
        });

        btnEliminar = findViewById(R.id.btnEliminar);
        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmar();
            }
        });
    }

    private void confirmar(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Eliminar "+titulo+" ? ");
        builder.setMessage("Desea eliminar el libro "+titulo+"?");
        builder.setPositiveButton("SI", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                daoLibro.abrirDB();
                daoLibro.eliminarLibro(id);
                finish();

            }
        });
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {

            }
        });
        builder.create().show();
    }

    private void capturarDatos(){
        titulo = editTitulo.getText().toString();
        autor = editAutor.getText().toString();
        paginas = Integer.parseInt(editPaginas.getText().toString());
        libro = new Libro(id, titulo, autor, paginas);
    }

    private void recibirInformacion(){
        titulo = getIntent().getStringExtra("titulo");
        autor = getIntent().getStringExtra("autor");
        id = Integer.parseInt(getIntent().getStringExtra("id"));
        paginas = Integer.parseInt(getIntent().getStringExtra("paginas"));
    //sit ifo en las cajas de texto
        editTitulo.setText(titulo);
        editAutor.setText(autor);
        editPaginas.setText(String.valueOf(paginas));

    }
}