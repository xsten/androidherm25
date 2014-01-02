package net.stenuit.xavier.androidherm25.db;

import java.util.Date;

public class Aliases {
	private int idAliases;
	private String unixUser;
	private String email;
	private int aliasSerial;
	private String alias;
	private Date validFrom;
	private Date validUntil;
	public int getIdAliases() {
		return idAliases;
	}
	public void setIdAliases(int idAlias) {
		this.idAliases = idAlias;
	}
	public String getUnixUser() {
		return unixUser;
	}
	public void setUnixUser(String unixUser) {
		this.unixUser = unixUser;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getAliasSerial() {
		return aliasSerial;
	}
	public void setAliasSerial(int aliasSerial) {
		this.aliasSerial = aliasSerial;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public Date getValidFrom() {
		return validFrom;
	}
	public void setValidFrom(Date validFrom) {
		this.validFrom = validFrom;
	}
	public Date getValidUntil() {
		return validUntil;
	}
	public void setValidUntil(Date validUntil) {
		this.validUntil = validUntil;
	}
	
	
}
