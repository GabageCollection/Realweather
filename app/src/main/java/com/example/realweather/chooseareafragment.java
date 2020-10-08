package com.example.realweather;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import db.city;
import db.county;
import db.province;
import kotlin.internal.UProgressionUtilKt;

public class chooseareafragment extends Fragment {
    public static final int LEVEL_PROVINCE =0;
    public static final int LEVEL_CITY =1;
    public static final int LEVEL_COUNTY =2;
    private ProgressDialog progressDialog;
    private TextView titleText;
    private Button backButton;
    private  ListView listview;
    private ArrayAdapter<String>adapter;
    private List<String> dataList =new ArrayList<>();
    private List<province>provinceList;
    private List<city>cityList;
    private List<county>countyList;
    private province selectprovince;
    private city seletcity;
    private county selectcounty;
    private int currentLevel;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.chooose_area,container,false);
        TextView titleText = (TextView) view.findViewById(R.id.title_text);
        ListView listView = (ListView) view.findViewById(R.id.list_view);
        Button backButton = (Button) view.findViewById(R.id.back_button);
        adapter =new ArrayAdapter<>((getContext()), android.R.layout.simple_list_item_1, dataList);
        listView.setAdapter(adapter);
        return view;
    }
}
