package pyneer.full_time_wannabe.activity.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

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
import pyneer.full_time_wannabe.adapter.ChatAdapter;
import pyneer.full_time_wannabe.model.ChatData;
import pyneer.full_time_wannabe.model.ChatListData;

/**
 * Created by ddjdd on 2018-11-04.
 */

public class ChatActivity extends AppCompatActivity {
    private String CHAT_ID;
    private String CURRENT_USER_NAME, CURRENT_USER_ID;
    private static final int ITEM_VIEW_TYPE_MYCHAT = 0;
    private static final int ITEM_VIEW_TYPE_OTHERCHAT = 1;
    private static final int ITEM_VIEW_TYPE_OTHERCHAT_SERIES = 2;

    @BindView(R.id.lv_chat) ListView lv_chat;
    @BindView(R.id.ed_chat) EditText ed_chat;
    @BindView(R.id.btn_send) ImageButton btn_send;
    @BindView(R.id.toolbar) Toolbar toolbar;

    private FirebaseDatabase fbDB = FirebaseDatabase.getInstance();
    private DatabaseReference dbRef = fbDB.getReference();

    ChatAdapter adapter;
    ChatListData chatListData;
    ActionBar actionBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        ButterKnife.bind(this);

        CURRENT_USER_NAME = "pyneer";
        CURRENT_USER_ID = "pyneer";

        Intent intent = new Intent(this.getIntent());
        chatListData = (ChatListData) intent.getSerializableExtra("chat_list_data");
        CHAT_ID = chatListData.getChatID();
        setToolBar(CHAT_ID);
        setChatListener(CHAT_ID);
    }

    private void setToolBar(String title) {
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true); //커스터마이징 하기 위해 필요
        actionBar.setDisplayHomeAsUpEnabled(true); // 뒤로가기 버튼, 디폴트로 true만 해도 백버튼이 생김
        actionBar.setTitle(title);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.chat_menu, menu);
        return true;
    }

    // ToolBar 에 추가된 항목의 select 이벤트를 처리하는 함수
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.action_settings1:
                // User chose the "Settings" item, show the app settings UI...
                Toast.makeText(getApplicationContext(), "1번 메뉴 클릭", Toast.LENGTH_LONG).show();
                return true;
            case R.id.action_settings2:
                // User chose the "Settings" item, show the app settings UI...
                Toast.makeText(getApplicationContext(), "2번 메뉴 클릭", Toast.LENGTH_LONG).show();
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
        }
    }

    private String tmp = "";
    private void addChat(DataSnapshot dataSnapshot, ChatAdapter adapter) {
        ChatData chatData = dataSnapshot.getValue(ChatData.class);
        // 내 chat 인지 확인
        String tUserName = chatData.getUserName();
        if(tUserName.equals(CURRENT_USER_NAME)) {
            chatData.setType(ITEM_VIEW_TYPE_MYCHAT);
            tmp = tUserName;
        }
        else if (tUserName.equals(tmp)) {
            chatData.setType(ITEM_VIEW_TYPE_OTHERCHAT_SERIES);
        }
        else {
            chatData.setType(ITEM_VIEW_TYPE_OTHERCHAT);
            tmp = tUserName;
        }
        if(chatData != null) {
            adapter.add(chatData);
        }
    }

    // 채팅방 열었을때 내용 표시
    private void setChatListener(String chatName) {
        adapter = new ChatAdapter();
        lv_chat.setAdapter(adapter);

        // 데이터 받아오기 및 어댑터 데이터 추가 및 삭제 등
        dbRef.child("chat").child(chatName).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                addChat(dataSnapshot, adapter);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                // 메시지 변경 없음.
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

    @OnClick(R.id.btn_send)
    public void onBtnSendClick() {
        if (ed_chat.getText().toString().equals("")) return;
        ChatData chat = new ChatData(CURRENT_USER_NAME, CURRENT_USER_ID, ed_chat.getText().toString(), ServerValue.TIMESTAMP); //ChatDTO를 이용하여 데이터를 묶는다.
        dbRef.child("chat").child(CHAT_ID).push().setValue(chat); // 데이터 푸쉬
        ed_chat.setText(""); //입력창 초기화
    }

}