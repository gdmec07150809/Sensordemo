package com.example.student.sensordemo;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorListener;

import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private SensorManager mSensorMananger;
    private Sensor mAccelerometer;
    private Sensor mOrientation;
    private Sensor mLight;
    private TextView tAcclerometer;
    private TextView tOrientAtion;
    private TextView tLight;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tAcclerometer= (TextView) this.findViewById(R.id.accelerometer);
        tOrientAtion= (TextView) this.findViewById(R.id.orientation);
        tLight= (TextView) this.findViewById(R.id.light);
        mSensorMananger= (SensorManager) getSystemService(SENSOR_SERVICE);
        mAccelerometer=mSensorMananger.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mOrientation=mSensorMananger.getDefaultSensor(Sensor.TYPE_ORIENTATION);
        mLight=mSensorMananger.getDefaultSensor(Sensor.TYPE_LIGHT);

    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorMananger.registerListener(this,mAccelerometer,SensorManager.SENSOR_DELAY_NORMAL);
        mSensorMananger.registerListener(this,mOrientation,SensorManager.SENSOR_DELAY_NORMAL);
        mSensorMananger.registerListener(this,mLight,SensorManager.SENSOR_DELAY_NORMAL);

    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorMananger.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float x=event.values[SensorManager.DATA_X];
        float y=event.values[SensorManager.DATA_Y];
        float z=event.values[SensorManager.DATA_Z];
        if(event.sensor.getType()==Sensor.TYPE_ORIENTATION){
            tOrientAtion.setText("方位："+x+","+y+","+z);
        }else if(event.sensor.getType()==Sensor.TYPE_ACCELEROMETER){
            tAcclerometer.setText("加速度："+x+","+y+","+z);
        }else if(event.sensor.getType()==Sensor.TYPE_LIGHT){
            tLight.setText("光线："+event.values[0]);

        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
