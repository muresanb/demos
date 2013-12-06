package com.example.testproject;

import java.util.Comparator;


import com.example.testproject.datastructure.Contact;

public class ContactComparator implements Comparator<Contact>{
	@Override
	public int compare(Contact contact1, Contact contact2) {
	
	        if(contact1.getName().equalsIgnoreCase(contact2.getName()))
	        {
	            return contact1.getName().compareTo(contact2.getName());
	        }
	        return contact1.getName().compareTo(contact2.getName());
	    
	}
}
