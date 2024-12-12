package com.nursing.nandapro.nicnoc;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.nursing.nandapro.nicnoc.Activitys.MainActivity;
import com.nursing.nandapro.nicnoc.Fragments.RewardedFragment;
import com.nursing.nandapro.nicnoc.Funcionalidades.Cartel;
import com.nursing.nandapro.nicnoc.R;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.nursing.nandapro.nicnoc.utils.Prefs;

import java.io.IOException;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Libro2descripcion extends AppCompatActivity {

    public TextView tvTitulo,tvDefinicion,tvCaracteristicas,tvFactores,tvPoblacion,tvCondiciones;
    public TextView tvDefinicionT,tvCaracteristicasT,tvFactoresT,tvPoblacionT,tvCondicionesT;

    Cursor c = null;
    Prefs prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Quitamos barra de notificaciones
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descripcion_diagnostico);

        //Inicializar varibles
        tvTitulo = findViewById(R.id.tv_titulo);
        tvDefinicion = findViewById(R.id.tv_definicion);
        tvCaracteristicas = findViewById(R.id.tv_caracteristicas);
        tvFactores = findViewById(R.id.tv_factores);
        tvPoblacion = findViewById(R.id.tv_poblacion);
        tvCondiciones = findViewById(R.id.tv_condiciones);

        tvDefinicionT = findViewById(R.id.titulo2);
        tvCaracteristicasT = findViewById(R.id.titulo4);
        tvFactoresT = findViewById(R.id.titulo6);
        tvPoblacionT = findViewById(R.id.titulo8);
        tvCondicionesT = findViewById(R.id.titulo10);

        //Título del action bar
        ActionBar ab = getSupportActionBar();
        ab.setTitle(getIntent().getStringExtra("Titulo"));
        ab.setSubtitle(getIntent().getStringExtra("Subtitulo"));

        //lista
        ComprobarIdiomaTitulos();
        init();
        prefs = new Prefs(getApplicationContext());

        //sobre la edad
        Bundle extras = new Bundle();
        extras.putString("max_ad_content_rating", "MA");

        AdRequest request = new AdRequest.Builder()
                .addNetworkExtrasBundle(AdMobAdapter.class, extras)
                .build();

        //Aqui en el libro deseado reemplazas por el nuevo
        if (prefs.getRemoveAd()==0){ //Para quitar la publicadad completa seria de volver a reemplazar el getAds si digo que me pongas el boton extra en el cartela vale ya te cache

        if (prefs.getLibroA()==0){
            //initializing admob and loading the banner ad.
            //sobre la edad
            lanzarwifi();
            // Buscar AdView como recurso y cargar una solicitud.
            AdView adView = (AdView)this.findViewById(R.id.adView);
            AdRequest adRequest = new AdRequest.Builder().build();
            adView.loadAd(adRequest);
            adView.setVisibility(View.VISIBLE);

            MobileAds.initialize(this, initializationStatus -> {
            });

            Cartel.GenerarCartel(getSupportFragmentManager(), "noadlinroa" );
        }
    }
        boolean subrayar = getIntent().getBooleanExtra("Highlight",false);
        if(subrayar) Subrayar();


// y poner el nuevo boton en el cartel
    }
    void Subrayar(){
        TextView[] textos ={tvTitulo,
                tvDefinicion,
                tvCaracteristicas,
                tvFactores,
                tvPoblacion,
                tvCondiciones};
        String palabraOringal = getIntent().getStringExtra("Palabra");
        String palabraSubrayada = "<span style='background-color:yellow'>"+palabraOringal+"</span>";
        Pattern pattern = Pattern.compile(palabraOringal,Pattern.CASE_INSENSITIVE);


        for (int i = 0; i < textos.length; i++) {
            String textoOrignal = textos[i].getText().toString();
            Matcher m = pattern.matcher(textoOrignal);

            String textoReemplzado = m.replaceAll(palabraSubrayada);

            textos[i].setText(Html.fromHtml(textoReemplzado));
        }

    }

    public void lanzarwifi() {
        //9 Toast.makeText(this, "Incompleto :c", Toast.LENGTH_SHORT).show();
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if(networkInfo!=null){
            //  if(isConnected())
            // showDialog();
            // Toast.makeText(getApplicationContext(),"INTERNET AVAILABLE",Toast.LENGTH_SHORT).show();
            // else if(isConnected())
            //
            //  info();

        }
        else{
            showDialog();
            Toast.makeText(getApplicationContext(),"INTERNET NOT AVAILABLE",Toast.LENGTH_SHORT).show();
        }
    }
    void showDialog() {

        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.alert_dialog, null);

        Button acceptButton = view.findViewById(R.id.acceptButton);
        Button cancelButton = view.findViewById(R.id.cancelButton);

        acceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e(TAG, "onClick: accept button");
                //  connectGooglePlayBilling();
                lanzarsuscricion();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e(TAG, "onClick: cancel button" );

                lanzarinicio();
            }
        });

        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setView(view)
                .setCancelable(false)
                // .setCanceledOnTouchOutside(false);
                .create();
        // alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.show();



    }

    public void lanzarsuscricion() {
        //9 Toast.makeText(this, "Incompleto :c", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(this, Libro2.class );
        startActivity(i);
/*
        if(isConnected())
            Toast.makeText(getApplicationContext(),"INTERNET AVAILABLE",Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(getApplicationContext(),"INTERNET NOT AVAILABLE",Toast.LENGTH_SHORT).show();
        //  info();
        showDialog();
*/
    }

    public void lanzarinicio() {
        //9 Toast.makeText(this, "Incompleto :c", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(this, MainActivity.class );
        startActivity(i);
/*
        if(isConnected())
            Toast.makeText(getApplicationContext(),"INTERNET AVAILABLE",Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(getApplicationContext(),"INTERNET NOT AVAILABLE",Toast.LENGTH_SHORT).show();
        //  info();
        showDialog();
*/
    }

    private void ComprobarIdiomaTitulos() {
        switch (MainActivity.namedb2){
            case "db_2esp.db":
                tvDefinicionT.setText(" Definición");
                tvCaracteristicasT.setText(" Puntuación global de resultado");
                tvFactoresT.setText(" Puntuación diana");
                tvPoblacionT.setText(" Puntuación global de resultado");
                tvCondicionesT.setText(" Puntuación diana ");
                break;
            case "db_2ng.db":
                tvDefinicionT.setText(" Definition");
                tvCaracteristicasT.setText(" Overall result score");
                tvFactoresT.setText(" Target score");
                tvPoblacionT.setText(" Overall result score");
                tvCondicionesT.setText(" Target score");
                break;
            case "db_2fr.db":
                tvDefinicionT.setText(" Définition");
                tvCaracteristicasT.setText(" Note globale du résultat");
                tvFactoresT.setText(" Note cible");
                tvPoblacionT.setText(" Note globale du résultat");
                tvCondicionesT.setText(" Note cible");
                break;
            case "db_2it.db":
                tvDefinicionT.setText(" Definizione");
                tvCaracteristicasT.setText(" Definire le caratteristiche");
                tvFactoresT.setText(" Punteggio obiettivo");
                tvPoblacionT.setText(" Punteggio complessivo del risultato");
                tvCondicionesT.setText(" Punteggio obiettivo");
                break;
            case "db_2de.db":
                tvDefinicionT.setText(" Definition");
                tvCaracteristicasT.setText(" Merkmale definieren");
                tvFactoresT.setText(" Zielpunktzah");
                tvPoblacionT.setText(" Gesamtergebnisnote");
                tvCondicionesT.setText(" Zielpunktzahl");
                break;
            case "db_2cs.db":
                tvDefinicionT.setText(" Definice");
                tvCaracteristicasT.setText(" Definování charakteristik");
                tvFactoresT.setText(" Cílové skóre");
                tvPoblacionT.setText(" Celkové výsledkové skóre");
                tvCondicionesT.setText(" Cílové skóre");
                break;
            case "db_2et.db":
                tvDefinicionT.setText(" Definitsioon");
                tvCaracteristicasT.setText(" Karakteristikute määratlemine");
                tvFactoresT.setText(" Sihttulemus");
                tvPoblacionT.setText(" Tulemuse üldskoor");
                tvCondicionesT.setText(" Sihttulemus");
                break;
            case "db_2id.db":
                tvDefinicionT.setText(" Definisi");
                tvCaracteristicasT.setText(" Mendefinisikan Karakteristik");
                tvFactoresT.setText(" Skor target");
                tvPoblacionT.setText(" Skor hasil keseluruhan");
                tvCondicionesT.setText(" Skor target");
                break;
            case "db_2pt.db":
                tvDefinicionT.setText(" Definição");
                tvCaracteristicasT.setText(" Características definidoras");
                tvFactoresT.setText(" Pontuação alvo");
                tvPoblacionT.setText(" Pontuação geral do resultado");
                tvCondicionesT.setText(" Pontuação alvo");
                break;
            case "db_2sv.db":
                tvDefinicionT.setText(" Definition");
                tvCaracteristicasT.setText(" Definiera egenskaper");
                tvFactoresT.setText(" Målpoäng");
                tvPoblacionT.setText(" Totalt resultat");
                tvCondicionesT.setText(" Målpoäng");
                break;
            case "db_2ja.db":
                tvDefinicionT.setText(" 意味");
                tvCaracteristicasT.setText(" 特性の定義");
                tvFactoresT.setText(" 目標スコア");
                tvPoblacionT.setText(" 全体的な結果スコア");
                tvCondicionesT.setText(" 目標スコア");
                break;
            case "db_2zh.db":
                tvDefinicionT.setText(" 定義");
                tvCaracteristicasT.setText(" 定義特徵");
                tvFactoresT.setText(" 目標分數");
                tvPoblacionT.setText(" 總成績");
                tvCondicionesT.setText(" 目標分數");
                break;

            case "db_2nl.db":
                tvDefinicionT.setText(" Definitie");
                tvCaracteristicasT.setText(" Algehele resultaatscore");
                tvFactoresT.setText(" Doelscore");
                tvPoblacionT.setText(" Algehele resultaatscore");
                tvCondicionesT.setText(" Doelscore");
                break;
            case "db_2ar.db":
                tvDefinicionT.setText(" تعريف");
                tvCaracteristicasT.setText(" النتيجة الإجمالية");
                tvFactoresT.setText(" النتيجة المستهدفة");
                tvPoblacionT.setText("  النتيجة الإجمالية");
                tvCondicionesT.setText(" النتيجة المستهدفة");
                break;
            case "db_2tr.db":
                tvDefinicionT.setText(" Tanım");
                tvCaracteristicasT.setText("Genel sonuç puanı");
                tvFactoresT.setText("Hedef puan");
                tvPoblacionT.setText("Genel sonuç puanı");
                tvCondicionesT.setText("Hedef puan");
                break;
            case "db_2hi.db":
                tvDefinicionT.setText(" परिभाषा");
                tvCaracteristicasT.setText(" कुल मिलाकर परिणाम स्कोर");
                tvFactoresT.setText(" लक्ष्य स्कोर");
                tvPoblacionT.setText(" कुल मिलाकर परिणाम स्कोर");
                tvCondicionesT.setText(" लक्ष्य स्कोर");
                break;
            case "db_2ur.db":
                tvDefinicionT.setText(" تعریف");
                tvCaracteristicasT.setText("مجموعی نتیجہ سکور");
                tvFactoresT.setText("ہدف سکور");
                tvPoblacionT.setText("مجموعی نتیجہ سکور");
                tvCondicionesT.setText("ہدف سکور");
                break;
            case "db_2pl.db":
                tvDefinicionT.setText(" Definicja");
                tvCaracteristicasT.setText(" Ogólna ocena wyniku");
                tvFactoresT.setText(" Wynik docelowy");
                tvPoblacionT.setText(" Ogólna ocena wyniku");
                tvCondicionesT.setText(" Wynik docelowy");
                break;
            case "db_2ko.db":
                tvDefinicionT.setText(" 정의");
                tvCaracteristicasT.setText(" 전체 결과 점수");
                tvFactoresT.setText(" 목표 점수");
                tvPoblacionT.setText(" 전체 결과 점수");
                tvCondicionesT.setText(" 목표 점수");
                break;
        }
    }


    public void init(){
        String nCodigo = getIntent().getStringExtra("nCodigo");

        DatabaseHelper myDbHelper = new DatabaseHelper(com.nursing.nandapro.nicnoc.Libro2descripcion.this, MainActivity.namedb2);
        try {
            myDbHelper.createDataBase();
            myDbHelper.openDataBase();
        } catch (IOException ioe) {
        }
        c = myDbHelper.query("Tema", null, "Codigo like '"+nCodigo+"'", null, null, null, null);
        if (c.moveToFirst()) {
            for (int i = 2; i < c.getColumnCount() ; i++) {
                if(c.getString(i) != null && !c.getString(i).isEmpty() && c.getString(i).toLowerCase() != "null")
                    mostrar(c.getString(i), i);
            }
        }
    }

    public void mostrar (String texto,int i){

        switch (i){
            case 2:
                tvTitulo.setText(texto);
                tvTitulo.setVisibility(View.VISIBLE);
                break;
            case 3:
                tvDefinicion.setText(texto);
                tvDefinicion.setVisibility(View.VISIBLE);
                tvDefinicionT.setVisibility(View.VISIBLE);
                break;
            case 4:
                tvCaracteristicas.setText(texto);
                tvCaracteristicas.setVisibility(View.VISIBLE);
                tvCaracteristicasT.setVisibility(View.VISIBLE);
                break;
            case 5:
                tvFactores.setText(texto);
                tvFactores.setVisibility(View.VISIBLE);
                tvFactoresT.setVisibility(View.VISIBLE);
                break;
            case 6:
                tvPoblacion.setText(texto);
                tvPoblacion.setVisibility(View.VISIBLE);
                tvPoblacionT.setVisibility(View.VISIBLE);
                break;
            case 7:
                tvCondiciones.setText(texto);
                tvCondiciones.setVisibility(View.VISIBLE);
                tvCondicionesT.setVisibility(View.VISIBLE);
                break;
        }

    }

}