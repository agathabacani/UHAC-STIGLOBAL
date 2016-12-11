package com.example.jacechristian.uhac_stiglobal;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.os.AsyncTask;
import android.widget.TextView;

import java.io.PrintWriter;
import java.io.StringWriter;
import okhttp3.*;



public class MainActivity extends AppCompatActivity  implements OnClickListener {
    OkHttpClient client = new OkHttpClient();

    Button btntransfer;
    Button btnbal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //balancetxt = (TextView) findViewById(R.id.balancetxt);
       // accName =(TextView) findViewById(R.id.accName);
        btntransfer=(Button)findViewById(R.id.btnTransfer);
        btntransfer.setOnClickListener(this);
        btnbal=(Button)findViewById(R.id.btnbal);
        btnbal.setOnClickListener(this);

    }

    public void onClick(View v){
        if(v==btnbal){

            startActivity(new Intent(MainActivity.this,
                    BalActivity.class));
            finish();

        }
        if(v==btntransfer){

            startActivity(new Intent(MainActivity.this,
                    TransferActivity.class));
            finish();

        }


    }




    public void showMessage(String title,String message){
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);

        builder.show();
    }
}
