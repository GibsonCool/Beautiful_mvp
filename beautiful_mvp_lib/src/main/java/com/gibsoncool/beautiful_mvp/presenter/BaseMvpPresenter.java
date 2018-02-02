package com.gibsoncool.beautiful_mvp.presenter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.gibsoncool.beautiful_mvp.view.IBaseMvpView;

/**
 * Created by GibsonCool on 2018/2/1
 */

public abstract class BaseMvpPresenter<V extends IBaseMvpView>
{
	public static final String TAG_MVP = "Bae-mvp";
	private V mvpView;

	public void onCreatePresenter(@Nullable Bundle saveState)
	{

	}

	/**
	 * 在Presenter意外销毁的时候被调用，它的调用时机和Activity、Fragment、View中的onSaveInstanceState
	 * 时机相同
	 *
	 * @param outState
	 */
	public void onSaveInstanceState(Bundle outState)
	{
		Log.e(TAG_MVP, "P onSaveInstanceState = ");
	}

	public void onDestoryPresenter()
	{
		Log.e(TAG_MVP, "P onDestoryPresenter = ");
	}

	public V getMvpView()
	{
		return mvpView;
	}

	public void onMvpViewAttach(V mvpView)
	{
		this.mvpView = mvpView;
	}

	public boolean isMvpViewAttach()
	{
		return mvpView != null;
	}

	public void onDetachMvpView()
	{
		mvpView = null;
	}
}
