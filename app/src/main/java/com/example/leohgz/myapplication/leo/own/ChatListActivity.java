package com.example.leohgz.myapplication.leo.own;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.leohgz.myapplication.R;
import com.example.leohgz.myapplication.adapter.Adapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class ChatListActivity extends Activity implements AdapterView.OnItemClickListener{
    private FirebaseDatabase database;
    private DatabaseReference ref;
    private String user;
    private List<String> chats;
    private ListView listView;
    private ArrayAdapter<String> adp;
    private HashMap<String,String> mapa;
    private List<Object> mapaList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_list);

        ((TextView)findViewById(R.id.textView)).setText("Chats");
        user = getIntent().getExtras().getString("chatSel");
        chats = new ArrayList<>();
        Log.d("extra: ",""+getIntent().getExtras().getString("chatSel"));
        listView = (ListView)findViewById(R.id.listView);
        mapa = new HashMap<>();
        mapaList = new ArrayList<>();

        retryChatList();
    }

    public void retryChatList(){

        database = FirebaseDatabase.getInstance();

        ref = database.getReference();

        Query q =ref.child("usrs/"+user+"/chats");

        q.addListenerForSingleValueEvent(new ValueEventListener() {
            @SuppressLint("LongLogTag")
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                Log.d("___________________________________________________", "____________________________________");

                Log.d("key: ", dataSnapshot.getKey());

                Iterator<DataSnapshot> iterator=dataSnapshot.getChildren().iterator();

                while(iterator.hasNext()){

                    DataSnapshot next =iterator.next();

                    mapa= (HashMap<String,String>)next.getValue();

                    chats.add(next.getKey());

                    mapaList.add(mapa);

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

        adp = new Adapter(getApplicationContext(),R.layout.row_layout,chats);
        listView.setAdapter(adp);
        listView.setOnItemClickListener(this);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(getApplicationContext(),ChatActivity.class);
        intent.putExtra("Chat",chats.get(position));
        intent.putExtra("User",user);
        HashMap<String,String> mapa = new HashMap<>();
        mapa =(HashMap<String,String>)mapaList.get(position);
        intent.putExtra("ChatId",mapa.get("chat_id"));
        startActivity(intent);

    }
}
