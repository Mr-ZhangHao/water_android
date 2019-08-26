package com.exchange.water.application.ui.home.contract;



import com.exchange.water.application.base.Contract;
import com.exchange.water.application.entity.BannerEntity;

import java.util.List;

/**
 * Created by Administrator on 2019/8/19.
 */

public interface MainUIContract {

    /*
    * 首页
    * */
    interface HomePresenter extends Contract.BasePresenter {

        void banners();

    }

    interface HomeView extends Contract.BaseView<HomePresenter> {

        void bannersSuccess(List<BannerEntity> obj);

        void bannersFail(Integer code, String toastMessage);

    }

    /*
    * 排行榜
    * */

    interface RankingPresenter extends Contract.BasePresenter {

        void allCurrency();

    }
    interface RankingView extends Contract.BaseView<RankingPresenter> {

        void allCurrencySuccess(Object obj);

        void allCurrencyFail(Integer code, String toastMessage);

    }
/*
* 行情
* */
interface MarketPresenter extends Contract.BasePresenter {

}

    interface MarketView extends Contract.BaseView<MarketPresenter> {

    }

}
