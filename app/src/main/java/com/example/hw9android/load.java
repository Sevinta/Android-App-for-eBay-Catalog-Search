package com.example.hw9android;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



public class load extends AppCompatActivity {


    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);



        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String keywords = intent.getStringExtra("keyword");
        final String message = intent.getStringExtra("item");
        String count = intent.getStringExtra("count");
        JSONArray obj9 = null;
        int count2 = 0;

        if (count.equals("[\"0\"]")) {
            RelativeLayout norecord = (RelativeLayout) findViewById(R.id.norecord);
            norecord.setVisibility(View.VISIBLE);
            Toast.makeText(getApplicationContext(), "No Records", Toast.LENGTH_SHORT).show();
            ScrollView scroll = (ScrollView)findViewById(R.id.scrolltable);
            scroll.setVisibility(View.GONE);

        } else {
        JSONArray obj2 = null;
        try {
            GridLayout mainGrid = findViewById(R.id.mainGrid);
            // final int N = 10; // total number of textviews to add
            // final CardView[] myTextViews = new CardView[N]; // create an empty array;
            //mainGrid.getChildCount()
            obj2 = new JSONArray(message);
            for (int i = 0; i < obj2.length(); i++) {

                Log.d("length111", String.valueOf(obj2.length()));
                JSONObject obj3 = obj2.getJSONObject(i);

                final String title = obj3.getString("title");
                final String itemid = obj3.getString("itemId");
                final String itemId = itemid.substring(2, itemid.length() - 2);

                String image = obj3.getString("galleryURL");
                String share = obj3.getString("viewItemURL");
                final String share2 = share.substring(2,share.length()-2);
                String substring = image.substring(2, image.length() - 2);
                if (substring.equals("https:\\/\\/thumbs1.ebaystatic.com\\/pict\\/04040_0.jpg")) {
                    substring = "https://www.csci571.com/hw/hw6/images/ebay_default.jpg";
                }

                String condition = obj3.getString("condition");
                String condition2 = new JSONArray(condition).getJSONObject(0).getString("conditionDisplayName");

                final String shipping = obj3.getString("shippingInfo");
                String shipping2 = new JSONArray(shipping).getJSONObject(0).getString("shippingServiceCost");
                String shipping3 = new JSONArray(shipping2).getJSONObject(0).getString("__value__");


                String toprate = obj3.getString("topRatedListing");

                String price = obj3.getString("sellingStatus");
                String price2 = new JSONArray(price).getJSONObject(0).getString("convertedCurrentPrice");
                String price3 = new JSONArray(price2).getJSONObject(0).getString("__value__");



                CardView cardView = (CardView) mainGrid.getChildAt(i);
                cardView.setVisibility(View.VISIBLE);
                ViewGroup viewGroup = ((ViewGroup) cardView.getChildAt(0));
                ImageView itemimage = (ImageView) viewGroup.getChildAt(0);

                final Intent detailinfo = new Intent(load.this, detailinfo.class);
                itemimage.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                       // RequestQueue queue = Volley.newRequestQueue(load.this);
                        RelativeLayout spinner=(RelativeLayout)findViewById(R.id.todetail);
                        spinner.setVisibility(View.VISIBLE);
                        ScrollView scrollView = (ScrollView)findViewById(R.id.scrolltable);
                        scrollView.setVisibility(View.GONE);
                        TextView textView = (TextView) findViewById(R.id.textView);
                        textView.setVisibility(View.GONE);

                        final String URL = "https://csci571hw6-425.wl.r.appspot.com/detail?itemid="+itemId;
                        StringRequest sr = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    detailinfo.putExtra("info", message);
                                    detailinfo.putExtra("title", title);
                                    detailinfo.putExtra("item", response);
                                    detailinfo.putExtra("share",share2);
                                    startActivity(detailinfo);

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
                });



                Picasso.get().load(substring).into(itemimage);
                TextView itemtitle = (TextView) viewGroup.getChildAt(1);
                TextView itemshipping = (TextView) viewGroup.getChildAt(2);
                TextView itemtoprate = (TextView) viewGroup.getChildAt(3);
                LinearLayout condition_price = (LinearLayout) viewGroup.getChildAt(4);
                TextView itemcondition = (TextView) condition_price.getChildAt(0);
                TextView itemprice = (TextView) condition_price.getChildAt(1);


                String htmlText = "<body><h3><font color='black'face='sans-serif-black'>" + title.substring(2, title.length() - 2) + "</font></h3></body>";
                itemtitle.setText(Html.fromHtml(htmlText));


                if (shipping3.equals("0.0")) {
                    String htmlShipping = "<body><h5><font color='Grey' face='sans-serif-black'>FREE </font><font color='Grey' face='sans-serif-thin'>Shipping</font></h5></body>";
                    itemshipping.setText(Html.fromHtml(htmlShipping));
                } else {
                    String htmlShipping = "<body><h5><font color='Grey' face='sans-serif-thin'>Ships for </font><font color='Grey'face='sans-serif-black'>$" + shipping3 + "</font></h5></body>";
                    itemshipping.setText(Html.fromHtml(htmlShipping));
                }

                if (toprate.equals("[\"true\"]")) {
                    String htmltoprate = "<body><h5><font color='Grey' face='sans-serif-black'>Top Rated Listing </font></h5></body>";
                    itemtoprate.setText(Html.fromHtml(htmltoprate));
                }

                String htmlCondition = "<body><h6><font color='Grey'face='sans-serif-black'>" + condition2.substring(2, condition2.length() - 2) + "</font></h6></body>";
                itemcondition.setText(Html.fromHtml(htmlCondition));

                String htmlPrice = "<body><h6><font color='#AFC94C'face='sans-serif-black'>$" + price3 + "</font></h6></body>";
                itemprice.setText(Html.fromHtml(htmlPrice));

                count2 = count2 +1;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d("count2222", String.valueOf(count2));
        TextView textView = findViewById(R.id.textView);
            String htmlcount = "<body><h4><font color='Grey 'face='sans-serif-black'>Showing </font><font color='#6076D8'face='sans-serif-black'>"+String.valueOf(count2)+"</font><font color='Grey 'face='sans-serif-black'> results for </font><font color='#6076D8' face='sans-serif-black'>" + keywords + "</font></h4></body>";
        textView.setText(Html.fromHtml(htmlcount));


    }

    }



}