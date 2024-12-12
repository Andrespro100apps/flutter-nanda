package com.nursing.nandapro.nicnoc.Fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.billingclient.api.BillingClient;
import com.android.billingclient.api.BillingClientStateListener;
import com.android.billingclient.api.BillingFlowParams;
import com.android.billingclient.api.BillingResult;
import com.android.billingclient.api.Purchase;
import com.android.billingclient.api.PurchasesUpdatedListener;
import com.android.billingclient.api.SkuDetails;
import com.android.billingclient.api.SkuDetailsParams;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.OnUserEarnedRewardListener;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;
import com.nursing.nandapro.nicnoc.Activitys.MainActivity;
import com.nursing.nandapro.nicnoc.R;
import com.nursing.nandapro.nicnoc.utils.Prefs;

import java.util.ArrayList;
import java.util.List;

public class RewardedFragment extends Fragment {

    private RewardedAd rewardedAd;
    private  String TAG="Tag";

    Button btnRewarded;
    Button btnBuy;
    Button btnBack;
    Button btnBuyAll;

    ImageView imgLibro;
    FrameLayout contenedorFragmento;
    BillingClient billingClient;
    String _clave;

    public  RewardedFragment(String clave){
        _clave = clave;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rewarded, container, false);
        btnRewarded = view.findViewById(R.id.button1);
        btnBuy = view.findViewById(R.id.button2);
        btnBack = view.findViewById(R.id.button3);
        btnBuyAll = view.findViewById(R.id.btnUnlockAll);
        imgLibro =view.findViewById(R.id.imgLibro);

        contenedorFragmento = getActivity().findViewById(R.id.contenedor_fragmento);

        contenedorFragmento.setVisibility(View.VISIBLE  );

        loadRewardedAd();
        btnRewarded.setOnClickListener(v -> showRewardedAd());
        btnBuy.setOnClickListener(v -> connectGooglePlayBilling(_clave));
        btnBack.setOnClickListener(v -> goBackToPreviousActivity());
        btnBuyAll.setOnClickListener(v -> {

            connectGooglePlayBilling("RemoveAd");
        });

        billingClient = BillingClient.newBuilder(getContext())
                .setListener(new PurchasesUpdatedListener() {
                    @Override
                    public void onPurchasesUpdated(@NonNull BillingResult billingResult, @Nullable List<Purchase> purchases) {
                        if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK && purchases != null) {
                            for (Purchase purchase : purchases) {
                                // Verifica la compra aquí
                                if (purchase.getSkus().contains("noads") && purchase.getPurchaseState() == Purchase.PurchaseState.PURCHASED) {
                                    Intent intent = new Intent(requireActivity(), MainActivity.class);
                                    startActivity(intent);

                                }
                            }
                        }

                    }
                })
                .enablePendingPurchases()
                .build();

        CargarImagen();
        return view;
    }

    private void CargarImagen() {
        switch (getContext().getClass().getSimpleName()){ //Aqui añadirias el resto bro  listo
            //Listo bro te dejo, saludos jaja espera
            //que paso ?
            //aqui es yeah
            case  "DescripcionDiagnosticoActivity":
                imgLibro.setImageResource(R.drawable.libronanda);
                break;
            case  "Libro2descripcion":
                imgLibro.setImageResource(R.drawable.noc2020);
                break;
            case  "Libro3descripcion":
                imgLibro.setImageResource(R.drawable.nic2020);
                break;
            case  "Libro4descripcion":
                imgLibro.setImageResource(R.drawable.inte);
                break;
        }
    }

    private void loadRewardedAd() {
        String adUnitId= getResources().getString(R.string.adUnitIdRewarded);
        RewardedAd.load(requireContext(),adUnitId, new AdRequest.Builder().build(), new RewardedAdLoadCallback() {
            @Override
            public void onAdLoaded(@NonNull RewardedAd ad) {
                rewardedAd = ad;
                Toast.makeText(requireContext(), "Rewarded Load", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {

            }
        });
    }

    void showRewardedAd() {

        if (rewardedAd != null) {
            Activity activityContext = requireActivity();
            rewardedAd.show(activityContext, new OnUserEarnedRewardListener() {
                @Override
                public void onUserEarnedReward(@NonNull RewardItem rewardItem) {

                    contenedorFragmento.setClickable(false);
                    contenedorFragmento.setFocusable(false);
                    contenedorFragmento.setVisibility(View.GONE);
                }
            });
        } else {
        }
    }

    void goBackToPreviousActivity() {
        if (getActivity() != null) {
            getActivity().finish();
        }
    }

    void connectGooglePlayBilling(String _clave) {

        billingClient.startConnection(new BillingClientStateListener() {
            @Override
            public void onBillingServiceDisconnected() {
                connectGooglePlayBilling(_clave);
            }

            @Override
            public void onBillingSetupFinished(@NonNull BillingResult billingResult1) {
                if (billingResult1.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                    List<String> skuList = new ArrayList<>();

                    skuList.add(_clave);
                    SkuDetailsParams.Builder params = SkuDetailsParams.newBuilder();
                    params.setSkusList(skuList).setType(BillingClient.SkuType.INAPP);

                    billingClient.querySkuDetailsAsync(params.build(),
                            (billingResult, skuDetailsList) -> {
                                // Process the result.
                                if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK && skuDetailsList != null) {

                                    Log.d("remove",skuDetailsList+"");

                                    for (SkuDetails skuDetails: skuDetailsList){
                                        if (skuDetails.getSku().equals(_clave)){

                                            BillingFlowParams billingFlowParams = BillingFlowParams.newBuilder()
                                                    .setSkuDetails(skuDetails)
                                                    .build();

                                            billingClient.launchBillingFlow(requireActivity(), billingFlowParams);
                                            Prefs p = new Prefs(getContext());
                                            switch (_clave){
                                                case "noadlinrod":
                                                    p.setLibroD(1);
                                                    break;
                                                case "noadlinroa":
                                                    p.setLibroA(1);
                                                    break;
                                                case "noadlinrob":
                                                    p.setLibroB(1);
                                                    break;
                                                case "noadlinroc":
                                                    p.setLibroC(1);
                                                    break;
                                                    //Asi sucesivamente para los otros libros
                                            }
                                        }
                                    }
                                }


                            });
                }

            }
        });

    }
}