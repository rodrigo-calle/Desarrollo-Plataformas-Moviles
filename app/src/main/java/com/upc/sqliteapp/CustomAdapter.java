package com.upc.sqliteapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.upc.sqliteapp.entidades.Libro;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.miVista> {

    private Context context;
    private ArrayList<Libro> listaLibros = new ArrayList<>();
    private Activity activity;

    public CustomAdapter(Activity activity, Context context, ArrayList<Libro> listaLibros){

        this.context = context;
        this.listaLibros = listaLibros;
        this.activity = activity;
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
        holder.filaLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ActualizarActivity.class);
                intent.putExtra("id", String.valueOf(listaLibros.get(position).getId()));
                intent.putExtra("titulo", String.valueOf(listaLibros.get(position).getTitulo()));
                intent.putExtra("autor", String.valueOf(listaLibros.get(position).getAutor()));
                intent.putExtra("paginas", String.valueOf(listaLibros.get(position).getPaginas()));
                //context
                activity.startActivityForResult(intent,1);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listaLibros.size();
    }
    public class miVista extends RecyclerView.ViewHolder {
        TextView libro_titulo, libro_autor, libro_paginas;
        LinearLayout filaLayout;
        public miVista(@NonNull View itemView) {
            super(itemView);
            libro_titulo = itemView.findViewById(R.id.libro_titulo);
            libro_autor = itemView.findViewById(R.id.libro_autor);
            libro_paginas = itemView.findViewById(R.id.libro_paginas);
            filaLayout = itemView.findViewById(R.id.filaLayout);
        }
    }
}
