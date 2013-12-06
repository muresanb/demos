package com.example.testproject.adapters;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.testproject.R;
import com.example.testproject.datastructure.Contact;

public class MultiselectContactAdapter extends ArrayAdapter<Contact> {
	private List<Contact> data = null;
	private Context context = null;

	private SparseBooleanArray mSelectedItemsIds;

	public MultiselectContactAdapter(Activity context, int resId,
			List<Contact> data) {
		super(context, resId, data);
		mSelectedItemsIds = new SparseBooleanArray();
		this.context = context;
		this.data = data;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.contact_row, parent, false);
		TextView name = (TextView) rowView
				.findViewById(R.id.textView_ActivityA_name);
		TextView phone = (TextView) rowView
				.findViewById(R.id.textView_ActivityA_phone);
		TextView email = (TextView) rowView
				.findViewById(R.id.textView_ActivityA_email);

		Contact person = data.get(position);
		name.setText(person.getName());
		phone.setText(person.getPhoneNr());
		email.setText(person.getEmail());

		rowView.setBackgroundColor(mSelectedItemsIds.get(position) ? context
				.getResources().getColor(R.color.rosu) : Color.TRANSPARENT);

		return rowView;
	}

	@Override
	public void add(Contact laptop) {
		data.add(laptop);
		notifyDataSetChanged();
		Toast.makeText(context, data.toString(), Toast.LENGTH_LONG).show();
	}

	@Override
	public void remove(Contact object) {
		// super.remove(object);
		data.remove(object);
		notifyDataSetChanged();
	}

	public List<Contact> getData() {
		return data;
	}

	public void toggleSelection(int position) {
		selectView(position, !mSelectedItemsIds.get(position));
	}

	public void removeSelection() {
		mSelectedItemsIds = new SparseBooleanArray();
		notifyDataSetChanged();
	}

	public void selectView(int position, boolean value) {
		if (value)
			mSelectedItemsIds.put(position, value);
		else
			mSelectedItemsIds.delete(position);

		notifyDataSetChanged();
	}

	public int getSelectedCount() {
		return mSelectedItemsIds.size();
	}

	public SparseBooleanArray getSelectedIds() {
		return mSelectedItemsIds;
	}
}
