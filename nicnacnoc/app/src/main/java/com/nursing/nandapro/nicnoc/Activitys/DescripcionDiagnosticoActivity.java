package com.nursing.nandapro.nicnoc.Activitys;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.nativead.NativeAd;
import com.google.android.gms.ads.nativead.NativeAdOptions;
import com.nursing.nandapro.nicnoc.DatabaseHelper;
import com.nursing.nandapro.nicnoc.Fragments.RewardedFragment;
import com.nursing.nandapro.nicnoc.Funcionalidades.Cartel;
import com.nursing.nandapro.nicnoc.NativeTemplateStyle;
import com.nursing.nandapro.nicnoc.R;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.nursing.nandapro.nicnoc.TemplateView;
import com.nursing.nandapro.nicnoc.utils.Prefs;

import java.io.IOException;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DescripcionDiagnosticoActivity extends AppCompatActivity {

    public TextView tvTitulo,tvDefinicion,tvCaracteristicas,tvFactores,tvPoblacion,tvCondiciones;
    public TextView tvDefinicionT,tvCaracteristicasT,tvFactoresT,tvPoblacionT,tvCondicionesT;

    Cursor c = null;
    Prefs prefs;
    NativeAd mNativeAd;


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
        TemplateView template = findViewById(R.id.my_template);

        //Título del action bar
        ActionBar ab = getSupportActionBar();
        ab.setTitle(getIntent().getStringExtra("Titulo"));
        ab.setSubtitle(getIntent().getStringExtra("Subtitulo"));

        //lista
        ComprobarIdiomaTitulos();
        init();
        prefs = new Prefs(getApplicationContext());

       // if (prefs.getPremium()==0){
        if (prefs.getRemoveAd()==0){
            if (prefs.getLibroD()==0){
              //  if (prefs.getLibroA()==0){

                loadNativeAd();
            //initializing admob and loading the banner ad.
            //sobre la edad
            //sobre la edad
            Bundle extras = new Bundle();
            extras.putString("max_ad_content_rating", "MA");

            AdRequest request = new AdRequest.Builder()
                    .addNetworkExtrasBundle(AdMobAdapter.class, extras)
                    .build();
            // Buscar AdView como recurso y cargar una solicitud.
            AdView adView = (AdView)this.findViewById(R.id.adView);
            AdRequest adRequest = new AdRequest.Builder().build();
            adView.loadAd(adRequest);
            adView.setVisibility(View.VISIBLE);

            MobileAds.initialize(this, initializationStatus -> {
            });

            MobileAds.initialize(this);
            AdLoader adLoader = new AdLoader.Builder(this, getResources().getString(R.string.nativeid))
                    .forNativeAd(new NativeAd.OnNativeAdLoadedListener() {
                        @Override
                        public void onNativeAdLoaded(NativeAd nativeAd) {
                            //   View dialogView = getLayoutInflater().inflate(R.layout.activity_main, null);

                           //   TemplateView template = findViewById(R.id.my_template);

                           //  template.setVisibility(View.GONE);

                            if(mNativeAd!=null) {
                                NativeTemplateStyle styles = new
                                        NativeTemplateStyle.Builder().build();
                                TemplateView template = findViewById(R.id.my_template);

                                template.setVisibility(View.VISIBLE);
                                template.setStyles(styles);
                                template.setNativeAd(nativeAd);

                            }
                            // NativeTemplateStyle styles = new
                            //      NativeTemplateStyle.Builder().build();
                            //    TemplateView template = findViewById(R.id.my_template);
                            //  template.setVisibility(View.VISIBLE);

                            // template.setStyles(styles);
                            // template.setNativeAd(nativeAd);
                        }
                    })
                    .build();

            adLoader.loadAd(new AdRequest.Builder().build());

          // Cartel.GenerarCartel(getSupportFragmentManager());
            Cartel.GenerarCartel(getSupportFragmentManager(), "noadlinrod" );

        } }

        boolean subrayar = getIntent().getBooleanExtra("Highlight",false);
        if(subrayar) Subrayar();




    }

    private void loadNativeAd(){
        AdLoader adLoader = new AdLoader.Builder(DescripcionDiagnosticoActivity.this, getResources().getString(R.string.nativeid))

                .forNativeAd(new NativeAd.OnNativeAdLoadedListener() {
                    @Override
                    public void onNativeAdLoaded(NativeAd nativeAd) {
                        Log.d(TAG, "Native Ad Loaded");


                        if (isDestroyed()) {
                            nativeAd.destroy();
                            Log.d(TAG, "Native Ad Destroyed");
                            return;
                        }

                        mNativeAd=nativeAd;

                    }
                })

                .withAdListener(new AdListener() {
                    @Override
                    public void onAdFailedToLoad(LoadAdError adError) {
                        // Handle the failure by logging, altering the UI, and so on.
                        Log.d(TAG, "Native Ad Failed To Load");

                    }
                })


                .withNativeAdOptions(new NativeAdOptions.Builder()
                        .build())
                .build();

        adLoader.loadAd(new AdRequest.Builder().build());

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
    private void ComprobarIdiomaTitulos() {
        switch (MainActivity.namedb){
            case "db_esp.db":
                tvDefinicionT.setText(" Definición");
                tvCaracteristicasT.setText(" (M/P) Características Definitorias");
                tvFactoresT.setText(" (R/C) Factores relacionados");
                tvPoblacionT.setText(" Población de riesgo");
                tvCondicionesT.setText(" Condiciones asociadas");
                break;
            case "db_ing.db":
                tvDefinicionT.setText(" Definition");
                tvCaracteristicasT.setText("Defining Characteristics");
                tvFactoresT.setText(" Related factors");
                tvPoblacionT.setText(" Population at risk");
                tvCondicionesT.setText(" Associated conditions");
                break;
            case "db_fr.db":
                tvDefinicionT.setText(" Définition");
                tvCaracteristicasT.setText(" Définir les caractéristiques");
                tvFactoresT.setText(" Facteurs liés");
                tvPoblacionT.setText(" Population à risque");
                tvCondicionesT.setText(" Conditions associées");
                break;
            case "db_it.db":
                tvDefinicionT.setText(" Definizione");
                tvCaracteristicasT.setText(" Definire le caratteristiche");
                tvFactoresT.setText(" Fattori correlati");
                tvPoblacionT.setText(" Popolazione a rischio");
                tvCondicionesT.setText(" Condizioni associate");
                break;
            case "db_de.db":
                tvDefinicionT.setText(" Definition");
                tvCaracteristicasT.setText(" Merkmale definieren");
                tvFactoresT.setText(" Verwandte Faktoren");
                tvPoblacionT.setText(" Bevölkerung in Gefahr");
                tvCondicionesT.setText(" Zugehörige Bedingungen");
                break;
            case "db_cs.db":
                tvDefinicionT.setText(" Definice");
                tvCaracteristicasT.setText(" Definování charakteristik");
                tvFactoresT.setText(" Související faktory");
                tvPoblacionT.setText(" Populace v ohrožení");
                tvCondicionesT.setText(" Přidružené podmínky");
                break;
            case "db_et.db":
                tvDefinicionT.setText(" Definitsioon");
                tvCaracteristicasT.setText(" Karakteristikute määratlemine");
                tvFactoresT.setText(" Seotud tegurid");
                tvPoblacionT.setText(" Ohustatud elanikkond");
                tvCondicionesT.setText(" Seotud tingimused");
                break;
            case "db_id.db":
                tvDefinicionT.setText(" Definisi");
                tvCaracteristicasT.setText(" Mendefinisikan Karakteristik");
                tvFactoresT.setText(" Faktor terkait");
                tvPoblacionT.setText(" Populasi berisiko");
                tvCondicionesT.setText(" Kondisi terkait");
                break;
            case "db_pt.db":
                tvDefinicionT.setText(" Definição");
                tvCaracteristicasT.setText(" Características definidoras");
                tvFactoresT.setText(" Fatores relacionados");
                tvPoblacionT.setText(" População em risco");
                tvCondicionesT.setText(" Condições associadas");
                break;
            case "db_sv.db":
                tvDefinicionT.setText(" Definition");
                tvCaracteristicasT.setText(" Definiera egenskaper");
                tvFactoresT.setText(" Relaterade faktorer");
                tvPoblacionT.setText(" Befolkning i riskzonen");
                tvCondicionesT.setText(" Tillhörande förhållanden");
                break;
            case "db_ja.db":
                tvDefinicionT.setText(" 意味");
                tvCaracteristicasT.setText(" 特性の定義");
                tvFactoresT.setText(" 関連する要因r");
                tvPoblacionT.setText(" 危険にさらされている人口");
                tvCondicionesT.setText(" 関連する条件");
                break;
            case "db_zh.db":
                tvDefinicionT.setText(" 定義");
                tvCaracteristicasT.setText(" 定義特徵");
                tvFactoresT.setText(" 相關因素");
                tvPoblacionT.setText(" 面臨風險的人口");
                tvCondicionesT.setText(" 相關條件");
                break;
            case "db_nl.db":
                tvDefinicionT.setText(" Definitie");
                tvCaracteristicasT.setText(" Eigenschappen definiëren");
                tvFactoresT.setText(" Verwante factoren");
                tvPoblacionT.setText(" Bevolking in gevaar");
                tvCondicionesT.setText(" Bijbehorende voorwaarden");
                break;
            case "db_ar.db":
                tvDefinicionT.setText(" تعريف");
                tvCaracteristicasT.setText(" تحديد الخصائص");
                tvFactoresT.setText(" العوامل ذات الصلة");
                tvPoblacionT.setText(" السكان في خطر");
                tvCondicionesT.setText(" الشروط المرتبطة");
                break;
            case "db_tr.db":
                tvDefinicionT.setText(" Tanım");
                tvCaracteristicasT.setText(" Özellikleri tanımlama");
                tvFactoresT.setText(" İlgili faktörler");
                tvPoblacionT.setText(" Risk altındaki nüfus");
                tvCondicionesT.setText(" ilişkili koşullar");
                break;

            case "db_hi.db":
                tvDefinicionT.setText(" परिभाषा");
                tvCaracteristicasT.setText(" विशिष्टता को परिभाषित");
                tvFactoresT.setText(" संबंधित कारक");
                tvPoblacionT.setText(" जोखिम में आबादी");
                tvCondicionesT.setText(" संबद्ध शर्तें");
                break;
            case "db_ur.db":
                tvDefinicionT.setText(" تعریف");
                tvCaracteristicasT.setText(" خصوصیات کی وضاحت کرنا");
                tvFactoresT.setText(" متعلقہ عوامل");
                tvPoblacionT.setText(" خطرے میں آبادی");
                tvCondicionesT.setText(" وابستہ حالات");
                break;
            case "db_pl.db":
                tvDefinicionT.setText(" Definicja");
                tvCaracteristicasT.setText(" Określenie charakterystyk\n");
                tvFactoresT.setText(" Powiązane czynniki");
                tvPoblacionT.setText(" Zagrożona populacja");
                tvCondicionesT.setText(" Powiązane warunki");
                break;
            case "db_ko.db":
                tvDefinicionT.setText(" 정의");
                tvCaracteristicasT.setText(" 기능 정의");
                tvFactoresT.setText(" 관련 요소");
                tvPoblacionT.setText(" 위험 인구");
                tvCondicionesT.setText(" 관련 조건");
                break;
        }
    }


    public void init(){
        String nCodigo = getIntent().getStringExtra("nCodigo");

        DatabaseHelper myDbHelper = new DatabaseHelper(DescripcionDiagnosticoActivity.this, MainActivity.namedb);
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