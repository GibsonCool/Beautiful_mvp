package com.gibsoncool.beautiful_mvp.proxy;

import com.gibsoncool.beautiful_mvp.factory.IBaseMvpPresenterFactory;
import com.gibsoncool.beautiful_mvp.presenter.BaseMvpPresenter;
import com.gibsoncool.beautiful_mvp.view.IBaseMvpView;

/**
 * Created by GibsonCool on 2018/2/2
 */

public interface IMvpPresenterProxy<V extends IBaseMvpView, P extends BaseMvpPresenter<V>>
{
	void setMvpPresenterFactory(IBaseMvpPresenterFactory<V, P> factory);

	IBaseMvpPresenterFactory<V, P> getMvpPresenterFactory();

	P getMvpPresenter();
}
