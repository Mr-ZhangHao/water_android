package com.exchange.water.application.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.exchange.water.application.R;
import com.exchange.water.application.databinding.AdapterCountryBinding;
import com.exchange.water.application.entity.Country;

import java.util.List;

/**
 * Created by Administrator on 2018/3/1.
 */

public class CountryAdapter extends BaseVDBRecyclerAdapter<AdapterCountryBinding, Country> {



    public CountryAdapter(Context context) {
        super(context);
    }

    @Override
    public int getLayoutRes() {
        return R.layout.adapter_country;
    }

    @Override
    public void onBind(AdapterCountryBinding dataBinding, Country data, int position) {
     //   dataBinding.tvEnName.setText( data.getEnName());
        dataBinding.tvZhName.setText( data.getName());
        dataBinding.number.setText( data.getArea());

    }

}
