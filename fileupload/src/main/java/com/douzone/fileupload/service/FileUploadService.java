package com.douzone.fileupload.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileUploadService {

	private static final String SAVE_PATH = "D:\\mysite-upload";
	private static final String URL = "/images";
	
	public String restore(MultipartFile multipartFile) {
		String url = "";
		try {
		if(multipartFile.isEmpty())
			return null;
		
		String originFilename = multipartFile.getOriginalFilename();
		
		// 확장자 이름만 추출
		int lastIndex = originFilename.lastIndexOf('.');
		String extName = originFilename.substring(lastIndex+1);
		
		String saveFilename = generateSaveFilename(extName);
		
		long fileSize = multipartFile.getSize();
		
		System.out.println("###### " + originFilename);
		System.out.println("###### " + saveFilename);
		System.out.println("###### " + fileSize);
		
		byte[] fileData = multipartFile.getBytes();
		OutputStream os = new FileOutputStream(SAVE_PATH + "/" + saveFilename);
		os.write(fileData);
		os.close();
		
		url = URL + "/" + saveFilename;
		
		} catch(IOException ex) {
			throw new RuntimeException("fileupload error:" + ex);
		}
		
		return url;
	}

	private String generateSaveFilename(String extName) {
		String filename = "";
		
		Calendar calendar = Calendar.getInstance();
		filename += calendar.get(Calendar.YEAR);
		filename += calendar.get(Calendar.MONTH);
		filename += calendar.get(Calendar.DATE);
		filename += calendar.get(Calendar.HOUR);
		filename += calendar.get(Calendar.MINUTE);
		filename += calendar.get(Calendar.SECOND);
		filename += calendar.get(Calendar.MILLISECOND);
		filename += ("." + extName);
		
		return filename;
	}
	
}
