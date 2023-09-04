package com.blackpuppydev.tracktalk_release.service;

import static android.speech.SpeechRecognizer.createSpeechRecognizer;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.IBinder;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Locale;

public class Recognizer extends Service {

    private SpeechRecognizer speechRecognizer;
    private Intent speechRecognizerIntent;
    private AudioManager mAudioManager;


    @Nullable
    @Override
    public IBinder onBind(Intent intent) { return null; }


    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //show popup
//        Toast.makeText(this, "Start.", Toast.LENGTH_LONG).show();
        super.onStartCommand(intent, flags, startId);

        initRecognizer();
        //start Speech listening
        speechRecognizer.startListening(speechRecognizerIntent);

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (speechRecognizer != null){
            speechRecognizer.cancel();
        }

    }




    private void initRecognizer() {

        //use for mute sound
//        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        //setting speech recognition
        speechRecognizer = createSpeechRecognizer(this);
        speechRecognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH); //start for speaking
        //set language model
        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        //set language for use (if not set then use default language on phone)
        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        //show confidence score
        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_CONFIDENCE_SCORES,true);


        speechRecognizer.setRecognitionListener(new RecognitionListener() {
            @Override
            public void onReadyForSpeech(Bundle params) {

            }

            @Override
            public void onBeginningOfSpeech() {

            }

            @Override
            public void onRmsChanged(float rmsdB) {

            }

            @Override
            public void onBufferReceived(byte[] buffer) {

            }

            @Override
            public void onEndOfSpeech() {


            }

            @Override
            public void onError(int error) {

                Log.d("Tracktalk - Error recognition ", String.valueOf(error));

                speechRecognizer.cancel();
                speechRecognizer.startListening(speechRecognizerIntent);
//                switch (error){
//                    case SpeechRecognizer.ERROR_AUDIO:
//                        //return error;
//                        break;
//                    case SpeechRecognizer.ERROR_CLIENT:
//                        //return error;
//                        break;
//                }
            }

            @Override
            public void onResults(Bundle results) {
                ArrayList<String> data = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
//                float [] confidence = results.getFloatArray(SpeechRecognizer.CONFIDENCE_SCORES);

                Log.d("Tracktalk - result speech : " , data.get(0));
                speechRecognizer.cancel();
                speechRecognizer.startListening(speechRecognizerIntent);

            }

            @Override
            public void onPartialResults(Bundle partialResults) {
                //Called when partial recognition results are available.
                speechRecognizer.cancel();
                speechRecognizer.startListening(speechRecognizerIntent);
            }

            @Override
            public void onEvent(int eventType, Bundle params) {

            }
        });

    }



}
