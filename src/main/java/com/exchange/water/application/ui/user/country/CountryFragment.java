package com.exchange.water.application.ui.user.country;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;

import com.exchange.water.application.R;
import com.exchange.water.application.adapter.CountryAdapter;
import com.exchange.water.application.app.Injection;
import com.exchange.water.application.base.BaseTitleFragment;
import com.exchange.water.application.databinding.FragmentCountryBinding;
import com.exchange.water.application.entity.Country;
import com.exchange.water.application.ui.home.HomeFragment;
import com.exchange.water.application.utils.WonderfulCodeUtils;

import java.util.ArrayList;
import java.util.List;

public class CountryFragment extends BaseTitleFragment<FragmentCountryBinding> implements CountryContract.View,CountryAdapter.OnItemClickListener {

    private CountryContract.Presenter presenter;

    private CountryAdapter adapter;
    private List<Country> countries = new ArrayList<>();
    public static CountryFragment newInstance() {
        Bundle args = new Bundle();
        CountryFragment fragment = new CountryFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_country;
    }

    @Override
    protected void onBind() {
        initTitle(getResources().getString(R.string.select_country),true);
        new CountryPresenter(Injection.provideTasksRepository(getContext()), this);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        presenter.country();
        adapter = new CountryAdapter(_mActivity);
        adapter.setOnItemClickListener(this);
        mDataBinding.rvCountry.setLayoutManager(new LinearLayoutManager(_mActivity));
        mDataBinding.rvCountry.setAdapter(adapter);
    }

    @Override
    public void countrySuccess(List<Country> obj) {
        if (obj == null) return;
        this.countries.clear();
        this.countries.addAll(obj);
        adapter.setData(countries);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void countryFail(Integer code, String toastMessage) {
        WonderfulCodeUtils.checkedErrorCode(this, code, toastMessage);

    }

    @Override
    public void setPresenter(CountryContract.Presenter presenter) {
        this.presenter = presenter;
    }


    @Override
    public void onItemClick(int position, final Object data) {
        updateUI(new Runnable() {
            @Override
            public void run() {
                Country dataBean = (Country) data;
                String Area = dataBean.getArea();
                String Country = dataBean.getName();
                Bundle datas = new Bundle();
                datas.putString("area", Area);
                datas.putString("country", Country);
                setFragmentResult(RESULT_OK, datas);
                pop();
            }
        });
    }
}
