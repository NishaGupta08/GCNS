package com.example.nishagupta.gcns;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    public ListView listView;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getApplication().getAssets().open("data.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public void initialize()
    {
        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset());
            listView = (ListView)findViewById(R.id.listView);

            JSONArray result = obj.getJSONArray("data");

            ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>();

            //ArrayAdapter<Map<String, String>> peopleArrayAdapter = new ArrayAdapter<Map<String, String>>(this, R.layout.support_simple_spinner_dropdown_item);

            for(int i=0;i<result.length();i++)
            {
                JSONArray jsonArray = result.getJSONArray(i);
                String fn = jsonArray.getString(0);
                String ln = jsonArray.getString(1);
                String email = jsonArray.getString(4);
                String fullText = fn + " " + ln;

                HashMap<String, String> datum = new HashMap<String, String>(2);
                datum.put("name", fullText);
                datum.put("email",email);

                list.add(datum);

            }

            SimpleAdapter sa = new SimpleAdapter(this, list,
                    R.layout.twolines,
                    new String[] { "name","email" },
                    new int[] {R.id.line_a, R.id.line_b});
            listView.setAdapter(sa);

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
