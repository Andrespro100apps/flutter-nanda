package com.nursing.nandapro.nicnoc.Activitys;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.billingclient.api.AcknowledgePurchaseParams;
import com.android.billingclient.api.BillingClient;
import com.android.billingclient.api.BillingClientStateListener;
import com.android.billingclient.api.BillingFlowParams;
import com.android.billingclient.api.BillingResult;
import com.android.billingclient.api.Purchase;
import com.android.billingclient.api.PurchasesUpdatedListener;
import com.android.billingclient.api.SkuDetails;
import com.android.billingclient.api.SkuDetailsParams;
import com.android.billingclient.api.SkuDetailsResponseListener;
import com.github.clans.fab.FloatingActionMenu;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.nativead.NativeAd;
import com.google.android.gms.ads.nativead.NativeAdOptions;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.play.core.appupdate.AppUpdateInfo;
import com.google.android.play.core.appupdate.AppUpdateManager;
import com.google.android.play.core.appupdate.AppUpdateManagerFactory;
import com.google.android.play.core.install.InstallState;
import com.google.android.play.core.install.InstallStateUpdatedListener;
import com.google.android.play.core.install.model.AppUpdateType;
import com.google.android.play.core.install.model.InstallStatus;
import com.google.android.play.core.install.model.UpdateAvailability;
import com.google.android.play.core.review.ReviewInfo;
import com.google.android.play.core.review.ReviewManager;
import com.google.android.play.core.review.ReviewManagerFactory;
import com.google.android.play.core.tasks.OnSuccessListener;
import com.google.android.play.core.tasks.Task;
import com.nursing.nandapro.nicnoc.Calculators.Calculadoras;
import com.nursing.nandapro.nicnoc.Chatmain;
import com.nursing.nandapro.nicnoc.Cie10;
import com.nursing.nandapro.nicnoc.Favoritos;
import com.nursing.nandapro.nicnoc.Fragments.RewardedFragment;
import com.nursing.nandapro.nicnoc.FuncionesMenu.MenuFunctions;
import com.nursing.nandapro.nicnoc.Libro2;
import com.nursing.nandapro.nicnoc.Libro3;
import com.nursing.nandapro.nicnoc.Libro4;
import com.nursing.nandapro.nicnoc.NativeTemplateStyle;
import com.nursing.nandapro.nicnoc.R;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.material.navigation.NavigationView;
import com.nursing.nandapro.nicnoc.TemplateView;
import com.nursing.nandapro.nicnoc.Tutorial;
import com.nursing.nandapro.nicnoc.Walpapers;
import com.nursing.nandapro.nicnoc.utils.Prefs;

import java.util.ArrayList;
import java.util.List;



public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    FloatingActionMenu materialDesignFAM;
    com.github.clans.fab.FloatingActionButton floatingActionButton1, floatingActionButton2, floatingActionButton3, floatingActionButton4, floatingActionButton5, floatingActionButton7, floatingActionButton8;

    NativeAd mNativeAd;

 //   String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
  //  requestPermissions(permissions, WRITE_REQUEST_CODE);


    private static final String TAG = "Main Activity";

    public TextView nandatitulo,nandatitulo2,nandatitulo3,nandatitulo6;
    TextView dominiooo;
    private InterstitialAd interstitial;
   // private InterstitialAd mInterstitialAd;
 //  ProductDetailsAdapter adapter;
    private AppUpdateManager mAppUpdateManager;
    private static final int RC_APP_UPDATE = 659;

    private ReviewInfo reviewInfo;
    private ReviewManager manager;

   BillingClient billingClient;

    Prefs prefs;


    MediaPlayer click;
    MediaPlayer mysound;

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;

    @Override
    protected void onStart() {
       mysound.start();
        //ComprobarIdiomaTitulos();

        super.onStart();

    }

    @Override
    protected void onDestroy() {
       // namedb.close();
        super.onDestroy();
    }


    private String sDescription,sAceptar;
    public static String namedb = "db_esp.db";
    public static String namedb2 = "db_2esp.db";
    public static String namedb3 = "db_3esp.db";
    public static String namedb4 = "db_cie10.db";
    public static String namedb5 = "db_4esp.db";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // setContentView(R.layout.list_element);  9
        setTitle("NANDA, NOC, NIC");

        //ayuda con esta parte
       // final TextView dominiooo = (TextView) findViewById(R.id.dominiooooo);
        //dominiooo = TextView.findViewById(R.id.dominiooooo);
        //  dominiooo.setTextColor(this.getResources().getColor(R.color.black));
        // dominiooo.setBackground(this.getResources().getColor(R.color.black));
        // dominiooo.setTextColor(Integer.parseInt("#ffff8888"));

     //   lanzarcolor();
          //  if(getFileStreamPath(idioma == itte wählen Sie hier Ihre Sprache))
                // ejecucion else ( )

        mysound = MediaPlayer.create(this, R.raw.puerta);
        click = MediaPlayer.create(this, R.raw.click);
        activateReviewInfo();
        AppUpdateManager mAppUpdateManager = AppUpdateManagerFactory.create(this);
        mAppUpdateManager.getAppUpdateInfo().addOnSuccessListener(new OnSuccessListener<AppUpdateInfo>()
        {
            @Override
            public void onSuccess(AppUpdateInfo result)
            {
                if(result.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE
                        && result.isUpdateTypeAllowed(AppUpdateType.FLEXIBLE))
                {
                    try
                    {
                        mAppUpdateManager.startUpdateFlowForResult(result, AppUpdateType.FLEXIBLE, MainActivity.this
                                ,RC_APP_UPDATE);

                    } catch (IntentSender.SendIntentException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        });
        mAppUpdateManager.registerListener(installStateUpdatedListener);
/*
        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            System.out.println("Fetching FCM registration token failed");
                            return;
                        }

                        // Get new FCM registration token
                        String token = task.getResult();

                        // Log and toast
                        System.out.println(token);
                        Toast.makeText(MainActivity.this, "Your device registration token is" + token
                                , Toast.LENGTH_SHORT).show();

                        etToken.setText(token);
                    }
                });


*/

    prefs = new Prefs(getApplicationContext());

        //if (prefs.getPremium()==0){
        if (prefs.getRemoveAd()==0){
            if (prefs.getLibroD()==0){
                if (prefs.getLibroA()==0){

                    if (prefs.getLibroB()==0){
                        if (prefs.getLibroC()==0){

                // connectGooglePlayBilling();
            //initializing admob and loading the banner ad.
            //sobre la edad
          //  lanzarwifi();
          //  lanzarwifi();
            Bundle extras = new Bundle();
            extras.putString("max_ad_content_rating", "MA");

            AdRequest request = new AdRequest.Builder()
                    .addNetworkExtrasBundle(AdMobAdapter.class, extras)
                    .build();

            //---> initializing Google Ad SDK
            MobileAds.initialize(this, new OnInitializationCompleteListener() {
                @Override
                public void onInitializationComplete(InitializationStatus initializationStatus) {
                    Log.d(TAG, "Google SDK Initialized");

                    loadNativeAd();
                }
            });

            // Buscar AdView como recurso y cargar una solicitud.
            AdView adView = (AdView)this.findViewById(R.id.adView);
            AdRequest adRequest = new AdRequest.Builder().build();
            adView.loadAd(adRequest);
            adView.setVisibility(View.VISIBLE);

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

            MobileAds.initialize(this);
            AdLoader adLoader = new AdLoader.Builder(this, getResources().getString(R.string.nativeid))
                    .forNativeAd(new NativeAd.OnNativeAdLoadedListener() {
                        @Override
                        public void onNativeAdLoaded(NativeAd nativeAd) {
                         //   View dialogView = getLayoutInflater().inflate(R.layout.activity_main, null);

                          //  TemplateView template = dialogView.findViewById(R.id.my_template);

                           // template.setVisibility(View.GONE);

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



        }   }} }}

        //Initialize a BillingClient with PurchasesUpdatedListener onCreate method

        billingClient = BillingClient.newBuilder(getApplicationContext())
                .setListener(new PurchasesUpdatedListener() {
                    @Override
                    public void onPurchasesUpdated(@NonNull BillingResult billingResult, @Nullable List<Purchase> list) {
                        if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK && list != null) {
                            for (Purchase purchase : list) {
                                verifyPayment(purchase);
                            }
                        }

                    }
                })
                .enablePendingPurchases()
                .build();

        // call connectGooglePlayBilling()
     //   connectGooglePlayBilling();
      //  setUpBillinClient();
       // connectGooglePlayBilling();
/*
        billingClient = BillingClient.newBuilder(this)
                .enablePendingPurchases()
                .setListener(
                        new PurchasesUpdatedListener() {
                            @Override
                            public void onPurchasesUpdated(@NonNull BillingResult billingResult, @Nullable List<Purchase> list) {
                                if(billingResult.getResponseCode()== BillingClient.BillingResponseCode.OK && list !=null) {
                                    for (Purchase purchase: list){
                                        verifySubPurchase(purchase);
                                    }
                                }
                            }
                        }
                ).build();

        //start the connection after initializing the billing client
        establishConnection();
*/

        nandatitulo = findViewById(R.id.nandatitulo);
        nandatitulo2 = findViewById(R.id.nandatitulo2);
        nandatitulo3 = findViewById(R.id.nandatitulo3);
        nandatitulo6 = findViewById(R.id.nandatitulo6);
        setNavigationViewListner();

        mDrawerLayout =  findViewById(R.id.drawer);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ComprobarIdiomaTitulos();


        materialDesignFAM = (FloatingActionMenu) findViewById(R.id.social_floating_menu);
        floatingActionButton1 = (com.github.clans.fab.FloatingActionButton) findViewById(R.id.share);
        floatingActionButton2 = (com.github.clans.fab.FloatingActionButton) findViewById(R.id.floating_home);
        floatingActionButton3 = (com.github.clans.fab.FloatingActionButton) findViewById(R.id.floating_info);
        floatingActionButton4 = (com.github.clans.fab.FloatingActionButton) findViewById(R.id.floating_rate);
        floatingActionButton5 = (com.github.clans.fab.FloatingActionButton) findViewById(R.id.floating_face);
      //  floatingActionButton6 = (com.github.clans.fab.FloatingActionButton) findViewById(R.id.floating_youtu);
        floatingActionButton7 = (com.github.clans.fab.FloatingActionButton) findViewById(R.id.floating_inste);
        floatingActionButton8 = (com.github.clans.fab.FloatingActionButton) findViewById(R.id.floating_tiktok);

        floatingActionButton1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
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
        });floatingActionButton2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Home button
                final String appPackageName = getPackageName(); // getPackageName() from Context or Activity object
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                } catch (android.content.ActivityNotFoundException anfe) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                }finally {

                }
            }
        });
        floatingActionButton3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Info button
                //Info button
                Intent twitterIntent = getOpenTwitterIntent(MainActivity.this);
                startActivity(twitterIntent);
            }
        });
        floatingActionButton4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                androidx.appcompat.app.AlertDialog.Builder alertDialog = new androidx.appcompat.app.AlertDialog.Builder(MainActivity.this);
                // Setting Dialog Message
                alertDialog.setTitle("Valora");
                alertDialog.setMessage(" \n" +
                        "Your comment is very important to me, please VALUE or QUALIFY ..." +
                        "\n" +
                        "Or leave a comment to improve this application " +
                        "You'll only take a few minutes. Thanks for your help..."  +"\n" + "\n" +
                        "Tu comentario es muy importante para mi, por favor VALORA o CALIFICA... " +
                        "O deja un comentario para poder mejorar esta alicación...Solo te va a tomar unos pocos minutos. Gracias por tu ayuda!");
                alertDialog.setCancelable(true);
                alertDialog.setPositiveButton("RATE", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        final String appPackageName = getPackageName(); // getPackageName() from Context or Activity object
                        try {
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=" + appPackageName)));

                        } catch (android.content.ActivityNotFoundException anfe) {
                        }finally {

                        }
                    }
                });
                alertDialog.show();
            }
        });
        floatingActionButton5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //TODO something when floating action menu first item clicked
                Intent facebookIntent = getOpenFacebookIntent(MainActivity.this);
                startActivity(facebookIntent);

            }
        });
/*
        floatingActionButton6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //TODO something when floating action menu third item clicked
                Intent youtubeIntent = getOpenYouTubeIntent(MainActivity.this);
                startActivity(youtubeIntent);
            }
        });

 */
        floatingActionButton7.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //TODO something when floating action menu second item clicked
                Intent instagramIntent = getOpenInstagramIntent(MainActivity.this);
                startActivity(instagramIntent);
            }
        });

        floatingActionButton8.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //TODO something when floating action menu first item clicked
                Intent tiktokIntent = getOpenTiktokIntent(MainActivity.this);
                startActivity(tiktokIntent);

            }
        });


       /// interstitial = new InterstitialAd(this);
      //  interstitial.setAdUnitId(getResources().getString(R.string.interaid));
      //  interstitial.loadAd(new AdRequest.Builder().build());



    }


    public void lanzarcolor(View view) {
        lanzarwifi();
     //   Intent i = new Intent(this, Calculadora.class );
     //   startActivity(i);
    }

    public void lanzarcolor33(View view) {
        //9 Toast.makeText(this, "Incompleto :c", Toast.LENGTH_SHORT).show();
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.black)));
        //  getWindow().setNavigationBarColor(getResources().getColor(R.color.black));
        // dominiooo.setTextColor(this.getResources().getColor(R.color.black));

        /*
        if (Build.VERSION.SDK_INT >= 21){
            getWindow().setNavigationBarColor(getResources().getColor(R.color.black));
        }
        */
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            //   window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            //   window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.black));
            //dominiooo = TextView.findViewById(R.id.dominiooooo);
           //  dominiooo.setTextColor(this.getResources().getColor(R.color.black));
            // dominiooo.setBackground(this.getResources().getColor(R.color.black));
            // dominiooo.setTextColor(Integer.parseInt("#ffff8888"));
        }
    }


 /*   @SuppressLint("SetTextI18n")


    void establishConnection() {

        billingClient.startConnection(new BillingClientStateListener() {
            @Override
            public void onBillingSetupFinished(@NonNull BillingResult billingResult) {
                if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                    // The BillingClient is ready. You can query purchases here.
                    showProducts();
                }
            }

            @Override
            public void onBillingServiceDisconnected() {
                // Try to restart the connection on the next request to
                // Google Play by calling the startConnection() method.
                establishConnection();
            }
        });
    }

    void showProducts() {

        ImmutableList<QueryProductDetailsParams.Product> productList = ImmutableList.of(
                //Product 1
                QueryProductDetailsParams.Product.newBuilder()
                        .setProductId("one_week")
                        .setProductType(BillingClient.ProductType.SUBS)
                        .build() ,

                //Product 2
                QueryProductDetailsParams.Product.newBuilder()
                        .setProductId("one_month")
                        .setProductType(BillingClient.ProductType.SUBS)
                        .build(),

                //Product 3
                QueryProductDetailsParams.Product.newBuilder()
                        .setProductId("one_year")
                        .setProductType(BillingClient.ProductType.SUBS)
                        .build()
        );

        QueryProductDetailsParams params = QueryProductDetailsParams.newBuilder()
                .setProductList(productList)
                .build();

        billingClient.queryProductDetailsAsync(
                params,
                (billingResult, prodDetailsList) -> {
                    // Process the result
                    productDetailsList.clear();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Log.d(TAG,"posted delayed");

                            loadProducts.setVisibility(View.INVISIBLE);

                            productDetailsList.addAll(prodDetailsList);
                            Log.d(TAG,productDetailsList.size()+" number of products");

                            adapter = new ProductDetailsAdapter(getApplicationContext(), productDetailsList, Subscriptions.this);
                            recyclerView.setHasFixedSize(true);
                            recyclerView.setLayoutManager(new LinearLayoutManager(Subscriptions.this, LinearLayoutManager.VERTICAL, false));
                            recyclerView.setAdapter(adapter);
                        }
                    },2000);

                }
        );

    }

    void launchPurchaseFlow(ProductDetails productDetails) {
        assert productDetails.getSubscriptionOfferDetails() != null;
        ImmutableList<BillingFlowParams.ProductDetailsParams> productDetailsParamsList =
                ImmutableList.of(
                        BillingFlowParams.ProductDetailsParams.newBuilder()
                                .setProductDetails(productDetails)
                                .setOfferToken(productDetails.getSubscriptionOfferDetails().get(0).getOfferToken())
                                .build()
                );
        BillingFlowParams billingFlowParams = BillingFlowParams.newBuilder()
                .setProductDetailsParamsList(productDetailsParamsList)
                .build();

        BillingResult billingResult = billingClient.launchBillingFlow(activity, billingFlowParams);
    }

    void verifySubPurchase(Purchase purchases) {

        AcknowledgePurchaseParams acknowledgePurchaseParams = AcknowledgePurchaseParams
                .newBuilder()
                .setPurchaseToken(purchases.getPurchaseToken())
                .build();

        billingClient.acknowledgePurchase(acknowledgePurchaseParams, billingResult -> {
            if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                //user prefs to set premium
                Toast.makeText(Subscriptions.this, "Subscription activated, Enjoy!", Toast.LENGTH_SHORT).show();
                //Setting premium to 1
                // 1 - premium
                // 0 - no premium
                prefs.setPremium(1);
                startActivity(new Intent(this,MainActivity.class));
                finish();            }
        });

        Log.d(TAG, "Purchase Token: " + purchases.getPurchaseToken());
        Log.d(TAG, "Purchase Time: " + purchases.getPurchaseTime());
        Log.d(TAG, "Purchase OrderID: " + purchases.getOrderId());
    }

    protected void onResume() {
        super.onResume();
        billingClient.queryPurchasesAsync(
                QueryPurchasesParams.newBuilder().setProductType(BillingClient.ProductType.SUBS).build(),
                (billingResult, list) -> {
                    if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                        for (Purchase purchase : list) {
                            if (purchase.getPurchaseState() == Purchase.PurchaseState.PURCHASED && !purchase.isAcknowledged()) {
                                verifySubPurchase(purchase);
                            }
                        }
                    }
                }
        );

    }

*/

    boolean isConnected(){

        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if(networkInfo!=null){
            if(networkInfo.isConnected())

                return true;
            else
                return false;
        }else

            return false;

    }


    void connectGooglePlayBilling() {

        billingClient.startConnection(new BillingClientStateListener() {
            @Override
            public void onBillingServiceDisconnected() {
                connectGooglePlayBilling();
            }

            @Override
            public void onBillingSetupFinished(@NonNull BillingResult billingResult) {
                if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                    Log.d(TAG, "Connected " + 0);
                    getProducts();
                }

            }
        });

    }


    void getProducts() {

        List<String> skuList = new ArrayList<>();

        //replace these with your product IDs from google play console
        skuList.add("noads");
        SkuDetailsParams.Builder params = SkuDetailsParams.newBuilder();
        params.setSkusList(skuList).setType(BillingClient.SkuType.INAPP);

        billingClient.querySkuDetailsAsync(params.build(),
                new SkuDetailsResponseListener() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onSkuDetailsResponse(BillingResult billingResult,
                                                     List<SkuDetails> skuDetailsList) {
                        // Process the result.
                        if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK && skuDetailsList != null) {

                            Log.d("remove",skuDetailsList+"");

                            for (SkuDetails skuDetails: skuDetailsList){
                                if (skuDetails.getSku().equals("noads")){
                                    //  btn_remove_ad.setVisibility(View.VISIBLE);
                                    // btn_remove_ad.setOnClickListener(view -> {
                                    launchPurchaseFlow(skuDetails);
                                }
                            }
                        }


                    }

                });

    }


    void launchPurchaseFlow(SkuDetails skuDetails) {

        BillingFlowParams billingFlowParams = BillingFlowParams.newBuilder()
                .setSkuDetails(skuDetails)
                .build();

        billingClient.launchBillingFlow(MainActivity.this, billingFlowParams);
    }



    void verifyPayment(Purchase purchase) {


        if (purchase.getPurchaseState() == Purchase.PurchaseState.PURCHASED) {
            if (!purchase.isAcknowledged()) {
                AcknowledgePurchaseParams acknowledgePurchaseParams =
                        AcknowledgePurchaseParams.newBuilder()
                                .setPurchaseToken(purchase.getPurchaseToken())
                                .build();
                billingClient.acknowledgePurchase(acknowledgePurchaseParams, billingResult -> {

                    if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                        // 1 - True
                        // 0 - False
                        prefs.setRemoveAd(1);
                    }

                });
            }
        }


    }


    protected void onResume() {
        super.onResume();
       // ComprobarIdiomaTitulos();
        Toast.makeText(MainActivity.this, " Ad- "+prefs.getRemoveAd(), Toast.LENGTH_SHORT).show();

        //   clicks.setText("You have "+prefs.getInt("clicks",0)+ " click(s)");
        /*
        billingClient.queryPurchasesAsync(
                BillingClient.SkuType.INAPP,
                (billingResult, list) -> {
                    if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                        for (Purchase purchase : list) {
                            if (purchase.getPurchaseState() == Purchase.PurchaseState.PURCHASED && !purchase.isAcknowledged()) {
                                verifyPayment(purchase);
                            }
                        }
                    }
                }
        );
        */
        MenuFunctions menuFunctions= new MenuFunctions();
        menuFunctions.FavoritosActualizarDataBase(MainActivity.this,MainActivity.namedb);
        menuFunctions.FavoritosActualizarDataBase(MainActivity.this,MainActivity.namedb2);
        menuFunctions.FavoritosActualizarDataBase(MainActivity.this,MainActivity.namedb3);
        menuFunctions.FavoritosActualizarDataBase(MainActivity.this,MainActivity.namedb4);
        menuFunctions.FavoritosActualizarDataBase(MainActivity.this,MainActivity.namedb5);

    }



    public void lanzartutorial(View view) {
        Intent i = new Intent(this, Tutorial.class );
        startActivity(i);
        click.start();
        overridePendingTransition(R.anim.left_in, R.anim.left_out);

        if (interstitial != null) {
            interstitial.show(MainActivity.this);
        } else {
            Log.d("TAG", "The interstitial ad wasn't ready yet.");
        }

    }

    public void lanzarcalculadoraweb(View view) {
        Intent i = new Intent(this, Calculadoras.class );
        startActivity(i);
        click.start();
        overridePendingTransition(R.anim.left_in, R.anim.left_out);

        if (interstitial != null) {
            interstitial.show(MainActivity.this);
        } else {
            Log.d("TAG", "The interstitial ad wasn't ready yet.");
        }

    }


    public void lanzarlistado(View view) {
        Intent i = new Intent(this, com.nursing.nandapro.nicnoc.Activitys.DominiosListActivity.class );
        startActivity(i);
        click.start();
        overridePendingTransition(R.anim.left_in, R.anim.left_out);

        if (interstitial != null) {
            interstitial.show(MainActivity.this);
        } else {
            Log.d("TAG", "The interstitial ad wasn't ready yet.");
        }

    }
    public void lanzarlibro2(View view) {
        Intent i = new Intent(this, Libro2.class );
        startActivity(i);
        overridePendingTransition(R.anim.left_in, R.anim.left_out);

        click.start();
      //  Animatoo.animateSwipeLeft(MainActivity.this);

        if (interstitial != null) {
            interstitial.show(MainActivity.this);
        } else {
            Log.d("TAG", "The interstitial ad wasn't ready yet.");
        }

    }
    public void noads(View view) {
       // Intent i = new Intent(this, Subscriptions.class );
      //  startActivity(i);        click.start();
        click.start();
        connectGooglePlayBilling();
    }
    public void noads2(View view) {
        Toast.makeText(this, "Proximamente", Toast.LENGTH_SHORT).show();

        click.start();

    }
    public void lanzarinterrelaciones(View view) {
       // Toast.makeText(this, "Proximamente", Toast.LENGTH_SHORT).show();

        Intent i = new Intent(this, Libro4.class );
        startActivity(i);
        click.start();
        overridePendingTransition(R.anim.left_in, R.anim.left_out);
        if (interstitial != null) {
            interstitial.show(MainActivity.this);
        } else {
            Log.d("TAG", "The interstitial ad wasn't ready yet.");
        }
    }

    public void lanzarlibro3(View view) {
       //9 Toast.makeText(this, "Incompleto :c", Toast.LENGTH_SHORT).show();

        Intent i = new Intent(this, Libro3.class );
        startActivity(i);
        click.start();
        overridePendingTransition(R.anim.left_in, R.anim.left_out);

        if (interstitial != null) {
            interstitial.show(MainActivity.this);
        } else {
            Log.d("TAG", "The interstitial ad wasn't ready yet.");
        }

    }

    public void lanzarchat(View view) {
        //9 Toast.makeText(this, "Incompleto :c", Toast.LENGTH_SHORT).show();

        Intent i = new Intent(this, Chatmain.class );
        startActivity(i);

    }

    public void lanzarcalculadora(View view) {
        //9 Toast.makeText(this, "Incompleto :c", Toast.LENGTH_SHORT).show();

        Intent i = new Intent(this, Calculadoras.class );
        startActivity(i);

    }

    public void lanzarsuscricion() {
        //9 Toast.makeText(this, "Incompleto :c", Toast.LENGTH_SHORT).show();
        //Intent i = new Intent(this, Subscriptions.class );
       // startActivity(i);
        connectGooglePlayBilling();
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
        Intent i = new Intent(this, Subscriptions.class );
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
                alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.show();



    }



    public void lanzarwalpapers(View view) {
        overridePendingTransition(R.anim.left_in, R.anim.left_out);

        //9 Toast.makeText(this, "Incompleto :c", Toast.LENGTH_SHORT).show();
        if (interstitial != null) {
            interstitial.show(MainActivity.this);
        } else {
            Log.d("TAG", "The interstitial ad wasn't ready yet.");
        }
        Intent i = new Intent(this, Walpapers.class );
        startActivity(i);

    }

    public void ic10(View view) {
        overridePendingTransition(R.anim.left_in, R.anim.left_out);

        if (interstitial != null) {
            interstitial.show(MainActivity.this);
        } else {
            Log.d("TAG", "The interstitial ad wasn't ready yet.");
        }
        Intent i = new Intent(this, Cie10.class );
        startActivity(i);

    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onBackPressed() {
        final androidx.appcompat.app.AlertDialog.Builder exitBuilderDialog = new androidx.appcompat.app.AlertDialog.Builder(this);

        View dialogView = getLayoutInflater().inflate(R.layout.exit_layout, null);

        Button yesBtm = dialogView.findViewById(R.id.id_exit_pos_btm);
        Button noBtm= dialogView.findViewById(R.id.id_exit_neg_btm);
        TextView txtTitle = dialogView.findViewById(R.id.id_exit_title);
        TextView txtMessage = dialogView.findViewById(R.id.id_exit_message);
        TemplateView template = dialogView.findViewById(R.id.my_template);

        template.setVisibility(View.GONE);

        if(mNativeAd!=null) {
            NativeTemplateStyle styles = new
                    NativeTemplateStyle.Builder().build();


            template.setStyles(styles);
            template.setVisibility(View.VISIBLE);
            template.setNativeAd(mNativeAd);
        }



        txtTitle.setText("Exit");
        txtMessage.setText("Do you want to exit?");

        exitBuilderDialog.setView(dialogView);
        final androidx.appcompat.app.AlertDialog alertDialog = exitBuilderDialog.create();
        alertDialog.setCanceledOnTouchOutside(false);

        alertDialog.show();

        yesBtm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentExit = new Intent(Intent.ACTION_MAIN);
                intentExit.addCategory(Intent.CATEGORY_HOME);
                intentExit.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intentExit);

                finish();
                System.exit(0);
            }
        });

        noBtm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mNativeAd!=null){
                    mNativeAd.destroy();
                }
                loadNativeAd();
                alertDialog.cancel();
            }
        });

    }

    private void loadNativeAd(){
        AdLoader adLoader = new AdLoader.Builder(MainActivity.this, getResources().getString(R.string.nativeid))

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

    public void rate(View view) {
        startReviewFlow();
        click.start();
        if (interstitial != null) {
            interstitial.show(MainActivity.this);
        } else {
            Log.d("TAG", "The interstitial ad wasn't ready yet.");
        }

    }

    void activateReviewInfo()
    {
        manager = ReviewManagerFactory.create(this);
        Task<ReviewInfo> managerInfoTask = manager.requestReviewFlow();
        managerInfoTask.addOnCompleteListener((task)->
        {
            if(task.isSuccessful())
            {
                reviewInfo = task.getResult();
            }
            else
            {
                Toast.makeText(this, "Review failed to start", Toast.LENGTH_SHORT).show();
            }
        });
    }

    void startReviewFlow()
    {
        if(reviewInfo !=null)
        {
            Task<Void> flow= manager.launchReviewFlow(this,reviewInfo);
            flow.addOnCompleteListener(task ->
            {
                Toast.makeText(this, "Rating is completed", Toast.LENGTH_SHORT).show();
                calificar();
            });
        }
    }

//actualisar
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
    /* we can check without requestCode == RC_APP_UPDATE because
    we known exactly there is only requestCode from  startUpdateFlowForResult() */
        if(requestCode == RC_APP_UPDATE && resultCode != RESULT_OK)
        {
            Toast.makeText(this, "Cancel", Toast.LENGTH_SHORT).show();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private InstallStateUpdatedListener installStateUpdatedListener =new InstallStateUpdatedListener()
    {
        @Override
        public void onStateUpdate(InstallState state)
        {
            if(state.installStatus() == InstallStatus.DOWNLOADED)
            {
                showCompletedUpdate();
            }
        }
    };

    private void showCompletedUpdate()
    {
        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content),"New app is ready!",
                Snackbar.LENGTH_INDEFINITE);
        snackbar.setAction("Install", new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                mAppUpdateManager.completeUpdate();
            }
        });
        snackbar.show();

    }

    @Override
    protected void onStop()
    {
        if(mAppUpdateManager!=null) mAppUpdateManager.unregisterListener(installStateUpdatedListener);
        super.onStop();
    }


    public void SeleccionarIdioma(View view){

        final String[] language = {"English language","language Spanish","Langue Française","lingua Italiana","Deutsche Sprache","český Jazyk","Eesti Keel","Bahasa Indonesia","Idioma Português","Svenska Språket","Nederlandse taal","日本語","中文","اللغة العربية","Türk","Hindi हिन्दी","Urdu اردو زبان","Język polski","한국어"};

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Select your language")
                .setSingleChoiceItems(language, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                            Button btn_idioma = findViewById(R.id.language);
                            btn_idioma.setText(language[which]);

                            switch (language[which]){
                                case "English language":
                                    namedb = "db_ing.db";
                                    namedb2 = "db_2ing.db";
                                    namedb3 = "db_3ing.db";
                                    namedb5 = "db_4ing.db";

                                    break;
                                case "language Spanish":
                                    namedb = "db_esp.db";
                                    namedb2 = "db_2esp.db";
                                    namedb3 = "db_3esp.db";
                                    namedb4 = "db_cie10.db";
                                    namedb5 = "db_4esp.db";
                                    break;

                                case "Langue Française":
                                    namedb = "db_fr.db";
                                    namedb2 = "db_2fr.db";
                                    namedb3 = "db_3fr.db";

                                    namedb5 = "db_4fr.db";

                                    break;
                                case "lingua Italiana":
                                    namedb = "db_it.db";
                                    namedb2 = "db_2it.db";
                                    namedb3 = "db_3it.db";
                                    namedb5 = "db_4it.db";
                                    Log.d(TAG, "onClick: Lingua Italinana");
                                    break;
                                case "Deutsche Sprache":
                                    namedb = "db_de.db";
                                    namedb2 = "db_2de.db";
                                    namedb3 = "db_3de.db";
                                    namedb5 = "db_4de.db";
                                    break;
                                case "český Jazyk":
                                    namedb = "db_cs.db";
                                    namedb2 = "db_2cs.db";
                                    break;
                                case "Eesti Keel":
                                    namedb = "db_et.db";
                                    namedb2 = "db_2et.db";
                                    break;
                                case "Bahasa Indonesia":
                                    namedb = "db_id.db";
                                    namedb2 = "db_2id.db";
                                    namedb3 = "db_3id.db";
                                    namedb5 = "db_4id.db";
                                    break;
                                case "Idioma Português":
                                    namedb = "db_pt.db";
                                    namedb2 = "db_2pt.db";
                                    namedb3 = "db_3pt.db";

                                    namedb5 = "db_4pt.db";
                                    break;
                                case "Svenska Språket":
                                    namedb = "db_sv.db";
                                    namedb2 = "db_2sv.db";
                                    break;
                                case "Nederlandse taal":
                                    namedb = "db_nl.db";
                                    namedb2 = "db_2nl.db";
                                    namedb3 = "db_3nl.db";

                                    namedb5 = "db_4nl.db";
                                    break;
                                case "日本語":
                                    namedb = "db_ja.db";
                                    namedb2 = "db_2ja.db";
                                    break;
                                case "中文":
                                    namedb = "db_zh.db";
                                    namedb2 = "db_2zh.db";
                                    break;
                                case "اللغة العربية":
                                    namedb = "db_ar.db";
                                    namedb2 = "db_2ar.db";
                                    namedb3 = "db_3ar.db";

                                    break;
                                case "Türk":
                                    namedb = "db_tr.db";
                                    namedb2 = "db_2tr.db";
                                    namedb3 = "db_3tr.db";

                                    break;
                                case "Hindi हिन्दी":
                                    namedb = "db_hi.db";
                                    namedb2 = "db_2hi.db";
                                    namedb3 = "db_3hi.db";

                                    break;
                                case "Urdu اردو زبان":
                                    namedb = "db_ur.db";
                                    namedb2 = "db_2ur.db";
                                    break;
                                case "Język polski":
                                    namedb = "db_pl.db";
                                    namedb2 = "db_2pl.db";
                                    namedb3 = "db_3pl.db";

                                    break;
                                case "한국어":
                                    namedb = "db_ko.db";
                                    namedb2 = "db_2ko.db";
                                    namedb3 = "db_3ko.db";

                                    break;


                                    /*
                                case "English language2":
                                    namedb3 = "db_3esp.db";

                                    break;
                                    */
                            }

/*
                        switch (language[which]){
                            case "English language":
                                namedb2 = "db_2ing.db";
                             //   namedb3 = "db_3esp.db";
                                break;
                            case "Idioma Español":
                              //  namedb = "db_esp.db";
                             //   namedb = "db_esp.db";
                                namedb3 = "db_3esp.db";
                                namedb2 = "db_2esp.db";
                             //  namedb3 = "db_3esp.db";
                                break;

                            case "Langue Française":
                                namedb2 = "db_2fr.db";
                                break;
                            case "lingua Italiana":
                                namedb2 = "db_2it.db";
                                break;
                            case "Deutsche Sprache":
                                namedb2 = "db_2de.db";
                                break;
                            case "český Jazyk":
                                namedb2 = "db_2cs.db";
                                break;
                            case "Eesti Keel":
                                namedb2 = "db_2et.db";
                                break;
                            case "Bahasa Indonesia":
                                namedb2 = "db_2id.db";
                                break;
                            case "Idioma Português":
                                namedb2 = "db_2pt.db";
                                namedb3 = "db_3pt.db";

                                break;
                            case "Svenska Språket":
                                namedb2 = "db_2sv.db";
                                break;
                            case "Nederlandse taal":
                                namedb2 = "db_2nl.db";
                                break;
                            case "日本語":
                                namedb2 = "db_2ja.db";
                                break;
                            case "中文":
                                namedb2 = "db_2zh.db";
                                break;
                            case "اللغة العربية":
                                namedb2 = "db_2ar.db";
                                break;
                            case "Türk":
                                namedb2 = "db_2tr.db";
                                break;
                            case "Hindi हिन्दी":
                                namedb2 = "db_2hi.db";
                                break;
                            case "Urdu اردو زبان":
                                namedb2 = "db_2ur.db";
                                break;
                            case "Język polski":
                                namedb2 = "db_2pl.db";
                                break;
/*
                            case "English language2":
                                namedb3 = "db_3esp.db";
                                break;
 }
                                */


                                                /*
                        switch (language[which]){
                            case "English language":
                                namedb3 = "db_4esp.db";
                                break;

                            case "English language2":
                                namedb3 = "db_3esp.db";
                                break;
                    }
                    */

                    }
                })
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ComprobarIdiomaTitulos();
                        MenuFunctions menuFunctions= new MenuFunctions();
                        menuFunctions.FavoritosActualizarDataBase(MainActivity.this,MainActivity.namedb);
                        menuFunctions.FavoritosActualizarDataBase(MainActivity.this,MainActivity.namedb2);
                        menuFunctions.FavoritosActualizarDataBase(MainActivity.this,MainActivity.namedb3);
                        menuFunctions.FavoritosActualizarDataBase(MainActivity.this,MainActivity.namedb4);
                        menuFunctions.FavoritosActualizarDataBase(MainActivity.this,MainActivity.namedb5);

                        dialog.dismiss();
                    }
                })
                .setCancelable(false);
        builder.create().show();
    }
    private void ComprobarIdiomaTitulos() {
        // Opcional para modificar los idiomas del main activity
        switch (MainActivity.namedb){
            case "db_esp.db":
                sAceptar = "Aceptar";
                sDescription = "Hola me pueden seguir y mi nombre es @ANDRESPRO100 me tomo muchisimo tiempo crear esta app incluso les ayudara en los estudio o universidad, para añadir todas las traduciones posibles y ayudar al mayor numero de personas posibles espero me apoyen haciendo compras he informarme de algun error tratare de arreglarlo lo antes posble.";
                nandatitulo.setText(" 100% diagnosticos de enfermeria" +
                        "100% ESPAÑOL");
                nandatitulo3.setText(" NIC 100% disponible" +
                        "                      (7ª ed.) ESPAÑOL");
                nandatitulo6.setText("         interrelaciones ");

                break;
            case "db_ing.db":
                sDescription = "Hello, you can follow me and my name is @ANDRESPRO100. It took me a long time to create this app. I even dropped out of university for love and believe in this project and add all possible translations to help as many people as possible. I hope you support me by making purchases. I'll try to fix it as soon as possible.";
                sAceptar = "Acept";
                nandatitulo.setText(" nursing diagnoses" +
                        "                        100% ENGLISH");
                nandatitulo3.setText(" nursing diagnoses" +
                        "                       (7ª ed.) ENGLISH");
                nandatitulo6.setText("          interrelationships ");
                break;
            case "db_fr.db":
                sDescription = "Bonjour, vous pouvez me suivre et je m'appelle @ANDRESPRO100. Il m'a fallu beaucoup de temps pour créer cette application et ajouter toutes les traductions possibles pour aider le plus de monde possible. J'espère que vous me soutiendrez en faisant des achats. J'ai signalé des erreurs Je vais essayer de le réparer dès que possible.";
                sAceptar = "Acept";
                nandatitulo.setText(" diagnostics infirmiers" +
                        "                            Français");
                nandatitulo3.setText(" NIC oui disponible" +
                        "                        Français");
                nandatitulo6.setText("           interrelations ");

                break;
            case "db_it.db":
                sDescription = "Bonjour, vous pouvez me suivre et je m'appelle @ANDRESPRO100. Il m'a fallu beaucoup de temps pour créer cette application et ajouter toutes les traductions possibles pour aider le plus de monde possible. J'espère que vous me soutiendrez en faisant des achats. J'ai signalé des erreurs Je vais essayer de le réparer dès que possible.";
                sAceptar = "Acept";
                nandatitulo.setText(" diagnostics infirmiers" +
                        "                            Italiano");
                nandatitulo3.setText(" NIC si disponibile" +
                        "                          Italiano");
                nandatitulo6.setText("           interrelazioni ");

                break;
            case "db_de.db":
                sDescription = "Hallo, du kannst mir folgen und mein Name ist @ANDRESPRO100. Ich habe lange gebraucht, um diese App zu erstellen. Ich habe sogar die Universität aus Liebe abgebrochen und glaube an dieses Projekt und füge alle möglichen Übersetzungen hinzu, um so vielen Menschen wie möglich zu helfen. Ich hoffe, Sie unterstützen mich mit Ihren Einkäufen. Ich werde versuchen, das Problem so schnell wie möglich zu beheben.";
                sAceptar = "Acept";
                nandatitulo.setText(" diagnostics infirmiers" +
                        "                           Deutsch");
                nandatitulo3.setText(" NIC ja vorhanden" +
                        "                           Deutsch");
                nandatitulo6.setText("          Zusammenhänge ");

                break;
            case "db_cs.db":
                sDescription = "Hej, du kan följa mig och jag heter @ANDRESPRO100. Det tog mig lång tid att skapa den här appen. Jag hoppade till och med av universitetet för kärlek och tror på det här projektet och lägger till alla möjliga översättningar för att hjälpa så många människor som möjligt. Jag hoppas att du stöttar mig genom att göra köp. Jag ska försöka fixa det så snart som möjligt.";
                sAceptar = "Acept";
                nandatitulo.setText(" diagnostics infirmiers" +
                        "                            ANGLAIS");
                nandatitulo3.setText(" NIC není dostupný" +
                        "                            JIŽ BRZY");
                break;
            case "db_et.db":
                sDescription = "Tere, võite mind jälgida ja minu nimi on @ANDRESPRO100. Selle rakenduse loomine võttis mul kaua aega. Ma isegi lahkusin ülikoolist armastuse pärast ja usun sellesse projekti ja lisan kõik võimalikud tõlked, et aidata võimalikult paljusid inimesi. Loodan, et toetate mind ostude tegemisel. Püüan selle esimesel võimalusel parandada.";
                sAceptar = "Acept";
                nandatitulo.setText(" õendusdiagnoosid" +
                        "                            rootsi keel");
                nandatitulo3.setText(" NIC pole saadaval" +
                        "                            TULEKUL");
                break;
            case "db_id.db":
                sDescription = "Halo, Anda dapat mengikuti saya dan nama saya @ANDRESPRO100. Butuh waktu lama untuk membuat aplikasi ini. Saya bahkan keluar dari universitas karena cinta dan percaya pada proyek ini dan menambahkan semua kemungkinan terjemahan untuk membantu sebanyak mungkin orang. Saya harap Anda mendukung saya dengan melakukan pembelian. Saya akan mencoba memperbaikinya sesegera mungkin.";
                sAceptar = "Acept";
                nandatitulo.setText(" omvårdnadsdiagnoser" +
                        "                            BAHASA INDONESIA");
                nandatitulo3.setText(" NIC omvårdnadsdiagnoser" +
                        "                      (7ª ed.) BAHASA INDONESIA");
                nandatitulo6.setText("  hubungan timbal balik ");

                break;
            case "db_pt.db":
                sDescription = "Olá, você pode me seguir e meu nome é @ANDRESPRO100. Demorei muito para criar este aplicativo. Até larguei a universidade por amor e acredito neste projeto e adicionei todas as traduções possíveis para ajudar o maior número de pessoas possível. Espero que você me ajude fazendo compras. Tentarei corrigi-lo o mais rápido possível.";
                sAceptar = "Acept";
                nandatitulo.setText(" diagnósticos de enfermagem" +
                        "                          100% PORTUGUÊS");
                nandatitulo3.setText(" NIC 100 % diagnósticos de enfermagem" +
                        "                       (7ª ed.) PORTUGUÊS");
                nandatitulo6.setText("          interrelações ");
                break;
            case "db_sv.db":
                sDescription = "Hej, du kan följa mig och jag heter @ANDRESPRO100. Det tog mig lång tid att skapa den här appen. Jag lämnade till och med universitetet för kärlek och tror på det här projektet och lägger till alla möjliga översättningar för att hjälpa så många människor som möjligt. Jag hoppas du stöttar mig genom att göra inköp. Jag ska försöka fixa det så snart som möjligt.";
                sAceptar = "Acept";
                nandatitulo.setText(" omvårdnadsdiagnoser" +
                        "                            SVENSKA");
                nandatitulo3.setText(" NIC inte tillgänglig" +
                        "                           KOMMER SNART");
                break;
            case "db_nl.db":
                sDescription = "Hallo, je kunt me volgen en mijn naam is @ANDRESPRO100. Het kostte me veel tijd om deze app te maken. Ik stopte zelfs met de universiteit uit liefde en geloof in dit project en voeg alle mogelijke vertalingen toe om zoveel mogelijk mensen te helpen. Ik hoop dat je me steunt door aankopen te doen. Ik probeer het zo snel mogelijk op te lossen.";
                sAceptar = "Acept";
                nandatitulo.setText(" verpleegkundige diagnoses" +
                        "                        100% nederlands");
                nandatitulo2.setText(" verpleegkundige diagnoses" +
                        "                   100% (6ª ed.) nederlands");
                nandatitulo3.setText(" NIC verpleegkundige diagnoses" +
                        "                   100% (7ª ed.) nederlands");
                nandatitulo6.setText("  onderlinge relaties ");
                break;

            case "db_ja.db":
                sDescription = "こんにちは、私をフォローしてください。私の名前は@ ANDRESPRO100です。このアプリを作成するのに長い時間がかかりました。私は大学を愛のために卒業し、このプロジェクトを信じて、できるだけ多くの人を助けるために可能な限りの翻訳を追加しました。あなたは購入することによって私をサポートします。私はできるだけ早くそれを修正しようとします。";
                sAceptar = "Acept";
                nandatitulo.setText(" 看護診断" +
                        "                            日本");
                nandatitulo3.setText(" 看護診断" +
                        "                           100%");
                break;
            case "db_zh.db":
                sDescription = "你好，你可以關注我，我的名字是@ANDRESPRO100。我花了很長時間來創建這個應用程序。我什至為了愛離開了大學，相信這個項目，並添加所有可能的翻譯來幫助盡可能多的人。我希望你通過購買來支持我。我會盡快修復它。";
                sAceptar = "Acept";
                nandatitulo.setText(" 護理診斷" +
                        "                            日本人");
                nandatitulo3.setText(" 無法使用" +
                        "                           快來了");
                break;

            case "db_ar.db":
                sDescription = "你好，你可以關注我，我的名字是@ANDRESPRO100。我花了很長時間來創建這個應用程序。我什至為了愛離開了大學，相信這個項目，並添加所有可能的翻譯來幫助盡可能多的人。我希望你通過購買來支持我。我會盡快修復它。";
                sAceptar = "Acept";
                nandatitulo.setText(" اللغة العربية" +
                        "                            اللغة العربية");
                nandatitulo3.setText(" اللغة العربية" +
                        "                           اللغة العربية");
                break;
            case "db_tr.db":
                sDescription = "你好，你可以關注我，我的名字是@ANDRESPRO100。我花了很長時間來創建這個應用程序。我什至為了愛離開了大學，相信這個項目，並添加所有可能的翻譯來幫助盡可能多的人。我希望你通過購買來支持我。我會盡快修復它。";
                sAceptar = "Acept";
                nandatitulo.setText(" Türk" +
                        "                            Türkة");
                nandatitulo3.setText(" hemşirelik tanıları" +
                        "                        TÜRK" );
                break;
            case "db_ho.db":
                sDescription = "你好，你可以關注我，我的名字是@ANDRESPRO100。我花了很長時間來創建這個應用程序。我什至為了愛離開了大學，相信這個項目，並添加所有可能的翻譯來幫助盡可能多的人。我希望你通過購買來支持我。我會盡快修復它。";
                sAceptar = "Acept";
                nandatitulo.setText(" Hindi" +
                        "                            Türkة");
                nandatitulo3.setText(" اللغة العربية" +
                        "                           Türkة");
                break;
            case "db_hi.db":
                sDescription = "你好，你可以關注我，我的名字是@ANDRESPRO100。我花了很長時間來創建這個應用程序。我什至為了愛離開了大學，相信這個項目，並添加所有可能的翻譯來幫助盡可能多的人。我希望你通過購買來支持我。我會盡快修復它。";
                sAceptar = "Acept";
                nandatitulo.setText(" नर्सिंग निदान" +
                        "                      हिन्दी");
                nandatitulo3.setText("Hindi नर्सिंग निदान" +
                        "                           Hindi");
                break;

            case "db_ur.db":
                sDescription = "你好，你可以關注我，我的名字是@ANDRESPRO100。我花了很長時間來創建這個應用程序。我什至為了愛離開了大學，相信這個項目，並添加所有可能的翻譯來幫助盡可能多的人。我希望你通過購買來支持我。我會盡快修復它。";
                sAceptar = "Acept";
                nandatitulo.setText(" نرسنگ کی تشخیص" +
                        "                        اردو");
                nandatitulo3.setText("نرسنگ کی تشخیصn" +
                        "                      اردو");
                break;

            case "db_2pl.db":
                sDescription = "你好，你可以關注我，我的名字是@ANDRESPRO100。我花了很長時間來創建這個應用程序。我什至為了愛離開了大學，相信這個項目，並添加所有可能的翻譯來幫助盡可能多的人。我希望你通過購買來支持我。我會盡快修復它。";
                sAceptar = "Acept";
                nandatitulo.setText("diagnozy pielęgniarskie" +
                        "                        Polski ");
                nandatitulo3.setText("diagnozy pielęgniarskie" +
                        "                        Polski ");
                break;
            case "db_ko.db":
                sAceptar = "Aceptar";
                sDescription = "Hola me pueden seguir y mi nombre es @ANDRESPRO100 me tomo muchisimo tiempo crear esta app incluso les ayudara en los estudio o universidad, para añadir todas las traduciones posibles y ayudar al mayor numero de personas posibles espero me apoyen haciendo compras he informarme de algun error tratare de arreglarlo lo antes posble.";
                nandatitulo.setText(" 100% 간호진단" +
                        "100% ESPAÑOL");
                nandatitulo3.setText(" NIC 100% 간호진단" +
                        "                      (7ª ed.) ");
                nandatitulo6.setText("         interrelaciones ");
                nandatitulo2.setText(" 간호진단" +
                        "                           (6ª ed.)");
                break;
        }
        switch (MainActivity.namedb2){
            case "db_2esp.db":
                sAceptar = "Aceptar";
                sDescription = " Esta aplicacion esta hecha con mucho cariño, comprar el libro es una forma de financiar los que faltan por complentar y sus respectivas traduciones, Gracias por su apoyo :) hecho por @ANDRESPRO100 sigueme :3";
                nandatitulo2.setText(" diagnosticos de enfermeria" +
                        "(6ª ed.)% ESPAÑOL");


                break;
            case "db_2ing.db":
                sDescription = "Hello, you can follow me and my name is @ANDRESPRO100. It took me a long time to create this app. I even dropped out of university for love and believe in this project and add all possible translations to help as many people as possible. I hope you support me by making purchases. I'll try to fix it as soon as possible.";
                sAceptar = "Acept";
                nandatitulo2.setText(" nursing diagnoses" +
                        "                           (6ª ed.) ENGLISH");
                nandatitulo3.setText(" NIC nursing diagnoses" +
                        "                         (7ª ed.) ENGLISH");
                nandatitulo.setText(" NOC nursing diagnoses" +
                        "                          100% ENGLISH");
                break;
            case "db_2fr.db":
                sDescription = "Bonjour, vous pouvez me suivre et je m'appelle @ANDRESPRO100. Il m'a fallu beaucoup de temps pour créer cette application et ajouter toutes les traductions possibles pour aider le plus de monde possible. J'espère que vous me soutiendrez en faisant des achats. J'ai signalé des erreurs Je vais essayer de le réparer dès que possible.";
                sAceptar = "Acept";
                nandatitulo2.setText(" diagnostics infirmiers" +
                        "                             Français");
                break;
            case "db_2it.db":
                sDescription = "Bonjour, vous pouvez me suivre et je m'appelle @ANDRESPRO100. Il m'a fallu beaucoup de temps pour créer cette application et ajouter toutes les traductions possibles pour aider le plus de monde possible. J'espère que vous me soutiendrez en faisant des achats. J'ai signalé des erreurs Je vais essayer de le réparer dès que possible.";
                sAceptar = "Acept";
                nandatitulo2.setText(" diagnostics infirmiers" +
                        "                              Italiano");
                break;
            case "db_2de.db":
                sDescription = "Hallo, du kannst mir folgen und mein Name ist @ANDRESPRO100. Ich habe lange gebraucht, um diese App zu erstellen. Ich habe sogar die Universität aus Liebe abgebrochen und glaube an dieses Projekt und füge alle möglichen Übersetzungen hinzu, um so vielen Menschen wie möglich zu helfen. Ich hoffe, Sie unterstützen mich mit Ihren Einkäufen. Ich werde versuchen, das Problem so schnell wie möglich zu beheben.";
                sAceptar = "Acept";
                nandatitulo2.setText(" diagnostics infirmiers" +
                        "                           Deutsch");
                break;
            case "db_2cs.db":
                sDescription = "Hej, du kan följa mig och jag heter @ANDRESPRO100. Det tog mig lång tid att skapa den här appen. Jag hoppade till och med av universitetet för kärlek och tror på det här projektet och lägger till alla möjliga översättningar för att hjälpa så många människor som möjligt. Jag hoppas att du stöttar mig genom att göra köp. Jag ska försöka fixa det så snart som möjligt.";
                sAceptar = "Acept";
                nandatitulo2.setText(" omvårdnadsdiagnoser" +
                        "                            svenska");
                break;
            case "db_2et.db":
                sDescription = "Tere, võite mind jälgida ja minu nimi on @ANDRESPRO100. Selle rakenduse loomine võttis mul kaua aega. Ma isegi lahkusin ülikoolist armastuse pärast ja usun sellesse projekti ja lisan kõik võimalikud tõlked, et aidata võimalikult paljusid inimesi. Loodan, et toetate mind ostude tegemisel. Püüan selle esimesel võimalusel parandada.";
                sAceptar = "Acept";
                nandatitulo2.setText(" õendusdiagnoosid" +
                        "                            rootsi keel");
                break;
            case "db_2id.db":
                sDescription = "Halo, Anda dapat mengikuti saya dan nama saya @ANDRESPRO100. Butuh waktu lama untuk membuat aplikasi ini. Saya bahkan keluar dari universitas karena cinta dan percaya pada proyek ini dan menambahkan semua kemungkinan terjemahan untuk membantu sebanyak mungkin orang. Saya harap Anda mendukung saya dengan melakukan pembelian. Saya akan mencoba memperbaikinya sesegera mungkin.";
                sAceptar = "Acept";
                nandatitulo2.setText(" omvårdnadsdiagnoser" +
                        "                          (6ª ed.) BAHASA INDONESIA");
                nandatitulo3.setText(" NIC omvårdnadsdiagnoser" +
                        "                          (7ª ed.) BAHASA INDONESIA");
                nandatitulo.setText("  omvårdnadsdiagnoser" +
                        "                       100% BAHASA INDONESIA");
                break;
            case "db_2pt.db":
                sDescription = "Olá, você pode me seguir e meu nome é @ANDRESPRO100. Demorei muito para criar este aplicativo. Até larguei a universidade por amor e acredito neste projeto e adicionei todas as traduções possíveis para ajudar o maior número de pessoas possível. Espero que você me ajude fazendo compras. Tentarei corrigi-lo o mais rápido possível.";
                sAceptar = "Acept";
                nandatitulo2.setText(" diagnósticos de enfermagem" +
                        "                             (6ª ed.) PORTUGUÊS");
                nandatitulo3.setText(" NIC diagnósticos de enfermagem" +
                        "                          (7ª ed.) PORTUGUÊS");
                break;
            case "db_2sv.db":
                sDescription = "Hej, du kan följa mig och jag heter @ANDRESPRO100. Det tog mig lång tid att skapa den här appen. Jag lämnade till och med universitetet för kärlek och tror på det här projektet och lägger till alla möjliga översättningar för att hjälpa så många människor som möjligt. Jag hoppas du stöttar mig genom att göra inköp. Jag ska försöka fixa det så snart som möjligt.";
                sAceptar = "Acept";
                nandatitulo2.setText(" omvårdnadsdiagnoser" +
                        "                            SVENSKA");
                break;
            case "db_2nl.db":
                sDescription = "Hallo, je kunt me volgen en mijn naam is @ANDRESPRO100. Het kostte me veel tijd om deze app te maken. Ik stopte zelfs met de universiteit uit liefde en geloof in dit project en voeg alle mogelijke vertalingen toe om zoveel mogelijk mensen te helpen. Ik hoop dat je me steunt door aankopen te doen. Ik probeer het zo snel mogelijk op te lossen.";
                nandatitulo2.setText(" verpleegkundige diagnoses" +
                        "                         (6ª ed.) nederlands");
                nandatitulo3.setText(" NIC verpleegkundige diagnoses" +
                        "                         (7ª ed.) nederlands");
                nandatitulo.setText(" 9NOC verpleegkundige diagnoses" +
                        "                         100% nederlands");
                sAceptar = "Acept";


                break;

            case "db_2ja.db":
                sDescription = "こんにちは、私をフォローしてください。私の名前は@ ANDRESPRO100です。このアプリを作成するのに長い時間がかかりました。私は大学を愛のために卒業し、このプロジェクトを信じて、できるだけ多くの人を助けるために可能な限りの翻訳を追加しました。あなたは購入することによって私をサポートします。私はできるだけ早くそれを修正しようとします。";
                sAceptar = "Acept";
                nandatitulo2.setText(" 看護診断" +
                        "                            日本");
                break;
            case "db_2zh.db":
                sDescription = "你好，你可以關注我，我的名字是@ANDRESPRO100。我花了很長時間來創建這個應用程序。我什至為了愛離開了大學，相信這個項目，並添加所有可能的翻譯來幫助盡可能多的人。我希望你通過購買來支持我。我會盡快修復它。";
                sAceptar = "Acept";
                nandatitulo2.setText(" 護理診斷" +
                        "                            日本人");
                break;

            case "db_2ar.db":
                sDescription = "تم إنشاء هذا التطبيق بالحب ، وشراء الكتاب هو وسيلة لتمويل الكتب التي يجب إكمالها وترجماتها الخاصة ، شكرًا لك على دعمك :) من إعداد @ ANDRESPRO100 اتبعني";
                sAceptar = "Acept";
                nandatitulo2.setText(" اللغة العربية" +
                        "                            اللغة العربية");
                nandatitulo.setText(" اللغة العربية" +
                        "                            اللغة العربية");
                nandatitulo3.setText(" اللغة العربية" +
                        "                           اللغة العربية");
                break;
            case "db_2tr.db":
                sDescription = "你好，你可以關注我，我的名字是@ANDRESPRO100。我花了很長時間來創建這個應用程序。我什至為了愛離開了大學，相信這個項目，並添加所有可能的翻譯來幫助盡可能多的人。我希望你通過購買來支持我。我會盡快修復它。";
                sAceptar = "Acept";
                nandatitulo2.setText(" hemşirelik tanıları" +
                        "                        TÜRK");
                nandatitulo.setText("  hemşirelik tanıları" +
                        "                            TÜRK");
                nandatitulo3.setText(" hemşirelik tanıları" +
                        "                        TÜRK" );
                break;

            case "db_2hi.db":
                sDescription = "你好，你可以關注我，我的名字是@ANDRESPRO100。我花了很長時間來創建這個應用程序。我什至為了愛離開了大學，相信這個項目，並添加所有可能的翻譯來幫助盡可能多的人。我希望你通過購買來支持我。我會盡快修復它。";
                sAceptar = "Acept";
                nandatitulo2.setText(" नर्सिंग निदान Hindi" +
                        "                         हिन्दी");
                break;
            case "db_2ur.db":
                sDescription = "你好，你可以關注我，我的名字是@ANDRESPRO100。我花了很長時間來創建這個應用程序。我什至為了愛離開了大學，相信這個項目，並添加所有可能的翻譯來幫助盡可能多的人。我希望你通過購買來支持我。我會盡快修復它。";
                sAceptar = "Acept";
                nandatitulo2.setText(" نرسنگ کی تشخیص" +
                        "                    اردو");
                break;

            case "db_2pl.db":
                sDescription = "Hola me pueden seguir y mi nombre es @ANDRESPRO100 me tomo muchisimo tiempo crear esta app incluso deje la universidad por amor y creer en este proyecto y añadir todas las traduciones posibles para ayudar al mayor numero de personas espero me apoyen haciendo compras he informarme de algun error tratare de arreglarlo lo antes posble.";
                sAceptar = "Acept";
                nandatitulo2.setText("diagnozy pielęgniarskie" +
                        "                        Polski ");
                nandatitulo.setText("diagnozy pielęgniarskie" +
                        "                        Polski ");
                nandatitulo3.setText("diagnozy pielęgniarskie" +
                        "                        Polski ");
                break;

        }
/*
        switch (MainActivity.namedb3){

                case "db_3esp.db":
                    sAceptar = "Aceptar";
                    sDescription = "Hola me pueden seguir y mi nombre es @ANDRESPRO100 me tomo muchisimo tiempo crear esta app incluso deje la universidad por amor y creer en este proyecto y añadir todas las traduciones posibles para ayudar al mayor numero de personas espero me apoyen haciendo compras he informarme de algun error tratare de arreglarlo lo antes posble.";
                    nandatitulo.setText(" diagnosticos de enfermeria" +
                            "                            ESPAÑOL");
                    nandatitulo3.setText(" NIC no disponible" +
                            "                          PROXIMAMENTE");
                    break;

    }
*/
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
        AlertDialog.Builder a = new AlertDialog.Builder(MainActivity.this);
        a.setMessage(sDescription + "\n")
                .setCancelable(false)
                .setPositiveButton(sAceptar, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = a.create();
        a.setTitle("About");
        a.show();
    }
    public void home(){

        final String appPackageName = getPackageName();
        try {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/dev?id=7404218892844546003")));
        } catch (android.content.ActivityNotFoundException anfe) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/dev?id=7404218892844546003")));
        }finally {

        }
    }
    public void calificar(){

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
        // Setting Dialog Message
        alertDialog.setTitle("CALIFICAR QUALIFY");
        alertDialog.setMessage(" \n" +
                "Your comment is very important to me, please VALUE or QUALIFY ..." +
                "\n" +
                "Or leave a comment to improve this application " +
                "You'll only take a few minutes. Thanks for your help..."  +"\n" + "\n" +
                "Tu comentario es muy importante y tambien demuestra tu apoyo a este proyecto, por favor califica esta app y danos tu reseña, leemos todos los comentarios, solo te tomara 1 minuto :,) " +
                "");

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


    public static Intent getOpenFacebookIntent(Context context) {

        try {
            context.getPackageManager()
                    .getPackageInfo("com.facebook.katana", 0); //Checks if FB is even installed.
            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse("fb://page/206722942682943")); //Trys to make intent with FB's URI
        } catch (Exception e) {
            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.facebook.com/lizmaithe")); //catches and opens a url to the desired page
        }
    }
    public static Intent getOpenYouTubeIntent(Context context) {

        try {
            context.getPackageManager()
                    .getPackageInfo("com.google.android.youtube", 0); //Checks if YT is even installed.
            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.youtube.com/user/ANDRESPRO100")); //Trys to make intent with YT's URI
        } catch (Exception e) {
            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.youtube.com/user/ANDRESPRO100")); //catches and opens a url to the desired page
        }
    }

    public static Intent getOpenInstagramIntent(Context context) {

        try {
            context.getPackageManager()
                    .getPackageInfo("com.instagram.android", 0); //Checks if Instagram is even installed.
            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.instagram.com/andrespro100")); //Trys to make intent with Instagram's URI
        } catch (Exception e) {
            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.instagram.com/andrespro100")); //catches and opens a url to the desired page
        }
    }
    public static Intent getOpenTwitterIntent(Context context) {

        try {
            context.getPackageManager()
                    .getPackageInfo("com.twitter.android", 0); //Checks if Twitter is even installed.
            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://twitter.com/Andrespro_100")); //Trys to make intent with Twitter's's URI
        } catch (Exception e) {
            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://twitter.com/Andrespro_100")); //catches and opens a url to the desired page
        }
    }

    public static Intent getOpenTiktokIntent(Context context) {

        try {
            context.getPackageManager()
                    .getPackageInfo("com.tiktok.android", 0); //Checks if Twitter is even installed.
            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.tiktok.com/@andrespro100")); //Trys to make intent with Twitter's's URI
        } catch (Exception e) {
            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.tiktok.com/@andrespro100")); //catches and opens a url to the desired page
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main3, menu);



        return true;
    }
    private void setNavigationViewListner() {
        NavigationView navigationView = (NavigationView) findViewById(R.id.navi);
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        switch (id) {
            /*
            case R.id.website: {
                click.start();
            //    lanzarsuscricion();
                connectGooglePlayBilling();

                break;
            }
            */
            case R.id.inste: {
               // Toasty.success(this, "INSTAGRAM SELEC :)", Toast.LENGTH_SHORT, true).show();

                click.start();
                Intent instagramIntent = getOpenInstagramIntent(MainActivity.this);
                startActivity(instagramIntent);
                break;
            }
            case R.id.walpappers: {
                // Toasty.success(this, "INSTAGRAM SELEC :)", Toast.LENGTH_SHORT, true).show();
                if (interstitial != null) {
                    interstitial.show(MainActivity.this);
                } else {
                    Log.d("TAG", "The interstitial ad wasn't ready yet.");
                }
                click.start();
                Intent i = new Intent(this, Walpapers.class );
                startActivity(i);
                break;
            }
            case R.id.bmi: {
                // Toasty.success(this, "INSTAGRAM SELEC :)", Toast.LENGTH_SHORT, true).show();
                if (interstitial != null) {
                    interstitial.show(MainActivity.this);
                } else {
                    Log.d("TAG", "The interstitial ad wasn't ready yet.");
                }
                click.start();
                Intent i = new Intent(this, Calculadoras.class );
                startActivity(i);
                break;
            }
            /*
            case R.id.youtu: {
              //  Toasty.success(this, "YOUTUBE SELEC :)", Toast.LENGTH_SHORT, true).show();

                click.start();
                Intent youtubeIntent = getOpenYouTubeIntent(MainActivity.this);
                startActivity(youtubeIntent);

                break;
            }
            */
            case R.id.tutorial: {
                // Toasty.success(this, "FACEBOOK SELEC :)", Toast.LENGTH_SHORT, true).show();
                if (interstitial != null) {
                    interstitial.show(MainActivity.this);
                } else {
                    Log.d("TAG", "The interstitial ad wasn't ready yet.");
                }
                click.start();
                Intent i = new Intent(this, Tutorial.class );
                startActivity(i);
                break;
            }

            case R.id.face: {
               // Toasty.success(this, "FACEBOOK SELEC :)", Toast.LENGTH_SHORT, true).show();
                click.start();
                Intent facebookIntent = getOpenFacebookIntent(MainActivity.this);
                startActivity(facebookIntent);
                break;
            }
            case R.id.tui: {
             //   Toasty.success(this, "TWITTER SELEC :)", Toast.LENGTH_SHORT, true).show();
                click.start();
                Intent twitterIntent = getOpenTwitterIntent(MainActivity.this);
                startActivity(twitterIntent);
                break;
            }
            case R.id.share: {
                click.start();

                shareenviar();
                break;
            }

            case R.id.rate: {
                click.start();

                startReviewFlow();

                break;
            }

            case R.id.home: {
                click.start();

                home();
                break;
            }
            case R.id.info: {
                click.start();

                info();
                break;
            }

            case R.id.tiktok:
                //click.start();

                Intent tiktokIntent = getOpenTiktokIntent(MainActivity.this);
                startActivity(tiktokIntent);
                click.start();
                break;

        }
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        if (mToggle.onOptionsItemSelected(item)){

            return true;
        }

        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_share) {
            click.start();

            shareenviar();
        }


        if(id == R.id.valorar) {
            click.start();
            calificar();

        }

        if (id == R.id.action_about) {
            click.start();

            info();
        }

        if(id == R.id.valorarr) {
//            startReviewFlow();
//            calificar();


            Intent mIntent  = new Intent(MainActivity.this, Favoritos.class);
            startActivity(mIntent);
        }
//        if (id == ) {
//            MenuFunctions menu = new MenuFunctions();
//            Busqueda encontradas = menu.Search(this,"Males");
//
//            Intent mIntent  = new Intent(MainActivity.this, DiagnosticosBusqueda.class);
//            mIntent.putExtra("Busqueda",  encontradas.elements);
//            mIntent.putExtra("Palabra",  encontradas.palabra);
//
//            startActivity(mIntent);
//        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }

    /*
       <RelativeLayout
                    android:layout_width="200dp"
                    android:layout_height="match_parent"
                    android:layout_gravity="center"
                    android:layout_marginTop="10dp"

                    android:background="@drawable/shape_background_button_idiomas">

                    <ImageView
                        android:id="@+id/imageView3"
                        android:layout_width="30dp"
                        android:layout_height="28dp"
                        android:layout_alignParentRight="true"
                        android:layout_centerVertical="true"
                        android:layout_marginRight="10dp"
                        app:srcCompat="@drawable/ic_drop" />

                    <Button
                        android:id="@+id/language22"
                        android:layout_width="match_parent"
                        android:layout_height="wrap_content"
                        android:background="@android:color/transparent"
                        android:onClick="SeleccionarIdioma"
                        android:text="Español"
                        android:textColor="#FFFFFF"
                        tools:visibility="gone" />
                </RelativeLayout>

     */
}