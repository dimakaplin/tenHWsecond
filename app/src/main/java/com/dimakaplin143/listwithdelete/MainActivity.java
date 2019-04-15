package com.dimakaplin143.listwithdelete;


import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Sample> samples = new ArrayList<>();
    private SampleAdapter sampleAdapter;
    /*private SwipeRefreshLayout swipeLayout;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createSamplesFromStrings();
        sampleAdapter = new SampleAdapter(this, samples);
        ListView list = findViewById(R.id.list);
        list.setAdapter(sampleAdapter);

/*        swipeLayout = findViewById(R.id.swiperefresh);
        swipeLayout.setOnRefreshListener(()-> {
            createSamplesFromStrings();
            sampleAdapter.notifyDataSetChanged();
            swipeLayout.setRefreshing(false);

        });*/

    }

    private void createSamplesFromStrings() {
        String[] strings = getResources().getStringArray(R.array.garage_items);
        for(String str : strings) {
            String[] items = str.split("//");
            samples.add(new Sample(items[0], items[1], items[2]));
        }
    }

}
