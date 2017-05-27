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

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class ClassActivity extends AppCompatActivity {

    private TableLayout stk;
    private String Inizio;
    private String Fine;
    private String edList;
    private String[] edScelti;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.percorso_di_studio);
        //inserisce la freccia di ritorno alla home
        //PROBLEMA:torna alla home,non al suo fragment ma al main fragment
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //LEGGO I PARAMETRI PASSATI DA CLASSFRAGMENT
        Inizio = getIntent().getStringExtra("Time_1");
        Fine = getIntent().getStringExtra("Time_2");
        edList = getIntent().getStringExtra("Ed_list");
        //Edificio scelto
        if(edList.equals(""))
            edScelti = new String[]{"**********"};
        else
            edScelti = edList.split(",");

        createTableHeader();

        String todayUrl = "http://www03.unibg.it//orari//orario_giornaliero.php?db=IN&data=oggi&orderby=ora";
        String otherDayUrl = "http://www03.unibg.it//orari//orario_giornaliero.php?db=IN&data=29/05/2017&orderby=ora";
        //Aziono il parsing della pagina html con gli orari
        ( new ClassActivity.ParseURL() ).execute(new String[]{otherDayUrl});

    }


    public void onStart(){
        super.onStart();
    }

    //Creo intestazione tabella
    public void createTableHeader(){
        //Creazione intestazione tabella dinamica
        stk = (TableLayout) findViewById(R.id.table_main);
        //riga della tabella
        TableRow tHeader = new TableRow(this);
        //prima colonna
        TextView tv0 = new TextView(this);
        tv0.setText(" AULA ");
        tv0.setTextAppearance(this, R.style.CustomTextView);
        tv0.setGravity(Gravity.LEFT);
        tHeader.addView(tv0);
        //seconda colonna
        TextView tv1 = new TextView(this);
        tv1.setText(" INIZIO ");
        tv1.setTextAppearance(this, R.style.CustomTextView);
        tv1.setGravity(Gravity.CENTER);
        tHeader.addView(tv1);
        //terza colonna
        TextView tv2 = new TextView(this);
        tv2.setText(" FINE ");
        tv2.setTextAppearance(this, R.style.CustomTextView);
        tv2.setGravity(Gravity.RIGHT);
        tHeader.addView(tv2);
        //aggiungo la riga
        stk.addView(tHeader);
    }
    //Carico le aule filtrate in base agli edScelti nella riga
    public void writeAuleFiltrate(TableLayout tbl, ArrayList<String> al, char ed){
        for(int k=0;k<al.size();k++){
            if(al.get(k).trim().charAt(0)==ed) {
                //ricorda di creare la riga ogni volta!
                TableRow tbrow = new TableRow(this);
                TextView t1v = new TextView(this);
                t1v.setText(al.get(k));
                t1v.setGravity(Gravity.LEFT);
                tbrow.addView(t1v);
                //aggiungo la riga finita
                tbl.addView(tbrow);
            }
        }
    }
    //Carico tutte le aule nella riga
    public void writeAllAule(TableLayout tbl, ArrayList<String> al){
        for(int k=0;k<al.size();k++){
            //ricorda di creare la riga ogni volta!
            TableRow tbrow = new TableRow(this);
            TextView t1v = new TextView(this);
            t1v.setText(al.get(k));
            t1v.setGravity(Gravity.LEFT);
            tbrow.addView(t1v);
            //aggiungo la riga finita
            tbl.addView(tbrow);
        }
    }
    //Carico tutte le righe in tabella
    public void loadTableRow(String[] edifici, ArrayList<String> al, TableLayout tbl){
        for (int i = 0; i < edifici.length; i++) {
            switch(edifici[i].charAt(9)){
                case 'A':
                    writeAuleFiltrate(tbl,al, 'A');
                    break;
                case 'B':
                    writeAuleFiltrate(tbl, al, 'B');
                    break;
                case 'C':
                    writeAuleFiltrate(tbl, al, 'C');
                    break;
                default:
                    writeAllAule(tbl, al);
                    break;
            }
        }
    }




    //CLASSE PARSING SULLA PAGINA HTML
    public class ParseURL extends AsyncTask<String, Void, ArrayList<String>> {

        ArrayList<String> infoAule = new ArrayList<String>();
        ArrayList<String> hAule = new ArrayList<String>();

        @Override
        protected ArrayList<String> doInBackground(String... strings) {
            //StringBuffer buffer = new StringBuffer();


            try {
                Log.d("JSwa", "Connecting to ["+strings[0]+"]");
                Document doc  = Jsoup.connect(strings[0]).get();
                Log.d("JSwa", "Connected to ["+strings[0]+"]");

                Element table = doc.select("table").get(0);
                Elements rowElems = table.select("tr");
                //buffer.append("AULE DATA\r\n");

                for(int i=1; i<rowElems.size();i++){
                    Element row = rowElems.get(i);
                    Elements cols = row.select("td");
                    String aule[]=cols.get(3).text().split("\\(");
                    //Prendo solo le aule degli ed A,B e C
                    if((aule[0].charAt(0)=='A') || (aule[0].charAt(0)=='B') || (aule[0].charAt(0)=='C')){
                        infoAule.add(aule[0]);
                        String orari[]=cols.get(3).text().split("\\)");
                        hAule.add(orari[1]);
                    }
                    //buffer.append(aule[0]+ " ; " + orari[1] +" \r\n \n");
                }
            }
            catch(Throwable t) {
                t.printStackTrace();
            }

            //return buffer.toString();
            return infoAule;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(ArrayList<String> s) {
            super.onPostExecute(s);
            loadTableRow(edScelti, s,stk);
        }
  }//end ParseURL
}//end ClassActivity
