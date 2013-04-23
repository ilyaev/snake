package pbartz.games.snake;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import pbartz.games.snake.R;

public class First extends Activity implements OnClickListener {

	Button btn;
	EditText et;
	TextView tv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.first);

		btn = (Button) findViewById(R.id.bPressMe);
		et = (EditText) findViewById(R.id.etOne);
		tv = (TextView) findViewById(R.id.tvOne);

		btn.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.bPressMe:
			tv.setText("You entered: " + et.getText().toString());
			break;
		}

	}

}