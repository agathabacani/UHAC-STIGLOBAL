package com.example.jacechristian.uhac_stiglobal;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.AsyncTask;
import android.support.v7.widget.SearchView;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.PrintWriter;
import java.io.StringWriter;
import okhttp3.*;


public class TransferActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn;
    OkHttpClient client = new OkHttpClient();

    UBTransactions ubt ;
    String transtatus;
    String amt;
    EditText amount;
    String demouserAcctUgo="101050943579";
    String demouserAcct="101065294818";
    TextView stat;
    ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer);

        amount= (EditText) findViewById(R.id.amountTxt);
        btn = (Button) findViewById(R.id.btnTrans);
        btn.setOnClickListener(this);
    }
    public void onClick(View v){
        try {
            amt = amount.getText().toString();
            String succ = "true";
            AsyncTaskRunner runner = new AsyncTaskRunner();
            runner.execute(succ);
            dialog = new ProgressDialog(this); // this = YourActivity
            dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            dialog.setMessage("Fetching Account...");
            dialog.setIndeterminate(true);
            dialog.setCanceledOnTouchOutside(false);
            dialog.setCancelable(false);
            dialog.show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    AlertDialog.Builder builder= new AlertDialog.Builder(TransferActivity.this);
                    builder.setCancelable(true);
                    builder.setTitle("Result");
                    if(transtatus.equalsIgnoreCase("f")) {
                        builder.setMessage("Transfer Failed!");
                    }
                    else{
                        builder.setMessage("Transfer Success!");
                        amount.setText("");
                    }
                    builder.show();

                    dialog.dismiss();

                }
            }, 2000);
        }
        catch(Exception e){
            StringWriter errors = new StringWriter();
            e.printStackTrace(new PrintWriter(errors));
            showMessage("error",errors.toString());
        }
    }
    void transac(){
        ubt = new UBTransactions();
        transtatus= ubt.payToll(demouserAcct,demouserAcctUgo,amt);

    }

    private class AsyncTaskRunner extends AsyncTask<String, String, String> {
        private String st;
        @Override
        protected String doInBackground(String... params) {
            transac();
            return st;
        }
    }

    public void showMessage(String title,String message){
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);

        builder.show();
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            return true;
        }
        return false;
    }

}
