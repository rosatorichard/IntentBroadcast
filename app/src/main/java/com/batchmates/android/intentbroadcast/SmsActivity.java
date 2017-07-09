package com.batchmates.android.intentbroadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.zip.Inflater;

public class SmsActivity extends AppCompatActivity {


    private TextInputEditText phone,message;
    private TextView recieved;
    private RecievedSMS smsReciever=new RecievedSMS();
    private IntentFilter intentFilter=new IntentFilter();
    final SmsManager sms = SmsManager.getDefault();
    private HideReciever hideReciever=new HideReciever();
    private IntentFilter intentFilterTwo=new IntentFilter();
    private BottomNavigationView bottomView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);

        phone=(TextInputEditText)findViewById(R.id.phoneNumber);
        message=(TextInputEditText)findViewById(R.id.messageToSend);
        recieved=(TextView)findViewById(R.id.recievedText);
        bottomView=(BottomNavigationView)findViewById(R.id.bottomNavigation);


        bottomView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.button1:
                        Toast.makeText(getApplicationContext(),"YAY BUTTON 1",Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent();
                        intent.setAction("orderedPair");
                        sendOrderedBroadcast(intent,null);
                        break;


                    case R.id.button2:
                        Toast.makeText(getApplicationContext(),"YAY BUTTON 2",Toast.LENGTH_SHORT).show();
                        break;


                    case R.id.button3:
                        Toast.makeText(getApplicationContext(),"YAY BUTTON 3",Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });

    }

    public void snackBarShower(View view) {

        Snackbar.make(view,"This is SMS",Snackbar.LENGTH_SHORT).show();
    }

    public void sendSMS(View view) {

        SmsManager smsManager=SmsManager.getDefault();

        smsManager.sendTextMessage(String.valueOf(phone.getText()),null,String.valueOf(message.getText()),null,null);
    }


    @Override
    protected void onStart() {
        super.onStart();
        intentFilter.addAction("android.provider.Telephony.SMS_RECEIVED");
        registerReceiver(smsReciever,intentFilter);
        intentFilterTwo.addAction("android.intent.action.AIRPLANE_MODE");
        intentFilterTwo.addAction("MONSTER");
        registerReceiver(hideReciever,intentFilterTwo);
    }


    @Override
    protected void onStop() {
        super.onStop();

        unregisterReceiver(smsReciever);
        unregisterReceiver(hideReciever);
    }

    public void showTheLove(View view) {

        Toast.makeText(this,"We Love Android",Toast.LENGTH_SHORT).show();
    }







    public class RecievedSMS extends BroadcastReceiver
    {

        @Override
        public void onReceive(Context context, Intent intent) {

            Toast.makeText(context,"Message Recieved",Toast.LENGTH_SHORT).show();
            Bundle bundle= intent.getExtras();
            if(bundle!=null)
            {
                Object[] sms = (Object[]) bundle.get("pdus");

                String smsMessageStr = "";
                for (int i = 0; i < sms.length; ++i) {
                    SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) sms[i]);

                    String smsBody = smsMessage.getMessageBody().toString();
                    String address = smsMessage.getOriginatingAddress();

                    smsMessageStr += "SMS From: " + address + "\n";
                    smsMessageStr += smsBody + "\n";
                }
                recieved.setText(smsMessageStr);
            }

        }
    }


}
