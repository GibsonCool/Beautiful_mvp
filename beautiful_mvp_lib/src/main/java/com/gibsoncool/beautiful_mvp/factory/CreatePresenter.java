package com.gibsoncool.beautiful_mvp.factory;

import com.gibsoncool.beautiful_mvp.presenter.BaseMvpPresenter;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * Created by GibsonCool on 2018/2/1
 */
@Inherited
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface CreatePresenter
{
	Class<? extends BaseMvpPresenter> value();
}
