package com.exchange.water.application.start.guide.details;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.view.View;

import com.exchange.water.application.R;
import com.exchange.water.application.base.BaseVDBFragment;
import com.exchange.water.application.databinding.FragmentGuideDetailsBinding;
import com.exchange.water.application.main.MainActivity;


/**
 * Created by lion on 2017/6/3.
 */

public class GuideDetailsFragment extends BaseVDBFragment<FragmentGuideDetailsBinding>
        implements View.OnClickListener {

    private static final int END_IMAGE_ID = R.mipmap.img_guide_3;

    @DrawableRes
    private int mImgResId = -1;

    @Override
    protected void onBind() {
        initData();

        if (mImgResId != -1) {
            mDataBinding.imgPage.setImageResource(mImgResId);
            mDataBinding.btnConfirm.setVisibility(mImgResId == END_IMAGE_ID ?
                    View.VISIBLE : View.GONE);
            mDataBinding.btnConfirm.setOnClickListener(this);
        }
    }

    private void initData() {
        Bundle data = getArguments();
        if (data != null) {
            mImgResId = data.getInt("imgResId");
        }
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_guide_details;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btnConfirm:
                Intent intent = new Intent(getContext(), MainActivity.class);
                startActivity(intent);
                getActivity().finish();
                break;
        }
    }
}
