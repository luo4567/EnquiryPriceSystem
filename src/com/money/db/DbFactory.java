package com.money.db;

import money.gis.welcome.R;

import com.litesuits.orm.LiteOrm;
import com.litesuits.orm.db.DataBase;

import android.app.Application;
import android.content.Context;

/***
 * 单例模式，确保只有一个DataBase实例
 * @author luohuang
 *
 */
public class DbFactory extends Application {
	private static Context instance;

	@Override
	public void onCreate() {
		super.onCreate();
		instance = this;
	}

	private static class DbHelper {
		private final static DataBase instance = LiteOrm.newInstance(getInstance(), getInstance().getResources().getString(R.string.db_name));
	}

	// 获取DataBase
	public static DataBase getDataBase() {
		return DbHelper.instance;
	}

	//获取Context对象
	public static Context getInstance() {
		return instance;
	}
}
