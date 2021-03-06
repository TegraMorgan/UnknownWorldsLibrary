package server.utils;

import server.model.Customer;

public class validations {

	public static boolean testCustomer(Customer cust) {

		if (testUserName(cust.getUsername()))
			if (testPassword(cust.getPassword()))
				if (testNickName(cust.getNickname()))
					if (testEmail(cust.getEmail()))
						if (testDescription(cust.getDescription()))
							if (testNewBNum(Integer.toString(cust.getBlNum())))
								if (testNewPhone(cust.getPhone()))
									if(testNewStreet(cust.getStName()))
										if(testNewZip(cust.getZip()))
											return true;
		return false;

	}

	public static boolean testUserName(String userName) {
		if (userName != null) {
			int len = userName.length();
			if (len > 10)
				return false;
			String regex = "^[a-zA-Z0-9]*$";
			if (!userName.matches(regex))
				return false;
			return true;
		}
		return false;
	}

	public static boolean testEmail(String email) {
		if (email != null) {
			String[] parts = email.split("@");

			if (parts[0] == null || parts[0].length() < 0)
				return false;
			if (parts[1] == null || parts[1].length() < 0)
				return false;
			return true;
		}
		return false;
	}

	public static boolean testNewStreet(String str) {
		if (str != null) {
			int len = str.length();
			if (len < 4)
				return false;
			String regex = "^[a-zA-Z0-9]*$";
			if (!str.matches(regex))
				return false;
			return true;
		}
		return false;
	}

	public static boolean testNewBNum(String str) {
		if (str != null) {
			String regex = "^[0-9]*$";

			if (str.matches(regex))
				return true;
			return false;
		}
		return false;
	}

	public static boolean testNewZip(String str) {
		if (str != null) {
			String regex = "^[0-9]*$";
			if (!str.matches(regex))
				return false;
			if (str.length() != 7)
				return false;
			return true;
		}
		return false;
	}

	public static boolean testNewPhone(String str) {
		if (str != null) {
			str = str.replace("-", "");
			str = str.replace(" ", "");
			String regex = "^[0-9]*$";
			if (!str.matches(regex))
				return false;
			String[] nmbrs = str.split("");
			if (nmbrs[0].compareTo("0") != 0)
				return false;
			if (nmbrs[1].compareTo("5") != 0 && str.length() != 9)
				return false;
			if (nmbrs[1].compareTo("5") == 0 && str.length() != 10)
				return false;
			return true;
		}
		return false;
	}

	public static boolean testPassword(String str) {
		if (str != null) {
			int len = str.length();
			if (len > 8)
				return false;
			return true;
		}
		return false;
	}

	public static boolean testNickName(String str) {
		if(str!=null)
		{
			int len = str.length();
			if (len > 20)
				return false;
			return true;
		}
		return false;
	}

	public static boolean testDescription(String str) {
		if (str != null) {
			int len = str.length();
			if (len > 50)
				return false;
			return true;
		} else
			return true;
	}

}