package net.stenuit.xavier.androidherm25;

import net.stenuit.xavier.androidherm25.db.PolyfaceDatasource;
import net.stenuit.xavier.androidherm25.db.RegisteredUser;
import android.os.Bundle;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends Activity implements OnItemClickListener {
	private static final String TAG="androidherm25";
	private PolyfaceDatasource datasource;
	Cursor cursor;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		datasource=new PolyfaceDatasource(this);
		datasource.open();
		
		SQLiteDatabase database=datasource.getDatabase();
		final String[] columns=new String[]{"Surname","FirstName","ForwardMail"};
		final int[] toViews={R.id.textView1,R.id.textView2,R.id.textView3};
		String qry="select idRegisteredUsers as _id";
		for(String c:columns) qry+=","+c;
		qry+=" from RegisteredUsers order by Surname COLLATE NOCASE";
		cursor=database.rawQuery(qry,null);
		ListView userListView=(ListView)findViewById(R.id.userListView);
		
		SimpleCursorAdapter adapter=new SimpleCursorAdapter(this, R.layout.userandname, cursor, columns, toViews,0);
		userListView.setAdapter(adapter);
		
		userListView.setOnItemClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	protected void onPause() {
		datasource.close();
		super.onPause();
	}

	@Override
	protected void onResume() {
		datasource.open();
		super.onResume();
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		Log.i(TAG,"AdapterView="+arg0);
		Log.i(TAG,"View="+arg1);
		Log.i(TAG,"expected view="+findViewById(R.id.userListView));
		Log.i(TAG,"third argument (int)="+arg2);
		Log.i(TAG,"fourth argument (long)="+arg3);
		if(arg0==findViewById(R.id.userListView))
		{
			RegisteredUser ru=datasource.getRegisteredUser(arg3);
			Log.i(TAG,"RegisteredUser="+ru);
		}
	}

}
