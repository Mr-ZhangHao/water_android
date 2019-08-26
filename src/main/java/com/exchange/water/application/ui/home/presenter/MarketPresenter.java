package com.exchange.water.application.ui.home.presenter;

import com.exchange.water.application.data.DataSource;
import com.exchange.water.application.ui.home.contract.MainUIContract;

/**
 * Created by Administrator on 2019/8/20.
 */

public class MarketPresenter implements MainUIContract.MarketPresenter {


    private MainUIContract.MarketView view;
    private DataSource              dataRepository;

    public MarketPresenter(DataSource dataRepository, MainUIContract.MarketView view) {
        this.view = view;
        this.dataRepository = dataRepository;
        this.view.setPresenter(this);
    }



}
