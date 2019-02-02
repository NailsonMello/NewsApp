package com.androstock.newsapp;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.androstock.newsapp.NovoRSS.ReadRss;
import com.androstock.newsapp.NovoRSS.ReadRssCultura;
import com.androstock.newsapp.NovoRSS.ReadRssEleicoes;
import com.androstock.newsapp.NovoRSS.ReadRssEsporte;
import com.androstock.newsapp.NovoRSS.ReadRssHolofote;
import com.androstock.newsapp.NovoRSS.ReadRssJustica;
import com.androstock.newsapp.NovoRSS.ReadRssMulher;
import com.androstock.newsapp.NovoRSS.ReadRssSaude;

public class Main2Activity extends AppCompatActivity {
    RecyclerView recyclerView;
    Spinner feed;
    RelativeLayout relativeLayout;
    String finalNoticias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        relativeLayout = (RelativeLayout)findViewById(R.id.main2);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        feed = (Spinner) findViewById(R.id.simpleSpinner);
        String[] list = getResources().getStringArray(R.array.feed);
        ArrayAdapter<String> adaptermva = new ArrayAdapter<String>(this, R.layout.spinner_layout, R.id.textView, list);
        feed.setAdapter(adaptermva);

        feed.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                ConnectivityManager on = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                final NetworkInfo netInfo = on.getActiveNetworkInfo();
                final String finalNoticias = feed.getSelectedItem().toString();

                if(finalNoticias.equals("Principais noticias")){
                    //Call Read rss asyntask to fetch rss
                    if (netInfo != null && netInfo.isConnectedOrConnecting()) {
                        ReadRss readRss = new ReadRss(Main2Activity.this, recyclerView);
                        readRss.execute();
                    } else {
                        Snackbar.make(relativeLayout, "Para visualizar as noticias, você precisa se conectar a internet",
                                Snackbar.LENGTH_SHORT).setAction("OK", null).show();
                    }



                }else if(finalNoticias.equals("Esporte")){
                    //Call Read rss asyntask to fetch rss
                    if (netInfo != null && netInfo.isConnectedOrConnecting()) {
                        ReadRssEsporte readRss = new ReadRssEsporte(Main2Activity.this, recyclerView);
                        readRss.execute();
                    } else {
                        Snackbar.make(relativeLayout, "Para visualizar as noticias, você precisa se conectar a internet",
                                Snackbar.LENGTH_SHORT).setAction("OK", null).show();
                    }

                }else if(finalNoticias.equals("Holofote")){
                    //Call Read rss asyntask to fetch rss
                    if (netInfo != null && netInfo.isConnectedOrConnecting()) {
                        ReadRssHolofote readRss = new ReadRssHolofote(Main2Activity.this, recyclerView);
                        readRss.execute();
                    } else {
                        Snackbar.make(relativeLayout, "Para visualizar as noticias, você precisa se conectar a internet",
                                Snackbar.LENGTH_SHORT).setAction("OK", null).show();
                    }

                }else if(finalNoticias.equals("Cultura")){
                    //Call Read rss asyntask to fetch rss
                    if (netInfo != null && netInfo.isConnectedOrConnecting()) {
                        ReadRssCultura readRss = new ReadRssCultura(Main2Activity.this, recyclerView);
                        readRss.execute();
                    } else {
                        Snackbar.make(relativeLayout, "Para visualizar as noticias, você precisa se conectar a internet",
                                Snackbar.LENGTH_SHORT).setAction("OK", null).show();
                    }

                }else if(finalNoticias.equals("Saúde")){
                    if (netInfo != null && netInfo.isConnectedOrConnecting()) {
                        ReadRssSaude readRss = new ReadRssSaude(Main2Activity.this, recyclerView);
                        readRss.execute();
                    } else {
                        Snackbar.make(relativeLayout, "Para visualizar as noticias, você precisa se conectar a internet",
                                Snackbar.LENGTH_SHORT).setAction("OK", null).show();
                    }

                }else if(finalNoticias.equals("Justiça")){
                    if (netInfo != null && netInfo.isConnectedOrConnecting()) {
                        ReadRssJustica readRss = new ReadRssJustica(Main2Activity.this, recyclerView);
                        readRss.execute();
                    } else {
                        Snackbar.make(relativeLayout, "Para visualizar as noticias, você precisa se conectar a internet",
                                Snackbar.LENGTH_SHORT).setAction("OK", null).show();
                    }

                }else if(finalNoticias.equals("Mulher")){
                    if (netInfo != null && netInfo.isConnectedOrConnecting()) {
                        ReadRssMulher readRss = new ReadRssMulher(Main2Activity.this, recyclerView);
                        readRss.execute();
                    } else {
                        Snackbar.make(relativeLayout, "Para visualizar as noticias, você precisa se conectar a internet",
                                Snackbar.LENGTH_SHORT).setAction("OK", null).show();
                    }

                }else if(finalNoticias.equals("Eleições")){
                    if (netInfo != null && netInfo.isConnectedOrConnecting()) {
                        ReadRssEleicoes readRss = new ReadRssEleicoes(Main2Activity.this, recyclerView);
                        readRss.execute();
                    } else {
                        Snackbar.make(relativeLayout, "Para visualizar as noticias, você precisa se conectar a internet",
                                Snackbar.LENGTH_SHORT).setAction("OK", null).show();
                    }

                }else if(finalNoticias.equals("Voice")){

                    startActivity(new Intent(Main2Activity.this, TextVoice.class));
                }else if(finalNoticias.equals("Codigo Barras")){

                    startActivity(new Intent(Main2Activity.this, CodigoBarra.class));
                    finish();
                }else if(finalNoticias.equals("YouTube")){
                    startActivity(new Intent(Main2Activity.this, MainActivity.class));
                    finish();
                }else if(finalNoticias.equals("Catalogo")){
                    startActivity(new Intent(Main2Activity.this, Catalogo.class));
                    finish();
                }else if(finalNoticias.equals("WebCatalogo")){
                    startActivity(new Intent(Main2Activity.this, NovoCatalogo.class));
                    finish();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




    }

    @Override
    protected void onStart() {
        super.onStart();
        //Call Read rss asyntask to fetch rss
       // ReadRss readRss = new ReadRss(this, recyclerView);
       // readRss.execute();
    }
}