package com.batchmates.android.intentbroadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.widget.Toast;

public class OrderedRecieverTwo extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        Toast.makeText(context,"RecieverTwo",Toast.LENGTH_SHORT).show();
        Log.d("", "onReceive: REcieverTwo");
    }
}
