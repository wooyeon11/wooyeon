package pyneer.full_time_wannabe.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pyneer.full_time_wannabe.R;
import pyneer.full_time_wannabe.activity.main.ChatActivity;
import pyneer.full_time_wannabe.adapter.ChatListAdapter;
import pyneer.full_time_wannabe.model.ChatListData;

/**
 * Created by ddjdd on 2018-11-27.
 */

public class ChatListFragment extends Fragment {
    View rootView;

    ChatListAdapter adapter;

    private FirebaseDatabase fbDB = FirebaseDatabase.getInstance();
    private DatabaseReference dbRef = fbDB.getReference();


    // ********************************************************************************
    // Temp
    // ********************************************************************************
    String CURRENT_USER_NAME = "pyneer";
    // ********************************************************************************


    @BindView(R.id.lv_chat_list) ListView lv_chat_list;
    @BindView(R.id.fab_add_chat) FloatingActionButton fab_add_chat;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        if (rootView != null) return rootView;
        rootView = inflater.inflate(R.layout.fragment_chat_list, container, false);
        ButterKnife.bind(this, rootView);

        openChatList();

        lv_chat_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Intent intent = new Intent(getActivity(), ChatActivity.class);
                ChatListData intentChatListData = (ChatListData) adapter.getItem(position);

                intent.putExtra("chat_list_data", intentChatListData);
                getActivity().startActivityForResult(intent, 1);
            }

            public void onClick(View v) {
            }
        });

        return rootView;
    }

    private void addChatList(DataSnapshot dataSnapshot, ChatListAdapter adapter) {
        ChatListData chatListData = dataSnapshot.getValue(ChatListData.class);
        adapter.add(chatListData);
    }

    private void openChatList() {
        adapter = new ChatListAdapter();
        lv_chat_list.setAdapter(adapter);

        dbRef.child("chat_list").child(CURRENT_USER_NAME).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                addChatList(dataSnapshot, adapter);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @OnClick(R.id.fab_add_chat)
    public void onFabAddChatClick () {
        String key = dbRef.child("chat_list").child(CURRENT_USER_NAME).push().getKey();
        ChatListData newChat = new ChatListData("Test", "@make@"+CURRENT_USER_NAME+"@key@"+key, "@"+CURRENT_USER_NAME, ServerValue.TIMESTAMP);
        dbRef.child("chat_list").child(CURRENT_USER_NAME).child(newChat.getChatID()).setValue(newChat);
        Toast.makeText(getActivity(), "채팅 추가됨", Toast.LENGTH_LONG).show();
    }
}


