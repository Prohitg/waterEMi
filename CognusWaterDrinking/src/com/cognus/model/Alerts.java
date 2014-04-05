package com.cognus.model;

import java.util.ArrayList;

import com.cognus.db.DBHandler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class Alerts {
	private int _id;
	private String time;
	private int enable;
	private String weekdays;
	public final String TABLENAME = "alerts";

	public static final String[] COLNAME = { "_id", "time", "enabled",
			"weekdays" };
	public static final String DDL = "CREATE TABLE IF Not EXISTS alerts (     _id      INTEGER,    time     TEXT,    enabled  INTEGER,    weekdays TEXT,    PRIMARY KEY ( _id )  ON CONFLICT REPLACE );";
	public ContentValues values = new ContentValues();

	/**
	 * @param _id
	 * @param time
	 * @param enable
	 * @param weekdays
	 */
	public Alerts(int _id, String time, int enable, String weekdays) {
		super();
		this._id = _id;
		this.time = time;
		this.enable = enable;
		this.weekdays = weekdays;
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
	 * @return the enable
	 */
	public int getEnable() {
		return enable;
	}

	/**
	 * @param enable
	 *            the enable to set
	 */
	public void setEnable(int enable) {
		this.enable = enable;
		setValue();
	}

	/**
	 * @return the weekdays
	 */
	public String getWeekdays() {
		return weekdays;
	}

	/**
	 * @param weekdays
	 *            the weekdays to set
	 */
	public void setWeekdays(String weekdays) {
		this.weekdays = weekdays;
		setValue();
	}

	private void setValue() {
		values.put(COLNAME[0], _id);
		values.put(COLNAME[1], time);
		values.put(COLNAME[2], enable);
		values.put(COLNAME[3], weekdays);
	}
/**
 * 
 * @param context
 * @return
 */
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

	/**
	 * 
	 * @param context
	 * @param whereClause
	 * @param whereArgs
	 * @return
	 */
	public long delete(Context context, String whereClause, String[] whereArgs) {
		long delete;
		DBHandler dbHandler = new DBHandler(context);
		SQLiteDatabase db = dbHandler.getWritableDatabase();
		delete = db.delete(TABLENAME, whereClause, whereArgs);
		db.close();
		dbHandler.close();
		return delete;
	}

	/**
	 * 
	 * @param context
	 * @param whereClause
	 * @param whereArgs
	 * @return
	 */
	public long update(Context context, String whereClause, String[] whereArgs) {
		long update;
		DBHandler dbHandler = new DBHandler(context);
		SQLiteDatabase db = dbHandler.getWritableDatabase();
		update = db.update(TABLENAME, values, whereClause, whereArgs);
		db.close();
		dbHandler.close();
		return update;
	}

	/**
	 * 
	 * @param context
	 * @param _id
	 * @return
	 */
	public Alerts getAlert(Context context, String _id) {
		Alerts alerts = null;
		DBHandler dbHandler = new DBHandler(context);
		SQLiteDatabase db = dbHandler.getReadableDatabase();
		Cursor query = db.query(TABLENAME, COLNAME, "_id=?",
				new String[] { _id }, null, null, null);
		if (query.moveToFirst()) {
			alerts = new Alerts(query.getInt(0), query.getString(1),
					query.getInt(2), query.getString(3));

		}
		db.close();
		dbHandler.close();
		return alerts;
	}

	/**
	 * 
	 * @param context
	 * @param selection
	 * @param selectionArgs
	 * @return
	 */
	public ArrayList<Alerts> getAlerts(Context context, String selection,
			String[] selectionArgs) {
		Alerts alerts = null;
		ArrayList<Alerts> alerts2 = new ArrayList<Alerts>();
		DBHandler dbHandler = new DBHandler(context);
		SQLiteDatabase db = dbHandler.getReadableDatabase();
		Cursor query = db.query(TABLENAME, COLNAME, selection, selectionArgs,
				null, null, null);
		if (query.moveToFirst()) {
			do {
				alerts = new Alerts(query.getInt(0), query.getString(1),
						query.getInt(2), query.getString(3));
				alerts2.add(alerts);
			} while (query.moveToNext());
		}
		query.close();
		db.close();
		dbHandler.close();
		return alerts2;
	}
}