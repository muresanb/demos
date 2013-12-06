package com.example.testproject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import android.app.Application;

import com.example.testproject.datastructure.Contact;

public class MyApplicationObject extends Application {
	private static MyApplicationObject instance = null;
	private List<Contact> list = null;
	private String str = null;

	@Override
	public void onCreate() {

		super.onCreate();
		this.list = new ArrayList<Contact>();

		for (int i = 0; i < 10; i++) {
			Contact contact = new Contact();
			contact.setEmail("bobby" + (10 - i) + "@gmail.com");
			contact.setName("Bobby" + (10 - i));
			contact.setPhoneNr("+4074564646" + (10 - i));
			this.list.add(contact);
		}
		instance = this;
	}

	public static MyApplicationObject getInstance() {
		return instance;
	}

	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}

	public void removeContact(Contact contact) {
		this.list.remove(contact);
	}

	public void order(boolean asc) {
		Comparator<Contact> comp = asc ? new ContactComparator() : Collections
				.reverseOrder(new ContactComparator());
		Collections.sort(this.list, comp);
	}

	public List<Contact> getList() {
		return list;
	}

	public void setList(List<Contact> list) {
		this.list = list;
	}

	public Contact get(int index) {
		return this.list.get(index);
	}

}