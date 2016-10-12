package com.naxesa.a0903;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class LogInActivity extends AppCompatActivity {

    // View
    private EditText inputEmail, inputPassword;
    private Button btnLogIn, btnSignUp;

    private JSONObject object;

    private String id;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        // View Reference
        inputEmail = (EditText)findViewById(R.id.email);
        inputPassword = (EditText)findViewById(R.id.password);
        btnLogIn = (Button)findViewById(R.id.log_in);
        btnSignUp = (Button)findViewById(R.id.sign_up);

        // View Event
        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        boolean isSuccess = false;
                        try {
                            object = new JSONObject();
                            object.put("Command", 112);
                            object.put("Id", id);
                            object.put("password", password);
                            JSONObject result = Connect.postData(object);
                            isSuccess = result.getBoolean("Command");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        final boolean finalIsSuccess = isSuccess;
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if(finalIsSuccess){
                                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                    intent.putExtra("Id", id);
                                    startActivity(intent);
                                }else{
                                    Toast.makeText(LogInActivity.this, "실패", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                }).start();
            }
        });
    }
}
