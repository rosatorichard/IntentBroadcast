package com.batchmates.android.intentbroadcast;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class MyIntentService extends IntentService {

    private static final String TAG = "MyIntentService";
    private ArrayList<PersonObject> list=new ArrayList<>();

    public MyIntentService() {
        super("MyIntentService");
    }


    @Override
    protected void onHandleIntent(Intent intent) {

        //recycler=(RecyclerView)
        Log.d(TAG, "onHandleIntent: "+intent.getStringExtra("EXTRA"));

        list.add(new PersonObject("Bob",15,"Male"));
        list.add(new PersonObject("Mary",18,"Female"));
        list.add(new PersonObject("Ivy",44,"Male"));
        list.add(new PersonObject("Will",27,"Male"));
        list.add(new PersonObject("Ace",40,"Male"));
        list.add(new PersonObject("Richard",28,"Male"));
        list.add(new PersonObject("Raul",37,"Male"));

        Intent intentSend =new Intent("RECIEVE");
        intentSend.putParcelableArrayListExtra("LIST",list);
        sendBroadcast(intentSend);
        //create a list of people object
    }
}
