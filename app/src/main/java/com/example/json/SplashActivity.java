package com.example.t31054.json;

/**
 * Created by SUBHA on 4/11/2016.
 */
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.util.Base64;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;



import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by t31045 on 12/3/2015.
 */
public class SplashActivity extends Activity {
ImageView imgLogo;
    Animation hyperspaceJumpAnimation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);
        imgLogo=(ImageView)findViewById(R.id.imgLogo);
        haveNetworkConnection();
        hyperspaceJumpAnimation = AnimationUtils.loadAnimation(this,
                R.anim.hyperspace_jump);
        if(haveNetworkConnection()==true) {
            imgLogo.startAnimation(hyperspaceJumpAnimation);

            new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

                @Override
                public void run() {
                    // This method will be executed once the timer is over
                    // Start your app main activity
                    Intent i = new Intent(SplashActivity.this, MainActivity1.class);
                    startActivity(i);

                    // close this activity
                    finish();
                }
            }, 5000);
        }
        else{
            Toast.makeText(getApplicationContext(),"Please check your network connection",Toast.LENGTH_LONG).show();
        }


    }


    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        finish();
    }



    private boolean haveNetworkConnection() {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;

        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
        for (NetworkInfo ni : netInfo) {
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected())
                    haveConnectedWifi = true;

            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if (ni.isConnected()) {
                    haveConnectedMobile = true;
                }


        }
        return haveConnectedWifi || haveConnectedMobile;
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode==-1)
        {
            Intent i=new Intent(SplashActivity.this,SplashActivity.class);
            startActivity(i);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        //getting event list

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i=new Intent(SplashActivity.this,SplashActivity.class);
        startActivity(i);
    }


}
