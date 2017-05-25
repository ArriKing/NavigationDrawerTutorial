package com.example.marco.navigationdrawertutorial;

/**
 * Created by Marco on 13/05/2017.
 */

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ClassActivity extends AppCompatActivity {

    private TextView respText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.percorso_di_studio);
        //inserisce la freccia di ritorno alla home
        //PROBLEMA:torna alla home,non al suo fragment ma al main fragment
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        respText = (TextView) findViewById(R.id.tv);
        String siteUrl = "http://www03.unibg.it//orari//orario_giornaliero.php?db=IN&data=oggi&orderby=ora";
        ( new ClassActivity.ParseURL() ).execute(new String[]{siteUrl});

    }


    public void onStart(){
        super.onStart();
        String Inizio = getIntent().getStringExtra("Time_1");
        String Fine = getIntent().getStringExtra("Time_2");
        String[] Edifici = getIntent().getStringExtra("Ed_list").split(",");
        ArrayList<String> auleList = new ParseURL().getInfo_aule();
        for(int k=0;k<auleList.size();k++){
            Toast.makeText(getApplicationContext(),auleList.get(k).toString(),Toast.LENGTH_LONG).show();
        }
        /*String[] Aule = {
                "A001",
                "A002",
                "A003",
                "A101",
                "A102",
                "A201",
                "A202",
                "A203",
                "A204",
                "B001",
                "B002",
                "B003",
                "B004",
                "B005",
                "B101",
                "B102",
                "B103",
                "B104",
                "C001",
                "C302",
                "D002",
                "Lab."
        };*/





        //Creazione dimaica tabella

        //Intestazione
        TableLayout stk = (TableLayout) findViewById(R.id.table_main);
        //riga della tabella
        TableRow tbrow0 = new TableRow(this);
        //prima colonna
        TextView tv0 = new TextView(this);
        tv0.setText(" AULA ");
        tbrow0.addView(tv0);
        //seconda colonna
        TextView tv1 = new TextView(this);
        tv1.setText(" INIZIO ");
        tbrow0.addView(tv1);
        //terza colonna
        TextView tv2 = new TextView(this);
        tv2.setText(" FINE ");
        tbrow0.addView(tv2);
        //aggiungo la riga
        stk.addView(tbrow0);

        for (int i = 0; i < Edifici.length; i++) {
            switch(Edifici[i].charAt(9)){
                case 'A':
                    /*for(int k=0;k<auleList.size();k++){
                        Toast.makeText(getApplicationContext(),auleList.get(k).toString(),Toast.LENGTH_LONG).show();
                    }*/
                    for(int k=0;k<auleList.size();k++){
                        Toast.makeText(getApplicationContext(),"aaaaaaaaaaaaa",Toast.LENGTH_LONG).show();
                        if(auleList.get(k).trim().charAt(0)=='A') {
                            //ricorda di creare la riga ogni volta!
                            TableRow tbrow = new TableRow(this);
                            TextView t1v = new TextView(this);
                            t1v.setText(auleList.get(k));
                            t1v.setGravity(Gravity.CENTER);
                            tbrow.addView(t1v);
                            //aggiungo la riga finita
                            stk.addView(tbrow);
                        }
                    }
                    break;
                case 'B':
                    Toast.makeText(getApplicationContext(),"BBBBBBBBBBBBBBBBB",Toast.LENGTH_LONG).show();
                    for(int k=0;k<auleList.size();k++){
                        if(auleList.get(k).trim().charAt(0)=='B') {
                            TableRow tbrow = new TableRow(this);
                            TextView t1v = new TextView(this);
                            t1v.setText(auleList.get(k));
                            t1v.setGravity(Gravity.CENTER);
                            tbrow.addView(t1v);
                            stk.addView(tbrow);
                        }
                    }
                    break;
                case 'C':
                    Toast.makeText(getApplicationContext(),"CCCCCCCCCCC",Toast.LENGTH_LONG).show();
                    for(int k=0;k<auleList.size();k++){
                        if(auleList.get(k).trim().charAt(0)=='C') {
                            TableRow tbrow = new TableRow(this);
                            TextView t1v = new TextView(this);
                            t1v.setText(auleList.get(k));
                            t1v.setGravity(Gravity.CENTER);
                            tbrow.addView(t1v);
                            stk.addView(tbrow);
                        }
                    }
                    break;
            }
        }
    }

    //CLASSE PARSING SULLA PAGINA HTML
    public class ParseURL extends AsyncTask<String, Void, String> {
        ArrayList<String> Info_aule = new ArrayList<String>();
        ArrayList<String> H_aule = new ArrayList<String>();

        @Override
        protected String doInBackground(String... strings) {
            StringBuffer buffer = new StringBuffer();


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
                    buffer.append(Aule[0]+ " ; " + Orari[1] +" \r\n \n");
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
            //respText.setText(s);
        }

      public ArrayList<String> getInfo_aule() {
          return Info_aule;
      }
  }

}
