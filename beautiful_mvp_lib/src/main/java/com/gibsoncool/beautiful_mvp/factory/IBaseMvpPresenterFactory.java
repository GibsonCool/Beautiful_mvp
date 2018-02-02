package com.gibsoncool.beautiful_mvp.factory;

import com.gibsoncool.beautiful_mvp.presenter.BaseMvpPresenter;
import com.gibsoncool.beautiful_mvp.view.IBaseMvpView;

/**
 * Created by GibsonCool on 2018/2/1
 */

public interface IBaseMvpPresenterFactory<V extends IBaseMvpView, P extends BaseMvpPresenter<V>>
{

	P createMvpPresenter();
}
