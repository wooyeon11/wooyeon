package pyneer.full_time_wannabe.activity.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pyneer.full_time_wannabe.R;

public class makeActivity extends AppCompatActivity {
    @BindView(R.id.ed_email)
    EditText ed_email;
    @BindView(R.id.ed_password)
    EditText ed_password;
    @BindView(R.id.btn_login)

    Button btn_login;
    @BindView(R.id.btn_signup)
    Button btn_signup;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }
}
