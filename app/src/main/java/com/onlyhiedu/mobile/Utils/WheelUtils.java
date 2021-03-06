package com.onlyhiedu.mobile.Utils;

import android.content.Context;
import android.graphics.Color;

import com.bigkoo.pickerview.OptionsPickerView;
import com.onlyhiedu.mobile.App.App;
import com.onlyhiedu.mobile.Model.bean.AddressBean;
import com.onlyhiedu.mobile.Model.bean.AddressModel;
import com.onlyhiedu.mobile.Model.bean.ProvinceBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/4.
 */

public class WheelUtils {

    public static OptionsPickerView getWhellView(Context context, OptionsPickerView.OnOptionsSelectListener l, ArrayList<ProvinceBean> data) {
        OptionsPickerView view = new OptionsPickerView.Builder(context, l)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(20)//设置滚轮文字大小
                .setSelectOptions(0, 1, 2)  //设置默认选中项
                .setOutSideCancelable(true)
                .build();
        view.setPicker(data);
        return view;
    }

    public static OptionsPickerView getWhellView2(Context context, OptionsPickerView.OnOptionsSelectListener l, ArrayList<ProvinceBean> data, ArrayList<ArrayList<String>> data2) {
        OptionsPickerView view = new OptionsPickerView.Builder(context, l)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(20)//设置滚轮文字大小
                .setSelectOptions(0, 1, 2)  //设置默认选中项
                .setOutSideCancelable(true)
                .build();
        view.setPicker(data, data2);
        return view;
    }


    public static ArrayList<ProvinceBean> getSexData() {
        ArrayList<ProvinceBean> data = new ArrayList<>();
        data.add(new ProvinceBean(0, "男", "描述部分", "其他数据"));
        data.add(new ProvinceBean(0, "女", "描述部分", "其他数据"));
        return data;
    }

    public static ArrayList<ProvinceBean> getGrade() {
        ArrayList<ProvinceBean> data = new ArrayList<>();
        data.add(new ProvinceBean(0, "小一", "描述部分", "其他数据"));
        data.add(new ProvinceBean(0, "小二", "描述部分", "其他数据"));
        data.add(new ProvinceBean(0, "小三", "描述部分", "其他数据"));
        data.add(new ProvinceBean(0, "小四", "描述部分", "其他数据"));
        data.add(new ProvinceBean(0, "小五", "描述部分", "其他数据"));
        data.add(new ProvinceBean(0, "小六", "描述部分", "其他数据"));
        data.add(new ProvinceBean(0, "初一", "描述部分", "其他数据"));
        data.add(new ProvinceBean(0, "初二", "描述部分", "其他数据"));
        data.add(new ProvinceBean(0, "初三", "描述部分", "其他数据"));
        data.add(new ProvinceBean(0, "高一", "描述部分", "其他数据"));
        data.add(new ProvinceBean(0, "高二", "描述部分", "其他数据"));
        data.add(new ProvinceBean(0, "高三", "描述部分", "其他数据"));
        return data;
    }

    public static ArrayList<ProvinceBean> getAddress() {
        ArrayList<ProvinceBean> datas = new ArrayList<>();
        ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
        ArrayList<String> cityList;
        String address = AppUtil.readAssert(App.getInstance().getApplicationContext(), "address.txt");
        AddressModel model = JsonUtil.parseJson(address, AddressModel.class);
        if (model != null) {
            AddressBean data = model.Result;
            if (data == null) return datas;
            if (data.ProvinceItems != null && data.ProvinceItems.Province != null) {
                for (int i = 0; i < data.ProvinceItems.Province.size(); i++) {
                    datas.add(new ProvinceBean(i, data.ProvinceItems.Province.get(i).Name, "描述部分", "其他数据"));
                    List<AddressBean.ProvinceEntity.CityEntity> city = data.ProvinceItems.Province.get(i).City;
                    cityList = new ArrayList<>();
                    for (int i1 = 0; i1 < city.size(); i1++) {
                        cityList.add(city.get(i1).Name);
                    }
                    options2Items.add(cityList);
                }
            }
        }
        return datas;
    }

}
