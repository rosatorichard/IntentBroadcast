package com.batchmates.android.intentbroadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {

    private static final String TAG = "This is My Reciever";

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        Toast.makeText(context,"Showing List",Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onReceive: Reciever Reached");
    }
}
