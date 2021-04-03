package com.upc.sqliteapp.modelo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.upc.sqliteapp.entidades.Libro;
import com.upc.sqliteapp.util.Constantes;
import com.upc.sqliteapp.util.LibroBD;

import java.util.ArrayList;

public class DAOLibro {
    LibroBD libroBD;
    SQLiteDatabase db;
    private final Context context;

    public DAOLibro(Context context){
        libroBD = new LibroBD(context);
        this.context = context;
    }

    public void abrirDB(){
        db = libroBD.getWritableDatabase();
    }
    //metodo para registrar libros

    public void registrarLibro(Libro libro){
         try {
             ContentValues valores = new ContentValues();
             valores.put("titulo", libro.getTitulo());
             valores.put("autor", libro.getAutor());
             valores.put("npaginas", libro.getPaginas());
             long resultado = db.insert(Constantes.NOMBRE_TABLA, null, valores);

             if (resultado == -1){
                 Toast.makeText(context, "Error al insertar", Toast.LENGTH_SHORT).show();
             }else{
                 Toast.makeText(context, "Se registró correctamente", Toast.LENGTH_SHORT).show();
             }

         }catch (Exception e){
             Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
         }
    }

    //metodo para mostrar libros
    public ArrayList<Libro> mostrarLibros(){
        ArrayList<Libro> listaLibros = new ArrayList<>();

        try{
            Cursor c = db.rawQuery("SELECT * FROM "+Constantes.NOMBRE_TABLA, null );
            while(c.moveToNext()){
                listaLibros.add(new Libro(
                        c.getInt(0),
                        c.getString(1),
                        c.getString(2),
                        c.getInt(3)
                ));

            }
        } catch (Exception e){
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return listaLibros;
    }

}
