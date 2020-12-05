package com.codeartist.androidpractice.repository;

import android.app.Application;
import android.os.Build;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

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
    public LiveData<List<Property>> finalProperties;
    private List<Property> prop = new ArrayList<>();
    private MutableLiveData<List<Property> > _properties = new MutableLiveData<>(prop);
    public CompositeDisposable mCompositeDisposable = new CompositeDisposable();
    private PropertyDao mDao;

    private Repository(Application application){
        PropertyRoomDatabase db = PropertyRoomDatabase.getDatabase(application);
        mDao = db.propertyDao();
        dbProperties = mDao.getProperty();
        finalProperties = _properties;
    }
    public static Repository getInstance(Application application){
        if(instance == null){
            instance = new Repository(application);
        }
        return instance;
    }


    public void getProperties(){
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

                        //Log.e("object", String.valueOf(mDao.getProperty().getValue()));

                        _properties.setValue(properties);

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        /*return LiveDataReactiveStreams.fromPublisher(NetworkCall.getInstance().getRequestApi()
                .makeQuery()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()));*/
       // Log.i("pppp", properties.getValue().toString());


    }

    private void getData(){
      //  mDao.getProperty().toOberservable
    }


    public void sort() {
        List<Property> pro = _properties.getValue();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            pro.sort(Comparator.comparing(Property::getPrice));
        } else{
            Collections.sort(pro,  (property, t1)-> property.getPrice()- t1.getPrice());
        }
        _properties.setValue(pro);

       /* sort();sort();
        Collections.sort(pro, new Comparator<Property>() {
            @Override
            public int compare(Property o1, Property o2) {
                return o1.getType().compareTo(o2.getType());
            }
        });*/
    }

    public void sortByType(){
        List<Property> pro = _properties.getValue();
        Collections.sort(pro, (Property o1, Property o2) -> o1.getType().compareTo(o2.getType()));
        _properties.setValue(pro);
    }
}
