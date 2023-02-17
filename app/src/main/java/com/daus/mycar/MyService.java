package com.daus.mycar;

import android.content.Context;

public class MyService {

    CarApi carApi;

    public MyService(Context context) {
        carApi = RetrofitBuilder
                .builder(context)
                .create(CarApi.class);
    }

    public CarApi getInstance() {
        return carApi;
    }
}
