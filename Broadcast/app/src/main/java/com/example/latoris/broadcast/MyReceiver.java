package com.example.latoris.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {
    public MyReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        String strMsg=intent.getStringExtra("broadcastmsg");
        Toast.makeText(context,strMsg, Toast.LENGTH_LONG).show();

        throw new UnsupportedOperationException("Not yet implemented");
    }
}
