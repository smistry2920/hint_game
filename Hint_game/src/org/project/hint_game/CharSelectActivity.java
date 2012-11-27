package org.project.hint_game;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class CharSelectActivity extends Activity implements OnClickListener {

	private Button m_char1Button;
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_char_select);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        m_char1Button = (Button)findViewById(R.id.char1Button);
        m_char1Button.setOnClickListener(this);
	}

	public void onClick(View v) {
//		Intent intent = new Intent(CharSelectActivity.this, );
//		startActivity(intent);
	}
}
