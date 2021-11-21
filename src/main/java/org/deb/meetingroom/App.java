package org.deb.meetingroom;

import java.io.IOException;
import java.util.List;

import org.deb.meetingroom.service.FileContentService;
import org.deb.meetingroom.service.FileContentServiceImpl;
import org.deb.meetingroom.service.MeetingRoomService;
import org.deb.meetingroom.service.MeetingRoomServiceImpl;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException
    {
        if (args.length == 0) {
        	System.err.println("Usage: org.deb.meetingroom.App <File Location>");
        }else {
        	FileContentService fileContentService = new FileContentServiceImpl();
        	List<String> recordList = fileContentService.getFileContent(args[0]);
        	MeetingRoomService meetingRoomService = new MeetingRoomServiceImpl();
        	System.out.println(meetingRoomService.getNoOfRooms(recordList));
        }
    }
}
