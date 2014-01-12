package net.stenuit.xavier.androidherm25.db;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import net.stenuit.xavier.androidherm25.SqliteCreator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

@SuppressLint("SimpleDateFormat")
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

	/*
	 * Returns all aliases for the registered user indicated
	 */
	public ArrayList<Aliases> getAllAliases(RegisteredUser ru,boolean showall) {
		/*
		 * "CREATE  TABLE 'Aliases' ("+
		"'idAliases' integer primary key autoincrement,"+
		"'UnixUser' VARCHAR(12) NOT NULL ,"+
		"'email' VARCHAR(45) NULL DEFAULT NULL ,"+
		"'AliasSerial' INT(11) NOT NULL DEFAULT '0' ,"+
		"'Alias' VARCHAR(12) NOT NULL ,"+
		"'ValidFrom' DATE NULL DEFAULT NULL ,"+
		"'ValidUntil' DATE NULL DEFAULT NULL ,"+
		// "PRIMARY KEY ('idAliases') ,"+
		"CONSTRAINT 'fk_UniqeUser'"+
		"FOREIGN KEY ('UnixUser' )"+
		"REFERENCES 'RegisteredUsers' ('UnixUser' ))"
		 */
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		if(database==null) return null;
		Cursor c=database.query("Aliases",new String[]{"idAliases","UnixUser","email","AliasSerial","Alias","ValidFrom","ValidUntil"},
				"UnixUser='"+ru.getUnixUser()+"'",
				null,
				null,null,null
				);

		ArrayList<Aliases> ret=new ArrayList<Aliases>();
		try
		{
			c.moveToFirst();
			while(!c.isAfterLast())
			{
				Aliases a=new Aliases();
				
				a.setIdAliases(c.getInt(0));
				a.setUnixUser(c.getString(1));
				a.setEmail(c.getString(2));
				a.setAliasSerial(c.getInt(3));
				a.setAlias(c.getString(4));
				a.setValidFrom(sdf.parse(c.getString(5)));
				a.setValidUntil(sdf.parse(c.getString(6)));
				
				Log.i(TAG,"Alias:"+a);
				if(showall || a.getValidUntil().compareTo(new Date())>=0)
					ret.add(a);
				
				c.moveToNext();
			}
		} catch (ParseException e) {
			Log.e(TAG,"Caught "+e.getMessage(),e);
		}
		finally{}
		
		return ret;
	}
}
