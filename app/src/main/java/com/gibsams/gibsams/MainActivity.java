package com.gibsams.gibsams;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private String ABOUT_US;
    private String ADVICE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            Log.i("info", "App Creating");
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            setConstants();
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
        contentLayer.addView(createContentTextLayout(page));
        Log.i("info", "toContentPage: " + page + " - end");
    }

    /**
     * Opens dialog that will take the user to the chat
     * @param v
     */
    public void openDisclaimerDialog(View v) {
        Log.i("info", "openDisclaimerDialog - start");

        final AlertDialog.Builder builder = new AlertDialog.Builder(
                new ContextThemeWrapper(this, R.style.popUpTheme));
        builder.setTitle(R.string.disclaimer_title);

        builder.setMessage(R.string.disclaimer_content);
        builder.setCancelable(true);

        builder.setPositiveButton(R.string.disclaimer_confirm, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                toChat();
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });

        builder.show();

        Log.i("info", "openDisclaimerDialog - end");
    }

    private void toChat() {
        Intent intent = new Intent(this, ChatActivity.class);
        startActivity(intent);
    }

    /**
     * Here we create a ScrollView that contains the populated TextView
     * @param page
     * @return
     */
    private ScrollView createContentTextLayout(String page) {
        Log.i("info", "createContentTextLayout - start");
        FrameLayout.LayoutParams scrollLayoutParams = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.FILL_PARENT);

        scrollLayoutParams.setMargins(40, 0, 40, 40);

        ScrollView contentScrollView = new ScrollView(this);
        contentScrollView.setLayoutParams(scrollLayoutParams);
        contentScrollView.setBackgroundColor(Color.WHITE);
        contentScrollView.setPadding(30, 20, 30, 20);

        TextView contentTextView = createTextView(page);
        contentScrollView.addView(contentTextView);

        Log.i("info", "createContentTextLayout - end");
        return contentScrollView;
    }

    /**
     * Set correct text content based on selected page
     * @param page
     * @return
     */
    private TextView createTextView(String page) {

        FrameLayout.LayoutParams textViewLayoutParams =
                new FrameLayout.LayoutParams(
                        FrameLayout.LayoutParams.MATCH_PARENT,
                        FrameLayout.LayoutParams.WRAP_CONTENT
                );

        textViewLayoutParams.setMargins(0, 20, 0, 0);

        TextView textView = new TextView(this);
        textView.setLayoutParams(textViewLayoutParams);
        textView.setPadding(30, 30, 30, 30);
        textView.setText(setPageContent(page));
        textView.setTextSize(18);
        return textView;
    }

    private String setPageContent(String page) {
        String content = "";
        if(page.equals(ABOUT_US))
            content = getString(R.string.about_us_content);
        else if (page.equals(ADVICE))
            content = getString(R.string.advice_content);

        return content;
    }

    private void setConstants() {
        ABOUT_US = getString(R.string.about_us);
        ADVICE = getString(R.string.advice);
    }
}
