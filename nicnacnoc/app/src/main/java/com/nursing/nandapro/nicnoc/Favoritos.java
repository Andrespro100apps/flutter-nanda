package com.nursing.nandapro.nicnoc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import com.nursing.nandapro.nicnoc.Activitys.DescripcionDiagnosticoActivity;
import com.nursing.nandapro.nicnoc.Activitys.MainActivity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Favoritos extends AppCompatActivity {
    List<ListElement> elements;
    Cursor c = null;
    DatabaseHelper myDbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoritos);
        populateList();
    }

    public void populateList(){
        elements = new ArrayList<>();

        String BaseDatos = getIntent().getStringExtra("Base");
        String dbName;
        if (BaseDatos.contains("2")) dbName = MainActivity.namedb2;
        else if (BaseDatos.contains("3")) dbName = MainActivity.namedb3;
        else if (BaseDatos.contains("4")) dbName = MainActivity.namedb5;
        else if (BaseDatos.contains("10")) dbName = MainActivity.namedb4;

        else dbName = MainActivity.namedb;

        myDbHelper = new DatabaseHelper(Favoritos.this, dbName);
        try {
            myDbHelper.createDataBase();
            myDbHelper.openDataBase();
        } catch (IOException ioe) {
        }
        c = myDbHelper.rawquery("SELECT * FROM Diagnosticos where favoritos like 1");
        if (c.moveToFirst()) {
            do {
                elements.add(new ListElement(c.getString(0),
                        c.getString(1), c.getString(2),
                        c.getString(3), c.getString(4),
                        false));
            } while (c.moveToNext());



    }
        ListAdapter listAdapter = new ListAdapter(elements, this, new ListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(ListElement item) {
                Class clase = DescripcionDiagnosticoActivity.class;
                if (BaseDatos.contains("2")) clase = Libro2descripcion.class;
                else if (BaseDatos.contains("3")) clase = Libro3descripcion.class;
                else if (BaseDatos.contains("4")) clase = Libro4descripcion.class;

                String nCodigo = item.getDominio();
                Intent mIntent = new Intent(Favoritos.this, clase);
                mIntent.putExtra("nCodigo", nCodigo);

                String a = ObtenerTitulo(item.getClases());
                String b = ObtenerSubtitulo(item.getClases(), item.getDiagnostico());
                mIntent.putExtra("Titulo", a);
                mIntent.putExtra("Subtitulo", b);
                startActivity(mIntent);

                overridePendingTransition(R.anim.left_in, R.anim.left_out);

            }
        });


        RecyclerView recyclerView = findViewById(R.id.listRecyclerview);
        recyclerView.setHasFixedSize(true);
        // puede ser cuadricula
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(listAdapter);
}

    private String ObtenerTitulo(String dominio) {
        c = myDbHelper.query("Dominios", new String[]{"Titulo"}, "Dominio like '" + dominio + "'", null, null, null, null);
        if (c.moveToFirst())
            return dominio + ":" + c.getString(0);
        else
            return "";
    }

    private String ObtenerSubtitulo(String dominio, String clase) {
        c = myDbHelper.query("Clases", new String[]{"Titulo"}, "N_Dominio like '" + dominio + "' and N_Clase like '" + clase + "'", null, null, null, null);
        if (c.moveToFirst())
            return clase + ":" + c.getString(0);
        else
            return "";
    }


}