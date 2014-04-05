/**
 * 
 */
package com.cognus.model;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.cognus.db.DBHandler;

/**
 * @author user
 * 
 */
public class Goals {

	private String date;
	private String dailyGoal;
	private int parameterState;
	private Double hotdayValue;
	private Double highactivityvalue;
	private int unit;
	public static final String TABLENAME = "goals";
	public static final String[] COLNAME = { "date", "dailyGoal",
			"parameterState", "hotDayValue", "hightenedActivityValue", "unit" };
	public static final String DDL = "CREATE TABLE IF Not EXISTS goals (     date                   TEXT,    dailyGoal              REAL,    parameterState         INTEGER,    hotDayValue            REAL, hightenedActivityValue REAL,    unit                   INTEGER,    PRIMARY KEY ( date )  ON CONFLICT REPLACE );";
	public ContentValues values = new ContentValues();

	/**
	 * @param date
	 * @param dailyGoal
	 * @param parameterState
	 * @param hotdayValue
	 * @param highactivityvalue
	 * @param unit
	 */
	public Goals(String date, String dailyGoal, int parameterState,
			Double hotdayValue, Double highactivityvalue, int unit) {
		super();
		this.date = date;
		this.dailyGoal = dailyGoal;
		this.parameterState = parameterState;
		this.hotdayValue = hotdayValue;
		this.highactivityvalue = highactivityvalue;
		this.unit = unit;
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
	 * @return the dailyGoal
	 */
	public String getDailyGoal() {
		return dailyGoal;
	}

	/**
	 * @param dailyGoal
	 *            the dailyGoal to set
	 */
	public void setDailyGoal(String dailyGoal) {
		this.dailyGoal = dailyGoal;
		setValue();

	}

	/**
	 * @return the parameterState
	 */
	public int getParameterState() {
		return parameterState;
	}

	/**
	 * @param parameterState
	 *            the parameterState to set
	 */
	public void setParameterState(int parameterState) {
		this.parameterState = parameterState;
		setValue();

	}

	/**
	 * @return the hotdayValue
	 */
	public Double getHotdayValue() {
		return hotdayValue;
	}

	/**
	 * @param hotdayValue
	 *            the hotdayValue to set
	 */
	public void setHotdayValue(Double hotdayValue) {
		this.hotdayValue = hotdayValue;
		setValue();

	}

	/**
	 * @return the highactivityvalue
	 */
	public Double getHighactivityvalue() {
		return highactivityvalue;
	}

	/**
	 * @param highactivityvalue
	 *            the highactivityvalue to set
	 */
	public void setHighactivityvalue(Double highactivityvalue) {
		this.highactivityvalue = highactivityvalue;
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
		values.put(COLNAME[0], date);
		values.put(COLNAME[1], dailyGoal);
		values.put(COLNAME[2], parameterState);
		values.put(COLNAME[3], hotdayValue);
		values.put(COLNAME[4], highactivityvalue);
		values.put(COLNAME[5], unit);
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
	public Goals selectGoal(Context context, String date) {

		DBHandler dbHandler = new DBHandler(context);
		SQLiteDatabase db = dbHandler.getReadableDatabase();
		Goals goals = null;
		Cursor query = db.query(TABLENAME, COLNAME, "date=?",
				new String[] { date }, null, null, null);
		if (query.moveToFirst()) {
			goals = new Goals(query.getString(0), query.getString(1),
					query.getInt(2), query.getDouble(3), query.getDouble(4),
					query.getInt(5));

		}
		db.close();
		dbHandler.close();
		return goals;
	}

	/**
	 * 
	 * @param context
	 * @param selection
	 * @param selectionArgs
	 * @return
	 */
	public ArrayList<Goals> selectGoals(Context context, String selection,
			String[] selectionArgs) {

		DBHandler dbHandler = new DBHandler(context);
		SQLiteDatabase db = dbHandler.getReadableDatabase();
		Goals goals = null;
		Cursor query = db.query(TABLENAME, COLNAME, selection, selectionArgs,
				null, null, null);
		ArrayList<Goals> list = new ArrayList<Goals>();
		if (query.moveToFirst()) {
			do {
				goals = new Goals(query.getString(0), query.getString(1),
						query.getInt(2), query.getDouble(3),
						query.getDouble(4), query.getInt(5));
				list.add(goals);
			} while (query.moveToNext());
		}
		query.close();
		db.close();
		dbHandler.close();
		return list;
	}

}
