package com.androstock.newsapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.os.StrictMode;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MostrarNoticias extends AppCompatActivity {

   private TextView Title,Desc,Date;
   private ImageView Thumbnail;
   String titulo, descr, data, imagem;
   String email;
   String password;
   Session session;
   ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_noticias);
        ActionBar act = getSupportActionBar();

        dialog = new ProgressDialog(this);
        titulo = getIntent().getStringExtra("titulo");
        descr = getIntent().getStringExtra("desc");
        data = getIntent().getStringExtra("data");
        imagem = getIntent().getStringExtra("imagem");
        act.setTitle(titulo);

        Title= (TextView) findViewById(R.id.title_mostrar);
        Desc= (TextView) findViewById(R.id.description_mostrar);
        Date= (TextView) findViewById(R.id.date_text);
        Thumbnail= (ImageView) findViewById(R.id.thumb_img_mostrar);

        Title.setText(titulo);
        Desc.setText(descr);
        Date.setText(data);
        Picasso.with(this).load(imagem).into(Thumbnail);

        email = "safadapp@gmail.com";
        password = "safad@1234";
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_share) {
            getSharing();

        }else if (id == R.id.action_email){
                sendEmail();
        }

        return super.onOptionsItemSelected(item);
    }

    private void sendEmail() {
        dialog.setMessage("Enviando e-mail");

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Properties properties = new Properties();
        properties.put("mail.smtp.host","smtp.googlemail.com");
        properties.put("mail.smtp.socketFactory.port","465");
        properties.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.auth","true");
        properties.put("mail.smtp.port","465");

        try {
            session = Session.getDefaultInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(email, password);
                }
            });

            if (session !=null){
                dialog.show();

                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(email));
                message.setSubject("via Brazil News App");
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("noslianmmello@gmail.com"));
                message.setContent(titulo+"\n\n"+descr, "text/plain; charset=utf-8");
                Transport.send(message);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        dialog.dismiss();
        Toast.makeText(this, "Mensagem enviada...", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(MostrarNoticias.this, Main2Activity.class));
        finish();
    }

    private void getSharing() {
        Bitmap bitmap =getBitmapFromView(Thumbnail);
        try {
            File file = new File(this.getExternalCacheDir(),"BrazilNewsApp.png");
            FileOutputStream fOut = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut);
            fOut.flush();
            fOut.close();
            file.setReadable(true, false);
            final Intent intent = new Intent(android.content.Intent.ACTION_SEND);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra(Intent.EXTRA_TEXT, "        ("+titulo+")"+"\n\n"+descr+"\nvia: Brazil News App");
            intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));
            intent.setType("image/png");
            startActivity(Intent.createChooser(intent, "Compartilhar noticias via"));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private Bitmap getBitmapFromView(View view) {
        Bitmap returnedBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(),Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(returnedBitmap);
        Drawable bgDrawable =view.getBackground();
        if (bgDrawable!=null) {
            bgDrawable.draw(canvas);
        }   else{
            canvas.drawColor(Color.WHITE);
        }
        view.draw(canvas);
        return returnedBitmap;
    }

}
