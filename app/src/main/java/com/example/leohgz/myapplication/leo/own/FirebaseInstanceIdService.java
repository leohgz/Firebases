package com.example.leohgz.myapplication.leo.own;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;

/**
 * Created by Leonardo on 19/7/2016.
 */
public class FirebaseInstanceIdService extends com.google.firebase.iid.FirebaseInstanceIdService {

    @Override
    public void onTokenRefresh() {

        super.onTokenRefresh();

        String refreshedToken = FirebaseInstanceId.getInstance().getToken();

        Log.d("TAG", "Refreshed token: " + refreshedToken);

    }
}
