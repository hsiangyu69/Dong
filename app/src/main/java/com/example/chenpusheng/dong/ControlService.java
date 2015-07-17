package com.example.chenpusheng.dong;


import android.app.Service;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.IBinder;
import android.util.Log;

public class ControlService extends Service implements SensorEventListener{

    private SensorManager aSensorManager;
    private SensorManager gSensorManager;
    private Sensor aSensor;
    public String a_data;
    public String g_data;

    @Override
    public IBinder onBind(Intent intent) {
        // TODO Auto-generated method stub
        return null;
    }

    public int onStartCommand(Intent intent, int flags, int startId) {

        super.onStartCommand(intent, flags, startId)  ;
        //Accelerometer
        aSensorManager=(SensorManager) getSystemService(SENSOR_SERVICE);
        aSensor = aSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        aSensorManager.registerListener(ControlService.this, aSensor, SensorManager.SENSOR_DELAY_NORMAL);


        gSensorManager=(SensorManager) getSystemService(SENSOR_SERVICE);
        Sensor gSensor = aSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        gSensorManager.registerListener(ControlService.this, gSensor, SensorManager.SENSOR_DELAY_NORMAL);

        // We want this service to continue running until it is explicitly
        // stopped, so return sticky.
        return START_STICKY;
    }

	/*@Override
    public void onStart(Intent intent, int startId) {


		handler.postDelayed(showTime, 1000);
        super.onStart(intent, startId);

    }*/

    @Override
    public void onDestroy() {

        super.onDestroy();
    }



    public void onSensorChanged(SensorEvent event) {
        synchronized (this) {
            switch (event.sensor.getType())	{
                case Sensor.TYPE_ACCELEROMETER:
                    String ax=String.valueOf(event.values[0]); //ACCELEROMETER x value
                    String ay=String.valueOf(event.values[1]); //ACCELEROMETER y value
                    String az=String.valueOf(event.values[2]); //ACCELEROMETER z value
                    a_data=ax+","+ay+","+az;
                    Log.i("ACCELEROMETER Data", a_data);
                    break;

                case Sensor.TYPE_GYROSCOPE:
                    String gx=String.valueOf(event.values[0]); //GYROSCOPE x value
                    String gy=String.valueOf(event.values[1]); //GYROSCOPE y value
                    String gz=String.valueOf(event.values[2]); //GYROSCOPE z value
                    g_data=gx+","+gy+","+gz;
                    Log.i("GYROSCOPE Data", g_data);

                    break;


            }
        }


    }

    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // TODO Auto-generated method stub

    }

}