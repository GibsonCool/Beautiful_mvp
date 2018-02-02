package com.gibsoncool.beautiful_mvp.view;

import android.app.Activity;
import android.os.Bundle;

import com.gibsoncool.beautiful_mvp.factory.AnnotationMvpPresenterFactory;
import com.gibsoncool.beautiful_mvp.factory.IBaseMvpPresenterFactory;
import com.gibsoncool.beautiful_mvp.presenter.BaseMvpPresenter;
import com.gibsoncool.beautiful_mvp.proxy.BaseMvpProxy;
import com.gibsoncool.beautiful_mvp.proxy.IMvpPresenterProxy;

import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.gibsoncool.beautiful_mvp.proxy.BaseMvpProxy.BUNDLE_KEY;

/**
 * Created by GibsonCool on 2018/2/1
 */

public abstract class BaseMvpActivity<V extends IBaseMvpView, P extends BaseMvpPresenter<V>> extends Activity implements IMvpPresenterProxy<V,P>
{
	private BaseMvpProxy<V, P> mvpProxy = new BaseMvpProxy<>(AnnotationMvpPresenterFactory.<V, P>createFactory(getClass()));

	Unbinder bind;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(getContentView());
		bind = ButterKnife.bind(this);

		if (savedInstanceState != null)
			mvpProxy.onRestoreInstanceState(savedInstanceState);
		initView();
	}


	@Override
	protected void onSaveInstanceState(Bundle outState)
	{
		super.onSaveInstanceState(outState);
		outState.putBundle(BUNDLE_KEY, mvpProxy.onSaveInstanceState());
	}

	@Override
	protected void onResume()
	{
		super.onResume();
		mvpProxy.onResume((V) this);
		callTheEntrance();
	}

	@Override
	protected void onDestroy()
	{
		super.onDestroy();
		if (bind != null)
			bind.unbind();
		mvpProxy.onDestroy();
	}

	@Override
	public void setMvpPresenterFactory(IBaseMvpPresenterFactory factory)
	{
		mvpProxy.setMvpPresenterFactory(factory);
	}

	@Override
	public IBaseMvpPresenterFactory getMvpPresenterFactory()
	{
		return mvpProxy.getMvpPresenterFactory();
	}

	@Override
	public P getMvpPresenter()
	{
		return mvpProxy.getMvpPresenter();
	}

	public abstract int getContentView();
	public abstract void initView();
	protected abstract void callTheEntrance();

}
