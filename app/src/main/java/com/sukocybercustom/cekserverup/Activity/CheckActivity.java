package com.sukocybercustom.cekserverup.Activity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.sukocybercustom.cekserverup.R;
import com.sukocybercustom.cekserverup.util.Utility;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.URL;

public class CheckActivity extends AppCompatActivity {
    TextView tvStatus;
    Button btnRefresh;
    String status;
    EditText etServer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         tvStatus = (TextView)findViewById(R.id.tvStatus);
        btnRefresh = (Button)findViewById(R.id.btnResfresh);
        etServer = (EditText)findViewById(R.id.etServer);
        etServer.setText("http://");
        btnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new KerjakanBackgroundTask(etServer.getText().toString()).execute();

            }
        });

    }

    private class KerjakanBackgroundTask extends AsyncTask<Void, Integer, String>
    {
        private String server;
        public KerjakanBackgroundTask(String URLL) {
            Log.d("tes",URLL);
            setServer(URLL);
        }

        public String getServer() {
            return server;
        }

        public void setServer(String server) {
            this.server = server;
        }

        @Override
        protected void onPreExecute() {
            ProgressBar progressBar  = (ProgressBar)findViewById(R.id.progress);
            progressBar.setVisibility(View.VISIBLE);
        }

        //proses
    protected String doInBackground(Void... v) {
        try {
            if (Utility.isServerReachable(CheckActivity.this,getServer())){
                status = "server up";
            }else {
                status = "server down";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }


    protected void onPostExecute(String result) {
        if (status=="server up"){
            tvStatus.setText(status);
            tvStatus.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_check,0,0,0);
        }else {
            tvStatus.setText(status);
            tvStatus.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_close,0,0,0);
        }

    }
}


}
