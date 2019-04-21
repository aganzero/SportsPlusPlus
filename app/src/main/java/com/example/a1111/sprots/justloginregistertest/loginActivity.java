package com.example.a1111.sprots.justloginregistertest;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a1111.sprots.NaviActivity;
import com.example.a1111.sprots.R;
import com.example.a1111.sprots.Http;

import org.json.JSONArray;
import org.json.JSONException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class loginActivity extends AppCompatActivity {
    private int Result_log;
    private String username, password;

    private void Login(){
        Http<JSONArray> http2 = new Http<>();
        http2.setListener(new Http.OnResponseListener<JSONArray>() {
            @Override
            public void onResponse(JSONArray user) throws JSONException {
                System.out.println("result:"+user.getJSONObject(0).get("result"));
                Result_log =  Integer.parseInt(String.valueOf(user.getJSONObject(0).get("result")));
                if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password)) {
                    if(Result_log == 1){
                        Toast.makeText(loginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(loginActivity.this, NaviActivity.class);
                        startActivity(intent);
                        finish();
                    }else{
                        Toast.makeText(loginActivity.this, "用户名或密码不正确", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(loginActivity.this, "请输入你的用户名或密码", Toast.LENGTH_SHORT).show();
                }

            }
        });
        http2.execute("Login",username,password);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @BindView(R.id.tv_loginactivity_register)
    TextView mTvLoginactivityRegister;
    @BindView(R.id.rl_loginactivity_top)
    RelativeLayout mRlLoginactivityTop;
    @BindView(R.id.et_loginactivity_username)
    EditText mEtLoginactivityUsername;
    @BindView(R.id.et_loginactivity_password)
    EditText mEtLoginactivityPassword;
    @BindView(R.id.ll_loginactivity_two)
    LinearLayout mLlLoginactivityTwo;
    @OnClick({
            R.id.tv_loginactivity_register,
            R.id.bt_loginactivity_login,
    })

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_loginactivity_register:
                startActivity(new Intent(this, RegisterActivity.class));
                finish();
                break;
            case R.id.bt_loginactivity_login:
                username = mEtLoginactivityUsername.getText().toString().trim();
                password = mEtLoginactivityPassword.getText().toString().trim();
                Login();
                break;
        }
    }
}


