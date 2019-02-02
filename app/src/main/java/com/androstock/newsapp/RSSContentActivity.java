package com.androstock.newsapp;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;
import android.widget.Toast;

public class RSSContentActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rsscontent);

        TextView tv = (TextView) findViewById(R.id.tv);

        String html = Html.fromHtml(getIntent().getExtras().getString("rss")).toString();
        tv.setText(html);
        Toast.makeText(this, html, Toast.LENGTH_SHORT).show();
    }

}
