package week16;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.StringTokenizer;

public class dday_1308 {	static String title;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		LocalDate startDate = LocalDate.of(
			Integer.parseInt(st.nextToken()),
			Integer.parseInt(st.nextToken()),
			Integer.parseInt(st.nextToken()));

		st = new StringTokenizer(br.readLine());

		LocalDate endDate = LocalDate.of(
			Integer.parseInt(st.nextToken()),
			Integer.parseInt(st.nextToken()),
			Integer.parseInt(st.nextToken()));

		if ((endDate.getYear() - startDate.getYear() > 1000)
			|| (endDate.getYear() - startDate.getYear() == 1000 && endDate.getMonthValue() > startDate.getMonthValue())
			|| (endDate.getYear() - startDate.getYear() == 1000 && endDate.getMonthValue() == startDate.getMonthValue() && endDate.getDayOfMonth() >= startDate.getDayOfMonth()))
			System.out.println("gg");
		else {
			long startDay = getDayCount(startDate.getYear(), startDate.getMonthValue(), startDate.getDayOfMonth());
			long endDay = getDayCount(endDate.getYear(), endDate.getMonthValue(), endDate.getDayOfMonth());
			System.out.println("D-" + (endDay - startDay));
		}
	}

	private static int[] getDayArray(int year) {
		int[] days = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
			days[1] = 29;
		}
		return days;
	}

	private static long getDayCount(int year, int month, int day) {
		long dayCount = 0;
		int[] date;
		for (int i = 1; i < year; i++) {
			date = getDayArray(i);
			for (int j = 0; j < 12; j++) {
				dayCount += date[j];
			}
		}

		date = getDayArray(year);
		for (int i = 0; i < month - 1; i++) {
			dayCount += date[i];
		}

		dayCount += day;

		return dayCount;
	}
}
