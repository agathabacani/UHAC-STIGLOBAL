package com.example.jacechristian.uhac_stiglobal;

import android.app.AlertDialog;

import java.io.PrintWriter;
import java.io.StringWriter;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Jace Christian on 12/10/2016.
 */

public class UBTransactions {

    OkHttpClient client;
    Response response;
    Request request;
    String bal="";
    Response res;
    Request req;
    MediaType mediaType;
    RequestBody body;
    String transactionId ;
    String status ;
    String confirmationNumber ;
    String account_no;
    String currency;
    String account_name;
    String statusAcc;
    String available_balance;
    String current_balance ;
    String accNum;
    String transinfo;
    String balanceInq(String accNums){
        try {
            client = new OkHttpClient();
            request = new Request.Builder()
                    .url("https://api.us.apiconnect.ibmcloud.com/ubpapi-dev/sb/api/RESTs/getAccount?account_no="+accNums)
                    .get()
                    .addHeader("x-ibm-client-id", "0b826325-782c-4a47-884e-6093beb45e99")
                    .addHeader("x-ibm-client-secret", "yC1fW0uB8nW8xY3bB3bM5mW0oL3eI3kK7vK5aY4lT8rR8yI2oI")
                    .addHeader("content-type", "application/json")
                    .addHeader("accept", "application/json")
                    .build();

            response = client.newCall(request).execute();
            bal = response.body().string();
        }
        catch(Exception e){
            StringWriter errors = new StringWriter();
            e.printStackTrace(new PrintWriter(errors));
        }
        return bal;
    }

    String payToll(String accNums,String ugoNum,String amt){
            String result="";
            StringWriter errors = new StringWriter();
             long chNum=0;
            long transactID=0;
        try {
                chNum = (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;
                transactID = (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;
            client = new OkHttpClient();
                mediaType = MediaType.parse("application/json");
                body = RequestBody.create(mediaType, "{\"channel_id\":\""+chNum+"\",\"transaction_id\":\""+transactID+"\",\"source_account\":\""+accNums+"\",\"source_currency\":\"php\",\"target_account\":\""+ugoNum+"\",\"target_currency\":\"php\",\"amount\":"+amt+"}");
                req = new Request.Builder()
                        .url("https://api.us.apiconnect.ibmcloud.com/ubpapi-dev/sb/api/RESTs/transfer")
                        .post(body)
                        .addHeader("x-ibm-client-id", "0b826325-782c-4a47-884e-6093beb45e99")
                        .addHeader("x-ibm-client-secret", "yC1fW0uB8nW8xY3bB3bM5mW0oL3eI3kK7vK5aY4lT8rR8yI2oI")
                        .addHeader("content-type", "application/json")
                        .addHeader("accept", "application/json")
                        .build();

                res= client.newCall(req).execute();
                getInfo(res.body().string());
                result=status;
            }
            catch(Exception e){

                e.printStackTrace(new PrintWriter(errors));
            }
            return result+errors;
    }

    void getInfo(String info)
    {
        transinfo=info;
        info = info.replace("]","").replace("{","").replace("}","").replace("\"","");
        String[] collection_info = info.split(",");

        for(int ctr = 0; ctr < collection_info.length-1; ctr++)
        {
            String[] temp = collection_info[ctr].split(":");
            collection_info[ctr] = temp[1];
        }

        transactionId = collection_info[1];
        status = collection_info[2];
        confirmationNumber = collection_info[3];
    }


}
