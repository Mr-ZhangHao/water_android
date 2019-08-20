package com.exchange.water.application.main;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import com.exchange.water.application.data.DataSource;
import com.exchange.water.application.utils.ExEventBus;


/**
 * Created by lion on 2017/3/14.
 */

public class MainPresenter implements MainContract.Presenter {
    private final DataSource        dataRepository;
    private final MainContract.View view;

    public MainPresenter(DataSource dataRepository, MainContract.View view) {
        this.dataRepository = dataRepository;
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public boolean checkHasSavePermission(Activity activity) {
        return Build.VERSION.SDK_INT < 23 || ContextCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public boolean requestSavePermission(Activity context) {
        boolean hasPermission = checkHasSavePermission(context);


        if (!hasPermission) {

            if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O) {
                String[] permission = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.REQUEST_INSTALL_PACKAGES
                };
                ActivityCompat.requestPermissions(context, permission,
                        ExEventBus.MessageFragment.REQUEST_PERMISSION_EXTERNAL_STORAGE);
            }else {
                String[] permission = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE
                };
                ActivityCompat.requestPermissions(context, permission,
                        ExEventBus.MessageFragment.REQUEST_PERMISSION_EXTERNAL_STORAGE);
            }

            //                    Manifest.permission.ACCESS_COARSE_LOCATION
            //                    Manifest.permission.ACCESS_FINE_LOCATION

        }

        return hasPermission;
    }

}
