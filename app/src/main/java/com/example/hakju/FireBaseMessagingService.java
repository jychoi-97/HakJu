package com.example.hakju;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class FireBaseMessagingService extends FirebaseMessagingService {
    private static final String TAG = "MyFirebaseMsgService";


    @Override
    public void onNewToken(String s){
//        super.onNewToken(s);
        Log.d(TAG,"Refreshed token: "+s);

        sendRegistrationToServer(s);
    }
    private void sendRegistrationToServer(String s){

    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage){
        Log.d(TAG,"From: "+remoteMessage.getFrom());
        if(remoteMessage.getData().size()>0){
            Log.d(TAG,"Message data payload: "+remoteMessage.getData());
            if(true){
            } else {
                handleNow();
            }
        }
        if(remoteMessage.getNotification() !=null){
            Log.d(TAG,"Message Notification Body: "+remoteMessage.getNotification().getBody());

        }
        sendNotification(remoteMessage.getNotification().getBody());
    }

    private void handleNow(){
        Log.d(TAG,"Short lived task is done");
    }

    private void sendNotification(String messageBody){
        Intent intent = new Intent(this,MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_ONE_SHOT);

        String channelId = getString(R.string.default_notification_channel_id);
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        long[] vibrate = {0,100,200,300};

        NotificationCompat.Builder notificationBuilder= new NotificationCompat.Builder(this,channelId)
                .setSmallIcon(R.mipmap.ic_launcher).setContentTitle("FCM Message").setContentText(messageBody)
                .setAutoCancel(true).setSound(defaultSoundUri).setVibrate(vibrate).setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            String channelName = getString(R.string.default_notification_channel_name);

            NotificationChannel channel = new NotificationChannel(channelId,channelName,NotificationManager.IMPORTANCE_HIGH);

            notificationManager.createNotificationChannel(channel);
        }
        notificationManager.notify(1,notificationBuilder.build());
    }

}

