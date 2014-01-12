package net.stenuit.xavier.androidherm25;

import java.util.List;

import net.stenuit.xavier.androidherm25.db.Aliases;
import net.stenuit.xavier.androidherm25.db.PolyfaceDatasource;
import net.stenuit.xavier.androidherm25.db.RegisteredUser;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class UserAliasesActivity extends Activity {
	private PolyfaceDatasource datasource;
	private RegisteredUser ru;
	private List<Aliases> al;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if(getIntent().getExtras()!=null)
		{
			long id=getIntent().getExtras().getLong("registeredUser");
		
			datasource=new PolyfaceDatasource(this);
			datasource.open();
			ru=datasource.getRegisteredUser(id);
			// al=datasource.getAllAliases(ru,true); // show all
			al=datasource.getAllAliases(ru, false); // show only active aliases
		}
		
		setTitle(ru.toString());
		
		setContentView(R.layout.useraliases);
	
		ArrayAdapter<Aliases> arrayAdapter=new ArrayAdapter<Aliases>(this, /*android.R.layout.simple_expandable_list_item_1*/R.layout.aliasanddate,al);
		ListView aliasListView=(ListView)findViewById(R.id.aliasListView);
		aliasListView.setAdapter(arrayAdapter);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.user_aliases, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
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
