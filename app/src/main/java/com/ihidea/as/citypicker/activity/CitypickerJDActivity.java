package com.ihidea.as.citypicker.activity;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ihidea.as.citypicker.R;
import com.lljjcoder.Interface.OnCityItemClickListener;
import com.lljjcoder.bean.CityBean;
import com.lljjcoder.bean.DistrictBean;
import com.lljjcoder.bean.ProvinceBean;
import com.lljjcoder.citywheel.CityConfig;
import com.lljjcoder.style.cityjd.JDCityConfig;
import com.lljjcoder.style.cityjd.JDCityPicker;

public class CitypickerJDActivity extends AppCompatActivity {
    JDCityPicker cityPicker;
    private Button jdBtn;
    private TextView resultV;
    TextView mTwoTv;
    TextView mThreeTv;

    public JDCityConfig.ShowType mWheelType = JDCityConfig.ShowType.PRO_CITY;


    private JDCityConfig jdCityConfig = new JDCityConfig.Builder().build();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citypicker_jd);

        jdBtn = (Button) findViewById(R.id.jd_btn);
        resultV = (TextView) findViewById(R.id.result_tv);
        mTwoTv = (TextView) findViewById(R.id.two_tv);
        mThreeTv = (TextView) findViewById(R.id.three_tv);

        jdCityConfig.setShowType(mWheelType);

        //二级联动，只显示省份， 市，不显示区
        mTwoTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mWheelType = JDCityConfig.ShowType.PRO_CITY;
                setWheelType(mWheelType);
                jdCityConfig.setShowType(mWheelType);
            }
        });

        //三级联动，显示省份， 市和区
        mThreeTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mWheelType = JDCityConfig.ShowType.PRO_CITY_DIS;
                setWheelType(mWheelType);
                jdCityConfig.setShowType(mWheelType);
            }
        });


        cityPicker = new JDCityPicker();
        //初始化数据
        cityPicker.init(this);
        //设置JD选择器样式位只显示省份和城市两级
        cityPicker.setConfig(jdCityConfig);
        cityPicker.setOnCityItemClickListener(new OnCityItemClickListener() {
            @Override
            public void onSelected(String s) {
                super.onSelected(s);
                Log.d("zzzs","返回的值："+s);
                resultV.setText(s);

            }
            @Override
            public void onCancel() {
            }
        });
       /* cityPicker.setOnCityItemClickListener(new OnCityItemClickListener() {
            @Override
            public void onSelected(ProvinceBean province, CityBean city, DistrictBean district) {

                String proData = null;
                if (province != null) {
                    proData = "省  " + province.getName();
                    //resultV.setText(proData);
                }

                String cituData = null;
                if (city != null) {
                    cituData = "市 " + city.getName();
                }


                String districtData = null;
                if (district != null) {
                    districtData = "县  " + district.getName();
                }

                    、
                if (mWheelType == JDCityConfig.ShowType.PRO_CITY_DIS) {
                    resultV.setText("城市选择结果：\n" + proData + "\n"
                            + cituData + "\n"
                            + districtData);
                } else {
                    resultV.setText("城市选择结果：\n" + proData + "\n"
                            + cituData + "\n"
                    );
                }
            }
        });*/
        jdBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showJD();
            }
        });
    }


    /**
     * @param wheelType
     */
    private void setWheelType(JDCityConfig.ShowType wheelType) {
        if (wheelType == JDCityConfig.ShowType.PRO_CITY) {
            mTwoTv.setBackgroundResource(R.drawable.city_wheeltype_selected);
            mThreeTv.setBackgroundResource(R.drawable.city_wheeltype_normal);
            mTwoTv.setTextColor(Color.parseColor("#ffffff"));
            mThreeTv.setTextColor(Color.parseColor("#333333"));
        } else {
            mTwoTv.setBackgroundResource(R.drawable.city_wheeltype_normal);
            mThreeTv.setBackgroundResource(R.drawable.city_wheeltype_selected);
            mTwoTv.setTextColor(Color.parseColor("#333333"));
            mThreeTv.setTextColor(Color.parseColor("#ffffff"));
        }
    }


    private void showJD() {
        cityPicker.showCityPicker();
    }
}
