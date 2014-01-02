package net.stenuit.xavier.androidherm25.db;

import java.util.Date;

public class RegisteredUser {
	private int idRegisteredUser;
	private String unixUser;
	private String firstName;
	private String surName;
	private String forwardMail;
	private String address;
	private String phone;
	private int Language;
	private Date creationDate;
	private Date updateDate;
	
	public int getIdRegisteredUser() {
		return idRegisteredUser;
	}
	public void setIdRegisteredUser(int idRegisteredUser) {
		this.idRegisteredUser = idRegisteredUser;
	}
	public String getUnixUser() {
		return unixUser;
	}
	public void setUnixUser(String unixUser) {
		this.unixUser = unixUser;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getSurName() {
		return surName;
	}
	public void setSurName(String surName) {
		this.surName = surName;
	}
	public String getForwardMail() {
		return forwardMail;
	}
	public void setForwardMail(String forwardMail) {
		this.forwardMail = forwardMail;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getLanguage() {
		return Language;
	}
	public void setLanguage(int language) {
		Language = language;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	
	public String toString()
	{
		return unixUser+"("+firstName+" "+surName+")"+" - "+forwardMail;
	}
}
