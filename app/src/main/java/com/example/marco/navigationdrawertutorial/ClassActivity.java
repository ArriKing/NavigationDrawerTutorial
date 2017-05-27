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

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ClassActivity extends AppCompatActivity {

    private TableLayout stk;
    private String oraInizio;
    private String oraFine;
    private String edList;
    private String[] edScelti;
    private ArrayList<String> auleOccupateList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.percorso_di_studio);
        //inserisce la freccia di ritorno alla home
        //PROBLEMA:torna alla home,non al suo fragment ma al main fragment
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //LEGGO I PARAMETRI PASSATI DA CLASSFRAGMENT
        oraInizio = getIntent().getStringExtra("Time_1");
        oraFine = getIntent().getStringExtra("Time_2");
        edList = getIntent().getStringExtra("Ed_list");
        //Edificio scelto
        if(edList.equals(""))
            edScelti = new String[]{"**********"};
        else
            edScelti = edList.split(",");

        auleOccupateList=new ArrayList<String>();

        //Setto testo TexView e Intestazione tabella
        TextView tvShowTime=(TextView)findViewById(R.id.tvShowTimeRange);
        tvShowTime.setText("Aule libere dalle "+oraInizio+" alle "+oraFine);
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
        tv1.setText(" ORARIO ");
        tv1.setTextAppearance(this, R.style.CustomTextView);
        tv1.setGravity(Gravity.CENTER);
        tHeader.addView(tv1);
        //terza colonna
        TextView tv2 = new TextView(this);
        tv2.setText(" STATO ");
        tv2.setTextAppearance(this, R.style.CustomTextView);
        tv2.setGravity(Gravity.RIGHT);
        tHeader.addView(tv2);
        //aggiungo la riga
        stk.addView(tHeader);
    }
    //Carico le aule filtrate in base agli edScelti nella riga
    public void writeAuleFiltrate(TableLayout tbl, ArrayList<String> al, ArrayList<String> ol, char ed){
        createBusyList(al,ol);
        for(int k=0;k<al.size();k++){
            boolean foundBusy=false;
            if(al.get(k).trim().charAt(0)==ed){
                for(int i=0;i<auleOccupateList.size();i++){
                    if(al.get(k).equals(auleOccupateList.get(i)))
                        foundBusy=true;
                }
                if(!foundBusy){
                    //ricorda di creare la riga ogni volta!
                    TableRow tbRow = new TableRow(this);
                    //Aggiungo l'aula
                    TextView tAula = new TextView(this);
                    tAula.setText(al.get(k));
                    tAula.setGravity(Gravity.LEFT);
                    tbRow.addView(tAula);
                    //Aggiungo l'orario
                    TextView tOrario = new TextView(this);
                    tOrario.setText(ol.get(k));
                    tOrario.setGravity(Gravity.CENTER);
                    tbRow.addView(tOrario);
                    //Aggiungo lo stato
                    TextView tStato = new TextView(this);
                    tStato.setText("LIBERA");
                    tStato.setGravity(Gravity.RIGHT);
                    tbRow.addView(tStato);
                    //aggiungo la riga finita
                    tbl.addView(tbRow);
                }
            }
        }
    }
    //Carico tutte le aule nella riga
    public void writeAllAule(TableLayout tbl, ArrayList<String> al, ArrayList<String> ol){
        for(int k=0;k<al.size();k++){
            //ricorda di creare la riga ogni volta!
            TableRow tbRow = new TableRow(this);
            //Aggiungo l'aula
            TextView tAula = new TextView(this);
            tAula.setText(al.get(k));
            tAula.setGravity(Gravity.LEFT);
            tbRow.addView(tAula);
            //Aggiungo l'orario
            TextView tOrario = new TextView(this);
            tOrario.setText(ol.get(k));
            tOrario.setGravity(Gravity.CENTER);
            tbRow.addView(tOrario);
            //Aggiungo lo stato
            TextView tStato = new TextView(this);
            tStato.setText("-TODO-");
            tStato.setGravity(Gravity.RIGHT);
            tbRow.addView(tStato);
            //aggiungo la riga finita
            tbl.addView(tbRow);
        }
    }
    //Carico tutte le righe in tabella
    public void loadTableRow(String[] edifici, ArrayList<String> al, ArrayList<String> ol, TableLayout tbl){
        for (int i = 0; i < edifici.length; i++) {
            switch(edifici[i].charAt(9)){
                case 'A':
                    writeAuleFiltrate(tbl,al, ol, 'A');
                    break;
                case 'B':
                    writeAuleFiltrate(tbl, al, ol, 'B');
                    break;
                case 'C':
                    writeAuleFiltrate(tbl, al, ol, 'C');
                    break;
                default:
                    writeAllAule(tbl, al, ol);
                    break;
            }
        }
    }

    //Controllo orario
    public boolean isBusy(ArrayList<String> ol, int indice){
        SimpleDateFormat parser = new SimpleDateFormat("HH.mm");
        Date beginLimit;
        Date endLimit;
        Date beginClass;
        Date endClass;
        boolean busy=false;
        String orari[]=ol.get(indice).split("-");
        try{
            beginLimit=parser.parse(oraInizio);
            endLimit=parser.parse(oraFine);
            beginClass=parser.parse(orari[0]);
            endClass=parser.parse(orari[1]);
            if(((beginClass.after(beginLimit) || beginClass.equals(beginLimit)) && beginClass.before(endLimit)) || (endClass.before(endLimit) || endClass.equals(endLimit))){
                busy=true;
            }else
                busy=false;
        }catch (ParseException e){
            //DO_SOMETHING
            Toast.makeText(getApplicationContext(),"PARSEEXCEPTION",Toast.LENGTH_LONG).show();
        }
        return busy;
    }

    public void createBusyList(ArrayList<String> al, ArrayList<String> ol){
        for(int i=0;i<al.size();i++){
            if(isBusy(ol, i)){
               auleOccupateList.add(al.get(i));
            }
        }
    }



//CLASSE PARSING SULLA PAGINA HTML
    public class ParseURL extends AsyncTask<String, Void, ArrayList<String>> {
        ArrayList<String> auleList = new ArrayList<String>();
        ArrayList<String> orariList = new ArrayList<String>();

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
                        auleList.add(aule[0]);
                        String orari[]=cols.get(3).text().split("\\)");
                        orariList.add(orari[1]);
                    }
                    //buffer.append(aule[0]+ " ; " + orari[1] +" \r\n \n");
                }
            }
            catch(Throwable t) {
                t.printStackTrace();
            }

            //return buffer.toString();
            return auleList;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        //Attraverso l'attributo s viene passato il return del metodo doInBackground, quindi auleList
        protected void onPostExecute(ArrayList<String> s) {
            super.onPostExecute(s);
            loadTableRow(edScelti, s, orariList ,stk);
        }
  }//end ParseURL
}//end ClassActivity
