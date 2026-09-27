package com.example.elderguard.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;
import android.net.Uri;

public class BatteryReceiver extends BroadcastReceiver {
    private boolean sent = false;
    @Override
    public void onReceive(Context c, Intent i) {
        int pct = i.getIntExtra(BatteryManager.EXTRA_LEVEL, -1) * 100 / i.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
        if (pct < 50 && !sent) {
            sent = true;
            Intent w = new Intent(Intent.ACTION_VIEW, Uri.parse("https://api.whatsapp.com/send?phone=YOUR_NUMBER&text=Battery_Low_50_Percent"));
            w.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            c.startActivity(w);
        } else if (pct >= 50) sent = false;
    }
}