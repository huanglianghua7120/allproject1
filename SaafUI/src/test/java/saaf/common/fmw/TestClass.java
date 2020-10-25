package saaf.common.fmw;

import java.math.BigDecimal;

public class TestClass {

	// private final static Map<String,Object> map = new HashMap<>();
	//
	// public static void main(String[] args) {
	// String s = "79S000027";
	// String regex = "^[0-9]+(\\.[0-9]+)?$";
	// System.out.println(s.matches(regex));
	// }


	public static void main(String[] args) {
		BigDecimal a = new BigDecimal(100000);
		for (int i = 0; i < 60; i++) {
			a = a.multiply(new BigDecimal(1.1));
		}
		System.out.println(a.setScale(2, BigDecimal.ROUND_DOWN));
		System.out.println(a.setScale(2, BigDecimal.ROUND_UP));
	}

}
