package com.example.hw9android;

import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link tab1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class tab1 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    List myDataFromActivity;
    List myDataInfo;


    public tab1() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment tab1.
     */
    // TODO: Rename and change types and number of parameters
    public static tab1 newInstance(String param1, String param2) {
        tab1 fragment = new tab1();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        detailinfo activity = (detailinfo) getActivity();
        myDataFromActivity = activity.getpicture();
        myDataInfo = activity.getinfo();
        return inflater.inflate(R.layout.fragment_tab1, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        LinearLayout layout = (LinearLayout)getView().findViewById(R.id.imageLayout);
        int count = 0;
        if (myDataFromActivity.size() > 7){
            count = 7;
        }else{
            count = myDataFromActivity.size();
        }
        for (int i = 0; i < count; i++) {
            ImageView itemimage = (ImageView)layout.getChildAt(i);
            itemimage.setVisibility(view.VISIBLE);
            Picasso.get().load((String) myDataFromActivity.get(i)).into(itemimage);
        }
        if(myDataInfo.get(0).equals("1")){
            TextView sub = (TextView) getView().findViewById(R.id.textView3);
            sub.setVisibility(View.GONE);
        }else{
            TextView titletext = (TextView) getView().findViewById(R.id.textView3);
            String htmlText = "<body><h5><font color='#676767'face='Normal'>" + myDataInfo.get(0).toString() + "</font></h5></body>";
            titletext.setText(Html.fromHtml(htmlText));
        }


        TextView priceandship = (TextView) getView().findViewById(R.id.priceandship);
        String priceandshipText = "<body><h6><font color='#AFC94C'face='sans-serif-black'>$" + myDataInfo.get(1).toString() + "</font><font color='Grey'face='sans-serif-medium'> ships for $"+myDataInfo.get(2).toString()+"</font></h6></body>";
        priceandship.setText(Html.fromHtml(priceandshipText));

        TextView productfeature = (TextView) getView().findViewById(R.id.productfeature);
        String productfeatureText ="<body><h4><font color='Black'face='sans-serif-black'>Product Features</font></h4></body>";
        productfeature.setText(Html.fromHtml(productfeatureText));

        if(myDataInfo.get(3).equals("1")){
            LinearLayout sub = (LinearLayout)getView().findViewById(R.id.subtitle1);
            sub.setVisibility(View.GONE);
        }else {
            TextView subtitle = (TextView) getView().findViewById(R.id.subtitle);
            String subtitleText ="<body><h5><font color='#636363'face='Normal'>Subtitle</font></h5></body>";
            subtitle.setText(Html.fromHtml(subtitleText));
            TextView subtitldetail = (TextView) getView().findViewById(R.id.subtitledetail);
            String subtitldetailText ="<body><h6><font color='#A8A8A8' face='sans-serif-medium'>"+myDataInfo.get(3)+"</font></h6></body>";
            subtitldetail.setText(Html.fromHtml(subtitldetailText));
        }
        if(myDataInfo.get(4).equals("1")){
            LinearLayout bran = (LinearLayout)getView().findViewById(R.id.brand1);
            bran.setVisibility(View.GONE);
        }else {
            TextView brand = (TextView) getView().findViewById(R.id.brand);
            String brandText = "<body><h5><font color='#636363'face='Normal'>Brand</font></h5></body>";
            brand.setText(Html.fromHtml(brandText));

            TextView branddetail = (TextView) getView().findViewById(R.id.branddetail);
            String branddetailText = "<body><h6><font color='#A8A8A8'face='sans-serif-medium'>" + myDataInfo.get(4).toString().substring(2, myDataInfo.get(4).toString().length() - 2) + "</font></h6></body>";
            branddetail.setText(Html.fromHtml(branddetailText));
        }

        TextView Specifications = (TextView) getView().findViewById(R.id.Specifications);
        String SpecificationsText ="<body><h4><font color='Black'face='sans-serif-black'>Specifications</font></h4></body>";
        Specifications.setText(Html.fromHtml(SpecificationsText));


            JSONArray aaa = null;
            int len =0;
            try {
                aaa = new JSONArray((String)myDataInfo.get(5));
                Log.d("aaaaa", String.valueOf(aaa));
            //getJSONObject(1).getString("Value");
                if(aaa.length() > 5){
                    len = 6;
                }else{
                    len = aaa.length();
                }
                String result = "<body><h6><font color='#A8A8A8'face='sans-serif-medium'>";
                String end = "</font></h6></body>";
                TextView spedetail = (TextView) getView().findViewById(R.id.Spedetail);
                for(int i =1 ; i <len; i ++){
                    String value = aaa.getJSONObject(i).getString("Value");

                    result = result +"・" +value.substring(2, value.length() - 2)+"<br><br>";

                }
                String spedetailText = result + end;
                spedetail.setText(Html.fromHtml(spedetailText));

            } catch (JSONException e) {
                e.printStackTrace();
            }



    }
}