package com.money.db;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import android.database.sqlite.SQLiteDatabase;
import com.litesuits.orm.db.DataBase;
import com.litesuits.orm.db.TableManager;
import com.litesuits.orm.db.assit.Checker;
import com.litesuits.orm.db.assit.QueryBuilder;
import com.litesuits.orm.db.impl.SQLStatement;
import com.litesuits.orm.db.model.ColumnsValue;
import com.litesuits.orm.db.model.ConflictAlgorithm;
import com.litesuits.orm.db.model.Relation;

public class DbMethod {
	private static DataBase db;

	public DbMethod() {
		db=DbFactory.getDataBase();
	}

	public static void execute(SQLiteDatabase db, SQLStatement statement) {
		if (statement != null)
			statement.execute(db);
	}

	public static long save(Object entity) {
		return db.save(entity);
	}

	public static int save(Collection<?> collection) {
		return db.save(collection);
	}

	public static long insert(Object entity) {
		return db.insert(entity);
	}

	public static long insert(Object entity, ConflictAlgorithm conflictAlgorithm) {
		return db.insert(entity, conflictAlgorithm);
	}

	public static int insert(Collection<?> collection) {
		return db.insert(collection);
	}

	public static int insert(Collection<?> collection, ConflictAlgorithm conflictAlgorithm) {
		return db.insert(collection, conflictAlgorithm);
	}

	public static int update(Object entity) {
		return db.update(entity);
	}

	public static int update(Object entity, ConflictAlgorithm conflictAlgorithm) {
		return db.update(entity, conflictAlgorithm);
	}

	public static int update(Object entity, ColumnsValue cvs, ConflictAlgorithm conflictAlgorithm) {
		return db.update(entity, cvs, conflictAlgorithm);
	}

	public static int update(Collection<?> collection) {
		return db.update(collection);
	}

	public static int update(Collection<?> collection, ConflictAlgorithm conflictAlgorithm) {
		return db.update(collection, conflictAlgorithm);
	}

	public static int update(Collection<?> collection, ColumnsValue cvs, ConflictAlgorithm conflictAlgorithm) {
		return db.update(collection, cvs, conflictAlgorithm);
	}

	public int delete(Object entity) {
		return db.delete(entity);
	}

	public static int deleteAll(Class<?> claxx) {
		return db.deleteAll(claxx);
	}

	public static int delete(Class<?> claxx, long start, long end, String orderAscColu) {
		return db.delete(claxx, start, end, orderAscColu);
	}

	public static int delete(Collection<?> collection) {
		return db.delete(collection);
	}

	public static long queryCount(Class<?> claxx) {
		return db.queryCount(claxx);
	}

	public static long queryCount(QueryBuilder qb) {
		return db.queryCount(qb);
	}

	public static <T> ArrayList<T> query(QueryBuilder qb) {
		return db.query(qb);
	}

	/***
	 * 通过字段属性获取单条记录
	 * 
	 * @param clazz
	 * @param property
	 * @param object
	 * @return
	 */
	public static <T> T getUniqueByProperty(Class<T> clazz, String property, Object object) {
		return getUniqueByProperties(clazz, new String[] { property }, new Object[] { object });
	}

	/***
	 * 通过多个字段属性获取单条记录
	 * 
	 * @param clazz
	 * @param properties
	 * @param objs
	 * @return
	 */
	public static <T> T getUniqueByProperties(Class<T> clazz, String[] properties, Object[] objs) {
		QueryBuilder builder = new QueryBuilder(clazz);
		String filter = " and 1=1";
		ArrayList<Object> objects = new ArrayList<Object>();
		for (int i = 0; i < objs.length; i++) {
			filter += " and" + properties[i] + "=?";
			objects.add(objs[i]);
		}
		builder.where(filter, objects.toArray());
		ArrayList<T> list = db.query(builder);
		if (!Checker.isEmpty(list)) {
			if (list.size() > 1) {
				return null;
			}
			return list.get(0);
		} else {
			return null;
		}
	}

	/***
	 * 通过单个字段值获取多条数据
	 * 
	 * @param clazz
	 * @param property
	 * @param object
	 * @return
	 */
	public static <T> ArrayList<T> getIListByProperty(Class<T> clazz, String property, Object object, Page... pages) {
		return getIListByProperties(clazz, new String[] { property }, new Object[] { object });
	}

	/***
	 * 通过多个字段值获取多条数据
	 * 
	 * @param clazz
	 * @param properties
	 * @param objects
	 * @return
	 */
	public static <T> ArrayList<T> getIListByProperties(Class<T> clazz, String[] properties, Object[] objects, Page... pages) {
		QueryBuilder builder = new QueryBuilder(clazz);
		String filter = " and 1=1";
		ArrayList<Object> objs = new ArrayList<Object>();
		for (int i = 0; i < objects.length; i++) {
			filter += " and" + properties[i] + "=?";
			objs.add(objects[i]);
		}
		builder.where(filter, objs.toArray());
		if (pages.length > 0) {
			builder.limit(pages[0].getLimit());
			builder.orderBy(pages[0].sortProperty);
		}
		return db.query(builder);
	}

	/***
	 * 通过自定义条件获取多条数据
	 * 
	 * @param clazz
	 * @param filter
	 * @param objects
	 * @return
	 */
	public static <T> ArrayList<T> getIListByFilter(Class<T> clazz, String filter, Object[] objects, String limit) {
		QueryBuilder builder = new QueryBuilder(clazz);
		builder.where(filter, objects);
		builder.limit(limit);
		return db.query(builder);
	}

	public static <T> T queryById(long id, Class<T> clazz) {
		return db.queryById(id, clazz);
	}

	public static <T> T queryById(String id, Class<T> clazz) {
		return db.queryById(id, clazz);
	}

	public static <T> ArrayList<T> queryAll(Class<T> claxx) {
		return db.queryAll(claxx);
	}

	public static ArrayList<Relation> queryRelation(Class<?> class1, Class<?> class2, List<String> key1List, List<String> key2List) {
		return db.queryRelation(class1, class2, key1List, key2List);
	}

	public static <E, T> boolean mapping(Collection<E> col1, Collection<T> col2) {
		return db.mapping(col1, col2);
	}

	public static SQLiteDatabase getReadableDatabase() {
		return db.getReadableDatabase();
	}

	public static SQLiteDatabase getWritableDatabase() {
		return db.getWritableDatabase();
	}

	public static TableManager getTableManager() {
		return db.getTableManager();
	}

	public static void close() {
		db.close();
	}

}
