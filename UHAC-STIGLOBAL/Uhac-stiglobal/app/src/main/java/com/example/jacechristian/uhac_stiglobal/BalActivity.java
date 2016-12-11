package com.example.jacechristian.uhac_stiglobal;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.AsyncTask;
import android.view.KeyEvent;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.PrintWriter;
import java.io.StringWriter;
import okhttp3.*;

public class BalActivity extends AppCompatActivity {

    OkHttpClient client = new OkHttpClient();

    UBTransactions ubt ;
    UBTransactions ubt2 ;
    String demouserAcct="101065294818";
    String demouserAcctUgo="101050943579";
    String bal;
    String balUgo;
    String account_no;
    String currency;
    String account_name;
    String status;
    String available_balance;
    String available_balanceUgo;
    String current_balance ;
    TextView Sample;
    TextView ugo;
    ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bal);
        Sample = (TextView) findViewById(R.id.ubtxt);
        ugo = (TextView) findViewById(R.id.ugotxt);
        try {
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
                    getInfo(bal);
                    getInfoUgo(balUgo);
                    Sample.setText(available_balance);
                    ugo.setText(available_balanceUgo);
                    dialog.dismiss();
                }
            }, 4500);
        }
        catch(Exception e){
            StringWriter errors = new StringWriter();
            e.printStackTrace(new PrintWriter(errors));
            showMessage("error",errors.toString());
        }
    }

    public void getInfo(String info)
    {
        info = info.replace("[","").replace("]","").replace("{","").replace("}","").replace("\"","");
        String[] collection_info = info.split(",");

        for(int ctr = 0; ctr < collection_info.length; ctr++)
        {
            String[] temp = collection_info[ctr].split(":");
            collection_info[ctr] = temp[1];
        }

        account_no = collection_info[0];
        currency = collection_info[1];
        account_name = collection_info[2];
        status = collection_info[3];
        available_balance = collection_info[4];
        current_balance = collection_info[5];
    }
    public void getInfoUgo(String info)
    {
        info = info.replace("[","").replace("]","").replace("{","").replace("}","").replace("\"","");
        String[] collection_info = info.split(",");

        for(int ctr = 0; ctr < collection_info.length; ctr++)
        {
            String[] temp = collection_info[ctr].split(":");
            collection_info[ctr] = temp[1];
        }


        available_balanceUgo = collection_info[4];

    }

    private class AsyncTaskRunner extends AsyncTask<String, String, String> {
        private String st;
        @Override
        protected String doInBackground(String... params) {
            balInq();
            balInq2();
            return st;
        }
    }

    void balInq(){
        try{
            ubt = new UBTransactions();
            bal = ubt.balanceInq(demouserAcct);

        }

        catch(Exception e){
            StringWriter errors = new StringWriter();
            e.printStackTrace(new PrintWriter(errors));
            showMessage("error",errors.toString());
        }
    }
    void balInq2(){
        try{
            ubt2 = new UBTransactions();
            balUgo = ubt2.balanceInq(demouserAcctUgo);

        }

        catch(Exception e){
            StringWriter errors = new StringWriter();
            e.printStackTrace(new PrintWriter(errors));
            showMessage("error",errors.toString());
        }
    }

    public void showMessage(String title,String message){
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);

        builder.show();
    }
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
