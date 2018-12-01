package pyneer.full_time_wannabe.activity.main;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pyneer.full_time_wannabe.R;
import pyneer.full_time_wannabe.api.OnRestApiListener;
import pyneer.full_time_wannabe.api.RestApiResult;
import pyneer.full_time_wannabe.api.RestApiTask;
import pyneer.full_time_wannabe.api.implement.Signup;


public class NaviActivity extends AppCompatActivity  {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navi);
//버튼 연결
        Button button = (Button) findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}


