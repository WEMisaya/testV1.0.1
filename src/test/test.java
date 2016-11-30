package test;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import test.enumTest.Color;

public class test {

	public test() {
		System.out.println("holle world");
	}

	public test(String str) {
		System.out.println("structs:" + str);
	}

	public static String changeToPercent(String str) {
		// if (isNull(str)) {
		// return "0";
		// }
		BigDecimal bigdec = new BigDecimal(str).multiply(new BigDecimal(100));
		NumberFormat nf = NumberFormat.getInstance();
		return bigdec.toPlainString();
	}

	public static void main(String[] args) {
		String date = parseNOToDate("161128");
		System.out.println(date);
		String date2 = parseNOToDate2("2016-11-28");
		System.out.println(date2);
	}
	
	
	
	
	
	public static String parseNOToDate2(String No) {
		Date date = null;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd").parse(No);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		} 
		String now = new SimpleDateFormat("yyMMdd").format(date);
		return now;
	} 
	public static String parseNOToDate(String No) {
		Date date = null;
		try {
			date = new SimpleDateFormat("yyMMdd").parse(No);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		} 
		String now = new SimpleDateFormat("yyyy-MM-dd").format(date);
		return now;
	} 

	
	
	
	
	
	
	
	
	public static void main1(String[] args) {
		System.out.println(changeToPercent("0.00055"));

		String string = "0i1i2i3iii4";
		System.out.println(string.split("i").length);

		String pwd = "987654321";
		System.out.println(pwd.substring(pwd.length() - 8));

		String strDate = "2099-12-30";
		try {
			System.out.println(new SimpleDateFormat("yyyy-MM-dd").parse(strDate).getTime());
			System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(new Date(4102243200000L)));
			System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(new Date(64063065600000L)));
		} catch (ParseException e) {
			// 64063065600000
			// 4102243200000
			e.printStackTrace();
		}
		Date now = new Date();
		Date date = new Date(now.getTime() + 000010); // 10分钟前的时间
		if (now.before(date)) {
			long time = date.getTime() - now.getTime();
			System.err.println("yes : time" + (time / 60000) + ":" + (time / 1000 % 60) + "." + time % 1000);
		} else
			System.err.println("no : time" + (date.getTime() - now.getTime()));
	}

}
