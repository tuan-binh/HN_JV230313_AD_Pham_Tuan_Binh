package exam_2;

import java.util.Stack;

public class Main {
	public static void main(String[] args) {
		System.out.print("Nhập vào một dãy số: ");
		String number = Validate.getString();
		if (checkNumber(number)) {
			if (number.length() == 10) {
				if (checkISBN(number)) {
					System.out.println(number + " Là số ISBN");
				} else {
					System.out.println(number + " Không phải là số ISBN");
				}
			} else {
				System.out.println("Đây không phải là số ISBN vì có nhiều hơn 10 chữ số");
			}
		} else {
			System.out.println("Đây không phải là số");
		}
	}
	
	public static boolean checkNumber(String number) {
		for (int i = 0; i < number.length(); i++) {
			if (!Character.isDigit(number.charAt(i))) {
				return false;
			}
		}
		return true;
	}
	
	public static boolean checkISBN(String number) {
		Stack<Character> list = new Stack<>();
		for (int i = 0; i < number.length(); i++) {
			list.push(number.charAt(i));
		}
		int result = 0;
		int i = 1;
		while (!list.isEmpty()) {
			result += i * Integer.parseInt(String.valueOf(list.pop()));
			i++;
		}
		if (result % 11 == 0) {
			return true;
		}
		return false;
	}
}
