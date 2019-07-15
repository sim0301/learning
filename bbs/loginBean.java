package bbs;

public class loginBean {
	private String ID;
	private String pass;
	
	final String ID_ok = "jsp";
	final String pass_ok = "asdg";
	
	public boolean checkUser() {
		if (ID.equals(ID_ok) && pass.equals(pass_ok)) {
			return true;
		}
		else {
			return false;
		}
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

}
