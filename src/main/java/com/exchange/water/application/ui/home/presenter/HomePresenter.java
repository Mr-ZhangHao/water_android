package com.exchange.water.application.ui.home.presenter;

import com.exchange.water.application.data.DataSource;
import com.exchange.water.application.entity.BannerEntity;
import com.exchange.water.application.ui.home.contract.MainUIContract;

import java.util.List;

/**
 * Created by Administrator on 2019/8/16.
 */

public class HomePresenter implements MainUIContract.HomePresenter {
    private MainUIContract.HomeView view;
    private DataSource dataRepository;

    public HomePresenter(DataSource dataRepository, MainUIContract.HomeView view) {
        this.view = view;
        this.dataRepository = dataRepository;
        this.view.setPresenter(this);
    }

    @Override
    public void banners() {
        dataRepository.banners( new DataSource.DataCallback() {
            @Override
            public void onDataLoaded(Object obj) {
                view.hideLoadingPopup();
                view.bannersSuccess((List<BannerEntity>) obj);
            }

            @Override
            public void onDataNotAvailable(Integer code, String toastMessage) {
                view.hideLoadingPopup();
                view.bannersFail(code, toastMessage);

            }
        });
    }



}
