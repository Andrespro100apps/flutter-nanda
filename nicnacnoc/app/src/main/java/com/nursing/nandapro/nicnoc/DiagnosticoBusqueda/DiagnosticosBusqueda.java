package com.nursing.nandapro.nicnoc.DiagnosticoBusqueda;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.nursing.nandapro.nicnoc.Activitys.DescripcionDiagnosticoActivity;
import com.nursing.nandapro.nicnoc.DatabaseHelper;
import com.nursing.nandapro.nicnoc.FuncionesMenu.Busqueda;
import com.nursing.nandapro.nicnoc.FuncionesMenu.MenuFunctions;
import com.nursing.nandapro.nicnoc.Libro2descripcion;
import com.nursing.nandapro.nicnoc.Libro3descripcion;
import com.nursing.nandapro.nicnoc.Libro4descripcion;
import com.nursing.nandapro.nicnoc.ListAdapter;
import com.nursing.nandapro.nicnoc.ListElement;
import com.nursing.nandapro.nicnoc.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DiagnosticosBusqueda extends AppCompatActivity {
    List<ListElement> elements;
    Cursor c = null;
    DatabaseHelper myDbHelper;
    Busqueda encontradas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diagnosticos_busqueda);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main2, menu);

        final SearchView searchView = (SearchView) menu.findItem(R.id.action_search2).getActionView();
        searchView.requestFocus();
        searchView.performClick();
        searchView.setIconified(false);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(
                new ComponentName(this, DiagnosticosBusqueda.class)));
        String bd = getIntent().getStringExtra("Base");
        MenuFunctions menuFunctions = new MenuFunctions();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {

                if (s.isEmpty() || s.equals(" ")) {
                    encontradas = menuFunctions.Search(DiagnosticosBusqueda.this, "   ", bd);
                    init();
                    return true;
                }
/*
                encontradas = menuFunctions.Search(DiagnosticosBusqueda.this, s, bd);
                init();

                return true;
              */
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if (s.isEmpty() || s.equals(" ")) {
                    encontradas = menuFunctions.Search(DiagnosticosBusqueda.this, "  ", bd);
                    init();
                    return true;
                }
                encontradas = menuFunctions.Search(DiagnosticosBusqueda.this, s, bd);
                init();
                return true;
            }
        });


        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    public void init() {
        ArrayList<String> busqueda = encontradas.elements;

        ActionBar ab = getSupportActionBar();
        ab.setTitle("Buscador");
//        ab.setSubtitle("que contienen " + encontradas.palabra );

        TextView text = findViewById(R.id.texto);
        text.setText("Se encontraron " + busqueda.size() + " diagnosticos que contienen '" + encontradas.palabra + "'");

        elements = new ArrayList<>();
        String dbName = encontradas.nameDb;

        myDbHelper = new DatabaseHelper(DiagnosticosBusqueda.this, dbName);
        try {
            myDbHelper.createDataBase();
            myDbHelper.openDataBase();
        } catch (IOException ioe) {
        }
        for (int i = 0; i < busqueda.size(); i++) {
            c = myDbHelper.query("Diagnosticos", null, "N_Diagnostico like '" + busqueda.get(i) + "'", null, null, null, null);
            if (c.moveToFirst()) {
                do {
                    elements.add(new ListElement(c.getString(0),
                            c.getString(1), c.getString(2),
                            c.getString(3), c.getString(4),
                            false));
                } while (c.moveToNext());
            }
        }


        ListAdapter listAdapter = new ListAdapter(elements, this, new ListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(ListElement item) {
                Class clase = DescripcionDiagnosticoActivity.class;
                if (dbName.contains("2")) clase = Libro2descripcion.class;
                else if (dbName.contains("3")) clase = Libro3descripcion.class;
                else if (dbName.contains("4")) clase = Libro4descripcion.class;

                String nCodigo = item.getDominio();
                Intent mIntent = new Intent(DiagnosticosBusqueda.this, clase);
                mIntent.putExtra("nCodigo", nCodigo);

              //  String a = ObtenerTitulo(item.getClases());
                String b = ObtenerSubtitulo(item.getClases(), item.getDiagnostico());
              //  mIntent.putExtra("Titulo", a);
                mIntent.putExtra("Subtitulo", b);
                mIntent.putExtra("Highlight",false);
                mIntent.putExtra("Palabra",encontradas.palabra);
                startActivity(mIntent);

              //  overridePendingTransition(R.anim.left_in, R.anim.left_out);

            }
        });


        RecyclerView recyclerView = findViewById(R.id.listRecyclerview);
        recyclerView.setHasFixedSize(false);
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