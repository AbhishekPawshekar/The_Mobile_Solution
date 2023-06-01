package com.example.the_mobile_solution;

public class usermemberretriveclass {

    String First_Name,Last_Name,Phone,Email,User_Name,Password,Address,Member;

    usermemberretriveclass() {

    }

    public usermemberretriveclass(String first_Name, String last_Name, String phone, String email, String user_Name, String password, String address, String member) {
        First_Name = first_Name;
        Last_Name = last_Name;
        Phone = phone;
        Email = email;
        User_Name = user_Name;
        Password = password;
        Address = address;
        Member = member;
    }

    public String getMember() {
        return Member;
    }

    public void setMember(String member) {
        Member = member;
    }

    public void setFirst_Name(String first_Name) {
        First_Name = first_Name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getFirst_Name() {
        return First_Name;
    }

    public void setFist_Name(String first_Name) {
        First_Name = first_Name;
    }

    public String getLast_Name() {
        return Last_Name;
    }

    public void setLast_Name(String last_Name) {
        Last_Name = last_Name;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getUser_Name() {
        return User_Name;
    }

    public void setUser_Name(String user_Name) {
        User_Name = user_Name;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
