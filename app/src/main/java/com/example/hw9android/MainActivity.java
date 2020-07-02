package com.example.hw9android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;



import androidx.appcompat.app.AppCompatActivity;

import android.text.TextUtils;
//import android.text.TextWatcher;
import android.util.Log;
import android.view.View;

import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setContentView(R.layout.form);
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.planets_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);

    }

    public void setupViews(View view) {
        TextView input_validation = (TextView) findViewById(R.id.input_validation);
        TextView price_validation = (TextView) findViewById(R.id.price_validation);
        final EditText myEditText = (EditText) findViewById(R.id.keyword);
        final String letter = myEditText.getText().toString();

        final EditText min_text = (EditText) findViewById(R.id.min);
        float min_letter;
        if (TextUtils.isEmpty(min_text.getText().toString())) {
            min_letter = 0F;
        } else {
            min_letter = Float.parseFloat(min_text.getText().toString().trim());
        }

        final EditText max_text = (EditText) findViewById(R.id.max);
        float max_letter;
        if (TextUtils.isEmpty(max_text.getText().toString())) {
            max_letter = 0F;
        } else {
            max_letter = Float.parseFloat(max_text.getText().toString().trim());
        }

        if (letter.length() == 0) {
            input_validation.setVisibility(view.VISIBLE);
        } else {
            input_validation.setVisibility(view.GONE);
        }
        if (min_letter > max_letter && max_letter > 0) {
            price_validation.setVisibility(view.VISIBLE);
        } else {
            price_validation.setVisibility(view.GONE);
        }

        CheckBox checkBox1 = (CheckBox) findViewById(R.id.checkbox1);
        String checkBox1letter = checkBox1.getText().toString();
        CheckBox checkBox2 = (CheckBox) findViewById(R.id.checkbox2);
        String checkBox2letter = checkBox2.getText().toString();
        CheckBox checkBox3 = (CheckBox) findViewById(R.id.checkbox3);
        String checkBox3letter = checkBox3.getText().toString();

        List<String> myIntArray = new ArrayList<String>();

        //listOfString.add("foo");
        //listOfString.add("bar");
        //String myIntArray[] = new String[3];
        if(checkBox1.isChecked()){
            myIntArray.add(checkBox1letter);
        }
        if(checkBox2.isChecked()){
            myIntArray.add(checkBox2letter);
        }
        if(checkBox3.isChecked()){
            myIntArray.add(checkBox3letter);
        }
        //myIntArray[0] = checkBox1letter;
        //myIntArray[1] = checkBox2letter;
        //myIntArray[2] = checkBox3letter;

        //String[] myIntArray = {checkBox1letter, checkBox2letter};
        Log.d("url", String.valueOf(myIntArray));
        Spinner sp = (Spinner)findViewById(R.id.spinner);
        String selected = sp.getSelectedItem().toString();
        Log.d("select",selected);

        final Intent intent = new Intent(this, load.class);

        if(input_validation.getVisibility() == View.GONE && price_validation.getVisibility() == View.GONE){
            RequestQueue queue = Volley.newRequestQueue(this);
            RelativeLayout spinner=(RelativeLayout)findViewById(R.id.loading);
            spinner.setVisibility(View.VISIBLE);
            final String URL = "https://csci571hw6-425.wl.r.appspot.com/info?keywords="+letter+"&minprice="+min_text.getText().toString()+"&maxprice="+max_text.getText().toString()+"&condition="+String.valueOf(myIntArray)+"&sort="+selected;
            Log.d("url",URL);
            StringRequest sr = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    //Toast.makeText(getApplicationContext(),response.toString()+" returned from node.js server",Toast.LENGTH_SHORT).show();
                    //Log.d("result",response.toString());

                    try {
                        String obj = new JSONObject(response).getString("findItemsAdvancedResponse");
                        Log.d("My App", obj);

                        JSONArray obj2 = new JSONArray(obj);
                        JSONObject obj3 = obj2.getJSONObject(0);


                        String paginationOutput = obj3.getString("paginationOutput");
                        JSONArray obj6 = new JSONArray(paginationOutput);
                        JSONObject obj7 = obj6.getJSONObject(0);
                        String count = obj7.getString("totalEntries");
                        Log.d("My App", count);
                        if(count.equals("[\"0\"]")){
                            Log.d("My App", count);
                            intent.putExtra("count", count);
                        }else {
                            String searchResult = obj3.getString("searchResult");
                            JSONArray obj4 = new JSONArray(searchResult);
                            JSONObject obj5 = obj4.getJSONObject(0);
                            String item = obj5.getString("item");

                            //JSONArray obj2 = new JSONObject(obj).getJSONArray();
                            Log.d("My App", item.toString());
                            //Log.d("count", count.toString());

                            intent.putExtra("item", item.toString());
                            intent.putExtra("keyword", letter);
                            intent.putExtra("count", count);

                        }startActivity(intent);
                    } catch (Throwable t) {
                        Log.e("My App", "Could not parse malformed JSON: \"" + response + "\"");
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(),error.toString()+"The Server returned error",Toast.LENGTH_SHORT).show();

                }
            });
            RequestQueue r = Volley.newRequestQueue(getApplicationContext());
            r.add(sr);
        }




    }

    public void clearViews(View view) {
        EditText myEditText = (EditText) findViewById(R.id.keyword);
        EditText min_text = (EditText) findViewById(R.id.min);
        EditText max_text = (EditText) findViewById(R.id.max);
        myEditText.getText().clear();
        min_text.getText().clear();
        max_text.getText().clear();
        TextView input_validation = (TextView) findViewById(R.id.input_validation);
        TextView price_validation = (TextView) findViewById(R.id.price_validation);
        input_validation.setVisibility(view.GONE);
        price_validation.setVisibility(view.GONE);

        CheckBox checkBox1 = (CheckBox) findViewById(R.id.checkbox1);
        if (checkBox1.isChecked()) {
            checkBox1.setChecked(false);
        }
        CheckBox checkBox2 = (CheckBox) findViewById(R.id.checkbox2);
        if (checkBox2.isChecked()) {
            checkBox2.setChecked(false);
        }
        CheckBox checkBox3 = (CheckBox) findViewById(R.id.checkbox3);
        if (checkBox3.isChecked()) {
            checkBox3.setChecked(false);
        }
        Spinner sp = (Spinner)findViewById(R.id.spinner);
        sp.setSelection(0);
    }


}