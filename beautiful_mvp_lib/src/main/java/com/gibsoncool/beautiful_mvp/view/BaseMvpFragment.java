package com.gibsoncool.beautiful_mvp.view;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gibsoncool.beautiful_mvp.factory.AnnotationMvpPresenterFactory;
import com.gibsoncool.beautiful_mvp.factory.IBaseMvpPresenterFactory;
import com.gibsoncool.beautiful_mvp.presenter.BaseMvpPresenter;
import com.gibsoncool.beautiful_mvp.proxy.BaseMvpProxy;
import com.gibsoncool.beautiful_mvp.proxy.IMvpPresenterProxy;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by GibsonCool on 2017/12/6
 */

public abstract class BaseMvpFragment<V extends IBaseMvpView, P extends BaseMvpPresenter<V>> extends Fragment implements IMvpPresenterProxy
{
	/**
	 * 调用onSaveInstanceState时存入Bundle的key
	 */
	private static final String             PRESENTER_SAVE_KEY = "presenter_save_key";
	/**
	 * 创建被代理对象,传入默认Presenter的工厂
	 */
	private              BaseMvpProxy<V, P> mProxy             = new BaseMvpProxy<>(AnnotationMvpPresenterFactory.<V, P>createFactory(getClass()));

	Unbinder unbinder;
	View     rootView;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		if (savedInstanceState != null)
		{
			mProxy.onRestoreInstanceState(savedInstanceState);
		}
	}


	@Override
	public void onResume()
	{
		super.onResume();
		mProxy.onResume((V) this);
		callTheEntrance();
	}


	@Override
	public void onDestroy()
	{
		super.onDestroy();
		mProxy.onDestroy();
		if (unbinder != null)
			unbinder.unbind();
	}

	@Override
	public void onSaveInstanceState(Bundle outState)
	{
		super.onSaveInstanceState(outState);
		outState.putBundle(PRESENTER_SAVE_KEY, mProxy.onSaveInstanceState());
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		if (rootView == null)
		{
			rootView = inflater.inflate(getContentView(), null);
			unbinder = ButterKnife.bind(this, rootView);
		}
		return rootView;
	}


	@Override
	public void setMvpPresenterFactory(IBaseMvpPresenterFactory factory)
	{
		mProxy.setMvpPresenterFactory(factory);
	}

	@Override
	public IBaseMvpPresenterFactory getMvpPresenterFactory()
	{
		return mProxy.getMvpPresenterFactory();
	}

	/**
	 * 获取Presenter
	 *
	 * @return P
	 */
	@Override
	public P getMvpPresenter()
	{
		return mProxy.getMvpPresenter();
	}


	public abstract String getTitle();

	protected abstract int getContentView();

	protected abstract void callTheEntrance();


}
