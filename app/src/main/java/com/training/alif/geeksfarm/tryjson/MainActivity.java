package com.training.alif.geeksfarm.tryjson;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    TextView tvNm,tvAge,tvKel,lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initiate();
        DeserialisasiJson();
    }

    private void DeserialisasiJson(){
        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset());
            JSONObject obj2 = obj.getJSONObject("person");
            JSONArray ar = obj2.getJSONArray("address");
            String names = obj2.getString("nama");
            int umur = obj2.getInt("umur");
            String gender = obj2.getString("kelamin");
            for (int i =0;i<ar.length();i++){
                JSONObject objData = ar.getJSONObject(i);
                String alamats = objData.getString("alamatInt");
                String alamatLeng = objData.getString("alamatLengkap");
                String kota = objData.getString("kota");
                String add = "alamat : "+alamats + "\n" + "alamat lengkap : " + alamatLeng + "\n" + "kota : " + kota +"\n";
                lv.append("\n"+add);
            }
            tvNm.setText("Nama : "+names);
            tvAge.setText("Umur : "+umur);
            tvKel.setText("Kelamin : "+gender);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            //InputStream is = getActivity().getAssets().open("yourfilename.json");
            InputStream is = getResources().openRawResource(R.raw.biodata);
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
    private void initiate(){
        tvNm = findViewById(R.id.textNama);
        tvAge = findViewById(R.id.textAge);
        tvKel = findViewById(R.id.TextGender);
        lv = findViewById(R.id.lv);
    }
}
