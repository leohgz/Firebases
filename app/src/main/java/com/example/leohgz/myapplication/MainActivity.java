package com.example.leohgz.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.multidex.MultiDex;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       // OtherClass otherClass = new OtherClass();

        MultiDex.install(getApplicationContext());
        startService(new Intent(this, MyService.class));


        String refreshedToken = FirebaseInstanceId.getInstance().getToken();

        Log.d("TAG", "token: " + refreshedToken);
    }
}
