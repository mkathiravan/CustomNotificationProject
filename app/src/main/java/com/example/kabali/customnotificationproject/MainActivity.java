package com.example.kabali.customnotificationproject;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RemoteViews;

import static com.example.kabali.customnotificationproject.App.CHANNEL_ID;

public class MainActivity extends AppCompatActivity {
    private NotificationManagerCompat notificationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        notificationManager = NotificationManagerCompat.from(this);
    }

    public void showNotification(View view)
    {
        Notification notification = new NotificationCompat.Builder(this,CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("Title").setContentText("This is a default notification").build();


        notificationManager.notify(1,notification);
    }

    public void expandNotificaion(View view)
    {
        RemoteViews collapsedView = new RemoteViews(getPackageName(),R.layout.notification_collapsed);
        RemoteViews expandView = new RemoteViews(getPackageName(),R.layout.notification_expand);

        //This is to get the view from the remote view layout and setit for dynamaically.
        collapsedView.setTextViewText(R.id.text_view_collapsed1,"Hello Notification");
        expandView.setImageViewResource(R.id.image_view_expanded,R.drawable.chew);

        Notification notification = new NotificationCompat.Builder(this,CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setCustomContentView(collapsedView)
                .setCustomBigContentView(expandView)
                .setStyle(new NotificationCompat.DecoratedCustomViewStyle()).build();


        notificationManager.notify(1,notification);
    }

    public void imageClickNotification(View view)
    {

        RemoteViews collapsedView = new RemoteViews(getPackageName(),R.layout.notification_collapsed);
        RemoteViews expandView = new RemoteViews(getPackageName(),R.layout.notification_expand);

        Intent clickIntent = new Intent(this,NotificationReceiver.class);
        PendingIntent clickpendingIntent = PendingIntent.getBroadcast(this,0,clickIntent,0);

        //This is to get the view from the remote view layout and setit for dynamaically.
        collapsedView.setTextViewText(R.id.text_view_collapsed1,"Hello Notification");
        expandView.setImageViewResource(R.id.image_view_expanded,R.drawable.chew);

        //If you want to click any listener perform we have to use only the pending intent via broadcast.
        expandView.setOnClickPendingIntent(R.id.image_view_expanded,clickpendingIntent);

        Notification notification = new NotificationCompat.Builder(this,CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setCustomContentView(collapsedView)
                .setCustomBigContentView(expandView)
                .setStyle(new NotificationCompat.DecoratedCustomViewStyle()).build();


        notificationManager.notify(1,notification);
    }
}
