package com.hazzard.nathan.clickerheroesrootcalculator;


import android.content.Context;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class SaveManager {
    final static String FILENAME = "clickerHeroesSave";

    public static String getSave(Context context) {
        String clickerHeroSave = "";

        try {
            FileOutputStream fileOutput = context.openFileOutput(FILENAME, Context.MODE_PRIVATE);
            OutputStreamWriter osw = new OutputStreamWriter(fileOutput);
            osw.write("I couldn't get your save. Either you haven't granted me root permission, or you don't have clicker heroes installed. If neither of these is the case, then please send a reddit PM to Hazzard13");
            osw.close();
        } catch (Exception e) {
            Toast.makeText(context, "Failed to create file", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }

        RootAccess root = new RootAccess();
        root.execute();

        try {
            FileInputStream fileInput = context.openFileInput(FILENAME);
            InputStreamReader fileReader = new InputStreamReader(fileInput);

            StringBuilder text = new StringBuilder();
            BufferedReader br = new BufferedReader(fileReader);

            String line;
            while ((line = br.readLine()) != null) {
                text.append(line);
                text.append('\n');
            }
            br.close();

            clickerHeroSave = text.toString();
        }catch (Exception e) {
            Toast.makeText(context, "Something went horribly wrong", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }

        return clickerHeroSave;
    }
}
