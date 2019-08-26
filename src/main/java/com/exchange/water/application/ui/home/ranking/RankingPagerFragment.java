package com.exchange.water.application.ui.home.ranking;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.exchange.water.application.R;
import com.exchange.water.application.adapter.HomeAdapter;
import com.exchange.water.application.app.Injection;
import com.exchange.water.application.base.BaseVDBFragment;
import com.exchange.water.application.databinding.FragmentRankingPagerBinding;
import com.exchange.water.application.entity.BannerEntity;
import com.exchange.water.application.entity.Currency;
import com.exchange.water.application.ui.home.contract.MainUIContract;
import com.exchange.water.application.ui.home.presenter.HomePresenter;
import com.exchange.water.application.ui.home.presenter.RankingPresenter;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * Created by Administrator on 2019/8/19.
 */

public class RankingPagerFragment extends BaseVDBFragment<FragmentRankingPagerBinding>implements HomeAdapter.OnItemClickListener ,MainUIContract.RankingView{
    private static final String KEY_TYPE = "key_type";
    private String mType;
    private HomeAdapter mHomeAdapter;
    private MainUIContract.RankingPresenter mPresenter;
    private Gson gson = new Gson();

    public static RankingPagerFragment newInstance(String type) {
        Bundle args = new Bundle();
        args.putString(KEY_TYPE, type);
        RankingPagerFragment fragment = new RankingPagerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void onBind() {
        if (getArguments() != null) {
            mType = getArguments().getString(KEY_TYPE);
        }
        new RankingPresenter(Injection.provideTasksRepository(getContext()),this);

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        if (mType.equals("Rising")){

        }else if (mType.equals("Trading")){

        }else if (mType.equals("Currency")){

        }

    //    mPresenter.allCurrency();
        initRvContent();


    }
    private void initRvContent() {
        // 涨幅榜的适配器
        mDataBinding.mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mHomeAdapter = new HomeAdapter(getContext());
      //  mHomeAdapter.isFirstOnly(true);
        mHomeAdapter.setLoad(true);
        mDataBinding.mRecyclerView.setAdapter(mHomeAdapter);
        mDataBinding. mRecyclerView.setHasFixedSize(true);
        mDataBinding.mRecyclerView.setNestedScrollingEnabled(false);

    }
    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_ranking_pager;
    }

    @Override
    public void onItemClick(int position, Object data) {

    }


    @Override
    public void allCurrencySuccess(Object obj) {
        JsonObject object = new JsonParser().parse((String) obj).getAsJsonObject();
        JsonArray array = object.getAsJsonArray("ticker").getAsJsonArray();

        List<Currency> objs = gson.fromJson(array, new TypeToken<List<Currency>>() {
        }.getType());
   //     mHomeAdapter.setData(objs);
    }

    @Override
    public void allCurrencyFail(Integer code, String toastMessage) {

    }

    @Override
    public void setPresenter(MainUIContract.RankingPresenter presenter) {
        this.mPresenter=presenter;
    }



}
