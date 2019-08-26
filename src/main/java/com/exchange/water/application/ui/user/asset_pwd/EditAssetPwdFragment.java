package com.exchange.water.application.ui.user.asset_pwd;


import com.exchange.water.application.R;
import com.exchange.water.application.base.BaseTitleFragment;

public class EditAssetPwdFragment extends BaseTitleFragment implements AssetPwdContract.EditView {


    @Override
    public void setPresenter(AssetPwdContract.EditPresenter presenter) {

    }

    @Override
    public void editAccountPedSuccess(String obj) {

    }

    @Override
    public void editAccountPedFail(Integer code, String toastMessage) {

    }

    @Override
    protected void onBind() {

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_modify_asset_pwd;
    }
}
