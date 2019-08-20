package com.exchange.water.application.ui.home;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.exchange.water.application.R;
import com.exchange.water.application.adapter.BannerImageLoader;
import com.exchange.water.application.adapter.HomeAdapter;
import com.exchange.water.application.adapter.HomeRankingPagerAdapter;
import com.exchange.water.application.app.Injection;
import com.exchange.water.application.app.UrlFactory;
import com.exchange.water.application.base.BaseTitleFragment;
import com.exchange.water.application.base.BaseVDBFragment;
import com.exchange.water.application.databinding.FragmentHomeBinding;
import com.exchange.water.application.entity.BannerEntity;
import com.exchange.water.application.entity.Message;
import com.exchange.water.application.main.MainContract;
import com.exchange.water.application.ui.home.contract.ICommonView;
import com.exchange.water.application.ui.home.contract.MainUIContract;
import com.exchange.water.application.ui.home.presenter.CommonPresenter;
import com.exchange.water.application.ui.home.presenter.HomePresenter;
import com.exchange.water.application.utils.SharedPreferenceInstance;
import com.exchange.water.application.utils.okhttp.StringCallback;
import com.exchange.water.application.utils.okhttp.WonderfulOkhttpUtils;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sunfusheng.marqueeview.MarqueeView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.Request;

/**
 * Created by Administrator on 2019/8/15.
 * 首页
 */

public class HomeFragment extends BaseTitleFragment<FragmentHomeBinding> implements MainUIContract.HomeView, ICommonView,OnTabSelectListener, ViewPager.OnPageChangeListener {
    private List<String> imageUrls = new ArrayList<>();
    private MainUIContract.HomePresenter mPresenter;
    private CommonPresenter commonPresenter;
    private List<Message> messageList = new ArrayList<>();
    private List<String> info = new ArrayList<>();
    private List<Integer> infoss = new ArrayList<>();
    private HomeRankingPagerAdapter mPagerAdapter;


    private List<Integer> localImageUrls = new ArrayList<Integer>() {{
       // add(R.mipmap.icon_banner);
     add(R.mipmap.ic_launcher_round);
    }};

    public static HomeFragment newInstance() {
        Bundle args = new Bundle();
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected void onBind() {
        initTitle("首页",false);

    }
    @Override
    public void onResume() {
        super.onResume();
        mDataBinding.banner.startAutoPlay();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {
            if (mDataBinding.marqueeView != null) {
                mDataBinding. marqueeView.stopFlipping();
            }
        } else {
            if (mDataBinding.marqueeView != null) {
                if (!mDataBinding.marqueeView.isFlipping()) {
                    mDataBinding.marqueeView.startFlipping();
                }
            }
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        mDataBinding.banner.stopAutoPlay();
    }
    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        new HomePresenter(Injection.provideTasksRepository(getContext()),this);
        if (imageUrls == null || imageUrls.size() == 0) {
            mPresenter.banners();
        }
     //   getMessage();
        mPagerAdapter = new HomeRankingPagerAdapter(_mActivity, getChildFragmentManager());
        mDataBinding.viewPager.setOffscreenPageLimit(4);
        mDataBinding.viewPager.setAdapter(mPagerAdapter);
        if (mPagerAdapter.getTitles() != null &&
                mPagerAdapter.getTitles().size() > 0) {
            mDataBinding.tabLayout.setTabData(mPagerAdapter.getTitles());
        }
        mDataBinding.tabLayout.setOnTabSelectListener(this);
        mDataBinding.viewPager.addOnPageChangeListener(this);
    }

    /*
        * banner 数据
        * */
    @Override
    public void bannersSuccess(final List<BannerEntity> obj) {
        if (obj == null) {
            return;
        }
        for (BannerEntity bannerEntity : obj) {
            imageUrls.add(bannerEntity.getA_img_file_en());
        }
   /*     for (int i = 0; i < obj.size() ; i++) {
            imageUrls.add(obj.get(i).getA_img_file_en());

        }*/
        if (imageUrls.size() == 0) {
          mDataBinding.banner.setImages(localImageUrls);
        } else {
            if (mDataBinding.banner == null) {
                return;
            }
            mDataBinding.banner.setImages(imageUrls);
        }
        if (imageUrls.size() > 0) {
            // 设置图片集合
            mDataBinding.banner.setImages(imageUrls);
        }
        // 设置样式
        mDataBinding.banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR).setIndicatorGravity(BannerConfig.CENTER)
                .setImageLoader(new BannerImageLoader( mDataBinding.banner.getWidth(),  mDataBinding.banner.getHeight()));
        mDataBinding.banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                if (obj.size() == 0) {
                    return;
                }
                if (!TextUtils.isEmpty(obj.get(position).getA_img_file_en())) {
                    Intent intent = new Intent();
                    intent.setData(Uri.parse(obj.get(position).getA_img_file_en()));
                    intent.setAction(Intent.ACTION_VIEW);
                    getmActivity().startActivity(intent);
                }
            }
        });
        //设置轮播时间
        mDataBinding.banner.setDelayTime(3000);
        mDataBinding.banner.start();
    }

    @Override
    public void bannersFail(Integer code, String toastMessage) {

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_home;
    }

    @Override
    public void setPresenter(MainUIContract.HomePresenter presenter) {
        this.mPresenter=presenter;
    }

    @Override
    public void setPresenter(CommonPresenter presenter) {
        this.commonPresenter = presenter;
    }

    @Override
    public void deleteSuccess(String obj, int position) {

    }

    @Override
    public void deleteFail(Integer code, String toastMessage) {

    }

    @Override
    public void addSuccess(String obj, int position) {

    }

    @Override
    public void addFail(Integer code, String toastMessage) {

    }

    /*
* 通知消息
* */
    private void getMessage() {
        WonderfulOkhttpUtils.post().url(UrlFactory.getMessageUrl())
                .addParams("pageNo", 1 + "").addParams("pageSize", "100")
                .build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(String response) {
                try {
                    JSONObject object = new JSONObject(response);
                    if (object.optInt("code") == 0) {
                        List<Message> messages = new Gson().fromJson(object.getJSONObject("data").getJSONArray("content").toString(), new TypeToken<List<Message>>() {
                        }.getType());
                        messageList.clear();
                        messageList.addAll(messages);
                        setMarqueeView(messageList);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        mDataBinding.marqueeView.setOnItemClickListener(new MarqueeView.OnItemClickListener() {
            @Override
            public void onItemClick(int position, TextView textView) {
                //   MessageDetailActivity.actionStart(getActivity(), messageList.get(infoss.get(position)).getId());
            }
        });
    }

    private void setMarqueeView(List<Message> messageList) {
        info.clear();
        int code = SharedPreferenceInstance.getInstance().getLanguageCode();
        if (code == 1) {
            //中文
            for (int i = 0; i < messageList.size(); i++) {
                Message message = messageList.get(i);
                if (isContainChinese(message.getTitle())) {
                    String str = "";
                    if (message.getTitle().length() > 15) {
                        str = message.getTitle();
                        str = str.substring(0, 15);
                        info.add(str + "...");
                    } else {
                        info.add(message.getTitle());
                    }

                    infoss.add(i);
                }
            }

        } else {
            for (int i = 0; i < messageList.size(); i++) {
                Message message = messageList.get(i);
                if (!isContainChinese(message.getTitle())) {
                    info.add(message.getTitle());
                    infoss.add(i);
                }
            }
        }
       mDataBinding.marqueeView.startWithList(info);
    }
    static Pattern p = Pattern.compile("[\u4e00-\u9fa5]");

    public static boolean isContainChinese(String str) {
        Matcher m = p.matcher(str);
        return m.find();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        mDataBinding.tabLayout.setCurrentTab(position);

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onTabSelect(int position) {
        mDataBinding.viewPager.setCurrentItem(position);

    }

    @Override
    public void onTabReselect(int position) {

    }
}
