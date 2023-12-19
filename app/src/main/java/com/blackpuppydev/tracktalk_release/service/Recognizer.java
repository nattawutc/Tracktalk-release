package com.blackpuppydev.tracktalk_release.service;

import static android.speech.SpeechRecognizer.createSpeechRecognizer;

import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.IBinder;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.util.Log;

import com.blackpuppydev.tracktalk_release.database.StandardData;
//import com.google.firebase.database.annotations.Nullable;

import java.util.ArrayList;
import java.util.Locale;

public class Recognizer extends Service {

    private final String TAG = "Recognizer";
    private SpeechRecognizer speechRecognizer;
    private Intent speechRecognizerIntent;
    private AudioManager mAudioManager;
    private StandardData standardData;


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

        standardData = new StandardData(this);
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
                Log.d(TAG,"onReadyForSpeech");
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

                Log.d(TAG, "onError : " + error);


                switch (error){
                    case SpeechRecognizer.ERROR_AUDIO:
                        //fix audio;
                        break;
                    case SpeechRecognizer.ERROR_SERVER:
                    case SpeechRecognizer.ERROR_SERVER_DISCONNECTED:
                        //return error;
                        break;
                    case SpeechRecognizer.ERROR_CLIENT:
                        //fix audio;
                        break;
                    case SpeechRecognizer.ERROR_RECOGNIZER_BUSY:
                        //return error;
                        break;
                    case SpeechRecognizer.ERROR_LANGUAGE_NOT_SUPPORTED:
                    case SpeechRecognizer.ERROR_LANGUAGE_UNAVAILABLE:
                        //fix audio;
                        break;
//                    case SpeechRecognizer.ERROR_CANNOT_CHECK_SUPPORT:
//                        //return error;
//                        break;
                    case SpeechRecognizer.ERROR_INSUFFICIENT_PERMISSIONS:
                        //fix audio;
                        break;
                    case SpeechRecognizer.ERROR_NETWORK:
                    case SpeechRecognizer.ERROR_NETWORK_TIMEOUT:
                        //return error;
                        break;
                    case SpeechRecognizer.ERROR_SPEECH_TIMEOUT:
                        //fix audio;
                        break;
                    case SpeechRecognizer.ERROR_TOO_MANY_REQUESTS:
                        //return error;
                        break;
                    case SpeechRecognizer.ERROR_NO_MATCH:
                    default:
                        speechRecognizer.cancel();
                        speechRecognizer.startListening(speechRecognizerIntent);
                        break;
                }


            }


            @Override
            public void onResults(Bundle results) {
                ArrayList<String> data = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
//                float [] confidence = results.getFloatArray(SpeechRecognizer.CONFIDENCE_SCORES);

                Log.d(TAG , "onResults : " + data.get(0));


                standardData.writeSetting();
                standardData.readSetting();

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
