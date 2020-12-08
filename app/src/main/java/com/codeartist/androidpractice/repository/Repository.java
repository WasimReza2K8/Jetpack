package com.codeartist.androidpractice.repository;

import android.content.Context;
import android.os.Build;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import com.codeartist.androidpractice.db.PropertyDao;
import com.codeartist.androidpractice.db.PropertyRoomDatabase;
import com.codeartist.androidpractice.model.Property;
import com.codeartist.androidpractice.network.NetworkCall;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class Repository {
    private static Repository instance;
    public LiveData<List<Property>> dbProperties;
    public MediatorLiveData<List<Property>> finalProperties = new MediatorLiveData<>();
    public CompositeDisposable mCompositeDisposable = new CompositeDisposable();
    private PropertyDao mDao;

    private Repository(Context application) {
        PropertyRoomDatabase db = PropertyRoomDatabase.getDatabase(application);
        mDao = db.propertyDao();
        dbProperties = mDao.getProperty();
        finalProperties.addSource(dbProperties, new androidx.lifecycle.Observer<List<Property>>() {
            @Override
            public void onChanged(List<Property> properties) {
                finalProperties.setValue(properties);
            }
        });
    }

    public static Repository getInstance(Context application) {
        if (instance == null) {
            instance = new Repository(application);
        }
        return instance;
    }


    public void getProperties() {
        NetworkCall.getInstance().getRequestApi()
                .makeQuery()
                .toObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ArrayList<Property>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(@NonNull ArrayList<Property> properties) {
                        Log.e("Response", String.valueOf(properties.size()));
                        //_properties.setValue(responseBody);
                        PropertyRoomDatabase.databaseWriteExecutor.execute(() -> {
                            mDao.deleteAll();
                            mDao.insert(properties);
                        });

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    public void sort() {
        List<Property> pro = finalProperties.getValue();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            pro.sort(Comparator.comparing(Property::getPrice));
        } else {
            Collections.sort(pro, (property, t1) -> property.getPrice() - t1.getPrice());
        }
        finalProperties.setValue(pro);

    }

    public void sortByType() {
        List<Property> pro = finalProperties.getValue();
        Collections.sort(pro, (Property o1, Property o2) -> o1.getType().compareTo(o2.getType()));
        finalProperties.setValue(pro);
    }
}
