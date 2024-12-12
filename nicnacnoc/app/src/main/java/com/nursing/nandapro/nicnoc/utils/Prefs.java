package com.nursing.nandapro.nicnoc.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.nursing.nandapro.nicnoc.R;

public class Prefs {

    private final Context context;
    private final SharedPreferences sharedPreferences;
    private final SharedPreferences.Editor editor;

    public Prefs(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }



    public void setInt(String key,int value) {
        editor.putInt(key, value);
        editor.apply();
    }

    public void setString(String key,String value) {
        editor.putString(key, value);
        editor.apply();
    }

    public void setPremium(int value) {
        editor.putInt("Premium", value);
        editor.apply();
    }


public void setRemoveAd(int value) {
        editor.putInt("RemoveAd", value);
        editor.apply();
    }


    public void setBoolean(String key, boolean value) {
        editor.putBoolean(key, value);
        editor.apply();
    }

    public void removeKey(String key){
        editor.remove(key);
        editor.apply();
    }

    public boolean getBoolean(String key, boolean def) {
        return sharedPreferences.getBoolean(key, def);
    }

    public int getInt(String key, int def) {
        return sharedPreferences.getInt(key, def);
    }


  public String getString(String key, String def) {
        return sharedPreferences.getString(key, def);
    }

    public int getPremium() {
        return sharedPreferences.getInt("Premium", 0);
    }

    public int getRemoveAd() {
        return sharedPreferences.getInt("RemoveAd", 0);
    }

    //Para el libro A
    public void setLibroA(int value) {
        editor.putInt("noadlinroa", value);
        editor.apply();
    }
    public void setLibroB(int value) {
        editor.putInt("noadlinrob", value);
        editor.apply();
    }
    public void setLibroC(int value) {
        editor.putInt("noadlinroc", value);
        editor.apply();
    }

    public void setLibroD(int value) {
        editor.putInt("noadlinrod", value);
        editor.apply();
    }
    public int getLibroA() {
        return sharedPreferences.getInt("noadlinroa", 0);
    }
    public int getLibroB() {
        return sharedPreferences.getInt("noadlinrob", 0);
    }

    public int getLibroC() {
        return sharedPreferences.getInt("noadlinroc", 0);
    }
    public int getLibroD() { return sharedPreferences.getInt("noadlinrod", 0);
    }


}
