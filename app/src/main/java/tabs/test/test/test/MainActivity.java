package tabs.test.test.test;


import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.applovin.adview.AppLovinIncentivizedInterstitial;
import com.applovin.adview.AppLovinInterstitialAd;
import com.applovin.adview.AppLovinInterstitialAdDialog;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdDisplayListener;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinAdRewardListener;
import com.applovin.sdk.AppLovinAdSize;
import com.applovin.sdk.AppLovinErrorCodes;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinSdkSettings;

import java.util.Map;

public class MainActivity extends AppCompatActivity {
    Button btnShowInterstitial, btnShowVideo;

    /*       APPLOVIN     */
    AppLovinSdk appLovinSdk;
    AppLovinAdLoadListener mAdLoadListener;
    AppLovinAdRewardListener mAdRewardListener;
    AppLovinAdDisplayListener mAdDisplayListener;
    AppLovinIncentivizedInterstitial myIncent;
    public AppLovinAd appLovinAd = null;
    AppLovinInterstitialAdDialog appLovinAdDialog;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnShowInterstitial = (Button) findViewById(R.id.btnShowInterstitial);
        btnShowVideo  = (Button) findViewById(R.id.btnShowVideo);
        btnShowVideo.setOnClickListener(v-> {showVideo();});

        btnShowInterstitial.setOnClickListener(v -> {
            showInterstitial();
        });

        initializeApplovin();
     //   loadVideo();
    }

    private void initializeApplovin() {
        initListeners();
        AppLovinSdkSettings appLovinSdkSettings = new AppLovinSdkSettings();
        appLovinSdkSettings.setTestAdsEnabled(true);
        appLovinSdkSettings.setVerboseLogging(true);

        appLovinSdk = AppLovinSdk.getInstance(AESHelper.decryption(AESHelper.KEY), appLovinSdkSettings, this);
        appLovinSdk.initializeSdk();

        myIncent = AppLovinIncentivizedInterstitial.create(appLovinSdk);
        myIncent.preload(mAdLoadListener);

        appLovinAdDialog = AppLovinInterstitialAd.create(appLovinSdk, this);
    }

    private void initListeners() {
        mAdLoadListener = new AppLovinAdLoadListener() {
            @Override
            public void adReceived(AppLovinAd ad) {
                appLovinAd = ad;
                Log.d("AppLovinDebug", "adReceived: ");
                Toast.makeText(getApplicationContext(), "loadin OK !", Toast.LENGTH_LONG).show();
            }

            @Override
            public void failedToReceiveAd(int i) {
                Log.d("AppLovinDebug", "failedToReceiveAd: " + i);
                Toast.makeText(getApplicationContext(), "loadin FAILED!" + i, Toast.LENGTH_LONG).show();
                new Handler().postDelayed(() -> myIncent.preload(mAdLoadListener), 5000);
            }
        };

        mAdRewardListener = new AppLovinAdRewardListener() {


            @Override
            public void userRewardVerified(AppLovinAd appLovinAd, Map map) {

            }

            @Override
            public void userOverQuota(AppLovinAd appLovinAd, Map map) {

            }

            @Override
            public void userRewardRejected(AppLovinAd appLovinAd, Map map) {

            }

            @Override
            public void validationRequestFailed(AppLovinAd appLovinAd, int i) {

            }

            @Override
            public void userDeclinedToViewAd(AppLovinAd appLovinAd) {

            }
        };
        mAdDisplayListener = new AppLovinAdDisplayListener() {
            @Override
            public void adDisplayed(AppLovinAd appLovinAd) {
                Log.d("AppLovinDebug", "adDisplayed: ");
            }

            @Override
            public void adHidden(AppLovinAd appLovinAd) {


            }
        };

    }
    private void loadVideo(){
        myIncent.preload(mAdLoadListener);
    }
    private void showVideo(){
        myIncent.show(this, mAdRewardListener, null, new AppLovinAdDisplayListener(){
            @Override
            public void adDisplayed(AppLovinAd appLovinAd) {
                Log.d("AppLovinDebug", "adDisplayed: ");
            }

            @Override
            public void adHidden(AppLovinAd appLovinAd) {
                loadVideo();
            }
        });


    }

//    private void showVideo(){
//        myIncent.show(this, mAdRewardListener, null, mAdDisplayListener);
//    }
//
    private void showInterstitial(){
        appLovinAdDialog.setAdDisplayListener(new AppLovinAdDisplayListener() {
            @Override
            public void adDisplayed(AppLovinAd appLovinAd) {

            }

            @Override
            public void adHidden(AppLovinAd appLovinAd) {

            }
        });
        appLovinAdDialog.showAndRender(appLovinAd);
    }
//    private void loadInterstitial(){
//        appLovinSdk.getAdService().loadNextAd(AppLovinAdSize.INTERSTITIAL, new AppLovinAdLoadListener() {
//            @Override
//            public void adReceived(AppLovinAd ad) {
//               appLovinAd = ad;
//            }
//
//            @Override
//            public void failedToReceiveAd(int i) {
//                Log.d("AppLovinDebug", "failed load interstitial: " + i);
//            }
//        });
//
//    }
}
