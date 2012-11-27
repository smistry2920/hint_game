package org.project.hint_game;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class NewJoinActivity extends Activity implements OnClickListener {

	private Button m_newGameButton;
	private Button m_joinGameButton;
	private Button m_joinGameCancelButton;
	private Button m_newGameCancelButton;
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	private TextView m_newGameText;
	private TextView m_joinGameText;
	private TextView m_joinedPlayerText;
	private TextView m_joinerUsernameText;
	private TextView m_creatorUsernameText;

	// boolean for checking if "create a game" has been clicked once
	boolean m_startGameCheck = false;
	boolean m_joinedStatus = false;

	// temporary variable for testing purposes...will probably remove!
	int m_joinedPlayers = 0;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_join);
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		m_newGameButton = (Button) findViewById(R.id.newGameButton);
		m_joinGameButton = (Button) findViewById(R.id.joinGameButton);
		m_newGameCancelButton = (Button) findViewById(R.id.newGameCancelButton);
		m_joinGameCancelButton = (Button) findViewById(R.id.joinGameCancelButton);
		m_newGameButton.setOnClickListener(this);
		m_joinGameButton.setOnClickListener(this);
		m_newGameCancelButton.setOnClickListener(this);
		m_joinGameCancelButton.setOnClickListener(this);

		m_newGameText = (TextView) findViewById(R.id.newGameText);
		m_joinGameText = (TextView) findViewById(R.id.joinGameText);
		m_joinedPlayerText = (TextView) findViewById(R.id.playersWaiting);
		m_creatorUsernameText = (TextView) findViewById(R.id.creatorUsernameText);
		m_joinerUsernameText = (TextView) findViewById(R.id.joinerUsernameText);
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.newGameButton:
			if (m_startGameCheck) {
				startGame();
			} else {
				newGame();
			}
			break;
		case R.id.joinGameButton:
			joinGame();
			break;
		case R.id.newGameCancelButton:
			newGameCancelButton();
			break;
		case R.id.joinGameCancelButton:
			joinGameCancelButton();
			break;
		default:
			break;
		}

	}

	// ********NOT SETUP FOR HTTP....FIX THIS WHEN NECESSARY!!!!!
	// NOTE: This thing is super bloated...probably want to split this up/new
	// class?

	// code to setup a new game! (now called "create a game"...MAYBE RENAME?)
	// code taken from online tutorial:
	// see http://androidsnippets.com/prompt-user-input-with-an-alertdialog
	public void newGame() {
		// alert dialog box for establishing a new game
		AlertDialog.Builder alert = new AlertDialog.Builder(this);
		alert.setTitle("Create a new game");
		alert.setMessage("New game name:");

		// for user input!!
		final EditText input = new EditText(this);
		alert.setView(input);

		// the 2 buttons!! ("create" and "cancel")
		alert.setPositiveButton("Create",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						String value = input.getText().toString();
						m_newGameText.setText("Your game name is: " + value);
						m_newGameButton.setText("Start Game");
						m_joinedPlayers = 1;
						m_joinedPlayerText.setText(Integer
								.toString(m_joinedPlayers)
								+ "/6 players in lobby");
						m_startGameCheck = true;
						createUsername(1);
					}
				});

		alert.setNegativeButton("Cancel",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
					}
				});

		alert.show();
	}

	// to join an existing game!
	// ***NOTE: will probably change this code entirely!
	// I just have sample test code in here to show players
	// being added to lobby.
	// *****Extra Note: Add dialog box to input game name!!
	public void joinGame() {
		if (m_joinedPlayers < 6) {
			++m_joinedPlayers;
			m_joinedPlayerText.setText(Integer.toString(m_joinedPlayers)
					+ "/6 players in lobby");
			m_joinGameText.setText("Joined Game!");
			m_joinedStatus = true;
			createUsername(2);
		} else {
			m_joinGameText
					.setText("ERROR: The game you are trying to join is full!");
		}
	}

	// can only be called after a game is created!
	public void startGame() {
		m_newGameText.setText("Game Started!");
		Intent intent = new Intent(NewJoinActivity.this, CharSelectActivity.class);
		startActivity(intent);
	}

	// for canceling a new game
	// if hit during "Create a game", send back to title screen
	public void newGameCancelButton() {
		if (m_startGameCheck) {
			m_startGameCheck = false;
			m_joinedPlayerText.setText(" ");
			m_newGameText.setText("Start a New Game!");
			m_newGameButton.setText("Create a Game");
			m_creatorUsernameText.setText(" ");
			m_joinedPlayers = 0;
		} else {
			Intent intent = new Intent(NewJoinActivity.this,
					TitleScreenActivity.class);
			startActivity(intent);
		}
	}

	// for canceling a joined game
	public void joinGameCancelButton() {
		if (m_joinedStatus) {
			m_joinedStatus = false;
			--m_joinedPlayers;
			m_joinGameText.setText("Join and Existing Game!");
			m_joinerUsernameText.setText(" ");
			m_joinedPlayerText.setText(Integer.toString(m_joinedPlayers)
					+ "/6 players in lobby");
		} else {
			Intent intent = new Intent(NewJoinActivity.this,
					TitleScreenActivity.class);
			startActivity(intent);
		}
	}

	public void createUsername(final int type) {
		// alert dialog box for establishing a username
		AlertDialog.Builder alert = new AlertDialog.Builder(this);
		alert.setTitle("Create a username");
		alert.setMessage("Type in a username:");

		// for user input!!
		final EditText input = new EditText(this);
		alert.setView(input);

		// the 2 buttons!! ("create" and "cancel")
		alert.setPositiveButton("Create",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						String value = input.getText().toString();
						if (type == 1) {
							m_creatorUsernameText.setText("Your username: "
									+ value);
						} else {
							m_joinerUsernameText.setText("Your username: "
									+ value);
						}
					}
				});

		alert.setNegativeButton("Cancel",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						if (type == 1) {
							newGameCancelButton();
						} else {
							joinGameCancelButton();
						}
					}
				});

		alert.show();

	}

}
