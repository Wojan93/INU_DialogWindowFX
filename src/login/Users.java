package login;

import java.util.HashMap;

public class Users {

	public static HashMap<String, String> users = new HashMap<String, String>();

	public static void addUser(String user, String pass) {
		users.put(user, pass);
	}

	public static void init(Environment e) {
		users.clear();

		if (e.equals(e.Deweloperskie)) {
			addUser("adam.nowak", "adam");
			addUser("ewa.cudna", "ewa");
			addUser("jan.kowalski", "jan");
		} else if (e.equals(e.Testowe)) {
			addUser("jan.nowak", "jan");
			addUser("paulina.cudna", "paulina");
			addUser("adam.kowalski", "ewa");
		} else if (e.equals(e.Produkcyjne)) {
			addUser("zbyszek.kowalski", "zbyszek");
			addUser("piotr.kowalski", "piotr");
			addUser("ewa.kowalska", "ewa");
		}
	}

	public static boolean passCheck(String user, String pass) {

		try {
			if (users.get(user).equals(pass)) {
				return true;
			} else {
				return false;
			}
		} catch (NullPointerException e) {
			return false;
		}

	}

}
