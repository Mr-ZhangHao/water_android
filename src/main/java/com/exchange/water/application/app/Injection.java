package com.exchange.water.application.app;

import android.content.Context;

import com.exchange.water.application.data.DataRepository;
import com.exchange.water.application.data.LocalDataSource;
import com.exchange.water.application.data.RemoteDataSource;


/**
 * Created by Administrator on 2017/9/25.
 */

public class Injection {
    public static DataRepository provideTasksRepository(Context context) {
        return DataRepository.getInstance(RemoteDataSource.getInstance(), LocalDataSource.getInstance(context));
    }
}
