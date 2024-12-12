package com.nursing.nandapro.nicnoc;

import androidx.viewpager.widget.ViewPager;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.google.android.gms.ads.interstitial.InterstitialAd;

import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.AdRequest;
//import com.google.android.gms.ads.InterstitialAd;

import java.util.ArrayList;

public class WallpaerPagerActivity extends Activity {

    //private MaxAdView adView;

    private InterstitialAd interstitial;
   // Prefs prefs;
    @Override
    protected void onResume() {
        // UnityAds.show(MainActivity.this, interstitial_id);

        // UnityBanners.loadBanner(MainActivity.this, banner_id);
       // interstitial.show();
        super.onResume();

    }
    int pos;
    ArrayList<String> allImageList=new ArrayList<>();
    ViewPager viewpager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Quitamos barra de notificaciones
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //Quitamos barra de titulo de la aplicacion
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallpaer_pager);
/*
        AppLovinSdk.getInstance( this ).setMediationProvider( "max" );
        AppLovinSdk.initializeSdk( this, new AppLovinSdk.SdkInitializationListener() {
            @Override
            public void onSdkInitialized(final AppLovinSdkConfiguration configuration)
            {
                // AppLovin SDK is initialized, start loading ads
            }
        } );
     //   LoadBannerAd();
*/
        pos=getIntent().getIntExtra("pos",0);
        allImageList=getIntent().getStringArrayListExtra("list");
        viewpager=findViewById(R.id.viewPager);

        PagerAdapter pagerAdapter=new PagerAdapter(WallpaerPagerActivity.this,allImageList);
        viewpager.setAdapter(pagerAdapter);
        viewpager.setCurrentItem(pos);

        //prefs = new Prefs(getApplicationContext());

        //sobre la edad
        Bundle extras = new Bundle();
        extras.putString("max_ad_content_rating", "MA");

        AdRequest request = new AdRequest.Builder()
                .addNetworkExtrasBundle(AdMobAdapter.class, extras)
                .build();

        //0 muestra los anuncios 1 no muestra por eso no se pone nada

        //   adView.bringToFront();

    }
/*
    private void LoadBannerAd() {
        adView = new MaxAdView(getResources().getString(R.string.banner), this);
        adView.setListener(new MaxAdViewAdListener() {
            @Override
            public void onAdExpanded(MaxAd ad) {

            }

            @Override
            public void onAdCollapsed(MaxAd ad) {

            }

            @Override
            public void onAdLoaded(MaxAd ad) {

            }

            @Override
            public void onAdDisplayed(MaxAd ad) {

            }

            @Override
            public void onAdHidden(MaxAd ad) {

            }

            @Override
            public void onAdClicked(MaxAd ad) {

            }

            @Override
            public void onAdLoadFailed(String adUnitId, MaxError error) {

            }

            @Override
            public void onAdDisplayFailed(MaxAd ad, MaxError error) {

            }
        });

        int width = ViewGroup.LayoutParams.MATCH_PARENT;
        int height = getResources().getDimensionPixelSize(R.dimen.banner_height);
        adView.setLayoutParams(new FrameLayout.LayoutParams(width, height, Gravity.TOP));
        adView.setBackgroundColor(Color.TRANSPARENT);


        ViewGroup rootView = findViewById( android.R.id.content );
        rootView.addView( adView );
        adView.loadAd();

    }
*/
}