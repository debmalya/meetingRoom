package org.deb.meetingroom.service;

import java.util.List;

public interface MeetingRoomService {
   int getNoOfRooms(List<String> meetingSchedule);
}
