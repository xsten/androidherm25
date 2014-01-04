package net.stenuit.xavier.androidherm25;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class SqliteCreator extends SQLiteOpenHelper {
	private static final String TAG="androidherm25";
	private Context ctx;
	
	private static final String[] drop={
		"DROP TABLE IF EXISTS 'RegisteredUsers'",
		"DROP TABLE IF EXISTS 'Aliases'",
		"DROP TABLE IF EXISTS 'Language'"
	};
	private static final String[] create={
		"CREATE  TABLE 'Language' ("+
				"'idLanguage' integer primary key autoincrement,"+
				"'Name' VARCHAR(45) NOT NULL )",
		//		+ ",PRIMARY KEY ('idLanguage') )",
		// ---------------------------
		"CREATE  TABLE 'RegisteredUsers' ("+
		"'idRegisteredUsers' integer primary key autoincrement,"+
		"'UnixUser' VARCHAR(12) NOT NULL ,"+
		"'FirstName' VARCHAR(45) NOT NULL ,"+
		"'Surname' VARCHAR(45) NOT NULL ,"+
		"'ForwardMail' VARCHAR(45) NOT NULL ,"+
		"'Address' VARCHAR(200) NULL DEFAULT NULL ,"+
		"'Phone' VARCHAR(45) NULL DEFAULT NULL ,"+
		"'Language' INT(11) NOT NULL ,"+
		"'CreationDate' DATETIME NOT NULL ,"+
		"'UpdateDate' DATETIME NOT NULL ,"+
		// "PRIMARY KEY ('idRegisteredUsers') ,"+
		"CONSTRAINT 'fk_language'"+
		"FOREIGN KEY ('Language' )"+
		"REFERENCES 'Language' ('idLanguage' )"+
		")",
		// --------------------------------------
		"CREATE  TABLE 'Aliases' ("+
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
};
	
	public SqliteCreator(Context context) {
		super(context, "polyface.db", null, 1);
		ctx=context;
	}

	@SuppressLint("SimpleDateFormat")
	@Override
	public void onCreate(SQLiteDatabase database) {
		Log.i(TAG,"onCreate() called");
		
		
		
		for(int i=0;i<create.length;i++)
		{
			Log.i(TAG,"Executing SQL : "+create[i]);
			database.execSQL(create[i]);
		}
		
		InputStream is=null;
		BufferedReader br=null;

		try
		{
			is=ctx.getResources().openRawResource(R.raw.aliases);
			br=new BufferedReader(new InputStreamReader(is));
			
			for(String line="";line!=null;line=br.readLine())
			{
				String[] st=line.split("\\t");
				int idx=0;
				
				Log.d(TAG,"number of tags : "+st.length);
				Log.d(TAG,"first tag : "+st[0]);
				
				if(st.length==7)
				{
					ContentValues cv=new ContentValues();
					
					cv.put("idAliases",Integer.parseInt(st[idx++]));
					cv.put("unixUser",st[idx++]);
					cv.put("email",st[idx++]);
					cv.put("aliasSerial",Integer.parseInt(st[idx++]));
					cv.put("alias",st[idx++]);
					cv.put("validFrom",st[idx++]);
					cv.put("validUntil",st[idx++]);
					Log.d(TAG,"cv="+cv.toString());
					
					database.insert("Aliases", null, cv);
				}
			}
		}
		catch(Exception ioe)
		{
			Log.e(TAG,"Exception caught",ioe);
		}
		finally
		{
			if(br!=null)try{br.close();}catch(Exception e){};
			if(is!=null)try{is.close();}catch(Exception e){};
			br=null;
			is=null;
		}
		
		try
		{
			is=ctx.getResources().openRawResource(R.raw.registeredusers);
			br=new BufferedReader(new InputStreamReader(is));
			
			for(String line="";line!=null;line=br.readLine())
			{
				String[] st=line.split("\\t");
				int idx=0;
				
				Log.d(TAG,"number of tags : "+st.length);
				Log.d(TAG,"first tag : "+st[0]);
				
				if(st.length==10)
				{
					ContentValues cv=new ContentValues();
					
					cv.put("idRegisteredUsers",Integer.parseInt(st[idx++]));
					cv.put("unixUser",st[idx++]);
					cv.put("firstName",st[idx++]);
					cv.put("surName",st[idx++]);
					cv.put("forwardMail",st[idx++]);
					cv.put("Address",st[idx++]);
					cv.put("Phone",st[idx++]);
					cv.put("Language",st[idx++]);
					cv.put("CreationDate",st[idx++]);
					cv.put("UpdateDate",st[idx++]);
					
					Log.d(TAG,"cv="+cv.toString());
					
					database.insert("RegisteredUsers", null, cv);
				}
			}
		}
		catch(Exception ioe)
		{
			Log.e(TAG,"Exception caught",ioe);
		}
		finally
		{
			if(br!=null)try{br.close();}catch(Exception e){};
			if(is!=null)try{is.close();}catch(Exception e){};
			br=null;
			is=null;
		}
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase database, int arg1, int arg2) {
		Log.i(TAG,"onUpgrade() called, arg1="+arg1+"arg2="+arg2);
/*		for(int i=0;i<drop.length;i++)
		{
			Log.i(TAG,"Executing SQL : "+drop[i]);
			database.execSQL(drop[i]);
		}
		onCreate(database);
*/	}

}
