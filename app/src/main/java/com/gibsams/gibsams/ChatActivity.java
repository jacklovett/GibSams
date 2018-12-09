package com.gibsams.gibsams;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Date;

public class ChatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("info", "onCreate for ChatActivity - start");
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_chat);
        } catch(Exception ex) {
            Log.e("error", ex.getMessage());
        }
        Log.i("info", "onCreate for ChatActivity - start");
    }

    /**
     * Go back to main activity
     * @param v
     */
    public void goBack(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void send(View v) {
        ViewGroup chatContentLayer = findViewById(R.id.conversation_content);
        sendMessage(chatContentLayer);
        try {
            Thread.sleep(2000);
        } catch (Exception ex) {
            Log.e("error", ex.getMessage());
        }
        returnResponse(chatContentLayer);
    }

    /**
     * Send message
     */
    private void sendMessage(ViewGroup chatContentLayer) {
        try {
            TextInputEditText input = findViewById(R.id.inputMessage);
            String message = input.getText().toString() + "\n" + generateDateText();
            input.setText("");

            FrameLayout.LayoutParams textViewLayoutParams =
                    new FrameLayout.LayoutParams(
                            FrameLayout.LayoutParams.WRAP_CONTENT,
                            FrameLayout.LayoutParams.WRAP_CONTENT
                    );

            TextView messageView = generateTextView(textViewLayoutParams);
            messageView.setText(message);
            messageView.setTextColor(getResources().getColor(R.color.colorAccent));
            messageView.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
            messageView.setPadding(20, 0, 20, 0);

            chatContentLayer.addView(messageView);

        } catch (Exception ex) {
            Log.e("error", ex.getMessage());
        }
    }

    /**
     * return response
     * @param chatContentLayer
     */
    private void returnResponse(ViewGroup chatContentLayer) {
        String response = "Hi! How can I help?" + "\n" + generateDateText();

        FrameLayout.LayoutParams textViewLayoutParams =
                new FrameLayout.LayoutParams(
                        FrameLayout.LayoutParams.WRAP_CONTENT,
                        FrameLayout.LayoutParams.WRAP_CONTENT
                );

        textViewLayoutParams.gravity = Gravity.RIGHT;

        TextView responseView = generateTextView(textViewLayoutParams);
        responseView.setText(response);
        responseView.setGravity(Gravity.RIGHT);
        responseView.setPadding(20, 0, 20, 0);
        responseView.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        responseView.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

        chatContentLayer.addView(responseView);
    }

    private TextView generateTextView(FrameLayout.LayoutParams layoutParams) {

        layoutParams.setMargins(0, 10, 0, 10);

        TextView textView = new TextView(this);
        textView.setLayoutParams(layoutParams);
        textView.setPadding(10, 0, 10, 0);
        textView.setTextSize(18);

        return textView;
    }

    private String generateDateText(){
        Date sentDate = new Date();
        return DateFormat.getTimeInstance().format(sentDate);
    }
}
