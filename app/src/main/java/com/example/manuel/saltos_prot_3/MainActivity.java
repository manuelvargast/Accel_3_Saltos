package com.example.manuel.saltos_prot_3;

import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements SensorEventListener{

    SensorManager sensorManager;
    Sensor acelerometro;
    TextView contador;
    int saltos = 0, bandera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contador = (TextView) findViewById(R.id.txtContador);
    }

    @Override
    protected void onResume() {
        super.onResume();

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        acelerometro = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this, acelerometro, SensorManager.SENSOR_DELAY_FASTEST);
    }

    @Override
    protected void onPause() {

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensorManager.unregisterListener(this, acelerometro);

        super.onPause();
    }

    protected void onStop(){

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensorManager.unregisterListener(this, acelerometro);

        super.onStop();
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        float x = sensorEvent.values[1];
        contador.setText("" + saltos);

        if(x < -3 && bandera == 0){
            bandera++;

        }else if(x > 3 && bandera == 1) {
            bandera++;

        }

        if (bandera == 2){
            saltos++;
            contador.setText("" + saltos);
            bandera = 0;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    public void reiniciar(View view) {

        view = contador;
        saltos = 0;
        contador.setText("" + saltos);
    }
}
