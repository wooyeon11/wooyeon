package pyneer.full_time_wannabe.activity.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pyneer.full_time_wannabe.R;
import pyneer.full_time_wannabe.api.OnRestApiListener;
import pyneer.full_time_wannabe.api.RestApiResult;
import pyneer.full_time_wannabe.api.RestApiTask;
import pyneer.full_time_wannabe.api.implement.Signup;

import android.os.AsyncTask;


import org.json.JSONObject;

import java.io.BufferedReader;

import java.io.BufferedWriter;

import java.io.IOException;

import java.io.InputStream;

import java.io.InputStreamReader;

import java.io.OutputStream;

import java.io.OutputStreamWriter;

import java.net.HttpURLConnection;

import java.net.MalformedURLException;

import java.net.URL;
/**
 * Log_in activity
 */

public class LoginActivity extends AppCompatActivity implements OnRestApiListener {

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

    @OnClick(R.id.btn_login)
    public void onClickLogin() {
        String email, password;
        email = ed_email.getText().toString();
        password = ed_password.getText().toString();

        // 로그인 검증 시도
        if (email.length() < 5 || password.length() < 6) {
            Toast.makeText(this, "Fill all", Toast.LENGTH_LONG).show();
            // 메소드화 필요
        } else {
            // 로그인 검증 완료시 행동 추가
            stringTomobileServer(email,password);

        }

        //email & password 서버로 보내기

    }





    @OnClick(R.id.btn_signup)
    public void onBtnSignup() {
        // 회원가입용 다이얼로그 생성
        MaterialDialog dialog =
                new MaterialDialog.Builder(this)
                        .title("가입")
                        .customView(R.layout.dialog_signup, true)
                        .positiveText("가입하기")
                        .negativeText(android.R.string.cancel)
                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
//                                doRegister(dialog.getCustomView());
                            }
                        })
                        .build();
        dialog.show();

    }

    /*
     사용자 타입 구분 필요
     알바생(model name : user) vs. 관리자
        추가 - 버튼, 회원가입, 관리자 모델
     */


//    private void doRegister(View registerView) {
////        String id = ((EditText) registerView.findViewById(R.id.ed_signup_id)).getText().toString();
////        String pw = ((EditText) registerView.findViewById(R.id.ed_signup_password)).getText().toString();
////        String name = ((EditText) registerView.findViewById(R.id.ed_signup_name)).getText().toString();
//        Signup register = new Signup();
//        register.setEmail(id);
//        register.setPw(pw);
//        register.setName(name);
//        new RestApiTask(this).execute(register);
//    }

    @Override
    public void onRestApiDone(RestApiResult restApiResult) {
        switch (restApiResult.getApiName()) {
//            case "login":
//                break;
            case "register":
                new MaterialDialog.Builder(this).content("가입이 완료되었습니다.").show();
                break;
        }
    }

    public class registDB extends AsyncTask<Void, Integer, Void>{
        @Override
        protected void doInBackground(Void... unused) {
            String param = "email" + email + "password" + password + "";
            try {
                URL url = new URL("http://127.0.0.1:3000");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            }
        }
}



