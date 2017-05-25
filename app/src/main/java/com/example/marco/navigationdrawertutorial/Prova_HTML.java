package com.example.marco.navigationdrawertutorial;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

/**
 * Created by Marco on 25/05/2017.
 */

public class Prova_HTML extends AppCompatActivity {

    private TextView respText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prova_html);


        Button btnGo = (Button) findViewById(R.id.btnGo);
        respText = (TextView) findViewById(R.id.edtResp);
        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String siteUrl = "http://www03.unibg.it//orari//orario_giornaliero.php?db=IN&data=oggi&orderby=ora";
                ( new ParseURL() ).execute(new String[]{siteUrl});
            }
        });

    }

    private class ParseURL extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            StringBuffer buffer = new StringBuffer();
            ArrayList<String> Info_aule = new ArrayList<String>();
            ArrayList<String> H_aule = new ArrayList<String>();


            try {
                Log.d("JSwa", "Connecting to ["+strings[0]+"]");
                Document doc  = Jsoup.connect(strings[0]).get();
                Log.d("JSwa", "Connected to ["+strings[0]+"]");

                Element table = doc.select("table").get(0);
                Elements rowElems = table.select("tr");
                buffer.append("AULE DATA\r\n");

                for(int i=1; i<rowElems.size();i++){
                    Element row = rowElems.get(i);
                    Elements cols = row.select("td");
                    String Aule[]=cols.get(3).text().split("\\(");
                    Info_aule.add(Aule[0]);
                    String Orari[]=cols.get(3).text().split("\\)");
                    H_aule.add(Orari[1]);
                    buffer.append("["+Aule[0]+ "-" + Orari[1] +"] \r\n \n");
                }
            }
            catch(Throwable t) {
                t.printStackTrace();
            }

            return buffer.toString();
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            respText.setText(s);
        }
    }
}