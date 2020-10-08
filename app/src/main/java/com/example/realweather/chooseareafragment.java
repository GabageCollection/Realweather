package com.example.realweather;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import org.litepal.LitePal;
import org.litepal.crud.LitePalSupport;

import java.util.ArrayList;
import java.util.List;


import db.city;
import db.county;
import db.province;



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
    private province selectedprovince;
    private city selectedcity;
    private county selectedcounty;
    private int currentLevel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.chooose_area,container,false);
        TextView titleText = (TextView) view.findViewById(R.id.title_text);
        ListView listView = (ListView) view.findViewById(R.id.list_view);
        Button backButton = (Button) view.findViewById(R.id.back_button);
        adapter =new ArrayAdapter<>((getContext()), android.R.layout.simple_list_item_1, dataList);
        listView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (currentLevel == LEVEL_PROVINCE) {
                    selectedprovince = provinceList.get(position);
                    querycities();
                } else if (currentLevel == LEVEL_CITY) {
                    selectedcity = cityList.get(position);
                    querycounties();
                }
            }
        });
        backButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if (currentLevel == LEVEL_COUNTY){
                    querycities();
                }else if(currentLevel==LEVEL_CITY){
                    queryprovinces();
                }
            }
        };
        queryprovinces();
    }

    private void queryprovinces(){
        titleText.setText("中国");
        backButton.setVisibility(View.GONE);
        provinceList = LitePal.findAll(province.class);
        if(provinceList.size()>0){
            dataList.clear();
            for(province province:provinceList){
                dataList.add(province.getProvinceName());
            }
            adapter.notifyDataSetChanged();
            listview.setSelection(0);
            currentLevel=LEVEL_PROVINCE;
        }else{
            String address = "http://guolin.tech/api/china";
            queryFormServer(addresss,"province");
        }
    }
}




