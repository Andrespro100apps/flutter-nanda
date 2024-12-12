package com.nursing.nandapro.nicnoc.Calculators;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.gms.ads.nativead.NativeAd;
import com.nursing.nandapro.nicnoc.NativeTemplateStyle;
import com.nursing.nandapro.nicnoc.R;
import com.nursing.nandapro.nicnoc.TemplateView;
import com.nursing.nandapro.nicnoc.utils.Prefs;

public class Calculadoras extends AppCompatActivity {

    Prefs prefs;
    NativeAd mNativeAd;
    private InterstitialAd interstitial;

    MediaPlayer click;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Quitamos barra de notificaciones
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculadoras);

        click = MediaPlayer.create(this, R.raw.click);

        Bundle extras = new Bundle();
        extras.putString("max_ad_content_rating", "MA");

        AdRequest request = new AdRequest.Builder()
                .addNetworkExtrasBundle(AdMobAdapter.class, extras)
                .build();
        prefs = new Prefs(getApplicationContext());

        if (prefs.getRemoveAd()==0){
            //initializing admob and loading the banner ad.
            //sobre la edad
            //sobre la edad

            // Buscar AdView como recurso y cargar una solicitud.
            AdView adView = (AdView)this.findViewById(R.id.adView);
            AdRequest adRequest = new AdRequest.Builder().build();
            adView.loadAd(adRequest);
            adView.setVisibility(View.VISIBLE);

            MobileAds.initialize(this, initializationStatus -> {
            });

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

            MobileAds.initialize(this);
            AdLoader adLoader = new AdLoader.Builder(this, getResources().getString(R.string.nativeid))
                    .forNativeAd(new NativeAd.OnNativeAdLoadedListener() {
                        @Override
                        public void onNativeAdLoaded(NativeAd nativeAd) {
                            //   View dialogView = getLayoutInflater().inflate(R.layout.activity_main, null);

                            //   TemplateView template = findViewById(R.id.my_template);

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

        } }


    public void lanzarbmi(View view) {
        //9 Toast.makeText(this, "Incompleto :c", Toast.LENGTH_SHORT).show();

        Intent i = new Intent(this, Bmi.class );
        startActivity(i);
        click.start();
        overridePendingTransition(R.anim.left_in, R.anim.left_out);

        if (interstitial != null) {
            interstitial.show(Calculadoras.this);
        } else {
            Log.d("TAG", "The interstitial ad wasn't ready yet.");
        }
    }

    public void lanzarCALCULADORAWEB(View view) {
        Intent i = new Intent(this, Calculadoraweb.class );
        startActivity(i);
        click.start();
        overridePendingTransition(R.anim.left_in, R.anim.left_out);

        if (interstitial != null) {
            interstitial.show(Calculadoras.this);
        } else {
            Log.d("TAG", "The interstitial ad wasn't ready yet.");
        }


    }
}