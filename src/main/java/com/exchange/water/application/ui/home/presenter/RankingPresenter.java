package com.exchange.water.application.ui.home.presenter;

import com.exchange.water.application.data.DataSource;
import com.exchange.water.application.ui.home.contract.MainUIContract;

/**
 * Created by Administrator on 2019/8/19.
 */

public class RankingPresenter implements MainUIContract.RankingPresenter {
    private MainUIContract.RankingView view;
    private DataSource              dataRepository;

    public RankingPresenter(DataSource dataRepository, MainUIContract.RankingView view) {
        this.view = view;
        this.dataRepository = dataRepository;
        this.view.setPresenter(this);
    }

    @Override
    public void allCurrency() {
        dataRepository.allHomeCurrency(new DataSource.DataCallback() {
            @Override
            public void onDataLoaded(Object obj) {
                view.allCurrencySuccess(obj);
            }

            @Override
            public void onDataNotAvailable(Integer code, String toastMessage) {
                view.allCurrencyFail(code, toastMessage);
            }
        });
    }
}
