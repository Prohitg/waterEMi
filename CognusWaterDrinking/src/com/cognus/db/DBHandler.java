/**
 * 
 */
package com.cognus.db;

import com.cognus.model.Alerts;
import com.cognus.model.Container;
import com.cognus.model.Entries;
import com.cognus.model.Goals;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @author user
 * 
 */
public class DBHandler extends SQLiteOpenHelper {

	private static final String DBNAME = "cognus.db";
	private static final int VERSION = 1;

	/**
	 * @param context
	 * @param name
	 * @param factory
	 * @param version
	 */
	public DBHandler(Context context) {
		super(context, DBNAME, null, VERSION);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param context
	 * @param name
	 * @param factory
	 * @param version
	 * @param errorHandler
	 */
	@SuppressLint("NewApi")
	public DBHandler(Context context, String name, CursorFactory factory,
			int version, DatabaseErrorHandler errorHandler) {
		super(context, name, factory, version, errorHandler);
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.database.sqlite.SQLiteOpenHelper#onCreate(android.database.sqlite
	 * .SQLiteDatabase)
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub

		db.execSQL(Entries.DDL);
		db.execSQL(Container.DDL);
		db.execSQL(Alerts.DDL);
		db.execSQL(Goals.DDL);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.database.sqlite.SQLiteOpenHelper#onUpgrade(android.database.sqlite
	 * .SQLiteDatabase, int, int)
	 */
	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}

}
