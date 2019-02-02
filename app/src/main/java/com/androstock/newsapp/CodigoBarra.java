package com.androstock.newsapp;

import android.app.PictureInPictureParams;
import android.content.res.Configuration;
import android.drm.DrmStore;
import android.graphics.Point;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Rational;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class CodigoBarra extends AppCompatActivity implements ZXingScannerView.ResultHandler{

    EditText editText;
    Button escaneer, pic;
    ActionBar actionBar;

    private ZXingScannerView scannerview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_codigo_barra);
        actionBar = getSupportActionBar();

        pic = (Button)findViewById(R.id.picture);
        pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    Display display = getWindowManager().getDefaultDisplay();
                    Point size = new Point();
                    display.getSize(size);
                    int width = size.x;
                    int heigth = size.y;

                    Rational aspectRatio = new Rational(width,heigth);
                    PictureInPictureParams.Builder mPictureInPictureParamsBuilder =
                            new PictureInPictureParams.Builder();
                    mPictureInPictureParamsBuilder.setAspectRatio(aspectRatio).build();

                    enterPictureInPictureMode(mPictureInPictureParamsBuilder.build());
                }

            }
        });

    }
    @Override
    public void onBackPressed() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Display display = getWindowManager().getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);
            int width = size.x;
            int heigth = size.y;

            Rational aspectRatio = new Rational(width,heigth);
            PictureInPictureParams.Builder mPictureInPictureParamsBuilder =
                    new PictureInPictureParams.Builder();
            mPictureInPictureParamsBuilder.setAspectRatio(aspectRatio).build();

            enterPictureInPictureMode(mPictureInPictureParamsBuilder.build());
        }

    }

    @Override
    public void onPictureInPictureModeChanged(boolean isInPictureInPictureMode, Configuration newConfig) {
        if (isInPictureInPictureMode){
            actionBar.hide();
        }else {
            actionBar.show();
        }
    }

    public void Escanear(View view) {
        scannerview = new ZXingScannerView(this);
        setContentView(scannerview);
        scannerview.setResultHandler(this);
        scannerview.startCamera();

    }

    @Override
    protected void onPause() {
        super.onPause();
        scannerview.stopCamera();
    }

    @Override
    public void handleResult(Result result) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Resultado");
        builder.setMessage(result.getText());
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

       // editText = (EditText)findViewById(R.id.codigBarras) ;
       // editText.setText(result.getText());
        scannerview.resumeCameraPreview(this);

    }
}
