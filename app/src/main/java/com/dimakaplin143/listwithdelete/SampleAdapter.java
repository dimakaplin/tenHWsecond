package com.dimakaplin143.listwithdelete;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SampleAdapter extends BaseAdapter {
    final String LOG_TAG = "myLogs";
    final String STRING_ARRAY = "samples.txt";
    Context ctx;
    LayoutInflater lInflater;
    Storage storage = new Storage();
    private List<Sample> samples;


    public SampleAdapter(Context ctx, List<Sample> samples) {
        this.samples = samples;
        this.ctx = ctx;
        lInflater = (LayoutInflater) ctx
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return samples.size();
    }

    @Override
    public Object getItem(int position) {
        return samples.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private Sample getSample(int position) {
        return ((Sample) getItem(position));
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = lInflater.inflate(R.layout.sample, parent, false);
        }

        Sample s = getSample(position);

        // заполняем View в пункте списка данными из товаров: наименование, цена
        // и картинка
        ((TextView) view.findViewById(R.id.sample_name)).setText(s.getName());
        ((TextView) view.findViewById(R.id.sample_target)).setText(s.getTarget());
        ((ImageView) view.findViewById(R.id.icon_elem)).setImageResource(s.getImgId());

        Button btnDelete = (Button) view.findViewById(R.id.btn_delete);

        btnDelete.setOnClickListener((v) -> {
            Sample deleteSample = this.samples.get((int) v.getTag());
            String sampleString = storage.readFile(storage.getPrivateDocStorageDir(ctx, STRING_ARRAY));
            String[] sampleArr = sampleString.split(";");
            StringBuilder text = new StringBuilder();
            for (int i = 0; i < sampleArr.length; i++) {
                String[] strArr = sampleArr[i].split("//");
                if (strArr.length < 3) {
                    break;
                }

                if (!strArr[0].equals(deleteSample.getName()) && !strArr[1].equals(deleteSample.getTarget()) && !strArr[2].equals(deleteSample.getType())) {
                    text.append(sampleArr[i]);
                    if (i + 1 != sampleArr.length) {
                        text.append(";");
                    }

                }

            }

            storage.writeFile(storage.getPrivateDocStorageDir(ctx, STRING_ARRAY), text.toString());


            this.samples.remove((int) v.getTag());

            notifyDataSetChanged();
        });

        view.setOnLongClickListener(v -> {
            Toast.makeText(ctx, s.getName(), Toast.LENGTH_SHORT).show();
            return true;
        });

        btnDelete.setTag(position);
        return view;
    }

}
