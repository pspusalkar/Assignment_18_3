package com.example.poojajoshi.assignment_18_3;

import android.app.Notification;
import android.app.NotificationManager;
import android.icu.util.Output;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.app.Notification.InboxStyle;
import android.support.v4.app.NotificationCompat;
import android.app.PendingIntent;
import android.net.Uri;
import android.widget.Button;
import android.app.NotificationChannel;
import android.graphics.Color;

public class MainActivity extends AppCompatActivity {

    public static final String ANDROID_CHANNEL_ID = "com.example.poojajoshi.assignment_18_3.ANDROID";
    public static final String ANDROID_CHANNEL_NAME = "ANDROID CHANNEL";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // create android channel
                NotificationChannel androidChannel = new NotificationChannel(ANDROID_CHANNEL_ID, ANDROID_CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
                // Sets whether notifications posted to this channel should display notification lights
                androidChannel.enableLights(true);
                // Sets whether notification posted to this channel should vibrate.
                androidChannel.enableVibration(true);
                // Sets the notification light color for notifications posted to this channel
                androidChannel.setLightColor(Color.GREEN);
                // Sets whether notifications posted to this channel appear on the lockscreen or not
                androidChannel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://google.com"));
                PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, 0, intent, 0);

                NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this, ANDROID_CHANNEL_ID);
                NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();

                // inboxStyle.addLine("4 applications got updated");
                inboxStyle.addLine("Maps");
                inboxStyle.addLine("Whatsapp");
                inboxStyle.addLine("hotstar");
                inboxStyle.addLine("amazon");
                inboxStyle.setBigContentTitle("Update Notification");
                builder.setStyle(inboxStyle);

                builder.setSubText("4 applications got updated");
                // builder.setContentText("Maps,Whatsapp,hotstar,amazon");

                builder.setSmallIcon(R.drawable.image1);
                builder.setContentIntent(pendingIntent);
                builder.setAutoCancel(true);
                // builder.setContentTitle("Updating Notification");

                NotificationManager notificationManager = (NotificationManager) getSystemService(MainActivity.this.NOTIFICATION_SERVICE);
                notificationManager.createNotificationChannel(androidChannel);
                notificationManager.notify(102, builder.build());
            }
        });

    }

}
