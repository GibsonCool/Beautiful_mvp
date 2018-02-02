package com.gibsoncool.beautiful_mvp.factory;

import android.util.Log;

import com.gibsoncool.beautiful_mvp.presenter.BaseMvpPresenter;
import com.gibsoncool.beautiful_mvp.view.IBaseMvpView;

import static com.gibsoncool.beautiful_mvp.proxy.BaseMvpProxy.BUNDLE_KEY;

/**
 * Created by GibsonCool on 2018/2/2
 */

public class AnnotationMvpPresenterFactory<V extends IBaseMvpView, P extends BaseMvpPresenter<V>> implements IBaseMvpPresenterFactory<V, P>
{
	private final Class<P> mPresenterClass;

	public AnnotationMvpPresenterFactory(Class<P> mPresenterClass)
	{
		this.mPresenterClass = mPresenterClass;
	}

	public static <V extends IBaseMvpView, P extends BaseMvpPresenter<V>> AnnotationMvpPresenterFactory<V, P> createFactory(Class<?> clazz)
	{
		CreatePresenter annotation = clazz.getAnnotation(CreatePresenter.class);
		Class<P> pClass = null;
		Log.e(BUNDLE_KEY, "annotation = " + annotation);
		if (annotation != null)
			pClass = (Class<P>) annotation.value();
		else
			throw new IllegalArgumentException("使用默认注解工厂类创建presenter，检测是否在activity中使用了 CreatePresenter.class 注解");
		return new AnnotationMvpPresenterFactory<V, P>(pClass);
	}

	@Override
	public P createMvpPresenter()
	{
		try
		{
			return mPresenterClass.newInstance();
		}
		catch (Exception e)
		{
			Log.e(BUNDLE_KEY, "Presenter创建失败！请检查是否声明了 @CreatePresenter（xx.class) 注解");
			throw new RuntimeException("Presenter创建失败！请检查是否声明了 @CreatePresenter（xx.class) 注解");
		}
	}
}
