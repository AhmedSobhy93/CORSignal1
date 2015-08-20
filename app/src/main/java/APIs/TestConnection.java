package APIs;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import JavaBeans.Data;
import Listeners.OnTaskCompleted;


/**
 * Created by Ahmed on 5/23/2015.
 */
public class TestConnection extends AsyncTask<String, String, ArrayList<Data>> {


    ProgressDialog myDialog;
    OnTaskCompleted onComplete ;

    public  TestConnection (ProgressDialog progress , OnTaskCompleted onTask ){


        myDialog = progress ;
        this.onComplete = onTask ;

    }

    //method which working before background
    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        myDialog.setMessage(" Now Loading ........ ");
        myDialog.setCancelable(false);
        myDialog.show();
        Log.d("Makany", "PreExecute");


    }

    @Override
    protected ArrayList<Data> doInBackground(String... params) {

        try{

            HttpClient client=new DefaultHttpClient();
            //post to sending data via HTTP
            HttpPost request=new HttpPost("http://corsignal.com/Blook2/parse_data/employee/show_attendence.php?q=1&for_id=8");

            //response to the request
            HttpResponse response = client.execute(request);

            HttpEntity entity=response.getEntity();

            String result1= EntityUtils.toString(entity);

            JSONArray myArr=new JSONArray(result1);

            ArrayList<Data> allData=new ArrayList<Data>();

            for(int i=0;i<myArr.length();i++) {
                JSONObject oneItem = myArr.getJSONObject(i);
                String day = oneItem.getString("date");
                String checkIn = oneItem.getString("check_in");
                String checkOut = oneItem.getString("check_out");

                Data oneProblem =new Data(day,checkIn,checkOut);

                allData.add(oneProblem);
            }

            return allData;

//            //get STATUS_CODE From STATUS_LINE
//            int result=response.getStatusLine().getStatusCode();
//
//            //return the STATUS_CODE
//            return result;

        }catch(Exception e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(ArrayList<Data> s) {

        myDialog.dismiss();
        try {
            onComplete.onTaskCompleted();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.d("Makany" , "PostExecute");

        super.onPostExecute(s);



    }
}

