package pyneer.full_time_wannabe.activity.__test;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import pyneer.full_time_wannabe.R;
import pyneer.full_time_wannabe.activity.main.LoadingActivity;
import pyneer.full_time_wannabe.utility.Constants;


public class __TestActivity extends AppCompatActivity {

    @BindView(R.id.listview_activity_test)
    ListView lv;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.__activity_test);
        ButterKnife.bind(this);

        startActivity(new Intent(this,LoadingActivity.class));

        final ArrayList<String> activites = new ArrayList<>();
        for(Class c : Constants.ACTIVITES) {
            activites.add(c.getName());
        }
        lv.setAdapter(new BaseAdapter() {

            @Override
            public int getCount() {
                return activites.size();
            }

            @Override
            public Class getItem(int position) {
                return Constants.ACTIVITES[position];
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if(convertView == null) {
                    convertView = getLayoutInflater().inflate(R.layout._test_listitem, null);
                }
                ((TextView)convertView.findViewById(R.id.text_test)).setText(getItem(position).getName());
                return convertView;
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(__TestActivity.this, Constants.ACTIVITES[position]);
                startActivity(intent);
            }
        });
    }
}
