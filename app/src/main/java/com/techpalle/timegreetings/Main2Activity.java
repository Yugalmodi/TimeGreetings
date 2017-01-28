package com.techpalle.timegreetings;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Locale;


public class Main2Activity extends AppCompatActivity {
    TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if(i == TextToSpeech.SUCCESS){
                    tts.setLanguage(Locale.ENGLISH);
                }
            }
        }, null);
        tts.speak("Good Evening, Mr.yugal", TextToSpeech.QUEUE_FLUSH, null);
    }
}
