package com.gibsoncool.beautiful_mvp.proxy;

import android.os.Bundle;
import android.util.Log;

import com.gibsoncool.beautiful_mvp.factory.IBaseMvpPresenterFactory;
import com.gibsoncool.beautiful_mvp.presenter.BaseMvpPresenter;
import com.gibsoncool.beautiful_mvp.view.IBaseMvpView;

import static com.gibsoncool.beautiful_mvp.presenter.BaseMvpPresenter.TAG_MVP;

/**
 * Created by GibsonCool on 2018/2/2
 */

public class BaseMvpProxy<V extends IBaseMvpView, P extends BaseMvpPresenter<V>> implements IMvpPresenterProxy<V, P>
{
	public static final String BUNDLE_KEY = "mvp_bundle";
	IBaseMvpPresenterFactory<V, P> mFactory;
	P                              mPresenter;
	private Bundle mBundle;

	public BaseMvpProxy(IBaseMvpPresenterFactory<V, P> factory)
	{
		mFactory = factory;
		Log.e(TAG_MVP, "BaseMvpProxy create mvpPresenterFactory =" + mFactory);
	}

	@Override
	public void setMvpPresenterFactory(IBaseMvpPresenterFactory<V, P> factory)
	{
		if (mPresenter != null)
			throw new IllegalArgumentException("这个方法只能在getMvpPresenter()之前调用，如果Presenter已经创建则不能再修改");
		mFactory = factory;
	}

	@Override
	public IBaseMvpPresenterFactory<V, P> getMvpPresenterFactory()
	{
		return mFactory;
	}

	@Override
	public P getMvpPresenter()
	{
		if (mFactory != null)
		{
			if (mPresenter == null)
			{
				Log.e(TAG_MVP, "mvpPresenterFactory.crateMvpPresenter()");
				mPresenter = mFactory.createMvpPresenter();
				mPresenter.onCreatePresenter(mBundle == null ? null : mBundle.getBundle(BUNDLE_KEY));
			}
		}

		return mPresenter;
	}

	public void onResume(V view)
	{
		getMvpPresenter();
		if (mPresenter != null && !mPresenter.isMvpViewAttach())
		{
			mPresenter.onMvpViewAttach(view);
		}
	}

	public void onDetachView()
	{

		if (mFactory != null && mPresenter.isMvpViewAttach())
		{
			mPresenter.onDetachMvpView();
		}
	}

	public void onDestroy()
	{
		if (mFactory != null)
		{
			onDetachView();
			mPresenter.onDestoryPresenter();
			mPresenter = null;
		}
	}

	public Bundle onSaveInstanceState()
	{
		Bundle bundle = new Bundle();
		getMvpPresenter();
		if (mPresenter != null)
		{
			Bundle presenterBundle = new Bundle();
			mPresenter.onSaveInstanceState(presenterBundle);
			bundle.putBundle(BUNDLE_KEY, presenterBundle);
		}
		return bundle;
	}

	public void onRestoreInstanceState(Bundle savedInstanceState)
	{

		mBundle = savedInstanceState.getBundle(BUNDLE_KEY);

	}

}
