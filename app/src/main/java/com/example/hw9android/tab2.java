package com.example.hw9android;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link tab2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class tab2 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    List myDataFromActivity;

    public tab2() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment tab2.
     */
    // TODO: Rename and change types and number of parameters
    public static tab2 newInstance(String param1, String param2) {
        tab2 fragment = new tab2();
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
        myDataFromActivity = activity.getSeller();
        return inflater.inflate(R.layout.fragment_tab2, container, false);
    }



    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        TextView text1 = (TextView)getView().findViewById(R.id.seller1);
        String sellerinfo1 ="<body><h4><font color='Black'face='sans-serif-black'>Seller Information</font></h4></body>";
        text1.setText(Html.fromHtml(sellerinfo1));

        TextView text2 = (TextView)getView().findViewById(R.id.seller2);
        String sellerinfo2 ="<body><h5><font color='Grey'face='sans-serif-black'><ul>" +
                "<li> Feedback Score <font face='sans-serif-thin' >: "+ myDataFromActivity.get(0) +"</font></li>" +
                "<li> User I D <font face='sans-serif-thin' >: "+ myDataFromActivity.get(1) +"</font></li>" +
                "<li> Positive Feedback Percent <font face='sans-serif-thin' >: "+ myDataFromActivity.get(2) +"</font></li>" +
                "<li> Feedback Rating Star <font face='sans-serif-thin' >: "+ myDataFromActivity.get(3) +"</font></li>" +
                "</ul></font><h5></body>";
        text2.setText(Html.fromHtml(sellerinfo2));

        TextView text3 = (TextView)getView().findViewById(R.id.seller3);
        String sellerinfo3 ="<body><h4><font color='Black'face='sans-serif-black'>Return Police</font></h4></body>";
        text3.setText(Html.fromHtml(sellerinfo3));

        TextView text4 = (TextView)getView().findViewById(R.id.seller4);
        String sellerinfo4 ="<body><h5><font color='Grey'face='sans-serif-black'><ul>" +
                "<li> Refund <font face='sans-serif-thin' >: "+ myDataFromActivity.get(4) +"</font></li>" +
                "<li> Returns Within <font face='sans-serif-thin' >: "+ myDataFromActivity.get(5) +"</font></li>" +
                "<li> Shipping Cost Paid By <font face='sans-serif-thin' >: "+ myDataFromActivity.get(6) +"</font></li>" +
                "<li> Return Accepted <font face='sans-serif-thin' >: "+ myDataFromActivity.get(7) +"</font></li>" +
                "</ul></font><h5></body>";
        text4.setText(Html.fromHtml(sellerinfo4));

    }
}