package com.exchange.water.application.ui.home;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.library.banner.BannerLayout;
import com.exchange.water.application.R;
import com.exchange.water.application.adapter.BannerAdapter;
import com.exchange.water.application.adapter.HomeRankingPagerAdapter;
import com.exchange.water.application.app.Injection;
import com.exchange.water.application.app.UrlFactory;
import com.exchange.water.application.base.BaseVDBFragment;
import com.exchange.water.application.databinding.FragmentHomeBinding;
import com.exchange.water.application.entity.BannerEntity;
import com.exchange.water.application.entity.Message;
import com.exchange.water.application.main.MainActivity;
import com.exchange.water.application.ui.home.contract.ICommonView;
import com.exchange.water.application.ui.home.contract.MainUIContract;
import com.exchange.water.application.ui.home.presenter.CommonPresenter;
import com.exchange.water.application.ui.home.presenter.HomePresenter;
import com.exchange.water.application.utils.SharedPreferenceInstance;
import com.exchange.water.application.utils.WonderfulLogUtils;
import com.exchange.water.application.utils.okhttp.StringCallback;
import com.exchange.water.application.utils.okhttp.WonderfulOkhttpUtils;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sunfusheng.marqueeview.MarqueeView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.Request;

import static com.exchange.water.application.utils.okhttp.WonderfulOkhttpUtils.get;

/**
 * Created by Administrator on 2019/8/15.
 * 首页
 */

public class HomeFragment extends BaseVDBFragment<FragmentHomeBinding> implements MainUIContract.HomeView, ICommonView,OnTabSelectListener, ViewPager.OnPageChangeListener,BannerLayout.OnBannerItemClickListener {
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
    private BannerAdapter mBannerAdapter;

    public static HomeFragment newInstance() {
        Bundle args = new Bundle();
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected void onBind() {

    }
    @Override
    public void onResume() {
        super.onResume();

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

    }
    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        params.height = getStatusBarHeight();
        mDataBinding.viewSpace.setLayoutParams(params);

        new HomePresenter(Injection.provideTasksRepository(getContext()),this);
        if (imageUrls == null || imageUrls.size() == 0) {
            mPresenter.banners();
        }

        //   getMessage();
        mPagerAdapter = new HomeRankingPagerAdapter(_mActivity, getChildFragmentManager());
        mDataBinding.listViewPager.setOffscreenPageLimit(4);
        mDataBinding.listViewPager.setAdapter(mPagerAdapter);
        if (mPagerAdapter.getTitles() != null &&
                mPagerAdapter.getTitles().size() > 0) {
            mDataBinding.tabLayout.setTabData(mPagerAdapter.getTitles());
        }
        mDataBinding.tabLayout.setOnTabSelectListener(this);
        mDataBinding.listViewPager.addOnPageChangeListener(this);

    }
  /*  public  void  test(){
        get().url("http://192.168.3.136/m/googleAuthReq")
           *//*     .addParams("account", phone)
                .addParams("Areacode", mAreacode)
               .addParams("from", "forgot")*//*.build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                super.onError(request,e);
                WonderfulLogUtils.logi("忘记密码手机验证码出错", "忘记密码手机验证码出错：" + e.getMessage());
            }

            @Override
            public void onResponse(String response) {
                WonderfulLogUtils.logi("忘记密码手机验证码回执：", "忘记密码手机验证码回执：" + response.toString());

            }
        });
    }*/
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
        mBannerAdapter = new BannerAdapter(getContext(),imageUrls);
        mDataBinding.banner.setAdapter(mBannerAdapter);
        mDataBinding.banner.setAutoPlaying(true);
        mDataBinding.banner.setAutoPlayDuration(2000);

        mBannerAdapter.setOnBannerItemClickListener(new BannerLayout.OnBannerItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(getmActivity(), "点击了第  " + position+"  项", Toast.LENGTH_SHORT).show();
            }
        });
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
        mDataBinding.listViewPager.setCurrentItem(position);

    }

    @Override
    public void onTabReselect(int position) {

    }

    @Override
    public void onItemClick(int position) {

    }
}
