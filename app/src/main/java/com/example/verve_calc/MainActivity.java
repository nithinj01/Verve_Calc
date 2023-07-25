package com.example.verve_calc;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import net.pubnative.lite.sdk.HyBidAdRequest;
import net.pubnative.lite.sdk.views.HyBidAdView;
import net.pubnative.lite.sdk.HyBidInterstitialAd;
import net.pubnative.lite.sdk.HyBidInterstitialAdLoadCallback;
import net.pubnative.lite.sdk.HyBid;
import net.pubnative.lite.sdk.HyBidBannerAd;
import net.pubnative.lite.sdk.HyBidBannerAdView;
import net.pubnative.lite.sdk.HyBidAdSize;
import net.pubnative.lite.sdk.HyBidBannerAdLoadCallback;

public class MainActivity extends AppCompatActivity {

    private TextView textViewResult;
    private StringBuilder currentNumber = new StringBuilder();
    private String operator = "";
    private double result = 0.0;
    private HyBidAdView bannerAdView;
    private HyBidInterstitialAd mInterstitial;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Initialize HyBid SDK
        HyBid.initialize(this, "YOUR_HYBID_APP_TOKEN");
        // Find the banner ad view
        bannerAdView = findViewById(R.id.bannerAdView);

        // Load Banner Ad
        loadBannerAd();
        // Load Interstitial Ad
        loadInterstitialAd();

        textViewResult = findViewById(R.id.textViewResult);

        // Set click listeners for number buttons
        setNumberButtonClickListeners();

        // Set click listeners for operator buttons
        setOperatorButtonClickListeners();

        // Set click listener for equals button
        Button buttonEquals = findViewById(R.id.buttonEquals);
        buttonEquals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onEqualClick();
                interstitialAd = new HyBidInterstitialAd(this,"", new HyBidInterstitialAd.Listener() {

                if (interstitialAd != null && interstitialAd.isReady()) {
                    interstitialAd.show();
                }
            }
        });

        // Set click listener for clear button
        Button buttonClear = findViewById(R.id.buttonClear);
        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClearClick();
            }
        });

        // Set click listener for dot button
        Button buttonDot = findViewById(R.id.buttonDot);
        buttonDot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDotClick();
            }
        });
    }
    //load banner ad
    private void loadBannerAd() {
            bannerAdView.load("ZONE_ID", new PNAdView.Listener() {
                @Override
                public void onAdLoaded() {

                }

                @Override
                public void onAdLoadFailed(Throwable error) {

                }

                @Override
                public void onAdImpression() {

                }

                @Override
                public void onAdClick() {

                }
            });
    }
    //load interstitial ad
    private void loadInterstitialAd() {
            mInterstitial = new HyBidInterstitialAd(this,"ZONE_ID", new HyBidInterstitialAd.Listener() {
                @Override
                public void onInterstitialLoaded() {

                }

                @Override
                public void onInterstitialLoadFailed(Throwable error) {

                }

                @Override
                public void onInterstitialImpression() {

                }

                @Override
                public void onInterstitialDismissed() {

                }

                @Override
                public void onInterstitialClick() {

                }
            });
            mInterstitial.load();
    }

    private void setNumberButtonClickListeners() {
        int[] numberButtonIds = {R.id.button0, R.id.button1, R.id.button2, R.id.button3, R.id.button4,
                R.id.button5, R.id.button6, R.id.button7, R.id.button8, R.id.button9};

        for (int buttonId : numberButtonIds) {
            Button button = findViewById(buttonId);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onNumberClick(button.getText().toString());
                }
            });
        }
    }

    private void setOperatorButtonClickListeners() {
        int[] operatorButtonIds = {R.id.buttonAdd, R.id.buttonSubtract, R.id.buttonMultiply, R.id.buttonDivide};

        for (int buttonId : operatorButtonIds) {
            Button button = findViewById(buttonId);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onOperatorClick(button.getText().toString());
                }
            });
        }
    }

    private void onNumberClick(String number) {
        currentNumber.append(number);
        updateDisplay();
    }

    private void onOperatorClick(String op) {
        if (currentNumber.length() > 0) {
            operator = op;
            result = Double.parseDouble(currentNumber.toString());
            currentNumber.setLength(0);
        }
    }

    private void onEqualClick() {
        if (currentNumber.length() > 0 && !operator.isEmpty())

