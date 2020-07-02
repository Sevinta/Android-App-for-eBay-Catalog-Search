package com.example.hw9android;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link tab3#newInstance} factory method to
 * create an instance of this fragment.
 */
public class tab3 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    List myDataFromActivity;
    public tab3() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment tab3.
     */
    // TODO: Rename and change types and number of parameters
    public static tab3 newInstance(String param1, String param2) {
        tab3 fragment = new tab3();
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
            mParam1 = getArguments().getString("params");
            //mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        detailinfo activity = (detailinfo) getActivity();
        myDataFromActivity = activity.getShipping();
        Log.d("param", String.valueOf(myDataFromActivity));
        return inflater.inflate(R.layout.fragment_tab3, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        //mParam1 = getArguments().getString("params");
        if(String.valueOf(myDataFromActivity).equals("[1]")){
            FrameLayout tab3 = (FrameLayout)getView().findViewById(R.id.Tab33);
            tab3.setVisibility(view.GONE);
        }else{
        TextView text1 = (TextView)getView().findViewById(R.id.shipping1);
        String shipping1 ="<body><h4><font color='Black'face='sans-serif-black'>Shipping Information</font></h4></body>";
        text1.setText(Html.fromHtml(shipping1));

        TextView text2 = (TextView)getView().findViewById(R.id.shipping2);
        String shipping2 ="<body><h5><font color='Grey'face='sans-serif-black'><ul>" +
                "<li> Handling Time <font face='sans-serif-thin' >: "+ myDataFromActivity.get(0) +"</font></li>" +
                "<li> One Day Shipping Available <font face='sans-serif-thin' >:  "+myDataFromActivity.get(1) +"</font></li>" +
                "<li> Shipping Type <font face='sans-serif-thin' >:  "+myDataFromActivity.get(2) +"</font></li>" +
                "<li> Shipping From <font face='sans-serif-thin' >:  "+myDataFromActivity.get(3) +"</font></li>" +
                "<li> Ship To Location <font face='sans-serif-thin' >:  "+myDataFromActivity.get(4) +"</font></li>" +
                "<li> Expedited Shipping <font face='sans-serif-thin' >:  "+myDataFromActivity.get(5) +"</font></li>" +
                "</ul></font><h5></body>";
        text2.setText(Html.fromHtml(shipping2));

    }}
}