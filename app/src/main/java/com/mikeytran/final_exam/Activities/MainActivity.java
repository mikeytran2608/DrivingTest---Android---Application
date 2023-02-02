package com.mikeytran.final_exam.Activities;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.mikeytran.final_exam.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, Runnable{
    LinearLayout bt_thiSatHach, bt_lichSuBaiThi;
    Button bt_a121, bt_b121, bt_cancel1, bt_a122, bt_b122, bt_cancel2;
    Dialog dialogThiSatHach, dialogMeoThucHanh;
    private AdView mAdView;
    private InterstitialAd mInterstitialAd;
    int dem1 = 0,dem2=0, dem3 = 0, dem4 = 0, dem5=0,dem6=0;

    public static int dem7=0,dem8=0,dem9=0,dem10=0;

    Thread t;
    int time = 0;
    public static boolean checkTime = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setControl();
        t = new Thread(this);
        t.start();
    }

    public void setControl(){
        mAdView =(AdView)findViewById(R.id.adView);

        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getString(R.string.interstitial_id));
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                loadInterstitialAd();
            }
        });

        loadInterstitialAd();
        bt_thiSatHach = findViewById(R.id.bt_thiSatHach);

        bt_lichSuBaiThi = findViewById(R.id.bt_lichSuBaiThi);
        bt_thiSatHach.setOnClickListener(this);

        bt_lichSuBaiThi.setOnClickListener(this);
    }


    private void loadInterstitialAd() {
        if (mInterstitialAd != null) {
            AdRequest adRequest = new AdRequest.Builder()
                    .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                    .build();

            mInterstitialAd.loadAd(adRequest);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (!mInterstitialAd.isLoaded())loadInterstitialAd();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mAdView != null) {
            mAdView.resume();
        }
        if (!mInterstitialAd.isLoaded())loadInterstitialAd();
    }
    @Override
    protected void onPause() {
        super.onPause();
        if (mAdView != null) {
            mAdView.pause();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mAdView != null) {
            mAdView.destroy();
        }
    }

    public void setDialogThiSatHach(){
        dialogThiSatHach = new Dialog(this);
        dialogThiSatHach.setContentView(R.layout.custom_dialog_thisathach);
        dialogThiSatHach.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogThiSatHach.setCanceledOnTouchOutside(true);
        dialogThiSatHach.show();
        bt_a121 = dialogThiSatHach.findViewById(R.id.bt_a121);
        bt_b121 = dialogThiSatHach.findViewById(R.id.bt_b2);
        bt_cancel1 = dialogThiSatHach.findViewById(R.id.bt_cancel1);
        bt_a121.setOnClickListener(this);
        bt_b121.setOnClickListener(this);
        bt_cancel1.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bt_thiSatHach:
                setDialogThiSatHach();
                break;

            case R.id.bt_lichSuBaiThi:
                Intent intent_lichsu = new Intent(MainActivity.this,LichSuBaiThiActivity.class);
                startActivity(intent_lichsu);
                dem6++;
                if (dem6==3 || checkTime){
                    dem6 = 0;
                    checkTime = false;
                    mInterstitialAd.show();
                }
                break;
            case R.id.bt_a121:
                Intent intentThiSatHachA = new Intent(MainActivity.this,ThiSatHachActivity.class);
                intentThiSatHachA.putExtra("tenBaiThi",'a');
                startActivity(intentThiSatHachA);
                dialogThiSatHach.dismiss();
                dem1++;
                if (dem1==3 || checkTime){
                    dem1 = 0;
                    checkTime = false;
                    mInterstitialAd.show();
                }
                break;
            case R.id.bt_b2:
                Intent intentThiSatHachB = new Intent(MainActivity.this,ThiSatHachActivity.class);
                intentThiSatHachB.putExtra("tenBaiThi",'b');
                startActivity(intentThiSatHachB);
                dialogThiSatHach.dismiss();
                dem1++;
                if (dem1==3 || checkTime){
                    dem1 = 0;
                    checkTime = false;
                    mInterstitialAd.show();
                }
                break;
            case R.id.bt_cancel1:
                dialogThiSatHach.dismiss();
                break;

            case R.id.bt_cancel2:
                dialogMeoThucHanh.dismiss();
                break;
            default:
                break;
        }
    }

    @Override
    public void run() {
        while (true) {
            time+=1;
            if (time== 180) {
                checkTime = true;
                time = 0;
                Log.d("Checktime","OK");
            }
            try {
                t.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
