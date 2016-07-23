package com.example.leohgz.myapplication.leo.own;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.leohgz.myapplication.Chat.Chat;
import com.example.leohgz.myapplication.R;
import com.example.leohgz.myapplication.adapter.AdapterChat;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ChatActivity extends Activity {
    private String chat, chat_id;
    private FirebaseDatabase database;
    private DatabaseReference ref;
    private String user;
    private List<Chat> chats;
    private ListView listView;
    private ArrayAdapter<String> adp;
    private EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        chat=getIntent().getExtras().getString("Chat");
        chat_id=getIntent().getExtras().getString("ChatId");
        user=getIntent().getExtras().getString("User");
        listView = (ListView)findViewById(R.id.listView2);
        chats = new ArrayList<>();
        ((TextView)findViewById(R.id.textView2)).setText(chat_id);
        editText=(EditText)findViewById(R.id.chat_text_entry_user);
        setChat();
    }

    public void setChat(){

        database = FirebaseDatabase.getInstance();

        ref = database.getReference();

        Query q =ref.child("chats/"+chat_id).orderByKey();

        q.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                chats.clear();

                Iterator<DataSnapshot> iterator = dataSnapshot.getChildren().iterator();

                while (iterator.hasNext()){

                    DataSnapshot dataSnapshot1 =iterator.next();

                    Chat chat =dataSnapshot1.getValue(Chat.class);
                    chat.setChatUser(user);

                    chats.add(chat);

                    Log.e("","");
                }

                setListView();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }


    public void sendChatText(String text){
        Chat chat= new Chat();
        chat.setText(text);
        chat.setUser_id((long) 233445555);
        ref.child("chats/"+chat_id).push().setValue(chat);
    }

    public void setListView(){

        AdapterChat adapterChat = new AdapterChat(chats,getApplicationContext());
        listView.setAdapter(adapterChat);


    }


    public void handleClick(View v){

        Log.d("click","click");

        String texto = editText.getText().toString();

        sendChatText(texto);
    }
}
