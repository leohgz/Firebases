package com.example.leohgz.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.multidex.MultiDex;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       // OtherClass otherClass = new OtherClass();

        MultiDex.install(getApplicationContext());
        startService(new Intent(this, MyService.class));


    }
}
