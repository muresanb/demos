package com.example.testproject.gui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.example.testproject.MyApplicationObject;
import com.example.testproject.R;
import com.example.testproject.datastructure.Contact;

public class ActivityB extends SherlockActivity {
	private final static String TAG = ActivityB.class.getSimpleName();
	private ActionBar actionBar = null;
	private Contact contact = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.d(TAG, "Activity onCreate started");
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_b);

		Intent intent = getIntent();
		Log.d(TAG, "Activity onCreate intent recieved");
		this.contact = new Contact();

		this.contact.setName(intent.getStringExtra("name"));
		this.contact.setEmail(intent.getStringExtra("email"));
		this.contact.setPhoneNr(intent.getStringExtra("phoneNr"));
		Log.d(TAG, "Activity onCreate contact set");
		this.actionBar = getSupportActionBar();
		this.actionBar.setTitle(intent.getStringExtra("name"));
		Log.d(TAG, "Activity onCreate ended");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		Log.d(TAG, "Activity onCreateOptionsMenu started");
		MenuInflater inflater = getSupportMenuInflater();
		Log.d(TAG, "Activity onCreateOptionsMenu Menu inflater is set");
		inflater.inflate(R.menu.menu2, menu);
		Log.d(TAG, "Activity onCreateOptionsMenu ended");
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		Log.d(TAG, "Activity onOptionsItemSelected started");
		switch (item.getItemId()) {
		case R.id.action_delete:
			try {
				MyApplicationObject.getInstance().removeContact(this.contact);
				Toast.makeText(getApplicationContext(), "Contact deleted",
						Toast.LENGTH_SHORT).show();
				onBackPressed();
				Log.d(TAG,
						"Activity onOptionsItemSelected contact succesfully deleted");
			} catch (Exception e) {
				Toast.makeText(getApplicationContext(), e.getMessage(),
						Toast.LENGTH_SHORT).show();
				Log.e(TAG,
						"Activity onOptionsItemSelected contact delete error"
								+ e.getMessage());
			}

			break;
		default:
			break;
		}
		Log.d(TAG, "Activity onOptionsItemSelected ended");
		return true;

	}
}
