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
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import org.jetbrains.annotations.NotNull;
import org.litepal.LitePal;
import org.litepal.crud.LitePalSupport;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;




import db.city;
import db.county;
import db.province;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import util.httputil;
import util.utility;


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
        });
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
            queryFromServer(address,"province");
        }
    }
    private void querycities(){
        titleText.setText(selectedprovince.getProvinceName());
        backButton.setVisibility(View.VISIBLE);
        cityList=LitePal.where("provinceId=?",String.valueOf(selectedprovince.getId()))
                .find(city.class);
        if(cityList.size()>0){
            dataList.clear();
            for(city city:cityList){
                dataList.add(city.getcityName());
            }
            adapter.notifyDataSetChanged();
            listview.setSelection(0);
            currentLevel = LEVEL_CITY;
        }else{
            int provinceCode = selectedprovince.getProvinceCode();
            String address = "http://guolin.tech/api/china/"+provinceCode;
        }
    }
    private void querycounties() {
        titleText.setText(selectedcity.getcityName());
        backButton.setVisibility(View.VISIBLE);
        countyList = LitePal.where("cityId=?", String.valueOf(selectedcity.getId()))
                .find(county.class);
        if (countyList.size() > 0) {
            dataList.clear();
            for (county county : countyList) {
                dataList.add(county.getCountyName());
            }
            adapter.notifyDataSetChanged();
            listview.setSelection(0);
            currentLevel = LEVEL_COUNTY;
        } else {
            int provinceCode = selectedprovince.getProvinceCode();
            int cityCode = selectedcity.getCityCode();
            String address = "http://guolin.tech/api/china/" + provinceCode + "/" + cityCode;
            queryFromServer(address, "county");
        }
    }
        private void queryFromServer(String address,final String type){
            showProgressDialog();
            httputil.sendOkHttpRequest(address, new Callback() {
                @Override
                public void onResponse(@NotNull Call call, @NotNull Response response)throws IOException{
                    String responseText=response.body().string();
                    boolean result = false;
                    if("province".equals(type)){
                        result = utility.handleprovinceResponce(responseText);
                    }else if ("city".equals(type)){
                        result=utility.handlecityResponce(responseText,selectedprovince.getId());
                    }else if("county".equals(type)){
                        result=utility.handlecountyResponce(responseText,selectedcity.getId());
                    }if (result){
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                closeProgressDialog();
                                if("province".equals(type)){
                                    queryprovinces();
                                }else if("city".equals(type)){
                                    querycities();
                                }else if("county".equals(type)){
                                    querycities();
                                }
                            }
                        });
                    }
                }
                public void onFailure(Call call,IOException e){
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            closeProgressDialog();
                            Toast.makeText(getContext(),"加载失败",Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });
        }
        private void showProgressDialog(){
        if(progressDialog==null){
            progressDialog=new ProgressDialog(getActivity());
            progressDialog.setMessage("正在加载...");
            progressDialog.setCanceledOnTouchOutside(false);
        }
        progressDialog.show();
        }
        private void closeProgressDialog(){
        if(progressDialog!=null){
            progressDialog.dismiss();
        }
        }

}




