/*
 * This is an Arduino application that takes some values from the Serial port
 * and do differend things depending on the values.
 * It created in order to make a connection between Android devices and Arduino.
 * The Android device sends some valuse to the 'MeddleMan' which is a server
 * running on a computer and the server whenever it accepts a message, it
 * forwards it to the Serial port and this application is waiting to read 
 * these values.
 *
 * Created by Apostolos Siokas
 *
*/


 #include "pitches.h"

// notes in the melody:
int melody[] = {
  NOTE_C4, NOTE_G3,NOTE_G3, NOTE_A3, NOTE_G3,0, NOTE_B3, NOTE_C4};

// note durations: 4 = quarter note, 8 = eighth note, etc.:
int noteDurations[] = {
  4, 8, 8, 4,4,4,4,4 };
  
char val;

int pin = 12; // Change it to the pin which is connected to the speaker

void setup() {
  
  Serial.begin(9600); // Begin the serial communication
  }
}

void loop() {
  // no need to repeat the melody.
  
  if (Serial.available()) { // Find if there are some values on the Serial
    val = Serial.read(); // Read the and store the in a variable
  }
  
  // Check what is the value is and play the corresponding note
  switch (val){
    case '1':
      tone(pin, melody[0], 1000/4);
    break;
    
    case '2':
      tone(pin, melody[1], 1000/4);
    break;
    
    case '3':
      tone(pin, melody[2], 1000/4);
    break;
    
    case '4':
      tone(pin, melody[3], 1000/4);
    break;
    
    case '5':
      tone(pin, melody[4], 1000/4);
    break;
    
    case '6':
      tone(pin, melody[5], 1000/4);
    break;
    
    case '7':
      tone(pin, melody[6], 1000/4);
    break;
  }
  
  delay(1);
}
