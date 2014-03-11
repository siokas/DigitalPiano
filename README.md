DigitalPiano
============

The DigitalPiano app is a combination of Android-Processing-Arduino applications which is playing piano in the speaker
connected to the Arduino.
The Android app is taking values from the accelerometer sensor and sends them to the Processing app via the WiFi network
using Sockets.
The Processing app is a simple server which forwards to the Arduino (using the Serial connection) the values 
which are taken from the Android app.
The Arduino app takes the values from the Serial connection and depending to these values sends the corresponding
note (pitch) to the connected speaker.

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
 
 ******************************************************************************/
