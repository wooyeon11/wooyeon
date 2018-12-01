package pyneer.full_time_wannabe.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import pyneer.full_time_wannabe.R;
import pyneer.full_time_wannabe.activity.main.LoadingActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startActivity(new Intent(this,LoadingActivity.class));

    }
}
