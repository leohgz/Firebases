package com.example.leohgz.myapplication.leo.own;

import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by Leonardo on 19/7/2016.
 */
public class MyFirebaseMessagingService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        int b=10;

        System.out.println(b);

        Log.d("DEBUG",remoteMessage.getFrom());
    }


}
