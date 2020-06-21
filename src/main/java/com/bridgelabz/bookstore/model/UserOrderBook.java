package com.bridgelabz.bookstore.model;

import javax.persistence.OneToOne;

public class UserOrderBook {
	
	@OneToOne
	Book book;
	
	int quantity;
	

}
