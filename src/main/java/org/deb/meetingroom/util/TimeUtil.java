package org.deb.meetingroom.util;

public class TimeUtil {

	/**
	 * 
	 * @param time string 24 hour format hh:mm 
	 * @return an integer array first element is hour. Second element is minute.
	 */
	public static int[] parseTime(String time) {
		String[] hhmm = time.split(":");
		int[] hourMinute = new int[2];
		hourMinute[0] = Integer.parseInt(hhmm[0]);
		hourMinute[1] = Integer.parseInt(hhmm[1]);

		return hourMinute;

	}
	
	private TimeUtil() {
		
	}
}
