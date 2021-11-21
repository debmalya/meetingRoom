package org.deb.meetingroom.service;

import java.util.ArrayList;
import java.util.List;

import org.deb.meetingroom.util.TimeUtil;

public class MeetingRoomServiceImpl implements MeetingRoomService {

//  This will store bookings details. Bookings will be maintained hour wise. 
//	All bookings from 09:00 - 10:00. will be available at bookings.get(9).
//	Then each element of will be an integer array of size 2. First element booking start minute
//	second element will be booking end minute. For 09:00-10:00 will be stored at index 9  [0,59]
	List<List<int[]>> bookings = new ArrayList<>(24);

	public int getNoOfRooms(List<String> meetingSchedule) {

		for (int i = 0; i < 24; i++) {
			bookings.add(new ArrayList<>());
		}

		return creatSchedule(meetingSchedule);
	}

	private int creatSchedule(List<String> meetingSchedule) {
		int maxMeetingRoom = 0;

		for (String eachMeeting : meetingSchedule) {
			String[] times = eachMeeting.split("-");
			int[] start = TimeUtil.parseTime(times[0]);
			int[] end = TimeUtil.parseTime(times[1]);

			if (start[0] == end[0]) {
				// within same hour (e.g. 09:00-09:30)
				List<int[]> hourlyMeetingList = getHourlyBooking(start[0]);
				maxMeetingRoom = Math.max(maxMeetingRoom,
						bookMeetingRoom(start[1], end[1], hourlyMeetingList, maxMeetingRoom));
			} else if (end[0] - start[0] == 1 && end[1] - start[1] == 0) {
				// 09:00 - 10:00 , will be stored at index 9 of bookings as [0,59]
				List<int[]> hourlyMeetingList = getHourlyBooking(start[0]);
				maxMeetingRoom = Math.max(maxMeetingRoom,
						bookMeetingRoom(start[1], 59, hourlyMeetingList, maxMeetingRoom));
			} else {
				for (int i = start[0]; i < end[0]; i++) {
					List<int[]> hourlyMeetingList = getHourlyBooking(i);
					if (i < end[0] || i == end[0] - 1 && end[1] == 0) {
						maxMeetingRoom = Math.max(maxMeetingRoom,
								bookMeetingRoom(start[1], 59, hourlyMeetingList, maxMeetingRoom));
					}
					
					if (end[1] > 0) {
						// for bookings like 09:30-11:30
						// to add bookings for hour 11.
						hourlyMeetingList = getHourlyBooking(end[0]);
						maxMeetingRoom = Math.max(maxMeetingRoom,
								bookMeetingRoom(start[1], end[1], hourlyMeetingList, maxMeetingRoom));
						
					}
				}

			}
		}

		return maxMeetingRoom;
	}

	/**
	 * 
	 * @param from              - meeting starting time.
	 * @param to                - meeting end time.
	 * @param hourlyMeetingList - all the meetings within that hour.
	 * @param maxMeetingRoom    - minimum number of rooms required to conduct all
	 *                          the meetings.
	 * @return updated max meeting room.
	 */
	private int bookMeetingRoom(int from, int to, List<int[]> hourlyMeetingList, int maxMeetingRoom) {
		long alreadyScheduledMeeting = hourlyMeetingList.stream()
				.filter(eachMeetingSchedule -> from >= eachMeetingSchedule[0] || to <= eachMeetingSchedule[1]).count();
		hourlyMeetingList.add(new int[] { from, to });

		
		return (int) (alreadyScheduledMeeting + 1);
	}

	/**
	 * Get booking details for the specified hour.
	 * 
	 * @param hour for which booking will be retrieved.
	 * @return all the bookings of that hour.
	 */
	private List<int[]> getHourlyBooking(int hour) {
		return bookings.get(hour);
	}

}
