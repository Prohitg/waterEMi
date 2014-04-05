package com.cognus.model;

import java.util.ArrayList;

import com.cognus.db.DBHandler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class Container {
	private int _id;
	private Double capacity;
	private int unit;
	private int drawable;
	public static final String TABLENAME = "container";
	public static final String[] COLNAME = { "_id", "capacity", "unit",
			"drawable" };
	public static final String DDL = "CREATE TABLE IF Not EXISTS container   (   _id      INTEGER,    capacity REAL,    unit     INTEGER,    drawable INTEGER,    PRIMARY KEY ( _id )  ON CONFLICT REPLACE );";
	public ContentValues values = new ContentValues();

	/**
	 * @param _id
	 * @param capacity
	 * @param unit
	 * @param drawable
	 */
	public Container(int _id, Double capacity, int unit, int drawable) {
		super();
		this._id = _id;
		this.capacity = capacity;
		this.unit = unit;
		this.drawable = drawable;
		setValue();
	}

	/**
	 * @return the _id
	 */
	public int get_id() {
		return _id;
	}

	/**
	 * @param _id
	 *            the _id to set
	 */
	public void set_id(int _id) {
		this._id = _id;
		setValue();
	}

	/**
	 * @return the capacity
	 */
	public Double getCapacity() {
		return capacity;
	}

	/**
	 * @param capacity
	 *            the capacity to set
	 */
	public void setCapacity(Double capacity) {
		this.capacity = capacity;
		setValue();
	}

	/**
	 * @return the unit
	 */
	public int getUnit() {
		return unit;
	}

	/**
	 * @param unit
	 *            the unit to set
	 */
	public void setUnit(int unit) {
		this.unit = unit;
		setValue();
	}

	/**
	 * @return the drawable
	 */
	public int getDrawable() {
		return drawable;
	}

	/**
	 * @param drawable
	 *            the drawable to set
	 */
	public void setDrawable(int drawable) {
		this.drawable = drawable;
		setValue();
	}

	private void setValue() {
		values.put(COLNAME[0], _id);
		values.put(COLNAME[1], capacity);
		values.put(COLNAME[2], unit);
		values.put(COLNAME[3], drawable);
	}

	public long insert(Context context) {
		long insert;
		DBHandler dbHandler = new DBHandler(context);
		SQLiteDatabase db = dbHandler.getWritableDatabase();
		values.remove(COLNAME[0]);
		insert = db.insert(TABLENAME, "_id", values);
		db.close();
		dbHandler.close();
		return insert;
	}

	public long delete(Context context, String whereClause, String[] whereArgs) {
		long delete;
		DBHandler dbHandler = new DBHandler(context);
		SQLiteDatabase db = dbHandler.getWritableDatabase();
		delete = db.delete(TABLENAME, whereClause, whereArgs);
		db.close();
		dbHandler.close();
		return delete;
	}

	public long update(Context context, String whereClause, String[] whereArgs) {
		long update;
		DBHandler dbHandler = new DBHandler(context);
		SQLiteDatabase db = dbHandler.getWritableDatabase();
		update = db.update(TABLENAME, values, whereClause, whereArgs);
		db.close();
		dbHandler.close();
		return update;
	}

	public static Container getContainer(Context context, String _id) {
		Container container = null;
		DBHandler dbHandler = new DBHandler(context);
		SQLiteDatabase db = dbHandler.getWritableDatabase();
		Cursor query = db.query(TABLENAME, COLNAME, "_id=?",
				new String[] { _id }, null, null, null);
		if (query.moveToFirst()) {

			container = new Container(query.getInt(0), query.getDouble(1),
					query.getInt(2), query.getInt(3));

		}

		query.close();
		db.close();
		dbHandler.close();

		return container;
	}

	public static ArrayList<Container> getContainers(Context context,
			String selection, String[] selectionArgs) {
		Container container = null;
		DBHandler dbHandler = new DBHandler(context);
		SQLiteDatabase db = dbHandler.getWritableDatabase();
		Cursor query = db.query(TABLENAME, COLNAME, selection, selectionArgs,
				null, null, null);

		ArrayList<Container> list = new ArrayList<Container>();
		if (query.moveToFirst()) {
			do {
				container = new Container(query.getInt(0), query.getDouble(1),
						query.getInt(2), query.getInt(3));
				list.add(container);
			} while (query.moveToNext());
		}

		query.close();
		db.close();
		dbHandler.close();

		return list;
	}
}
