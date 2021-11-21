package org.deb.meetingroom.service;

import java.io.IOException;
import java.util.List;

public interface FileContentService {
	
	List<String> getFileContent(String fileName) throws IOException;

}
