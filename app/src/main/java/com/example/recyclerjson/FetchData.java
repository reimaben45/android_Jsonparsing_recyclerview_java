package com.example.recyclerjson;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FetchData  extends AsyncTask<Void, Void, String> {
    String data = "";
    public ProgressDialog progressDialog;
    Activity activity;
    Context context;
    JSONArray jsonArray;
    public FetchData(Activity activity, Context context)
    {
        this.activity = activity;
        this.context = context;
       this.progressDialog = new ProgressDialog(this.context);
    }

    public List<ExampleModel> exampleModelList = new ArrayList<>();

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog.dismiss();
        progressDialog.setMessage("loading");
        progressDialog.show();
        progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener()
        {
            public void onCancel(DialogInterface dialog)
            {
                FetchData.this.cancel(true);
            }
        });
       // progressDialog.setCancelable(true);
        //progressDialog.setMessage("Progress");
//        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
//        progressDialog.setProgress(1);
        //progressDialog.show();
    }

    @Override
    protected String doInBackground(Void... voids) {
        //connection
        try {
            URL url = new URL("https://api.androidhive.info/contacts/");
            HttpURLConnection httpURLConnection = null;
            try {
                httpURLConnection = (HttpURLConnection) url.openConnection();
                //inputdata is taken by the input stream
                InputStream inputStream = httpURLConnection.getInputStream();
                //buffered reader takes the input stream
                BufferedReader bufferedReader = new BufferedReader((new InputStreamReader(inputStream)));
                String line = "";
                while (line != null) {
                    line = bufferedReader.readLine();
                    data = data + line;
                }
                return data;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    protected void onPostExecute(String aVoid) {
        //translating the data
            try{
                JSONObject jsonObject = new JSONObject(data);
                JSONArray jsonArray = jsonObject.getJSONArray("contacts");
                  //jsonArray = new JSONArray(data);
                  if(jsonArray!=null) {
                      for(int i = 0; i< jsonArray.length(); i++){
                          JSONObject c = jsonArray.getJSONObject(i);
                          ExampleModel exampleModel = new ExampleModel();
                          exampleModel.id = c.getString("id");
                          exampleModel.name = c.getString("name");
                          exampleModel.gender = c.getString("gender");
                          exampleModelList.add(exampleModel);

                      }
                  }
                progressDialog.dismiss();
                RecyclerView recyclerView;
                recyclerView = (RecyclerView) this.activity.findViewById(R.id.recycleView);
                MyListAdapter myListAdapter = new MyListAdapter(this.context, exampleModelList);
                recyclerView.setAdapter(myListAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(this.context));

            } catch (JSONException e) {
                e.printStackTrace();
            }
            // MyListAdapter.theTextView.setText(this.data);
    }
}
