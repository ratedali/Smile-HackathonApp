package io.divergents.hackathon.smile.exercises;


import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import io.divergents.hackathon.smile.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class AccelWorkoutFragment extends Fragment implements SensorEventListener {
    private SensorManager senSensorManager;
    private Sensor senAccelerometer;
    private long lastUpdate = 0;
    private float last_x, last_y, last_z;
    private static final int SPEED_THREHOLD = 100;
    public int counter = 40;


    public AccelWorkoutFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_accel_workout, container, false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        senSensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        senAccelerometer = senSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        senSensorManager.registerListener(this, senAccelerometer , SensorManager.SENSOR_DELAY_NORMAL);
        counter = 20;
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        Sensor mySensor = sensorEvent.sensor;
        Button accelCounter = (Button) getView().findViewById(R.id.accelCounterButton);


        if (mySensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float x = sensorEvent.values[0];
            float y = sensorEvent.values[1];
            float z = sensorEvent.values[2];
            Log.d("sensorData" , "X = " + String.valueOf(x) + "Y = " + String.valueOf(y) + "Z = " + String.valueOf(z));
            long curTime = System.currentTimeMillis();


            if ((curTime - lastUpdate) > 200) {
                long diffTime = (curTime - lastUpdate);
                lastUpdate = curTime;

                float distance = Math.abs(x + y + z - last_x - last_y - last_z);
                float speed = Math.abs(x + y + z - last_x - last_y - last_z)/ diffTime * 10000;

                Log.d("sensordistance" , String.valueOf(distance));
                if (counter >0) {
                    if (distance > 15) {


                        counter--;
                        accelCounter.setText(String.valueOf(counter ));


                    }
                }
                if (counter == 0){
                    MediaPlayer mediaPlayer = MediaPlayer.create(getActivity(), R.raw.tada);
                    mediaPlayer.start();
                    Intent intent = new Intent(getActivity() ,WorkoutFinishedActivity.class );
                    startActivity(intent);
                }


                last_x = x;
                last_y = y;
                last_z = z;
            }
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
    public void onPause() {
        super.onPause();
        senSensorManager.unregisterListener(this);
    }
    public void onResume() {
        super.onResume();
        senSensorManager.registerListener(this, senAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

}
