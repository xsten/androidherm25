package net.stenuit.xavier.androidherm25;

import net.stenuit.xavier.androidherm25.db.PolyfaceDatasource;
import android.os.Bundle;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends Activity {
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

}
