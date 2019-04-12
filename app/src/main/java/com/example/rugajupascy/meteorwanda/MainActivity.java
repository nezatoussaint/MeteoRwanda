package com.example.rugajupascy.meteorwanda;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.app.ProgressDialog;
import android.widget.EditText;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import android.os.AsyncTask;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, SensorEventListener {
    private Button ambientBtn, lightBtn, pressureBtn, humidityBtn;
    private TextView ambientValue, lightValue, pressureValue, humidityValue;
    private TextView[] valueFields = new TextView[4];
    private final int AMBIENT = 0;
    private final int LIGHT = 1;
    private final int PRESSURE = 2;
    private final int HUMIDITY = 3;
    EditText StudentName, StudentPhoneNumber, StudentClass;
    Button RegisterStudent, ShowStudents;
    String StudentNameHolder, StudentPhoneHolder, StudentClassHolder;
    Boolean CheckEditText ;
    ProgressDialog progressDialog;
    String finalResult ;
    HashMap<String,String> hashMap = new HashMap<>();
    HttpParse httpParse = new HttpParse();
    String HttpURL = "http://192.168.8.102/meteo/crud/StudentRegister.php";


    private SensorManager senseManage;
    private Sensor envSense;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//               Toolbar toolbar = (Toolbar) findViewById(R.id.mytoolbar);
//        toolbar.setTitle("Meteo Rwanda");
//        toolbar.inflateMenu(R.menu.main_menu);
//        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(MenuItem menuItem) {
//                String title = (String) menuItem.getTitle();
//                switch (menuItem.getItemId()){
//                    case R.id.login:
//                        startActivity(new Intent(getApplicationContext(),login.class));
//                        break;
//                    case R.id.exit:
//                        finish();
//                }
//                return true;
//            }
//        });

//        ambientBtn = (Button)findViewById(R.id.ambient_btn);
//        lightBtn = (Button)findViewById(R.id.light_btn);
        pressureBtn = (Button)findViewById(R.id.pressure_btn);
//        humidityBtn = (Button)findViewById(R.id.humidity_btn);

//        ambientBtn.setOnClickListener(this);
//        lightBtn.setOnClickListener(this);
        pressureBtn.setOnClickListener(this);
//        humidityBtn.setOnClickListener(this);

//        ambientValue = (TextView)findViewById(R.id.ambient_text);
//        valueFields[AMBIENT]=ambientValue;
//        lightValue = (TextView)findViewById(R.id.light_text);
//        valueFields[LIGHT]=lightValue;
        pressureValue = (TextView)findViewById(R.id.pressure_text);
        valueFields[PRESSURE]=pressureValue;
//        humidityValue = (TextView)findViewById(R.id.humidity_text);
//        valueFields[HUMIDITY]=humidityValue;
        senseManage = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

    }

    class SensorData extends AsyncTask<String,Void,String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

//                progressDialog = ProgressDialog.show(MainActivity.this,"Loading Data",null,true,true);
        }

        @Override
        protected void onPostExecute(String httpResponseMsg) {

            super.onPostExecute(httpResponseMsg);

//                progressDialog.dismiss();


        }

        @Override
        protected String doInBackground(String... params) {

            hashMap.put("StudentName",params[0]);

            new Timer().scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    finalResult = httpParse.postRequest(hashMap, HttpURL);
                }
            }, 5000, 5000);


            return finalResult;
        }
    }

    public void StudentRegistration(final String S_Name){

        SensorData sensorData = new SensorData();

        sensorData.execute(S_Name);
    }


    //to retreiev data from sensor
    @Override
    public void onSensorChanged(SensorEvent event) {
        float sensorValue = event.values[0];
        TextView currValue = pressureValue;
        String envInfo = "";

        int currType = event.sensor.getType();
        switch (currType) {
            case Sensor.TYPE_AMBIENT_TEMPERATURE:
                envInfo = sensorValue + " degrees Celsius";
                currValue = valueFields[AMBIENT];
                break;
            case Sensor.TYPE_LIGHT:
                envInfo = sensorValue + " SI lux units";
                currValue = valueFields[LIGHT];
                break;
            case Sensor.TYPE_PRESSURE:
                envInfo = sensorValue + " hPa (millibars)";
                currValue = valueFields[PRESSURE];
                break;
            case Sensor.TYPE_RELATIVE_HUMIDITY:
                envInfo = sensorValue + " percent humidity";
                currValue = valueFields[HUMIDITY];
                break;
            default:
                break;
        }

        currValue.setText(envInfo);
        StudentRegistration(Float.toString(sensorValue));

    }

    //to get message from sensor
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        String accuracyMsg = "";
        switch (accuracy) {
            case SensorManager.SENSOR_STATUS_ACCURACY_HIGH:
                accuracyMsg = "Sensor has high accuracy";
                break;
            case SensorManager.SENSOR_STATUS_ACCURACY_MEDIUM:
                accuracyMsg = "Sensor has medium accuracy";
                break;
            case SensorManager.SENSOR_STATUS_ACCURACY_LOW:
                accuracyMsg = "Sensor has low accuracy";
                break;
            case SensorManager.SENSOR_STATUS_UNRELIABLE:
                accuracyMsg = "Sensor has unreliable accuracy";
                break;
            default:
                break;
        }
        Toast accuracyToast = Toast.makeText(this.getApplicationContext(), accuracyMsg, Toast.LENGTH_SHORT);
        accuracyToast.show();
    }

    @Override
    public void onClick(View v) {

        envSense = senseManage.getDefaultSensor(Sensor.TYPE_PRESSURE);

//        if (v.getId()==R.id.ambient_btn)
//        {
////ambient temperature
//            if(envSense==null)
//                Toast.makeText(this.getApplicationContext(),
//                        "Sorry - your device doesn't have a temperature sensor!",
//                        Toast.LENGTH_SHORT).show();
//            else
//                senseManage.registerListener(this, envSense, SensorManager.SENSOR_DELAY_NORMAL);
//
//        }
//        else if(v.getId()==R.id.light_btn)
//        {
////light
//            if(envSense==null)
//                Toast.makeText(this.getApplicationContext(),
//                        "Sorry - your device doesn't have a light sensor!",
//                        Toast.LENGTH_SHORT).show();
//            else
//                senseManage.registerListener(this, envSense, SensorManager.SENSOR_DELAY_NORMAL);

//        }
         if(v.getId()==R.id.pressure_btn)
        {

            if(envSense==null)
                Toast.makeText(this.getApplicationContext(),
                        "Sorry - your device doesn't have a pressure sensor!",
                        Toast.LENGTH_SHORT).show();
            else
                senseManage.registerListener(this, envSense, SensorManager.SENSOR_DELAY_NORMAL);
//pressure

        }
//        else if(v.getId()==R.id.humidity_btn)
//        {
////humidity
//            if(envSense==null)
//                Toast.makeText(this.getApplicationContext(),
//                        "Sorry - your device doesn't have a humidity sensor!",
//                        Toast.LENGTH_SHORT).show();
//            else
//                senseManage.registerListener(this, envSense, SensorManager.SENSOR_DELAY_NORMAL);
//
//        }

//        if (v.getId() == R.id.pressure_btn) {
////pressure
//            envSense = senseManage.getDefaultSensor(Sensor.TYPE_PRESSURE);
//            if (envSense == null)
//                Toast.makeText(this.getApplicationContext(),
//                        "Sorry - your device doesn't have an Pressure sensor!",
//                        Toast.LENGTH_SHORT).show();
//            else
//                senseManage.registerListener(this, envSense, SensorManager.SENSOR_DELAY_NORMAL);
//
//        }

    }



    @Override
    protected void onPause() {
        super.onPause();
        envSense = null;
        senseManage.unregisterListener(this);
    }
}
