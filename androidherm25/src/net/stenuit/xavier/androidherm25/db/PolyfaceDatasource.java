package net.stenuit.xavier.androidherm25.db;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import net.stenuit.xavier.androidherm25.SqliteCreator;

public class PolyfaceDatasource {
	private SqliteCreator dbHelper;
	private SQLiteDatabase database;

	public PolyfaceDatasource(Context context) {
		dbHelper = new SqliteCreator(context);
	}

	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
		database=null;
	}

	public SQLiteDatabase getDatabase() {
		return database;
	}
}
