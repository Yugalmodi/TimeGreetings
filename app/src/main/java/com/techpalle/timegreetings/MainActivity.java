package com.techpalle.timegreetings;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    TextView textViewTime, textViewGreeting;
    ImageView imageView;
    ImageButton imageButtonCamera, imageButtonGallery, imageButtonDrive;
    TextToSpeech speech;
    int hour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewTime = (TextView) findViewById(R.id.text_time);
        textViewGreeting = (TextView) findViewById(R.id.text_greeting);
        imageView = (ImageView) findViewById(R.id.image_view1);
        imageButtonCamera = (ImageButton) findViewById(R.id.image_button_camera);
        imageButtonGallery = (ImageButton) findViewById(R.id.image_button_gallery);
        imageButtonDrive = (ImageButton) findViewById(R.id.image_button_drive);

        Calendar c = Calendar.getInstance();
        hour = c.get(Calendar.HOUR_OF_DAY);
        int minute= c.get(Calendar.MINUTE);
        int seconds = c.get(Calendar.SECOND);
        textViewTime.setText(hour+":"+minute+":"+seconds);

        speech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if (i == TextToSpeech.SUCCESS) {
                    speech.setLanguage(Locale.US);
                }
            }
        });

        switch (hour){
            case 22:
                textViewGreeting.setText("Good Day!");
                break;
            case 23:
                textViewGreeting.setText("Good Day!");
                break;
            case 0:
                textViewGreeting.setText("Good Day!");
                break;
            case 1:
                textViewGreeting.setText("Good Day!");
                break;
            case 2:
                textViewGreeting.setText("Good Day!");
                break;
            case 3:
                textViewGreeting.setText("Good Day!");
                break;
            case 4:
                textViewGreeting.setText("Good Morning!");
                break;
            case 5:
                textViewGreeting.setText("Good Morning!");
                break;
            case 6:
                textViewGreeting.setText("Good Morning!");
                break;
            case 7:
                textViewGreeting.setText("Good Morning!");
                break;
            case 8:
                textViewGreeting.setText("Good Morning!");
                break;
            case 9:
                textViewGreeting.setText("Good Morning!");
                break;
            case 10:
                textViewGreeting.setText("Good Morning!");
                break;
            case 11:
                textViewGreeting.setText("Good Morning!");
                break;
            case 12:
                textViewGreeting.setText("Good After Noon!");
                break;
            case 13:
                textViewGreeting.setText("Good After Noon!");
                break;
            case 14:
                textViewGreeting.setText("Good After Noon!");
                break;
            case 15:
                textViewGreeting.setText("Good After Noon!");
                break;
            case 16:
                textViewGreeting.setText("Good After Noon!");
                break;
            case 17:
                textViewGreeting.setText("Good Evening!");
                mySpeech("Good Evening! Yugal");
                break;
            case 18:
                textViewGreeting.setText("Good Evening!");
                break;
            case 19:
                textViewGreeting.setText("Good Evening!");
                break;
            case 20:
                textViewGreeting.setText("Good Evening!");
                break;
            case 21:
                textViewGreeting.setText("Good Evening!");
                break;
        }
        imageButtonCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 1);
            }
        });

        imageButtonGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent,0);

            }
        });

        imageButtonDrive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mySpeech("Good Morning, Yugal");
                if(imageView.getDrawable() == null){
                    mySpeech("image view is null");
                    Toast.makeText(MainActivity.this, "image view is null", Toast.LENGTH_SHORT).show();
                }
                else{
                    mySpeech("image view have some value");
                    Toast.makeText(MainActivity.this, "image view have some value", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 0){
            try {
                Uri image = data.getData();
                if (image == null){
                    return;}
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), image);
                imageView.setImageBitmap(bitmap);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(requestCode == 1){
            Bundle b = data.getExtras();
            if(b==null){return;}
            Bitmap bitmap = b.getParcelable("data");
            imageView.setImageBitmap(bitmap);
        }
    }

    private void mySpeech(String s){
        if(s.isEmpty() == false){
            speech.speak(s, TextToSpeech.QUEUE_FLUSH, null);
        }
    }

    @Override
    public void onDestroy() {
        // Don't forget to shutdown tts!
        if (speech != null) {
            speech.stop();
            speech.shutdown();
        }
        super.onDestroy();
    }
}
