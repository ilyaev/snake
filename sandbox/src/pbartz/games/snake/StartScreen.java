package pbartz.games.snake;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import pbartz.games.snake.R;


public class StartScreen extends ListActivity {

	String menuOptions[] = {"First", "DrawBox", "SnakeBox", "Fourth"};	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setListAdapter(new ArrayAdapter<String>(StartScreen.this, android.R.layout.simple_list_item_1, menuOptions));
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);

		try {
			@SuppressWarnings("rawtypes")
			Class runClass = Class.forName("pbartz.games.snake." + menuOptions[position]);
			Intent runIntent = new Intent(StartScreen.this, runClass);
			startActivity(runIntent);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		super.onCreateOptionsMenu(menu);
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.mainmenu, menu);
		return true;
	}
}
