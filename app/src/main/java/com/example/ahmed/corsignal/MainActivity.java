package com.example.ahmed.corsignal;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import APIs.TestConnection;
import JavaBeans.Data;
import Listeners.OnTaskCompleted;


public class MainActivity extends ActionBarActivity implements OnTaskCompleted {

    private TestConnection testConn;
    private ArrayList<Data> allData;
    private List<String> dayAL,checkInAL,checkOutAL;
    private ListView dayLV,checkInLV,checkOutLV;
    private Data oneData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dayLV=(ListView)findViewById(R.id.Main_Day_ListView);
        checkInLV=(ListView)findViewById(R.id.Main_CheckIn_ListView);
        checkOutLV=(ListView)findViewById(R.id.Main_CheckOut_ListView);

        dayAL=new ArrayList<String>();
        checkInAL=new ArrayList<String>();
        checkOutAL=new ArrayList<String>();

        ProgressDialog ps = new ProgressDialog(this);

        testConn=new TestConnection(ps,this);
        testConn.execute();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //OnTaskCompleted Retrieve all data in ListView
    @Override
    public void onTaskCompleted() throws ExecutionException, InterruptedException {

        allData=testConn.get();

        for(int i=0;i<allData.size();i++){

            oneData=allData.get(i);

            String date=oneData.getDay();
            dayAL.add(date);

           // Toast.makeText(MainActivity.this,dayAL.get(i).toString(),Toast.LENGTH_SHORT).show();

            String checkIN=allData.get(i).getCheckIn();
            checkInAL.add(checkIN);

            String checkOUT=allData.get(i).getCheckOut();
            checkOutAL.add(checkOUT);

//            oneData=allData.get(i);
//            Toast.makeText(this,oneData.getDay()+oneData.getCheckIn()+oneData.getCheckOut(),Toast.LENGTH_SHORT).show();
        }

        ArrayAdapter<String> adapter=new ArrayAdapter<>(MainActivity.this,android.R.layout.simple_list_item_1,dayAL);
        dayLV.setAdapter(adapter);

        ArrayAdapter<String> adapter1=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,checkInAL);
        checkInLV.setAdapter(adapter1);

        ArrayAdapter<String> adapter2=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,checkOutAL);
        checkOutLV.setAdapter(adapter2);
    }
}
