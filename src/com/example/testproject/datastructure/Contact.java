package com.example.testproject.datastructure;

import java.io.Serializable;

public class Contact implements Serializable {
	private static final long serialVersionUID = 9106173246042670811L;
	private String name = null;
	private String phoneNr = null;
	private String email = null;

	public Contact() {
		this(null, null, null);
	}

	public Contact(String name, String phoneNr, String email) {
		this.name = name;
		this.phoneNr = phoneNr;
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNr() {
		return phoneNr;
	}

	public void setPhoneNr(String phoneNr) {
		this.phoneNr = phoneNr;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		 if(o instanceof Contact)
	        {
	            Contact p = (Contact) o;
	            return (p.getName().equals(this.name) && p.getEmail().equals(this.email) && p.getPhoneNr().equals(phoneNr));
	        }
	        else return super.equals(o);
	}
}
