package com.bridgelabz.bookstore.service;

public class MessageReference {
    public static final String USER_NOT_EXISTING = " user not existing ";
    public static final String INVALID_TOKEN = " invalid token ";
    public static final String REGISTRATION_MAIL_TEXT = "\t validate your email\\n\"+\"http://localhost:8080/validate?token=";
    public static final String Verfiy_MAIL_TEXT = "\t Verficiaton email \n"+"http://localhost:3000/ResetPassword?token=";
	public static final String USER_UPDATE_SUCCESSFULLY = "user updated successfully";
	public static final String PASSWORD_CHANGE_SUCCESSFULLY = "password Change  successfully";
	public static final String PASSWORD_IS_NOT_MATCHING = "password is not matching";
	public static final String EMAIL_VERFIY = "email verified successfully";
	public static final String NOT_VERFIY_EMAIL = "kindly verfiy email your account";
	public static final String ORDER_PLACED = "Order placed successfully";
	public static final String RECORD_UPDATED = "record updated successfully";
	public static final String RECORD_DELETED = "Book deleted successfully";
	public static final String VERIFIED_ACCOUNT = "Congratulation account is verified";
	public static final String Book_Removed = "Book Removed Successfully";
	public static final String ADDED_WISHLIST = "Added to Wishlist successfully";
	public static final String REMOVE_FROM_WISHLIST = "Removed from Wishlist successfully";
    public static final String FILE_UPLOADED = "File uploaded and saved in db";
	public static final String UPLOADED_SINGLE_BOOK = "Single book record inserted";
	public static final String DELETE_USER = "user deleted where id is : ";
}
