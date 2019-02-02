package com.androstock.newsapp;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Locale;

public class TextVoice extends AppCompatActivity {

    private static final int REQ_CODE_SPEACH_INPUT = 1000;
    private EditText txtVoice;
    private ImageView imgVoice, imgVoice1;
    TTSManager ttsManager=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_voice);

        ttsManager=new TTSManager();
        ttsManager.init(this);

        txtVoice = (EditText) findViewById(R.id.txtVoice);
        imgVoice = (ImageView) findViewById(R.id.imgVoice);
        imgVoice1 = (ImageView) findViewById(R.id.imgVoice1);

        imgVoice1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text=txtVoice.getText().toString();
                ttsManager.initQueue(text);
            }
        });
        imgVoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iniciarGravacao();
            }
        });
    }

    private void iniciarGravacao() {

        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Fale algo...");
        try{
           startActivityForResult(intent, REQ_CODE_SPEACH_INPUT);
        }catch (ActivityNotFoundException e){

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case REQ_CODE_SPEACH_INPUT:{
                if (resultCode == RESULT_OK && null != data){
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    txtVoice.setText(result.get(0));

                }
                break;
            }
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        ttsManager.shutDown();
    }
}
