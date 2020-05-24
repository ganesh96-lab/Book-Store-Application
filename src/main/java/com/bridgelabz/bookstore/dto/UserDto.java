package com.bridgelabz.bookstore.dto;

import javax.validation.constraints.Email;

public class UserDto {
    public int id;
    public String first_Name;
    public String last_Name;
    @Email
    public String email;
    public String password;
    public String mobile;
    public String roll;
}
