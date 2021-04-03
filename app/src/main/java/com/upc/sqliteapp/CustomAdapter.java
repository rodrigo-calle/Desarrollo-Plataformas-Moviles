package com.upc.sqliteapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.upc.sqliteapp.entidades.Libro;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.miVista> {

    private Context context;
    private ArrayList<Libro> listaLibros = new ArrayList<>();

    public CustomAdapter(Context context, ArrayList<Libro> listaLibros){

        this.context = context;
        this.listaLibros = listaLibros;
    }

    @NonNull
    @Override
    public CustomAdapter.miVista onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.nueva_fila, parent, false);
        return new miVista(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.miVista holder, int position) {
        holder.libro_titulo.setText(String.valueOf(listaLibros.get(position).getTitulo()));
        holder.libro_autor.setText(String.valueOf(listaLibros.get(position).getAutor()));
        holder.libro_paginas.setText(String.valueOf(listaLibros.get(position).getPaginas()));

    }

    @Override
    public int getItemCount() {
        return listaLibros.size();
    }

    public class miVista extends RecyclerView.ViewHolder {
        TextView libro_titulo, libro_autor, libro_paginas;
        public miVista(@NonNull View itemView) {
            super(itemView);
            libro_titulo = itemView.findViewById(R.id.libro_titulo);
            libro_autor = itemView.findViewById(R.id.libro_autor);
            libro_paginas = itemView.findViewById(R.id.libro_paginas);
        }
    }
}
