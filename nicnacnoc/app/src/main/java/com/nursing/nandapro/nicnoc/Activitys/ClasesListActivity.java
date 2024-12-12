package com.nursing.nandapro.nicnoc.Activitys;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;

import com.nursing.nandapro.nicnoc.DatabaseHelper;
import com.nursing.nandapro.nicnoc.DiagnosticoBusqueda.DiagnosticosBusqueda;
import com.nursing.nandapro.nicnoc.Favoritos;
import com.nursing.nandapro.nicnoc.ListAdapter;
import com.nursing.nandapro.nicnoc.ListElement;
import com.nursing.nandapro.nicnoc.R;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.nursing.nandapro.nicnoc.utils.Prefs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ClasesListActivity extends AppCompatActivity {

    List<ListElement> elements;
    Cursor c = null;
    private InterstitialAd interstitial;
    Prefs prefs;

    protected void onResume() {
        // UnityAds.show(MainActivity.this, interstitial_id);

        // UnityBanners.loadBanner(MainActivity.this, banner_id);
        if (interstitial != null) {
            interstitial.show(ClasesListActivity.this);
        } else {
            Log.d("TAG", "The interstitial ad wasn't ready yet.");
        }        super.onResume();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Quitamos barra de notificaciones
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_clases);

        //Título del action bar
        ActionBar ab = getSupportActionBar();
        ab.setTitle(getIntent().getStringExtra("Titulo"));
        ab.setSubtitle(getIntent().getStringExtra("Subtitulo"));

        //lista
        init();
        prefs = new Prefs(getApplicationContext());

        //sobre la edad
        Bundle extras = new Bundle();
        extras.putString("max_ad_content_rating", "MA");

        AdRequest request = new AdRequest.Builder()
                .addNetworkExtrasBundle(AdMobAdapter.class, extras)
                .build();
        // Buscar AdView como recurso y cargar una solicitud.
       // if (prefs.getPremium()==0){

            if (prefs.getRemoveAd()==0){
                if (prefs.getLibroD()==0){
            //initializing admob and loading the banner ad.
            //sobre la edad

            // Buscar AdView como recurso y cargar una solicitud.
            AdView adView = (AdView)this.findViewById(R.id.adView);
            AdRequest adRequest = new AdRequest.Builder().build();
            adView.loadAd(adRequest);

            MobileAds.initialize(this, initializationStatus -> {
            });

            // AdRequest adRequest = new AdRequest.Builder().build();
            InterstitialAd.load(this,(getResources().getString(R.string.interaid)), adRequest,
                    new InterstitialAdLoadCallback() {
                        @Override
                        public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                            // The mInterstitialAd reference will be null until
                            // an ad is loaded.
                            interstitial = interstitialAd;
                            Log.i(TAG, "onAdLoaded");
                        }

                        @Override
                        public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                            // Handle the error
                            Log.i(TAG, loadAdError.getMessage());
                            interstitial = null;
                        }
                    });

        } }
    }


    public void init(){
        String nDominio = getIntent().getStringExtra("nDominio");
        elements = new ArrayList<>();

        DatabaseHelper myDbHelper = new DatabaseHelper(ClasesListActivity.this, MainActivity.namedb);
        try {
            myDbHelper.createDataBase();
            myDbHelper.openDataBase();
        } catch (IOException ioe) {
        }
        c = myDbHelper.query("Clases", null, "N_Dominio like '"+nDominio+"'"  , null, null, null, null);
        if (c.moveToFirst()) {
            do {
                elements.add(new ListElement(c.getString(1),
                        c.getString(3),c.getString(2),
                        c.getString(4),c.getString(5), false));
            } while (c.moveToNext());
        }


        ListAdapter listAdapter = new ListAdapter(elements, this, new ListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(ListElement item) {

                String nClase = item.getDominio();
                Intent mIntent  = new Intent(com.nursing.nandapro.nicnoc.Activitys.ClasesListActivity.this, DiagnosticosListActivity.class);
                mIntent.putExtra("nClase",nClase);
                mIntent.putExtra("nDominio",nDominio);

                mIntent.putExtra("Titulo",getIntent().getStringExtra("Titulo"));
                mIntent.putExtra("Subtitulo",item.getDominio()+":"+item.getTitulo());
                startActivity(mIntent);
                if (interstitial != null) {
                    interstitial.show(ClasesListActivity.this);
                } else {
                    Log.d("TAG", "The interstitial ad wasn't ready yet.");
                }
                overridePendingTransition(R.anim.left_in, R.anim.left_out);

            }
        });


        RecyclerView recyclerView = findViewById(R.id.listRecyclerview);
        recyclerView.setHasFixedSize(true);
        // puede ser cuadricula
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(listAdapter);
    }

    //region Menú
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);



        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_share) {
            shareenviar();
        }

        if(id == R.id.valorar) {
            //calificar();
            Intent mIntent  = new Intent(ClasesListActivity.this, Favoritos.class);
            mIntent.putExtra("Base", "Libro1");
            startActivity(mIntent);
        }

        if (id == R.id.action_about) {
            info();
        }

        if (id == R.id.action_search) {
            Intent mIntent = new Intent(ClasesListActivity.this, DiagnosticosBusqueda.class);
            mIntent.putExtra("Base", "Libro1");
            startActivity(mIntent);
        }
        if(id == R.id.favoritos){
            Intent mIntent  = new Intent(ClasesListActivity.this, Favoritos.class);
            mIntent.putExtra("Base", "Libro1");
            startActivity(mIntent);
        }
        return super.onOptionsItemSelected(item);
    }
    public void shareenviar(){
        try {
            final String appPackageName = getPackageName();
            Intent sharingIntent = new Intent(Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            String shareBody = "https://play.google.com/store/apps/details?id="+appPackageName;
            String shareSub = "enviando/share link";
            sharingIntent.putExtra(Intent.EXTRA_SUBJECT,shareSub);
            sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
            startActivity(Intent.createChooser(sharingIntent, "Share Using"));
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

    }
    public void info(){
        AlertDialog.Builder a = new AlertDialog.Builder(ClasesListActivity.this);
        a.setMessage("This application is designed to have a good time between friends or family the sound is HD made by @ANDRESPRO100" + "\n Version 1.5")
                .setCancelable(false)
                .setPositiveButton("Acept", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = a.create();
        a.setTitle("About");
        a.show();
    }
    public void calificar(){

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(ClasesListActivity.this);
        // Setting Dialog Message
        alertDialog.setTitle("CALIFICAR QUALIFY");
        alertDialog.setMessage(" \n" +
                "Your comment is very important to me, please VALUE or QUALIFY ..." +
                "\n" +
                "Or leave a comment to improve this application " +
                "You'll only take a few minutes. Thanks for your help..."  +"\n" + "\n" +
                "تعليقك مهم جدًا بالنسبة لي ، يرجى RATE أو RATE ... " +
                "أو اترك تعليقاً لتحسين هذا التطبيق ... لن يستغرق الأمر سوى بضع دقائق. شكرا لمساعدتك!");

        alertDialog.setCancelable(true);
        alertDialog.setPositiveButton("===>> RATE <<===", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

                final String appPackageName = getPackageName(); // getPackageName() from Context or Activity object
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=" + appPackageName)));
                } catch (android.content.ActivityNotFoundException anfe) {
                }
            }
        });
        alertDialog.show();

    }
    //endregion
}


