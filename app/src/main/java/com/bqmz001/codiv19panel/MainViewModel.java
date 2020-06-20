package com.bqmz001.codiv19panel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.bqmz001.codiv19panel.data.DataSource;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainViewModel extends ViewModel {
    MutableLiveData<String> status = new MutableLiveData<>();
    MutableLiveData<DataSource> data = new MutableLiveData<>();

    public void load() {
        status.setValue("loading");
        DataRepository.getData("把这行文字去掉，填写你自己的appcode")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<DataSource>() {

                    @Override
                    public void accept(DataSource dataSource) throws Exception {
                        data.setValue(dataSource);
                        status.setValue("success");
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                        status.setValue("fail");
                    }
                });
    }
}
