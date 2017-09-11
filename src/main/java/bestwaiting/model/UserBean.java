package bestwaiting.model;

import java.io.Serializable;

public class UserBean implements Serializable {

	/**
	 * �û���Ϣ
	 */
	private static final long serialVersionUID = -6465264325743081862L;
	private int id;
	private String username;
	private String userpwd;
	private String user_phone;
	private String user_email;
	private String user_note;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserpwd() {
		return userpwd;
	}

	public void setUserpwd(String userpwd) {
		this.userpwd = userpwd;
	}

	public String getUser_phone() {
		return user_phone;
	}
	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}
	public String getUser_email() {
		return user_email;
	}
	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}
	public String getUser_note() {
		return user_note;
	}
	public void setUser_note(String user_note) {
		this.user_note = user_note;
	}
	@Override
	public String toString(){
		return "UserBean [id="+id+"user_name="+username+"]";
	}

}
