package com.dimakaplin143.listwithdelete;


import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    final String STRING_ARRAY = "samples.txt";
    final String LOG_TAG = "myLogs";
    Storage storage;
    private ArrayList<Sample> samples = new ArrayList<>();
    private SampleAdapter sampleAdapter;
    /*private SwipeRefreshLayout swipeLayout;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        storage = new Storage();
        createSamplesFromStrings();

        sampleAdapter = new SampleAdapter(this, samples);
        ListView list = findViewById(R.id.list);
        list.setAdapter(sampleAdapter);

    }

    private void createSamplesFromStrings() {
        String samplesString = storage.readFile(storage.getPrivateDocStorageDir(this, STRING_ARRAY));
        if(!"".equals(samplesString)) {
            Log.e(LOG_TAG,  "BBBB"+ samplesString);
            String[] strings = samplesString.split(";");
            for(String str : strings) {
                String[] items = str.split("//");
                samples.add(new Sample(items[0], items[1], items[2]));
            }
        } else {
            Log.e(LOG_TAG,  "aAAAA"+ samplesString);
            String[] strings = getResources().getStringArray(R.array.garage_items);
            StringBuilder text = new StringBuilder();

            for (int i = 0; i < strings.length; i++) {
                text.append(strings[i]);
                if(i+1 != strings.length) {
                    text.append(";");
                }

            }

            storage.writeFile(storage.getPrivateDocStorageDir(this, STRING_ARRAY), text.toString());
            System.out.println(storage.readFile(storage.getPrivateDocStorageDir(this, STRING_ARRAY)));
            for(String str : strings) {
                String[] items = str.split("//");
                samples.add(new Sample(items[0], items[1], items[2]));
            }

        }


    }




}
