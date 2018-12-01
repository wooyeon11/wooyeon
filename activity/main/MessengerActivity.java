package pyneer.full_time_wannabe.activity.main;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import butterknife.BindView;
import butterknife.ButterKnife;
import pyneer.full_time_wannabe.R;
import pyneer.full_time_wannabe.adapter.FriendListAdapter;
import pyneer.full_time_wannabe.adapter.ScreenSlidePagerAdapter;
import pyneer.full_time_wannabe.fragment.ChatListFragment;
import pyneer.full_time_wannabe.fragment.FriendListFragment;
import pyneer.full_time_wannabe.fragment.MainFragment;

/**
 * Created by ddjdd on 2018-11-22.
 */
public class MessengerActivity extends AppCompatActivity implements ActivityCompat.OnRequestPermissionsResultCallback {
    @BindView(R.id.tab_container) ViewPager viewPager;
    @BindView(R.id.navigation) BottomNavigationView bottomNavigationView;

    FriendListFragment friendListFragment;
    ChatListFragment chatListFragment;
    ScreenSlidePagerAdapter screenSlidePagerAdapter;
    MenuItem prevMenu;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messenger);
        ButterKnife.bind(this);
        viewPager.addOnPageChangeListener(onPageChangeListener);
        bottomNavigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);
        setViewPager(viewPager);
    }

    public void requestPermission(){
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
        }
        else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode){
            case 1:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                }
        }
    }

    private void setViewPager(ViewPager viewPager){
        friendListFragment = new FriendListFragment();
        chatListFragment = new ChatListFragment();
        screenSlidePagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        screenSlidePagerAdapter.addFragment(friendListFragment);
        screenSlidePagerAdapter.addFragment(chatListFragment);
        viewPager.setOffscreenPageLimit(2);
        viewPager.setAdapter(screenSlidePagerAdapter);
    }

    ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            if (prevMenu != null){
                prevMenu.setChecked(false);
            }
            else {
                bottomNavigationView.getMenu().getItem(0).setChecked(false);
            }
            bottomNavigationView.getMenu().getItem(position).setChecked(true);
            prevMenu = bottomNavigationView.getMenu().getItem(position);

            if (position == 1) {
                screenSlidePagerAdapter.getItem(1).onResume();
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()){
                case R.id.tab1:
                    viewPager.setCurrentItem(0);
                    break;
                case R.id.tab2:
                    viewPager.setCurrentItem(1);
                    break;
            }
            return true;
        }
    };
}