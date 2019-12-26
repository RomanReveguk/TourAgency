package travelagency.modelsdata;

import java.util.List;

public class UserData {
	
	private int user_id;
	private String user_name;
	private String user_surname;
	private String user_date_of_birth;
	private String mail;
	private String password;
	private int roleId;
	private List<TourData> tours;
	
	public String getUser_surname() {
		return user_surname;
	}
	public void setUser_surname(String user_surname) {
		this.user_surname = user_surname;
	}
	public String getUser_date_of_birth() {
		return user_date_of_birth;
	}
	public void setUser_date_of_birth(String user_date_of_birth) {
		this.user_date_of_birth = user_date_of_birth;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public List<TourData> getTours() {
		return tours;
	}
	public void setTours(List<TourData> tours) {
		this.tours = tours;
	}
	
	@Override
	public String toString() {
		return "UserData [user_id=" + user_id + ", user_name=" + user_name + ", user_surname=" + user_surname
				+ ", user_date_of_birth=" + user_date_of_birth + ", mail=" + mail + ", roleId="
				+ roleId + "]";
	}
}