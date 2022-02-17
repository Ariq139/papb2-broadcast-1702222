package com.example.broadcastproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private CustomReceiver mCustomReceiver = new CustomReceiver();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_POWER_CONNECTED);
        intentFilter.addAction(Intent.ACTION_POWER_DISCONNECTED);

        registerReceiver(mCustomReceiver, intentFilter);

        View sendBtn = findViewById(R.id.send_btn);
        sendBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent ("ACTION_CUSTOM_BROADCAST");
                intent.putExtra("Data", "extra data");
                LocalBroadcastManager.getInstance(view.getContext()).sendBroadcast(intent);
            }
        });

        IntentFilter intentFilter1 = new IntentFilter("ACTION_CUSTOM_BROADCAST");
        LocalBroadcastManager.getInstance(this).registerReceiver(mCustomReceiver, intentFilter1);
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        unregisterReceiver(mCustomReceiver);
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mCustomReceiver);
    }
}