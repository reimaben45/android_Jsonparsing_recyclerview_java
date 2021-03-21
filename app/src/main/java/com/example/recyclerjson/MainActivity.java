package com.example.recyclerjson;

import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //RecyclerView recyclerView;
    //private List<Object> viewItems = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try{
            //
            //FetchData fetchdata = (FetchData) new FetchData(this, this).execute();
            FetchData fetchdata = new FetchData(this, this);
            fetchdata.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


