package net.stenuit.xavier.androidherm25.db;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import net.stenuit.xavier.androidherm25.SqliteCreator;

public class PolyfaceDatasource {
	private final String TAG="androidherm25";
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
	
	public RegisteredUser getRegisteredUser(long idRegisteredUser)
	{
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		if(database==null)return null;
		Cursor c=database.query("RegisteredUsers",new String[]{"address","creationDate","firstName","forwardMail","idRegisteredUsers","Language","phone","surName","unixUser","updateDate"},
				"idRegisteredUsers="+idRegisteredUser,
				null,
				null,null,null
				);

		RegisteredUser ret=null;
		
		if(c.getCount()==1)
		{
			try
			{
				c.moveToFirst();
				ret=new RegisteredUser();
				ret.setAddress(c.getString(0));
				ret.setCreationDate(sdf.parse(c.getString(1)));
				ret.setFirstName(c.getString(2));
				ret.setForwardMail(c.getString(3));
				ret.setIdRegisteredUser((int) c.getLong(4));
				ret.setLanguage(c.getInt(5));
				ret.setPhone(c.getString(6));
				ret.setSurName(c.getString(7));
				ret.setUnixUser(c.getString(8));
				ret.setUpdateDate(sdf.parse(c.getString(9)));
			} catch (ParseException e) {
				Log.e(TAG,"Error - could not parse date : "+c.getString(1));
			}

		}
		else
		{
			Log.e(TAG,"Problem - query returned "+c.getCount()+" rows");
		}
		
		return ret;
	}
}
