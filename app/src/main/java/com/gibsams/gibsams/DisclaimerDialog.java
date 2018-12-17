package com.gibsams.gibsams;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.ContextThemeWrapper;

public class DisclaimerDialog extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Log.i("info", "onCreateDialog - start");

        final AlertDialog.Builder builder = new AlertDialog.Builder(
                        new ContextThemeWrapper(getActivity(), R.style.popUpTheme));

        builder.setTitle(R.string.disclaimer_title);
        builder.setMessage(R.string.disclaimer_content);
        builder.setCancelable(true);

        builder.setPositiveButton(R.string.disclaimer_confirm, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                toChat();
            }
        });

        builder.setNegativeButton(R.string.disclaimer_cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });

        Log.i("info", "onCreateDialog - end");
        return builder.create();
    }

    private void toChat() {
        Intent intent = new Intent(getActivity(), ChatActivity.class);
        startActivity(intent);
    }

}
