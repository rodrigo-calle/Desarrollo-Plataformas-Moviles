package com.upc.sqliteapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.upc.sqliteapp.entidades.Libro;
import com.upc.sqliteapp.modelo.DAOLibro;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{

    RecyclerView recyclerView;
    FloatingActionButton botonAgregar;

    DAOLibro daoLibro = new DAOLibro(this);
    ArrayList<Libro> listaLibros = new ArrayList<>();
    CustomAdapter customAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        daoLibro.abrirDB();
        asignarReferencias();
        mostrarLibros();
    }

    private void asignarReferencias(){
        recyclerView =findViewById(R.id.recyclerView);
        botonAgregar = findViewById(R.id.botonAgregar);
        botonAgregar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(MainActivity.this, AgregarActivity.class);
                startActivity(intent);
            }
        });
    }
    private void mostrarLibros(){
        listaLibros = daoLibro.mostrarLibros();
        customAdapter = new CustomAdapter(MainActivity.this,this, listaLibros);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1){
            recreate();
        }

    }
}