package com.nursing.nandapro.nicnoc.FuncionesMenu;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.nursing.nandapro.nicnoc.Activitys.MainActivity;
import com.nursing.nandapro.nicnoc.DatabaseHelper;
import com.nursing.nandapro.nicnoc.R;

import java.io.IOException;

public class MenuFunctions {

    private static final String TAG = "Menu Functions";

    public Busqueda Search(Context context, String word, String BaseDatos) {
//        Activity activity = (Activity) context;
        String dbName;
        if (BaseDatos.contains("2")) dbName = MainActivity.namedb2;
        else if (BaseDatos.contains("3")) dbName = MainActivity.namedb3;
        else if (BaseDatos.contains("4")) dbName = MainActivity.namedb5;
        else if (BaseDatos.contains("10")) dbName = MainActivity.namedb4;

        else dbName = MainActivity.namedb;


        Busqueda busqueda = new Busqueda();
        busqueda.palabra = word;
        busqueda.nameDb = dbName;
        DatabaseHelper myDbHelper = new DatabaseHelper(context, dbName);
        try {
            myDbHelper.createDataBase();
            myDbHelper.openDataBase();
        } catch (IOException ioe) {
        }
        String query = "Definicion like '%" + word + "%' Or Caracteristicas like '%" + word + "%' Or Factores like '%" + word + "%' Or Poblacion like '%" + word + "%' Or Condiciones like '%" + word + "%'";

        Cursor c = myDbHelper.query("Tema", null, query, null, null, null, null);

        if (c.moveToFirst()) {
            do {
                busqueda.elements.add(c.getString(1));
//                Log.d("xd", "Search: " + c.getString(1));
            } while (c.moveToNext());
        }
        return busqueda;


    }


    public void FavoritosActualizarDataBase(Context context, String BaseDatos){
        Log.d(TAG, "FavoritosActualizarDataBase: 1 "+BaseDatos);

        DatabaseHelper myDbHelper;
        myDbHelper = new DatabaseHelper(context, BaseDatos);
        try {
            myDbHelper.createDataBase();
            myDbHelper.openDataBase();
        } catch (IOException ioe) {
        }
        Cursor c =  myDbHelper.rawquery("SELECT * FROM Diagnosticos limit 1");
        if (c.getColumnIndex("favoritos") == -1) {
            Log.d(TAG, "FavoritosActualizarDataBase: "+BaseDatos);
            myDbHelper.insert("ALTER TABLE Diagnosticos ADD favoritos INTEGER default 0");
        }
    }

}

