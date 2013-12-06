package com.example.testproject.adapters;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.testproject.R;
import com.example.testproject.datastructure.Contact;

public class ContactAdapter extends ArrayAdapter<Contact> {
	private List<Contact> data = null;
	private Context context = null;

	public ContactAdapter(Context context, List<Contact> data) {
		super(context, R.layout.contact_row, data);
		this.data = data;
		this.context = context;
	}

	@Override
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

		return rowView;
	}
}
