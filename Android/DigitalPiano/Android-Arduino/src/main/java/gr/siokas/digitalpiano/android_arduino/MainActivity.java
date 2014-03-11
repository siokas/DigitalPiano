/*******************************************************************************
 Copyright (c) 2014 Apostolos Siokas

 This program is free software: you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation, either version 3 of the License, or
 any later version.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 Created and designed by Apostolos Siokas
 ******************************************************************************/

/*
 *  This is an Android application which connects to a server (in a computer) and sends some values.
 *  It created in order to make a connection between an Android device and an Arduino using a server
 *  in order to create a Digital Piano.
 *  This application creates a client socket and makes the connection with the server and sends some
 *  data which are taken from the accelerometer sensor of the device.
 *  If the user tilts (or shakes) the phone to the left or right the application sends values
 *  to the server depending on the x position of the accelerometer sensor.
 *  The server's job is to forward this message to the Arduino in order to establish the connection
 *  between the Android device and Arduino.
 */


package gr.siokas.digitalpiano.android_arduino;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.DataOutputStream;
import java.net.Socket;
import java.util.List;



public class MainActivity extends ActionBarActivity implements SensorEventListener {

    Socket client;
    Button accelerometer;
    EditText server;
    DataOutputStream dos;
    boolean sensorON = false;
    int note;
    int sameNote = 0;
    int step = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Assign the values with the correct id's
        accelerometer = (Button) findViewById(R.id.accel);
        server = (EditText) findViewById(R.id.server);

        // This method is called if the 'Start Accelerometer' button is pressed
        accelerometer.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                sensorON = true; // Change the sensorON accelerometer to true
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    @Override
    protected void onResume() {

        super.onResume();

        // This initiates the Accelerometer Sensor
        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        List<Sensor> sensors = sensorManager
                .getSensorList(Sensor.TYPE_ACCELEROMETER);

        // This initiates the Sensor Manager
        Sensor sensor = sensors.get(0);
        sensorManager.registerListener(this, sensor,
                SensorManager.SENSOR_MIN);

    }

    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // onAccuracyChanged() Method
    }

    // This Method is called whenever the accelerometer sensor changes values
    public void onSensorChanged(SensorEvent event) {
        // Check if the boolean value sensorON is true
        if (sensorON) {
            if (step == 0) { // Check if it is the first time to find the note
                note = (int) event.values.clone()[0]; // Initialize the note with the corresponding value taken from accelerometer sensor
                sameNote = (int) note;
            } else {
                note = (int) event.values.clone()[0];
                if (note != sameNote) { // Check if this note is the same with the previous note
                    int noteAbsValue = (note ^ 2) / note; // Create the absolute  value of the x axis
                    String noteToSend = "" + noteAbsValue; // Create a string containing the note to send
                    Send send = new Send(MainActivity.this, server
                            .getText().toString(), noteToSend); // Make the call to the server and send the note which is taken from accelerometer sensor
                    send.play(); // Call the play() in order to send the data to the server
                }
            }
        }
        step++;
    }

}