package com.dimakaplin143.listwithdelete;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Storage {

    final String LOG_TAG = "myLogs";

    public File getPrivateDocStorageDir(Context context, String fileName) {

        File file = new File(context.getExternalFilesDir(
                Environment.DIRECTORY_DOCUMENTS), fileName);
        if (!context.getExternalFilesDir(
                Environment.DIRECTORY_DOCUMENTS).mkdirs()) {
            Log.e(LOG_TAG, "Directory not created");
        }
        return file;
    }

    public void writeFile(File file, String fileText)  {

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            bw.write(fileText);
            bw.close();
            Log.d(LOG_TAG, "Файл записан " + fileText);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String readFile(File file) {

        StringBuilder text = new StringBuilder();

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            text.append("");
            while ((line = br.readLine()) != null) {
                text.append(line);

            }
        }
        catch (IOException e) {
            Log.e(LOG_TAG, "READ " + e.getMessage());
        }
        Log.e(LOG_TAG, "READED " + text.toString());
        return text.toString();
    }

}
