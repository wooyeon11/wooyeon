package pyneer.full_time_wannabe.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pyneer.full_time_wannabe.R;
import pyneer.full_time_wannabe.adapter.FriendListAdapter;
import pyneer.full_time_wannabe.model.FriendData;

/**
 * Created by ddjdd on 2018-11-27.
 */

public class FriendListFragment extends Fragment {
    View rootView;

    FriendListAdapter adapter;

    private FirebaseDatabase fbDB = FirebaseDatabase.getInstance();
    private DatabaseReference dbRef = fbDB.getReference();


    // ********************************************************************************
                String userName = "pyneer"; // Temp
    // ********************************************************************************


    @BindView(R.id.lv_friend) ListView lv_friend;
    @BindView(R.id.fab_add_friend) FloatingActionButton fab_add_friend;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        if (rootView != null) return rootView;
        rootView = inflater.inflate(R.layout.fragment_friend_list, container, false);
        ButterKnife.bind(this, rootView);

        openFriendList();

        return rootView;
    }

    private void addFriend(DataSnapshot dataSnapshot, FriendListAdapter adapter) {
        FriendData friendData = dataSnapshot.getValue(FriendData.class);
        adapter.add(friendData);
    }

    private void openFriendList() {
        adapter = new FriendListAdapter();
        lv_friend.setAdapter(adapter);

        dbRef.child("friend").child(userName).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                addFriend(dataSnapshot, adapter);
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

    @OnClick(R.id.fab_add_friend)
    public void onFabAddFriendClick () {
        FriendData newFriend = new FriendData("최진호");
        dbRef.child("friend").child(userName).push().setValue(newFriend);
        Toast.makeText(getActivity(), "친구 추가됨", Toast.LENGTH_LONG).show();
    }
}
