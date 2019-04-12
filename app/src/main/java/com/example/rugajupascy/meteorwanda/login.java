package com.example.rugajupascy.meteorwanda;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class login extends AppCompatActivity {
private Button loginBtn;
private EditText username, userpass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginBtn=(Button) findViewById(R.id.login);
        username =(EditText) findViewById(R.id.username);
        userpass=(EditText) findViewById(R.id.password);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DisplayedLogin();
            }
        });
    }
    public  void DisplayedLogin(){
        StringRequest request = new StringRequest(Request.Method.POST, "http://192.168.8.102/meteo/API/login.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                       if(response.contains("1")){
                           startActivity(new Intent(getApplicationContext(),MainActivity.class));
                       }
                       else{
                           Toast.makeText(getApplicationContext(),"Wrong Username or Password",Toast.LENGTH_SHORT).show();
                       }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params= new HashMap<>();
                params.put("username",username.getText().toString());
                params.put("password",userpass.getText().toString());
                return  params;
            }
        };

        Volley.newRequestQueue(this).add(request);
    }
}
