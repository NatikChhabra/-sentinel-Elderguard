package com.example.elderguard.services;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.IBinder;
import com.example.elderguard.receivers.BatteryReceiver;

public class MonitorService extends Service implements SensorEventListener {
    private SensorManager sm;
    private BatteryReceiver br;

    @Override
    public void onCreate() {
        super.onCreate();
        NotificationChannel c = new NotificationChannel("eg", "Guard", NotificationManager.IMPORTANCE_LOW);
        getSystemService(NotificationManager.class).createNotificationChannel(c);
        Notification n = new Notification.Builder(this, "eg")
                .setContentTitle("ElderGuard Active")
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .build();
        startForeground(1, n);
        sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        sm.registerListener(this, sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
        br = new BatteryReceiver();
        registerReceiver(br, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
    }

    @Override
    public void onSensorChanged(SensorEvent e) {
        double g = Math.sqrt(e.values[0]*e.values[0] + e.values[1]*e.values[1] + e.values[2]*e.values[2]);
        if (g > 35) wa("Emergency: Fall Detected!");
    }

    private void wa(String m) {
        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://api.whatsapp.com/send?phone=YOUR_NUMBER&text=" + m));
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
    }

    @Override public void onAccuracyChanged(Sensor s, int a) {}
    @Override public IBinder onBind(Intent i) { return null; }
}