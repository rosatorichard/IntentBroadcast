package com.batchmates.android.intentbroadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ActionMenuView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private static final String TAG = "Main Activity";
    private MyReceiver myReceiver=new MyReceiver();
    private ClassReciever classReciever=new ClassReciever();
    private IntentFilter intentFilter=new IntentFilter();
    private RecyclerView recyclerView;
    private RecyclerAdapter recyclerAdapter;
    private DefaultItemAnimator defaultItemAnimator;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<PersonObject> list;
    private HideReciever hideReciever=new HideReciever();
    private IntentFilter intentFilterTwo=new IntentFilter();


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=(RecyclerView)findViewById(R.id.recyclerList);
        defaultItemAnimator=new DefaultItemAnimator();
        layoutManager=new GridLayoutManager(this,1, LinearLayoutManager.VERTICAL,false);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setShowHideAnimationEnabled(true);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater m = getMenuInflater();
        m.inflate(R.menu.broadcastfun, menu);
        return true;
        //return super.onCreateOptionsMenu(menu);


    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId())
        {
            case R.id.fun:
                Log.d(TAG, "onOptionsItemSelected: To Fun");
                break;

            case R.id.sms:
                Intent intent =new Intent(this,SmsActivity.class);
                startActivity(intent);
                Log.d(TAG, "onOptionsItemSelected: SMS APP");
                break;

            case android.R.id.home:
                finish();
                break;

            case R.id.androidGuy:
                Intent intent1=new Intent("MONSTER");
                sendBroadcast(intent1);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
        intentFilter.addAction("com.batchmates.android.intentbroadcast");
        registerReceiver(myReceiver,intentFilter);

        IntentFilter intentFilter2=new IntentFilter();
        intentFilter2.addAction("RECIEVE");
        registerReceiver(classReciever,intentFilter2);

        intentFilterTwo.addAction("android.intent.action.AIRPLANE_MODE");
        intentFilterTwo.addAction("MONSTER");
        registerReceiver(hideReciever,intentFilterTwo);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(myReceiver);
        unregisterReceiver(classReciever);
        unregisterReceiver(hideReciever);
    }

    public void sendReciever(View view) {

        Intent intent= new Intent("com.batchmates.android.intentbroadcast");
        sendBroadcast(intent);

        Intent intent1= new Intent(this,MyIntentService.class);
        intent1.putExtra("EXTRA","SENT PROPERLY");
        startService(intent1);

    }



    public class ClassReciever extends BroadcastReceiver
    {
        @Override
        public void onReceive(Context context, Intent intent) {

            list = intent.getParcelableArrayListExtra("LIST");
            recyclerAdapter=new RecyclerAdapter(list);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setItemAnimator(defaultItemAnimator);
            recyclerView.setAdapter(recyclerAdapter);
            Log.d("Yataa", "onReceive: List Recieved"+list);

        }
    }
}
