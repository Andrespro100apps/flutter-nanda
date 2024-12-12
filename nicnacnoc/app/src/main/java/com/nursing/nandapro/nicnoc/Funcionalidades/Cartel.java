package com.nursing.nandapro.nicnoc.Funcionalidades;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.nursing.nandapro.nicnoc.Fragments.RewardedFragment;
import com.nursing.nandapro.nicnoc.R;

import java.util.Random;

public class Cartel {
    public static void GenerarCartel(FragmentManager fragmentManager, String clave){
        // Generar un número aleatorio entre 0 y 1
        double numeroAleatorio = new Random().nextDouble();
        // Comprobar si el número aleatorio es menor que 0.5 (50% de probabilidad)
        if(numeroAleatorio < 0.6){
        // Crea una instancia del fragmento
        RewardedFragment miFragmento = new RewardedFragment(clave);

        // Agrega el fragmento dinámicamente a la actividad
        fragmentManager.beginTransaction()
                .replace(R.id.contenedor_fragmento, miFragmento)
                .commit();
        }
    }
}
