package org.deb.meetingroom.service;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;

import org.junit.Test;

public class FileContentServiceImplTest {

	FileContentService fileContentService = new FileContentServiceImpl();

	@Test
	public void testGetFileContent() throws IOException {
		List<String> records = fileContentService.getFileContent("./src/test/resources/Example1.txt");
		assertTrue(!records.isEmpty());
		assertTrue(records.size() == 2);
		assertEquals("09:00-10:00", records.get(0));
		assertEquals("10:00-11:00", records.get(1));
	}

}
