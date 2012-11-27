package org.project.hint_game;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.view.MotionEvent;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.TextView;

public class TitleScreenActivity extends Activity {

	private TextView m_startText;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        
        m_startText = (TextView)findViewById(R.id.startText);
        
        Animation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(300); //You can manage the time of the blink with this parameter
        anim.setStartOffset(20);
        anim.setRepeatMode(Animation.REVERSE);
        anim.setRepeatCount(Animation.INFINITE);
        m_startText.startAnimation(anim);
    }
    
    public boolean onTouchEvent(MotionEvent event) {
    	m_startText.setText("tap registered!!");
    	Intent intent = new Intent(TitleScreenActivity.this, NewJoinActivity.class);
    	startActivity(intent);
    	
    	return true;
    }
}

