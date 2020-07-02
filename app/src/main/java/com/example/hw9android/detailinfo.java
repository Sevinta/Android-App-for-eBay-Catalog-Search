package com.example.hw9android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import androidx.fragment.app.Fragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class detailinfo extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private TabItem tab1,tab2,tab3;
    public PageAdapter pageradapter;
    List<String> myshipping = new ArrayList<String>();
    List<String> myseller = new ArrayList<String>();
    List<String> mypicture = new ArrayList<String>();
    List<String> myinfo = new ArrayList<String>();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailinfo);

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String item = intent.getStringExtra("item");
        String info = intent.getStringExtra("info");
        final String share = intent.getStringExtra("share");

        try {
            String obj = new JSONObject(item).getString("Item");
            JSONArray obj2 = new JSONArray(info);
            JSONObject obj3 = obj2.getJSONObject(0);
            JSONObject sub = new JSONObject(obj);

            final String shipping = obj3.getString("shippingInfo");
            JSONObject ship =  new JSONArray(shipping).getJSONObject(0);
            if(ship.has("oneDayShippingAvailable")&&ship.has("shippingType")&&ship.has("expeditedShipping")
                    &&sub.has("HandlingTime")&&sub.has("Location")&&sub.has("ShipToLocations")) {
                String onedayshipping = new JSONArray(shipping).getJSONObject(0).getString("oneDayShippingAvailable");
                String shippingtype = new JSONArray(shipping).getJSONObject(0).getString("shippingType");
                String expedited = new JSONArray(shipping).getJSONObject(0).getString("expeditedShipping");

                String HandlingTime = new JSONObject(obj).getString("HandlingTime");
                String location = new JSONObject(obj).getString("Location");
                String shiptolocation = new JSONObject(obj).getString("ShipToLocations");

                myshipping.add(HandlingTime);
                myshipping.add(onedayshipping.substring(2, onedayshipping.length() - 2));
                myshipping.add(shippingtype.substring(2, shippingtype.length() - 2));
                myshipping.add(location);
                myshipping.add(shiptolocation.substring(2, shiptolocation.length() - 2));
                myshipping.add(expedited.substring(2, expedited.length() - 2));
            }else{
                myshipping.add("1");
            }

            String seller = new JSONObject(obj).getString("Seller");
            String feedbackscore = new JSONObject(seller).getString("FeedbackScore");
            String userid = new JSONObject(seller).getString("UserID");
            String PositiveFeedbackPercent = new JSONObject(seller).getString("PositiveFeedbackPercent");
            String FeedbackRatingStar = new JSONObject(seller).getString("FeedbackRatingStar");

            myseller.add(feedbackscore);
            myseller.add(userid);
            myseller.add(PositiveFeedbackPercent);
            myseller.add(FeedbackRatingStar);

            String ReturnPolicy = new JSONObject(obj).getString("ReturnPolicy");
            String Refund = new JSONObject(ReturnPolicy).getString("Refund");
            String ReturnsWithin = new JSONObject(ReturnPolicy).getString("ReturnsWithin");
            String ShippingCostPaidBy = new JSONObject(ReturnPolicy).getString("ShippingCostPaidBy");
            String ReturnsAccepted = new JSONObject(ReturnPolicy).getString("ReturnsAccepted");


            myseller.add(Refund);
            myseller.add(ReturnsWithin);
            myseller.add(ShippingCostPaidBy);
            myseller.add(ReturnsAccepted);



            JSONArray PictureURL = new JSONObject(obj).getJSONArray("PictureURL");
            for (int i = 0; i < PictureURL.length(); i++) {
                String oneObject = PictureURL.getString(i);
                mypicture.add(oneObject);
            }

            if(sub.has("Title")){
                String Title = new JSONObject(obj).getString("Title");
                myinfo.add(Title);
            }else{
                myinfo.add("1");
            }


            String price = obj3.getString("sellingStatus");
            String price2 = new JSONArray(price).getJSONObject(0).getString("convertedCurrentPrice");
            String price3 = new JSONArray(price2).getJSONObject(0).getString("__value__");
            myinfo.add(price3);

            String shipping2 = new JSONArray(shipping).getJSONObject(0).getString("shippingServiceCost");
            String shipping3 = new JSONArray(shipping2).getJSONObject(0).getString("__value__");
            myinfo.add(shipping3);


            if(sub.has("Subtitle")){
                String Subtitle = sub.getString("Subtitle");
                myinfo.add(Subtitle);
            }else{
                String Subtitle = "1";
                myinfo.add(Subtitle);
            }

            if(sub.has("ItemSpecifics")){
                String Specifications = new JSONObject(obj).getString("ItemSpecifics");
                String NameValueList = new JSONObject(Specifications).getString("NameValueList");
                String Brand = new JSONArray(NameValueList).getJSONObject(0).getString("Value");
                myinfo.add(Brand);
            }else{
                String Brand = "1";
                myinfo.add(Brand);
            }


                String Specifications = new JSONObject(obj).getString("ItemSpecifics");
                String NameValueList = new JSONObject(Specifications).getString("NameValueList");
                myinfo.add(NameValueList);


        } catch (JSONException e) {
            e.printStackTrace();
        }



        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        myToolbar.setTitle(title.substring(2,title.length()-2));
        myToolbar.setBackgroundColor(Color.parseColor("#E08462"));
        myToolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));


        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        ImageButton imgButton;
        imgButton =(ImageButton)findViewById(R.id.share);
        imgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openURL = new Intent(android.content.Intent.ACTION_VIEW);
                openURL.setData(Uri.parse(share)) ;
                startActivity(openURL);

            }
        });

        myToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RelativeLayout progress = (RelativeLayout)findViewById(R.id.todetail1);
                progress.setVisibility(View.VISIBLE);
                startActivity(new Intent(getApplicationContext(),load.class));

            }
        });




        tabLayout = (TabLayout)findViewById(R.id.tablayout);
        tabLayout.setTabTextColors(Color.parseColor("#2453CF"), Color.parseColor("#2453CF"));

        tab1 = (TabItem)findViewById(R.id.Tab1);
        tab2 = (TabItem)findViewById(R.id.Tab2);
        tab3 = (TabItem)findViewById(R.id.Tab3);
        viewPager = findViewById(R.id.viewpager);

        pageradapter = new PageAdapter(getSupportFragmentManager(),tabLayout.getTabCount());
        viewPager.setAdapter(pageradapter);
        tabLayout.getTabAt(1).getIcon().setColorFilter(Color.parseColor("#1434CB"), PorterDuff.Mode.SRC_IN);



        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                if(tab.getPosition() == 0){
                    pageradapter.notifyDataSetChanged();
                }else  if(tab.getPosition() == 1){
                    pageradapter.notifyDataSetChanged();
                }else  if(tab.getPosition() == 2){
                    pageradapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

    }

    public List<String> getShipping() {
        return myshipping;
    }

    public List<String> getSeller() {
        return myseller;
    }

    public List<String> getpicture() {
        return mypicture;
    }
    public List<String> getinfo() {
        return myinfo;
    }

}

