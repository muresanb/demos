package com.example.testproject.gui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.ActionMode;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.example.testproject.MyApplicationObject;
import com.example.testproject.R;
import com.example.testproject.adapters.MultiselectContactAdapter;
import com.example.testproject.datastructure.Contact;

public class ActivityA extends SherlockActivity implements OnItemClickListener {
	private final static String TAG = ActivityA.class.getSimpleName();
	private ListView list = null;
	private ActionMode mActionMode;
	private MultiselectContactAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		Log.d(TAG, "onCreate - started");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_a);

		this.list = (ListView) findViewById(R.id.listView_ActivityA_contacts);

		enableMultipleSelection();

		Log.d(TAG, "onCreate - ended");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getSupportMenuInflater();
		inflater.inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_ascending:
			MyApplicationObject.getInstance().order(true);
			this.adapter.notifyDataSetChanged();
			// list.getAdapter().notifyDataSetChanged();
			break;
		case R.id.action_descending:
			MyApplicationObject.getInstance().order(false);
			this.adapter.notifyDataSetChanged();
			break;
		default:
			break;
		}
		return true;

	}

	private void enableSimpleSelection() {
		this.adapter = new MultiselectContactAdapter(this,
				R.layout.contact_row, MyApplicationObject.getInstance()
						.getList());
		list.setAdapter(adapter);
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Toast.makeText(
						getApplicationContext(),
						"Click ListItem Number "
								+ position
								+ " Name: "
								+ MyApplicationObject.getInstance().getList()
										.get(position).getName(),
						Toast.LENGTH_LONG).show();
				Log.d(TAG, "clicked");
				Intent intent = new Intent(ActivityA.this, ActivityB.class);
				Log.d(TAG, "intent created");
				Contact selectedContact = MyApplicationObject.getInstance()
						.getList().get(position);
				Log.d(TAG, "contact selected");
				intent.putExtra("name", selectedContact.getName());
				intent.putExtra("phoneNr", selectedContact.getPhoneNr());
				intent.putExtra("email", selectedContact.getEmail());
				Log.d(TAG, "intent ready to send");
				startActivity(intent);
				Log.d(TAG, "sent intent");
			}
		});

	}

	private void enableMultipleSelection() {
		adapter = new MultiselectContactAdapter(this, R.layout.contact_row,
				MyApplicationObject.getInstance().getList());
		list.setAdapter(adapter);
		list.setOnItemClickListener(this);

		list.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				onListItemSelect(position);
				return false;
			}
		});
	}

	private void onListItemSelect(int position) {
		adapter.toggleSelection(position);
		boolean hasCheckedItems = adapter.getSelectedCount() > 0;

		if (hasCheckedItems && mActionMode == null) {
			// there are some selected items, start the actionMode
			mActionMode = startActionMode(new ActionModeCallback());
		} else {
			if (!hasCheckedItems && mActionMode != null) {
				// there no selected items, finish the actionMode
				mActionMode.finish();
			}
		}
		if (mActionMode != null) {
			mActionMode.setTitle(String.valueOf(adapter.getSelectedCount())
					+ " selected");
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		this.adapter.notifyDataSetChanged();
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int position,
			long arg3) {
		if (mActionMode == null) {

			Toast toast = Toast.makeText(
					getApplicationContext(),
					"Item "
							+ (position + 1)
							+ ": "
							+ MyApplicationObject.getInstance().getList()
									.get(position), Toast.LENGTH_SHORT);
			toast.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
			toast.show();

		} else
			onListItemSelect(position);

	}

	private class ActionModeCallback implements ActionMode.Callback {

		@Override
		public boolean onCreateActionMode(ActionMode mode, Menu menu) {
			// inflate contextual menu
			mode.getMenuInflater().inflate(R.menu.menu2, menu);
			return true;
		}

		@Override
		public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
			return false;
		}

		@Override
		public boolean onActionItemClicked(ActionMode mode, MenuItem item) {

			switch (item.getItemId()) {
			case R.id.action_delete:
				// retrieve selected items and delete them out
				SparseBooleanArray selected = adapter.getSelectedIds();
				for (int i = (selected.size() - 1); i >= 0; i--) {
					if (selected.valueAt(i)) {
						Contact selectedItem = adapter.getItem(selected
								.keyAt(i));
						MyApplicationObject.getInstance().removeContact(
								selectedItem);
					}
				}
				adapter.notifyDataSetChanged();
				mode.finish(); // Action picked, so close the CAB
				return true;
			default:
				return false;
			}

		}

		@Override
		public void onDestroyActionMode(ActionMode mode) {
			// remove selection
			adapter.removeSelection();
			mActionMode = null;
		}
	}
}
