package com.batchmates.android.intentbroadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.widget.Toast;

public class HideReciever extends BroadcastReceiver {

    boolean truth=true;
    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.


        switch (intent.getAction())
        {
            case "MONSTER":
                Toast.makeText(context, "Hee hee that tickles", Toast.LENGTH_SHORT).show();
                break;

            case "android.intent.action.AIRPLANE_MODE":
            if (truth == true) {
                Toast.makeText(context, "You cant hide from me forevor", Toast.LENGTH_LONG).show();
                truth = false;
            } else {
                Toast.makeText(context, "Good to see your Courage has returned", Toast.LENGTH_LONG).show();
                truth = true;
            }
            break;
        }
    }
}
