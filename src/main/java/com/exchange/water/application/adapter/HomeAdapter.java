package com.exchange.water.application.adapter;


import android.content.Context;
import android.databinding.ViewDataBinding;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;

import com.exchange.water.application.R;
import com.exchange.water.application.app.MyApplication;
import com.exchange.water.application.databinding.AdapterHomeLayoutBinding;
import com.exchange.water.application.entity.Currency;
import com.exchange.water.application.main.MainActivity;
import com.exchange.water.application.utils.WonderfulMathUtils;
import com.exchange.water.application.utils.WonderfulToastUtils;

import java.math.BigDecimal;
import java.text.DecimalFormat;


/**
 * Created by Administrator on 2019/8/19.
 */

public class HomeAdapter extends BaseVDBRecyclerAdapter<AdapterHomeLayoutBinding,Currency> {
    private boolean isLoad;

    public boolean isLoad() {
        return isLoad;
    }

    public void setLoad(boolean load) {
        isLoad = load;
    }
    public HomeAdapter(Context context) {
        super(context);
    }

    @Override
    public int getLayoutRes() {
        return R.layout.adapter_home_layout;
    }

    @Override
    public void onBind(AdapterHomeLayoutBinding dataBinding, Currency data, int position) {
        if (isLoad) {
            dataBinding.itemHomeMoney.setText( "≈" + WonderfulMathUtils.getRundNumber(data.getClose()*data.getBaseUsdRate()*2 ,2,null)+" CNY");
        } else {
            dataBinding.itemHomeMoney.setText( "$" + WonderfulMathUtils.getRundNumber(data.getUsdRate(),2,null));
        }

        dataBinding.itemHomePosition.setText( "" + (position + 1));
        dataBinding.itemHomeChg.setText( (data.getChg() >= 0 ? "+" : "") + WonderfulMathUtils.getRundNumber(data.getChg() * 100, 2, "########0.") + "%");
        dataBinding.itemHomeClose.setTextColor( data.getChg() >= 0 ? ContextCompat.getColor(MyApplication.getApp(),
                R.color.typeGreen) : ContextCompat.getColor(MyApplication.getApp(), R.color.typeRed));
        dataBinding.itemHomeChg.setEnabled(data.getChg() >= 0);
        dataBinding.itemHomeSymbol.setText( data.getSymbol());
        dataBinding.itemHomeChange.setText( WonderfulToastUtils.getString(R.string.text_24_change) + WonderfulMathUtils.getRundNumber(Double.valueOf(data.getVolume().toString()),2,null));

        //        helper.setText(R.id.item_home_close, WonderfulMathUtils.getRundNumber(item.getClose(),2,null));
        String format = new DecimalFormat("#0.00000000").format(data.getClose());
        BigDecimal bg = new BigDecimal(format);
        String v =  bg.setScale(8,BigDecimal.ROUND_DOWN).stripTrailingZeros().toPlainString();
        dataBinding.itemHomeClose.setText(v);


    }


}
