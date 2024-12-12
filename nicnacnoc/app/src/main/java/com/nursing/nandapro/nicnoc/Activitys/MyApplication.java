package com.nursing.nandapro.nicnoc.Activitys;

import android.app.Application;

import androidx.annotation.NonNull;

import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.nursing.nandapro.nicnoc.utils.Prefs;

/** The Application class that manages AppOpenManager. */
public class MyApplication extends Application {
    Prefs prefs;

    private static AppOpenManager appOpenManager;
    @Override
    public void onCreate() {

        super.onCreate();
        prefs = new Prefs(getApplicationContext());
        if (prefs.getPremium()==0){
        if (prefs.getRemoveAd()==0){
        MobileAds.initialize(
                this,
                new OnInitializationCompleteListener() {
                    @Override
                    public void onInitializationComplete(@NonNull InitializationStatus initializationStatus) {}
                });
        appOpenManager = new AppOpenManager(this);
    }
        }
    }
}