package com.business.electr.clothes.mvp.presenter.basePresenter;


import android.content.Context;

import com.business.electr.clothes.mvp.view.IView;
import com.business.electr.clothes.net.ApiClient;
import com.business.electr.clothes.net.ApiStores;
import com.business.electr.clothes.net.BaseObserver;
import com.business.electr.clothes.net.ResponseConvert;
import com.business.electr.clothes.net.exception.ExceptionConvert;
import com.business.electr.clothes.ui.activity.BaseActivity;
import com.business.electr.clothes.ui.fragment.BaseFragment;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by pantianxiong on 2018/4/23.
 * 描述：
 */
public class BasePresenter<V extends IView> implements IPresenter<V> {

    protected V mView;
    protected Context context;
    protected ApiStores apiStores = ApiClient.retrofit().create(ApiStores.class);
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public BasePresenter(V view) {
        mView = view;
        if (view instanceof BaseActivity) {
            context = (BaseActivity) view;
        } else if (view instanceof BaseFragment) {
            context = ((BaseFragment) view).getActivity();
        }
    }

    @Override
    public void detachView() {
        if (!compositeDisposable.isDisposed()) {
            compositeDisposable.clear();
        }
        mView = null;
    }

    public IView getView() {
        return mView;
    }

    protected void addSubscription(Observable observable, BaseObserver observer) {
        observable.map(new ResponseConvert())
                .onErrorResumeNext(new ExceptionConvert())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
        compositeDisposable.add(observer.getDisposable());
    }

    protected void addDisposable(Disposable disposable) {
        compositeDisposable.add(disposable);
    }

    protected void removeSubscription(Disposable disposable) {
        if (!compositeDisposable.isDisposed()) {
            compositeDisposable.remove(disposable);
        }
    }
}
