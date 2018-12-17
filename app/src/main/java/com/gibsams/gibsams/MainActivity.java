package com.gibsams.gibsams;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author jacklovett
 */
public class MainActivity extends AppCompatActivity {

    private String ABOUT_US;
    private String ADVICE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("info", "Creating App");
        try {
            super.onCreate(savedInstanceState);
            setConstants();
            setContentView(R.layout.activity_main);
        } catch (Exception ex) {
            Log.e("error", ex.getMessage());
        }
    }

    @Override
    protected void onStart() {
        Log.i("info", "Starting App");
        try {
            super.onStart();
        } catch (Exception ex) {
            Log.e("error", ex.getMessage());
        }
    }

    @Override
    protected void onResume() {
        Log.i("info", "Resuming App");
        try {
            super.onResume();
        } catch (Exception ex) {
            Log.e("error", ex.getMessage());
        }
    }

    @Override
    protected void onPause() {
        Log.i("info", "Pausing App");
        try {
            super.onPause();
        } catch (Exception ex) {
            Log.e("error", ex.getMessage());
        }
    }

    @Override
    protected void onStop() {
        Log.i("info", "Stopping App");
        try {
            super.onStop();
        } catch (Exception ex) {
            Log.e("error", ex.getMessage());
        }
    }

    @Override
    protected void onDestroy() {
        Log.i("info", "Destroying App");
        try {
            super.onDestroy();
        } catch (Exception ex) {
            Log.e("error", ex.getMessage());
        }
    }

    /**
     * Display page content for information pages
     * @param v
     */
    public void changePage(View v) {
        String page = v.getTag().toString();
        Log.i("info", "toContentPage: " + page + " - start");
        ViewGroup contentLayer = findViewById(R.id.contentLayout);
        contentLayer.removeAllViewsInLayout();
        try {
            LayoutInflater layoutInflater = (LayoutInflater)
                    this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            if (page.equals(ABOUT_US)) {
                contentLayer.addView(layoutInflater.inflate(R.layout.about_us_page, contentLayer));
            }
            else if (page.equals(ADVICE)) {
                contentLayer.addView(layoutInflater.inflate(R.layout.advice_page, contentLayer));
            }
        } catch (Exception ex) {
            Log.e("error", "An error occurred while changing page" + ex.getMessage());
        }
        Log.i("info", "toContentPage: " + page + " - end");
    }

    /**
     * Opens disclaimer dialog that will take the user to the chat
     * @param v
     */
    public void openDisclaimerDialog(View v) {
        Log.i("info", "openDisclaimerDialog - start");
        DisclaimerDialog disclaimerDialog = new DisclaimerDialog();
        disclaimerDialog.show(getSupportFragmentManager(), "123");
        Log.i("info", "openDisclaimerDialog - end");
    }

    private void setConstants() {
        ABOUT_US = getString(R.string.about_us);
        ADVICE = getString(R.string.advice);
    }
}
