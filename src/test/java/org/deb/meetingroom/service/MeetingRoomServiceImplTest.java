package org.deb.meetingroom.service;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class MeetingRoomServiceImplTest {

	MeetingRoomService meetingRoomService = new MeetingRoomServiceImpl();
	FileContentService fileContentService = new FileContentServiceImpl();

	@Test
	public void testGetNoOfRooms_Example1() throws IOException {
		List<String> records = fileContentService.getFileContent("./src/test/resources/Example1.txt");
		assertEquals(1, meetingRoomService.getNoOfRooms(records));
	}

	
	@Test
	public void testGetNoOfRooms_Example2() throws IOException {
		List<String> records = fileContentService.getFileContent("./src/test/resources/Example2.txt");
		assertEquals(2, meetingRoomService.getNoOfRooms(records));
	}
	
	@Test
	public void testGetNoOfRooms_Example3() throws IOException {
		List<String> records = new ArrayList<>();
		records.add("09:00-10:00");
		records.add("09:30-11:30");
		assertEquals(2, meetingRoomService.getNoOfRooms(records));
	}
	
	@Test
	public void testGetNoOfRooms_Example4() throws IOException {
		List<String> records = new ArrayList<>();
		records.add("09:30-11:30");
		records.add("09:00-10:00");
		assertEquals(2, meetingRoomService.getNoOfRooms(records));
	}
	
	@Test
	public void testGetNoOfRooms_Example5() throws IOException {
		List<String> records = new ArrayList<>();
		records.add("09:30-11:30");
		records.add("11:00-12:00");
		assertEquals(2, meetingRoomService.getNoOfRooms(records));
	}
	
	@Test
	public void testGetNoOfRooms_8hour() throws IOException {
		List<String> records = new ArrayList<>();
		records.add("09:00-18:00");
		records.add("15:30-16:00");
		records.add("15:45-16:15");
		assertEquals(3, meetingRoomService.getNoOfRooms(records));
	}
}
