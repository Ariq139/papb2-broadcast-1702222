package com.example.broadcastproject;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class CustomReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent){
        String msg = "default";
        switch (intent.getAction()){
            case Intent.ACTION_POWER_CONNECTED:
                msg = "power connected";
                break;
            case Intent.ACTION_POWER_DISCONNECTED:
                msg = "power disconnected";
                break;
            case "ACTION_CUSTOM_BROADCAST":
                msg = intent.getStringExtra("Data");
            default:
                break;
        }
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
}