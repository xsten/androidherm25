package net.stenuit.xavier.androidherm25;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class SqliteCreator extends SQLiteOpenHelper {
	private static final String TAG="androidherm25";
	
	private static final String[] drop={
		"DROP TABLE IF EXISTS 'RegisteredUsers'",
		"DROP TABLE IF EXISTS 'Aliases'",
		"DROP TABLE IF EXISTS 'Language'"
	};
	private static final String[] create={
		"CREATE  TABLE 'Language' ("+
				"'idLanguage' INT(11) NOT NULL ,"+
				"'Name' VARCHAR(45) NOT NULL ,"+
				"PRIMARY KEY ('idLanguage') )",
		// ---------------------------
		"CREATE  TABLE 'RegisteredUsers' ("+
		"'idRegisteredUsers' INT(11) NOT NULL ,"+
		"'UnixUser' VARCHAR(12) NOT NULL ,"+
		"'FirstName' VARCHAR(45) NOT NULL ,"+
		"'Surname' VARCHAR(45) NOT NULL ,"+
		"'ForwardMail' VARCHAR(45) NOT NULL ,"+
		"'Address' VARCHAR(200) NULL DEFAULT NULL ,"+
		"'Phone' VARCHAR(45) NULL DEFAULT NULL ,"+
		"'Language' INT(11) NOT NULL ,"+
		"'CreationDate' DATETIME NOT NULL ,"+
		"'UpdateDate' DATETIME NOT NULL ,"+
		"PRIMARY KEY ('idRegisteredUsers') ,"+
		"CONSTRAINT 'fk_language'"+
		"FOREIGN KEY ('Language' )"+
		"REFERENCES 'Language' ('idLanguage' )"+
		")",
		// --------------------------------------
		"CREATE  TABLE 'Aliases' ("+
		"'idAliases' INT(11) NOT NULL ,"+
		"'UnixUser' VARCHAR(12) NOT NULL ,"+
		"'email' VARCHAR(45) NULL DEFAULT NULL ,"+
		"'AliasSerial' INT(11) NOT NULL DEFAULT '0' ,"+
		"'Alias' VARCHAR(12) NOT NULL ,"+
		"'ValidFrom' DATE NULL DEFAULT NULL ,"+
		"'ValidUntil' DATE NULL DEFAULT NULL ,"+
		"PRIMARY KEY ('idAliases') ,"+
		"CONSTRAINT 'fk_UniqeUser'"+
		"FOREIGN KEY ('UnixUser' )"+
		"REFERENCES 'RegisteredUsers' ('UnixUser' ))"
};
	
	public SqliteCreator(Context context) {
		super(context, "polyface.db", null, 1);
		
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		Log.i(TAG,"onCreate() called");
		
		for(int i=0;i<create.length;i++)
		{
			Log.i(TAG,"Executing SQL : "+create[i]);
			database.execSQL(create[i]);
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase database, int arg1, int arg2) {
		Log.i(TAG,"onUpgrade() called");
		for(int i=0;i<drop.length;i++)
		{
			Log.d(TAG,"lmkj");
			Log.i(TAG,"Executing SQL : "+drop[i]);
			database.execSQL(drop[i]);
		}
		onCreate(database);
	}

}
