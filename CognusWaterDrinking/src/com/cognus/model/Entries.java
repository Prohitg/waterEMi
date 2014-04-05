package com.cognus.model;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.cognus.db.DBHandler;

public class Entries {
	private int _id;
	private String date;
	private String time;
	private int container_id;
	private Double capacity;
	private int unit;
	public static final String TABLENAME = "entries";
	public static final String[] COLNAME = { "_id", "date", "time",
			"drinkware", "capacity", "unit" };
	public static final String DDL = "CREATE TABLE IF Not EXISTS  entries (     _id       INTEGER,    date      TEXT,    time      TEXT,    drinkware INTEGER,    capacity  REAL,    unit      INTEGER,    PRIMARY KEY ( _id )  ON CONFLICT REPLACE );";
	public ContentValues values = new ContentValues();

	/**
	 * @param _id
	 * @param date
	 * @param time
	 * @param container_id
	 * @param capacity
	 * @param unit
	 */
	public Entries(int _id, String date, String time, int container_id,
			Double capacity, int unit) {
		super();
		this._id = _id;
		this.date = date;
		this.time = time;
		this.container_id = container_id;
		this.capacity = capacity;
		this.unit = unit;
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
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate(String date) {
		this.date = date;
		setValue();
	}

	/**
	 * @return the time
	 */
	public String getTime() {
		return time;
	}

	/**
	 * @param time
	 *            the time to set
	 */
	public void setTime(String time) {
		this.time = time;
		setValue();
	}

	/**
	 * @return the container_id
	 */
	public int getContainer_id() {
		return container_id;
	}

	/**
	 * @param container_id
	 *            the container_id to set
	 */
	public void setContainer_id(int container_id) {
		this.container_id = container_id;
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

	private void setValue() {
		values.put(COLNAME[0], _id);
		values.put(COLNAME[1], date);
		values.put(COLNAME[2], time);
		values.put(COLNAME[3], container_id);
		values.put(COLNAME[4], capacity);
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

	public Entries getEntry(Context context, String _id) {
		Entries entry = null;
		DBHandler dbHandler = new DBHandler(context);
		SQLiteDatabase db = dbHandler.getWritableDatabase();
		Cursor query = db.query(TABLENAME, COLNAME, "_id=?",
				new String[] { _id }, null, null, null);
		if (query.moveToFirst()) {
			// do {
			entry = new Entries(query.getInt(0), query.getString(1),
					query.getString(2), query.getInt(3), query.getDouble(4),
					query.getInt(5));
			// } while (query.moveToNext());
		}
		query.close();
		db.close();
		dbHandler.close();
		return entry;
	}

	public ArrayList<Entries> getEntries(Context context, String _id) {
		Entries entry = null;
		ArrayList<Entries> entries = new ArrayList<Entries>();
		DBHandler dbHandler = new DBHandler(context);
		SQLiteDatabase db = dbHandler.getWritableDatabase();
		Cursor query = db.query(TABLENAME, COLNAME, "_id=?",
				new String[] { _id }, null, null, null);
		if (query.moveToFirst()) {
			do {
				entry = new Entries(query.getInt(0), query.getString(1),
						query.getString(2), query.getInt(3),
						query.getDouble(4), query.getInt(5));
				entries.add(entry);
			} while (query.moveToNext());
		}
		query.close();
		db.close();
		dbHandler.close();
		return entries;
	}
}