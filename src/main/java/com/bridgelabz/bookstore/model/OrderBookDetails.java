package com.bridgelabz.bookstore.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity
@Setter
@Getter
@NoArgsConstructor
@ToString
public class OrderBookDetails {
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
    @ManyToOne
    private User user;
    
    @OneToMany
    private List<Cart> bookList ;
    
    private int totalPrice;
	/*
	 * public int orderId; public int bookIds; public int bookQuantity; public
	 * Double orderPrice; public String customerName; public String mobileNo; public
	 * String pincode; public String locality; public String address; public String
	 * mailId; public String city; public String town;
	 */

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Cart> getBookList() {
		return bookList;
	}

	public void setBookList(List<Cart> bookList) {
		this.bookList = bookList;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	@Override
	public String toString() {
		return "OrderBookDetails [id=" + id + ", user=" + user + ", bookList=" + bookList + ", totalPrice=" + totalPrice
				+ "]";
	}
}
