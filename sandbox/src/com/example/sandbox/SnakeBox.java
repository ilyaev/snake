package com.example.sandbox;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnTouchListener;

public class SnakeBox extends Activity implements OnTouchListener {

	private SnakeBoxSurface aSurfaceView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		aSurfaceView = new SnakeBoxSurface(this);
		aSurfaceView.setOnTouchListener(this);
		setContentView(aSurfaceView);		
	}

	@Override
	protected void onPause() {
		super.onPause();
		aSurfaceView.pause();
	}

	@Override
	protected void onResume() {
		super.onResume();
		aSurfaceView.resume();
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		aSurfaceView.snake.processTouch(event);
		return true;
	}	
	

}
