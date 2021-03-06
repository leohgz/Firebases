package com.example.leohgz.myapplication;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.multidex.MultiDex;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.leohgz.myapplication.adapter.Adapter;
import com.example.leohgz.myapplication.leo.own.ChatListActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends Activity implements AdapterView.OnItemClickListener{

    private FirebaseDatabase database;
    private DatabaseReference ref;
    private List<String> userList;
    private ListView listView;
    private ArrayAdapter<String> adp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView)findViewById(R.id.listView);

        // OtherClass otherClass = new OtherClass();

        MultiDex.install(getApplicationContext());
        startService(new Intent(this, MyService.class));

        String token = FirebaseInstanceId.getInstance().getToken();
        Log.i("", "FCM Registration Token: " + token);

        userList = new ArrayList<>();

        retryUserList();



    }

    public void retryUserList(){

        database = FirebaseDatabase.getInstance();

        ref = database.getReference();

        Query q =ref.child("usrs");

        q.addListenerForSingleValueEvent(new ValueEventListener() {
            @SuppressLint("LongLogTag")
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                Log.d("___________________________________________________", "____________________________________");

                Log.d("key: ", dataSnapshot.getKey());

                Iterator<DataSnapshot> iterator=dataSnapshot.getChildren().iterator();

                while(iterator.hasNext()){

                    DataSnapshot next =iterator.next();


                    userList.add(next.getKey());

                    Log.d("next: ",""+next.getKey());
                }
                setListView();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void setListView(){

        adp = new Adapter(getApplicationContext(),R.layout.row_layout,userList);
        listView.setAdapter(adp);
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Log.d("TAG: ","onItemClick position "+position);

        Intent intent = new Intent(getApplicationContext(), ChatListActivity.class);
        intent.putExtra("chatSel",userList.get(position));
        startActivity(intent);

        String refreshedToken = FirebaseInstanceId.getInstance().getToken();

        Log.d("TAG", "token: " + refreshedToken);
    }
}
