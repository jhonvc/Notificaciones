package com.example.tecsup.myapplication;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    NotificationManager notificationManager;
    NotificationReceiver not;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        not=new NotificationReceiver();
        registerReceiver(not,new IntentFilter("com.android.example.notificacionesdemo.ACTION_UPDATE_NOTIFICATION"));
        CrearCanalNotificaciones();
        CrearNotificacion();
    }
    void CrearCanalNotificaciones() {
        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        NotificationChannel notificationChannel = new NotificationChannel("notification1", "Codigo Notification 2",
                NotificationManager.IMPORTANCE_DEFAULT);
        notificationChannel.enableLights(true);
        notificationChannel.setLightColor(Color.RED);
        notificationChannel.enableVibration(true);
        notificationChannel.setDescription("Notificacion codigo");


        notificationManager.createNotificationChannel(notificationChannel);
      }

    void CrearNotificacion(){
        Intent updateIntent=new Intent("com.android.example.notificacionesdemo.ACTION_UPDATE_NOTIFICATION");
        PendingIntent updatePedingIntent=PendingIntent.getBroadcast(this,
                0,updateIntent,PendingIntent.FLAG_ONE_SHOT);

        NotificationCompat.Builder builder=new NotificationCompat.Builder(this,"notification1")
                .setContentTitle("Primera notificacion")
                .setContentText("Notification codigo")
                .setSmallIcon(R.drawable.ic_launcher_background);
        builder.addAction(new NotificationCompat.Action(R.drawable.ic_launcher_background,"yara",updatePedingIntent));
        notificationManager.notify(0,builder.build());
    }
    public  class NotificationReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context,"rataaaa",Toast.LENGTH_SHORT).show();

        }
    }

}
